package br.com.alura.forum.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class TokenDto {

    private final String token;
    private final TokenTipo tipo;
}
