package modelo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import util.Util;

@NamedQueries(
		{	@NamedQuery
			(	name = "Item.recuperaListaDeItens",
				query = "select i from Item i order by i.id"
			),
			@NamedQuery
			(	name = "Item.recuperaUltimoItem",
				query = "select i from Item i where i.documento = ?1 order by i.id desc"
			),
			@NamedQuery
			(	name = "Lance.recuperaUmItemComDocumento",
				query = "select i from Item i left outer join fetch i.documento where i.id = ?1"
			)
		})

@Entity
@Table(name = "item")

public class Item {
	private Long id;
	private String nome;
	private String conteudo;
	private Documento documento;
	private Date dataCriacao;

	// ********* Construtores *********

	public Item() {
	}

	public Item(String nome, Date dataCriacao) {
		this.nome = nome;
		this.dataCriacao = dataCriacao;
	}

	// ********* Métodos do Tipo Get *********

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getConteudo() {
		return conteudo;
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	// ********* Métodos para Associações *********

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOCUMENTO_ID")
	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

}
