package logica;

import javax.xml.stream.util.EventReaderDelegate;

import org.jfugue.player.Player;

public class Logica {
	Object tempo;
	Object octava;
	Object alteracion;
	
	public Logica(Object tempo, Object octava, Object alteracion)
	{
		this.tempo = tempo;
		this.octava = octava;
		this.alteracion = alteracion;
	}
	
	public Object getTempo() {
		return tempo;
	}
	
	public void setTempo(Object tempo) {
		this.tempo = tempo;
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
	public void testeo(Object notaExtraida, Object nombreFig) {
		
		Player player = new Player();
		
		float t = Float.parseFloat(tempo.toString());
		//System.out.println(t);
		Object tiempoNota = determinarTiempo(nombreFig, t);
		Object notaFinal = determinarNota(notaExtraida);
	
		Object octavaFinal = determinarOctava(this.getOctava());
		Object alteracionFinal = determinarAlteracion(this.getAlteracion());
		
		System.out.println(alteracionFinal);
		//System.out.println(octavaFinal);
		NotasMusicales nota = new NotasMusicales(player, notaFinal.toString()+alteracionFinal+octavaFinal+"/" + tiempoNota);
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
}
