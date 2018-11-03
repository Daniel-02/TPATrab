package modelo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import util.Util;

@Entity
@Table(name = "documento")
//@SequenceGenerator(name="SEQUENCIA", 
//		           sequenceName="SEQ_PRODUTO",
//		           allocationSize=1)

public class Documento {
	private Long id;
	private String cabecalho;
	private String conclusao;
	private Date dataCriacao;

	// ********* Construtores *********

	public Documento() {
	}

	public Documento(String cabecalho, Date dataCriacao) {
		this.cabecalho = cabecalho;
		this.dataCriacao = dataCriacao;
	}

	// ********* Métodos do Tipo Get *********

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public String getCabecalho() {
		return cabecalho;
	}

	public String getconclusao() {
		return conclusao;
	}

	public Date getdataCriacao() {
		return dataCriacao;
	}

	@Transient
	public String getDataCriacaoMasc() {
		return Util.dateToStr(dataCriacao);
	}

	// ********* Métodos do Tipo Set *********

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}

	public void setConclusao(String conclusao) {
		this.conclusao = conclusao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
}
