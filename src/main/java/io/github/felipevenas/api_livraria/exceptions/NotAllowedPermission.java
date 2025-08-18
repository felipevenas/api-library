package io.github.felipevenas.api_livraria.exceptions;

public class NotAllowedPermission extends RuntimeException {
    public NotAllowedPermission(String message) {
        super(message);
    }
}
