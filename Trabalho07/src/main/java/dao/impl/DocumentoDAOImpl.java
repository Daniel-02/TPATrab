package dao.impl;

import dao.DocumentoDAO;
import modelo.Documento;

public abstract class DocumentoDAOImpl extends JPADaoGenerico<Documento, Long> implements DocumentoDAO {
	public DocumentoDAOImpl() {
		super(Documento.class);
	}
}
