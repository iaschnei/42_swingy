package com.iaschnei.app.model;

import jakarta.validation.constraints.Size;
import java.util.Random;

/**
 * Hero:
 * Playable character
 */
public class Hero extends Character {

  @Size(min = 2, max = 12, message="Name must be between 2 and 12 characters")
  private String  name;
  private String  class_type;
  private int     exp;
  private int     max_hp;

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
    this.max_hp = hp;
    this.position = new Pos(x,y);
    this.helm = helm;
    this.armor = armor;
    this. weapon = weapon;
  }

  public void level_up() {

    level += 1;
    atk += 2;
    def += 2;
    max_hp += 2;
    hp = max_hp;
  }

  public boolean move(String direction) {

  //TODO : update x and y, check if the map is big enough and move is actually possible

    switch (direction) {

      case "east":
        return (true);

      case "west":
        return (true);

      case "south":
        return (true);

      case "north":
        return (true);

      default:
        return (false);
    }
  }

  // Gives a chance to avoid a fight
  public boolean run() {

    Random rand = new Random();

    int random_int = rand.nextInt(1);

    if (random_int == 0) {
      return (false);
    }

    return (true);
  }

  public boolean fight(Villain villain) {
    //TODO : simulate fight between this hero and the villain
    return (false);
  }

  public void pick_up_artifact() {
    //TODO : allow the player to swap artifacts
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

  public int get_max_hp() {
    return (max_hp);
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

  public void set_max_hp(int new_max_hp) {
    max_hp = new_max_hp;
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
