package br.com.alura.forum.controller.form;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class TopicFormToUpdate {

    @NotBlank
    private String titulo;
    @NotBlank
    private String mensagem;

}
