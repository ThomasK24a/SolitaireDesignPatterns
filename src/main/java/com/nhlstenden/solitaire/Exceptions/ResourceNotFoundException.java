package main.java.com.nhlstenden.solitaire.Exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName) {
        super(String.format("Could not find resource %s ", resourceName));
    }
}
