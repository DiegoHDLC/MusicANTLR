package controlador;

import java.io.File;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import com.miorganizacion.simple.interprete.Principal;

import logica.Logica2;
import vista.VistaPrincipal2;

public class Coordinador2 {
	JTextArea textPane;
	VistaPrincipal2 vistaPrincipal = new VistaPrincipal2();
	Logica2 logica = new Logica2(0, 0, 0, 0,null,"", 0);
	Principal main = new Principal();
	public Coordinador2(JTextArea textPane) {
		this.textPane = textPane;
	}
	
	public void setTextPane (JTextArea textPane) {
		this.textPane = textPane;
	}
	
	public JTextArea getTextPane() {
		return textPane;
	}
	

	public void setVistaPrincipal(VistaPrincipal2 vistaPrincipal) {
		this.vistaPrincipal = vistaPrincipal;
	}

	public void setLogica(Logica2 logica) {
		this.logica = logica;
	}
	
	public void play(JTextArea textPane, JTextPane textAlerta) throws IOException {
		String[] lines = logica.leerTextArea(textPane);
		this.setTextPane(textPane);
		logica.crearArchivo(lines, "test/test.smp");
		logica.play(textAlerta);
	}

	public void LeerArchivo(JPanel contentPane, JTextArea textPane) {
		logica.leerArchivo(contentPane, textPane);
		
	}

	public boolean verificarTextArea(JTextPane textPane) {
		if(textPane.getText()=="") {
			return false;
		}
		return true;
	}

	public void guardarArchivo(JTextArea textPane) {
		logica.guardarArchivo(textPane);
		
		
		
	}

}
