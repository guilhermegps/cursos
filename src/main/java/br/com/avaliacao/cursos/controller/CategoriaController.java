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
	public ResponseEntity<?> listAllCategorias() {
		try {
			List<CategoriaDTO> all = categoriaService.listAllDTO();
			return new ResponseEntity<>(all, HttpStatus.OK);
		} catch (Exception e) {
			WLogger.error(e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("buscar/{codCategoria}")
	public ResponseEntity<?> buscarPorCodigo(@PathVariable("codCategoria") Integer codCategoria) {
		try {
			Categoria categoria = categoriaService.findByCodigo(codCategoria);
			return new ResponseEntity<>((categoria!=null) ? categoria.convertToCategoriaDTO() : null, HttpStatus.OK);
		} catch (Exception e) {
			WLogger.error(e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("registrar")
	public ResponseEntity<?> registrar(@RequestBody CategoriaDTO categoriaDTO) {
		try {
			categoriaService.registrarNovo(categoriaDTO);
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
	public ResponseEntity<?> atualizar(@RequestBody CategoriaDTO categoriaDTO) {
		try {
			categoriaService.atualizar(categoriaDTO);
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
	@DeleteMapping("remover/{codCategoria}")
	public ResponseEntity<?> remover(@PathVariable("codCategoria") Integer codCategoria) {
		try {
			categoriaService.remover(codCategoria);
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
