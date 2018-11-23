package servico;

import java.util.List;

import excecao.DocumentoNaoEncontradoException;
import modelo.Documento;

public interface DocumentoAppService
{	
	long inclui(Documento documento); 
	
	void altera(Documento documento) throws DocumentoNaoEncontradoException;
	
	void exclui(Documento documento) throws DocumentoNaoEncontradoException;
	
	Documento recuperaDocumento(long numero) throws DocumentoNaoEncontradoException;
	
	List<Documento> recuperaDocumentos(); 
}