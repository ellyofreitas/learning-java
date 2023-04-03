package br.com.ellyofreitas.learning.domain.errors;

public class ValidationError extends RuntimeException {
    public ValidationError(String message) {
        super(message);
    }
}