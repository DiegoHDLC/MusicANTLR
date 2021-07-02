package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.BitSet;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.xml.stream.util.EventReaderDelegate;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

import com.miorganizacion.simple.interprete.SimpleCustomVisitor;
import com.miorganizacion.simple.interprete.SimpleLexer;
import com.miorganizacion.simple.interprete.SimpleParser;

import controlador.Coordinador2;
import jm.JMC;
import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.View;
import vista.VistaPrincipal2;

public class Logica2 implements JMC {
	Object tempo;
	Object octava;
	Object alteracion;
	Object instrumento;
	String[] args;
	String path;
	public static int numLinea;
	Coordinador2 coordinador;
	public static Phrase phr = new Phrase();
	
	int contador = 0;
	private static final String EXTENSION = "smp";
	private Highlighter.HighlightPainter painter;
	

	
	public Logica2(Object tempo, Object octava, Object alteracion, Object instrumento, String[] args, String path)
	{
		this.tempo = tempo;
		this.octava = octava;
		this.alteracion = alteracion;
		this.instrumento = instrumento;
		this.args = args;
		this.path = path;
		
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	public Object getTempo() {
		return tempo;
	}
	
	public void setTempo(Object tempo) {
		this.tempo = tempo;
	}
	
	public String[] getArgs() {
		return args;
	}
	
	public void setArgs(String[] args) {
		this.args = args;
	}
	
	public Object getOctava() {
		return octava;
	}
	public void setOctava(Object octava) {
		this.octava = octava;
	}
	public Object getAlteracion() {
		return alteracion;
	}
	
	public void setAlteracion(Object alteracion) {
		this.alteracion = alteracion;
	}
	public Object getInstrumento() {
		return instrumento;
	}
	
	public void setInstrumento(Object instrumento) {
		this.instrumento = instrumento;
	}
	
	public void testeo(Object notaExtraida, Object nombreFig) {
		
		
		Player player = new Player();
		float t = Float.parseFloat(tempo.toString());
		Object tiempoNota = determinarTiempo(nombreFig, t);
		Object notaFinal = determinarNota(notaExtraida);
		Object octavaFinal = determinarOctava(this.getOctava());
		Object alteracionFinal = determinarAlteracion(this.getAlteracion());
		System.out.println("Alteracion Final Test: "+ alteracionFinal);
		int notaMIDI = obtenerMIDI(notaFinal, octavaFinal, this.getAlteracion());
		double tipoNota = determinarTipoNota(nombreFig);
		Note n = new Note(notaMIDI, tipoNota);
		
        phr.addNote(n);
 
		NotasMusicales nota = new NotasMusicales(player,"I["+this.getInstrumento().toString()+"] "+notaFinal.toString()+alteracionFinal+octavaFinal+"/" + tiempoNota);
		nota.start();
		float test = Float.parseFloat(tiempoNota.toString());
		float test1 = test*1000;
		contador++;
		int n2 = numLinea - 4;
		System.out.println("contador: "+ contador);
		System.out.println("numLinea:"+ n2);
	
		
		System.out.println(notaExtraida);
		try {
			int lineNumber = contador +2;
			int startIndex = VistaPrincipal2.textPane.getLineStartOffset(lineNumber);
			 int endIndex = VistaPrincipal2.textPane.getLineEndOffset(lineNumber);
			 painter = new DefaultHighlighter.DefaultHighlightPainter( new Color(242, 65, 48));
			 VistaPrincipal2.textPane.getHighlighter().addHighlight(startIndex, endIndex, painter);
			Thread.sleep((long)test1);
			VistaPrincipal2.textPane.getHighlighter().removeAllHighlights();
			VistaPrincipal2.notaLabel.setText("-");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
			
	
        
	}

	private double determinarTipoNota(Object nombreFig) {
			switch(nombreFig.toString()) {
			case "negra": return QUARTER_NOTE;
			case "blanca": return MINIM;
			case "redonda": return SEMIBREVE;
			case "corchea": return QUAVER;
			case "semicorchea": return SEMI_QUAVER;
			}
		return 0;
	}

	private int obtenerMIDI(Object notaFinal, Object octavaFinal, Object alteracionFinal) {
		int[] columnaC = {0,12,24,36,48,60,72,84,96,108,120};
		int[] columnaCSost = {1,13,25,37,49,61,73,85,97,109,121};
		int[] columnaD = {2,14,26,38,50,62,74,86,98,110,122};
		int[] columnaDSost = {3,15,27,39,51,63,75,87,99,111,123};
		int[] columnaE = {4,16,28,40,52,64,76,88,100,112,124};
		int[] columnaF = {5,17,29,41,53,65,77,89,101,113,125};
		int[] columnaFSost = {6,18,30,42,54,66,78,90,102,114,126};
		int[] columnaG = {7,19,31,43,55,67,79,91,103,115,127};
		int[] columnaGSost = {8,20,32,44,56,68,80,92,104,116};
		int[] columnaA = {9,21,33,45,57,69,81,93,105,117};
		int[] columnaASost = {10,22,34,46,58,70,82,94,106,118};
		int[] columnaB = {11,23,35,47,59,71,83,95,106,119};
		byte[] nota = notaFinal.toString().getBytes(StandardCharsets.US_ASCII);
		
		byte[] alteracion = alteracionFinal.toString().getBytes(StandardCharsets.US_ASCII);
		
		
		System.out.println("alteracion normal: "+ alteracionFinal);
		System.out.println("Nota final: " + nota[0]);
		System.out.println("Alteracion Final: " + alteracion[0]);
		
		if(nota[0] == 67) {// C
			if(alteracion[0] == 35) { //#
				return columnaCSost[Integer.parseInt(octavaFinal.toString())];
			}
			if(alteracion[0] == 45) { // " "
				return columnaC[Integer.parseInt(octavaFinal.toString())];
			}
			//if(alteracion[0] == )
		}
		if(nota[0] == 68) {// D
			if(alteracion[0] == 35) { //#
				return columnaDSost[Integer.parseInt(octavaFinal.toString())];
			}
			if(alteracion[0] == 45) { // " "
				return columnaD[Integer.parseInt(octavaFinal.toString())];
			}

		}
		if(nota[0] == 69) {// E
		
			if(alteracion[0] == 45) { // " "
				return columnaE[Integer.parseInt(octavaFinal.toString())];
			}

		}
		if(nota[0] == 70) {// F
			if(alteracion[0] == 35) { //#
				return columnaFSost[Integer.parseInt(octavaFinal.toString())];
			}
			if(alteracion[0] == 45) { // " "
				return columnaF[Integer.parseInt(octavaFinal.toString())];
			}

		}
		if(nota[0] == 71) {// G
			if(alteracion[0] == 35) { //#
				return columnaGSost[Integer.parseInt(octavaFinal.toString())];
			}
			if(alteracion[0] == 45) { // " "
				return columnaG[Integer.parseInt(octavaFinal.toString())];
			}
		}
		if(nota[0] == 65) {// A
			if(alteracion[0] == 35) { //#
				return columnaASost[Integer.parseInt(octavaFinal.toString())];
			}
			if(alteracion[0] == 45) { // " "
				return columnaA[Integer.parseInt(octavaFinal.toString())];
			}

		}
		if(nota[0] == 66) {// B
			
			if(alteracion[0] == 45) { // " "
				return columnaB[Integer.parseInt(octavaFinal.toString())];
			}
		}
		return 0;
	}


	private Object determinarAlteracion(Object alteracion) {
		byte[] alt = alteracion.toString().getBytes(StandardCharsets.US_ASCII);
		if(alt[0] == 45) {
			return "";
		}
		return alteracion;
	}

	private Object determinarOctava(Object octava) {
		byte[] oct = octava.toString().getBytes(StandardCharsets.US_ASCII);
		if(oct[0] == 45) {
			return 4;
		}
		/*
		if(octava == "-") {
			return 4;
		}
		*/
		return octava;
	}

	private Object determinarNota(Object notaExtraida) {
		switch((String)notaExtraida) {
		case "C":
		case "DO":
			return "C";
		case "D":
		case "RE":
			return "D";
		case "E":
		case "MI":
			return "E";
		case "F":
		case "FA":
			return "F";
		case "G":
		case "SOL":
			return "G";
		case "A":
		case "LA":
			return "A";
		case "B":
		case "SI":
			return "B";
		}
		return null;
	}

	private Object determinarTiempo(Object nombreFig, float tempo) {
		
		
		float negra = 60/tempo;
		float blanca = 2*negra;
		float redonda = 4*negra;
		float corchea = negra/2;
		float semicorchea = negra/4;
		float fusa = negra/8;
		float semifusa = negra/16;
		
		switch((String)nombreFig) {
			case "negra":
				return negra;
			case "blanca":
				return blanca;
			case "redonda":
				return redonda;
			case "corchea":
				return corchea;
			case "semicorchea":
				return semicorchea;
			case "fusa":
				return fusa;
			case "semifusa":
				return semifusa;
				
			}
		return negra;
	}

	public void setCoordinador(Coordinador2 coordinador) {
		this.coordinador = coordinador;
		
	}
	
	public void play(JTextPane textAlerta) throws IOException {
		textAlerta.setText("");
		String program = args.length > 1 ? args[1] : "test/test." + EXTENSION;
		System.out.println("Interpreting file " + program);
		
		SimpleLexer lexer = new SimpleLexer(new ANTLRFileStream(program));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SimpleParser parser = new SimpleParser(tokens);
		ANTLRErrorListener listener = new ANTLRErrorListener() {
			
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
					String msg, RecognitionException e) {
				textAlerta.setText("");
				
				StyledDocument document = (StyledDocument) textAlerta.getDocument();
			     try {
			    	 
					document.insertString(document.getLength(), "linea:" + line + " "+ msg, null);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			@Override
			public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction,
					ATNConfigSet configs) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex,
					BitSet conflictingAlts, ATNConfigSet configs) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact,
					BitSet ambigAlts, ATNConfigSet configs) {
				// TODO Auto-generated method stub
				
			}
		};
		parser.addErrorListener(listener);
		SimpleParser.ProgramContext tree = parser.program();

		SimpleCustomVisitor visitor = new SimpleCustomVisitor();
		
		visitor.visit(tree);
		
	}

	public void leerArchivo(JPanel contentPane, JTextArea textArea ) {
		
		JFileChooser jf = new JFileChooser();
		jf.showOpenDialog(contentPane);
		File archivo = jf.getSelectedFile();
		String path = archivo.getAbsolutePath();
		textArea.setText("");
		this.setPath(path);
		try {
		 FileReader reader = new FileReader( this.getPath() );
         BufferedReader br = new BufferedReader(reader);
         textArea.read( br, null );
         br.close();
         textArea.requestFocus();
	 }
    catch(Exception e2) { System.out.println(e2); }
}
		
	
	public void crearArchivo(String[] lines, String path) {
		 FileWriter fichero = null;
	        PrintWriter pw = null;
	        try
	        {
	            fichero = new FileWriter(path);
	            pw = new PrintWriter(fichero);

	            for (int i = 0; i < lines.length; i++)
	            	pw.println(lines[i]);

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           try {
	           // Nuevamente aprovechamos el finally para 
	           // asegurarnos que se cierra el fichero.
	           if (null != fichero)
	              fichero.close();
	           } catch (Exception e2) {
	              e2.printStackTrace();
	           }
	        }
		
	}
	public String[] leerTextArea(JTextArea editorPane) {
		String[] lines = editorPane.getText().split("\\n");
		return lines;
	}
	public void guardarArchivo(JTextArea textPane) {
		
		String[] lines = this.leerTextArea(textPane);
		this.crearArchivo(lines, "test/test.smp");
		JFileChooser seleccion = new JFileChooser();
		seleccion.setDialogTitle("GUARDAR");
		seleccion.setDialogType(JFileChooser.SAVE_DIALOG);
		//seleccion.showOpenDialog(textPane);
		int respuesta = seleccion.showSaveDialog(textPane); // Se apertura la ventana.
		String path = seleccion.getSelectedFile().getAbsolutePath();
		this.crearArchivo(lines, path);
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