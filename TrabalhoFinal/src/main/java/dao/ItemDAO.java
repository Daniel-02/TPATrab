package dao;

import java.util.List;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;
import excecao.ObjetoNaoEncontradoException;
import modelo.Documento;
import modelo.Item;

public interface ItemDAO extends DaoGenerico<Item, Long> {
	/* ****** M�todos Gen�ricos ******* */

	@RecuperaLista
	List<Item> recuperaListaDeItens();

	@RecuperaUltimoOuPrimeiro
	Item recuperaUltimoItem(Documento documento) throws ObjetoNaoEncontradoException;

	@RecuperaObjeto
	Item recuperaUmItemComDocumento(long numero) throws ObjetoNaoEncontradoException;
}