package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import modelo.Lance;
import modelo.Produto;
import dao.LanceDAO;
import excecao.ObjetoNaoEncontradoException;

@Repository
public class LanceDAOImpl implements LanceDAO
{	
	@PersistenceContext
	private EntityManager em;

    public long inclui(Lance umLance) 
	{	em.persist(umLance);
		return umLance.getId();
	}

    public void exclui(Lance umLance) 
		throws ObjetoNaoEncontradoException 
	{	
    	Lance lance = em.find(Lance.class, new Long(umLance.getId()), LockModeType.PESSIMISTIC_WRITE);
    	
    	if (lance == null)
    	{	throw new ObjetoNaoEncontradoException();	
		}
    	
		em.remove(lance);
	}

	public Lance recuperaUmLance(long numero) 
		throws ObjetoNaoEncontradoException 
	{	
		Lance umLance = (Lance)em.find(Lance.class, new Long(numero));

		if(umLance == null)
		{	throw new ObjetoNaoEncontradoException();
		}

		return umLance;
	}

	@SuppressWarnings("unchecked")
	public List<Lance> recuperaLances()
	{	
		return em.createQuery("select l from Lance l order by l.id")
				 .getResultList();
	}

	@SuppressWarnings("unchecked")
	public Lance recuperaUltimoLance(Produto produto) 
		throws ObjetoNaoEncontradoException
	{	 
		String busca = "select l from Lance l " +
				       "where l.produto.id = :idProduto " +
				       "order by l.id desc"; 		

		List<Lance> lances = em.createQuery(busca)
							   .setParameter("idProduto", produto.getId())
							   .getResultList();

		if (lances.isEmpty()) 
			throw new ObjetoNaoEncontradoException();
		else 
			return lances.get(0);
	}
}
