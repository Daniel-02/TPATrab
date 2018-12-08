package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import excecao.ItemNaoEncontradoException;
import modelo.Documento;
import modelo.Item;
import servico.ItemAppService;
import util.Util;

public class DialogItem extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	private static ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

	private static ItemAppService itemService = (ItemAppService) fabrica.getBean("itemAppService");

	private JButton novoButton;
	private JButton cadastrarButton;
	private JButton editarButton;
	private JButton alterarButton;
	private JButton removerButton;
	private JButton cancelarButton;
	private JButton buscarButton;

	private JPanel panel;

	private Item umItem;
	private JLabel msgNome;
	private JLabel msgConteudo;
	private JLabel msgData;
	private JTextArea nomeTextArea;
	private JTextArea conteudoTextArea;
	private JTextField dataCricacoTextField;
	private JTextField textFieldDocumento;

	private Documento documentoEncontrado;
	private JLabel msgDocumento;

	public void designaItemAFrame(Item umItem) {
		this.umItem = umItem;

		nomeTextArea.setText(umItem.getNome());
		conteudoTextArea.setText(umItem.getConteudo());
		textFieldDocumento.setText(umItem.getDocumento().getCabecalho());
		dataCricacoTextField.setText(umItem.getDataCriacaoMasc());

		msgNome.setText("");
		msgConteudo.setText("");
		msgData.setText("");
	}

	public void designaDocumentoAFrame(Documento umDocumento) {
		this.documentoEncontrado = umDocumento;
		textFieldDocumento.setText(umDocumento.getCabecalho());
	}

	public DialogItem(JFrame frame) {
		super(frame);

		setBounds(105, 147, 618, 330);
		setTitle("Cadastro de Items");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 602, 292);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel cadastrarLabel = new JLabel("Cadastro de Itens");
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
		buscarButton.setBounds(307, 190, 96, 23);
		panel.add(buscarButton);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 54, 73, 14);
		panel.add(lblNome);

		JLabel lblConteudo = new JLabel("Conteudo");
		lblConteudo.setBounds(10, 100, 61, 14);
		panel.add(lblConteudo);

		msgNome = new JLabel("");
		msgNome.setForeground(Color.RED);
		msgNome.setBounds(92, 80, 350, 14);
		panel.add(msgNome);

		msgConteudo = new JLabel("");
		msgConteudo.setForeground(Color.RED);
		msgConteudo.setBounds(92, 175, 350, 14);
		panel.add(msgConteudo);

		nomeTextArea = new JTextArea();
		nomeTextArea.setLineWrap(true);
		nomeTextArea.setForeground(Color.WHITE);
		nomeTextArea.setBackground(Color.DARK_GRAY);
		nomeTextArea.setBounds(93, 54, 349, 26);
		panel.add(nomeTextArea);

		conteudoTextArea = new JTextArea();
		conteudoTextArea.setLineWrap(true);
		conteudoTextArea.setForeground(Color.WHITE);
		conteudoTextArea.setBackground(Color.DARK_GRAY);
		conteudoTextArea.setBounds(93, 100, 349, 75);
		panel.add(conteudoTextArea);

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

		JLabel lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(10, 194, 73, 14);
		panel.add(lblDocumento);

		textFieldDocumento = new JTextField();
		textFieldDocumento.setForeground(Color.WHITE);
		textFieldDocumento.setBackground(Color.BLACK);
		textFieldDocumento.setBounds(92, 191, 205, 20);
		panel.add(textFieldDocumento);
		textFieldDocumento.setColumns(10);
		textFieldDocumento.setEnabled(false);

		msgDocumento = new JLabel("");
		msgDocumento.setForeground(Color.RED);
		msgDocumento.setBounds(92, 211, 205, 14);
		panel.add(msgDocumento);
		buscarButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == novoButton) {
			novo();
		} else if (obj == cadastrarButton) {
			boolean deuErro = validaItem();

			if (!deuErro) {
				umItem = new Item();
				umItem.setNome(nomeTextArea.getText());
				umItem.setConteudo(conteudoTextArea.getText());
				umItem.setDocumento(this.documentoEncontrado);
				umItem.setDataCriacao(Util.strToDate(dataCricacoTextField.getText()));

				itemService.inclui(umItem);

				salvo();

				JOptionPane.showMessageDialog(this, "Item cadastrado com sucesso", "", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (obj == editarButton) {
			editavel();
		} else if (obj == alterarButton) {
			boolean deuErro = validaItem();

			if (!deuErro) {
				umItem.setNome(nomeTextArea.getText());
				umItem.setConteudo(conteudoTextArea.getText());
				umItem.setDocumento(this.documentoEncontrado);
				umItem.setDataCriacao(Util.strToDate(dataCricacoTextField.getText()));

				try {
					itemService.altera(umItem);

					salvo();

					JOptionPane.showMessageDialog(this, "Item atualizado com sucesso", "",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (ItemNaoEncontradoException e1) {
					novo();

					JOptionPane.showMessageDialog(this, "Item não encontrado", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (obj == removerButton) {
			try {
				itemService.exclui(umItem);

				removido();

				JOptionPane.showMessageDialog(this, "Item removido com sucesso", "", JOptionPane.INFORMATION_MESSAGE);
			} catch (ItemNaoEncontradoException e1) {
				novo();

				JOptionPane.showMessageDialog(this, "Item não encontrado", "", JOptionPane.ERROR_MESSAGE);
			}
		} else if (obj == cancelarButton) {
			try {
				umItem = itemService.recuperaItem(umItem.getId());

				nomeTextArea.setText(umItem.getNome());
				conteudoTextArea.setText(umItem.getConteudo());
				textFieldDocumento.setText(umItem.getDocumento().getId().toString());
				dataCricacoTextField.setText(umItem.getDataCriacaoMasc());
				dataCricacoTextField.setText(umItem.getDataCriacaoMasc());

				cancelado();
			} catch (ItemNaoEncontradoException e1) {
				novo();

				JOptionPane.showMessageDialog(this, "Item não encontrado", "", JOptionPane.ERROR_MESSAGE);
			}
		} else if (obj == buscarButton) {
			DialogTabelaItem dialog = new DialogTabelaItem(this);
			dialog.setVisible(true);
		}
	}

	private boolean validaItem() {
		boolean deuErro = false;
		if (nomeTextArea.getText().trim().length() == 0) {
			deuErro = true;
			msgNome.setText("Campo de preenchimento obrigatório");
		} else {
			msgNome.setText("");
		}
		if (conteudoTextArea.getText().trim().length() == 0) {
			deuErro = true;
			msgConteudo.setText("Campo de preenchimento obrigatório");
		} else {
			msgConteudo.setText("");
		}
		if (textFieldDocumento.getText().trim().length() == 0) {
			deuErro = true;
			msgDocumento.setText("Campo de preenchimento obrigatório");
		} else {
			msgDocumento.setText("");
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
		nomeTextArea.setEnabled(true);
		conteudoTextArea.setEnabled(true);
		dataCricacoTextField.setEnabled(true);

		nomeTextArea.setText("");
		conteudoTextArea.setText("");
		dataCricacoTextField.setText("");
		textFieldDocumento.setText("");

		novoButton.setEnabled(false);
		cadastrarButton.setEnabled(true);
		editarButton.setEnabled(false);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(false);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}

	public void novoDoDocumento() {
		nomeTextArea.setEnabled(true);
		conteudoTextArea.setEnabled(true);
		dataCricacoTextField.setEnabled(true);

		novoButton.setEnabled(false);
		cadastrarButton.setEnabled(true);
		editarButton.setEnabled(false);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(false);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}

	public void salvo() {
		nomeTextArea.setEnabled(false);
		conteudoTextArea.setEnabled(false);
		dataCricacoTextField.setEnabled(false);

		novoButton.setEnabled(true);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(true);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(true);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}

	public void editavel() {
		nomeTextArea.setEnabled(true);
		conteudoTextArea.setEnabled(true);
		dataCricacoTextField.setEnabled(true);

		novoButton.setEnabled(false);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(false);
		alterarButton.setEnabled(true);
		removerButton.setEnabled(false);
		cancelarButton.setEnabled(true);
		buscarButton.setEnabled(false);
	}

	public void removido() {
		nomeTextArea.setEnabled(false);
		conteudoTextArea.setEnabled(false);
		dataCricacoTextField.setEnabled(false);

		novoButton.setEnabled(true);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(false);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(false);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}

	public void cancelado() {
		nomeTextArea.setEnabled(false);
		conteudoTextArea.setEnabled(false);
		dataCricacoTextField.setEnabled(false);

		novoButton.setEnabled(true);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(true);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(true);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}
}
