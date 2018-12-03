package excecao;

import anotacao.ExcecaoDeAplicacao;

@ExcecaoDeAplicacao
public class ValorDeLanceInvalidoException extends Exception
{	
	private final static long serialVersionUID = 1;

	public ValorDeLanceInvalidoException(String msg)
	{	super(msg);
	}
}	