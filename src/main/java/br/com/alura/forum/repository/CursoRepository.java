package br.com.alura.forum.repository;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    /*
    * Uso de Query and JPQL
    * */
    @Query("SELECT c FROM Curso c WHERE c.nome = :nome")
    Curso findByNomeDoCurso(@Param("nome") String nome);

}
