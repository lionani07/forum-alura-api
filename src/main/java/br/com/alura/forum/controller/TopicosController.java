package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @GetMapping
    public List<TopicoDto> findAll() {
        final var topico = new Topico("Duvida", "Duvida de Srping", new Curso("Spring", "Programacao"));

        final var topicoDto = TopicoDto.of(topico);

        return TopicoDto.of(List.of(topico, topico));
    }
}
