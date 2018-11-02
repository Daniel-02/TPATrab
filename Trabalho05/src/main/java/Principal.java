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

		System.out.println("\nCriou o proxy. Classe de implementação = " + documentoAppService.getClass().getName());

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um documento");
			System.out.println("2. Alterar um documento");
			System.out.println("3. Remover um documento");
			System.out.println("4. Listar todos os documentos");
			System.out.println("5. Sair");

			int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");

			switch (opcao) {
			case 1: {
				cabeçalho = Console.readLine("Informe o cabeçalho do documento: ");
				dataCadastro = Console.readLine("Informe a data de cadastramento do documento: ");

				documento = new Documento(cabeçalho, Util.strToDate(dataCadastro));

				System.out.println("\nDentro do Principal. Vai chamar documentoAppService.inclui");

				long numero = documentoAppService.inclui(documento);

				System.out.println("\nDentro do Principal. Chamou documentoAppService.inclui");

				System.out.println('\n' + "Documento número " + numero + " incluído com sucesso!");

				break;
			}

			case 2: {
				int resposta = Console.readInt('\n' + "Digite o número do documento que você deseja alterar: ");

				try {
					documento = documentoAppService.recuperaDocumento(resposta);
				} catch (DocumentoNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out
						.println('\n' + "Número = " + documento.getId() + "\n      Cabeçalho = " + documento.getCabecalho());

				System.out.println('\n' + "O que você deseja alterar?");
				System.out.println('\n' + "1. Cabeçalho");
				System.out.println('\n' + "1. Conclusão");

				int opcaoAlteracao = Console.readInt('\n' + "Digite o número de 1 a 2:");

				switch (opcaoAlteracao) {
				case 1:
					String novoCabecalho = Console.readLine("Digite o novo cabeçalho: ");

					documento.setCabecalho(novoCabecalho);

					try {
						System.out.println("\nDentro do Principal. Vai chamar documentoAppService.altera");

						documentoAppService.altera(documento);

						System.out.println("\nDentro do Principal. Chamou documentoAppService.altera");

						System.out.println('\n' + "Alteração de cabeçalho efetuada com sucesso!");
					} catch (DocumentoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;

				case 2:
					String novaConclusao = Console.readLine("Digite a nova conclusão: ");

					documento.setConclusao(novaConclusao);

					try {
						System.out.println("\nDentro do Principal. Vai chamar documentoAppService.altera");

						documentoAppService.altera(documento);

						System.out.println("\nDentro do Principal. Chamou documentoAppService.altera");

						System.out.println('\n' + "Alteração de conclusão efetuada com sucesso!");
					} catch (DocumentoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;

				default:
					System.out.println('\n' + "Opção inválida!");
				}

				break;
			}

			case 3: {
				int resposta = Console.readInt('\n' + "Digite o número da documento que você deseja remover: ");

				try {
					documento = documentoAppService.recuperaDocumento(resposta);
				} catch (DocumentoNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out
						.println('\n' + "Número = " + documento.getId() + "\n      Cabeçalho = " + documento.getCabecalho());

				String resp = Console.readLine('\n' + "Confirma a remoção da documento?");

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
					System.out.println('\n' + "Documento não removida.");
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
				System.out.println('\n' + "Opção inválida!");
			}
		}
	}
}
