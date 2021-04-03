package fr.rader.srmacro.exceptions;

public class NoRobotException extends RuntimeException {
    
    public NoRobotException() {
        super();
    }

    public NoRobotException(String message) {
        super(message);
    }
}
