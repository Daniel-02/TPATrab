package excecao;


public class ProdutoJaCadastradoException extends RuntimeException
{
	private final static long serialVersionUID = 1;

	public ProdutoJaCadastradoException(String msg)
	{	super(msg);
	}
}
