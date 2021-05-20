
package com.miorganizacion.simple.interprete;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.jfugue.player.Player;

import controlador.Coordinador;
import logica.Logica;
import logica.NotasMusicales;
import vista.VistaPrincipal;

public class Principal {
	
	
	
	
	public static void main(String[] args) throws IOException {
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
		Coordinador coordinador = new Coordinador();
		Logica logica = new Logica("","","","", null,"");
		Player player = new Player();
		NotasMusicales nota = new NotasMusicales(player,"I[FLUTE] "+"C"+""+"0"+"/" + 0.5);
		nota.start();
		logica.setArgs(args);
		VistaPrincipal vistaPrincipal = new VistaPrincipal();
		
		vistaPrincipal.setCoordinador(coordinador);
		logica.setCoordinador(coordinador);
		
		coordinador.setVistaPrincipal(vistaPrincipal);
		coordinador.setLogica(logica);
		vistaPrincipal.setVisible(true);
		
		
		
		

		System.out.println("Interpretation finished");

	}

}
