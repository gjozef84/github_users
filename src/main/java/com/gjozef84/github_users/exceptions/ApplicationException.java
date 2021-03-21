package com.gjozef84.github_users.exceptions;

import java.util.List;

public class ApplicationException extends RuntimeException {

    private final List<String> params;

    public ApplicationException(List<String> params, String message, Throwable cause) {
        super(message, cause);
        this.params = params;
    }

    public ApplicationException(String message, Throwable cause) {
        this(null, message, cause);
    }

    public ApplicationException(String message) {
        this(null, message, null);
    }
}
