package visao;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import excecao.DocumentoNaoEncontradoException;
import modelo.Documento;
import servico.DocumentoAppService;
import util.Util;

public class DocumentoModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	public static final int COLUNA_ID = 0;
	public static final int COLUNA_CABECALHO = 1;
	public static final int COLUNA_CONCLUSAO = 2;
	public static final int COLUNA_DATA = 3;
	public static final int COLUNA_ACAO = 4;


	private final static int NUMERO_DE_LINHAS_POR_PAGINA = 6;

	private static ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");
    
	private static DocumentoAppService documentoService = 
			(DocumentoAppService)fabrica.getBean ("documentoAppService");

	private Map<Integer, Documento> cache;
	private int rowIndexAnterior = 0;
	private Integer qtd;
	private Date data;

	public DocumentoModel() {
		this.qtd = null;
		this.cache = new HashMap<Integer, Documento>(NUMERO_DE_LINHAS_POR_PAGINA * 4 / 75 / 100 + 2);
	}

	public void setDataDocumento(Date data) {
		this.data = data;
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
		if (c == COLUNA_ACAO)
			return "Ação";
		return null;
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		if (qtd == null)
			qtd = (int) documentoService.recuperaQtdPelaData(data);
		return qtd;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (!cache.containsKey(rowIndex)) {
			if (cache.size() > (NUMERO_DE_LINHAS_POR_PAGINA * 3)) {
				cache.clear();

				if (rowIndex >= rowIndexAnterior) {
					List<Documento> resultados = documentoService.recuperaDocumentosPelaData(data,
							rowIndex - (NUMERO_DE_LINHAS_POR_PAGINA - 1), NUMERO_DE_LINHAS_POR_PAGINA * 2);

					for (int j = 0; j < resultados.size(); j++) {
						Documento documento = resultados.get(j);
						cache.put(rowIndex - (NUMERO_DE_LINHAS_POR_PAGINA - 1) + j, documento);
					}
				} else {
					int inicio = rowIndex - NUMERO_DE_LINHAS_POR_PAGINA;
					if (inicio < 0)
						inicio = 0;

					List<Documento> resultados = documentoService.recuperaDocumentosPelaData(data, inicio,
							NUMERO_DE_LINHAS_POR_PAGINA * 2);

					for (int j = 0; j < resultados.size(); j++) {
						Documento documento = resultados.get(j);
						cache.put(inicio + j, documento);
					}
				}
			} else {
				if (rowIndex >= rowIndexAnterior) {
					List<Documento> resultados = documentoService.recuperaDocumentosPelaData(data, rowIndex,
							NUMERO_DE_LINHAS_POR_PAGINA * 2);

					for (int j = 0; j < resultados.size(); j++) {
						Documento documento = resultados.get(j);
						cache.put(rowIndex + j, documento);
					}
				} else {
					int inicio = rowIndex - (NUMERO_DE_LINHAS_POR_PAGINA * 2 - 1);
					if (inicio < 0)
						inicio = 0;

					List<Documento> resultados = documentoService.recuperaDocumentosPelaData(data, inicio,
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
		if (c == COLUNA_ACAO)
			classe = ButtonColumn.class;

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
		if (c == COLUNA_DATA)
			umDocumento.setDataCriacao(((Util.strToDate((String) obj))));

		try {
			documentoService.altera(umDocumento);
		} catch (DocumentoNaoEncontradoException e) {
			e.printStackTrace();
		}
	}
}
