package excecao;


public class ProdutoComLancesException extends RuntimeException
{
	private final static long serialVersionUID = 1;

	public ProdutoComLancesException(String msg)
	{	super(msg);
	}
}
