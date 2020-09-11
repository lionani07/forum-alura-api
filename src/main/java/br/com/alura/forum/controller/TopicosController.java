package br.com.alura.forum.controller;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @GetMapping
    public List<Topico> findAll() {
        final var topic = new Topico("Duvida", "Port de spring", new Curso("Spring", "Programacao"));
        return List.of(topic, topic);
    }
}
