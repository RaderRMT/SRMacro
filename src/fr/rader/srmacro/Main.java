package fr.rader.srmacro;

import java.awt.*;

public class Main {

    private static Main instance;

    private Robot robot;

    private void start() {
        Mouse.setRobot(robot);
        Mouse.press(Mouse.LEFT_BUTTON);
        Mouse.release(Mouse.LEFT_BUTTON);
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
