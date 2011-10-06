package nl.klasse.octopus.expressions.internal.analysis.context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.expressionVisitors.AstWalker;
import nl.klasse.octopus.expressions.internal.analysis.Analyzer;
import nl.klasse.octopus.expressions.internal.analysis.Conformance;
import nl.klasse.octopus.expressions.internal.analysis.Environment;
import nl.klasse.octopus.expressions.internal.analysis.expressions.AnalysisException;
import nl.klasse.octopus.expressions.internal.analysis.expressions.ExpressionAnalyzer;
import nl.klasse.octopus.expressions.internal.analysis.expressions.TypeAnalyzer;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedType;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedVariableDeclaration;
import nl.klasse.octopus.expressions.internal.parser.parsetree.context.ParsedOclAttributeContext;
import nl.klasse.octopus.expressions.internal.parser.parsetree.context.ParsedOclClassContext;
import nl.klasse.octopus.expressions.internal.parser.parsetree.context.ParsedOclContext;
import nl.klasse.octopus.expressions.internal.parser.parsetree.context.ParsedOclFile;
import nl.klasse.octopus.expressions.internal.parser.parsetree.context.ParsedOclOperationContext;
import nl.klasse.octopus.expressions.internal.parser.parsetree.context.ParsedOclUsage;
import nl.klasse.octopus.expressions.internal.types.OclExpression;
import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.expressions.internal.types.VariableDeclaration;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IDataType;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.INameSpace;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.model.internal.types.AttributeImpl;
import nl.klasse.octopus.model.internal.types.OperationImpl;
import nl.klasse.octopus.oclengine.IModelElementReference;
import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.octopus.oclengine.IOclError;
import nl.klasse.octopus.oclengine.internal.ModelElementReferenceImpl;
import nl.klasse.octopus.oclengine.internal.OclContextImpl;
import nl.klasse.octopus.oclengine.internal.OclEngine;
import nl.klasse.octopus.oclengine.internal.OclErrContextImpl;
import nl.klasse.octopus.oclengine.internal.OclError;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.octopus.stdlib.internal.types.StdlibClassifier;
import nl.klasse.tools.common.Check;
import nl.klasse.tools.common.Util;


public class ContextAnalyzer extends Analyzer {
	
	public ContextAnalyzer(String filename, List<IOclError> errors) {
		super(filename, errors);
	}
	
	/**
	 * Method analyzeCFile: takes a parsed file containing context expressions and builds OclContext
	 * objects from them.
	 * @param parsedFile: the result of parsing the file containing the context expressions
	 * @param model: the model for which the expressions are defined
	 * @param errors: a list to which all errors encountered can be added. This should not be null.
	 * @return List: set of OclContext objects, for every expression that was not analyzed 
	 * correctly an OclErrContextImpl object is present.
	 */
	public List<IOclContext> analyzeCFile(ParsedOclFile parsedFile, IPackage model) {
		if (Check.ENABLED) Check.pre("ContextAnalyzer.analyzeCFile: parsedFile should not be null", parsedFile != null);
		if (Check.ENABLED) Check.pre("ContextAnalyzer.analyzeCFile: model should not be null", model != null);
		if (Check.ENABLED) Check.pre("ContextAnalyzer.analyzeCFile: errors should not be null", errors != null);
		List<IOclContext> result = new ArrayList<IOclContext>();
		IPackage pack = findPackage(parsedFile, model, errors);
		if (pack != null){
	    	Iterator it = parsedFile.getContents().iterator();
	    	it = parsedFile.getContents().iterator();
	    	while (it.hasNext() ) {
	    		ParsedOclContext elem = (ParsedOclContext) it.next();
				result.addAll(analyzeContext(pack, elem));
	    	}
	    	//START TEST
//			System.out.println(Util.collectionToString(result, StringHelpers.newLine));
//			System.out.println(Util.collectionToString(errors, StringHelpers.newLine));
	       	//END TEST
		}
    	return result;
	}
	
	/**
	 * Method analyzeContext: takes a parsed context and builds a list of OclContext
	 * objects from them. Note that following one context keyword a number of different 
	 * Ocl Expressions may follow, e.g. one after each 'inv' keyword.
	 * @param pack: the directly enclosing package of the context
	 * @param elem: the context to be analyzed
	 * @param errors: a list to which all errors encountered can be added. This should not be null.
	 * @return List: set of OclContext objects, for every expression that was not analyzed 
	 * correctly an OclErrContextImpl object is present.
	 */
	public List<IOclContext> analyzeContext( IPackage pack, ParsedOclContext elem) {
		List<IOclContext> result = new ArrayList<IOclContext>();
		if( elem instanceof ParsedOclClassContext) {
			result = analyzeClassContext(pack, (ParsedOclClassContext) elem, errors);
		} else if ( elem instanceof ParsedOclAttributeContext){
			result = analyzeAttributeContext(pack, (ParsedOclAttributeContext)elem, errors);
		} else if ( elem instanceof ParsedOclOperationContext){
			result = analyzeOperationContext(pack, (ParsedOclOperationContext)elem, errors);
		}
		return result;
	}
	
	/**
	 * Method analyzeClassContext: takes a parsed class context and builds a list of OclContext
	 * objects from them.
	 * @param pack: the directly enclosing package of the context
	 * @param elem: the context to be analyzed
	 * @param errors: a list to which all errors encountered can be added. This should not be null.
	 * @return List: set of OclContext objects, for every expression that was not analyzed 
	 * correctly an OclErrContextImpl object is present.
	 */
	private List<IOclContext> analyzeClassContext(IPackage pack, ParsedOclClassContext elem, List<IOclError> errors) {
		List<IOclContext> result = new ArrayList<IOclContext>();
   		PathName contextName = elem.getReferredElem().toPathName();
		IClassifier foundClass = findClass(pack, elem, contextName, errors);
		if ( foundClass == null) {
			return result;
		} else {
			Iterator defs = elem.getDefinitions();
			while( defs.hasNext() ) {
				ParsedOclUsage use = (ParsedOclUsage)defs.next();
				result.add(analyzeDef(foundClass, use));
			}
			Iterator invs = elem.getInvariants();
			while( invs.hasNext() ) {
				ParsedOclUsage use = (ParsedOclUsage)invs.next();
				IOclContext cont = analyzeExp(foundClass, foundClass, use);
				if (cont != null) result.add(cont);
			}
		}
		return result;
	}

	/**
	 * Method analyzeAttributeContext: takes a parsed attribute context and builds a list of OclContext
	 * objects from them.
	 * @param pack: the directly enclosing package of the context
	 * @param elem: the context to be analyzed
	 * @param errors: a list to which all errors encountered can be added. This should not be null.
	 * @return List: set of OclContext objects, for every expression that was not analyzed 
	 * correctly an OclErrContextImpl object is present.
	*/	
	private List<IOclContext> analyzeAttributeContext(IPackage pack, ParsedOclAttributeContext elem, List<IOclError> errors) {
		List<IOclContext> result = new ArrayList<IOclContext>();
   		PathName contextName = elem.getReferredElem().toPathName();
		if (contextName.isSingleName()){
	   		errors.add( new OclError(currentFile, elem.getStartLine(), elem.getStartColumn(),
	                                 "A single name is not a correct context for an 'init' or 'derive' expression." ));
	    	return result;
		}
		IClassifier foundClass = findClass(pack, elem, contextName.getHead(), errors);
		if ( foundClass == null) {
			errors.add( new OclError(currentFile, elem.getStartLine(), 
								 elem.getStartColumn(),
								 "Attribute context must be prefixed by class." ));
			return result;
		} else {
			IModelElement foundElem = foundClass.lookup( new PathName( contextName.getLast() ));
			if (foundElem == null){
   				errors.add( new OclError(currentFile, elem.getStartLine(), 
   			                         elem.getStartColumn(),
                                     "Cannot find attribute or assocation end '" + contextName.toString() + "'." ));
           	return result;                         
			}
			IClassifier foundType = null;
			if (foundElem instanceof IAssociationEnd) foundType = ((IAssociationEnd) foundElem).getType();
			if (foundElem instanceof IAttribute) foundType = ((IAttribute) foundElem).getType();
			if (foundElem != null && foundType != null) {
				checkDeclaredType(elem, foundClass, foundType, errors);
				// do actual analysis
				Iterator exps = elem.getExpressions();
				while( exps.hasNext() ) {
					ParsedOclUsage use = (ParsedOclUsage)exps.next();
					IOclContext cont = analyzeExp(foundClass, foundElem, use);
					if (cont != null) result.add(cont);
				}
			}
		}			
		return result;
	}

	/**
	 * Method analyzeOperationContext: takes a parsed operation context and builds a list of OclContext
	 * objects from them.
	 * @param pack: the directly enclosing package of the context
	 * @param elem: the context to be analyzed
	 * @param errors: a list to which all errors encountered can be added. This should not be null.
	 * @return List: set of OclContext objects, for every expression that was not analyzed 
	 * correctly an OclErrContextImpl object is present.
	*/	
	private List<IOclContext> analyzeOperationContext(IPackage pack, ParsedOclOperationContext elem, List<IOclError> errors) {
		List<IOclContext> result = new ArrayList<IOclContext>();
   		PathName elemN = elem.getReferredElem().toPathName();
		IClassifier foundClass = findClass(pack, elem, elemN.getHead(), errors);
		if ( foundClass == null) {
			errors.add( new OclError(currentFile, elem.getStartLine(), 
								 elem.getStartColumn(),
								 "Operation context must be prefixed by class." ));
			return result;
		} else {
			List<IClassifier> paramTypes = getTypesFrom(foundClass.getOwner(), foundClass, elem.getPars());
			IClassifier foundReturnType = analyzeType(elem.getReturnType(), foundClass);
			IOperation foundOper = foundClass.findOperation(elemN.getLast(), paramTypes);
			if (foundOper == null) foundOper = foundClass.findClassOperation(elemN.getLast(), paramTypes);
			if (foundReturnType == OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName) )
				foundReturnType = null;
			if (foundOper == null || foundOper.getReturnType() != foundReturnType) {
				String foundReturnTypeString = "";
				if (foundReturnType != null) {
					foundReturnTypeString = ": " + foundReturnType.toString();
				}
	   			errors.add( new OclError(currentFile, elem.getStartLine(), 
	   			                         elem.getStartColumn(),
	                                     "Cannot find operation '" + elemN.toString() +
		                                     "(" + Util.collectionToString(elem.getPars(), ",") + ")" +
		                                      foundReturnTypeString + "'."));
			} else {
				if (Check.ENABLED) checkOwnership(foundClass, foundOper);
				// do actual analysis
				Iterator exps = elem.getExpressions();
				while( exps.hasNext() ) {
					ParsedOclUsage use = (ParsedOclUsage)exps.next();
					IOclContext cont = analyzeExp(foundClass, foundOper, use);
					if (cont != null) result.add(cont);
				}
			}
		}
		return result;
	}

	/**
	 * Method analyzeDef: takes a ParsedOclUsage that holds a 'def' expression and builds an OclContext
	 * object from it.
	 * @param foundClass: the class for which the 'def' element is defined
	 * @param use: the parsed 'def' expression
	 * @param errors: a list to which all errors encountered can be added. This should not be null.
	 * @return OclContext: an OclContext object, if the expression was not analyzed 
	 * correctly an OclErrContextImpl object is returned, otherwise an OclContextImpl is returned.
	 */
	private IOclContext analyzeDef(IClassifier foundClass, ParsedOclUsage use) {
		IOclContext result 		= null;
		OclExpression exp 		= null; 
		IClassifier expectedType = null;
		IAttribute foundAttr 	= null;
		IOperation foundOper 	= null;
		String defElemName 		= "";
		//find defined element
		if (use.getType() == OclUsageType.DEF){
			if (use.getVariable() != null) {
				defElemName = use.getVariable().getName().toString();
				foundAttr = foundClass.findAttribute(defElemName);
			} else if (use.getOperation() != null) {
				DefinitionAnalyzer defAnal = new DefinitionAnalyzer(currentFile, errors);
				IOperation dummy = defAnal.createOperation(use.getOperation(), foundClass, new ArrayList<IOclError>());
				foundOper = foundClass.findOperation(dummy.getName(), dummy.getParamTypes());
				defElemName = dummy.getName();
			}
		}
		// analyze expression and determine expected type of the expression
		List<IOclError> myErrors = new ArrayList<IOclError>();
		ExpressionAnalyzer asc = new ExpressionAnalyzer(currentFile, myErrors);
		if (foundAttr != null) {
			expectedType = foundAttr.getType();
			exp = asc.createAst(use.getExpression(), foundClass);
		} else if (foundOper != null) {
			expectedType = foundOper.getReturnType();
			exp = asc.createPre(use.getExpression(), foundClass, foundOper);
		} else {
	   		myErrors.add( new OclError(currentFile, use.getStartLine(), use.getStartColumn(),
	                                 "Model incorrect: cannot find defined element '" + defElemName + "'." ));
		}		
		if (exp != null) {
			if (!checkType(expectedType, exp.getExpressionType(), use, myErrors)) {
				exp = null; // type of the expression was not correct, cannot return a correct OclContext object	
			}
		}
		// create resulting OclContext
		result = createOclContext(foundClass, foundClass, use, exp, myErrors);
		if (result instanceof OclContextImpl) {
			if (foundAttr != null) {
				((OclContextImpl)result).setDefinedAttribute(foundAttr);
				// create the correct info for foundAttribute
				OclContextImpl init = ((OclContextImpl)result).copyExpOfDef();
				if (foundAttr instanceof AttributeImpl) ((AttributeImpl)foundAttr).setDerivationRule(init);
			} 
			if (foundOper != null) {
				((OclContextImpl)result).setDefinedOperation(foundOper);
				// create the correct info for foundOperation
				OclContextImpl body = ((OclContextImpl)result).copyExpOfDef();
				if (foundOper instanceof OperationImpl) ((OperationImpl)foundOper).setBodyExpression(body);
			} 
		} else if (result instanceof OclErrContextImpl) {
			if (foundAttr != null) {
				((OclErrContextImpl)result).setDefinedAttribute(foundAttr);
			} 
			if (foundOper != null) {
				((OclErrContextImpl)result).setDefinedOperation(foundOper);
			} 
		}
		if (!myErrors.isEmpty()) errors.addAll(myErrors);
		return result;
	}

	/**
	 * Method analyzeExp analyzes the expression in 'use', using 'contextualType' and 'context'.
	 * @param contextualType: the classifier that is or holds the context
	 * @param context: the context of the expression
	 * @param use: the ParsedOclUsage object that holds the expression te be analyzed
	 * @param errors: a list to which all errors encountered can be added. This should not be null.
	 * @return OclContext: an OclContext object, if the expression was not analyzed 
	 * correctly an OclErrContextImpl object is returned, otherwise an OclContextImpl is returned.
	 */
	private IOclContext analyzeExp(IClassifier contextualType, IModelElement context, ParsedOclUsage use) {
		if (Check.ENABLED) Check.pre("ContextAnalyzer.doAnalysis: contextualType should not be null", 
		 							 contextualType != null);
		if (Check.ENABLED) Check.pre("ContextAnalyzer.doAnalysis: context should not be null", 
		 							 context != null);
		if (Check.ENABLED) Check.pre("ContextAnalyzer.doAnalysis: use should not be null", 
		 							 use != null);
		if (Check.ENABLED) Check.pre("ContextAnalyzer.doAnalysis: errors should not be null", 
		 							 errors != null);
		IOclContext result = null;
		List<IOclError> myErrors = new ArrayList<IOclError>();
		if (use.getExpression() == null ) { // found "inv:", or "def:", etc, but no OCL expression
			// TODO create warning when empty expression is found
//			myErrors.add(new OclError(currentFile, use.getStartLine(), use.getStartColumn(),
//			                           "Empty expression: " + use.getName() + " " + use.getType()) );
//			result = createExpHolder(contextualType, context, use, myErrors);
//			if (!myErrors.isEmpty()) errors.addAll(myErrors);
			return result;
		}
		OclExpression exp = null;
		ExpressionAnalyzer asc = new ExpressionAnalyzer(currentFile, myErrors);
		if (context instanceof IOperation){
			if (use.getType() == OclUsageType.PRE)
				exp = asc.createPre(use.getExpression(), contextualType, (IOperation)context );
			if (use.getType() == OclUsageType.POST)
				exp = asc.createPost(use.getExpression(), contextualType, (IOperation)context );
// before change for BodyExpressions
			if (use.getType() == OclUsageType.BODY)
				exp = asc.createPre(use.getExpression(), contextualType, (IOperation)context );
// after change for BodyExpressions
//		   	if (use.getType() == OclUsageType.BODY)
//			   	exp = asc.createPost(use.getExpression(), contextualType, (IOperation)context );
// end change for BodyExpressions
		} else {
			exp = asc.createAst(use.getExpression(), contextualType);
		}
		if (exp != null){
			IClassifier expectedType = determineExpectedType(context, use);
			if (!checkType(expectedType, exp.getExpressionType(), use, myErrors)) {
				exp = null; // type of the expression was not correct, cannot return a correct OclContext object	
			}
			if (exp != null && !checkPostconditions(exp, use, myErrors)) {
				exp = null;
			}
		}
		result = createOclContext(contextualType, context, use, exp, myErrors);
		if (!myErrors.isEmpty()) errors.addAll(myErrors);
		return result;
	}

	/**
	 * @param exp
	 * @param use
	 * @param myErrors
	 * @return
	 */
	private boolean checkPostconditions(OclExpression exp, ParsedOclUsage use, List<IOclError> myErrors) {
		if (use.getType() == OclUsageType.POST) return true;
		boolean result = true;
		AstWalker walker = new AstWalker();
		PostconditionChecker checker = new PostconditionChecker();
		walker.walk(exp, checker);

		Iterator it = checker.getErrors().iterator();
		while (it.hasNext()) {
			OclError err = (OclError) it.next();
			err.setFilename(currentFile);
			err.setLineNumber(use.getStartLine());
			err.setColumnNumber(use.getStartColumn());
			myErrors.add(err);
			result = false;
		} 
		return result;
	}

	/**
	 * Method analyzeType: analyzes the given parsed type.
	 * @param type: the parsed type to be analyzed
	 * @param foundClass: used to build up the environment in which to search the type
	 * @return IClassifier: the found type
	 */
	private IClassifier analyzeType(ParsedType type, IClassifier foundClass) {
		if (type == null) {
			return OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);			
		}
		TypeAnalyzer analyzer = new TypeAnalyzer(currentFile, new ArrayList<IOclError>());
		Environment env  = Environment.createEnvironment(foundClass.getOwner(), foundClass);
		IClassifier t = null;
		try{  
			t = analyzer.analyzeType(type, env);
		} catch(AnalysisException e) {
			errors.add(e.getError());
			// provide a dummy
			t = OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);			
		}
		return t;
	}

/*******************************************************************************************************
 *  Helper methods to check things
 * 
 * *****************************************************************************************************/
	/**
	 * Method checkDeclaredType adds an error to the list 'errors' when the declared type
	 * of an attribute context is not the same as the found type. To enable further checking of 
	 * the expression only an error is produced, nothing else.
	 * @param elem: the attribute context to be checked
	 * @param foundClass: the class which is the owner of the found attribute or association end
	 * @param foundType: the type of the found attribute or association end
	 * @param errors: a list to which all errors encountered can be added. This should not be null.
	 * @return void
	 */
	private void checkDeclaredType(ParsedOclAttributeContext elem, IClassifier foundClass, IClassifier foundType, List<IOclError> errors) {
   		PathName contextName = elem.getReferredElem().toPathName();
		if (elem.getType() != null){
			IClassifier t = analyzeType(elem.getType(), foundClass);
			if( foundType != t){
				if (t == null) {
					t = OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);
				}
				errors.add( new OclError(currentFile, elem.getStartLine(), 
				                         elem.getStartColumn(),
		                                 "Found attribute or association end '" + contextName.toString() + "' with type '" 
		                                 + foundType.toString() + "', declared type is '" + t.toString() + "'."));
			}
		}
	}

	
	/**
	 * Method checkType adds an error to the list 'errors' when the declared type
	 * of an attribute context is not the same as the found type. To enable further checking of 
	 * the expression only an error is produced, nothing else.
	 * @param expectedType: the type that is expected here
	 * @param foundType: the type that is found
	 * @param use: gives the position and so on, of the 'foundType' in the input
	 * @param errors: a list to which all errors encountered can be added. This should not be null.
	 * @return boolean: true if the 'foundType' conforms to the 'expectedType'.
	 */
	private boolean checkType(IClassifier expectedType, IClassifier foundType, ParsedOclUsage use, List<IOclError> errors) {
        if (Check.ENABLED) Check.pre("ContextAnalyzer.checkType: expectedType should not be null", expectedType != null);
        if (Check.ENABLED) Check.pre("ContextAnalyzer.checkType: foundType should not be null", foundType != null);
		if (Conformance.conformsTo(foundType, expectedType)) {
	 		return true;
		} else {
			errors.add(new OclError(currentFile, use.getExpression().getStartLine(), use.getExpression().getStartColumn(),
				"Expression should be of type '" + expectedType.toString() + 
                "', found type '" + foundType.toString() + "'."));
			return false;
        }
	}
			
	/**
	 * Method checkOwnership checks whether 'foundClass' conforms to the owner of 'foundOper' .
	 * @param foundClass: the class that should own or inherit 'foundOper'
	 * @param foundOper: the operation to be checked
	 */
	private void checkOwnership(IClassifier foundClass, IOperation foundOper) {
		String ownerType = "";
		if (foundOper.getOwner() instanceof IClassifier)	{
			ownerType = "IClassifier";
		} else if (foundOper.getOwner() instanceof StdlibClassifier)	{
			ownerType = "StdlibClassifier";
		} else if (foundOper.getOwner() instanceof IDataType)	{
			ownerType = "IDataType";
		}
		Check.isTrue("ContextAnalyzer.analyzeOperationContext: '" + foundOper.getName() + 
		             "' is not a child of '" + foundClass.getName() + "'\n" 
		             + "its owner is '" + foundOper.getOwner() + "' of type '" 
		             + ownerType + "'.", 
		             Conformance.conformsTo(foundClass, foundOper.getOwner()));
	}	


/*******************************************************************************************************
 *  Helper methods to create the resulting OclContext objects
 * 
 * *****************************************************************************************************/

	/**
	 * Method createOclContext creates an OclContext object. If 'exp' is correctly analyzed it will be a genuine
	 * OclExpression instance. In that case the implementing OclContext object will be of type OclContextImpl.
	 * If 'exp' is null, the expression was not correctly analyzed, and the implementing OclContext
	 * object will be of type OclErrContextImpl.
	 * @param context: the context for which the expression is defined
	 * @param use: the ParsedOclUsage object that holds the info on this OclContext. For instance, the type and name.
	 * @param exp: the expression
	 * @param myErrors: the errors that apply to this context only
	 * @return OclContext
	 */
	private IOclContext createOclContext(IClassifier contextualType, IModelElement context, 
										 ParsedOclUsage use, OclExpression exp, List myErrors) {
		boolean okee = true;
		if (exp != null) {
			okee = myErrors.isEmpty();
			if (Check.ENABLED) Check.pre("ContextAnalyzer.createOclContext: if 'exp' is not null, 'myErrors' should be empty\n" 
						+ currentFile + " line " + use.getStartLine(), okee );
		}
		if (exp == null) {
			okee = !myErrors.isEmpty();
			if (Check.ENABLED) Check.pre("ContextAnalyzer.createOclContext: if 'exp' is null, 'myErrors' should not be empty. File: " 
						+ currentFile + " line " + use.getStartLine(), okee );
		}
		IOclContext result = null;
		if (exp != null ) {
			IModelElementReference cont = new ModelElementReferenceImpl(contextualType, context);
			result = new OclContextImpl(use.getName(), use.getType(), cont, exp);
			((OclContextImpl) result).setLineAndColumn( use.getStartLine(), use.getStartColumn() );
			((OclContextImpl) result).setFilename(currentFile);
			((OclContextImpl) result).setExpressionByUser(use.getExpression().toString());
		} else {
			result = createErrContext(contextualType, context, use, myErrors);
		}
		return result;
	}

	/**
	 * Method createExpHolder creates an OclErrContextImpl object for ParsedOclUsage 'use'.
	 * @param context: the context for which the expression is defined
	 * @param use: the ParsedOclUsage object that holds the info on this OclContext. For instance, the type and name.
	 * @param myErrors: the errors that apply to this context only
	 * @return OclContext
	 */
	private IOclContext createErrContext(IClassifier contextualType, IModelElement context, ParsedOclUsage use, List myErrors) {
		IOclContext result;
		OclErrContextImpl holder = new OclErrContextImpl(use.getName(), use.getType(), contextualType, context);
		if ( use.getExpression() != null ) {
			holder.setExpressionString(use.getExpression().toString());
			holder.setParsetree(use.getExpression());
		} else {
			holder.setExpressionString("");			
		}
		holder.setErrors(myErrors);
		holder.setLineAndColumn( use.getStartLine(), use.getStartColumn() );
		holder.setFilename(currentFile);
		result = holder;
		return result;
	}
		
/*******************************************************************************
 * 	Helper methods to find things
 * 
 ********************************************************************************/

	/**
	 * Method findPackage finds the package indicated with the 'package' keyword in 'parsedFile' 
	 * in package 'model'. If the 'package 'keyword was not present 'model' is returned.
	 * @param parsedFile: the parsetree that may contain the package name
	 * @param model: the model to search for the package
	 * @param errors: a list to which all errors encountered can be added. This should not be null.
	 * @return IPackage
	 */
	private IPackage findPackage(ParsedOclFile parsedFile, IPackage model, List<IOclError> errors) {
		if (Check.ENABLED) Check.pre("ContextAnalyzer.findPackage: model may not be null", model != null);
		IPackage result = null;
		if (parsedFile.getPackageName() != null) {
			PathName packN = parsedFile.getPackageName().toPathName();
			IModelElement elem = model.lookup(packN);
			if ( elem == null || !( elem instanceof IPackage) ) {
				errors.add( new OclError(currentFile, parsedFile.getPackageName().getStartLine(), 
				                         parsedFile.getPackageName().getStartColumn(),
		                                 "Cannot find package '" + packN.toString() + "'.") );
				result = model;
			} else {
				result = (IPackage) elem;
			}
		} else {
			result = model;
		}
		return result;
	}
	
	/**
	 * Method findClass finds the class indicated with the 'context' keyword in 'elem' 
	 * in package 'model'. If the class cannot be found, null is returned.
	 * @param model: the directly enclosing package
	 * @param elem: used for line, column etc.
	 * @param className: naam of the class to be found
	 * @param errors: a list to which all errors encountered can be added. This should not be null.
	 * @return IClassifier
	 */
	private IClassifier findClass(IPackage model, ParsedOclContext elem, PathName className, List<IOclError> errors) {
		IClassifier foundClass = null;
		IModelElement modelElement = model.lookup(className);
		if ( modelElement == null || !(modelElement instanceof IClassifier) ) {
    		errors.add( new OclError(currentFile, elem.getStartLine(), elem.getStartColumn(),
									"Cannot find class '" + className.toString()+ "' in package '" 
            						+ model.getPathName().toString() + "' or its subpackages."));
//			printSubPackNames(model);
		} else {
			foundClass = (IClassifier) modelElement;
		}
		return foundClass;
	}
	
	/**
	 * Method printSubPackNames. A testroutine.
	 * @param pack
	 */
//	private void printSubPackNames(IPackage pack) {
//		Iterator it = pack.getSubpackages().iterator();
//		while (it.hasNext()) {
//			IPackage sub = (IPackage) it.next();
//			System.out.println(sub.getPathName().toString() );
//			printSubPackNames(sub);
//			printIncludingClassNames(sub);
//		}
//	}	

	/**
	 * Method printSubPackNames. A testroutine.
	 * @param pack
	 */
//	private void printIncludingClassNames(IPackage pack) {
//		Iterator it = pack.getClassifiers().iterator();
//		while (it.hasNext()) {
//			IClassifier cls = (IClassifier) it.next();
//			System.out.println("\t\t" + cls.getPathName().toString() );
//		}
//	}		
	
	/**
	 * Method getTypesFrom transforms a list of parsed arguments into a list of classifiers.
	 * @param model: the model in which to search for the types
	 * @param owner: used to build the environment
	 * @param pars: the parsed arguments
	 * @return List: contains Classifiers
	 */
	private List<IClassifier> getTypesFrom( INameSpace model, IClassifier owner, List pars ) {
		ArrayList<IClassifier> result = new ArrayList<IClassifier>();
		Iterator it = pars.iterator();
		while( it.hasNext() ) {
			Object temp = it.next();
			if (Check.ENABLED) 
				Check.isTrue("ContextAnalyzer.getTypesFrom: 'pars' should contain VariableDeclarations only", 
							 !(temp instanceof VariableDeclaration));
			ParsedVariableDeclaration elem = (ParsedVariableDeclaration) temp;	
	        Environment env  = Environment.createEnvironment(model, owner);
	        TypeAnalyzer analyzer = new TypeAnalyzer(currentFile, new ArrayList<IOclError>());
	        IClassifier type = null;
			try{
        		type = analyzer.analyzeType(elem.getType(), env);
			} catch(AnalysisException e) {
				errors.add(e.getError());
				// provide a dummy
				type = OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclAnyTypeName);			
			}
			result.add(type);
		}
		return result;	
	}
	
	/**
	 * Method determineExpectedType determines the expected type of an OCL expression based on how it 
	 * is used. For instance, an invariant should always have a Boolean type, and an init expression
	 * should always have the type of its context.
	 * @param context: the context of the expression for which the expected type is determined
	 * @param use: the ParsedOclUsage element that holds the expression
	 * @return IClassifier
	 */
	private IClassifier determineExpectedType(IModelElement context, ParsedOclUsage use) {
		IClassifier expectedType = null;
		if ( use.getType() == OclUsageType.INV ||
			 use.getType() == OclUsageType.PRE ||
			 use.getType() == OclUsageType.POST ) {
			 	expectedType = OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName);
		} else if ( use.getType() == OclUsageType.INIT ||
			 		use.getType() == OclUsageType.DERIVE ) 
		{
		 	if (context instanceof IAttribute) expectedType = ((IAttribute) context).getType();			 	
		 	if (context instanceof IAssociationEnd) expectedType = ((IAssociationEnd) context).getType();	
		} else if ( use.getType() == OclUsageType.BODY ) {
		 	if (context instanceof IOperation) expectedType = ((IOperation) context).getReturnType();			 				
		}
		if (expectedType == null)
		 	expectedType = OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);
		return expectedType;
	}
}
