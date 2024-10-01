package com.iaschnei.app.model;

/**
 * Villain
 * Represents ennemies
 */
public class Villain extends Character {

	public Villain(int level, int x, int y) {
    this.level = level;
    this.atk = level * 2;
    this.def = level * 2 - 2;
    this.hp = level * 3 - 1;
    this.position = new Pos(x, y);
	}
}
