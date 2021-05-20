package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;
import javax.xml.stream.util.EventReaderDelegate;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

import com.miorganizacion.simple.interprete.SimpleCustomVisitor;
import com.miorganizacion.simple.interprete.SimpleLexer;
import com.miorganizacion.simple.interprete.SimpleParser;

import controlador.Coordinador;

public class Logica {
	Object tempo;
	Object octava;
	Object alteracion;
	Object instrumento;
	String[] args;
	String path;
	Coordinador coordinador;
	private static final String EXTENSION = "smp";
	
	public Logica(Object tempo, Object octava, Object alteracion, Object instrumento, String[] args, String path)
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
		Pattern pattern = new Pattern();
		
		Player player = new Player();
		
		float t = Float.parseFloat(tempo.toString());
		//System.out.println(t);
		Object tiempoNota = determinarTiempo(nombreFig, t);
		Object notaFinal = determinarNota(notaExtraida);
		Object octavaFinal = determinarOctava(this.getOctava());
		Object alteracionFinal = determinarAlteracion(this.getAlteracion());
		Object instrumentoFinal = determinarInstrumento(this.getInstrumento());
		System.out.println(alteracionFinal);
		//System.out.println(octavaFinal);
		NotasMusicales nota = new NotasMusicales(player,"I["+this.getInstrumento().toString()+"] "+notaFinal.toString()+alteracionFinal+octavaFinal+"/" + tiempoNota);
		nota.start();
		float test = Float.parseFloat(tiempoNota.toString());
		float test1 = test*1000;
		
		//System.out.println((long)test1);
		System.out.println(notaExtraida);
		try {
			Thread.sleep((long)test1);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Object determinarInstrumento(Object instrumento2) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object determinarAlteracion(Object alteracion) {
		if(alteracion == "-") {
			return " ";
		}
		return alteracion;
	}

	private Object determinarOctava(Object octava) {
		if(octava == "-") {
			return " ";
		}
		return octava;
	}

	private Object determinarNota(Object notaExtraida) {
		switch((String)notaExtraida) {
		case "C":
		case ("DO"):
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

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
		
	}

	public void play() throws IOException {
		
		String program = args.length > 1 ? args[1] : "test/test." + EXTENSION;
		System.out.println("Interpreting file " + program);

		SimpleLexer lexer = new SimpleLexer(new ANTLRFileStream(program));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SimpleParser parser = new SimpleParser(tokens);

		SimpleParser.ProgramContext tree = parser.program();

		SimpleCustomVisitor visitor = new SimpleCustomVisitor();
		visitor.visit(tree);
		
	}

	public void leerArchivo(JPanel contentPane, JTextPane textPane ) {
		JFileChooser jf = new JFileChooser();
		jf.showOpenDialog(contentPane);
		File archivo = jf.getSelectedFile();
		String path = archivo.getAbsolutePath();
		textPane.setText("");
		this.setPath(path);
		try {
			BufferedReader leer = new BufferedReader(new FileReader(archivo));
			String linea = leer.readLine();
			StyledDocument doc = textPane.getStyledDocument();
			while(linea!=null) {
				doc.insertString(doc.getLength(), linea+"\n", null);
				linea = leer.readLine();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(path);
		
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
	public String[] leerTextArea(JEditorPane editorPane) {
		String[] lines = editorPane.getText().split("\\n");
		return lines;
	}
}
