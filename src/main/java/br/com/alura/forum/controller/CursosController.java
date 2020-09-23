package br.com.alura.forum.controller;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursosController {

    private final CursoRepository cursoRepository;

    @GetMapping
    public List<Curso> findAll(String nomeCurso) {
        return this.cursoRepository.findAll();
    }


}
