package br.com.avaliacao.cursos.model.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;


/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Getter
@Setter
@MappedSuperclass
public class EntidadeBase implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Basic
	@Column(nullable = false)
	protected Boolean ativo = true;

	@Basic
	@Column(name = "codigo", insertable = false, updatable = false, unique = true)
	protected Integer codigo;

	@Basic
	@Column(name = "dt_registro", insertable = false, updatable = false)
	protected Date dtRegistro;

}
