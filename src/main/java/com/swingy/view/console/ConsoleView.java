package com.swingy.view.console;

import com.swingy.controller.GameController;
import com.swingy.controller.HeroCreationInput;
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
        System.out.print("-> ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        this.controller.onLoadOrCreateChoice(answer);
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
    public void requestHeroSelection(List<Hero> heroes) {

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