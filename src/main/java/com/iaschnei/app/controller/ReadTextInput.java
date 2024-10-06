package com.iaschnei.app.controller;

import java.util.Scanner;

/**
 * ReadTextInput
 */
public class ReadTextInput {

  public String get_next_input() {

    var console = System.console();

    Scanner input = new Scanner(console.reader());

    String ret = input.nextLine();

    input.close();

    return (ret);

  }
}
