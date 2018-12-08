package excecao;

import anotacao.ConstraintViolada;

@ConstraintViolada(nome="PRODUTO_NOME_UN")
public class ProdutoJaCadastradoException extends RuntimeException
{
	private final static long serialVersionUID = 1;

	public ProdutoJaCadastradoException()
	{	super();
	}

	public ProdutoJaCadastradoException(String msg)
	{	super(msg);
	}
}
