package br.com.alura.forum.controller.dto;

import br.com.alura.forum.model.Resposta;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder(toBuilder = true)
public class RespostaDto {

    private final Long id;
    private final String mensagem;
    private final LocalDateTime dataCriacao;
    private final String nomeAutor;

    public static RespostaDto of(final Resposta resposta) {
        return RespostaDto
                .builder()
                .id(resposta.getId())
                .mensagem(resposta.getMensagem())
                .dataCriacao(resposta.getDataCriacao())
                .nomeAutor(resposta.getAutor().getNome())
                .build();
    }

    public static List<RespostaDto> of(List<Resposta> respostas) {
        return respostas
                .stream()
                .map(RespostaDto::of)
                .collect(Collectors.toList());
    }


}
