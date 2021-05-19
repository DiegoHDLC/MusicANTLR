
package com.miorganizacion.simple.interprete;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.jfugue.player.Player;

import logica.NotasMusicales;

public class Main {
	
	private static final String EXTENSION = "smp";

	public static void main(String[] args) throws IOException {
		String program = args.length > 1 ? args[1] : "test/test." + EXTENSION;
		System.out.println("Interpreting file " + program);

		SimpleLexer lexer = new SimpleLexer(new ANTLRFileStream(program));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SimpleParser parser = new SimpleParser(tokens);

		SimpleParser.ProgramContext tree = parser.program();

		SimpleCustomVisitor visitor = new SimpleCustomVisitor();
		visitor.visit(tree);
		
		

		System.out.println("Interpretation finished");

	}

}
