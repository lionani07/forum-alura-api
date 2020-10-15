package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.DetalheTopicoDto;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.TopicForm;
import br.com.alura.forum.controller.form.TopicFormToUpdate;
import br.com.alura.forum.service.TopicosService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/topicos")
public class TopicosController {

    private final TopicosService topicosService;

    @GetMapping
    public Page<TopicoDto> findAll(
            @RequestParam(required = false) String nomeCurso,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1") int size,
            @RequestParam(defaultValue = "id") String orderBy,
            @RequestParam(defaultValue = "DESC") String direction) {

        final var sortDirection = Sort.Direction.valueOf(direction.toUpperCase());
        final var sort = Sort.by(sortDirection, orderBy);

        return this.topicosService.findAll(nomeCurso, PageRequest.of(page, size,sort));
    }

    @PostMapping
    public ResponseEntity<TopicoDto> save(@RequestBody @Valid TopicForm topicForm, UriComponentsBuilder uriComponentsBuilder) {
        final var topicoSaved = this.topicosService.save(topicForm);

        final var location = uriComponentsBuilder
                .path("/topicos/{id}")
                .buildAndExpand(topicoSaved.getId()).toUri();

        return ResponseEntity.created(location).body(topicoSaved);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheTopicoDto> detail(@PathVariable Long id) {
        final var detail = this.topicosService.findById(id);
        return ResponseEntity.ok(detail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoDto> update(@PathVariable Long id, @RequestBody @Valid TopicFormToUpdate topicFormToUpdate) {
        return this.topicosService.update(id, topicFormToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        this.topicosService.delete(id);
        return ResponseEntity.ok().build();
    }

}
