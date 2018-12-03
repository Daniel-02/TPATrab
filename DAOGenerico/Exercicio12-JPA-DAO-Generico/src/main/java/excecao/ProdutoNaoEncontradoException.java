package excecao;

import anotacao.ExcecaoDeAplicacao;

@ExcecaoDeAplicacao
public class ProdutoNaoEncontradoException extends Exception
{	
	private final static long serialVersionUID = 1;
	
	public ProdutoNaoEncontradoException(String msg)
	{	super(msg);
	}
}	