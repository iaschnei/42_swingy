package com.swingy.controller;

import com.swingy.view.console.ConsoleView;
import com.swingy.view.gui.GUIView;

public class ViewSwitcher {

    public ViewSwitcher() {}

    public void switchView(GameController gameController) {

        if (gameController.currentView instanceof GUIView) {
            ((GUIView) gameController.currentView).exitWindow();
            gameController.setCurrentView(new ConsoleView());
            gameController.currentView.showMessage("Switching to console view...");
        }
        else {
            gameController.setCurrentView(new GUIView());
            gameController.currentView.showMessage("Switching to GUI view...");
        }

        gameController.currentView.setController(gameController);
        gameController.currentView.showHeroStats(gameController.currentHero);
        gameController.currentView.showMap(gameController.gameMap);
        gameController.currentView.requestMovement();
    }

}
