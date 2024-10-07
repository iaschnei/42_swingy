package com.iaschnei.app.controller;

import com.iaschnei.app.textView.TextStartup;

/**
 * Startup
 */
public class Startup {

  public void control_startup(String mode) throws Exception {

    switch (mode) {

      case "console":
        // TODO get_save_info
        TextStartup.display_welcome_screen();
        ControlTextStartup.awaitNewContinue();
        break;

      case "gui":
        // TODO
        break;

      default:
        throw new Exception(
            "Unknown mode, please choose between gui and console");
    }
  }
}
