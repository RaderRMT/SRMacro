package fr.rader.srmacro;

import java.awt.*;
import java.awt.event.InputEvent;

public class Mouse {

    private static Robot mouseRobot;

    public static int LEFT_BUTTON = InputEvent.BUTTON1_DOWN_MASK;
    public static int MIDDLE_BUTTON = InputEvent.BUTTON2_DOWN_MASK;
    public static int RIGHT_BUTTON = InputEvent.BUTTON3_DOWN_MASK;

    /**
     * Move the mouse to a new position
     * @param x New mouse X
     * @param y New mouse Y
     */
    public static void move(int x, int y) {
        if(mouseRobot == null) throw new NoRobotException("Please use Mouse#setRobot before using this function!");

        int newX = verifyPosition(x, getDisplayWidth());
        int newY = verifyPosition(y, getDisplayHeight());

        mouseRobot.mouseMove(newX, newY);
    }

    /**
     * Move the mouse relative to it's current position
     * @param dx Direction in the X axis
     * @param dy Direction in the Y axis
     */
    public static void moveRelative(int dx, int dy) {
        move(getMouseX() + dx, getMouseY() + dy);
    }

    public static void press(int mouseButton) {
        if(mouseRobot == null) throw new NoRobotException("Please use Mouse#setRobot before using this function!");

        mouseRobot.mousePress(mouseButton);
    }

    public static void release(int mouseButton) {
        if(mouseRobot == null) throw new NoRobotException("Please use Mouse#setRobot before using this function!");

        mouseRobot.mouseRelease(mouseButton);
    }

    private static int verifyPosition(int position, int max) {
        if(position < 0) position = 0;
        if(position > getDisplayWidth()) position = max - 1;

        return position;
    }

    public static int getMouseX() {
        return (int) MouseInfo.getPointerInfo().getLocation().getX();
    }

    public static int getMouseY() {
        return (int) MouseInfo.getPointerInfo().getLocation().getY();
    }

    private static int getDisplayWidth() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
    }

    private static int getDisplayHeight() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
    }

    public static void setRobot(Robot robot) {
        mouseRobot = robot;
    }
}
