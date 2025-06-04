package com.swingy.view.console;

import com.swingy.controller.GameController;
import com.swingy.model.artifact.Artifact;
import com.swingy.model.hero.Hero;
import com.swingy.model.map.GameMap;
import com.swingy.model.villain.Villain;
import com.swingy.view.View;

import java.util.List;
import java.util.Scanner;

public class ConsoleView implements View {

    private final boolean visible_enemies;

    public ConsoleView(boolean visible_enemies) {
        this.visible_enemies = visible_enemies;
    }

    GameController controller;


    @Override
    public void setController(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void showHeroStats(Hero hero) {
        System.out.println("\n=== Hero Stats ===");
        System.out.println("Name: " + hero.getName());
        System.out.println("Class: " + hero.getClassName());
        System.out.println("Level: " + hero.getLevel());
        System.out.println("Experience: " + hero.getExperience());
        System.out.println("HP: " + hero.getHp());
        System.out.println("Attack: " + hero.getAttack());
        System.out.println("Defense: " + hero.getDefense());
        System.out.println("Position: (" + hero.getXPos() + ", " + hero.getYPos() + ")");
        System.out.println("Special Power: " + getHeroPower(hero));
        
        // Add artifact information
        if (hero.getArtifact() != null) {
            System.out.println("Artifact: Level " + hero.getArtifact().getLevel() + " " + 
                             hero.getArtifact().getType() + " (+" + 
                             getArtifactBonus(hero.getArtifact()) + ")");
        } else {
            System.out.println("Artifact: None");
        }
        System.out.println("================\n");
    }

    private String getHeroPower(Hero hero) {
        if (hero.isPowerSecondChance()) {
            return "Second Chance (Survives at 1 HP)";
        } else if (hero.isPowerExecute()) {
            return "Execute (Instant kill under 3 HP)";
        } else if (hero.isPowerEscape()) {
            return "Escape Master (+15% escape chance)";
        }
        return "None";
    }

    @Override
    public void showMap(GameMap gameMap) {

        int mapSize = gameMap.getSize();

        for (int h = 0; h < mapSize; h++) {
            for (int w = 0; w < mapSize; w++) {
                if (gameMap.getTile(h, w).isHero()) {
                    System.out.print("H ");
                }
                else if (gameMap.getTile(h, w).isVisited()) {
                    System.out.print("~ ");
                }
                else if (gameMap.getTile(h, w).isEnemy() && this.visible_enemies) {
                    System.out.print("E ");
                }
                else {
                    System.out.print("· ");
                }
            }
            System.out.println();
        }
    }

    @Override
    public void showBattleResult(boolean success, Villain villain) {
        if (success) {
            System.out.println("\n=== Battle Result ===");
            System.out.println("You've won this battle!");
            System.out.println("The enemy has been defeated and your experience has been increased!");
            System.out.println("==================\n");
        }
        else {
            System.out.println("\n=== Battle Result ===");
            System.out.println("You've lost this battle!");
            System.out.println("Your hero is dead and you have to start over!");
            System.out.println("The villain still had " + villain.getHp() + "HP left...");
            System.out.println("==================\n");
        }
    }

    @Override
    public void showVillain(Villain villain) {
        System.out.println("\n=== Villain Stats ===");
        System.out.println("Level: " + villain.getLevel());
        System.out.println("HP: " + villain.getHp());
        System.out.println("Attack: " + villain.getAttack());
        System.out.println("Defense: " + villain.getDefense());
        if (villain.getArtifact() != null) {
            System.out.println("Carries: Level " + villain.getArtifact().getLevel() + " " + 
                             villain.getArtifact().getType());
        }
        System.out.println("==================\n");
    }

    @Override
    public void showLevelUp(Hero hero) {
        System.out.println("Your hero leveled up!");
        System.out.println("You are back to full health and have gained:");
        System.out.println(" +3 max hp");
        System.out.println(" +2 attack");
        System.out.println(" +2 defense");
    }

    @Override
    public void showExperienceProgress(Hero hero) {
        int level = hero.getLevel();
        int currentExp = hero.getExperience();
        int necessaryExp = level * 1000 + (((level - 1) * (level - 1)) * 450);

        double percentage = (double) currentExp / necessaryExp * 100;
        int barLength = 20;
        int filledLength = (int) (barLength * percentage / 100);

        StringBuilder progressBar = new StringBuilder("[");
        for (int i = 0; i < barLength; i++) {
            if (i < filledLength) {
                progressBar.append("=");
            } else {
                progressBar.append(" ");
            }
        }
        progressBar.append("]");

        System.out.println("Experience: " + currentExp + "/" + necessaryExp + " " + progressBar + " " + percentage + "%)");
    }

    @Override
    public void showVictory() {
        System.out.println("\n=== Victory ===");
        System.out.println("Congratulations! You won!");
        System.out.println("You should play again with a different class!");
        System.out.println("==================\n");
    }

    @Override
    public void showGameOver() {
        System.out.println("\n=== Game Over ===");
        System.out.println("Ouch! You lost");
        System.out.println("Maybe try again with a different class or strategy!");
        System.out.println("==================\n");
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showError(String message) {
        System.err.println(message);
    }

    @Override
    public void requestLoadOrCreate(GameController controller) {
        this.controller = controller;
        System.out.println("Pick an option:");
        System.out.println("-- Load_save");
        System.out.println("-- Create_save");
        System.out.println("-- Delete_save");
        System.out.print("-> ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        this.controller.onLoadOrCreateChoice(answer);
    }

    @Override
    public void requestSaveToDelete(List<String> saves) {
        System.out.println("Select save to delete:");
        for (String save : saves) {
            System.out.println("-- " + save);
        }
        System.out.print("-> ");
        Scanner scanner = new Scanner(System.in);
        String saveName = scanner.nextLine();
        this.controller.onSaveToDelete(saveName);
    }


    @Override
    public void requestHeroName() {
        System.out.println("Please enter your hero's name:");
        System.out.println("(Alphabetic chars only)");
        System.out.print("-> ");
        Scanner scanner = new Scanner(System.in);
        String heroName = scanner.nextLine();
        this.controller.heroCreationInput.onNameInput(heroName);
    }

    @Override
    public void requestHeroClass() {
        System.out.println("Please choose a class for your hero:");
        System.out.println("/----Berserk----\\");
        System.out.println("| HP:  15       |");
        System.out.println("| ATK: 5        |");
        System.out.println("| DEF: 10       |");
        System.out.println("| Has a second  |");
        System.out.println("| chance at 1 HP|");
        System.out.println("\\---------------/");
        System.out.println("/-----Mage------\\");
        System.out.println("| HP:  10       |");
        System.out.println("| ATK: 15       |");
        System.out.println("| DEF: 5        |");
        System.out.println("| Executes under|");
        System.out.println("| 3 HP          |");
        System.out.println("\\---------------/");
        System.out.println("/----Archer-----\\");
        System.out.println("| HP:  12       |");
        System.out.println("| ATK: 10       |");
        System.out.println("| DEF: 7        |");
        System.out.println("| Escape chance |");
        System.out.println("| +15%          |");
        System.out.println("\\---------------/");
        System.out.print("-> ");
        Scanner scanner = new Scanner(System.in);
        String heroClass = scanner.nextLine();
        this.controller.heroCreationInput.onClassInput(heroClass);
    }

    @Override
    public void requestGameStartConfirm() {
        System.out.println("Ready to start the game ?");
        System.out.println("  -- Yes ---- No -- ");
        System.out.print("-> ");
        Scanner scanner = new Scanner(System.in);
        String confirm = scanner.nextLine();
        this.controller.heroCreationInput.onGameStartConfirm(confirm);
    }

    @Override
    public void requestHeroSelection(List<String> heroes) {
        System.out.println("Select hero to load:");
        for (String hero : heroes) {
            System.out.println("-- " + hero);
        }
        System.out.print("-> ");
        Scanner scanner = new Scanner(System.in);
        String heroName = scanner.nextLine();
        this.controller.onHeroLoad(heroName);
    }

    @Override
    public void requestMovement() {
        System.out.println("Where do you want to go next?");
        System.out.println("- N - S - E - W -");
        System.out.print("-> ");
        Scanner scanner = new Scanner(System.in);
        String direction = scanner.nextLine();
        this.controller.onHeroMovement(direction);
    }

    @Override
    public void requestBattleDecision(Villain villain, int escapeChance) {
        System.out.println("Oh no ! You encountered an enemy !");
        System.out.println("What do you want to do?");
        System.out.println("-- Fight");
        System.out.println("-- Escape (" + escapeChance + "% chance)");
        System.out.print("-> ");
        Scanner scanner = new Scanner(System.in);
        String decision = scanner.nextLine();
        this.controller.onBattleDecision(decision);
    }

    private String getArtifactBonus(Artifact artifact) {
        int bonus = artifact.getLevel();
        switch (artifact.getType()) {
            case "Helm" -> { return bonus + " HP"; }
            case "Weapon" -> { return bonus + " Attack"; }
            case "Armor" -> { return bonus + " Defense"; }
            default -> { return "0"; }
        }
    }

    @Override
    public void showArtifactDrop(Villain villain) {
        System.out.println("\n=== Artifact Dropped ===");
        System.out.println("The villain dropped: Level " + villain.getArtifact().getLevel() + " " + 
                          villain.getArtifact().getType() + " (+" + 
                          getArtifactBonus(villain.getArtifact()) + ")");
        System.out.println("==================\n");
    }

    @Override
    public void requestArtifactDecision(Hero hero, Villain villain) {
        System.out.println("Do you want to equip the new artifact?");
        if (hero.getArtifact() != null) {
            System.out.println("Current: Level " + hero.getArtifact().getLevel() + " " + 
                             hero.getArtifact().getType() + " (+" + 
                             getArtifactBonus(hero.getArtifact()) + ")");
        } else {
            System.out.println("Current: None");
        }
        System.out.println("New: Level " + villain.getArtifact().getLevel() + " " + 
                          villain.getArtifact().getType() + " (+" + 
                          getArtifactBonus(villain.getArtifact()) + ")");
        System.out.println("-- Yes");
        System.out.println("-- No");
        System.out.print("-> ");
        Scanner scanner = new Scanner(System.in);
        String decision = scanner.nextLine();
        this.controller.onArtifactDecision(decision);
    }

    @Override
    public boolean getVisibleEnemies() {
        return this.visible_enemies;
    }
}