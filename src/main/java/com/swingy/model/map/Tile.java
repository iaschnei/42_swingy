package com.swingy.model.map;

public class Tile {

    public boolean isEnemy;
    public boolean isHero;
    public boolean isVisited;

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
