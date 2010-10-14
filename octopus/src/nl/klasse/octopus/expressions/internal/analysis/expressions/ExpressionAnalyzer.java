/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.analysis.expressions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.octopus.expressions.IPropertyCallExp;
import nl.klasse.octopus.expressions.internal.analysis.Analyzer;
import nl.klasse.octopus.expressions.internal.analysis.Conformance;
import nl.klasse.octopus.expressions.internal.analysis.Environment;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedBracketExp;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedIfExpression;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedLetExp;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedLiteral;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedMessageExp;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedOclExpression;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedPropertyCallExp;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedSimpleType;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedType;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedUnspecifiedValue;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedVariableDeclaration;
import nl.klasse.octopus.expressions.internal.types.IfExp;
import nl.klasse.octopus.expressions.internal.types.LetExp;
import nl.klasse.octopus.expressions.internal.types.OclExpression;
import nl.klasse.octopus.expressions.internal.types.OclMessageExp;
import nl.klasse.octopus.expressions.internal.types.OclTypeLiteralExp;
import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.expressions.internal.types.PropertyCallExp;
import nl.klasse.octopus.expressions.internal.types.UnspecifiedValueExp;
import nl.klasse.octopus.expressions.internal.types.VariableDeclaration;
import nl.klasse.octopus.model.CollectionMetaType;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.ICollectionType;
import nl.klasse.octopus.model.INameSpace;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.oclengine.IOclError;
import nl.klasse.octopus.oclengine.internal.OclEngine;
import nl.klasse.octopus.oclengine.internal.OclError;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.tools.common.Util;


/** The OclAnalyser class analyzes an Ocl parse tree as delivered by the parser.
 * It creates the corresponding OCL Abstract Syntax Tree and/or finds all
 * semantic OCL errors found during anlysis.
 *
 * @author  Jos Warmer
 * @version $Id: ExpressionAnalyzer.java,v 1.2 2008/01/19 13:16:05 annekekleppe Exp $
 */
public class ExpressionAnalyzer extends Analyzer {
	private TryAnalyzer tryer = null;

    public ExpressionAnalyzer(String filename, List<IOclError> errors) {
		super(filename, errors);
		tryer = new TryAnalyzer(currentFile, errors);
    }
   
    public OclExpression createAst(ParsedOclExpression parseTree, IClassifier c) {
    	INameSpace owner = c.getOwner();
        Environment env  = Environment.createEnvironment(owner, c);
		return analyze(parseTree, c, owner, env);
    }
   
    public OclExpression createPre(ParsedOclExpression parseTree, IClassifier c, IOperation op) {
    	INameSpace owner = c.getOwner();
        Environment env  = Environment.createPreEnvironment(owner, c, op);
		return analyze(parseTree, c, owner, env);
    }

    public OclExpression createPost(ParsedOclExpression parseTree, IClassifier c, IOperation op) {
    	INameSpace owner = c.getOwner();
        Environment env  = Environment.createPostEnvironment(owner, c, op);
		return analyze(parseTree, c, owner, env);
    }
    
	private OclExpression analyze(ParsedOclExpression parseTree, IClassifier c, INameSpace owner, Environment env) {
		OclExpression answer;
		try {
			answer = analyzeParsetree(parseTree, c, owner, env);
		} catch (AnalysisException e) {
			errors.add(e.getError());
			answer = null;
		}
		// Make sure we return null in case of errors.
		if( ! errors.isEmpty() ) {
			answer = null;
		}
		return answer;
	}
	    	
    /** analyzeParsetree analyzes an Ocl parse tree as delivered by the parser.
     *  It creates the corresponding OCL Abstract Syntax Tree and/or finds all
     *  semantic OCL errors in the parse tree. If there are semantic errors the
     *  resulting AST is not complete and/or correct.
     * 
     * @param tree The parse tree to be analyzed
     * @param contextualType The contextual classifier within whose context the OCL expresion is to be analyzed.
     * @param p The package to which the contextual classifier belongs.
     * @param env The environment in which the parse tree is analyzed.
     * @return Result containing all the analysis results in String form.
     */
    public OclExpression analyzeParsetree(ParsedOclExpression tree, IClassifier contextualType, 
    		INameSpace p, Environment env) throws AnalysisException {
        OclExpression result;
        // First make sure the priorities of the binary operators are handled.
        tree.arrangeOperators();
        tree.applyPriority();
        // Find an OclMEssageExp
        // Analyze the first element in the parse tree
        if( tree instanceof ParsedLiteral ){
        	LiteralExpAnalyzer litanalyzer = new LiteralExpAnalyzer( currentFile, errors );
            result = litanalyzer.analyzeLiteral( (ParsedLiteral)tree, contextualType, p, env ) ;
		} else if( tree instanceof ParsedUnspecifiedValue ){
			result = analyzeUnspecifiedValue( (ParsedUnspecifiedValue)tree, contextualType, p, env ) ;
		} else if( tree instanceof ParsedPropertyCallExp ){
			result = analyzeInitialPropertyCallExp( (ParsedPropertyCallExp)tree, contextualType, p, env ) ;
        } else if( tree instanceof ParsedBracketExp){
			result = analyzeParsetree(((ParsedBracketExp)tree).getExp(), contextualType, p, env);
        } else if( tree instanceof ParsedLetExp ){
            result = analyzeLetExp( (ParsedLetExp)tree, contextualType, p, env ) ;
		} else if( tree instanceof ParsedIfExpression ){
			result = analyzeIfExpression( (ParsedIfExpression)tree, contextualType, p, env ) ;
		} else if( tree instanceof ParsedMessageExp ){
			result = analyzeMessageExp( (ParsedMessageExp)tree, contextualType, p, env ) ;
		/* begin bodyExpression additions */
//		} else if( tree instanceof ParsedBodyExpression ){
//			BodyExpAnalyzer bodyanalyzer = new BodyExpAnalyzer( currentFile, errors );
//			result = bodyanalyzer.analyzeBodyExpression( (ParsedBodyExpression)tree, contextualType, p, env ) ;
		/* end bodyExpression additions */
        } else {
            error(tree.getStartLine(), tree.getStartColumn(),
                      "INTERNAL ERROR analyzeParseTree: Incorrect type of parse tree: " + tree);
            result = null;
        }
        if( result == null ) { return result; }

        // analyze applied properties to the first element and further on until
        // the end of the expression is reached or an error has occurred.
		analyzeAppliedProperties(tree, p, env, result);
        return result;
    }

	private void analyzeAppliedProperties(ParsedOclExpression tree, INameSpace p,
										  Environment env, OclExpression source) throws AnalysisException {
		ParsedPropertyCallExp next     = tree.getAppliedProperty();
		PropertyCallExp       lastCall = source.getLastAppliedProperty();
		IClassifier           lastType = null;
		if (source instanceof OclTypeLiteralExp) {
			// TODO should be TryAnalyzer.getCorrectReturnType
			lastType = ((OclTypeLiteralExp)source).getReferredClassifier();
		} else {
			lastType = source.getExpressionType(); 
		} 
		while( !((next == null) || (lastType == null)) ){
		    PropertyCallExp nextResult = analyzePropertyCallExp(next, lastType, p, env);
			if( nextResult instanceof IPropertyCallExp ){
				((PropertyCallExp)nextResult ).setMarkedPre( next.isMarkedPre());
			}

		    if( lastCall == null ){
		    	// result is variable declaration, literal, let expresssion, or if expression
		        source.setAppliedProperty(nextResult);
		    } else {
		        lastCall.setAppliedProperty(nextResult);
		    }
		
		    lastCall = source.getLastAppliedProperty();
		    lastType = (lastCall == null ? null : lastCall.getNodeType() );
		    next     = next.getAppliedProperty();
		}
	}

	/** Analyzes an OclMEssageExp, which is either a ^ or a ^^ in the concrete syntax.
	 * @param exp
	 * @param contextualType
	 * @param p
	 * @param env
	 * @return
	 */
	private OclMessageExp analyzeMessageExp(ParsedMessageExp tree, IClassifier contextualType, INameSpace p, Environment env) 
																										throws AnalysisException
	{
		OclMessageExp result = null;
		OclExpression target = analyzeParsetree(tree.getTarget(), contextualType, p, env);

		List<IOclExpression>	analyzedArgs 	= new ArrayList<IOclExpression>();
		Iterator      			parsedargs   	= tree.getArguments().iterator();
		OclExpression 			argExp       	= null;

		while( parsedargs.hasNext() ){
			ParsedOclExpression arg = (ParsedOclExpression)parsedargs.next();
			ExpressionAnalyzer analyzer = new ExpressionAnalyzer(currentFile, errors);
			argExp = analyzer.analyzeParsetree(arg, null, p, env);
			analyzedArgs.add(argExp);
		}
		TryAnalyzer tryer = new TryAnalyzer(currentFile, errors);
		List<IClassifier> types = tryer.getTypeList(analyzedArgs);
            
		PathName   pathname = tree.getPropertyName().toPathName();
		String     name     = pathname.getLast();
		IOperation op       = target.getExpressionType().findOperation(name, types);
		
		if( op == null ) {
			throw new AnalysisException(new OclError(currentFile, tree.getStartLine(), tree.getStartColumn(),
					  "Type " + target.getExpressionType().getName() + " has no operation " + name + "(" +
					  Util.collectionToString(types, ", ") + ")"));
		} else {
//			System.err.println("AnaluzeMEssageExp: found operation " + name + "(" +
//								Util.collectionToString(types, ", ") + ")");
		}
		result = new OclMessageExp();
		result.setTarget(target);
		result.setCalledOperation(op);
		result.setArguments(analyzedArgs);
		if( (tree.getMessageKind() == ParsedMessageExp.DAKJE) ){
			result.setNodeType(OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName));
		} else if (tree.getMessageKind() == ParsedMessageExp.DOUBLE_DAKJE) {
			IClassifier messageType = OclEngine.getCurrentOclLibrary().lookupOclMessageType(op);			
			result.setNodeType( OclEngine.getCurrentOclLibrary().lookupCollectionType(CollectionMetaType.ORDEREDSET, messageType));			
		} else {
			System.err.println("Internal error in ExpressionAnalyzer.analyzeMessageExp");
		}
		return result;
	}

    /**
	 * @param exp
	 * @param contextualType
	 * @param p
	 * @param env
	 * @return
	 */
	private OclExpression analyzeUnspecifiedValue(ParsedUnspecifiedValue tree, IClassifier contextualType, INameSpace p, Environment env) {
		UnspecifiedValueExp ast = new UnspecifiedValueExp("?");
//		OclMessageArg ast = new OclMessageArg("?") ;
		if (tree.getTypeName() != null) {
			IClassifier type = null;
			TypeAnalyzer typeanalyzer = new TypeAnalyzer(currentFile, errors);
			try{
				type = typeanalyzer.analyzeType( new ParsedSimpleType(tree.getTypeName()), env);
			} catch(AnalysisException e) {
				errors.add(e.getError());
				type = OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);			
			}
			ast.setNodeType(type);
		} else {
			ast.setNodeType(OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclAnyTypeName)); 
		}
		return ast;
	}
    
    /** Create AST from Parse tree for If Expression
     **/
    private IfExp analyzeIfExpression(ParsedIfExpression tree,IClassifier owner, INameSpace p,
                                       Environment env) throws AnalysisException {
        IfExp ast = null;
        OclExpression ifPart   = analyzeParsetree(tree.getIfExp  (), owner, p, env);
        OclExpression thenPart = analyzeParsetree(tree.getThenExp(), owner, p, env);
        OclExpression elsePart = analyzeParsetree(tree.getElseExp(), owner, p, env);
		// Check whether the if part is of boolean type
		IClassifier bool = OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName);
		if( ! ifPart.getExpressionType().conformsTo(bool) ) {
			throw new AnalysisException(new OclError(currentFile, tree.getStartLine(), tree.getStartColumn(),
					  "The condition of the if expression is not a boolean."));
		}
		IClassifier commonSuperType = thenPart.getExpressionType().findCommonSuperType(elsePart.getExpressionType() );
		if ( commonSuperType == null ) {
			commonSuperType = OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclAnyTypeName);
			throw new AnalysisException(new OclError(currentFile, tree.getStartLine(), tree.getStartColumn(),
					  "The types of the else-part and the then-part of the if expression do not conform."));
		}
        ast = new IfExp(ifPart, thenPart, elsePart);
        return ast;
    }

    /** Create AST from Parse tree for let expression.
     * One parsed let expression becomes a nested AST, because a LetExp in the
     * AST can only have one variable, while the parse tree may hold many.
     **/
    private LetExp analyzeLetExp(ParsedLetExp tree,IClassifier owner, INameSpace p,
                                   Environment env) throws AnalysisException {
        LetExp let  = null;
        LetExp tail = null;
        Environment child = new Environment();
        child.setParent(env);
        Iterator vars = tree.getVariables();
        while( vars.hasNext() ){
            ParsedVariableDeclaration pvar = (ParsedVariableDeclaration) vars.next();
			VariableDeclaration       var  = analyzeVariableDeclaration(pvar, env, null);
			env.addElement(var.getName(), var, false);
            // Check for mandatory initExpression
            if( var.getInitExpression() == null ) {
                throw new AnalysisException(new OclError(currentFile, tree.getStartLine(), tree.getStartColumn(),
                          "A variable defined by a let must have an initialization expression"));
            }
            // Check for mandatory declared type
            if( var.getType() == null ) {
                throw new AnalysisException(new OclError(currentFile, tree.getStartLine(), tree.getStartColumn(),
                          "A variable defined by a let must have a defined type"));
            }

            LetExp tmp = new LetExp();
            tmp.setVariable(var);
            if( let == null ) {
                let = tmp;
            } else {
                tail.setIn(tmp);
            }
            tail = tmp;
        }
        OclExpression in = analyzeParsetree(tree.getIn(), owner, p, child);
        tail.setIn(in);
        return let;
    }

    /** Create the AST for a ParsedVariableDeclaration. Never returns null.
     *  The type of the returned VariableDeclaration is OclVoid if there is no default
     *  type and the declared type cannot be found.
     *
     * @param pvar
     * @param p
     * @param env
     * @param defaultType This is the default type of the varaible in case that the
     *        parsed type is not available. If the defaultType is null, absense of the
     *        parsed type is an error.
     * @return VariableDeclaration
     */
    public VariableDeclaration analyzeVariableDeclaration( ParsedVariableDeclaration pvar, Environment env,
                                                            IClassifier defaultType) throws AnalysisException {
        VariableDeclaration result  = null;
        IClassifier          varType = null;
		String              varName = pvar.getName().toString();
		ParsedType          type    = (ParsedType)pvar.getType();

        if( (type == null) && (defaultType == null) ){
            varType = OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);
            result  = new VariableDeclaration(varName, varType);
            throw new AnalysisException(currentFile, pvar.getStartLine(), pvar.getStartColumn(), 
                  "Variable '" + varName + "' must have a declared type\n");
        } else { 
            if( (type == null) && (defaultType != null) ){
                varType = defaultType;
            } else {
            	TypeAnalyzer typeanalyzer = new TypeAnalyzer(currentFile, errors);
				try{
					varType = typeanalyzer.analyzeType(type, env);
    			} catch(AnalysisException e) {
					errors.add(e.getError());
					// provide a dummy
					varType = OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);			
				}
	         }
            result = new VariableDeclaration(varName, varType);
        }
        // Analyze the init expression if it is there.
        if( pvar.getInitExpression() != null ){
            OclExpression init = analyzeParsetree(pvar.getInitExpression(), null, null, env);
            result.setInitExpression(init);
            // the type of the init expression should conform to the type of the variable.
            if( ! Conformance.conformsTo(init.getExpressionType(), varType ) ){
                throw new AnalysisException(currentFile,  pvar.getStartLine(), pvar.getStartColumn(),
                    "Type " + init.getExpressionType().getName() +
                    " of init expression of variable '" + varName +
                    "' does not conform to type '" + varType.getName() + "'\n");                
            }
        }
		return result;
	}

    private OclExpression analyzeInitialPropertyCallExp(ParsedPropertyCallExp tree,
                                                 IClassifier owner,
                                                 INameSpace p,
                                                 Environment env) throws AnalysisException {
        OclExpression ast  = null;
        PathName      name = tree.getPropertyName().toPathName();
        if( tree.hasBrackets() ) {
            if( name.isSingleName() ) {
            	// must be an implicit operation
            	ast = tryer.tryToMatchImplicitOperation(tree, p, env, name);
			} else {
				// not a basename, must be a class operation
				ast = tryer.tryToMatchInitialClassOperation(tree, p, env, name);
			}
			if( ast == null ){
			    throw new AnalysisException(currentFile, tree.getStartLine(), tree.getStartColumn(),
	    	       "No property '" + name + "(" + Util.collectionToString(tree.getArguments(), ",") + ")' found.");
			}
        } else {
        	if( name.isSingleName() ) {
        		// Must be a variable, a classname, an implicit attribute or association end
				ast = tryer.tryToMatchInitialSimpleName(tree, p, env, name); 
        	} else {
        		// must be a classname, enumeration literal, class attribute
				ast = tryer.tryToMatchInitialPathName(env, tree);
        	}
			if( ast == null ){
				if (name.toString().equals("result")) {
					throw new AnalysisException(currentFile, tree.getStartLine(), tree.getStartColumn(),
					   "'result' may be used only in postconditions of operations that have a return type.");
				}
			    throw new AnalysisException(currentFile, tree.getStartLine(), tree.getStartColumn(),
			    	       "No property '" + name + "' found.");
			}			
        }
        if( ast instanceof IPropertyCallExp ){
        	((PropertyCallExp)ast).setMarkedPre( tree.isMarkedPre());
        } else if( ast.getAppliedProperty() != null ){
			ast.getAppliedProperty().setMarkedPre( tree.isMarkedPre());
        }
		return ast;
   }

    
    /** Tries to match in order:
     * <OL>
     * <LI>attribute (of sourceType)
     * <LI>navigation (of sourceType)
     * <LI>operation (of sourceType)
     * <LI>iterator (if sourceType is a collection type)
     * <LI>collection operation on single object
     * </OL>
     * Otherwise returns null
     */
    private PropertyCallExp  analyzePropertyCallExp(ParsedPropertyCallExp tree,
                                          IClassifier sourceType, INameSpace p,
                                          Environment env) throws AnalysisException {
        PropertyCallExp result   = null;
        PathName        pathname = tree.getPropertyName().toPathName();
        String          name     = pathname.getLast();

        // check whether name is simple name
        if( !pathname.isSingleName() ){
            throw new AnalysisException(currentFile,  tree.getStartLine(), tree.getStartColumn(),
                        "The name '" + pathname.toString() + "' cannot be a pathname");
        }
		// No brackets ==> attribute or association-end
        if( ! tree.hasBrackets() ){
            result = tryer.tryToMatchAsAttribute(tree, sourceType, name);
            if( result != null ) { return result; }
    
            result = tryer.tryToMatchAsAssociationEnd(tree, sourceType, name);
            if( result != null ) { return result; }
 
			result = tryer.tryToMatchAsAssociationClass(tree, sourceType, name);
			if( result != null ) { return result; }
         }
		// Operators: +, -, *, /, =, or, etc.
		// and 
        if( tree.hasBrackets() || (tree.getPropertyKind() == ParsedPropertyCallExp.BINARY_OPERATOR) ){
            result = tryer.tryToMatchAsOperation(tree, sourceType, p, env, name);
            if( result != null ) { return result; }
        }
		// TODO this works, but a cleaner solution would be nice.
		// Unary operators
        if( (name.equals("not")) || name.equals("-") ){
            result = tryer.tryToMatchAsOperation(tree, sourceType, p, env, name);
            if( result != null ) { return result; }
        }
		// Operation calls for collections: ->
        if( tree.hasBrackets() && (tree.getPropertyKind() == ParsedPropertyCallExp.ARROW_CALL) ) {
           	result = tryer.tryToAnalyzeLoopExp(sourceType, env, tree, p);
            if( result != null ) { return result; }

            // Usage of single object as collection
            result = tryer.tryToMatchObjectAsSet(tree, sourceType, name, env , p);
            if( result != null ) { return result; }
            
        }

		// Now test for implicit collect
		result = tryer.tryToMatchAsImplicitCollect(tree, sourceType, p, env, name);
        if( result != null ) { return result; }

        // Finally if nothing has been found ... create a full eror message.
        if( result == null ){
			List<IClassifier> typeList = new ArrayList<IClassifier>(1);
			if( sourceType.isCollectionKind() &&  (tree.getPropertyKind() != ParsedPropertyCallExp.ARROW_CALL) 
				&& (tree.getPropertyKind() != ParsedPropertyCallExp.BINARY_OPERATOR)){
				throw new AnalysisException(currentFile, tree.getStartLine(), tree.getStartColumn(),
						"To call the operation or iterator '" + name + "' on " + sourceType.getName() + " an arrow (->) must be used, " +
						" or '" + name + "' must be a property of '" + ((ICollectionType) sourceType).getElementType().getName() + "'");
			}
			if( tree.hasBrackets() || (tree.getPropertyKind() == ParsedPropertyCallExp.BINARY_OPERATOR)) {
				List<IOclExpression> args = tryer.analyzeArguments(tree, sourceType, p, env);
				typeList = tryer.getTypeList(args);
			}
			if( sourceType.isCollectionKind() ){
				throw new AnalysisException(currentFile, tree.getStartLine(), tree.getStartColumn(),
						"Collectiontype " + sourceType.getName() + " has no operation or iterator " + name  
						+ (tree.hasBrackets() || (tree.getPropertyKind() == ParsedPropertyCallExp.BINARY_OPERATOR) ?
						   "(" + Util.collectionToString(typeList, ", ") + ")" : ""));
			} else {
				throw new AnalysisException(currentFile, tree.getStartLine(), tree.getStartColumn(),
                    "Type " + sourceType.getName() + " has no property " + name  
                    + (tree.hasBrackets() || (tree.getPropertyKind() == ParsedPropertyCallExp.BINARY_OPERATOR) ?
                       "(" + Util.collectionToString(typeList, ", ") + ")" : ""));
			}
        }
		if( result instanceof IPropertyCallExp ){
			((PropertyCallExp)result).setMarkedPre( tree.isMarkedPre());
		}

        return result;
    }

}