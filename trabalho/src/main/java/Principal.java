import java.util.List;

import corejava.Console;
import excecao.DocumentoNaoEncontradoException;
import modelo.Documento;
import servico.DocumentoAppService;
import servico.controle.FabricaDeServico;
import util.Util;

public class Principal {
	public static void main(String[] args) {
		String cabeçalho;
		String dataCadastro;
		Documento documento;

		System.out.println("\nVai criar o proxy de serviço");

		DocumentoAppService documentoAppService = FabricaDeServico.getServico(DocumentoAppService.class);
		
		List<Documento> teste = documentoAppService.recuperaDocumentosPelaData(Util.strToDate("10/10/1000"), 0, 10);
		for (Documento documento2 : teste) {
			System.out.println(documento2.getCabecalho());
		}
	}
}
