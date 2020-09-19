package br.com.alura.forum.service;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursosService {

    private final CursoRepository cursoRepository;

    public Curso findByNome(String nome) {
        return this.cursoRepository.findByNomeDoCurso(nome);
    }
}
