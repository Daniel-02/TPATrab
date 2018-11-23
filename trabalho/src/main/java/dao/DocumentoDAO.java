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
	/* ****** Métodos Genéricos ******* */

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

	/* ****** Métodos não Genéricos ******* */

	// Um método definido aqui, que não seja anotado, deverá ser
	// implementado como final em ProdutoDAOImpl.
}