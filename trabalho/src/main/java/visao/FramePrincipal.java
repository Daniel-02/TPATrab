package visao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


public class FramePrincipal extends JFrame
{
	private static final long serialVersionUID = 1L;

	private JMenuItem menuItemDocumento;
	private JMenuItem menuItemItem;
	private JMenuItem menuItemSair;
	private JPanel panel;
	private JFrame frame;
	
	public FramePrincipal() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 382);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCadastrar = new JMenu("Cadastro");
		menuBar.add(mnCadastrar);
	
		frame = this;
		
		menuItemDocumento = new JMenuItem("Documento");
		menuItemDocumento.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK)); // Diz a combina��o necessaria para chamar os action listeners
		menuItemDocumento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogDocumento dialogDocumento = new DialogDocumento(frame);
				dialogDocumento.novo();
				dialogDocumento.setVisible(true);
			}
		});
		mnCadastrar.add(menuItemDocumento);

		menuItemItem = new JMenuItem("Item");
		menuItemItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK)); // Diz a combina��o necessaria para chamar os action listeners
		mnCadastrar.add(menuItemItem);
		
		menuItemSair = new JMenuItem("Sair");
		menuItemSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK)); // Diz a combina��o necessaria para chamar os action listeners
		menuItemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnCadastrar.add(menuItemSair);
		
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 614, 325);
		getContentPane().add(panel);
	}
}
