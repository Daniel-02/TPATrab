package excecao;

import anotacao.ExcecaoDeAplicacao;

@ExcecaoDeAplicacao
public class LanceNaoEncontradoException extends Exception
{	
	private final static long serialVersionUID = 1;

	public LanceNaoEncontradoException(String msg)
	{	super(msg);
	}
}	