package servico.impl;

import java.util.List;

import anotacao.Autowired;
import anotacao.RollbackFor;
import anotacao.Transactional;
import dao.DocumentoDAO;
import excecao.ClienteNaoEncontradoException;
import excecao.DocumentoNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Documento;
import servico.DocumentoAppService;

public class DocumentoAppServiceImpl implements DocumentoAppService
{	
//	private static DocumentoDAO documentoDAO = FabricaDeDAOs.getDAO(DocumentoDAO.class);
	@Autowired
	public static DocumentoDAO documentoDAO;

	@Transactional
	public long inclui(Documento documento) 
	{	
		System.out.println("\nDentro de DocumentoAppServiceImpl. Vai chamar o método inclui() de DocumentoDAOImpl.");
		
		long numero = documentoDAO.inclui(documento);
		
		System.out.println("\nDentro de DocumentoAppServiceImpl. Chamou o método inclui() de DocumentoDAOImpl.");
		
		return numero;
	}

	
	@Transactional
	@RollbackFor(nomes={DocumentoNaoEncontradoException.class, 
			            ClienteNaoEncontradoException.class})
	public void altera(Documento documento)
		throws DocumentoNaoEncontradoException
	{	
		try
		{	
			System.out.println("\nVai chamar o método altera() de DocumentoDAOImpl.");

			documentoDAO.altera(documento);
			
			System.out.println("\nChamou o método altera() de DocumentoDAOImpl.");
		} 
		catch(ObjetoNaoEncontradoException e)
		{	
			throw new DocumentoNaoEncontradoException("Documento não encontrada");
		}
	}
	
	@Transactional
	public void exclui(long numero) 
		throws DocumentoNaoEncontradoException
	{	
		try
		{	
			System.out.println("Vai chamar o método exclui() de DocumentoDAOImpl.");

			documentoDAO.exclui(numero);

			System.out.println("Chamou o método exclui() de DocumentoDAOImpl.");
		} 
		catch(ObjetoNaoEncontradoException e)
		{	
		    throw new DocumentoNaoEncontradoException("Documento não encontrada");
		}
	}

	public Documento recuperaDocumento(long numero) 
		throws DocumentoNaoEncontradoException
	{	
		try
		{	
			// System.out.println("Vai chamar o método recuperaUmaDocumento() de DocumentoDAOImpl.");

			Documento conta = documentoDAO.recuperaDocumento(numero);
			
			// System.out.println("Chamou o método recuperaUmaDocumento() de DocumentoDAOImpl.");
			
			return conta;
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new DocumentoNaoEncontradoException("Documento não encontrada");
		}
	}

	public List<Documento> recuperaDocumentos() 
	{	
		// System.out.println("Vai chamar o método recuperaDocumentos() de DocumentoDAOImpl.");

		List<Documento> contas = documentoDAO.recuperaDocumentos();
		
		// System.out.println("Chamou o método recuperaDocumentos() de DocumentoDAOImpl.");

		return contas;
	}
}