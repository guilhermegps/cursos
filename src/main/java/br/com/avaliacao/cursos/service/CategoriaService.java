package br.com.avaliacao.cursos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.avaliacao.cursos.model.Categoria;
import br.com.avaliacao.cursos.repository.CategoriaRepository;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria findByCodigo(Integer codigo){
		return categoriaRepository.findOneByCodigo(codigo);
	}

}
