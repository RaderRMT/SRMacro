package fr.rader.srmacro;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Keyboard {

    private static Robot keyboardRobot;

    private static boolean isHolding = false;

    public static void write(String string) {
        if(keyboardRobot == null) throw new NoRobotException("Please use Keyboard#setRobot before using this function!");

        for(char character : string.toCharArray()) {
            boolean isUpperCase = Character.isUpperCase(character);

            if(isUpperCase) keyboardRobot.keyPress(KeyEvent.VK_SHIFT);
            write(validateCharacter(character));
            if(isUpperCase) keyboardRobot.keyRelease(KeyEvent.VK_SHIFT);
        }
    }

    /**
     * Holds a {@code character} for a {@code duration} amount of time
     * @param character The character to hold
     * @param duration The duration of the hold (in milliseconds)
     */
    public static void hold(char character, int duration) {
        if(keyboardRobot == null) throw new NoRobotException("Please use Keyboard#setRobot before using this function!");
        if(duration < 0) throw new IllegalArgumentException("duration must be a positive number");

        int keyCode = validateCharacter(character);

        isHolding = true;
        Thread thread = new Thread(() -> {
            try {
                keyboardRobot.keyPress(keyCode);
                Thread.sleep(500);

                while(isHolding) {
                    keyboardRobot.keyPress(keyCode);

                    Thread.sleep(20);
                }
            } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        });
        thread.start();

        try {
            Thread.sleep(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }

        keyboardRobot.keyRelease(keyCode);
        isHolding = false;
    }

    /**
     * Write a key based on it's key code
     * @param keyCode Key code of the key to write.
     */
    public static void write(int keyCode) {
        if(keyboardRobot == null) throw new NoRobotException("Please use Keyboard#setRobot before using this function!");

        keyboardRobot.keyPress(keyCode);
        keyboardRobot.keyRelease(keyCode);
    }

    private static int validateCharacter(char character) {
        int keyCode = KeyEvent.getExtendedKeyCodeForChar(character);
        if(keyCode == KeyEvent.CHAR_UNDEFINED) {
            throw new RuntimeException("No key code found for character '" + character + "'");
        }

        return keyCode;
    }

    public static void setRobot(Robot robot) {
        keyboardRobot = robot;
    }
}
