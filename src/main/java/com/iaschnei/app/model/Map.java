package com.iaschnei.app.model;

import com.iaschnei.app.model.Hero;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Map
 */
public class Map {

	private int        size;
	private String     map;
  private Object[][] elements;


  // Generating size is following this formula : (level - 1) * 5 + 10 - (level % 2)
  public String generate_map(Hero hero) {

    int hero_lvl = hero.get_lvl();

    String generated_map = new String();

    size = (hero_lvl - 1) * 5 + 10 - (hero_lvl % 2);

    for (int i = 0; i < size; i ++) {
      for (int j = 0; j < size; j++) {

        int random_int = ThreadLocalRandom.current().nextInt(0, 99 + 1);
        int random_coeff = ThreadLocalRandom.current().nextInt(3, 8 + 1);

        if (random_int % random_coeff ==  0) {
          generated_map += "V";
        }
        else {
          generated_map += "0";
        }
      }
    }

    elements = new Object[size][size];

    load_map(generated_map, size);
    return (generated_map);
  }

  public void load_map(String map, int size) {

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {

        elements[i][j] = map.charAt(i + j);
      }
    }
  }

// -------- GETTERS ----------

  public int get_size() {
    return (size);
  }

  public Object get_element_at(int x, int y) {
    return (elements[x][y]);
  }

  public String get_map_string() {

    map = new String();

    for (int i = 0; i < size; i ++) {
      for (int j = 0; j < size; j++) {

        map += elements[i][j].toString();
      }
    }
    return (map);
  }

  public void set_element_at(int x, int y, char elem) {
    elements[x][y] = elem;
  }
}
