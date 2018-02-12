package ro.balinator.gameengine.exception;

/**
 * Created by Balinator on 2018. 02. 06..
 */
public class StageException extends GameEngineException {
    public StageException(String message) {
        super(message);
    }

    public StageException(String message, Throwable cause) {
        super(message, cause);
    }
}
