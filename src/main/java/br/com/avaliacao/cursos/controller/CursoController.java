package br.com.avaliacao.cursos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.avaliacao.cursos.WLogger;
import br.com.avaliacao.cursos.exception.ValidationException;
import br.com.avaliacao.cursos.model.Curso;
import br.com.avaliacao.cursos.model.dto.CursoDTO;
import br.com.avaliacao.cursos.service.CursoService;


/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Controller
@RequestMapping("rest/curso")
public class CursoController {
	
	@Autowired
	private CursoService cursoService;
	
	@GetMapping("listar/todos")
	public ResponseEntity<?> listAllCursos() {
		try {
			List<CursoDTO> all = cursoService.listAllDTO();
			return new ResponseEntity<>(all, HttpStatus.OK);
		} catch (Exception e) {
			WLogger.error(e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("listar/porDescricao/{descricao}")
	public ResponseEntity<?> listarPorDescricao(@PathVariable("descricao") String descricao) {
		try {
			List<CursoDTO> listaCursos = cursoService.listForDescricaoDTO(descricao);
			return new ResponseEntity<>(listaCursos, HttpStatus.OK);
		} catch (Exception e) {
			WLogger.error(e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("buscar/{codCurso}")
	public ResponseEntity<?> buscarPorCodigo(@PathVariable("codCurso") Integer codCurso) {
		try {
			Curso curso = cursoService.findByCodigo(codCurso);
			return new ResponseEntity<>((curso!=null) ? curso.convertToCursoDTO() : null, HttpStatus.OK);
		} catch (Exception e) {
			WLogger.error(e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("registrar")
	public ResponseEntity<?> registrar(@RequestBody CursoDTO cursoDTO) {
		try {
			cursoService.registrarNovo(cursoDTO);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (ValidationException e) {
			WLogger.error(e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (Exception e) {
			WLogger.error(e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("atualizar")
	public ResponseEntity<?> atualizar(@RequestBody CursoDTO cursoDTO) {
		try {
			cursoService.atualizar(cursoDTO);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (ValidationException e) {
			WLogger.error(e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (Exception e) {
			WLogger.error(e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Apenas para demonstração, na vida real utilizaria deleção lógica
	@DeleteMapping("remover/{codCurso}")
	public ResponseEntity<?> remover(@PathVariable("codCurso") Integer codCurso) {
		try {
			cursoService.remover(codCurso);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (ValidationException e) {
			WLogger.error(e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (Exception e) {
			WLogger.error(e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
