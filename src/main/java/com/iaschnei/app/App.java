package com.iaschnei.app;

import com.iaschnei.app.controller.Startup;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception{

      if (args.length != 1) {
        System.out.println("Please choose between 'gui' and 'console'");
        return ;
      }

      try {

        Startup.control_startup(args[0]);
      }
      catch (Exception e) {
        System.out.println(e);
      }
    }
}
