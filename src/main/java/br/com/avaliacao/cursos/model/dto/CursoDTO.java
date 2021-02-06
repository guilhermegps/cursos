package br.com.avaliacao.cursos.model.dto;

import java.util.Date;

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
public class CursoDTO {
	
	private Long codigo;
	private Date dataRegistro;
	private boolean ativo;
	private String descricao;

}
