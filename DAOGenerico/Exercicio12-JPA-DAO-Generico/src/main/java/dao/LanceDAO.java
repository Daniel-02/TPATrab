package dao;

import java.util.List;

import modelo.Lance;
import modelo.Produto;
import anotacao.RecuperaLista;
import anotacao.RecuperaUltimoOuPrimeiro;
import excecao.ObjetoNaoEncontradoException;

public interface LanceDAO extends DaoGenerico<Lance, Long>
{	
	@RecuperaLista
	List<Lance> recuperaListaDeLances();
	
	@RecuperaUltimoOuPrimeiro
	Lance recuperaUltimoLance(Produto produto)
		throws ObjetoNaoEncontradoException; 
}
