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
	/* ****** M�todos Gen�ricos ******* */

	@RecuperaObjeto
	Documento recuperaUmDocumentoEItens(long numero) throws ObjetoNaoEncontradoException;

	@RecuperaLista
	List<Documento> recuperaListaDeDocumentos();

	@RecuperaUltimoOuPrimeiro
	Documento recuperaPrimeiroDocumento() throws ObjetoNaoEncontradoException;

	@RecuperaLista
	List<Documento> recuperaListaDeDocumentosEItens();

	@RecuperaConjunto
	Set<Documento> recuperaConjuntoDeDocumentosEItens();

	/* ****** M�todos n�o Gen�ricos ******* */

	// Um m�todo definido aqui, que n�o seja anotado, dever� ser
	// implementado como final em ProdutoDAOImpl.
}