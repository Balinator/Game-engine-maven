package ro.balinator.gameengine.exception;

/**
 * Created by Balinator on 2018. 01. 27..
 */
public class GameEngineException extends RuntimeException{

    public GameEngineException(String message) {
        super(message);
    }

    public GameEngineException(String message, Throwable cause) {
        super(message, cause);
    }
}
