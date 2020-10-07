package br.com.alura.forum.service.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Long id) {
        super(String.format("Resource not found with id: %s", id));
    }
}
