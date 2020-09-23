package br.com.alura.forum.controller.handler;

import lombok.Getter;

@Getter
public class FormErrorDto {

    private final String field;
    private final String message;

    public FormErrorDto(final String field, final String message) {
        this.field = field;
        this.message = message;
    }
}
