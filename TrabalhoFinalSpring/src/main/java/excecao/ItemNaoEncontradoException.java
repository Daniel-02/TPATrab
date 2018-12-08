package excecao;

public class ItemNaoEncontradoException extends Exception
{	
	private final static long serialVersionUID = 1;
	
	public ItemNaoEncontradoException()
	{	super();
	}

	public ItemNaoEncontradoException(String msg)
	{	super(msg);
	}
}	