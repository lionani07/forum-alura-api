package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.TopicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/topicos")
public class TopicosController {

    private final TopicoRepository repository;

    @GetMapping
    public List<TopicoDto> findAll() {
        final var topicos = this.repository.findAll();
        return TopicoDto.of(topicos);
    }
}
