package excecao;

import anotacao.ConstraintViolada;

@ConstraintViolada(nome="item_documento_fk")
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
