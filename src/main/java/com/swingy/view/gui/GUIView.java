package com.swingy.view.gui;

import com.swingy.controller.GameController;
import com.swingy.model.hero.Hero;
import com.swingy.model.map.GameMap;
import com.swingy.model.map.Tile;
import com.swingy.model.villain.Villain;
import com.swingy.view.View;

import java.util.List;

public class GUIView implements View {

    GameController controller;

    @Override
    public void showHeroStats(Hero hero) {

    }

    @Override
    public void showMap(GameMap gameMap) {

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
    public void showExperienceProgress(Hero hero) {

    }

    @Override
    public void showVictory() {

    }

    @Override
    public void showGameOver() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void requestLoadOrCreate(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void requestSaveToDelete(List<String> saves) {

    }

    @Override
    public void requestHeroName() {

    }

    @Override
    public void requestHeroClass() {

    }

    @Override
    public void requestGameStartConfirm() {

    }

    @Override
    public void requestHeroSelection(List<String> heroes) {

    }

    @Override
    public void requestMovement() {

    }

    @Override
    public void requestBattleDecision(Villain villain, int escapeChance) {

    }

    @Override
    public void showArtifactDrop(Villain villain) {

    }

    @Override
    public void requestArtifactDecision(Hero hero, Villain villain) {

    }
}
