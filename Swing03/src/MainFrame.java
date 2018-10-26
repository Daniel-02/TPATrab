import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTittle = new JLabel("Cadastro de Clientes");
		lblTittle.setFont(new Font("Serif", Font.BOLD, 20));
		lblTittle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTittle.setBounds(10, 11, 424, 27);
		contentPane.add(lblTittle);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 49, 87, 14);
		contentPane.add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(107, 46, 317, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(10, 74, 46, 14);
		contentPane.add(lblSexo);
		
		JRadioButton rdbtnMasculino = new JRadioButton("Masculino");
		buttonGroup.add(rdbtnMasculino);
		rdbtnMasculino.setBounds(107, 73, 109, 23);
		contentPane.add(rdbtnMasculino);
		
		JRadioButton rdbtnFeminino = new JRadioButton("Feminino");
		buttonGroup.add(rdbtnFeminino);
		rdbtnFeminino.setBounds(260, 73, 109, 23);
		contentPane.add(rdbtnFeminino);
		
		JLabel lblFaixaEtria = new JLabel("Faixa Et\u00E1ria:");
		lblFaixaEtria.setBounds(10, 106, 87, 14);
		contentPane.add(lblFaixaEtria);
		
		JComboBox comboBoxFaixaEtaria = new JComboBox();
		comboBoxFaixaEtaria.setModel(new DefaultComboBoxModel(new String[] {"", "Menor de 18", "Entre 18 e 24", "Entre 24 e 35", "Entre 35 e 50", "Entre 50 e 65", "Maior de 65"}));
		comboBoxFaixaEtaria.setBounds(107, 103, 317, 20);
		contentPane.add(comboBoxFaixaEtaria);
		
		JLabel lblDesejaReceber = new JLabel("Deseja receber:");
		lblDesejaReceber.setBounds(10, 131, 87, 14);
		contentPane.add(lblDesejaReceber);
		
		JCheckBox chckbxReceber = new JCheckBox("Sim, desejo receber.");
		chckbxReceber.setBounds(107, 127, 317, 23);
		contentPane.add(chckbxReceber);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(107, 195, 317, 119);
		contentPane.add(textArea);
		
		JButton btnCopiar = new JButton("Copiar");
		btnCopiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuffer sb = new StringBuffer(100);
				sb.append("Nome: " + textFieldNome.getText() + "\n");
				if (rdbtnMasculino.isSelected()) sb.append("Sexo: Masculino\n");
				if (rdbtnFeminino.isSelected()) sb.append("Sexo: Feminino\n");
				sb.append("Faixa etária: " + comboBoxFaixaEtaria.getSelectedItem() + "\n");
				sb.append("Receber? " + (chckbxReceber.isSelected() == true ? "Sim\n" : "Não\n"));
				textArea.setText(sb.toString());
				
			}
		});
		btnCopiar.setBounds(107, 157, 89, 23);
		contentPane.add(btnCopiar);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
			}
		});
		btnApagar.setBounds(260, 157, 89, 23);
		contentPane.add(btnApagar);
		
	}
}
