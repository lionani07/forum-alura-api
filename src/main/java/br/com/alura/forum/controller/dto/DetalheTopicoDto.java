package br.com.alura.forum.controller.dto;

import br.com.alura.forum.model.StatusTopico;
import br.com.alura.forum.model.Topico;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder(toBuilder = true)
@Getter
public class DetalheTopicoDto {

    private final Long id;
    private final String titulo;
    private final String mensagem;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private final LocalDateTime dataCriacao;
    private final StatusTopico status;
    private final String autor;
    private List<RespostaDto> respostas = new ArrayList<>();

    public static DetalheTopicoDto of(final Topico topico) {
        return DetalheTopicoDto
                .builder()
                .id(topico.getId())
                .titulo(topico.getTitulo())
                .mensagem(topico.getMensagem())
                .dataCriacao(topico.getDataCriacao())
                .status(topico.getStatus())
                .autor(topico.getAutor().getNome())
                .respostas(RespostaDto.of(topico.getRespostas()))
                .build();
    }

}
