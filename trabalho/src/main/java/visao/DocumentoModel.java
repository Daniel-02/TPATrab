package visao;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import excecao.DocumentoNaoEncontradoException;
import modelo.Documento;
import servico.DocumentoAppService;
import servico.controle.FabricaDeServico;

public class DocumentoModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	public static final int COLUNA_ID = 0;
	public static final int COLUNA_CABECALHO = 1;
	public static final int COLUNA_CONCLUSAO = 2;
	public static final int COLUNA_DATA = 3;

	private final static int NUMERO_DE_LINHAS_POR_PAGINA = 6;

	private static DocumentoAppService documentoService;

	static {
		documentoService = FabricaDeServico.getServico(DocumentoAppService.class);
	}

	private Map<Integer, Documento> cache;
	private int rowIndexAnterior = 0;
	private Integer qtd;
	private String nomeDocumento;

	public DocumentoModel() {
		this.qtd = null;
		this.cache = new HashMap<Integer, Documento>(NUMERO_DE_LINHAS_POR_PAGINA * 4 / 75 / 100 + 2);
	}

	public void setNomeDocumento(String nomeDocumento) {
		this.nomeDocumento = nomeDocumento;
	}

	public String getColumnName(int c) {
		if (c == COLUNA_ID)
			return "ID";
		if (c == COLUNA_CABECALHO)
			return "Cabeçalho";
		if (c == COLUNA_CONCLUSAO)
			return "Conclusao";
		if (c == COLUNA_DATA)
			return "Data";
		return null;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		if (qtd == null)
//			qtd = (int) documentoService.recuperaQtdPeloNome(nomeDocumento);
			qtd = 0;

		return qtd;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (!cache.containsKey(rowIndex)) {
			if (cache.size() > (NUMERO_DE_LINHAS_POR_PAGINA * 3)) {
				cache.clear();

				if (rowIndex >= rowIndexAnterior) {
					List<Documento> resultados = documentoService.recuperaPeloNome(nomeDocumento,
							rowIndex - (NUMERO_DE_LINHAS_POR_PAGINA - 1), NUMERO_DE_LINHAS_POR_PAGINA * 2);

					for (int j = 0; j < resultados.size(); j++) {
						Documento documento = resultados.get(j);
						cache.put(rowIndex - (NUMERO_DE_LINHAS_POR_PAGINA - 1) + j, documento);
					}
				} else {
					int inicio = rowIndex - NUMERO_DE_LINHAS_POR_PAGINA;
					if (inicio < 0)
						inicio = 0;

					List<Documento> resultados = documentoService.recuperaPeloNome(nomeDocumento, inicio,
							NUMERO_DE_LINHAS_POR_PAGINA * 2);

					for (int j = 0; j < resultados.size(); j++) {
						Documento documento = resultados.get(j);
						cache.put(inicio + j, documento);
					}
				}
			} else {
				if (rowIndex >= rowIndexAnterior) {
					List<Documento> resultados = documentoService.recuperaPeloNome(nomeDocumento, rowIndex,
							NUMERO_DE_LINHAS_POR_PAGINA * 2);

					for (int j = 0; j < resultados.size(); j++) {
						Documento documento = resultados.get(j);
						cache.put(rowIndex + j, documento);
					}
				} else {
					int inicio = rowIndex - (NUMERO_DE_LINHAS_POR_PAGINA * 2 - 1);
					if (inicio < 0)
						inicio = 0;

					List<Documento> resultados = documentoService.recuperaPeloNome(nomeDocumento, inicio,
							NUMERO_DE_LINHAS_POR_PAGINA * 2);

					for (int j = 0; j < resultados.size(); j++) {
						Documento documento = resultados.get(j);
						cache.put(inicio + j, documento);
					}
				}
			}
		}

		rowIndexAnterior = rowIndex;

		Documento documento = cache.get(rowIndex);

		if (columnIndex == COLUNA_ID)
			return documento.getId();
		else if (columnIndex == COLUNA_CABECALHO)
			return documento.getCabecalho();
		else if (columnIndex == COLUNA_CONCLUSAO)
			return documento.getConclusao();
		else if (columnIndex == COLUNA_DATA)
			return documento.getDataCriacao();
		else
			return null;
	}

	// Para que os campos booleanos sejam renderizados como check box.
	// Neste caso, não há campo boleano.
	public Class<?> getColumnClass(int c) {
		Class<?> classe = null;
		if (c == COLUNA_ID)
			classe = Long.class;
		if (c == COLUNA_CABECALHO)
			classe = String.class;
		if (c == COLUNA_CONCLUSAO)
			classe = String.class;
		if (c == COLUNA_DATA)
			classe = Date.class;

		return classe;
	}

	// Para que as células referentes às colunas 1 em diante possam ser editadas
	public boolean isCellEditable(int r, int c) {
		return (c != 0) && (c != 3);
	}

	@Override
	public void setValueAt(Object obj, int r, int c) {
		Documento umDocumento = cache.get(r);

		if (c == COLUNA_CABECALHO)
			umDocumento.setCabecalho((String) obj);
		if (c == COLUNA_CONCLUSAO)
			umDocumento.setConclusao((String) obj);

		try {
			documentoService.altera(umDocumento);
		} catch (DocumentoNaoEncontradoException e) {
			e.printStackTrace();
		}
	}
}
