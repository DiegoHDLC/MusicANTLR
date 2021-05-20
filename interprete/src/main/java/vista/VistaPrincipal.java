package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;

public class VistaPrincipal extends JFrame {

	private JPanel contentPane;
	Coordinador coordinador;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPrincipal frame = new VistaPrincipal();
					
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

					SwingUtilities.updateComponentTreeUI(frame);
					
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
	public VistaPrincipal() {
		initComponent();
	}
	public void initComponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 98, 289, 371);
		contentPane.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("tempo;60\r\ninstrumento;PIANO\r\n{\r\nC;4;#;blanca\r\n}");
		scrollPane.setViewportView(textPane);
		JTextArea textAreaAux = new JTextArea();
		textAreaAux.setBounds(217, 11, 40, 22);
		textAreaAux.setVisible(false);
		contentPane.add(textAreaAux);
		
		
		JButton btnNewButton = new JButton("Play");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					public void run() {
						try {
							coordinador.play(textPane);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}.start();
				
			
		}
			
		});
		btnNewButton.setBounds(399, 100, 100, 46);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Agregar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coordinador.LeerArchivo(contentPane, textPane);
				
			}
		});
		btnNewButton_1.setBounds(399, 173, 100, 46);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Guardar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coordinador.guardarArchivo(textPane);
			}
		});
		btnNewButton_2.setBounds(399, 246, 100, 46);
		contentPane.add(btnNewButton_2);
		
		
		
		
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
		
	}
}
