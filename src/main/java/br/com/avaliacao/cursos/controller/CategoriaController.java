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
import br.com.avaliacao.cursos.model.Categoria;
import br.com.avaliacao.cursos.model.dto.CategoriaDTO;
import br.com.avaliacao.cursos.service.CategoriaService;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Controller
@RequestMapping("rest/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("listar/todos")
	public ResponseEntity<Object> listAllCategorias() {
		try {
			List<CategoriaDTO> all = categoriaService.listAllDTO();
			return ResponseEntity.ok(all);
		} catch (Exception e) {
			WLogger.error(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("buscar/{codCategoria}")
	public ResponseEntity<Object> buscarPorCodigo(@PathVariable("codCategoria") Integer codCategoria) {
		try {
			Categoria categoria = categoriaService.findByCodigo(codCategoria);
			return ResponseEntity.ok((categoria!=null) ? categoria.convertToCategoriaDTO() : null);
		} catch (Exception e) {
			WLogger.error(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@PostMapping("registrar")
	public ResponseEntity<Object> registrar(@RequestBody CategoriaDTO categoriaDTO) {
		try {
			categoriaService.registrarNovo(categoriaDTO);
			return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
		} catch (ValidationException e) {
			WLogger.error(e);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
		} catch (Exception e) {
			WLogger.error(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@PutMapping("atualizar")
	public ResponseEntity<Object> atualizar(@RequestBody CategoriaDTO categoriaDTO) {
		try {
			categoriaService.atualizar(categoriaDTO);
			return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
		} catch (ValidationException e) {
			WLogger.error(e);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
		} catch (Exception e) {
			WLogger.error(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	// Apenas para demonstração, na vida real utilizaria deleção lógica
	@DeleteMapping("remover/{codCategoria}")
	public ResponseEntity<Object> remover(@PathVariable("codCategoria") Integer codCategoria) {
		try {
			categoriaService.remover(codCategoria);
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
