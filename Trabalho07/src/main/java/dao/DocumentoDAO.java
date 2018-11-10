package dao;

import java.util.List;
import java.util.Set;

import anotacao.RecuperaConjunto;
import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;
import excecao.ObjetoNaoEncontradoException;
import modelo.Documento;

public interface DocumentoDAO extends DaoGenerico<Documento, Long> {
//	long inclui(Documento documento); 
//
//	void altera(Documento documento)
//		throws ObjetoNaoEncontradoException; 
//	
//	void exclui(long id) 
//		throws ObjetoNaoEncontradoException; 
//
//	Documento recuperaDocumento(long numero) 
//		throws ObjetoNaoEncontradoException; 
//	
//	List<Documento> recuperaDocumentos();
	/* ****** Métodos Genéricos ******* */

	@RecuperaObjeto
	Documento recuperaUmDocumento(long numero) throws ObjetoNaoEncontradoException;

	@RecuperaLista
	List<Documento> recuperaListaDeDocumentos();

	@RecuperaUltimoOuPrimeiro
	Documento recuperaPrimeiroDocumento() throws ObjetoNaoEncontradoException;

//	@RecuperaLista
//	List<Documento> recuperaListaDeDocumentosELances();

	@RecuperaConjunto
	Set<Documento> recuperaConjuntoDeDocumentos();

	/* ****** Métodos não Genéricos ******* */

	// Um método definido aqui, que não seja anotado, deverá ser
	// implementado como final em ProdutoDAOImpl.
}