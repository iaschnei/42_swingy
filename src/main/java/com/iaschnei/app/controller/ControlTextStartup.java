package com.iaschnei.app.controller;

import com.iaschnei.app.textView.TextStartup;
import com.iaschnei.app.model.WriteRead;
import com.iaschnei.app.model.Hero;

/**
 * ControlTextStartup
 */
public class ControlTextStartup {

  static void awaitNewContinue() throws Exception {

    String answer = ReadTextInput.get_next_input();

    switch (answer) {

      case "new_save":
        TextStartup.display_new_save_screen();
        awaitSaveSelection();
        break;

      case "continue":
        // TODO
        break;

      default:
        awaitNewContinue();
    }
  }

    static void awaitSaveSelection() throws Exception {
      String answer = ReadTextInput.get_next_input();

      WriteRead save_reader = new WriteRead();

      switch (answer) {

        case "1":
          save_reader.open_saves_file("save_1.txt");
          save_reader.erase_saves_file();
          TextStartup.display_name_pick_screen();
          awaitNameSelection(save_reader);
          break;

        case "2":
          save_reader.open_saves_file("save_2.txt");
          save_reader.erase_saves_file();
          TextStartup.display_name_pick_screen();
          awaitNameSelection(save_reader);
          break;

        case "3":
          save_reader.open_saves_file("save_3.txt");
          save_reader.erase_saves_file();
          TextStartup.display_name_pick_screen();
          awaitNameSelection(save_reader);
          break;

        default:
          awaitSaveSelection();
      }
    }

    static void awaitNameSelection(WriteRead save_reader) throws Exception {

        String answer = ReadTextInput.get_next_input();

        save_reader.add_json_entry("Hero_name", answer);
        TextStartup.display_class_pick_screen();
        awaitClassSelection(save_reader);
    }

    static void awaitClassSelection(WriteRead save_reader) throws Exception {

      String answer = ReadTextInput.get_next_input();

      Hero hero;

      switch (answer) {

        case "Warrior":
          save_reader.add_json_entry("Hero_class", answer);
          save_reader.add_json_entry("Hero_lvl", "1");
          save_reader.add_json_entry("Hero_atk", "8");
          save_reader.add_json_entry("Hero_def", "12");
          save_reader.add_json_entry("Hero_hp", "20");
          save_reader.add_json_entry("Hero_pos_x", "0");
          save_reader.add_json_entry("Hero_pos_y", "0");
          hero = new Hero(save_reader.get_json_entry("Hero_name"), answer, 1, 0, 8, 12, 20, 0, 0, null, null, null);
          TextStartup.display_hero_info_screen(save_reader);
          awaitContinueToGame(save_reader, hero);
          break;

        case "Mage":
          save_reader.add_json_entry("Hero_class", answer);
          save_reader.add_json_entry("Hero_lvl", "1");
          save_reader.add_json_entry("Hero_atk", "15");
          save_reader.add_json_entry("Hero_def", "5");
          save_reader.add_json_entry("Hero_hp", "20");
          save_reader.add_json_entry("Hero_pos_x", "0");
          save_reader.add_json_entry("Hero_pos_y", "0");
          hero = new Hero(save_reader.get_json_entry("Hero_name"), answer, 1, 0, 15, 5, 20, 0, 0, null, null, null);
          TextStartup.display_hero_info_screen(save_reader);
          awaitContinueToGame(save_reader, hero);
          break;


        case "Thief":
          save_reader.add_json_entry("Hero_class", answer);
          save_reader.add_json_entry("Hero_lvl", "1");
          save_reader.add_json_entry("Hero_atk", "10");
          save_reader.add_json_entry("Hero_def", "7");
          save_reader.add_json_entry("Hero_hp", "20");
          save_reader.add_json_entry("Hero_pos_x", "0");
          save_reader.add_json_entry("Hero_pos_y", "0");
          hero = new Hero(save_reader.get_json_entry("Hero_name"), answer, 1, 0, 10, 7, 20, 0, 0, null, null, null);
          TextStartup.display_hero_info_screen(save_reader);
          awaitContinueToGame(save_reader, hero);
          break;

        default:
          awaitClassSelection(save_reader);
      }
    }

    static void awaitContinueToGame(WriteRead save_reader, Hero hero) throws Exception {

      String answer = ReadTextInput.get_next_input();

      if (answer.matches("continue")) {
        System.out.println("Launching the game.....");
      }
      else {
        awaitContinueToGame(save_reader, hero);
      }

    }
}
