package br.com.alura.forum.controller.form;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class TopicForm {

    @NotBlank
    private String titulo;
    @NotBlank
    private String mensagem;
    @NotBlank
    private String nomeCurso;
}
