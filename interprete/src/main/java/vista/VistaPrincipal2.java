package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador2;
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

public class VistaPrincipal2 extends JFrame {

	private JPanel contentPane;
	Coordinador2 coordinador;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPrincipal2 frame = new VistaPrincipal2();
					
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
	public VistaPrincipal2() {
		initComponent();
	}
	public void initComponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 313, 613);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 172, 371);
		contentPane.add(scrollPane);
		setLocationRelativeTo(null);
		
		JTextArea textPane = new JTextArea();
		textPane.setText("tempo;60\r\ninstrumento;PIANO\r\n{\r\nC;4;#;blanca\r\n}");
		scrollPane.setViewportView(textPane);
		//coordinador.setTextPane(textPane);
		JTextArea textAreaAux = new JTextArea();
		textAreaAux.setBounds(217, 11, 40, 22);
		textAreaAux.setVisible(false);
		contentPane.add(textAreaAux);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 498, 282, 67);
		contentPane.add(scrollPane_1);
		JTextPane textAlerta = new JTextPane();
		textAlerta.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		scrollPane_1.setViewportView(textAlerta);
		
		
		JButton btnNewButton = new JButton("Play");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					public void run() {
						try {
							
							coordinador.play(textPane, textAlerta);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}.start();
				
			
		}
			
		});
		btnNewButton.setBounds(192, 68, 100, 46);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Agregar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coordinador.LeerArchivo(contentPane, textPane);
				
			}
		});
		btnNewButton_1.setBounds(192, 125, 100, 46);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Guardar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coordinador.guardarArchivo(textPane);
			}
		});
		btnNewButton_2.setBounds(192, 182, 100, 46);
		contentPane.add(btnNewButton_2);
		
		
		
		
	}

	public void setCoordinador(Coordinador2 coordinador) {
		this.coordinador = coordinador;
		
	}
}
