package br.com.alura.forum.controller;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.service.CursosService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursosController {

    private final CursosService cursosService;

    @GetMapping
    public List<Curso> findAll() {
        return this.cursosService.findAll();
    }

    @GetMapping("/byNome")
    public Curso findByNome(@RequestParam(name = "nome") String nome) {
        return this.cursosService.findByNome(nome);
    }

    @GetMapping("/contains")
    public List<Curso> findAllContainsNome(@RequestParam(name = "nome") String nome) {
        return this.cursosService.findAllContains(nome);
    }


}
