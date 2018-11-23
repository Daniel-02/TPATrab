package modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import util.Util;

@Entity
@Table(name = "documento")
// @SequenceGenerator(name="SEQUENCIA",
// sequenceName="SEQ_PRODUTO",
// allocationSize=1)
//@NamedQueries({
//		@NamedQuery(name = "Documento.recuperaUmDocumentoEItens", query = "select d from Documento d left outer join fetch d.itens  where d.id = ?1"),
//		@NamedQuery(name = "Documento.recuperaListaDeDocumentos", query = "select d from Documento d order by d.id"),
//		@NamedQuery(name = "Documento.recuperaListaDeDocumentosEItens", query = "select distinct d from Documento d left outer join fetch d.itens order by d.id asc"),
//		@NamedQuery(name = "Documento.recuperaConjuntoDeDocumentosEItens", query = "select  d from Documento d left outer join fetch d.itens order by d.id asc"),
//		@NamedQuery(name = "Documento.recuperaPrimeiroDocumento", query = "select d from Documento d order by d.dataCriacao asc") })

@NamedQueries(
		{	@NamedQuery
			(	name = "Produto.recuperaUmProdutoELances",
				query = "select p from Produto p left outer join fetch p.lances where p.id = ?1"
			),
			@NamedQuery
			(	name = "Produto.recuperaListaDeProdutos",
				query = "select p from roduto p order by p.id"
			),
			@NamedQuery
			(	name = "Produto.recuperaListaDeProdutosELances",
				query = "select distinct p from Produto p left outer join fetch p.lances order by p.id asc"
			),
			@NamedQuery
			(	name = "Produto.recuperaConjuntoDeProdutosELances",
				query = "select p from Produto p left outer join fetch p.lances order by p.id asc"
			),
			@NamedQuery
			(	name = "Produto.recuperaPrimeiroProduto",
				query = "select p from Produto p order by p.nome asc"
			)
		})

public class Documento {
	private Long id;
	private String cabecalho;
	private String conclusao;
	private Date dataCriacao;
	private List<Item> itens = new ArrayList<Item>();

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

	// ********* Métodos para Associações *********

	@OneToMany(mappedBy = "documento")
	@OrderBy
	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
}
