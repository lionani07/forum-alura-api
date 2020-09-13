package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.repository.TopicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/topicos")
public class TopicosController {

    private final TopicoRepository repository;

    @GetMapping
    public List<TopicoDto> findAll(String nomeCurso) {
        if (isNull(nomeCurso)) {
            return TopicoDto.of(this.repository.findAll());
        }
        return TopicoDto.of(this.repository.findByCurso_Nome(nomeCurso));
    }
}
