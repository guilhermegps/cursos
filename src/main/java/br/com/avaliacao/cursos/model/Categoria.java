package br.com.avaliacao.cursos.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.avaliacao.cursos.model.base.EntidadeBase;
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
@Table(name="tb_categoria")
public class Categoria extends EntidadeBase {
	
	@Basic
	@Column(length = 50, nullable = false)
	private String descricao;

}
