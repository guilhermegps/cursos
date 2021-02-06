package br.com.avaliacao.cursos.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.avaliacao.cursos.exception.ValidationException;
import br.com.avaliacao.cursos.model.Categoria;
import br.com.avaliacao.cursos.model.dto.CategoriaDTO;
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

	public List<Categoria> listAll() {
		return categoriaRepository.findAll();
	}

	public List<CategoriaDTO> listAllDTO() {
		List<Categoria> all = listAll();
		List<CategoriaDTO> allDTO = all.parallelStream().map(Categoria::convertToCategoriaDTO)
			.collect(Collectors.toList());
		return allDTO;
	}

	@Transactional
	public void registrarNovo(CategoriaDTO categoriaDTO) {
		Categoria categoria = categoriaDTO.convertToCategoria();
		
		categoriaRepository.save(categoria);
	}

	@Transactional
	public void atualizar(CategoriaDTO categoriaDTO) {
		Categoria categoria = findByCodigo(categoriaDTO.getCodigo());
		if(categoria==null)
			throw new ValidationException("Esta categoria não existe.");
		
		categoria.setDescricao(categoriaDTO.getDescricao());
		categoriaRepository.save(categoria);
	}

	@Transactional
	public void remover(Integer codCategoria) {
		Categoria categoria = findByCodigo(codCategoria);
		if(categoria==null)
			throw new ValidationException("Esta categoria não existe.");
		
		categoriaRepository.delete(categoria);
	}

}
