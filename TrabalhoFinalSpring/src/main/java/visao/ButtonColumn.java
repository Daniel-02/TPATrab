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

// Essa classe tem os seguintes métodos:
// - getTableCellRendererComponent() - Método que renderiza o botão
// - getTableCellEditorComponent() - Método que indica qual botão foi clicado
// - actionPerformed() - Método listener do botão
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

		// Designa um renderizador (o objeto corrente) para o botão
		tableColumnModel.getColumn(coluna).setCellRenderer(this);
		// getTableCellRendererComponent()

		// Designa um editor (o objeto corrente) para o botão
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

		// Designa um renderizador (o objeto corrente) para o botão
		tableColumnModel.getColumn(coluna).setCellRenderer(this);
		// getTableCellRendererComponent()

		// Designa um editor (o objeto corrente) para o botão
		tableColumnModel.getColumn(coluna).setCellEditor(this);
		// getTableCellEditorComponent()
		// getCellEditorValue()
	}

	// Esse método retorna o botão que será exibido - RESPONSÁVEL PELA
	// RENDERIZAÇÃO DO BOTÃO (APARÊNCIA)
	@Override // Método de TableCellRenderer
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// A cor utilizada para exibir as letras será a do componente (table),
		// isto é, preto
		button.setForeground(table.getForeground());

		// Será utilizada como cor de fundo a cor default para os botões
		button.setBackground(UIManager.getColor("Button.background"));

		return button; // Esse é o botão que será exibido.

		// hasFocus = true quando navegamos com TAB e entramos na célula do
		// botão
		// isSelected = true quando clicamos na linha do botão em alguma outra
		// célula
		// hasFocus = false e isSelected = false quando a janela é exibida.
	}

	// retorna o botão que foi clicado. No clique no botão, por exemplo, o botão
	// pode mudar de cor.
	@Override // Método de TableCellEditor
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// IMPORTANTE

		// Quando um botão é clicado, esse método é executado, e em seguida o
		// listener do botão (actionPerformed()) é executado.

		return button; // Se mudar para null o clique do botão pára de
						// funcionar.

	}

	@Override // Método de TableCellEditor - Não é executado
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
				JOptionPane.showMessageDialog(dialogDocumento, "Documento não encontrado", "",
						JOptionPane.ERROR_MESSAGE);
			}
			if (dialogItem != null) {
				dialogItem.novo();
				dialogTabelaItem.dispose();
				JOptionPane.showMessageDialog(dialogItem, "Documento não encontrado", "", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
