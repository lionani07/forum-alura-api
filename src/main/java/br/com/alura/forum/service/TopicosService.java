package br.com.alura.forum.service;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.TopicForm;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.TopicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class TopicosService {

    private final TopicoRepository topicoRepository;
    private final CursosService cursoService;

    public List<TopicoDto> findAll(final String nomeCurso) {
        if (isNull(nomeCurso)) {
            return TopicoDto.of(this.topicoRepository.findAll());
        }
        return TopicoDto.of(this.topicoRepository.findByCurso_Nome(nomeCurso));
    }


    public TopicoDto save(TopicForm topicForm) {
        final var curso = cursoService.findByNome(topicForm.getNomeCurso());
        final var topicoSaved = this.topicoRepository.save(new Topico(topicForm.getTitulo(), topicForm.getMensagem(), curso));
        return TopicoDto.of(topicoSaved);
    }
}
