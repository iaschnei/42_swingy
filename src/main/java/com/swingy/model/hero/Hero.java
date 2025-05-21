package com.swingy.model.hero;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Hero {


    @NotNull
    @Size(min = 2, max = 10, message = "Name must be 2 char long min and 10 char long max")
    protected String name;

    @NotNull
    @Pattern(regexp = "Berserk|Mage|Archer", message = "Class must be Berserk, Mage, or Archer")
    protected String className;

    protected int HP;
    protected int DEF;
    protected int ATK;
    protected int Level;
    protected int EXP;
    protected int XPos;
    protected int YPos;

    //TODO : add an artifact slot

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }

    public int getHp() {
        return HP;
    }
    public void setHp(int hp) {
        this.HP = hp;
    }

    public int getDefense() {
        return DEF;
    }
    public void setDefense(int defense){
        this.DEF = defense;
    }

    public int getAttack() {
        return ATK;
    }
    public void  setAttack(int attack) {
        this.ATK = attack;
    }

    public int getLevel() {
        return Level;
    }
    public void setLevel(int level) {
        this.Level = level;
    }

    public int getExperience() {
        return EXP;
    }
    public void setExperience(int experience) {
        this.EXP = experience;
    }

    public int getXPos() {
        return XPos;
    }
    public void setXPos(int xPos) {
        this.XPos = xPos;
    }
    public int getYPos() {
        return YPos;
    }
    public void setYPos(int yPos) {
        this.YPos = yPos;
    }
}
