/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.oclengine.internal;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.octopus.expressions.internal.analysis.context.ContextAnalyzer;
import nl.klasse.octopus.expressions.internal.analysis.context.DefinitionAnalyzer;
import nl.klasse.octopus.expressions.internal.analysis.expressions.ExpressionAnalyzer;
import nl.klasse.octopus.expressions.internal.parser.javacc.OclParser;
import nl.klasse.octopus.expressions.internal.parser.javacc.ParseException;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedOclExpression;
import nl.klasse.octopus.expressions.internal.parser.parsetree.context.ParsedOclContext;
import nl.klasse.octopus.expressions.internal.parser.parsetree.context.ParsedOclFile;
import nl.klasse.octopus.expressions.internal.types.OclExpression;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.octopus.oclengine.IOclEngine;
import nl.klasse.octopus.oclengine.IOclError;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.octopus.stdlib.internal.types.OclLibraryImpl;
import nl.klasse.tools.common.Check;


/** The OclEngine class keeps global information about the runtime environment
 *  in which the OCL Engine is working. It constains at least a reference
 *  to the OclLibray being used.
 * 
 * @author  Jos Warmer
 * @version $Id: OclEngine.java,v 1.2 2008/01/19 13:31:10 annekekleppe Exp $
 */
public class OclEngine implements IOclEngine {
    /** Keeps the reference to the real OclLibrary object.
     */
    private IOclLibrary oclLib = new OclLibraryImpl();

	static private IOclLibrary currentLib = null;

	/** returns the OclLibrary object that is currently used.
	 * 
	 * @return OclLibrary
	 */
	static public IOclLibrary getCurrentOclLibrary() {
		return currentLib;
	}
    
    /** returns the OclLibrary object that is currently used.
     * 
     * @return OclLibrary
     */
    public IOclLibrary getOclLibrary() {
        return oclLib;
    }

    /** sets the OclLibrary object to be used.
     * 
     * @return OclLibrary
     */
    public void setOclLibrary(IOclLibrary lib) {
        oclLib = lib;
        currentLib = lib;
    }
        
    public OclEngine(){
    	currentLib = oclLib;
    }
    
	/**
	 * Method parseAndAnalyze.
	 * @param exp
	 * @param model
	 * @param context
	 * @param errors: a list of errors found in the input. Each element is of type OclError.
	 * @return OclExpression: the ast build from 'exp'
	 */
    public IOclExpression parseAndAnalyze(String exp, IPackage model, IClassifier contextualType, List<IOclError> errors) {
		if (Check.ENABLED) Check.pre("OclEngine.parseAndAnalyze: The parameter exp may not be null",  			 exp != null);
		if (Check.ENABLED) Check.pre("OclEngine.parseAndAnalyze: The parameter model may not be null", 		 model != null);
		if (Check.ENABLED) Check.pre("OclEngine.parseAndAnalyze: The parameter contextualType may not be null", contextualType != null);
		if (Check.ENABLED) Check.pre("OclEngine.parseAndAnalyze: The parameter errors may not be null", 		 errors != null);

		currentLib = oclLib;
		
    	OclExpression result = null;
    	OclParser parser = createParser(exp, errors); 
    	ParsedOclExpression parsetree = null;
		try {
			parsetree = parser.OclExpression();
		} catch (ParseException e) {
			OclError err = new OclError("",e.currentToken.beginLine, 
			 							e.currentToken.beginColumn,
			 							e.getMessage());
			errors.add(err);
		}

		if (parsetree != null) {
			ExpressionAnalyzer analyzer = new ExpressionAnalyzer("", errors);
			result = analyzer.createAst(parsetree, contextualType);
		}		
    	return result;        }
    
	/**
	 * Method parseAndAnalyze.
	 * @param contextExp: one context declaration and a series of ocl expressions with usage type
	 * @param model
	 * @param errors: a list of errors found in the input. Each element is of type OclError.
	 * @return List: set of IOclContext objects
	 */
    public List<IOclContext> parseAndAnalyze(String contextExp, IPackage model, List<IOclError> errors) {
		if (Check.ENABLED) Check.pre("OclEngine.parseAndAnalyze: The parameter contextExp may not be null", contextExp != null);
		if (Check.ENABLED) Check.pre("OclEngine.parseAndAnalyze: The parameter model may not be null", 	 model != null);
		if (Check.ENABLED) Check.pre("OclEngine.parseAndAnalyze: The parameter errors may not be null", 	 errors != null);
		currentLib = oclLib;
		
    	List<IOclContext> result = new ArrayList<IOclContext>();
		OclParser parser = createParser(contextExp, errors);
    	ParsedOclContext parsetree = null;
		try {
			parsetree = parser.contextDeclaration();
		} catch (ParseException e) {
			OclError err = new OclError("",e.currentToken.beginLine, 
			 							e.currentToken.beginColumn,
			 							e.getMessage());
			errors.add(err);
		}

		if (parsetree != null) {
			ContextAnalyzer anal = new ContextAnalyzer("", errors);
			result = anal.analyzeContext(model, parsetree);
		}
		return result;		
    }

	/**
	 * Method createParser.
	 * @param in
	 * @param errors
	 * @return OclParser
	 */
	private OclParser createParser(String in, List<IOclError> errors) {
		OclParser parser = null;
		try {
		    java.io.StringReader sr = new java.io.StringReader( in );
		    java.io.Reader r = new java.io.BufferedReader( sr );
		    parser = new OclParser ( r );
		} catch(Exception e) {
			OclError err = new OclError("",1,1,"Parse Error: " + e.getMessage());
			errors.add(err);
		} 
		return parser;
	} 
        
	/**
	 * Method parseAndAnalyze.
	 * @param filename: the name of the file that corresponds to 'in'.
	 * @param in
	 * @param model
	 * @param errors: a list of errors found in the input. Each element is of type OclError.
	 * @return List: set of IOclContext objects.
	 */
    public List<IOclContext> parseAndAnalyze(String filename, InputStream in, IPackage model, List<IOclError> errors) {
		if (Check.ENABLED) Check.pre("OclEngine.parseAndAnalyze: The parameter in may not be null", 	in != null);
		if (Check.ENABLED) Check.pre("OclEngine.parseAndAnalyze: The parameter model may not be null",  model != null);
		if (Check.ENABLED) Check.pre("OclEngine.parseAndAnalyze: The parameter errors may not be null", errors != null);
		currentLib = oclLib;
		
    	List<IOclContext> result = new ArrayList<IOclContext>();
    	ParsedOclFile parsetree = null;
		OclParser parser = new OclParser(in);
		try {
			parsetree = parser.oclFile(filename);
		} catch (ParseException e) {
			OclError err = new OclError(filename, e);
			errors.add(err);
		}

		if (parsetree != null) {
			DefinitionAnalyzer defAnal = new DefinitionAnalyzer(filename, errors);
			defAnal.createDefs(parsetree, model);
			ContextAnalyzer anal = new ContextAnalyzer(filename, errors);
			result = anal.analyzeCFile(parsetree, model);
		}
		if (Check.ENABLED) Check.post("OclEngine.parseAndAnalyze: result should contain only OclContext instances",
		                              Check.elementsOfType(result, IOclContext.class));		
    	return result;                                      						
    }

	/**
	 * Method parseAndAnalyze.
	 * @param streams: a List of InputStream objects
	 * @param model
	 * @param errors: a list of errors found in the input. Each element is of type OclError.
	 * @return List: set of IOclContext objects.
	 */
    public List<IOclContext> parseAndAnalyze(List<String> filenames, List<FileInputStream> streams, IPackage model, List<IOclError> errors) {
		if (Check.ENABLED) Check.pre("OclEngine.parseAndAnalyze: The parameter streams may not be null", streams != null);
		if (Check.ENABLED) Check.pre("OclEngine.parseAndAnalyze: The parameter model may not be null",   model != null);
		if (Check.ENABLED) Check.pre("OclEngine.parseAndAnalyze: The parameter errors may not be null",  errors != null);
		if (Check.ENABLED) Check.pre("OclEngine.parseAndAnalyze: The list filenames and the list streams should be of equal length", 
		                              filenames.size() == streams.size());
		currentLib = oclLib;
    	List<IOclContext> result = new ArrayList<IOclContext>();

    	List<ParsedOclFile> trees = new ArrayList<ParsedOclFile>();
		Iterator it = streams.iterator();
		Iterator ns = filenames.iterator();
		while ( it.hasNext() && ns.hasNext()) {
			String filename = (String) ns.next();
			InputStream in = (InputStream) it.next();
	    	ParsedOclFile parsetree = null;
			OclParser parser = new OclParser(in);
			try {
				parsetree = parser.oclFile(filename);
			} catch (ParseException e) {
				OclError err = new OclError(filename, e);
				errors.add(err);
			}
	
			if (parsetree != null) {
				DefinitionAnalyzer defAnal = new DefinitionAnalyzer(parsetree.getFilename(), errors);
				defAnal.createDefs(parsetree, model);
				trees.add(parsetree);
			}
		}
		Iterator treeIt = trees.iterator();
		while ( treeIt.hasNext() ) {
			ParsedOclFile parsetree = (ParsedOclFile) treeIt.next();
			ContextAnalyzer anal = new ContextAnalyzer(parsetree.getFilename(), errors);
			result.addAll(anal.analyzeCFile(parsetree, model));
		}
		if (Check.ENABLED) Check.post("OclEngine.parseAndAnalyze: result should contain only OclContext instances",
		                              Check.elementsOfType(result, IOclContext.class));		
    	return result;                                      						
    }   
}
