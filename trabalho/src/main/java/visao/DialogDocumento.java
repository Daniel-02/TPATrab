package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import excecao.DocumentoNaoEncontradoException;
import modelo.Documento;
import servico.DocumentoAppService;
import servico.controle.FabricaDeServico;
import util.Util;

import java.awt.SystemColor;
import javax.swing.JTextField;

public class DialogDocumento extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	private static DocumentoAppService documentoService;

	static {
		documentoService = FabricaDeServico.getServico(DocumentoAppService.class);
	}

	private JButton novoButton;
	private JButton cadastrarButton;
	private JButton editarButton;
	private JButton alterarButton;
	private JButton removerButton;
	private JButton cancelarButton;
	private JButton buscarButton;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private JPanel panel;

	private Documento umDocumento;
	private JLabel msgCabecalho;
	private JLabel msgConclusao;
	private JLabel msgData;
	private JTextArea cabecalhoTextArea;
	private JTextArea conclusaoTextArea;
	private JTextField dataCricacoTextField;

	public void designaDocumentoAFrame(Documento umDocumento) {
		this.umDocumento = umDocumento;

		cabecalhoTextArea.setText(umDocumento.getCabecalho());
		conclusaoTextArea.setText(umDocumento.getConclusao());
		dataCricacoTextField.setText(umDocumento.getDataCriacaoMasc());

		msgCabecalho.setText("");
		msgConclusao.setText("");
		msgData.setText("");
	}

	public DialogDocumento(JFrame frame) {
		super(frame);

		setBounds(105, 147, 618, 330);
		setTitle("Cadastro de Documentos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 602, 292);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel cadastrarLabel = new JLabel("Cadastro de Documentos");
		cadastrarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cadastrarLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		cadastrarLabel.setBounds(189, 11, 190, 26);
		panel.add(cadastrarLabel);

		novoButton = new JButton("Novo");
		novoButton.setBounds(452, 50, 96, 23);
		panel.add(novoButton);
		novoButton.addActionListener(this);

		cadastrarButton = new JButton("Cadastrar");
		cadastrarButton.setBounds(452, 77, 96, 23);
		panel.add(cadastrarButton);
		cadastrarButton.addActionListener(this);

		editarButton = new JButton("Editar");
		editarButton.setBounds(452, 104, 96, 23);
		panel.add(editarButton);
		editarButton.addActionListener(this);

		alterarButton = new JButton("Alterar");
		alterarButton.setBounds(452, 131, 96, 23);
		panel.add(alterarButton);
		alterarButton.addActionListener(this);

		removerButton = new JButton("Remover");
		removerButton.setBounds(452, 158, 96, 23);
		panel.add(removerButton);
		removerButton.addActionListener(this);

		cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(452, 185, 96, 23);
		panel.add(cancelarButton);
		cancelarButton.addActionListener(this);

		buscarButton = new JButton("Buscar");
		buscarButton.setBounds(452, 212, 96, 23);
		panel.add(buscarButton);

		JLabel lblCabecalho = new JLabel("Cabe\u00E7alho");
		lblCabecalho.setBounds(10, 54, 73, 14);
		panel.add(lblCabecalho);

		JLabel lblConclusao = new JLabel("Conclusao");
		lblConclusao.setBounds(10, 150, 61, 14);
		panel.add(lblConclusao);

		msgCabecalho = new JLabel("");
		msgCabecalho.setForeground(Color.RED);
		msgCabecalho.setBounds(92, 130, 350, 14);
		panel.add(msgCabecalho);

		msgConclusao = new JLabel("");
		msgConclusao.setForeground(Color.RED);
		msgConclusao.setBounds(92, 226, 350, 14);
		panel.add(msgConclusao);
		
		cabecalhoTextArea = new JTextArea();
		cabecalhoTextArea.setLineWrap(true);
		cabecalhoTextArea.setForeground(Color.WHITE);
		cabecalhoTextArea.setBackground(Color.DARK_GRAY);
		cabecalhoTextArea.setBounds(93, 54, 349, 75);
		panel.add(cabecalhoTextArea);
		
		conclusaoTextArea = new JTextArea();
		conclusaoTextArea.setLineWrap(true);
		conclusaoTextArea.setForeground(Color.WHITE);
		conclusaoTextArea.setBackground(Color.DARK_GRAY);
		conclusaoTextArea.setBounds(93, 150, 349, 75);
		panel.add(conclusaoTextArea);
		
		JLabel lblDataDeCriacao = new JLabel("Data de Cria\u00E7\u00E3o");
		lblDataDeCriacao.setBounds(10, 244, 84, 14);
		panel.add(lblDataDeCriacao);
		
		dataCricacoTextField = new JTextField();
		dataCricacoTextField.setForeground(Color.WHITE);
		dataCricacoTextField.setBackground(Color.DARK_GRAY);
		dataCricacoTextField.setBounds(92, 241, 175, 20);
		panel.add(dataCricacoTextField);
		dataCricacoTextField.setColumns(10);
		
		msgData = new JLabel("");
		msgData.setForeground(Color.RED);
		msgData.setBounds(92, 265, 350, 14);
		panel.add(msgData);
		buscarButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == novoButton) {
			novo();
		} else if (obj == cadastrarButton) {
			boolean deuErro = validaDocumento();

			if (!deuErro) {
				umDocumento = new Documento();
				umDocumento.setCabecalho(cabecalhoTextArea.getText());
				umDocumento.setConclusao(conclusaoTextArea.getText());
				umDocumento.setDataCriacao(Util.strToDate(dataCricacoTextField.getText()));
				

				documentoService.inclui(umDocumento);

				salvo();

				JOptionPane.showMessageDialog(this, "Documento cadastrado com sucesso", "",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (obj == editarButton) {
			editavel();
		} else if (obj == alterarButton) {
			boolean deuErro = validaDocumento();

			if (!deuErro) {
				umDocumento.setCabecalho(cabecalhoTextArea.getText());
				umDocumento.setConclusao(conclusaoTextArea.getText());

				try {
					documentoService.altera(umDocumento);

					salvo();

					JOptionPane.showMessageDialog(this, "Documento atualizado com sucesso", "",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (DocumentoNaoEncontradoException e1) {
					novo();

					JOptionPane.showMessageDialog(this, "Documento não encontrado", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (obj == removerButton) {
			try {
				documentoService.exclui(umDocumento);

				removido();

				JOptionPane.showMessageDialog(this, "Documento removido com sucesso", "",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (DocumentoNaoEncontradoException e1) {
				novo();

				JOptionPane.showMessageDialog(this, "Documento não encontrado", "", JOptionPane.ERROR_MESSAGE);
			}
		} else if (obj == cancelarButton) {
			try {
				umDocumento = documentoService.recuperaDocumento(umDocumento.getId());

				cabecalhoTextArea.setText(umDocumento.getCabecalho());
				conclusaoTextArea.setText(umDocumento.getConclusao());

				cancelado();
			} catch (DocumentoNaoEncontradoException e1) {
				novo();

				JOptionPane.showMessageDialog(this, "Documento não encontrado", "", JOptionPane.ERROR_MESSAGE);
			}
		} else if (obj == buscarButton) {
			DialogTabelaDocumento dialog = new DialogTabelaDocumento(this);
			dialog.setVisible(true);
		}
	}

	private boolean validaDocumento() {
		boolean deuErro = false;
		if (cabecalhoTextArea.getText().trim().length() == 0) {
			deuErro = true;
			msgCabecalho.setText("Campo de preenchimento obrigatório");
		} else {
			msgCabecalho.setText("");
		}
		if (conclusaoTextArea.getText().trim().length() == 0) {
			deuErro = true;
			msgConclusao.setText("Campo de preenchimento obrigatório");
		} else {
			msgConclusao.setText("");
		}
		if (dataCricacoTextField.getText().trim().length() == 0) {
			deuErro = true;
			msgData.setText("Campo de preenchimento obrigatório");
		} else if (!Util.dataValida(dataCricacoTextField.getText().trim())) {
			deuErro = true;
			msgData.setText("Formato de data inválida: dd/mm/yyyy");
		} else {
			msgData.setText("");
		}

		return deuErro;
	}

	public void novo() {
		cabecalhoTextArea.setEnabled(true);
		conclusaoTextArea.setEnabled(true);

		cabecalhoTextArea.setText("");
		conclusaoTextArea.setText("");
		buttonGroup.clearSelection();

		novoButton.setEnabled(false);
		cadastrarButton.setEnabled(true);
		editarButton.setEnabled(false);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(false);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}

	public void salvo() {
		cabecalhoTextArea.setEnabled(false);
		conclusaoTextArea.setEnabled(false);

		novoButton.setEnabled(true);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(true);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(true);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}

	public void editavel() {
		cabecalhoTextArea.setEnabled(true);
		conclusaoTextArea.setEnabled(true);

		novoButton.setEnabled(false);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(false);
		alterarButton.setEnabled(true);
		removerButton.setEnabled(false);
		cancelarButton.setEnabled(true);
		buscarButton.setEnabled(false);
	}

	public void removido() {
		cabecalhoTextArea.setEnabled(false);
		conclusaoTextArea.setEnabled(false);

		novoButton.setEnabled(true);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(false);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(false);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}

	public void cancelado() {
		cabecalhoTextArea.setEnabled(false);
		conclusaoTextArea.setEnabled(false);

		novoButton.setEnabled(true);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(true);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(true);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}
}
