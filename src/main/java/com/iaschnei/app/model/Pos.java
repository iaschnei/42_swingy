package com.iaschnei.app.model;

/**
 * Pos
 * Represents an entity's position
 */
public class Pos {

  private int x;
  private int y;

  public Pos(int x, int y) {
    this.x = x;
    this.y = y;
  }

// ---------- GETTERS ----------

  public int get_x() {
    return (x);
  }

  public int get_y() {
    return (y);
  }

// ---------- SETTERS ----------

  public void set_x(int new_x) {
    x = new_x;
  }

  public void set_y(int new_y) {
    y = new_y;
  }
}
