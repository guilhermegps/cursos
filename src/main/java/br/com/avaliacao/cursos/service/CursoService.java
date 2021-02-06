package br.com.avaliacao.cursos.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

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

	public List<CursoDTO> listAllDTO() {
		List<Curso> all = listAll();
		List<CursoDTO> allDTO = all.parallelStream().map(Curso::convertToCursoDTO)
			.collect(Collectors.toList());
		
		return allDTO;
	}

	@Transactional
	public void registrarNovo(CursoDTO cursoDTO) {
		if(cursoDTO.getDtInicio().after(cursoDTO.getDtFim()))
			throw new ValidationException("A data de inicio não pode ser após a data fim.");
		
		List<Curso> periodUsed = cursoRepository.listForPeriodUsed(cursoDTO.getDtInicio(), cursoDTO.getDtFim());
		if(periodUsed!=null && !periodUsed.isEmpty())
			throw new ValidationException("Existe(m) curso(s) planejados(s) dentro do período informado.");
		
		Date today = DateUtils.truncate(new Date(), Calendar.DATE);
		if(cursoDTO.getDtInicio().before(today))
			throw new ValidationException("A data de inicio não pode ser no passado.");
		
		Categoria categoria = categoriaService.findByCodigo(cursoDTO.getCodCategoria());
		Curso curso = cursoDTO.convertToCurso();
		curso.setCategoria(categoria);
		cursoRepository.save(curso);
	}

	@Transactional
	public void atualizar(CursoDTO cursoDTO) {
		Curso curso = findByCodigo(cursoDTO.getCodigo());
		if(curso==null)
			throw new ValidationException("Este curso não existe.");

		if(cursoDTO.getDtInicio().after(cursoDTO.getDtFim()))
			throw new ValidationException("A data de inicio não pode ser após a data fim.");
		if(!curso.getDtInicio().equals(cursoDTO.getDtInicio())
				|| !curso.getDtFim().equals(cursoDTO.getDtFim())) {
			List<Curso> periodUsed = cursoRepository.listForPeriodUsed(cursoDTO.getDtInicio(), cursoDTO.getDtFim());
			if( periodUsed!=null 
					&& (periodUsed.size()>1 || !periodUsed.stream().findFirst().get().getId().equals(curso.getId())) )
				throw new ValidationException("Existe(m) curso(s) planejados(s) dentro do período informado.");
		}
		
		Date today = DateUtils.truncate(new Date(), Calendar.DATE);
		if(cursoDTO.getDtInicio().before(today) && !curso.getDtInicio().equals(cursoDTO.getDtInicio()))
			throw new ValidationException("A data de inicio não pode ser no passado.");
		
		curso.setDescricao(cursoDTO.getDescricao());
		curso.setDtInicio(cursoDTO.getDtInicio());
		curso.setDtFim(cursoDTO.getDtFim());
		curso.setQtdAlunos(cursoDTO.getQtdAlunos());
		Categoria categoria = categoriaService.findByCodigo(cursoDTO.getCodCategoria());
		curso.setCategoria(categoria);
		cursoRepository.save(curso);
	}

	@Transactional
	public void remover(Integer codCurso) {
		Curso curso = findByCodigo(codCurso);
		if(curso==null)
			throw new ValidationException("Este curso não existe.");
		
		cursoRepository.delete(curso);
	}
}
