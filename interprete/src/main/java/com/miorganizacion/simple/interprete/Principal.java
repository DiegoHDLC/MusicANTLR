
package com.miorganizacion.simple.interprete;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.jfugue.player.Player;


import controlador.Coordinador2;
import logica.Logica2;
import logica.NotasMusicales;
import vista.VistaPrincipal2;

public class Principal {
	
	public static VistaPrincipal2 v = new VistaPrincipal2();
	
	
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
		
		
		Coordinador2 coordinador = new Coordinador2(null);
		Logica2 logica = new Logica2("","","","", null,"");
		Player player = new Player();
		NotasMusicales nota = new NotasMusicales(player,"I[FLUTE] "+"C"+""+"0"+"/" + 0.5);
		nota.start();
		logica.setArgs(args);
		
		v.setCoordinador(coordinador);
		logica.setCoordinador(coordinador);
		
		coordinador.setVistaPrincipal(v);
		coordinador.setLogica(logica);
		v.setVisible(true);

		System.out.println("Interpretation finished");

	}

}
