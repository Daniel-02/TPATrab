package servico;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import modelo.Lance;
import modelo.Produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import util.Util;
import dao.LanceDAO;
import dao.ProdutoDAO;
import excecao.DataDeLanceInvalidaException;
import excecao.LanceNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import excecao.ProdutoNaoEncontradoException;
import excecao.ValorDeLanceInvalidoException;

// @Service
public class LanceAppService
{	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private LanceDAO lanceDAO;
	
	@Transactional
	public long inclui(Lance umLance) 
		throws ProdutoNaoEncontradoException, 
		       ValorDeLanceInvalidoException, 
		       DataDeLanceInvalidaException 
	{	
		// A execu��o do m�todo  recuperaUmProdutoComLock(id)  abaixo
		// impede  que  dois lances  sejam  cadastrados em  paralelo.
		// Como este  m�todo p�e  um lock em  Produto, a inser��o  de 
		// dois  lances  acontece  sempre em  s�rie, i. �, obedecendo
		// a uma fila. Isto impede que o valor do  segundo lance seja
		// inferior ao valor do primeiro ou que se tente cadastrar um
		// lance para um produto que tenha sido removido.
		
		Produto umProduto = umLance.getProduto();
		
		try
		{	produtoDAO.recuperaUmProdutoComLock(umProduto.getId());
		}
		catch(ObjetoNaoEncontradoException e)
		{	
			throw new ProdutoNaoEncontradoException("Produto n�o encontado");
		}

		Lance ultimoLance; 
		try
		{	ultimoLance = lanceDAO.recuperaUltimoLance(umProduto);
		}
		catch(ObjetoNaoEncontradoException e)
		{	ultimoLance = null;	
		}

		double valorUltimoLance;
		Calendar dataUltimoLance;  
		
		if (ultimoLance == null)
		{	valorUltimoLance = umProduto.getLanceMinimo() - 1;
			dataUltimoLance  = umProduto.getDataCadastro();
		}
		else
		{	valorUltimoLance = ultimoLance.getValor();
			dataUltimoLance  = ultimoLance.getDataCriacao();
		}
		
		if(umLance.getValor() <= valorUltimoLance)
		{	throw new ValorDeLanceInvalidoException("O valor do lance tem que ser superior a " + valorUltimoLance);
		}
		
		if(umLance.getDataCriacao().before(dataUltimoLance))
		{	throw new DataDeLanceInvalidaException("A data de emiss�o do lance n�o pode ser anterior a " 
				+ Util.calendarToStr(dataUltimoLance));
		}

		GregorianCalendar hoje = new GregorianCalendar();
	
		if(umLance.getDataCriacao().after(hoje))
		{	throw new DataDeLanceInvalidaException("A data de emiss�o do lance n�o pode ser posterior � data de hoje: " 
				+ Util.calendarToStr(hoje));
		}
		
		long numero = lanceDAO.inclui(umLance);

		return numero;
	}
	
	@Transactional
	public void exclui(Lance umLance) 
		throws LanceNaoEncontradoException 
	{	
		try
		{	
			lanceDAO.exclui(umLance);	
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new LanceNaoEncontradoException("Lance n�o encontrado.");
		}
	}

	public Lance recuperaUmLance(long numero) 
		throws LanceNaoEncontradoException
	{	try
		{	return lanceDAO.recuperaUmLance(numero);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new LanceNaoEncontradoException("Lance n�o encontrado");
		}
	}

	public List<Lance> recuperaLances()
	{	return lanceDAO.recuperaLances();
	}
}