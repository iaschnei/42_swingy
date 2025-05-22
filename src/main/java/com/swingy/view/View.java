package com.swingy.view;

import com.swingy.controller.GameController;
import com.swingy.model.hero.Hero;
import com.swingy.model.map.Tile;
import com.swingy.model.villain.Villain;

import java.util.List;

public interface View {

    // Display various information
    void showHeroStats(Hero hero);
    void showMap(Tile[][] map, int heroX, int heroY);
    void showBattleResult(boolean success, Villain villain);
    void showVillain(Villain villain);
    void showLevelUp(Hero hero);
    void showVictory();
    void showGameOver();

    // Display misc messages
    void showMessage(String message);
    void showError(String message);

    // Request input
    void requestLoadOrCreate(GameController gameController);
    void requestSaveToDelete(List<String> saves);
    void requestHeroName();
    void requestHeroClass();
    void requestGameStartConfirm();
    void requestHeroSelection(List<String> heroes);
    void requestMovement();
    void requestBattleDecision(Villain villain);
}
