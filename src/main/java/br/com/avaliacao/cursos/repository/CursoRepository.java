package br.com.avaliacao.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.avaliacao.cursos.model.Curso;


/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

	public Curso findOneByCodigo(Long codigo);
	
}
