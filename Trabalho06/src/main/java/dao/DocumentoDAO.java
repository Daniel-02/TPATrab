package dao;

import java.util.List;

import excecao.ObjetoNaoEncontradoException;
import modelo.Documento;

public interface DocumentoDAO
{	
	long inclui(Documento documento); 

	void altera(Documento documento)
		throws ObjetoNaoEncontradoException; 
	
	void exclui(long id) 
		throws ObjetoNaoEncontradoException; 

	Documento recuperaDocumento(long numero) 
		throws ObjetoNaoEncontradoException; 
	
	List<Documento> recuperaDocumentos();
}