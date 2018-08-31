package exercicio;

public class EstadoDeObjetoObsoletoException extends RuntimeException {
	private final static long serialVersionUID = 1;

	public EstadoDeObjetoObsoletoException() {
	}
	public EstadoDeObjetoObsoletoException(String msg) {
		super(msg);
	}
}