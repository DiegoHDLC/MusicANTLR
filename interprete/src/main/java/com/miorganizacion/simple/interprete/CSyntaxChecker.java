package com.miorganizacion.simple.interprete;
import com.miorganizacion.simple.interprete.*;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.List;

import static org.antlr.v4.runtime.CharStreams.fromString;
public class CSyntaxChecker {
	 public static List<SyntaxError> getSyntaxErrors(String sourceCode)
	    {
	        CodePointCharStream inputStream = fromString(sourceCode);
	        MusicANTLRLexer lexer = new MusicANTLRLexer(inputStream);
	        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
	        MusicANTLRParser parser = new MusicANTLRParser(commonTokenStream);
	        SyntaxErrorListener listener = new SyntaxErrorListener();
	        parser.addErrorListener(listener);
	        

	        return listener.getSyntaxErrors();
	    }
}
