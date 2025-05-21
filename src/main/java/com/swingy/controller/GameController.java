package com.swingy.controller;

import com.swingy.view.View;

public class GameController {

    View currentView;
    public HeroCreationInput heroCreationInput;

    public GameController(View viewType) {
        this.currentView = viewType;
        this.currentView.requestLoadOrCreate(this);
    }

    public void onLoadOrCreateChoice(String choice) {
        if (choice.equals("Load_save")) {
            System.out.println("Loading saves ...");
        }
        else if (choice.equals("Create_save")) {
            this.heroCreationInput = new HeroCreationInput(this);
            this.currentView.requestHeroName();
        }
        else {
            this.currentView.showError("Invalid choice");
            this.currentView.requestLoadOrCreate(this);
        }
    }
}
