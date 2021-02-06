package br.com.avaliacao.cursos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.avaliacao.cursos.model.Curso;


/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

	public Curso findOneByCodigo(Integer codigo);
	
	
	@Query("select distinct c from Curso c"
			+ " where c.dtInicio between :dtInicio and :dtFim"
			+ " or c.dtFim between :dtInicio and :dtFim")
	public List<Curso> listForPeriodUsed(@Param("dtInicio") Date dtInicio, @Param("dtFim") Date dtFim);
	
}
