package com.swingy;

import com.swingy.controller.GameController;
import com.swingy.storage.DbUtil;
import com.swingy.view.console.ConsoleView;
import com.swingy.view.gui.GUIView;

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

            if (args.length < 1 || args.length > 2)
            {
                System.out.println("Usage: java -jar Swingy.jar [gui/console] [-v]");
                return;
            }

            boolean visible_enemies = false;

            if (args.length == 2) {
                if (args[1].equals("-v")) {
                    visible_enemies = true;
                }
                else {
                    throw new Exception("Unknown option, usage: java -jar Swingy.jar [gui/console] [-v]");
                }
            }

            if (args[0].equals("gui")) {
                GUIView guiView = new GUIView(visible_enemies);
                gameController = new GameController(guiView);
            }
            else if (args[0].equals("console")) {
                ConsoleView consoleView = new ConsoleView(visible_enemies);
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
