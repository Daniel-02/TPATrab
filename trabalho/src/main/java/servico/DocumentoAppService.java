package servico;

import java.sql.Date;
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
	
	List<Documento> recuperaDocumentosPelaData(Date data, int deslocamento, int linhasPorPagina);
}