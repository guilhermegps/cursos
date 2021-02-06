package br.com.avaliacao.cursos.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.avaliacao.cursos.exception.ValidationException;
import br.com.avaliacao.cursos.model.Categoria;
import br.com.avaliacao.cursos.model.Curso;
import br.com.avaliacao.cursos.model.dto.CursoDTO;
import br.com.avaliacao.cursos.repository.CursoRepository;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Service
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepository;
	@Autowired
	private CategoriaService categoriaService;
	
	public List<Curso> listAll(){
		return cursoRepository.findAll();
	}
	
	public Optional<Curso> findById(Long id){
		return cursoRepository.findById(id);
	}
	
	public Curso findByCodigo(Integer codigo){
		return cursoRepository.findOneByCodigo(codigo);
	}
	
	public void registrarNovo(CursoDTO cursoDTO) {
		List<Curso> periodUsed = cursoRepository.listForPeriodUsed(cursoDTO.getDtInicio(), cursoDTO.getDtFim());
		if(periodUsed!=null && !periodUsed.isEmpty())
			throw new ValidationException("Existe(m) curso(s) planejados(s) dentro do período informado.");
		
		Date today = DateUtils.truncate(new Date(), Calendar.DATE);
		if(cursoDTO.getDtInicio().before(today))
			throw new ValidationException("Não é permitido registrar um curso com data de inicio passada.");
		
		Categoria categoria = categoriaService.findByCodigo(cursoDTO.getCodCategoria());
		Curso curso = cursoDTO.convertToCurso();
		curso.setCategoria(categoria);
		cursoRepository.save(curso);
	}

	public List<CursoDTO> listAllDTO() {
		List<Curso> all = listAll();
		List<CursoDTO> allDTO = all.parallelStream().map(Curso::convertToCursoDTO)
			.collect(Collectors.toList());
		
		return allDTO;
	}
}
