package fr.rader.srmacro;

import fr.rader.srmacro.inputs.Keyboard;

import java.awt.*;

public class Main {

    private static Main instance;

    private Robot robot;

    private void start() {
        Keyboard keyboard = new Keyboard(robot);
        keyboard.write("Hello, World!");
    }

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        instance = this;

        try {
            robot = new Robot();

            start();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static Main getInstance() {
        return instance;
    }

    public Robot getRobot() {
        return robot;
    }
}
