package br.com.avaliacao.cursos.model.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
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
	@GeneratedValue
    @Column(name = "id")
	protected Long id;
	
	@Basic
	@Column(nullable = false)
	protected boolean ativo;

	@Basic
	@Column(name = "codigo", nullable = false, insertable = false, columnDefinition="serial", unique = true)
	protected Long codigo;

	@Basic
	@Column(name = "dt_registro", nullable = false)
	protected Date dtRegistro;

}
