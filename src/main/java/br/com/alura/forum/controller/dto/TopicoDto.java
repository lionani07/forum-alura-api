package br.com.alura.forum.controller.dto;

import br.com.alura.forum.model.Topico;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder(toBuilder = true)
@Getter
public class TopicoDto {

    private final Long id;
    private final String titulo;
    private final String mensagem;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private final LocalDateTime dataCriacao;
    private final String autor;

    public static TopicoDto of(final Topico topico) {
        return TopicoDto
                .builder()
                .id(topico.getId())
                .titulo(topico.getTitulo())
                .mensagem(topico.getMensagem())
                .dataCriacao(topico.getDataCriacao())
                .autor(topico.getAutor().getNome())
                .build();
    }

    public static List<TopicoDto> of(final List<Topico> topicos) {
        return topicos
                .stream()
                .map(TopicoDto::of)
                .collect(Collectors.toList());
    }
}
