package com.iaschnei.app.model;

/**
 * Character:
 * Parent class for hero and villains
 */
public class Character {

  protected int level;
  protected int atk;
  protected int def;
  protected int hp;
  protected Pos position;


// ---------- GETTERS ----------

  public int get_lvl() {
    return (level);
  }

  public int get_atk() {
    return (atk);
  }

  public int get_def() {
    return (def);
  }

  public int get_hp() {
    return (hp);
  }

  public Pos get_position() {
    return (position);
  }

// ---------- SETTERS ----------

  public void set_level(int new_level) {
    level = new_level;
  }

  public void set_atk(int new_atk) {
    atk = new_atk;
  }

  public void set_def (int new_def) {
    def = new_def;
  }

  public void set_hp (int new_hp) {
    hp = new_hp;
  }

  public void set_position(Pos new_position) {
    position = new_position;
  }
}
