package com.iaschnei.app.model;

/**
 * Map
 */
public class Map {

	private int size;
  private Object[][] elements;

  public void generate_map() {
  //TODO : generate map according to given formula
  }

// -------- GETTERS ----------

  public int get_size() {
    return (size);
  }

  public Object get_element_at(int x, int y) {
    return (elements[x][y]);
  }
}
