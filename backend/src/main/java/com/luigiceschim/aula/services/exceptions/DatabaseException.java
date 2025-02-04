package com.luigiceschim.aula.services.exceptions;



public class DatabaseException extends RuntimeException {
    public DatabaseException(String mensagem) {
        super(mensagem);
    }
}
