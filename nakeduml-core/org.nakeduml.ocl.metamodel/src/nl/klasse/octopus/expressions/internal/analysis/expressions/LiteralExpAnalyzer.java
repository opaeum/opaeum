/*
 * Created on Dec 13, 2003
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.analysis.expressions;

import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.expressions.internal.analysis.Analyzer;
import nl.klasse.octopus.expressions.internal.analysis.Environment;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedBooleanLiteral;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedCollectionLiteral;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedLiteral;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedNumericLiteral;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedOclExpression;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedOclUndefinedLiteral;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedRange;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedStringLiteral;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedTupleLiteral;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedVariableDeclaration;
import nl.klasse.octopus.expressions.internal.types.BooleanLiteralExp;
import nl.klasse.octopus.expressions.internal.types.CollectionItem;
import nl.klasse.octopus.expressions.internal.types.CollectionLiteralExp;
import nl.klasse.octopus.expressions.internal.types.CollectionRange;
import nl.klasse.octopus.expressions.internal.types.IntegerLiteralExp;
import nl.klasse.octopus.expressions.internal.types.OclExpression;
import nl.klasse.octopus.expressions.internal.types.OclUndefinedLiteralExp;
import nl.klasse.octopus.expressions.internal.types.RealLiteralExp;
import nl.klasse.octopus.expressions.internal.types.StringLiteralExp;
import nl.klasse.octopus.expressions.internal.types.TupleLiteralExp;
import nl.klasse.octopus.expressions.internal.types.VariableDeclaration;
import nl.klasse.octopus.model.CollectionMetaType;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.INameSpace;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.oclengine.IOclError;
import nl.klasse.octopus.oclengine.internal.OclEngine;
import nl.klasse.octopus.oclengine.internal.OclError;
import nl.klasse.octopus.stdlib.IOclLibrary;


/**
 * LiteralExpAnalyzer : 
 */
public class LiteralExpAnalyzer extends Analyzer {
	/**
	 * 
	 */
	public LiteralExpAnalyzer(String filename, List<IOclError> errors) {
		super(filename, errors);
	}


	/** Create AST from Parse tree for Literals:
	 *  Boolean, Integer, Real, String, Tuple or Collection literals.
	 **/
	public OclExpression analyzeLiteral(ParsedLiteral tree,IClassifier owner, 
			INameSpace p, Environment env) throws AnalysisException {
		OclExpression result = null;
		if( tree instanceof ParsedNumericLiteral ){
			result = analyzeNumericLiteral( (ParsedNumericLiteral)tree, owner, p ) ;
		} else if( tree instanceof ParsedStringLiteral ){
			result = analyzeStringLiteral( (ParsedStringLiteral)tree, owner, p ) ;
		} else if( tree instanceof ParsedBooleanLiteral ){
			result = analyzeBooleanLiteral( (ParsedBooleanLiteral)tree, owner, p ) ;
		} else if( tree instanceof ParsedOclUndefinedLiteral ){
			result = analyzeOclUndefinedLiteral( (ParsedOclUndefinedLiteral)tree, owner, p ) ;
		} else if( tree instanceof ParsedTupleLiteral ){
			result = analyzeTupleLiteral( (ParsedTupleLiteral)tree, owner, p, env ) ;
		} else if( tree instanceof ParsedCollectionLiteral ){
			result = analyzeCollectionLiteral( (ParsedCollectionLiteral)tree, owner, p, env ) ;
		} else {
			error(tree.getStartLine(), tree.getStartColumn(),
				  "INTERNAL ERROR : analyzeLiteral: incorrect literal type: " + tree);
			result = null;
		}
		return result;
	}
	/** Create AST from Parse tree for Numeric Literal
	 * 
	 **/
	private OclExpression analyzeNumericLiteral(ParsedNumericLiteral tree, IClassifier owner, INameSpace p) {
		OclExpression ast = null;
		String str = tree.getSymbol();
		if (str.indexOf(".") == -1) {
			ast = new IntegerLiteralExp(str);
		} else {
			ast = new RealLiteralExp(str);
		}
		return ast;
	}

	/** 
	 * @param tree
	 * @param owner
	 * @param p
	 * @return OclExpression
	 */
	private BooleanLiteralExp analyzeBooleanLiteral(ParsedBooleanLiteral tree,IClassifier owner, INameSpace p) {
		BooleanLiteralExp ast = new BooleanLiteralExp (tree.getSymbol());
		return ast;
	}

	/** 
	 * @param tree
	 * @param owner
	 * @param p
	 * @return OclExpression
	 */
	private OclUndefinedLiteralExp analyzeOclUndefinedLiteral(ParsedOclUndefinedLiteral tree,IClassifier owner, INameSpace p) {
		OclUndefinedLiteralExp ast = new OclUndefinedLiteralExp(tree.getSymbol());
		return ast;
	}

	/** Create AST from Parse tree for String Literal
	 **/
	private OclExpression analyzeStringLiteral(ParsedStringLiteral tree,IClassifier owner, INameSpace p) {
		StringLiteralExp ast = new StringLiteralExp(tree.getSymbol()) ;
		return ast;
	}

	/** Create AST from Parse tree for Tuple Literal
	 **/
	private OclExpression analyzeTupleLiteral(ParsedTupleLiteral tree, IClassifier  owner,
			INameSpace           p   , Environment env   ) throws  AnalysisException {
		TupleLiteralExp ast = new TupleLiteralExp() ;
		Iterator vars = tree.getVariables();
		IClassifier defaultType = OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclAnyTypeName);
		while( vars.hasNext() ){
			ParsedVariableDeclaration pvar = (ParsedVariableDeclaration) vars.next();
			// Check for mandatory initExpression
			if( pvar.getInitExpression() == null ) {
				throw new AnalysisException(new OclError(currentFile, tree.getStartLine(), tree.getStartColumn(),
						  "A variable defined in a Tuple literal must have an initialization expression"));
			}
			// Check for mandatory declared type
			if( pvar.getType() == null ) {
				throw new AnalysisException(new OclError(currentFile, tree.getStartLine(), tree.getStartColumn(),
						  "A variable defined in a Tuple literal must have a defined type"));
			}
			ExpressionAnalyzer expanalyzer = new ExpressionAnalyzer(currentFile, errors);
			VariableDeclaration       var  = expanalyzer.analyzeVariableDeclaration(pvar, env, defaultType);
			ast.addTuplePart(var);
			if( var.getType() == OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclAnyTypeName) ){ 
				// deduce type from init expression
				var.setType( var.getInitExpression().getExpressionType());
			}
		}
		return ast;
	}

	/** Create AST from Parse tree for Collection Literal
	 **/
	private OclExpression analyzeCollectionLiteral(ParsedCollectionLiteral tree, IClassifier  owner,
												   INameSpace p, Environment env   )  throws AnalysisException {
		CollectionLiteralExp ast = new CollectionLiteralExp();
		IClassifier elementType = null;
		// see if there are any collection elements present in the parse tree
		Iterator parts = tree.getParts().iterator();
		while( parts.hasNext() ){
			Object parsedExp = parts.next();
			if( parsedExp instanceof ParsedRange ) {
				CollectionRange r = analyzeCollectionRange((ParsedRange)parsedExp, owner, p, env);
				elementType = OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName);
				ast.addPart(r);
			} else if (parsedExp instanceof ParsedOclExpression ) {
				ExpressionAnalyzer expanalyzer = new ExpressionAnalyzer(currentFile, errors);
				OclExpression exp = expanalyzer.analyzeParsetree((ParsedOclExpression)parsedExp, owner, p, env);
				if (elementType == null) {
					elementType = exp.getExpressionType();
				} else {
					// check whether all elements have the same type
					if (elementType != exp.getExpressionType()) {
						elementType = elementType.findCommonSuperType(exp.getExpressionType());
					}
					if (elementType == null) {
							throw new AnalysisException(new OclError(currentFile, tree.getStartLine(), tree.getStartColumn(),
							  "All parts of a collection literal should have the same (super)type."));
					}
				}
				CollectionItem part = new CollectionItem();
				part.setItem(exp);
				ast.addPart(part);
			} else {
				throw new AnalysisException(new OclError(currentFile, tree.getStartLine(), tree.getStartColumn(),
					  "Collection literal should hold either an integer range, or a list of valid expressions."));
			}
		}
		if ( elementType == null ) elementType = OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);
		// find out which type of collection this is
		CollectionMetaType metatype = CollectionMetaType.getMetaType(tree.getSymbol());
		ast.setNodeType(OclEngine.getCurrentOclLibrary().lookupCollectionType(metatype, elementType));
		return ast;
	}

	/** Create AST from Parse tree for Collection Range
	 **/
	private CollectionRange analyzeCollectionRange(ParsedRange tree, IClassifier  owner,
												   INameSpace p, Environment env   ) throws AnalysisException {
		CollectionRange ast = new CollectionRange();
		ExpressionAnalyzer expanalyzer = new ExpressionAnalyzer(currentFile, errors);
		OclExpression lower = expanalyzer.analyzeParsetree((ParsedOclExpression)tree.getLower(), owner, p, env);
		OclExpression upper = expanalyzer.analyzeParsetree((ParsedOclExpression)tree.getUpper(), owner, p, env);
		ast.setFirst(lower);
		ast.setLast(upper);
		return ast;
	}
    
}
