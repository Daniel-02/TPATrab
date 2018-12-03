package visao;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.TableColumnModel;

import util.Util;


public class DialogTabelaDocumento extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField dataTextField;
	private JTable table;
	private DocumentoModel documentoModel;
	private JScrollPane scrollPane;
	
	private TableColumnModel columnModel;
	
	DialogDocumento dialogDocumento;
	
	public DialogTabelaDocumento(DialogDocumento dialogDocumento)
	{
		super(dialogDocumento);
		
		this.dialogDocumento = dialogDocumento;
		
		setTitle("Pesquisa de Documentos");
		setBounds(110, 171, 608, 301);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 588, 111);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPesquisaPorData = new JLabel("Pesquisa por Data");
		lblPesquisaPorData.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPesquisaPorData.setHorizontalAlignment(SwingConstants.CENTER);
		lblPesquisaPorData.setBounds(203, 11, 195, 22);
		panel.add(lblPesquisaPorData);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblData.setBounds(92, 42, 55, 22);
		panel.add(lblData);
		
		dataTextField = new JTextField();
		dataTextField.setBackground(UIManager.getColor("Button.light"));
		dataTextField.setForeground(SystemColor.desktop);
		dataTextField.setBounds(142, 44, 324, 20);
		panel.add(dataTextField);
		dataTextField.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(this);
		btnPesquisar.setBounds(249, 75, 102, 23);
		panel.add(btnPesquisar);
		
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBounds(0, 112, 588, 144);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);

		/**********************************************************************/
		
		columnModel = table.getColumnModel();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		documentoModel = new DocumentoModel();	
    	documentoModel.setDataDocumento(Util.strToDate(dataTextField.getText()));
		table.setModel(documentoModel);

		// Designa um renderer e um editor para os bot�es.
		// Para cada bot�o exibido � executado o m�todo getTableCellRendererComponent() 
		// que retorna o bot�o que deve ser renderizado.
		// E sempre que um bot�o � clicado � executado o m�todo getTableCellEditorComponent()
		// que retorna o bot�o que foi clicado para que o listener deste bot�o possa ser executado.
		new ButtonColumn(table, 4, this, dialogDocumento); //Parametros na ordem ==> Tabela, n�mero da coluna onde est� o bot�o, this da janela de busca, janela anterior a janela de busca.

		// Designa um valor preferido para a coluna. Se ele for menor
		// ou maior do que o m�ximo poss�vel, ele ser� ajustado.
		// columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(0).setMinWidth(0);
		columnModel.getColumn(0).setMaxWidth(0);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(30);
		columnModel.getColumn(3).setPreferredWidth(100);
		columnModel.getColumn(4).setPreferredWidth(70);
		
		scrollPane.setVisible(true);
	}
}
