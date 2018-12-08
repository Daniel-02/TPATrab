package dao;

import java.util.List;

import modelo.Lance;
import modelo.Produto;
import excecao.ObjetoNaoEncontradoException;

public interface LanceDAO
{	
	public long inclui(Lance umLance);

	public void exclui(Lance umLance) 
		throws ObjetoNaoEncontradoException; 
	
	public Lance recuperaUmLance(long numero) 
		throws ObjetoNaoEncontradoException; 
	
	public List<Lance> recuperaLances();
	
	public Lance recuperaUltimoLance(Produto produto)
		throws ObjetoNaoEncontradoException; 
}
