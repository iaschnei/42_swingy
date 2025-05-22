package com.swingy.controller;

import com.swingy.model.hero.Hero;
import com.swingy.storage.HeroDbRepository;
import com.swingy.view.View;

import java.sql.SQLException;
import java.util.List;

public class GameController {

    View currentView;
    Hero currentHero;
    private final HeroDbRepository heroRepository;
    private final HeroLoader heroLoader;
    public HeroCreationInput heroCreationInput;

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
            startGame();
        } else {
            this.currentView.requestLoadOrCreate(this);
        }
    }

    public void setCurrentHero(Hero hero) {
        this.currentHero = hero;
    }

    protected void startGame() {
        this.currentView.showMessage("Game starting with hero: " + currentHero.getName());
        this.currentView.showHeroStats(currentHero);
    }

}

