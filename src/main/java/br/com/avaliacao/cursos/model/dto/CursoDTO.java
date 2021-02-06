package br.com.avaliacao.cursos.model.dto;

import java.util.Date;

import br.com.avaliacao.cursos.model.Curso;
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
	
	private Integer codigo;
	private Date dtRegistro;
	private boolean ativo;
	private String descricao;
	private Date dtInicio;
	private Date dtFim;
	private Integer qtdAlunos;
	
	private Integer codCategoria;
	
	public Curso convertToCurso() {
		return Curso.builder()
			.descricao(descricao)
			.dtFim(dtFim)
			.dtFim(dtFim)
			.qtdAlunos(qtdAlunos)
			.build();
	}

}
