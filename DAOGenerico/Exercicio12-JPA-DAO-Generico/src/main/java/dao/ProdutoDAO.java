package dao;

import java.util.List;
import java.util.Set;

import modelo.Produto;
import anotacao.RecuperaConjunto;
import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;
import excecao.ObjetoNaoEncontradoException;

public interface ProdutoDAO extends DaoGenerico<Produto, Long>
{   
	/* ****** Métodos Genéricos ******* */

	@RecuperaObjeto
	Produto recuperaUmProdutoELances(long numero) 
		throws ObjetoNaoEncontradoException;

	@RecuperaLista
	List<Produto> recuperaListaDeProdutos();
	
	@RecuperaUltimoOuPrimeiro
	Produto recuperaPrimeiroProduto()
		throws ObjetoNaoEncontradoException;

	@RecuperaLista
	List<Produto> recuperaListaDeProdutosELances();

	@RecuperaConjunto
	Set<Produto> recuperaConjuntoDeProdutosELances();
	
	/* ****** Métodos não Genéricos ******* */

	// Um método definido aqui, que não seja anotado, deverá ser
	// implementado como final em ProdutoDAOImpl.
}
