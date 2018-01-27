package ro.balinator.gameengine.exception;

/**
 * Created by Balinator on 2018. 01. 27..
 */
public class ShaderException extends GameEngineException {
    public ShaderException(String message) {
        super(message);
    }

    public ShaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
