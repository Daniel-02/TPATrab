import java.util.List;

import corejava.Console;
import excecao.DocumentoNaoEncontradoException;
import modelo.Documento;
import servico.DocumentoAppService;
import servico.controle.FabricaDeServico;
import util.Util;

public class Principal {
	public static void main(String[] args) {
		String cabe�alho;
		String dataCadastro;
		Documento documento;

		System.out.println("\nVai criar o proxy de servi�o");

		DocumentoAppService documentoAppService = FabricaDeServico.getServico(DocumentoAppService.class);

		System.out.println("\nCriou o proxy. Classe de implementa��o = " + documentoAppService.getClass().getName());

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que voc� deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um documento");
			System.out.println("2. Alterar um documento");
			System.out.println("3. Remover um documento");
			System.out.println("4. Listar todos os documentos");
			System.out.println("5. Sair");

			int opcao = Console.readInt('\n' + "Digite um n�mero entre 1 e 5:");

			switch (opcao) {
			case 1: {
				cabe�alho = Console.readLine("Informe o cabe�alho do documento: ");
				dataCadastro = Console.readLine("Informe a data de cadastramento do documento: ");

				documento = new Documento(cabe�alho, Util.strToDate(dataCadastro));

				System.out.println("\nDentro do Principal. Vai chamar documentoAppService.inclui");

				long numero = documentoAppService.inclui(documento);

				System.out.println("\nDentro do Principal. Chamou documentoAppService.inclui");

				System.out.println('\n' + "Documento n�mero " + numero + " inclu�do com sucesso!");

				break;
			}

			case 2: {
				int resposta = Console.readInt('\n' + "Digite o n�mero do documento que voc� deseja alterar: ");

				try {
					documento = documentoAppService.recuperaDocumento(resposta);
				} catch (DocumentoNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out
						.println('\n' + "N�mero = " + documento.getId() + "\n      Cabe�alho = " + documento.getCabecalho());

				System.out.println('\n' + "O que voc� deseja alterar?");
				System.out.println('\n' + "1. Cabe�alho");
				System.out.println('\n' + "1. Conclus�o");

				int opcaoAlteracao = Console.readInt('\n' + "Digite o n�mero de 1 a 2:");

				switch (opcaoAlteracao) {
				case 1:
					String novoCabecalho = Console.readLine("Digite o novo cabe�alho: ");

					documento.setCabecalho(novoCabecalho);

					try {
						System.out.println("\nDentro do Principal. Vai chamar documentoAppService.altera");

						documentoAppService.altera(documento);

						System.out.println("\nDentro do Principal. Chamou documentoAppService.altera");

						System.out.println('\n' + "Altera��o de cabe�alho efetuada com sucesso!");
					} catch (DocumentoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;

				case 2:
					String novaConclusao = Console.readLine("Digite a nova conclus�o: ");

					documento.setConclusao(novaConclusao);

					try {
						System.out.println("\nDentro do Principal. Vai chamar documentoAppService.altera");

						documentoAppService.altera(documento);

						System.out.println("\nDentro do Principal. Chamou documentoAppService.altera");

						System.out.println('\n' + "Altera��o de conclus�o efetuada com sucesso!");
					} catch (DocumentoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;

				default:
					System.out.println('\n' + "Op��o inv�lida!");
				}

				break;
			}

			case 3: {
				int resposta = Console.readInt('\n' + "Digite o n�mero da documento que voc� deseja remover: ");

				try {
					documento = documentoAppService.recuperaDocumento(resposta);
				} catch (DocumentoNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out
						.println('\n' + "N�mero = " + documento.getId() + "\n      Cabe�alho = " + documento.getCabecalho());

				String resp = Console.readLine('\n' + "Confirma a remo��o da documento?");

				if (resp.equals("s")) {
					try {
						System.out.println("\nDentro do Principal. Vai chamar documentoAppService.exclui");

						documentoAppService.exclui(documento.getId());

						System.out.println("\nDentro do Principal. Chamou documentoAppService.exclui");

						System.out.println('\n' + "Documento removida com sucesso!");
					} catch (DocumentoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
				} else {
					System.out.println('\n' + "Documento n�o removida.");
				}

				break;
			}

			case 4: {
				List<Documento> documentos = documentoAppService.recuperaDocumentos();

				for (Documento doc : documentos) {
					System.out.println('\n' + "Id = " + doc.getId() + "\n    Cabecalho = " + doc.getCabecalho()
							+ "  Data Cadastro = " + doc.getDataCriacaoMasc());
				}

				break;
			}

			case 5: {
				continua = false;
				break;
			}

			default:
				System.out.println('\n' + "Op��o inv�lida!");
			}
		}
	}
}
