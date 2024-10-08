package com.iaschnei.app.textView;

import com.iaschnei.app.model.WriteRead;

/**
 * TextStartup
 */
public class TextStartup {

  public static void display_welcome_screen() {

    System.out.println("/---------- SWINGY ----------\\");
    System.out.println("|                            |");
    System.out.println("| Please choose an option :  |");
    System.out.println("|                            |");
    System.out.println("| -> new_save                |");
    System.out.println("| -> continue                |");
    System.out.println("\\----------------------------/");

  }

  public static void display_new_save_screen() {

    System.out.println("/--------- NEW SAVE ---------\\");
    System.out.println("|                            |");
    System.out.println("| Pick a save file :         |");
    System.out.println("| Existing data will be      |");
    System.out.println("| erased.                    |");
    System.out.println("|                            |");
    System.out.println("| -> 1, 2 or 3               |");
    System.out.println("\\----------------------------/");
  }

  public static void display_name_pick_screen() {

    System.out.println("/--------- NEW SAVE ---------\\");
    System.out.println("|                            |");
    System.out.println("| Enter your hero's name :   |");
    System.out.println("|                            |");
    System.out.println("| ->                         |");
    System.out.println("\\----------------------------/");
  }

  public static void display_class_pick_screen() {

    System.out.println("/--------- NEW SAVE ---------\\");
    System.out.println("|                            |");
    System.out.println("| Choose a class :           |");
    System.out.println("|                            |");
    System.out.println("| -> Warrior                 |");
    System.out.println("| -> Mage                    |");
    System.out.println("| -> Thief                   |");
    System.out.println("\\----------------------------/");
  }

  // Displayed after a new save has been created
  // - Hero name (Class) Level 1
  public static void display_hero_info_screen(WriteRead save_reader) throws Exception {

    String hero_name = save_reader.get_json_entry("Hero_name");
    String hero_class = save_reader.get_json_entry("Hero_class");
    String hero_lvl = save_reader.get_json_entry("Hero_lvl");
    String hero_atk = save_reader.get_json_entry("Hero_atk");
    String hero_def = save_reader.get_json_entry("Hero_def");
    String hero_hp = save_reader.get_json_entry("Hero_hp");

    System.out.println("/--------- YOUR HERO --------\\");
    System.out.println("| - " + String.format("%-25s", hero_name) + "|");
    System.out.println("|   " + String.format("%-8s", hero_class) + String.format("%-17s", " Level: " + hero_lvl) + "|");
    System.out.println("|   " + String.format("%-25s", "ATK: " + hero_atk + " DEF: " + hero_def + " HP: " + hero_hp) + "|");
    System.out.println("|                            |");
    System.out.println("| -> continue                |");
    System.out.println("\\----------------------------/");
  }

  public static void display_continue_screen() {

    System.out.println("/--------- CONTINUE ---------\\");
    System.out.println("|                            |");
    System.out.println("| Pick a save file :         |");
    System.out.println("| You will be able to see    |");
    System.out.println("| infos about the save.      |");
    System.out.println("|                            |");
    System.out.println("| -> 1, 2 or 3               |");
    System.out.println("\\----------------------------/");
  }

  // Displayed after picking a save file from continue
  // all relevant info
  public static void display_save_info_screen() {

    System.out.println("/--------- SAVE INFO --------\\");
    System.out.println("|                            |");

    System.out.println("| -> back                    |");
    System.out.println("| -> continue                |");
    System.out.println("\\----------------------------/");
  }
}
