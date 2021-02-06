package br.com.avaliacao.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.avaliacao.cursos.model.Categoria;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	public Categoria findOneByCodigo(Integer codigo);

}
