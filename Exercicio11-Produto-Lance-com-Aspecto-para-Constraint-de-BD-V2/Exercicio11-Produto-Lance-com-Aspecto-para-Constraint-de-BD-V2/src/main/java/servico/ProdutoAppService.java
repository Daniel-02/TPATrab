package servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ProdutoDAO;
import excecao.ObjetoNaoEncontradoException;
import excecao.ProdutoNaoEncontradoException;
import modelo.Produto;

@Service
public class ProdutoAppService
{	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Transactional
	public long inclui(Produto umProduto) 
	{	return produtoDAO.inclui(umProduto);
	}

	@Transactional
	public void altera(Produto umProduto)
		throws ProdutoNaoEncontradoException
	{	try
		{	produtoDAO.altera(umProduto);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new ProdutoNaoEncontradoException("Produto não encontrado");
		}
	}

	@Transactional
	public void exclui(Produto umProduto) 
		throws ProdutoNaoEncontradoException
	{	try
		{	
//			Produto produto = produtoDAO.recuperaUmProdutoELances(umProduto.getId());
//	
//			if(produto.getLances().size() > 0)
//			{	throw new ProdutoNaoEncontradoException("Este produto possui lances e não pode ser removido");
//			}
	
			produtoDAO.exclui(umProduto.getId());
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new ProdutoNaoEncontradoException("Produto não encontrado");
		}
	}

	public Produto recuperaUmProduto(long numero) 
		throws ProdutoNaoEncontradoException
	{	try
		{	return produtoDAO.recuperaUmProduto(numero);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new ProdutoNaoEncontradoException("Produto não encontrado");
		}
	}

	public Produto recuperaUmProdutoELances(long numero) 
		throws ProdutoNaoEncontradoException
	{	try
		{	return produtoDAO.recuperaUmProdutoELances(numero);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new ProdutoNaoEncontradoException("Produto não encontrado");
		}
	}

	public List<Produto> recuperaProdutosELances()
	{	return produtoDAO.recuperaProdutosELances();
	}
}