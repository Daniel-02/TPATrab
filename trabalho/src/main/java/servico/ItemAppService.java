package servico;

import java.util.List;

import excecao.ItemNaoEncontradoException;
import modelo.Item;

public interface ItemAppService
{	
	long inclui(Item item); 
	
	void altera(Item item) throws ItemNaoEncontradoException;
	
	void exclui(Item item) throws ItemNaoEncontradoException;
	
	Item recuperaItem(long numero) throws ItemNaoEncontradoException;
	
	List<Item> recuperaItens(); 
}