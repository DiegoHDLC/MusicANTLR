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
	        SimpleLexer lexer = new SimpleLexer(inputStream);
	        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
	        SimpleParser parser = new SimpleParser(commonTokenStream);
	        SyntaxErrorListener listener = new SyntaxErrorListener();
	        parser.addErrorListener(listener);
	        

	        return listener.getSyntaxErrors();
	    }
}
