/*
 * Created on Dec 13, 2003
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.analysis.expressions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.octopus.expressions.IUnspecifiedValueExp;
import nl.klasse.octopus.expressions.internal.analysis.Analyzer;
import nl.klasse.octopus.expressions.internal.analysis.Conformance;
import nl.klasse.octopus.expressions.internal.analysis.Environment;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedOclExpression;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedPropertyCallExp;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedVariableDeclaration;
import nl.klasse.octopus.expressions.internal.types.AssociationClassCallExp;
import nl.klasse.octopus.expressions.internal.types.AssociationEndCallExp;
import nl.klasse.octopus.expressions.internal.types.AttributeCallExp;
import nl.klasse.octopus.expressions.internal.types.EnumLiteralExp;
import nl.klasse.octopus.expressions.internal.types.IterateExp;
import nl.klasse.octopus.expressions.internal.types.IteratorExp;
import nl.klasse.octopus.expressions.internal.types.LoopExp;
import nl.klasse.octopus.expressions.internal.types.OclExpression;
import nl.klasse.octopus.expressions.internal.types.OclStateLiteralExp;
import nl.klasse.octopus.expressions.internal.types.OclTypeLiteralExp;
import nl.klasse.octopus.expressions.internal.types.OperationCallExp;
import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.expressions.internal.types.PropertyCallExp;
import nl.klasse.octopus.expressions.internal.types.VariableDeclaration;
import nl.klasse.octopus.expressions.internal.types.VariableExp;
import nl.klasse.octopus.model.CollectionMetaType;
import nl.klasse.octopus.model.IAssociation;
import nl.klasse.octopus.model.IAssociationClass;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.ICollectionType;
import nl.klasse.octopus.model.IDataType;
import nl.klasse.octopus.model.IEnumLiteral;
import nl.klasse.octopus.model.IInterface;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.INameSpace;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IState;
import nl.klasse.octopus.model.ITupleType;
import nl.klasse.octopus.oclengine.IOclError;
import nl.klasse.octopus.oclengine.internal.OclEngine;
import nl.klasse.octopus.oclengine.internal.OclError;
import nl.klasse.octopus.stdlib.IOclIterator;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.octopus.stdlib.internal.types.OclIterator;
import nl.klasse.tools.common.Check;


/**
 * TryAnalyzer : 
 */
public class TryAnalyzer extends Analyzer {
	private static int uniqueNumber = 1;

	/**
	 * 
	 */
	public TryAnalyzer(String filename, List<IOclError> errors) {
		super(filename, errors);
	}

	public PropertyCallExp tryToMatchAsImplicitCollect(ParsedPropertyCallExp tree,	IClassifier sourceType,
														INameSpace        p,      Environment env,
														String name) throws AnalysisException
	{
		PropertyCallExp result = null;
		if( (tree.getPropertyKind() == ParsedPropertyCallExp.DOT_CALL) 
			&&
			(sourceType.isCollectionKind()) )
		{
			IClassifier elem = ((ICollectionType) sourceType).getElementType();
		
			// Try to match any property possible
			PropertyCallExp exp = null;
			exp = tryToMatchAsAttribute      (tree, elem, name);
			if( exp == null ) { exp = tryToMatchAsAssociationEnd (tree, elem, name); }
			if( exp == null ) { exp = tryToMatchAsOperation      (tree, elem, p, env, name); }
			// Property found, so continue to build the collect.
			if( exp != null) {
				IClassifier baseType = exp.getNodeType();
				OclIterator collect = OclIterator.getOclIterator("collect");
				IClassifier resultType =
					collect.getReturnType((ICollectionType)sourceType, baseType );		
				result = new IteratorExp();
				((IteratorExp) result).setReferredIterator(collect);
				result.setNodeType(resultType);
				result.setName("collect");
				result.setImplicit(true);
				// add the implicit iterator variable to the collect and analyze
				// the attribute/operation again inside the collect.
				Environment collectEnv = new Environment();
				collectEnv.setParent(env);
				String varName = "i_" + elem.getName();
				VariableDeclaration iter = new VariableDeclaration(varName, elem);
				collectEnv.addElement(varName, iter, true);
		        
				((IteratorExp) result).addIterator(iter);
				VariableExp varRef = new VariableExp(iter);
				varRef.setAppliedProperty(exp);
				((IteratorExp) result).setBody    (varRef);
                
			}
		}
		return result;
	}

	public AttributeCallExp tryToMatchAsAttribute(ParsedPropertyCallExp tree, IClassifier sourceType,
													String name) 
	{
		AttributeCallExp result = null;
		if( ! tree.hasBrackets() && (tree.getPropertyKind() == ParsedPropertyCallExp.DOT_CALL) ){
			IAttribute        att    = sourceType.findAttribute(name);
			if( att != null ){
				result = new AttributeCallExp();
				((AttributeCallExp)result).setReferredAttribute(att);
				((AttributeCallExp)result).setMarkedPre(tree.isMarkedPre());
			}
		}
		return result;
	}

	public AssociationEndCallExp tryToMatchAsAssociationEnd(ParsedPropertyCallExp tree, IClassifier sourceType, String name) {
		AssociationEndCallExp result = null;
		if( ! tree.hasBrackets() && (tree.getPropertyKind() == ParsedPropertyCallExp.DOT_CALL) ){
			IAssociationEnd ae = sourceType.findAssociationEnd(name);
			if( ae != null ){
				result = new AssociationEndCallExp();
				result.setReferredAssociationEnd(ae);
				result.setNavigationSource( ae.getAssociation().getOtherEnd(ae) );
				result.setMarkedPre(tree.isMarkedPre());
//				result.setNavigationSource( findNavigationSource(sourceType, ae.getAssociation()) );
			}
		}
		return result;
	}

	public AssociationClassCallExp tryToMatchAsAssociationClass(ParsedPropertyCallExp tree, IClassifier sourceType, String name) throws AnalysisException {
		AssociationClassCallExp result = null;
		if( ! tree.hasBrackets() && (tree.getPropertyKind() == ParsedPropertyCallExp.DOT_CALL) ){
			IAssociationClass assCls = sourceType.findAssociationClass(name);
			if( assCls != null ){
				result = new AssociationClassCallExp();
				result.setReferredAssociationClass(assCls);
				result.setMarkedPre(tree.isMarkedPre());
				String rolename = tree.getPropertyName().getRolename();
				IAssociationEnd navSource =
					findNavigationSource(tree, sourceType, assCls, rolename);
				result.setNavigationSource( navSource );
			}
		}
		return result;
	}

	private IAssociationEnd findNavigationSource(ParsedPropertyCallExp tree, IClassifier sourceType, 
												 IAssociationClass assCls, String rolename) throws AnalysisException {
		IAssociationEnd navSource = null;
		if (rolename.length() == 0) {
			IClassifier endType1 = assCls.getEnd1().getBaseType();
			IClassifier endType2 = assCls.getEnd2().getBaseType();
			if (endType1 == endType2) {
				throw new AnalysisException(currentFile, tree.getStartLine(), tree.getStartColumn(),
				   "Navigation to association class should have rolename indication.");
			}
			if (endType1 == sourceType) navSource = assCls.getEnd1();
			if (endType2 == sourceType) navSource = assCls.getEnd2();
		} else {
			IAssociationEnd end = sourceType.findAssociationEnd(rolename);
			if (end != null) {
				IAssociation assoc = end.getAssociation();
				if (assoc instanceof IAssociationClass) {
					assCls = (IAssociationClass)assoc;
					navSource = assCls.getOtherEnd(end);
				}
			}else{
				throw new AnalysisException(currentFile, tree.getStartLine(), tree.getStartColumn(),
				   "No associationEnd found for " + rolename);
			}
		}
		return navSource;
	}

	public OperationCallExp tryToMatchAsOperation(ParsedPropertyCallExp tree,	IClassifier sourceType,
													INameSpace p,	Environment env, String name)
													throws  AnalysisException
	{
		OperationCallExp result = null;
		if( (tree.hasBrackets() &&  (tree.getPropertyKind() == ParsedPropertyCallExp.DOT_CALL) 
								&&  (! sourceType.isCollectionKind() ) )
			||
			(tree.getPropertyKind() == ParsedPropertyCallExp.BINARY_OPERATOR)
			||
			(tree.hasBrackets() &&  (tree.getPropertyKind() == ParsedPropertyCallExp.ARROW_CALL) 
								&& sourceType.isCollectionKind() 
								&& (OclIterator.getOclIterator(name) == null) )
//			||
//			(tree.hasBrackets() &&  (tree.getPropertyKind() == ParsedPropertyCallExp.DAKJE) )
//			||
//			(tree.hasBrackets() &&  (tree.getPropertyKind() == ParsedPropertyCallExp.DOUBLE_DAKJE) )
			||
			(tree.getPropertyName().toPathName().getLast().equals("not"))
			||
			(tree.getPropertyName().toPathName().getLast().equals("-"))
			 )
		{
			List<IOclExpression> args;
			try {
				args = analyzeArguments(tree, sourceType, p, env);
			} catch (AnalysisException e) {
				return null;
			}
			checkArgumentsForUnspecifiedValue(tree, args);

			List<IClassifier> types = getTypeList(args);
            
			IOperation op = sourceType.findOperation(name, types);
			if (op != null) {
				boolean okee = true;
				if ( !checkTypes(sourceType, op.getParamTypes(), args) ) {
					okee = false;
				} 
				if (!checkAsTypeArguments(tree, sourceType, op, args)) {
					okee = false;
				}
				if (okee) {
					result = new OperationCallExp();
					result.setReferredOperation(op);
					result.setArguments(args);
					result.setMarkedPre(tree.isMarkedPre());
					if( tree.getPropertyKind() == ParsedPropertyCallExp.BINARY_OPERATOR 
						&& !op.isInfix() ){
							throw new AnalysisException(currentFile, tree.getStartLine(), tree.getStartColumn(),
							   "Operation '" + op.getName() + "' may not be used as infix operation.");
					}
//					if ((tree.getPropertyKind() == ParsedPropertyCallExp.DAKJE)
//						|| (tree.getPropertyKind() == ParsedPropertyCallExp.DOUBLE_DAKJE)) {
//							// TODO this should be recognized as an OclMessageExp
//							result.setReturnType(OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName));
//					} else {
						getCorrectReturnType(sourceType, result, args, op);
//					}
				}
			}
		} else {
			// do not analyze, this is not an operation call
		}
		if( result != null ){
			// check return type
			if( result.getReferredOperation().getReturnType() == null ){
				throw new AnalysisException(currentFile, tree.getStartLine(), tree.getStartColumn(),
				   "Operation '" + result.getReferredOperation().getName() + "' has no return type.");
			}
			// check on sum
			if (result.getReferredOperation().getName().equals("sum")) {
				if (sourceType instanceof ICollectionType) {
					IClassifier elementType = ((ICollectionType)sourceType).getElementType();
					List<IClassifier> params = new ArrayList<IClassifier>();
					params.add(elementType);
					IOperation plusOp = elementType.findOperation("+", params);
					if (plusOp == null || (plusOp.getReturnType() != elementType)) {
						throw new AnalysisException(currentFile, tree.getStartLine(), tree.getStartColumn(),
								"Element type " + elementType.getName() + " does not define operation " +
								"'+ " + elementType.getName() + ": " + elementType.getName() + "'.");
					}
				}
			}
		}
		return result;
	}

	private void getCorrectReturnType(IClassifier sourceType, OperationCallExp opExp, List<IOclExpression> args, IOperation op) {
		if (op.getReturnType() == OclEngine.getCurrentOclLibrary().lookupStandardType("DependsOnSourceType")) {
			opExp.setReturnType( OclEngine.getCurrentOclLibrary().getReturnType(sourceType, op));
		}  
		if (op.getReturnType() == OclEngine.getCurrentOclLibrary().lookupStandardType("DependsOnArgumentType")) {
			IClassifier returnType = OclEngine.getCurrentOclLibrary().getReturnType(getTypeList(args), op);
			if ( returnType == OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclTypeTypeName)){
				OclExpression actualPar = null;
				if (args.size() > 0) actualPar = (OclExpression) args.get(0);
				if( actualPar instanceof OclTypeLiteralExp ) returnType = ((OclTypeLiteralExp) actualPar).getReferredClassifier();	
			}
			opExp.setReturnType( returnType );
		}          		
	}

	/** 
	 * @param tree
	 * @param sourceType
	 * @param name
	 * @param env
	 * @param p
	 * @return PropertyCallExp
	 */
	public PropertyCallExp tryToMatchObjectAsSet( ParsedPropertyCallExp tree,
													   IClassifier sourceType, String name,
													   Environment env, INameSpace p) throws AnalysisException {
	  if (Check.ENABLED) Check.pre("Match object as set without arrow call", 
				 tree.getPropertyKind() == ParsedPropertyCallExp.ARROW_CALL );

	  PropertyCallExp result = null;
	  ICollectionType ct = OclEngine.getCurrentOclLibrary().lookupCollectionType(CollectionMetaType.SET, sourceType);
	  // there are two possibilities: either the property call is an iterator 
	  // or it is a predefined collection operation
	  
	  // try collection operations ...
	  result = tryToMatchAsOperation(tree, ct, p, env, name);

	  // try iterators ...
	  if( result == null ){
		  result = tryToAnalyzeLoopExp(ct, env,tree,p);
	  }
	  if( result != null ){
		  // insert operation call asSet() in the ast.
		  IOperation asSet = sourceType.findOperation("asSet", new ArrayList<IClassifier>());
		  OperationCallExp asSetExp = new OperationCallExp(asSet);
		  asSetExp.setAppliedProperty(result);
		  getCorrectReturnType(sourceType, asSetExp, new ArrayList<IOclExpression>(), asSet);
		  result = asSetExp;
	  }
	  return result;
	}

	/** Try to analyse the parse tree as an iterator expression.
	 *  Returns null if this isn't possible.
	 */
	public PropertyCallExp tryToAnalyzeLoopExp(IClassifier sourceType, Environment env, 
													ParsedPropertyCallExp tree, INameSpace p) throws AnalysisException {
		LoopExp  result = null;
		String   name   = tree.getPropertyName().toPathName().getLast();

		if( sourceType instanceof ICollectionType ){
			// try to find a Loop Expression
			IOclIterator iter = OclIterator.getOclIterator(name);
			// check whether the propertycall is an iterator.
			if( iter != null ){
				if( iter.getName().equals("iterate") ) { // TODO this should be cleaner
					result = new IterateExp();
				} else {
					result = new IteratorExp();
				}
				result.setName(iter.getName());
				// add the iterator variable(s) and analyze the body
				Environment iteratorEnv = analyzeLoopVariables(sourceType, env, tree, p, result);
				analyzeBody(result, tree, p, iteratorEnv);

				IClassifier bodyType = null;
				bodyType = result.getBody().getExpressionType();
				if( ! iter.checkBodyType(bodyType) ) {
					throw new AnalysisException(currentFile, tree.getStartLine(), tree.getStartColumn(),
						  "Body type of iterator [" + name + "] is incorrect\n");
				}
				IClassifier returnType = iter.getReturnType((ICollectionType)sourceType, bodyType );
				if (Check.ENABLED) Check.isTrue("ExpressionAnalyzer::TryToAnalyseIteratorExp returntype == null", returnType != null);
				result.setNodeType(returnType);
			} else {
				// Check whether there are iterator variables. If they are present,
				// this  is an error, because this is not an iterator
				Iterator its = tree.getIterators();
				if( (its != null) && (its.hasNext()) ){
					throw new AnalysisException(new OclError(currentFile, tree.getStartLine(), tree.getStartColumn(), 
						  "Operation " + name + " is not an iterator, it should not have iterator variables\n"));
				}
			}

		}
		return result;
	}

	/** analyze the iterators variables in <i>tree</i>. The found iterators will be
	 *  added to <i>loopExp</i>.
	 * 
	 * @param sourceType
	 * @param env
	 * @param tree
	 * @param p
	 * @param loopExp
	 * @return Environment a new environment that includes the loop variuables and has <i>env</i>
	 *                     as parent environment.
	 */
	private Environment analyzeLoopVariables(IClassifier sourceType,	Environment env,
											 ParsedPropertyCallExp tree, INameSpace p, LoopExp loopExp)
											 throws  AnalysisException  {
		Environment iteratorEnv = new Environment();
		iteratorEnv.setParent(env);
		Iterator it = tree.getIterators();
		if( (it != null) && ( it.hasNext()) ) { // Iterator variable is defined
			while( it.hasNext() ){
				ParsedVariableDeclaration pvar  = (ParsedVariableDeclaration)it.next();
				IClassifier defaultType = null;
				defaultType = ((ICollectionType)sourceType).getElementType(); // in case we need to deduce the type
				ExpressionAnalyzer analyzer = new ExpressionAnalyzer(currentFile, errors);
				VariableDeclaration var = analyzer.analyzeVariableDeclaration(pvar, env, defaultType);
				iteratorEnv.addElement(var.getName(), var, true);
				var.setIteratorVar(true);
				loopExp.addIterator(var);
				if( !Conformance.conformsTo(var.getType(), defaultType) ) {
					throw new AnalysisException(currentFile, tree.getStartLine(), tree.getStartColumn(),
						  	"Declared type of iterator variable ('" + var.getType().getName() + 
							"') does not match element type of source ('" + defaultType.getName() + "').\n");
				}
			}
	   } else {  // add default iterator variable
			String varName = "i_" + ((ICollectionType)sourceType).getElementType().getName();
			if (((ICollectionType)sourceType).getElementType() instanceof ITupleType) {
				varName = "i_Tuple" + uniqueNumber++;
			}
			VariableDeclaration var =
						 new VariableDeclaration(varName, ((ICollectionType)sourceType).getElementType());
			iteratorEnv.addElement(varName, var, true);
			var.setIteratorVar(true);
			loopExp.addIterator(var);
		}
        
		// checks whether this is an iterate with result variable.
		ParsedVariableDeclaration resultVar = tree.getResult();
		if( resultVar != null ){
			if( ! loopExp.getName().equals("iterate") ){
				throw new AnalysisException(currentFile, tree.getStartLine(), tree.getStartColumn(),
				"iterator [" + loopExp.getName() + "] cannot have a result variable\n");
			}
			ExpressionAnalyzer analyzer = new ExpressionAnalyzer(currentFile, errors);
			VariableDeclaration var = analyzer.analyzeVariableDeclaration(resultVar, env, null);
			iteratorEnv.addElement(var.getName(), var, false);
			((IterateExp)loopExp).setResult(var);
		} else {
			if(  loopExp.getName().equals("iterate") ) {
				throw new AnalysisException(currentFile, tree.getStartLine(), tree.getStartColumn(),
					  "iterate is missing a result variable\n");
			}
		}

		return iteratorEnv;
	}

	/** Check whether this is a propertycall where only an OclState is allowed as parameter
	 *
	 * @param tree
	 * @param env
	 * @param result
	 * @param args
	 */
	private OclExpression tryToMatchState(ParsedPropertyCallExp tree, IClassifier sourceType, 
										  Environment env) throws AnalysisException {
		OclStateLiteralExp result = null;
		PathName name = tree.getPropertyName().toPathName();

		if( name.isSingleName() && (name.getLast().equals("oclInState") || name.getLast().equals("oclIsInState"))){
			if( tree.getArguments().size() == 1 ) {
				// CHECK FOR STATE !
				ParsedOclExpression arg = tree.getArgument(0);
				if( arg instanceof ParsedPropertyCallExp ) {
					PathName parsedName = ((ParsedPropertyCallExp)arg).getPropertyName().toPathName();
					IState foundState = null;
					if( sourceType != null) {
						foundState = sourceType.findState(parsedName);
					} else {
						foundState = env.lookupImplicitState(parsedName);
					}
					if (foundState != null) {
						result = new OclStateLiteralExp(foundState);
					}
				}
			}				
		}
		return result;
	}

	/**
	 * Method tryToMatchInitialClassOperation.
	 * @param tree
	 * @param p
	 * @param env
	 * @param name
	 * @return OclExpression
	 */
	public OclExpression tryToMatchInitialClassOperation(ParsedPropertyCallExp tree, INameSpace p,
										  Environment env, PathName name) throws AnalysisException {
		OclExpression ast = null;
		OperationCallExp propcall = null;
		
		IClassifier myClass = env.lookupClassifier(name.getHead());
		if( myClass == null ){
			throw new AnalysisException(currentFile, tree.getStartLine(), tree.getStartColumn(),
					"Cannot find class : '" + name.getHead() + "'.");
		}
		List<IOclExpression> args  = analyzeArguments(tree, myClass, p, env);
		List<IClassifier> types = getTypeList(args);
		IOperation classOp = myClass.findClassOperation(name.getLast(), types);
		if (classOp != null) {
			ast = new OclTypeLiteralExp(myClass);
			propcall = new OperationCallExp(classOp, args);
			propcall.setMarkedPre(tree.isMarkedPre());
			ast.setAppliedProperty(propcall);
			if (classOp.getName().equals("allInstances")){
				if (myClass instanceof IDataType) {
					throw new AnalysisException(currentFile, tree.getStartLine(), tree.getStartColumn(),
					   "Operation 'allInstances' may not be used on datatypes.");		
				}			
			}
			if( tree.getPropertyKind() == ParsedPropertyCallExp.BINARY_OPERATOR ){
				if( !classOp.isInfix() ){
					throw new AnalysisException(currentFile, tree.getStartLine(), tree.getStartColumn(),
					   "Operation '" + classOp.getName() + "' may not be used as infix operation.");
				}
			}
		  // get correct return type in case the return type is of a special kind
		  getCorrectReturnType(myClass, propcall, args, classOp);
		}
		if( ast != null ){
			if( propcall.getReferredOperation().getReturnType() == null ){
				throw new AnalysisException(currentFile, tree.getStartLine(), tree.getStartColumn(),
						"Operation '" + propcall.getReferredOperation().getName() + "' has no return type.");
			}
		}
		return ast;
	}


	/**
	 * Method tryToMatchInitialPathName.
	 * @param tree
	 * @param p
	 * @param env
	 * @param name
	 * @return OclExpression
	 */
	public OclExpression tryToMatchInitialPathName(Environment env, ParsedPropertyCallExp tree ) {
		OclExpression ast = null;
		PathName name = tree.getPropertyName().toPathName();
		IModelElement  hit  = env.lookup(name);

		// Find the name in the environment
		if( hit != null ){
			if( hit instanceof IAttribute ) {
				// This is always a class attribute
				ast = new AttributeCallExp((IAttribute)hit);
				((AttributeCallExp)ast).setMarkedPre(tree.isMarkedPre());
			} else if( hit instanceof IEnumLiteral ) {
				ast = new EnumLiteralExp( (IEnumLiteral) hit );
			} else if( hit instanceof IClassifier ) {
				ast = new OclTypeLiteralExp( (IClassifier) hit );
			} else {
				System.err.println("tryToMatchInitialPathName: incorrect type " + name.toString());
			}
		} else {
			// see if 'tree' matches a type from the standard lib
			IClassifier stdType = findStdType(env, tree);
			if (stdType != null) ast = new OclTypeLiteralExp( stdType );
		}
		return ast;
	}

	private IClassifier findStdType(Environment env, ParsedPropertyCallExp tree) {
		IClassifier type = null;
		PathName name = tree.getPropertyName().toPathName();
		if (!tree.getArguments().isEmpty()){
			IClassifier elementType = null;
			CollectionMetaType metaType = CollectionMetaType.getMetaType(name.getLast());
			// Find the element type recursively.
			ParsedOclExpression arg = tree.getArgument(0);
			ParsedPropertyCallExp parsedElementType = null;
			if (arg instanceof ParsedPropertyCallExp) {
				parsedElementType = (ParsedPropertyCallExp) arg;
				elementType = findStdType(env, parsedElementType);
			}
			//
			if (elementType != null && metaType != null) {
				type = OclEngine.getCurrentOclLibrary().lookupCollectionType(metaType, elementType);
			}
		} else if (name.isSingleName()) {
			type = OclEngine.getCurrentOclLibrary().lookupStandardType(name.getLast());
		} else {
			name = tree.getPropertyName().toPathName();
			IModelElement  hit  = env.lookup(name);
			if (hit != null && hit instanceof IClassifier ) {
				type = (IClassifier) hit;
			}
		}
		return type;
	}

	/**
	 * Method tryToMatchInitialSimpleName.
	 * @param tree
	 * @param p
	 * @param env
	 * @param name
	 */
	public OclExpression tryToMatchInitialSimpleName(ParsedPropertyCallExp tree, INameSpace p,
													  Environment env, PathName name) throws AnalysisException {
		OclExpression result = null;
		
		// Find the name in the environment
		IModelElement  hit  = env.lookup(name);
		if( hit != null ){
			if( hit instanceof VariableDeclaration ){
				result = new VariableExp((VariableDeclaration)hit);
			}
		}
        
		if( result == null ) {
			result = tryToMatchImplicitAttribute(tree, env, name);
			if( result != null ) { return result; }

			result = tryToMatchImplicitAssociationEnd(tree, env, name);
			if( result != null ) { return result; }

			result = tryToMatchImplicitAssociationClass(tree, env, name);
			if( result != null ) { return result; }
		}

		// An implicit property has been tried first, now try a classifier literal.
		if( hit != null ){
			if( hit instanceof IClassifier ) {
				result = new OclTypeLiteralExp( (IClassifier) hit );
			}
		}
		return result;	
	}

	/** Try to analyze <i>name</i> as if it is an implicit attribute. If this is true
	 *  return an AST that includes the implicit variable and the attribute call.
	 * 
	 * @param env     The environment in which the implicit variable should be found.
	 * @param name    The name of the attribute to look for
	 * @return VariableExp null if the attribute cannot be found.
	 */
	private VariableExp tryToMatchImplicitAttribute(ParsedPropertyCallExp tree, Environment env, PathName name) {
	  VariableExp result = null;
	  IAttribute   att    = env.lookupImplicitAttribute(name.getLast());
	  if( att != null ){
		  VariableDeclaration source = env.lookupImplicitSourceForAttribute(name.getLast());
		  result = new VariableExp( source );
		  result.setImplicit(true);
		  AttributeCallExp attExp = new AttributeCallExp(att);
		  attExp.setMarkedPre(tree.isMarkedPre());
		  result.setAppliedProperty(attExp);

	  }
	  return result ;
	}

	/** @see tryToMatchImplicitAttribute.
	 * 
	 * @param env
	 * @param name
	 * @return OclExpression
	 */
	private OclExpression tryToMatchImplicitAssociationEnd(ParsedPropertyCallExp tree, Environment env, PathName name) {
		OclExpression result = null;
		IAssociationEnd ae = env.lookupImplicitAssociationEnd(name.getLast());
		if( ae != null ){
			VariableDeclaration source = env.lookupImplicitSourceForAssociationEnd(name.getLast());
			result = new VariableExp( source );
			result.setImplicit(true);
			AssociationEndCallExp assExp = new AssociationEndCallExp(ae);
			assExp.setMarkedPre(tree.isMarkedPre());
			result.setAppliedProperty(assExp);
			assExp.setNavigationSource( ae.getAssociation().getOtherEnd(ae) );
		}
		return result ;
	}

	/** @see tryToMatchImplicitAttribute.
	 * 
	 * @param env
	 * @param name
	 * @return OclExpression
	 */
	private OclExpression tryToMatchImplicitAssociationClass(ParsedPropertyCallExp tree, Environment env, PathName name) throws AnalysisException {
		OclExpression result = null;
		IAssociationClass ae = env.lookupImplicitAssociationClass(name.getLast());
		if( ae != null ){
			VariableDeclaration source = env.lookupImplicitSourceForAssociationClass(name.getLast());
			result = new VariableExp( source );
			result.setImplicit(true);
			AssociationClassCallExp assExp = new AssociationClassCallExp(ae);
			assExp.setMarkedPre(tree.isMarkedPre());
			String rolename = tree.getPropertyName().getRolename();
			assExp.setNavigationSource( findNavigationSource(tree, source.getType(), ae, rolename) );
			result.setAppliedProperty(assExp);
		}
		return result ;
	}
    
	/** @see tryToMatchImplicitAttribute
	 * 
	 * @param tree
	 * @param p
	 * @param env
	 * @param name
	 * @return VariableExp
	 */
	public VariableExp tryToMatchImplicitOperation(ParsedPropertyCallExp tree, INameSpace p, Environment env, PathName name)
		throws AnalysisException {
		VariableExp      result   = null;
		OperationCallExp opExp = null;

		List<IOclExpression> args     = analyzeArguments(tree, null, p, env);
		checkArgumentsForUnspecifiedValue(tree, args);
		List<IClassifier> argTypes = getTypeList(args);
		IOperation op = env.lookupImplicitOperation(name.getLast(), argTypes);
		if( op != null ){
			VariableDeclaration source = env.lookupImplicitSourceForOperation(name.getLast(), argTypes);
			if (checkAsTypeArguments(tree, source.getType(), op, args)) {
				result = new VariableExp( source );
				result.setImplicit(true);
				opExp = new OperationCallExp(op);           
				result.setAppliedProperty(opExp);
				opExp.setArguments(args);
				getCorrectReturnType(source.getType(), opExp, args, op);
			}
		}
		if( result != null ){
			if( opExp.getReferredOperation().getReturnType() == null ){
				throw new AnalysisException(currentFile, tree.getStartLine(), tree.getStartColumn(),
				"Operation '" + opExp.getReferredOperation().getName() + "' has no return type.");
			}
		}
		return result;
	}

	/**
	 * @param args
	 */
	private void checkArgumentsForUnspecifiedValue(ParsedPropertyCallExp tree, List<IOclExpression> args) throws AnalysisException {
		Iterator arguments = args.iterator();
		while( arguments.hasNext()){
			OclExpression argument = (OclExpression)arguments.next();
			if( argument instanceof IUnspecifiedValueExp ) {
				throw new AnalysisException(currentFile, tree.getStartLine(), tree.getStartColumn(),
				"Unspecified values '?' can only be used with Ocl Message expressions using ^ or ^^");
			}
		}
	}

	/**
	 * Checks whether the argument of the special operations oclAsType, etc.
	 * conforms to its source.
	 * 
	 * @param classifier
	 * @param op
	 * @param args
	 * @return
	 */
	private boolean checkAsTypeArguments(ParsedPropertyCallExp tree, IClassifier sourceType, IOperation op, List<IOclExpression> args) throws AnalysisException {
		boolean result = true;
		String opname = op.getName();
		if( opname.equals("oclAsType") ) {
			if (args != null && args.get(0) instanceof OclTypeLiteralExp) {
				IClassifier argumentType = ((OclTypeLiteralExp)args.get(0)).getReferredClassifier();
				if (! (Conformance.conformsTo(argumentType, sourceType) ||
					   Conformance.conformsTo(sourceType, argumentType) || argumentType instanceof IInterface)) {
					throw new AnalysisException(new OclError(currentFile, tree.getStartLine(), tree.getStartColumn(), 
						  opname + ": " + argumentType.getName() + " does not conform to " +  sourceType.getName() + "\n"));
				}
			}
		}
		return result;
	}

	public List<IClassifier> getTypeList(List<IOclExpression> args) {
		List<IClassifier> 	types	= new ArrayList<IClassifier>( args.size() );
		Iterator         		i      	= args.iterator();
		while( i.hasNext() ) {
			OclExpression exp = (OclExpression) i.next();
			types.add( exp.getExpressionType() );
		}
		return types;
	}
	
	/**
	 * Method checkTypes: checks whether one of the formal param types is equal to DependsOnSourceType. Operations with
	 * these kind of param types are found okee by the Conformance class.
	 * @param sourceType
	 * @param formal
	 * @param actual
	 * @return boolean
	 */
	private boolean checkTypes(IClassifier sourceType, List<IClassifier> formal, List<IOclExpression> actual) {
		Iterator         i1      = actual.iterator();
		Iterator         i2      = formal.iterator();
		while( i1.hasNext() && i2.hasNext()) {
			OclExpression exp = (OclExpression) i1.next();
			IClassifier    formalType = (IClassifier) i2.next();
			if (formalType == OclEngine.getCurrentOclLibrary().lookupStandardType("DependsOnSourceType")) {
				if( !Conformance.conformsTo(exp.getExpressionType(), sourceType) ) return false;
			}
		}
		return true;
	}
	/** Analyze the arguments of an operationExp
	 *  Adds the parsed arguments to the OperationCallExp <i>opExp</i>
	 */
	public List<IOclExpression> analyzeArguments(ParsedPropertyCallExp tree,
								  IClassifier sourceType,
								  INameSpace p,
								  Environment env) throws  AnalysisException 
	{
		List<IOclExpression>     result = new ArrayList<IOclExpression>();
		Iterator args   = tree.getArguments().iterator();
		OclExpression argExp = null;
        
		String basename = tree.getPropertyName().toPathName().getLast();
		// Check whether this is a propertycall where only an OclType is allowed as parameter
		if( basename.equals("oclIsTypeOf") || 
			basename.equals("oclIsKindOf") || 
			basename.equals("oclAsType") )
		{
			if ((ParsedPropertyCallExp)tree.getArgument(0) instanceof ParsedPropertyCallExp){
				ParsedPropertyCallExp arg = ((ParsedPropertyCallExp)tree.getArgument(0));
				argExp = tryToMatchInitialPathName(env, arg);
				if( argExp != null && (argExp instanceof OclTypeLiteralExp)) {
					result.add(argExp);
					return result;
				} else {
					throw new AnalysisException(new OclError(currentFile, tree.getStartLine(), tree.getStartColumn(), 
						  basename + ": expected a classifier name as parameter\n"));
				}
			} else {
				throw new AnalysisException(new OclError(currentFile, tree.getStartLine(), tree.getStartColumn(), 
					  basename + ": expected a classifier name as parameter\n"));
			}
		}
        
		// Check whether this is a propertycall where only a IState is allowed as parameter
		if( basename.equals("oclInState") || basename.equals("oclIsInState")){
			argExp = tryToMatchState(tree, sourceType, env);
			if( argExp != null) {
				result.add(argExp);
				return result;
			} else {
				throw new AnalysisException(new OclError(currentFile, tree.getStartLine(), tree.getStartColumn(), 
					  "Operation " + basename + ": expected a state name as parameter\n"));
			}
		}       
        
		while( args.hasNext() ){
			ParsedOclExpression arg = (ParsedOclExpression)args.next();
			ExpressionAnalyzer analyzer = new ExpressionAnalyzer(currentFile, errors);
			argExp = analyzer.analyzeParsetree(arg, null, p, env);
			result.add(argExp);
		}
		return result;
	}
	
	/** Analyse the parse tree as the body of 'iteratorExp'
	 */
	private void analyzeBody(LoopExp iteratorExp, ParsedPropertyCallExp tree,
							 INameSpace p, Environment env) throws  AnalysisException 
	{
		// There should be exactly one body for an iterator
		// TODO REFACTOR
		if( tree.getArguments().size() != 1 ){
			throw new AnalysisException(new OclError(currentFile, tree.getStartLine(), tree.getStartColumn(), 
				  "Number of arguments of an iterator should be 1 \n"));
		}
		Iterator args = tree.getArguments().iterator();
		while( args.hasNext() ){
			ParsedOclExpression body = (ParsedOclExpression)args.next();
			ExpressionAnalyzer analyzer = new ExpressionAnalyzer(currentFile, errors);
			OclExpression bodyExp = analyzer.analyzeParsetree(body, null, p, env);
			iteratorExp.setBody(bodyExp);
		}
	}


}
