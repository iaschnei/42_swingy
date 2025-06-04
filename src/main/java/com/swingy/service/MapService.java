package com.swingy.service;

import com.swingy.model.hero.Hero;
import com.swingy.model.map.GameMap;
import com.swingy.model.villain.Villain;

import java.util.Random;

public class MapService {

    private final int mapSize;
    private final boolean[][] visitedPositions;
    private final Hero hero;
    private final GameMap gameMap;

    public MapService(Hero hero) {
        this.hero = hero;
        this.mapSize = calculateMapSize(hero.getLevel());
        this.visitedPositions = new boolean[mapSize][mapSize];
        this.gameMap = new GameMap(mapSize);
        initializeHeroPosition();
        populateGameMap();
    }

    private int calculateMapSize(int heroLevel) {
        return (heroLevel - 1) * 6 + 10 - (heroLevel % 2);
    }

    private void initializeHeroPosition() {
        int center = mapSize / 2;
        hero.setXPos(center);
        hero.setYPos(center);
        visitedPositions[center][center] = true;
        this.gameMap.setHeroOnTile(center, center, true);
        this.gameMap.setVisitedOnTile(center, center, true);
    }

    public GameMap getGameMap() {
        return this.gameMap;
    }

    private void populateGameMap() {
        Random random = new Random();
        int enemyCount = hero.getLevel() * 10 + ((hero.getLevel() - 1) * 5);
        int center = mapSize / 2;

        while (enemyCount > 0) {
            int h = random.nextInt(mapSize);
            int w = random.nextInt(mapSize);

            if (!(h == center && w == center) && !gameMap.getTile(h, w).isEnemy()) {
                gameMap.setEnemyOnTile(h, w, true);
                gameMap.getTile(h, w).setEnemy(new Villain(this.hero, h, w));
                enemyCount--;
            }
        }
    }

}
