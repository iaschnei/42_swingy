package com.iaschnei.app.model;

/**
 * Hero:
 * Playable character
 */
public class Hero extends Character {

  private String  name;
  private String  class_type;
  private int     exp;

  private Artifact helm;
  private Artifact armor;
  private Artifact weapon;

  public Hero(String name, String class_type, int level, int exp, int atk, int def, int hp, int x, int y,
              Artifact helm, Artifact armor, Artifact weapon) {
    this.name = name;
    this.class_type = class_type;
    this.level = level;
    this.exp = exp;
    this.atk = atk;
    this.def = def;
    this.hp = hp;
    this.position = new Pos(x,y);
    this.helm = helm;
    this.armor = armor;
    this. weapon = weapon;
  }

// ---------- GETTERS ---------

  public String get_name() {
    return (name);
  }

  public String get_class() {
    return (class_type);
  }

  public int get_exp() {
    return (exp);
  }

  public Artifact get_helm() {
    return (helm);
  }

  public Artifact get_armor() {
    return (armor);
  }

  public Artifact get_weapon() {
    return (weapon);
  }

// ---------- SETTERS ----------

  public void set_exp(int new_exp) {
    exp = new_exp;
  }

  public void set_helm(Artifact new_helm) {
    helm = new_helm;
  }

  public void set_armor(Artifact new_armor) {
    armor = new_armor;
  }

  public void set_weapon(Artifact new_weapon) {
    weapon = new_weapon;
  }
}
