package excecao;

import anotacao.ConstraintViolada;

@ConstraintViolada(nome="ITEM_NOME_UN")
public class ItemJaCadastradoException extends RuntimeException
{
	private final static long serialVersionUID = 1;

	public ItemJaCadastradoException()
	{	super();
	}

	public ItemJaCadastradoException(String msg)
	{	super(msg);
	}
}
