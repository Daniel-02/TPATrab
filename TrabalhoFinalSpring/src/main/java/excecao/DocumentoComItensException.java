package excecao;

import anotacao.ConstraintViolada;

@ConstraintViolada(nome="LANCE_PRODUTO_FK")
public class DocumentoComItensException extends RuntimeException
{
	private final static long serialVersionUID = 1;

	public DocumentoComItensException()
	{	super();
	}

	public DocumentoComItensException(String msg)
	{	super(msg);
	}
}
