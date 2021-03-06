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
		System.out.println("\nDentro de DocumentoAppServiceImpl. Vai chamar o m�todo inclui() de DocumentoDAOImpl.");

		long numero = documentoDAO.inclui(documento).getId();

		System.out.println("\nDentro de DocumentoAppServiceImpl. Chamou o m�todo inclui() de DocumentoDAOImpl.");

		return numero;
	}

	@Transactional
	public void altera(Documento documento) throws DocumentoNaoEncontradoException {
		System.out.println("\nVai chamar o m�todo altera() de DocumentoDAOImpl.");

		documentoDAO.altera(documento);

		System.out.println("\nChamou o m�todo altera() de DocumentoDAOImpl.");
	}

	@Transactional
	public void exclui(Documento documento) throws DocumentoNaoEncontradoException {
		System.out.println("Vai chamar o m�todo exclui() de DocumentoDAOImpl.");

		Documento docAremover = recuperaDocumento(documento.getId());
		documentoDAO.exclui(docAremover);

		System.out.println("Chamou o m�todo exclui() de DocumentoDAOImpl.");
	}

	public Documento recuperaDocumento(long numero) throws DocumentoNaoEncontradoException {
		try {
			// System.out.println("Vai chamar o m�todo recuperaUmaDocumento() de
			// DocumentoDAOImpl.");

			Documento documento = documentoDAO.recuperaUmDocumentoEItens(numero);

			// System.out.println("Chamou o m�todo recuperaUmaDocumento() de
			// DocumentoDAOImpl.");

			return documento;
		} catch (ObjetoNaoEncontradoException e) {
			throw new DocumentoNaoEncontradoException("Documento n�o encontrado");
		}
	}

	public List<Documento> recuperaDocumentos() {
		// System.out.println("Vai chamar o m�todo recuperaDocumentos() de
		// DocumentoDAOImpl.");

		List<Documento> contas = documentoDAO.recuperaListaDeDocumentos();

		// System.out.println("Chamou o m�todo recuperaDocumentos() de
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