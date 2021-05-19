package logica;
import org.jfugue.player.*;
public class NotasMusicales extends Thread{
	Player player;
	String nota;
	
	public NotasMusicales(Player player, String nota) {
		this.player = player;
		this.nota = nota;
	}
	
	@Override
	public void run() {
		player.play(nota);
	}
}
