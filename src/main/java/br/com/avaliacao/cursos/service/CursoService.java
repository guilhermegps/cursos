package br.com.avaliacao.cursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.avaliacao.cursos.model.Curso;
import br.com.avaliacao.cursos.repository.CursoRepository;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Service
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	public List<Curso> listAll(){
		return cursoRepository.findAll();
	}
	
	public Optional<Curso> findById(Long id){
		return cursoRepository.findById(id);
	}
	
	public Curso findByCodigo(Long codigo){
		return cursoRepository.findOneByCodigo(codigo);
	}
	
	public void save(Curso curso) {
		cursoRepository.save(curso);
	}
}
