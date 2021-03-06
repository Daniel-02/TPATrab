package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import modelo.Produto;

import org.springframework.stereotype.Repository;

import dao.ProdutoDAO;
import excecao.ObjetoNaoEncontradoException;

@Repository
public class ProdutoDAOImpl implements ProdutoDAO
{	
	@PersistenceContext
	private EntityManager em;

    public long inclui(Produto umProduto) 
	{	
    	em.persist(umProduto);
    	em.flush();
		return umProduto.getId();
	}

	public void altera(Produto umProduto) 
		throws ObjetoNaoEncontradoException 
	{	
		Produto produto = em.find(Produto.class, umProduto.getId(), LockModeType.PESSIMISTIC_WRITE);
		
		if(produto == null)
		{	throw new ObjetoNaoEncontradoException();
		}

		em.merge(umProduto);
		em.flush();
	}

    public void exclui(long id) 
		throws ObjetoNaoEncontradoException 
	{	
		Produto produto = em.find(Produto.class, id, LockModeType.PESSIMISTIC_WRITE);
		
		if(produto == null)
		{	throw new ObjetoNaoEncontradoException();
		}
		
		em.remove(produto);
		em.flush();
	}

    public Produto recuperaUmProduto(long numero) 
		throws ObjetoNaoEncontradoException 
	{	
		Produto umProduto = (Produto)em
			.find(Produto.class, new Long(numero));
			
		if (umProduto == null)
		{	throw new ObjetoNaoEncontradoException();
		}

		return umProduto;
	}

	public Produto recuperaUmProdutoComLock(long numero) 
		throws ObjetoNaoEncontradoException 
	{	
		Produto umProduto = (Produto)em
			.find(Produto.class, new Long(numero), LockModeType.PESSIMISTIC_WRITE);

		if (umProduto == null)
		{	throw new ObjetoNaoEncontradoException();
		}

		return umProduto;
	}

	public Produto recuperaUmProdutoELances(long numero) 
		throws ObjetoNaoEncontradoException 
	{	
		/* O  que a  maioria das  pessoas pensam  quando escutam a 
		 * palavra  join no  contexto  de  bancos  de dados SQL  � 
		 * um inner join.  Um  inner  join � o  tipo de join  mais 
		 * simples.
		 *
		 * Por exemplo, para se  recuperar  todos os  produtos que 
		 * possuem lances, � preciso utilizar um inner join. Neste 
		 * caso apenas produtos que possuem lances s�o recuperados. 
		 * Mas se desejarmos recuperar os produtos e valores nulos
		 * para os  dados dos  lances  quando o  produto n�o tiver 
		 * lances,  neste caso  utilizaremos um  left  outer join. 
		 * (estilo ANSI).
		 *
		 * Se fizermos a  jun��o de  duas tabelas PRODUTO e LANCE, 
		 * utilizando um inner join obteremos  todos os produtos e 
		 * seus lances na tabela resultante.  No caso de um  "left 
		 * outer join", cada  linha da  tabela a  esquerda (left - 
		 * tabela PRODUTO) que nunca satisfaz a condi��o de jun��o
		 * tamb�m  �  inclu�da  no  resultado  com  valores  nulos 
		 * retornados para todas as colunas da tabela LANCE.
		 * 
		 * Um "right outer join" recuperaria  todos os lances  com 
		 * um valor nulo para o produto se o lance n�o tem rela��o
		 * com nenhum produto.
		 * 
		 * A condi��o de  join deve ser  especificada na  cl�usula 
		 * "on" para  uma  jun��o no  estilo "ANSI" ou na cl�usula 
		 * "where" para uma jun��o no estilo "theta". 
		 * 
		 * Exemplo: P.ID = L.PRODUTO_ID.
		 *
		 * Left Outer Join no Oracle:
		 *
		 * SELECT P.ID, P.NOME, L.ID, L.VALOR
		 * FROM PRODUTO P, LANCE L
		 * WHERE P.ID = L.PRODUTO_ID(+)
		 * ORDER BY P.ID, L.VALOR;
		 */

		try
		{	String busca = "select p from Produto p " +
			               "left outer join fetch p.lances l " +
			               "where p.id = :id";

			Produto umProduto =
				(Produto) em.createQuery(busca)
						    .setParameter("id", numero)
						    .getSingleResult();

			// A busca retorna um �nico produto (SingleResult()).

			/* 	Em fun��o do m�todo getSingleResult() ser� propagada
			 *  a exce��o NoResultException caso nenhum produto seja
			 *  encontrado.
			 */

			return umProduto;
		} 
		catch(NoResultException e)
		{	throw new ObjetoNaoEncontradoException();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Produto> recuperaProdutosELances()
	{	
		List<Produto> produtos = em
			.createQuery("select distinct p from Produto p " +
					     "left outer join fetch p.lances l " +
					     "order by p.id asc")
			.getResultList();

		return produtos;
	}
}