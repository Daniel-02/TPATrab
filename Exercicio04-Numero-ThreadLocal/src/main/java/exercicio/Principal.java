package exercicio;

public class Principal {
	public static void main(String[] args) {
		EstendeThread umaThread = new EstendeThread("Thread numero 1");
		umaThread.start();
		EstendeThread outraThread = new EstendeThread("Thread numero 2");
		outraThread.start();
		System.out.println("Fim da execução do programa principal");
	}
}