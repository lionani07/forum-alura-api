package br.com.alura.forum.controller.dto;

import br.com.alura.forum.model.Topico;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Getter
public class TopicoDto {

    private final Long id;
    private final String titulo;
    private final String mensagem;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private final LocalDateTime dataCriacao;

    public static TopicoDto of(final Topico topico) {
        return TopicoDto
                .builder()
                .id(topico.getId())
                .titulo(topico.getTitulo())
                .mensagem(topico.getMensagem())
                .dataCriacao(topico.getDataCriacao())
                .build();
    }

    public static Page<TopicoDto> of(final Page<Topico> topicos) {
        return topicos.map(TopicoDto::of);
    }
}
