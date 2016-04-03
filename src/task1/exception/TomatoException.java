package task1.exception;

public class TomatoException extends Exception {

    public TomatoException() {
        super();
    }

    public TomatoException(Exception exception) {
        super(exception);        
    }

    public TomatoException(String message) {
        super(message);        
    }
    
}
