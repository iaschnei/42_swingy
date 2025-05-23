package com.swingy.model.map;

public class GameMap {

    private final Tile[][] tiles;
    private final int size;

    public GameMap(int size) {
        this.size = size;
        this.tiles = new Tile[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tiles[i][j] = new Tile();
            }
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }
    public int getSize() {
        return size;
    }

    public void setHeroOnTile(int h, int w, boolean hero) {
        tiles[h][w].setIsHero(hero);
    }

    public void setEnemyOnTile(int h, int w, boolean enemy) {
        tiles[h][w].setIsEnemy(enemy);
    }

    public void setVisitedOnTile(int h, int w, boolean visited) {
        tiles[h][w].setIsVisited(visited);
    }

    public Tile getTile(int h, int w) {
        return tiles[h][w];
    }
}
