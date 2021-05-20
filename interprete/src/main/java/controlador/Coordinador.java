package controlador;

import java.io.File;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import com.miorganizacion.simple.interprete.Principal;

import logica.Logica;
import vista.VistaPrincipal;

public class Coordinador {
	VistaPrincipal vistaPrincipal = new VistaPrincipal();
	Logica logica = new Logica(0, 0, 0, 0,null,"");
	Principal main = new Principal();

	public void setVistaPrincipal(VistaPrincipal vistaPrincipal) {
		this.vistaPrincipal = vistaPrincipal;
	}

	public void setLogica(Logica logica) {
		this.logica = logica;
	}
	
	public void play(JTextPane textPane) throws IOException {
		String[] lines = logica.leerTextArea(textPane);
		logica.crearArchivo(lines, "test/test.smp");
		logica.play();
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
		String[] lines = logica.leerTextArea(textPane);
		logica.crearArchivo(lines, "test/test.smp");
		JFileChooser seleccion = new JFileChooser();
		seleccion.setDialogTitle("GUARDAR");
		seleccion.setDialogType(JFileChooser.SAVE_DIALOG);
		//seleccion.showOpenDialog(textPane);
		int respuesta = seleccion.showSaveDialog(textPane); // Se apertura la ventana.
		String path = seleccion.getSelectedFile().getAbsolutePath();
		logica.crearArchivo(lines, path);
	    switch(respuesta){ // segun la opcion del usuario se ejecutan los algoritmos de interes
	        case JFileChooser.APPROVE_OPTION:
	          System.out.println("Guardar");
	          break;
	        case JFileChooser.CANCEL_OPTION:
	          System.out.println("Cancelado");
	          break;
	        default :
	          System.out.println("Error");
	          break;
	    }
		
	}

}
