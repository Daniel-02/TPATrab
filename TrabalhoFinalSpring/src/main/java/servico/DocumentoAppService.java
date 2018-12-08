package servico;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.DocumentoDAO;
import excecao.DocumentoNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Documento;

public class DocumentoAppService {
//	private static DocumentoDAO documentoDAO = FabricaDeDAOs.getDAO(DocumentoDAO.class);
	private DocumentoDAO documentoDAO = null;

	@Autowired
	public void setdocumentoDAO(DocumentoDAO documentoDAO) {
		this.documentoDAO = documentoDAO;
	}

	@Transactional
	public long inclui(Documento documento) {
		System.out.println("\nDentro de DocumentoAppServiceImpl. Vai chamar o método inclui() de DocumentoDAOImpl.");

		long numero = documentoDAO.inclui(documento).getId();

		System.out.println("\nDentro de DocumentoAppServiceImpl. Chamou o método inclui() de DocumentoDAOImpl.");

		return numero;
	}

	@Transactional
	public void altera(Documento documento) throws DocumentoNaoEncontradoException {
		System.out.println("\nVai chamar o método altera() de DocumentoDAOImpl.");

		documentoDAO.altera(documento);

		System.out.println("\nChamou o método altera() de DocumentoDAOImpl.");
	}

	@Transactional
	public void exclui(Documento documento) throws DocumentoNaoEncontradoException {
		System.out.println("Vai chamar o método exclui() de DocumentoDAOImpl.");

		documentoDAO.exclui(documento);

		System.out.println("Chamou o método exclui() de DocumentoDAOImpl.");
	}

	public Documento recuperaDocumento(long numero) throws DocumentoNaoEncontradoException {
		try {
			// System.out.println("Vai chamar o método recuperaUmaDocumento() de
			// DocumentoDAOImpl.");

			Documento documento = documentoDAO.recuperaUmDocumentoEItens(numero);

			// System.out.println("Chamou o método recuperaUmaDocumento() de
			// DocumentoDAOImpl.");

			return documento;
		} catch (ObjetoNaoEncontradoException e) {
			throw new DocumentoNaoEncontradoException("Documento não encontrado");
		}
	}

	public List<Documento> recuperaDocumentos() {
		// System.out.println("Vai chamar o método recuperaDocumentos() de
		// DocumentoDAOImpl.");

		List<Documento> contas = documentoDAO.recuperaListaDeDocumentos();

		// System.out.println("Chamou o método recuperaDocumentos() de
		// DocumentoDAOImpl.");

		return contas;
	}

	public int recuperaQtdPelaData(Date data) {
		int qtd = documentoDAO.recuperaQtdPelaData(data);
		return qtd;
	}

	public List<Documento> recuperaDocumentosPelaData(Date data, int deslocamento, int linhasPorPagina) {

		List<Documento> contas = documentoDAO.recuperaListaDeDocumentosPelaData(data, deslocamento, linhasPorPagina);

		return contas;
	}

}