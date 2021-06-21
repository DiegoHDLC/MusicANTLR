grammar Simple;


@parser::header{
	import logica.*;
	import controlador.*;
	import vista.*;
	import javax.swing.JTextPane;
	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	import javax.swing.text.*;
	import javax.swing.border.EmptyBorder;
	
}
@parser::members{
	Logica2 logica = new Logica2(0,"","","",null,"");
	Coordinador2 coordinador = new Coordinador2(null);
	private Highlighter.HighlightPainter painter;
	int numLinea = 0;
	
}
	
	
	


program returns [Object tv, Object ins]:
	TEMPO SEMICOLON 
	tv1 = tempoValor{
		$tv = $tv1.tv;
		logica.setTempo($tv);
	}
	INS SEMICOLON 
	ins1 = instru{
		$ins = $ins1.ins;
		logica.setInstrumento($ins);
	}
	partitura 
	;
	
partitura: BRACKET_OPEN cuerpo* BRACKET_CLOSE;
		
cuerpo returns [Object n]:
n1 = nota 
	{
		$n = $n1.n;
	}
	SEMICOLON NOMBRE_FIG
	{
		
		
		numLinea++;
		//System.out.println(vista.textArea);
		//coordinador.setTextArea(vista.textArea);
		VistaPrincipal2.notaLabel.setText($n.toString());
		logica.testeo($n, $NOMBRE_FIG.text);
		
		
		//logica.setNumLinea(numLinea);
		//System.out.println(numLinea);
		
	}
	;
		
tempoValor returns [Object tv]:
	TEMPO_VALOR{
		$tv = $TEMPO_VALOR.text;
	};
nota returns [Object n, Object oct, Object alt]:
	NOTA{
		$n = $NOTA.text;
	}
	SEMICOLON 
	(oct1 = octava{
		$oct = $oct1.oct;
		logica.setOctava($oct);
	}|NULO{
		logica.setAlteracion("-");
	}) 
	SEMICOLON 
	(alt1 = alteracion{
		$alt = $alt1.alt;
			logica.setAlteracion($alt);
	} |NULO{
		logica.setAlteracion("-");
	}) ;
	
octava returns [Object oct]:
	OCTAVA{
		$oct = $OCTAVA.text;
	};
	
alteracion returns [Object alt]:
	ALTERACION{
		$alt = $ALTERACION.text;
	};

instru returns [Object ins]:
	INSTRUMENTO{
		$ins = $INSTRUMENTO.text;
	};

	


PROGRAM: 'program';
INS: 'instrumento';
INSTRUMENTO: 
/*Viento*/ 'FLUTE'|'OCARINA'
/*Guitarras */|'GUITAR'|'ELECTRIC_CLEAN_GUITAR'
/*Bajos */ |'SLAP_BASS_1'|'ACOUSTIC_BASS'
/*Pianos */ |'PIANO'
/*Efectos */|'RAIN'|'APPLAUSE'|'GUNSHOT'
/*Percusion */|'XYLOPHONE'
/*Organo */ | 'HARMONICA'
;
TEMPO: 'tempo';
PARTITURA: 'partitura';
NOMBRE_FIG: 'redonda'|'blanca'|'negra'|'corchea'|'semicorchea'|'fusa'|'semifusa';
TEMPO_VALOR: '60'|'120'|'180'|'360'|'520'; 
OCTAVA: [0-8];
ALTERACION: '#'|'b';
NULO: '-';


NOTA: 'C'| 'D' | 'E' |'F' |'G'  | 'A'|'B'
	|'DO'| 'RE'| 'MI'|'FA'|'SOL'|'LA'|'SI';
	
BRACKET_OPEN: '{';
BRACKET_CLOSE: '}';

PAR_OPEN: '(';
PAR_CLOSE: ')';

COMILLA: '"';
SEMICOLON: ';';
COMA: ',';
PUNTO: '.';

ID: [a-zA-Z_][A-Za-z0-9_]*;

NUMBER: [0-9]+;

WS: [ \t\n\r]+ -> skip;