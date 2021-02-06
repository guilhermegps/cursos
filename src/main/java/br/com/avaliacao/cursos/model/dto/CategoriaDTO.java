package br.com.avaliacao.cursos.model.dto;

import java.util.Date;

import br.com.avaliacao.cursos.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {
	
	private Integer codigo;
	private Date dtRegistro;
	private boolean ativo;
	private String descricao;
	
	public Categoria convertToCategoria() {
		return Categoria.builder()
			.descricao(descricao)
			.build();
	}

}
