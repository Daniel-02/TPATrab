package excecao;

import anotacao.ConstraintViolada;

@ConstraintViolada(nome="ITEM_DOCUMENTO_FK")
public class ProdutoComLancesException extends RuntimeException
{
	private final static long serialVersionUID = 1;

	public ProdutoComLancesException()
	{	super();
	}

	public ProdutoComLancesException(String msg)
	{	super(msg);
	}
}
