package excecao;

public class DocumentoNaoEncontradoException extends Exception
{	
	private final static long serialVersionUID = 1;
	
	public DocumentoNaoEncontradoException()
	{	super();
	}

	public DocumentoNaoEncontradoException(String msg)
	{	super(msg);
	}
}	