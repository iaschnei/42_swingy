package com.iaschnei.app.textView;

/**
 * Startup
 */
public class Startup {

  public void display_welcome_screen() {

    System.out.println("/---------- SWINGY ----------\\");
    System.out.println("|                            |");
    System.out.println("| Please choose an option :  |");
    System.out.println("|                            |");
    System.out.println("| -> new_save                |");
    System.out.println("| -> continue                |");
    System.out.println("\\----------------------------/");

  }

  public void display_new_save_screen() {

    System.out.println("/--------- NEW SAVE ---------\\");
    System.out.println("|                            |");
    System.out.println("| Pick a save file :         |");
    System.out.println("| Existing data will be      |");
    System.out.println("| erased.                    |");
    System.out.println("|                            |");
    System.out.println("| -> 1, 2 or 3               |");
    System.out.println("\\----------------------------/");
  }

  public void display_name_pick_screen() {

    System.out.println("/--------- NEW SAVE ---------\\");
    System.out.println("|                            |");
    System.out.println("| Enter your hero's name :   |");
    System.out.println("|                            |");
    System.out.println("| ->                         |");
    System.out.println("\\----------------------------/");
  }

  public void display_class_pick_screen() {

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
  public void display_hero_info_screen() {

    System.out.println("/--------- YOUR HERO --------\\");
//TODO : fetch info from model
    System.out.println("| -> continue                |");
    System.out.println("\\----------------------------/");
  }

  public void display_continue_screen() {

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
  public void display_save_info_screen() {

    System.out.println("/--------- SAVE INFO --------\\");
    System.out.println("|                            |");
//TODO : fetch info from model
    System.out.println("| -> back                    |");
    System.out.println("| -> continue                |");
    System.out.println("\\----------------------------/");
  }
}
