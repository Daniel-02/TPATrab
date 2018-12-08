package visao;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import excecao.DocumentoNaoEncontradoException;
import modelo.Documento;
import servico.DocumentoAppService;

// Essa classe tem os seguintes m�todos:
// - getTableCellRendererComponent() - M�todo que renderiza o bot�o
// - getTableCellEditorComponent() - M�todo que indica qual bot�o foi clicado
// - actionPerformed() - M�todo listener do bot�o
public class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JButton button;
	private DialogTabelaDocumento dialogTabelaDocumento;
	private DialogDocumento dialogDocumento;
	private DialogTabelaItem dialogTabelaItem;
	private DialogItem dialogItem;

	private static ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");
    
	private static DocumentoAppService documentoService = 
			(DocumentoAppService)fabrica.getBean ("documentoAppService");

	public ButtonColumn(JTable table, int coluna, DialogTabelaDocumento dialogTabelaDocumento,
			DialogDocumento dialogDocumento) {
		// super();
		this.table = table;
		this.dialogTabelaDocumento = dialogTabelaDocumento;
		this.dialogDocumento = dialogDocumento;

		button = new JButton();
		button.setText("Editar");
		button.addActionListener(this);

		TableColumnModel tableColumnModel = table.getColumnModel();

		// Designa um renderizador (o objeto corrente) para o bot�o
		tableColumnModel.getColumn(coluna).setCellRenderer(this);
		// getTableCellRendererComponent()

		// Designa um editor (o objeto corrente) para o bot�o
		tableColumnModel.getColumn(coluna).setCellEditor(this);
		// getTableCellEditorComponent()
		// getCellEditorValue()
	}

	public ButtonColumn(JTable table, int coluna, DialogTabelaItem dialogTabelaItem, DialogItem dialogItem) {
		// super();
		this.table = table;
		this.dialogTabelaItem = dialogTabelaItem;
		this.dialogItem = dialogItem;

		button = new JButton();
		button.setText("Editar");
		button.addActionListener(this);

		TableColumnModel tableColumnModel = table.getColumnModel();

		// Designa um renderizador (o objeto corrente) para o bot�o
		tableColumnModel.getColumn(coluna).setCellRenderer(this);
		// getTableCellRendererComponent()

		// Designa um editor (o objeto corrente) para o bot�o
		tableColumnModel.getColumn(coluna).setCellEditor(this);
		// getTableCellEditorComponent()
		// getCellEditorValue()
	}

	// Esse m�todo retorna o bot�o que ser� exibido - RESPONS�VEL PELA
	// RENDERIZA��O DO BOT�O (APAR�NCIA)
	@Override // M�todo de TableCellRenderer
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// A cor utilizada para exibir as letras ser� a do componente (table),
		// isto �, preto
		button.setForeground(table.getForeground());

		// Ser� utilizada como cor de fundo a cor default para os bot�es
		button.setBackground(UIManager.getColor("Button.background"));

		return button; // Esse � o bot�o que ser� exibido.

		// hasFocus = true quando navegamos com TAB e entramos na c�lula do
		// bot�o
		// isSelected = true quando clicamos na linha do bot�o em alguma outra
		// c�lula
		// hasFocus = false e isSelected = false quando a janela � exibida.
	}

	// retorna o bot�o que foi clicado. No clique no bot�o, por exemplo, o bot�o
	// pode mudar de cor.
	@Override // M�todo de TableCellEditor
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// IMPORTANTE

		// Quando um bot�o � clicado, esse m�todo � executado, e em seguida o
		// listener do bot�o (actionPerformed()) � executado.

		return button; // Se mudar para null o clique do bot�o p�ra de
						// funcionar.

	}

	@Override // M�todo de TableCellEditor - N�o � executado
	public Object getCellEditorValue() {
		return null;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			Documento umDocumento = documentoService
					.recuperaDocumento((Long) table.getValueAt(table.getSelectedRow(), 0));
			if (dialogDocumento != null) {
				dialogDocumento.designaDocumentoAFrame(umDocumento);
				dialogDocumento.editavel();
				dialogTabelaDocumento.dispose();
			}
			if (dialogItem != null) {
				dialogItem.designaDocumentoAFrame(umDocumento);
				dialogItem.novoDoDocumento();
				dialogTabelaItem.dispose();
			}
		} catch (DocumentoNaoEncontradoException e1) {
			if (dialogDocumento != null) {
				dialogDocumento.novo();
				dialogTabelaDocumento.dispose();
				JOptionPane.showMessageDialog(dialogDocumento, "Documento n�o encontrado", "",
						JOptionPane.ERROR_MESSAGE);
			}
			if (dialogItem != null) {
				dialogItem.novo();
				dialogTabelaItem.dispose();
				JOptionPane.showMessageDialog(dialogItem, "Documento n�o encontrado", "", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
