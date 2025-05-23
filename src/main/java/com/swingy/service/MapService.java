package com.swingy.service;

import com.swingy.model.hero.Hero;
import com.swingy.model.map.GameMap;

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

    public boolean[][] getVisitedPositions() {
        return visitedPositions;
    }

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < mapSize && y >= 0 && y < mapSize;
    }

    public GameMap getGameMap() {
        return this.gameMap;
    }

    private void populateGameMap() {
        Random random = new Random();
        int enemyCount = hero.getLevel() * 5 + 7;
        int center = mapSize / 2;

        while (enemyCount > 0) {
            int h = random.nextInt(mapSize);
            int w = random.nextInt(mapSize);

            if (!(h == center && w == center) && !gameMap.getTile(h, w).isEnemy()) {
                gameMap.setEnemyOnTile(h, w, true);
                enemyCount--;
            }
        }
    }

}
