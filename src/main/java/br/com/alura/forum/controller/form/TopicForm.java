package br.com.alura.forum.controller.form;

import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import lombok.Getter;

@Getter
public class TopicForm {

    private String titulo;
    private String mensagem;
    private String nomeCurso;

    public Topico toTopico(final CursoRepository cursoRepository) {
        final var curso = cursoRepository.findByNomeDoCurso(this.nomeCurso);
        return new Topico(this.titulo, this.mensagem, curso);
    }
}
