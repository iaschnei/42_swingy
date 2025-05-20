package com.swingy;

public class App {

    public static void main(String[] args) {

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
                System.out.println("Starting Console");
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
