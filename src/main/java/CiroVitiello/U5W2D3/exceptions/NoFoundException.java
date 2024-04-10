package CiroVitiello.U5W2D3.exceptions;

public class NoFoundException extends RuntimeException {
    public NoFoundException(long id) {
        super("element with " + id + " not found");
    }
}