/*
 * Created on Dec 13, 2003
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.analysis.expressions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.expressions.internal.analysis.Analyzer;
import nl.klasse.octopus.expressions.internal.analysis.Environment;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedCollectionType;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedSimpleType;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedTupleType;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedType;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedVariableDeclaration;
import nl.klasse.octopus.model.CollectionMetaType;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.ITupleType;
import nl.klasse.octopus.oclengine.IOclError;
import nl.klasse.octopus.oclengine.internal.OclEngine;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.tools.common.Check;


/**
 * TypeAnalyzer : 
 */
public class TypeAnalyzer extends Analyzer {

	/**
	 * 
	 */
	public TypeAnalyzer(String filename, List<IOclError> errors) {
		super(filename, errors);
	}

	/** Analyze the type of a variable
	 * 
	 * @param type         The parsed type to be analyzed
	 * @param env          The environment in which the analysis takes place.
	 * @return IClassifier
	 */
	public IClassifier analyzeType(ParsedType type, Environment env) throws AnalysisException {
		if (Check.ENABLED) Check.pre("ExpressionAnalyzer.analyzeType: type should not be null", type != null);
		if (Check.ENABLED) Check.pre("ExpressionAnalyzer.analyzeType: env should not be null", env != null);
		IClassifier result = null;

		if (type instanceof ParsedSimpleType) {
			ParsedSimpleType simple = (ParsedSimpleType) type;
			result = env.lookupClassifier(simple.getName().toPathName());
			if( result == null ) { // Try the basic types
				result = OclEngine.getCurrentOclLibrary().lookupStandardType(simple.getName().toPathName().getLast());
			}
		} else if (type instanceof ParsedCollectionType) {
			// Find the collection type recursively.
			ParsedCollectionType collType = (ParsedCollectionType)type;
			ParsedType parsedElemType = collType.getElementType();
			IClassifier elementType = analyzeType(parsedElemType, env);
			if( elementType != null) {
				CollectionMetaType metatype = CollectionMetaType.getMetaType(collType.getCollectionName());
				result = OclEngine.getCurrentOclLibrary().lookupCollectionType(metatype, elementType);
			}
		} else if (type instanceof ParsedTupleType) {
			result = analyzeTupleType(type, env);
		} else {
			// This should never happen !!!
			error(type.getStartLine(), type.getStartColumn(),
				  "TypeAnalyzer::analyzeType: Internal error: " + type.getStart().toString() + " is not recognized\n");
			System.out.println(
				  "TypeAnalyzer::analyzeType: Internal error: " + type.getStart().toString() + " is not recognized\n");
		}

		if( result == null ){
			throw new AnalysisException(this.currentFile, type.getStartLine(), type.getStartColumn(),
					                  "Type " + type.toString() + " not found" );
		}
		return result;
	}   
	
	private ITupleType analyzeTupleType(ParsedType type, Environment env) throws AnalysisException 
	{
		ParsedTupleType pTupleType = (ParsedTupleType) type; 
		List<IVariableDeclaration> analyzedParts = new ArrayList<IVariableDeclaration>();
		Iterator it = pTupleType.getParts();
		while (it.hasNext()) {
			ParsedVariableDeclaration pvar  = (ParsedVariableDeclaration)it.next();
//			ParsedType                ptype = pvar.getType();
			IClassifier defaultType = OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclAnyTypeName);
			IVariableDeclaration var = null;
			ExpressionAnalyzer expanalyzer = new ExpressionAnalyzer(currentFile, errors);
			var = expanalyzer.analyzeVariableDeclaration(pvar, env, defaultType);

			if( var.getType() == defaultType ){ 
				// deduce type from init expression
				var.setType( var.getInitExpression().getExpressionType());
			}
			analyzedParts.add(var);
		}
		return OclEngine.getCurrentOclLibrary().lookupTupleType(analyzedParts);
	}


}
