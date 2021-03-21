package com.gjozef84.github_users.exceptions;

public class ResourceNotFoundException extends ApplicationException {
    public ResourceNotFoundException(final Class objectClass, final Object param, final Object objectValue) {
        super(String.format("Resource %s with %s = %s not found", objectClass.getSimpleName(), param, objectValue));
    }
}
