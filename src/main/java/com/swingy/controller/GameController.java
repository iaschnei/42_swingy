package com.swingy.controller;

import com.swingy.model.artifact.Artifact;
import com.swingy.model.hero.Hero;
import com.swingy.model.map.GameMap;
import com.swingy.model.villain.Villain;
import com.swingy.service.BattleService;
import com.swingy.service.MapService;
import com.swingy.storage.HeroDbRepository;
import com.swingy.view.View;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class GameController {

    View currentView;
    Hero currentHero;
    private final HeroDbRepository heroRepository;
    private final HeroLoader heroLoader;
    public HeroCreationInput heroCreationInput;
    private GameMap gameMap;
    private String savedDirection;
    private Villain savedVillain;
    private int savedEscapeChance;

    public GameController(View viewType) {
        this.currentView = viewType;
        this.heroRepository = new HeroDbRepository();
        this.heroLoader = new HeroLoader(heroRepository, currentView);
        this.currentView.requestLoadOrCreate(this);
    }

    public void onLoadOrCreateChoice(String choice) {
        try {
            switch (choice) {
                case "Load_save": {
                    List<String> saves = heroRepository.getHeroNames();
                    if (saves.isEmpty()) {
                        this.currentView.showError("No saves found!");
                        this.currentView.requestLoadOrCreate(this);
                    } else {
                        this.currentView.requestHeroSelection(saves);
                    }
                    break;
                }
                case "Create_save":
                    if (!heroRepository.canAddHero()) {
                        this.currentView.showError("Maximum number of saves reached (3). Please delete a save first.");
                        this.currentView.requestLoadOrCreate(this);
                        return;
                    }
                    this.heroCreationInput = new HeroCreationInput(this);
                    this.currentView.requestHeroName();
                    break;
                case "Delete_save": {
                    List<String> saves = heroRepository.getHeroNames();
                    if (saves.isEmpty()) {
                        this.currentView.showError("No saves to delete!");
                        this.currentView.requestLoadOrCreate(this);
                    } else {
                        this.currentView.requestSaveToDelete(saves);
                    }
                    break;
                }
                default:
                    this.currentView.showError("Invalid choice");
                    this.currentView.requestLoadOrCreate(this);
                    break;
            }
        } catch (SQLException e) {
            this.currentView.showError("Database error: " + e.getMessage());
            this.currentView.requestLoadOrCreate(this);
        }
    }

    public void onSaveToDelete(String saveName) {
        try {
            heroRepository.deleteSave(saveName);
            this.currentView.showMessage("Save deleted successfully!");
            this.currentView.requestLoadOrCreate(this);
        } catch (SQLException e) {
            this.currentView.showError("Error deleting save: " + e.getMessage());
            this.currentView.requestLoadOrCreate(this);
        }
    }

    public void onHeroLoad(String heroName) {
        Hero loadedHero = heroLoader.loadHero(heroName);
        if (loadedHero != null) {
            this.currentHero = loadedHero;
            startNewGame();
        } else {
            this.currentView.requestLoadOrCreate(this);
        }
    }

    public void setCurrentHero(Hero hero) {
        this.currentHero = hero;
    }

    public void onHeroMovement(String direction) {
        this.savedEscapeChance = 50;
        if (currentHero.isPowerEscape()) {
            this.savedEscapeChance += 15;
        }

        int nextY = currentHero.getYPos();
        int nextX = currentHero.getXPos();

        switch (direction) {
            case "N" -> nextY -= 1;
            case "S" -> nextY += 1;
            case "E" -> nextX += 1;
            case "W" -> nextX -= 1;
            default -> {
                this.currentView.showError("Invalid direction");
                this.currentView.requestMovement();
                return;
            }
        }

        if (nextX < 0 || nextX >= gameMap.getSize() || nextY < 0 || nextY >= gameMap.getSize()) {
            this.currentView.showVictory();
            try {
                this.heroRepository.updateHero(this.currentHero);
            }
            catch (SQLException e) {
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
            }
            this.currentView.requestLoadOrCreate(this);
            return;
        }

        if (gameMap.getTile(nextY, nextX).isEnemy()) {
            this.savedDirection = direction;
            this.savedVillain = gameMap.getTile(nextY, nextX).getEnemy();
            this.currentView.showHeroStats(currentHero);
            this.currentView.showVillain(this.savedVillain);
            this.currentView.requestBattleDecision(this.savedVillain, this.savedEscapeChance);
        } else {
            int oldX = currentHero.getXPos();
            int oldY = currentHero.getYPos();

            currentHero.setXPos(nextX);
            currentHero.setYPos(nextY);

            gameMap.setHeroOnTile(oldY, oldX, false);
            gameMap.setVisitedOnTile(oldY, oldX, true);
            gameMap.setHeroOnTile(nextY, nextX, true);
            gameMap.setVisitedOnTile(nextY, nextX, true);
            
            this.currentView.showMap(gameMap);
            this.currentView.requestMovement();
        }
    }

    public void onBattleDecision(String decision) {
        switch (decision) {
            case "Fight":
                BattleService battle = new BattleService(this.currentHero, this.savedVillain);
                if (battle.isBattleWon()) {
                    int oldX = currentHero.getXPos();
                    int oldY = currentHero.getYPos();
                    int nextY = oldY;
                    int nextX = oldX;

                    switch (this.savedDirection) {
                        case "N" -> nextY -= 1;
                        case "S" -> nextY += 1;
                        case "E" -> nextX += 1;
                        case "W" -> nextX -= 1;
                    }

                    gameMap.setHeroOnTile(oldY, oldX, false);
                    gameMap.setVisitedOnTile(oldY, oldX, true);
                    gameMap.getTile(nextY, nextX).setIsEnemy(false);
                    gameMap.getTile(nextY, nextX).setEnemy(null);

                    currentHero.setXPos(nextX);
                    currentHero.setYPos(nextY);
                    gameMap.setHeroOnTile(nextY, nextX, true);
                    gameMap.setVisitedOnTile(nextY, nextX, true);
                    
                    this.currentView.showBattleResult(true, this.savedVillain);
                    if (battle.didLevelUp()) {
                        this.currentView.showLevelUp(this.currentHero);
                    }
                    this.currentView.showExperienceProgress(this.currentHero);

                    this.currentView.showArtifactDrop(this.savedVillain);
                    this.currentView.requestArtifactDecision(this.currentHero, this.savedVillain);

                } else {
                    this.currentView.showBattleResult(false, this.savedVillain);
                    this.currentView.showGameOver();
                    try {
                        this.heroRepository.deleteSave(this.currentHero.getName());
                    }
                    catch (SQLException e) {
                        //noinspection CallToPrintStackTrace
                        e.printStackTrace();
                    }
                    this.currentView.requestLoadOrCreate(this);
                }
                break;
            case "Escape":
                int random = new Random().nextInt(100);
                if (random < this.savedEscapeChance) {
                    this.currentView.showMessage("You escaped successfully!");
                    this.savedVillain = null;
                    this.savedDirection = null;
                    this.currentView.requestMovement();
                } else {
                    this.currentView.showMessage("You failed to escape! Time to fight!");
                    this.onBattleDecision("Fight");
                }
                break;
            default:
                this.currentView.showError("Invalid input, please choose between Fight and Escape");
                this.currentView.requestBattleDecision(this.savedVillain, this.savedEscapeChance);
        }
    }

    protected void startNewGame() {
        this.currentView.showMessage("Game starting with hero: " + currentHero.getName());
        this.currentView.showHeroStats(currentHero);
        MapService mapService = new MapService(this.currentHero);
        gameMap = mapService.getGameMap();
        this.currentView.showMap(gameMap);
        this.currentView.requestMovement();
    }

    public void onArtifactDecision(String decision) {
        switch (decision) {
            case "Yes":
                if (this.currentHero.getArtifact() != null) {
                    Artifact oldArtifact = this.currentHero.getArtifact();
                    switch (oldArtifact.getType()) {
                        case "Helm" -> this.currentHero.setMaxHp(this.currentHero.getMaxHp() - (oldArtifact.getLevel()));
                        case "Weapon" -> this.currentHero.setAttack(this.currentHero.getAttack() - (oldArtifact.getLevel()));
                        case "Armor" -> this.currentHero.setDefense(this.currentHero.getDefense() - (oldArtifact.getLevel()));
                    }
                }

                Artifact newArtifact = this.savedVillain.getArtifact();
                this.currentHero.setArtifact(newArtifact);
                this.currentView.showMessage("New artifact equipped!");
                break;

            case "No":
                this.currentView.showMessage("Artifact discarded!");
                break;

            default:
                this.currentView.showError("Invalid choice!");
                this.currentView.requestArtifactDecision(this.currentHero, this.savedVillain);
                return;
        }

        this.currentView.showMap(gameMap);
        this.currentView.requestMovement();
    }

}