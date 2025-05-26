package com.swingy.model.villain;

import com.swingy.model.artifact.Artifact;
import com.swingy.model.hero.Hero;

import java.util.Random;

public class Villain {

    private int level;
    private int hp;
    private int attack;
    private int defense;
    private int posX;
    private int posY;
    private Artifact artifact;

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

        random = new Random().nextInt(100);
        if (random <= 33) {
            this.artifact = new Artifact("Helm");
        } else if (random <= 66) {
            this.artifact = new Artifact("Weapon");
        } else {
            this.artifact = new Artifact("Armor");
        }

        this.artifact.setLevel(this.level);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        this.attack = this.level * 2;
        this.defense = this.level * 2;
        this.hp = this.level * 5;
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

    public int getArtifactLevel() {
        return this.artifact.getLevel();
    }

    public String getArtifactType() {
        return this.artifact.getType();
    }

    public void setArtifactLevel(int level) {
        this.artifact.setLevel(level);
    }

    public Artifact getArtifact() {
        return this.artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }
}
