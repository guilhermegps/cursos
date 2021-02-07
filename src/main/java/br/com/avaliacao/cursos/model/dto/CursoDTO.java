package br.com.avaliacao.cursos.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@JsonFormat(timezone="GMT-3")
	private Date dtInicio;
	@JsonFormat(timezone="GMT-3")
	private Date dtFim;
	private Integer qtdAlunos;
	
	private String descCategoria;
	private Integer codCategoria;
	
	public Curso convertToCurso() {
		return Curso.builder()
			.descricao(descricao)
			.dtInicio(dtInicio)
			.dtFim(dtFim)
			.qtdAlunos(qtdAlunos)
			.build();
	}

}
