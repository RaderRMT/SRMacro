package fr.rader.srmacro;

import java.awt.*;

public class Main {

    private static Main instance;

    private Robot robot;

    private void start() {

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
