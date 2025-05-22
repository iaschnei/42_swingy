package com.swingy.view.console;

import com.swingy.controller.GameController;
import com.swingy.model.hero.Hero;
import com.swingy.model.map.Tile;
import com.swingy.model.villain.Villain;
import com.swingy.view.View;

import java.util.List;
import java.util.Scanner;

public class ConsoleView implements View {

    public ConsoleView() {}

    GameController controller;

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
    public void showMap(Tile[][] map, int heroX, int heroY) {

    }

    @Override
    public void showBattleResult(boolean success, Villain villain) {

    }

    @Override
    public void showVillain(Villain villain) {

    }

    @Override
    public void showLevelUp(Hero hero) {

    }

    @Override
    public void showVictory() {
        System.out.println("Congratulations! You won!");
        System.out.println("You should play again with a different class!");
    }

    @Override
    public void showGameOver() {
        System.out.println("Ouch! You lost");
        System.out.println("Maybe try again with a different class or strategy!");
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
        String heroName = scanner.nextLine();
    }

    @Override
    public void requestBattleDecision(Villain villain) {

    }
}