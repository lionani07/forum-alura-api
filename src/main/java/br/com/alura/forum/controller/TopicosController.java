package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.TopicForm;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/topicos")
public class TopicosController {

    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> findAll(String nomeCurso) {
        if (isNull(nomeCurso)) {
            return TopicoDto.of(this.topicoRepository.findAll());
        }
        return TopicoDto.of(this.topicoRepository.findByCurso_Nome(nomeCurso));
    }


    /*@PostMapping
    public ResponseEntity<TopicoDto> save(Topico topico) {
        final var topicoSave = this.repository.save(topico);
        final var topicoDto = TopicoDto.of(topicoSave);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(topico.getId())
                .toUri();
        return ResponseEntity.created(uri).body(topicoDto);
    }*/

    @PostMapping
    public void save(@RequestBody TopicForm topicForm) {
        final var topicoToSave = topicForm.toTopico(cursoRepository);
        this.topicoRepository.save(topicoToSave);
    }
}
