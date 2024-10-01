package com.iaschnei.app.model;

/**
 * Artifact
 * Objects that the hero can equip
 */
public class Artifact {

  private String  type;
  private String  name;
  private int     stat_bonus;

  public Artifact(String type, String name, int stat_bonus) {
    this.type = type;
    this.name = name;
    this.stat_bonus = stat_bonus;
  }

// ----------- GETTERS -----------

  public String get_type() {
    return (type);
  }

  public String get_name() {
    return (name);
  }

  public int get_stat_bonus() {
    return (stat_bonus);
  }

}
