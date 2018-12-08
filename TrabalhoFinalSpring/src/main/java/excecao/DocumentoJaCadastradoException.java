package excecao;

import anotacao.ConstraintViolada;

@ConstraintViolada(nome="DOCUMENTO_NOME_UN")
public class DocumentoJaCadastradoException extends RuntimeException
{
	private final static long serialVersionUID = 1;

	public DocumentoJaCadastradoException()
	{	super();
	}

	public DocumentoJaCadastradoException(String msg)
	{	super(msg);
	}
}
