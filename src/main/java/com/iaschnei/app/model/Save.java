package com.iaschnei.app.model;

import java.util.Map;

/**
 * Save
 */
public class Save {

  private Map<String, String> save;
  private int                 save_number;

  // Reads the save file and returns how many saves there are
  public int fetch_saves() {
    return (0);
  }

  public void load_save() {
    if (save_number == 0) {
      //TODO : throw exception
      return ;
    }

    //TODO : read file and put content into 'save'
  }

  public void new_save(int number) {
    save_number = number;

    //TODO: write into file a new save file, throw if not possible
  }

  public void delete_save(int number) {
    //TODO : read file and delete specified save if possible, else, throw
  }

  public void save_to_file() {
    if (save_number == 0) {
      //TODO : throw
      return;
    }

    //TODO: write whatever changes have occured to file
  }

// ---------- GETTER ----------

  public String get_save_element(String key) {
    return (save.get(key));
  }

// --------- SETTER -----------

  public void add_save_element(String key, String content) {
    save.put(key, content);
  }

  public void remove_save_element(String key) {
    save.remove(key);
  }

  public void set_save_number(int new_save_number) {
    save_number = new_save_number;
  }
}
