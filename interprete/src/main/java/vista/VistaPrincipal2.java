package vista;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.sonatype.guice.bean.reflect.LoadedClass;

import controlador.Coordinador2;
import jm.JMC;
import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.View;
import logica.Logica2;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.SystemColor;

public class VistaPrincipal2 extends JFrame implements JMC{

	private JPanel contentPane;
	Coordinador2 coordinador;
	public static JLabel notaLabel;
	public static JTextArea textPane;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPrincipal2 frame = new VistaPrincipal2();
					
					try {
						UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
		setBackground(SystemColor.textHighlight);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\di_eg\\Desktop\\Libreria Notas Musicales\\55431-200.png"));
		setTitle("MusicANTLR");
		initComponent();
		//textTest.setText(coordinador.contarNumLinea(textPane));
	}
	

	public void initComponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 335, 613);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(242, 96, 82));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(10, 68, 172, 371);
		contentPane.add(scrollPane);
		setLocationRelativeTo(null);
		
		textPane = new JTextArea();
		textPane.setBackground(new Color(242,242,242));
		textPane.setFont(new Font("Monospaced", Font.BOLD, 13));
		
		
		textPane.setText("tempo;60\r\ninstrumento;PIANO\r\n{\r\nC;4;#;blanca\r\n}");
		scrollPane.setViewportView(textPane);
		//coordinador.setTextPane(textPane);
		JTextArea textAreaAux = new JTextArea();
		textAreaAux.setBounds(252, 46, 40, 22);
		textAreaAux.setVisible(false);
		contentPane.add(textAreaAux);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 498, 299, 67);
		contentPane.add(scrollPane_1);
		JTextPane textAlerta = new JTextPane();
		textAlerta.setBackground(new Color(242,242,242));
		textAlerta.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		scrollPane_1.setViewportView(textAlerta);
		
		
		JButton btnNewButton = new JButton("Reproducir");
		btnNewButton.setBackground(new Color(242,242,242));
		btnNewButton.setFont(new Font("Monospaced", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					public void run() {
						
							
							try {
								Logica2.phr = new Phrase();
								coordinador.play(textPane, textAlerta);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
					}
				}.start();
				
			
		}
			
		});
		btnNewButton.setBounds(192, 68, 117, 46);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Agregar");
		btnNewButton_1.setBackground(new Color(242,242,242));
		btnNewButton_1.setFont(new Font("Monospaced", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coordinador.LeerArchivo(contentPane, textPane);
				
			}
		});
		btnNewButton_1.setBounds(192, 125, 117, 46);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Guardar");
		btnNewButton_2.setBackground(new Color(242,242,242));
		btnNewButton_2.setFont(new Font("Monospaced", Font.BOLD, 11));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coordinador.guardarArchivo(textPane);
			}
		});
		btnNewButton_2.setBounds(192, 182, 117, 46);
		contentPane.add(btnNewButton_2);
		
		
		JButton generarPartitura = new JButton("Partitura");
		generarPartitura.setBackground(new Color(242,242,242));
		generarPartitura.setFont(new Font("Monospaced", Font.BOLD, 11));
		generarPartitura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						
				        View.notate(Logica2.phr);			      
				    }	
		});
		generarPartitura.setBounds(192, 239, 117, 46);
		contentPane.add(generarPartitura);
		
		JLabel titulo = new JLabel("MusicANTLR");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Monospaced", Font.BOLD, 25));
		titulo.setBounds(0, 11, 321, 46);
		contentPane.add(titulo);
		
		notaLabel = new JLabel("-");
		notaLabel.setFont(new Font("Monospaced", Font.BOLD, 35));
		notaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		notaLabel.setBounds(192, 349, 117, 77);
		contentPane.add(notaLabel);
		
		JLabel labelNota = new JLabel("Nota");
		labelNota.setFont(new Font("Monospaced", Font.BOLD, 18));
		labelNota.setHorizontalAlignment(SwingConstants.CENTER);
		labelNota.setBounds(192, 324, 117, 14);
		contentPane.add(labelNota);
		
		JTextArea textPane_1 = new JTextArea();
		textPane_1.setBackground(new Color(242,242,242));
		textPane_1.setBounds(192, 296, 117, 141);
		contentPane.add(textPane_1);
		
		JLabel lblErrores = new JLabel("Detalles");
		lblErrores.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrores.setFont(new Font("Monospaced", Font.BOLD, 25));
		lblErrores.setBounds(10, 452, 301, 46);
		contentPane.add(lblErrores);
	}

	public void setCoordinador(Coordinador2 coordinador) {
		this.coordinador = coordinador;
		
	}
}