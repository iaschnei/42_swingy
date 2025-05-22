package com.swingy;

import com.swingy.controller.GameController;
import com.swingy.storage.DbUtil;
import com.swingy.view.console.ConsoleView;

public class App {

    public static void main(String[] args) {

        // Make sure the database is properly closed if the program exits
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down database...");
            DbUtil.shutdown();
        }));


        DbUtil.initDatabase();

        GameController gameController;

        try {

            if (args.length != 1)
            {
                System.out.println("Usage: java -jar Swingy.jar [gui/console]");
                return;
            }

            if (args[0].equals("gui")) {
                System.out.println("Starting GUI");
            }
            else if (args[0].equals("console")) {
                ConsoleView consoleView = new ConsoleView();
                gameController = new GameController(consoleView);
            }
            else {
                throw new Exception("Unknown mode, please choose one of the following: gui, console");
            }


        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
}
