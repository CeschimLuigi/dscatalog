package com.luigiceschim.aula.services.exceptions;



public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String mensagem) {
        super(mensagem);
    }
}
