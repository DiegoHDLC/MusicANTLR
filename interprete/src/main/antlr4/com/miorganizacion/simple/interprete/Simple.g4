grammar Simple;


@parser::header{
	import java.util.Map;
	import java.util.HashMap;
	import logica.*;
	
}
@parser::members{
	Map<String, Object> symbolTable = new HashMap<String, Object>();
	Logica logica = new Logica(0,"","","");
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
		logica.testeo($n, $NOMBRE_FIG.text);
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
		logica.setAlteracion("");
	}) 
	SEMICOLON 
	(alt1 = alteracion{
		$alt = $alt1.alt;
		
		
			logica.setAlteracion($alt);
		
		
	} |NULO{
		logica.setAlteracion("");
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
/*Viento*/ 'FLUTE'
/*Guitarras */|'GUITAR'|'ELECTRIC_CLEAN_GUITAR'
/*Bajos */ |'SLAP_BASS_1'|'ACOUSTIC_BASS'
/*Pianos */ |'PIANO'|
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