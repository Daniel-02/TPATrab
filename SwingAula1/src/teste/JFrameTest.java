package teste;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class JFrameTest extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JButton btnTexto;
	private JButton btnEsconder;
	private JButton btnPintar;
	private JLabel lblAlMundo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameTest frame = new JFrameTest();
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
	public JFrameTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 434, 332);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnTexto = new JButton("Mostrar");
		btnTexto.setBounds(120, 70, 89, 23);
		btnTexto.addActionListener(this);
		panel.add(btnTexto);
		
		btnEsconder = new JButton("Esconder");
		btnEsconder.setBounds(230, 70, 89, 23);
		btnEsconder.addActionListener(this);
		panel.add(btnEsconder);
		
		btnPintar = new JButton("Pintar");
		btnPintar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setBackground(new Color((float)Math.random(),(float)Math.random(),(float)Math.random()));
			}
		});
		btnPintar.setBounds(177, 228, 89, 23);
//		btnPintar.addActionListener(this);
		panel.add(btnPintar);
		
		lblAlMundo = new JLabel("Al\u00F4 Mundo!");
		lblAlMundo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlMundo.setBounds(0, 123, 434, 14);
		lblAlMundo.setVisible(false);
		panel.add(lblAlMundo);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnTexto) lblAlMundo.setVisible(true);
		if (obj == btnEsconder) lblAlMundo.setVisible(false);
//		if (obj == btnPintar) panel.setBackground(new Color((float)Math.random(),(float)Math.random(),(float)Math.random()));
	}
}
