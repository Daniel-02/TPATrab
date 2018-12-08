package dao.impl;

import java.sql.Date;
import java.util.List;

import dao.DocumentoDAO;
import modelo.Documento;

public abstract class DocumentoDAOImpl extends JPADaoGenerico<Documento, Long> implements DocumentoDAO {
	public DocumentoDAOImpl() {
		super(Documento.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public final List<Documento> recuperaListaDeDocumentosPelaData(Date data, int deslocamento, int linhasPorPagina) {
		List<Documento> documentos = em
				.createQuery("select d from Documento d " + "where d.dataCriacao = :data order by d.dataCriacao asc")
				.setParameter("data", data).setFirstResult(deslocamento).setMaxResults(linhasPorPagina).getResultList();
		return documentos;
	}

	@Override
	public final int recuperaQtdPelaData(Date data) {
		int qtd =  (int)(long)(em.createQuery("select count(d) from Documento d " + "where d.dataCriacao = :data")
				.setParameter("data", data).getSingleResult());
		return qtd;
	}

}
