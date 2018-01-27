package ro.balinator.gameengine.exception;

/**
 * Created by Balinator on 2018. 01. 27..
 */
public class DisplayException extends GameEngineException {

    public DisplayException(String message) {
        super(message);
    }

    public DisplayException(String message, Throwable cause) {
        super(message, cause);
    }
}
