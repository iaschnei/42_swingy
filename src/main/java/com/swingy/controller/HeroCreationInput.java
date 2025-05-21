package com.swingy.controller;

import com.swingy.model.hero.Hero;
import jakarta.validation.ConstraintViolation;

import java.util.Objects;
import java.util.Set;

public class HeroCreationInput {

    Hero tmp_hero;
    GameController gameController;

    public HeroCreationInput(GameController gameController) {
        this.tmp_hero = new Hero();
        this.gameController = gameController;
    }

    public void onNameInput(String nameInput) {

        this.tmp_hero.setName(nameInput);

        Set<ConstraintViolation<Hero>> violations = ValidationUtil.validateProperty(tmp_hero, "name");

        if (!violations.isEmpty()) {
            for (ConstraintViolation<Hero> violation : violations) {
                this.gameController.currentView.showError(violation.getMessage());
            }
            this.gameController.currentView.requestHeroName();
            return;
        }
        this.gameController.currentView.requestHeroClass();
    }

    public void onClassInput(String classInput) {

        this.tmp_hero.setClassName(classInput);

        Set<ConstraintViolation<Hero>> violations = ValidationUtil.validateProperty(tmp_hero, "className");

        if (!violations.isEmpty()) {
            for (ConstraintViolation<Hero> violation : violations) {
                this.gameController.currentView.showError(violation.getMessage());
            }
            this.gameController.currentView.requestHeroClass();
            return;
        }
        this.gameController.currentView.requestGameStartConfirm();
    }

    public void onGameStartConfirm(String input) {

        if (Objects.equals(input, "No")) {
            this.gameController.currentView.showMessage("Resetting character creation..");
        }
        else if (Objects.equals(input, "Yes")) {
            this.gameController.currentView.showMessage("Saving Hero's data and launching game..");
        }
        else {
            this.gameController.currentView.showError("Invalid input");
            this.gameController.currentView.requestGameStartConfirm();
        }
    }

}
