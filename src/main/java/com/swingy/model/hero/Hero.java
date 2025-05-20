package com.swingy.model.hero;

abstract class Hero {

    int HP;
    int DEF;
    int ATK;
    int Level;
    int EXP;
    int XPos;
    int YPos;

    //TODO : add an artifact slot

    int getHp() {
        return HP;
    }
    void setHp(int hp) {
        HP = hp;
    }

    int getDefense() {
        return DEF;
    }
    void setDefense(int defense){
        DEF = defense;
    }

    int getAttack() {
        return ATK;
    }
    void  setAttack(int attack) {
        ATK = attack;
    }

    int getLevel() {
        return Level;
    }
    void setLevel(int level) {
        Level = level;
    }

    int getExperience() {
        return EXP;
    }
    void setExperience(int experience) {
        EXP = experience;
    }

    int getXPos() {
        return XPos;
    }
    void setXPos(int xPos) {
        XPos = xPos;
    }
    int getYPos() {
        return YPos;
    }
    void setYPos(int yPos) {
        YPos = yPos;
    }
}
