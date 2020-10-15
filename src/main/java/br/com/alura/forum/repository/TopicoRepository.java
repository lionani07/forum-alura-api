package br.com.alura.forum.repository;

import br.com.alura.forum.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    /*
    * Poderia chamarse findByCursoNome, mas poderia dar conflito si tiver um atributo chamado cursoNome.
    * Entao como Guideline Lio: Vou usar underline(_) para este tipo de metodos.
    * */
    Page<Topico> findByCurso_Nome(String nomeCurso, Pageable pageable);
}
