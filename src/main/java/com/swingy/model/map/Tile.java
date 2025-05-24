package com.swingy.model.map;

import com.swingy.model.villain.Villain;

public class Tile {

    private boolean isEnemy;
    private boolean isHero;
    private boolean isVisited;
    private Villain enemy;

    public Tile() {
    }

    public boolean isHero() {
        return this.isHero;
    }

    public boolean isEnemy() {
        return this.isEnemy;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public Villain getEnemy() {
        return enemy;
    }

    public void setEnemy(Villain enemy) {
        this.enemy = enemy;
    }

    public void setIsEnemy(boolean isEnemy) {
        this.isEnemy = isEnemy;
    }

    public void setIsHero(boolean isHero) {
        this.isHero = isHero;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

}
