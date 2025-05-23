package com.swingy.model.hero;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Hero {
    public static final String CLASS_BERSERK = "Berserk";
    public static final String CLASS_MAGE = "Mage";
    public static final String CLASS_ARCHER = "Archer";

    // Stats constants
    private static final int BERSERK_HP = 15;
    private static final int BERSERK_ATK = 5;
    private static final int BERSERK_DEF = 10;

    private static final int MAGE_HP = 10;
    private static final int MAGE_ATK = 15;
    private static final int MAGE_DEF = 5;

    private static final int ARCHER_HP = 12;
    private static final int ARCHER_ATK = 10;
    private static final int ARCHER_DEF = 7;

    @NotNull
    @Size(min = 2, max = 10, message = "Name must be 2 char long min and 10 char long max")
    private String name;

    @NotNull
    @Pattern(regexp = "Berserk|Mage|Archer", message = "Class must be Berserk, Mage, or Archer")
    private String className;

    // Base stats
    private int hp;
    private int defense;
    private int attack;
    private int level;
    private int experience;

    // Position
    private int xPos;
    private int yPos;

    // Special powers
    private boolean powerSecondChance;
    private boolean powerExecute;
    private boolean powerEscape;

    //TODO Add an artifact slot

    public void initHero(String className) {
        this.setClassName(className);
        this.setLevel(1);
        this.setExperience(0);
        this.setXPos(0);
        this.setYPos(0);

        switch (className) {
            case CLASS_BERSERK -> {
                this.setHp(BERSERK_HP);
                this.setAttack(BERSERK_ATK);
                this.setDefense(BERSERK_DEF);
                this.setPowerSecondChance(true);
            }
            case CLASS_MAGE -> {
                this.setHp(MAGE_HP);
                this.setAttack(MAGE_ATK);
                this.setDefense(MAGE_DEF);
                this.setPowerExecute(true);
            }
            case CLASS_ARCHER -> {
                this.setHp(ARCHER_HP);
                this.setAttack(ARCHER_ATK);
                this.setDefense(ARCHER_DEF);
                this.setPowerEscape(true);
            }
        }
    }

    // Getters and setters
    public boolean isPowerEscape() { return powerEscape; }
    public void setPowerEscape(boolean powerEscape) { this.powerEscape = powerEscape; }

    public boolean isPowerSecondChance() { return powerSecondChance; }
    public void setPowerSecondChance(boolean powerSecondChance) { this.powerSecondChance = powerSecondChance; }

    public boolean isPowerExecute() { return powerExecute; }
    public void setPowerExecute(boolean powerExecute) { this.powerExecute = powerExecute; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }

    public int getDefense() { return defense; }
    public void setDefense(int defense) { this.defense = defense; }

    public int getAttack() { return attack; }
    public void setAttack(int attack) { this.attack = attack; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }

    public int getXPos() { return xPos; }
    public void setXPos(int xPos) { this.xPos = xPos; }

    public int getYPos() { return yPos; }
    public void setYPos(int yPos) { this.yPos = yPos; }
}