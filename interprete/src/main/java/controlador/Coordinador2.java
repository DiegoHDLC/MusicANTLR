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
	VistaPrincipal2 vistaPrincipal = new VistaPrincipal2();
	Logica2 logica = new Logica2(0, 0, 0, 0,null,"");
	Principal main = new Principal();

	public void setVistaPrincipal(VistaPrincipal2 vistaPrincipal) {
		this.vistaPrincipal = vistaPrincipal;
	}

	public void setLogica(Logica2 logica) {
		this.logica = logica;
	}
	
	public void play(JTextPane textPane, JTextPane textAlerta) throws IOException {
		String[] lines = logica.leerTextArea(textPane);
		logica.crearArchivo(lines, "test/test.smp");
		logica.play(textAlerta);
	}

	public void LeerArchivo(JPanel contentPane, JTextPane textPane) {
		logica.leerArchivo(contentPane, textPane);
		
	}

	public boolean verificarTextArea(JTextPane textPane) {
		if(textPane.getText()=="") {
			return false;
		}
		return true;
	}

	public void guardarArchivo(JTextPane textPane) {
		logica.guardarArchivo(textPane);
		
		
		
	}

}
