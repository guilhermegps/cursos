package br.com.avaliacao.cursos.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	private CursoService cursoService;
	
	@GetMapping("listar/todos")
	public ResponseEntity<Object> listAllCursos() {
		try {
			List<CursoDTO> all = cursoService.listAllDTO();
			return ResponseEntity.ok(all);
		} catch (Exception e) {
			WLogger.error(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@PostMapping("registrar")
	public ResponseEntity<Object> registrar(@RequestBody CursoDTO cursoDTO) {
		try {
			cursoService.registrarNovo(cursoDTO);
			return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
		} catch (ValidationException e) {
			WLogger.error(e);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
		} catch (Exception e) {
			WLogger.error(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@PutMapping("atualizar/{codCurso}")
	public ResponseEntity<Object> atualizar(@PathParam("codCurso") Integer codCurso, @RequestBody CursoDTO cursoDTO) {
		try {
			
			return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
		} catch (ValidationException e) {
			WLogger.error(e);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
		} catch (Exception e) {
			WLogger.error(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@DeleteMapping("remover/{codCurso}")
	public ResponseEntity<Object> remover(@PathParam("codCurso") Integer codCurso) {
		try {
			
			return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
		} catch (ValidationException e) {
			WLogger.error(e);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
		} catch (Exception e) {
			WLogger.error(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
