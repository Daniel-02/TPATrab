package service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import dao.LanceDAO;
import dao.ProdutoDAO;
import excecao.DataDeLanceInvalidaException;
import excecao.LanceNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import excecao.ProdutoNaoEncontradoException;
import excecao.ValorDeLanceInvalidoException;
import modelo.Lance;
import modelo.Produto;
import util.Util;

public class LanceAppService
{	
	private ProdutoDAO produtoDAO = null;
	private LanceDAO lanceDAO = null;
	
	public void setProdutoDAO(ProdutoDAO produtoDAO)
	{	this.produtoDAO = produtoDAO;
	}

	public void setLanceDAO(LanceDAO lanceDAO)
	{	this.lanceDAO = lanceDAO;
	}

	@Transactional
	public long inclui(Lance umLance) throws ProdutoNaoEncontradoException, ValorDeLanceInvalidoException, DataDeLanceInvalidaException 
	{
		// A  implementação do  método  getPorIdComLock(umProduto.getId()) 
		// impede que dois  lances  sejam  cadastrados em  paralelo, i. é, 
		// os lances  devem ser  cadastrados  obedecendo a uma fila.  Isto
		// impede que o valor do segundo lance seja  inferior  ao valor do
		// primeiro.
	
		Produto umProduto = umLance.getProduto();
		
		try
		{	umProduto = produtoDAO.getPorIdComLock(umProduto.getId());
		}
		catch(ObjetoNaoEncontradoException e)
		{	throw new ProdutoNaoEncontradoException("Produto não encontrado");
		}

		Lance ultimoLance; 
		try
		{	ultimoLance = lanceDAO.recuperaUltimoLance(umProduto);
		}
		catch(ObjetoNaoEncontradoException e)
		{	ultimoLance = null;	
		}
	
		double valorUltimoLance;
		Calendar   dataUltimoLance;
		
		if (ultimoLance == null)
		{	valorUltimoLance = umProduto.getLanceMinimo() - 1;
			dataUltimoLance  = umProduto.getDataCadastro();
		}
		else
		{	valorUltimoLance = ultimoLance.getValor();
			dataUltimoLance  = ultimoLance.getDataCriacao();
		}
		
		if(umLance.getValor() <= valorUltimoLance)
		{	
			throw new ValorDeLanceInvalidoException("O valor do lance tem que ser superior a " + valorUltimoLance);
		}
	
		if(umLance.getDataCriacao().before(dataUltimoLance))
		{	
			throw new DataDeLanceInvalidaException("A data de emissão do lance não pode ser anterior a " 
					+ Util.calendarToStr(dataUltimoLance));
		}
	
		GregorianCalendar hoje = new GregorianCalendar();
		
		if(umLance.getDataCriacao().after(hoje))
		{	
			throw new DataDeLanceInvalidaException("A data de emissão do lance não pode ser posterior à data de hoje: " 
					+ Util.calendarToStr(hoje));
		}
	
		Lance lance = lanceDAO.inclui(umLance);

		return lance.getId();
	}	

	@Transactional
	public void exclui(Lance umLance) 
		throws LanceNaoEncontradoException 
	{	try
		{	umLance = lanceDAO.getPorId(umLance.getId());
			lanceDAO.exclui(umLance);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new LanceNaoEncontradoException("Lance não encontrado.");
		}
	}

	public Lance recuperaUmLance(long numero) 
		throws LanceNaoEncontradoException
	{	try
		{	return lanceDAO.getPorId(numero);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new LanceNaoEncontradoException("Lance não encontrado");
		}
	}

	public List<Lance> recuperaLances()
	{	return lanceDAO.recuperaListaDeLances();
	}
}