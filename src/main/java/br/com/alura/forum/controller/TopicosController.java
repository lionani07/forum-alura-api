package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.TopicForm;
import br.com.alura.forum.service.TopicosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/topicos")
public class TopicosController {

    private final TopicosService topicosService;

    @GetMapping
    public List<TopicoDto> findAll(String nomeCurso) {
        return this.topicosService.findAll(nomeCurso);
    }

    @PostMapping
    public ResponseEntity<TopicoDto> save(@RequestBody @Valid TopicForm topicForm, UriComponentsBuilder uriComponentsBuilder) {
        final var topicoSaved = this.topicosService.save(topicForm);

        final var location = uriComponentsBuilder
                .path("/topicos/{id}")
                .buildAndExpand(topicoSaved.getId()).toUri();

        return ResponseEntity.created(location).body(topicoSaved);

    }
}
