package dsa.practice.exceptionHandling;

public class CustomRuntimeException extends RuntimeException{
    public CustomRuntimeException(String message) {
        super(message);
    }
}

class CustomCompileTimeException extends Exception{
    public CustomCompileTimeException(String message) {
        super(message);
    }
}
