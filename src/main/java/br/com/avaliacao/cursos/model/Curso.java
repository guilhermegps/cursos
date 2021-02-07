package br.com.avaliacao.cursos.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.avaliacao.cursos.model.base.EntidadeBase;
import br.com.avaliacao.cursos.model.dto.CursoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_curso")
public class Curso extends EntidadeBase {
	
	@Basic
	@Column(length = 50, nullable = false)
	private String descricao;
	
	@Basic
	@Column(name = "dt_inicio", nullable = false)
	private Date dtInicio;
	
	@Basic
	@Column(name = "dt_fim", nullable = false)
	private Date dtFim;
	
	@Basic
	@Column(name = "qtd_alunos", nullable = true)
	private Integer qtdAlunos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria", nullable = false)
	private Categoria categoria;
	
	public CursoDTO convertToCursoDTO() {
		CursoDTO cursoDTO = CursoDTO.builder()
				.codigo(codigo)
				.ativo(ativo)
				.dtRegistro(dtRegistro)
				.descricao(descricao)
				.dtInicio(dtInicio)
				.dtFim(dtFim)
				.qtdAlunos(qtdAlunos)
				.build();
		
		if(categoria!=null) {
			cursoDTO.setCodCategoria(categoria.getCodigo());
			cursoDTO.setDescCategoria(categoria.getDescricao());
		}
			
		return cursoDTO;
	}

}
