package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import anotacao.PersistenceContext;
import dao.DocumentoDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Documento;

public class DocumentoDAOImpl implements DocumentoDAO 
{	
	@PersistenceContext
	public EntityManager em;
	
	public long inclui(Documento documento) 
	{	
		em.persist(documento);
		
		return documento.getId();
	}

	public void altera(Documento documento) 
		throws ObjetoNaoEncontradoException 
	{	
		Documento doc = em.find(Documento.class, documento.getId(), LockModeType.PESSIMISTIC_WRITE);
		
		if(doc == null)
		{	throw new ObjetoNaoEncontradoException();
		}
	
		em.merge(documento);
	}

	public void exclui(long id) 
		throws ObjetoNaoEncontradoException 
	{	
		Documento doc = em.find(Documento.class, id, LockModeType.PESSIMISTIC_WRITE);
		
		if(doc == null)
		{	throw new ObjetoNaoEncontradoException();
		}

		em.remove(doc);
	}

	public Documento recuperaDocumento(long id) 
		throws ObjetoNaoEncontradoException 
	{	
		Documento doc = (Documento)em
			.find(Documento.class, new Long(id));
		
		if (doc == null)
		{	throw new ObjetoNaoEncontradoException();
		}

		return doc;
	}

	public Documento recuperaDocumentoComLock(long id) 
		throws ObjetoNaoEncontradoException 
	{	
		Documento doc = em.find(Documento.class, id, LockModeType.PESSIMISTIC_WRITE);

		if (doc == null)
		{	throw new ObjetoNaoEncontradoException();
		}

		return doc;
	}
	
	@SuppressWarnings("unchecked")
	public List<Documento> recuperaDocumentos()
	{	
		List<Documento> docs = em
			.createQuery("select d from Documento d " +
					     "order by d.id asc")
			.getResultList();

		return docs;
	}
}