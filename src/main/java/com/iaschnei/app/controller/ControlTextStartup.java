package com.iaschnei.app.controller;

import com.iaschnei.app.textView.TextStartup;

/**
 * ControlTextStartup
 */
public class ControlTextStartup {

  static void awaitNewContinue() {

    String answer = ReadTextInput.get_next_input();

    switch (answer) {

      case "new_game":
        TextStartup.display_new_save_screen();
        awaitSaveSelection();
        break;

      case "continue":
        // TODO
        break;

      default:
        awaitNewContinue();
    }

    static void awaitSaveSelection() {

      //TODO  :
      //      -> select 1, 2 or 3 then save in save file
      //      -> then go to the next step, select class, name etc..
    }
}
