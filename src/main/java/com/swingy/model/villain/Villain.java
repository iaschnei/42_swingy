package com.swingy.model.villain;

import com.swingy.model.hero.Hero;

import java.util.Random;

public class Villain {

    private int level;
    private int hp;
    private int attack;
    private int defense;
    private int posX;
    private int posY;

    public Villain(Hero hero, int posY, int posX) {
        int random = new Random().nextInt(100);
        if (random < 50) {
            this.level = hero.getLevel();
        } else if (random < 75) {
            this.level = Math.max(1, hero.getLevel() - 1);
        } else {
            this.level = hero.getLevel() + 1;
        }
        this.attack = this.level * 2;
        this.defense = this.level * 2;
        this.hp = this.level * 5;
        this.posX = posX;
        this.posY = posY;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
