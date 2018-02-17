package ro.balinator.gameengine.exception;

/**
 * Created by Balinator on 2018. 01. 27..
 */
public class FileException extends GameEngineException{

    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
