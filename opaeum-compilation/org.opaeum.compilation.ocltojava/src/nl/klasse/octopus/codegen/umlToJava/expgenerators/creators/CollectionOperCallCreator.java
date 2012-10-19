package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;

import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.common.ExpGeneratorHelper;
import nl.klasse.octopus.codegen.umlToJava.common.TypeHelper;
import nl.klasse.octopus.codegen.umlToJava.expgenerators.visitors.OclUtilityCreator;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.tools.common.Check;
import nl.klasse.tools.common.StringHelpers;
import nl.klasse.tools.common.Util;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.uml.CollectionType;
import org.eclipse.ocl.uml.OCLExpression;
import org.eclipse.ocl.uml.OperationCallExp;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.PrimitiveType;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.OJWhileStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.utilities.JavaPathNames;
import org.opaeum.javageneration.util.OJUtil;

public class CollectionOperCallCreator{
	private OJClass myClass = null;
	OJUtil ojUtil;
	ExpGeneratorHelper expGeneratorHelper;
	private TypeHelper typeHelper;
	public CollectionOperCallCreator(ExpGeneratorHelper h,OJClass myClass){
		super();
		this.expGeneratorHelper = h;
		this.ojUtil = h.ojUtil;
		this.myClass = myClass;
		this.typeHelper = new TypeHelper(ojUtil);
	}
	public String collectionOperCall(OperationCallExp exp,String source,List<String> args,Operation referedOp,boolean isStatic,
			List<OJParameter> params){
		Check.pre("source is not null", exp.getSource() != null);
		String result = "";
		//
		if(referedOp != null){
			// the order is which the operation names appear is taken from
			// tables 9-1 and 9-2
			// of the book "The Object Constraint Language, 2e edition"
			if(referedOp.getName().equals("count")){
				result = buildCount(exp, source, args, isStatic, params);
			}else if(referedOp.getName().equals("excludes")){
				result = buildExcludes(exp, source, args);
			}else if(referedOp.getName().equals("excludesAll")){
				result = buildExcludesAll(exp, source, args);
			}else if(referedOp.getName().equals("includes")){
				result = buildIncludes(exp, source, args);
			}else if(referedOp.getName().equals("includesAll")){
				result = buildIncludesAll(exp, source, args);
			}else if(referedOp.getName().equals("isEmpty")){
				result = source + "." + "isEmpty()";
			}else if(referedOp.getName().equals("notEmpty")){
				result = "!" + source + "." + "isEmpty()";
			}else if(referedOp.getName().equals("size")){
				result = source + "." + "size()";
			}else if(referedOp.getName().equals("sum")){
				result = buildSum(exp, source, isStatic, params).toString();
				// from here with variant meaning
			}else if(referedOp.getName().equals("=")){
				result = buildEquals(exp, source, args, isStatic, params);
			}else if(referedOp.getName().equals("<>")){
				result = "!" + buildEquals(exp, source, args, isStatic, params);
			}else if(referedOp.getName().equals("-")){
				result = buildMinus(exp, source, args, isStatic, params);
			}else if(referedOp.getName().equals("append")){
				result = buildAppend(exp, source, args, isStatic, params);
			}else if(referedOp.getName().equals("at")){
				return makeGet(exp, source, (String) args.get(0) + "-1", (String) args.get(0) + "-1");
			}else if(referedOp.getName().equals("excluding")){
				result = buildExcluding(exp, source, args, isStatic, params);
			}else if(referedOp.getName().equals("first")){
				return makeGet(exp, source, "0", "0");
			}else if(referedOp.getName().equals("flatten")){
				result = buildFlatten(exp, source, args, isStatic, params);
			}else if(referedOp.getName().equals("including")){
				result = buildIncluding(exp, source, args, isStatic, params);
			}else if(referedOp.getName().equals("indexOf")){
				result = source + ".indexOf(" + args.get(0) + ")+1";
			}else if(referedOp.getName().equals("insertAt")){
				result = buildInsertAt(exp, source, args);
			}else if(referedOp.getName().equals("intersection")){
				result = buildIntersection(exp, source, args, isStatic, params);
			}else if(referedOp.getName().equals("last")){
				return makeGet(exp, source, "0", "source.size()-1");
			}else if(referedOp.getName().equals("prepend")){
				result = buildPrepend(exp, source, args, isStatic, params);
			}else if(referedOp.getName().equals("subOrderedSet")){
				result = buildSubList(exp, source, args);
			}else if(referedOp.getName().equals("subSequence")){
				result = buildSubList(exp, source, args);
			}else if(referedOp.getName().equals("symmetricDifference")){
				result = buildSymmetricDifference(exp, source, args, isStatic, params);
			}else if(referedOp.getName().equals("union")){
				result = buildUnion(exp, source, args, isStatic, params);
			}else if(referedOp.getName().equals("asBag")){
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getLast() + "." + "collectionAsBag(" + source + ")";
			}else if(referedOp.getName().equals("asSequence")){
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getTypeName() + "." + "collectionAsSequence(" + source + ")";
			}else if(referedOp.getName().equals("asOrderedSet")){
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getTypeName() + "." + "collectionAsOrderedSet(" + source + ")";
			}else if(referedOp.getName().equals("asSet")){
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getTypeName() + "." + "collectionAsSet(" + source + ")";
			}else if(referedOp.getName().equals("oclIsUndefined")){
				result = source + " == null";
			}else{
				System.err.println("unspecified option in CollectionOperCallGenerator.collectionOperCall:");
				System.err.println("\t" + referedOp.getName());
			}
		}
		return result;
	}
	CollectionKind getCollectionKind(OperationCallExp exp){
		return ((CollectionType) exp.getType()).getKind();
	}
	private String buildEquals(OperationCallExp exp,String source,List<String> args,boolean isStatic,List<OJParameter> params){
		CollectionKind collectionKind = getCollectionKind(exp);
		String operName = "";
		if(collectionKind == CollectionKind.SET_LITERAL){
			operName = "setEquals";
		}else if(collectionKind == CollectionKind.BAG_LITERAL){
			operName = "bagEquals";
		}else if(collectionKind == CollectionKind.SEQUENCE_LITERAL){
			operName = "sequenceEquals";
		}else if(collectionKind == CollectionKind.ORDERED_SET_LITERAL){
			operName = "orderedsetEquals";
		}
		myClass.addToImports(OclUtilityCreator.getStdlibPath());
		return "Stdlib." + operName + "(" + source + ", " + args.get(0) + ")";
	}
	private String makeGet(OperationCallExp exp,String source,String minSize, String index){
		CollectionType elementType = (CollectionType) exp.getSource().getType();
		ClassifierMap elementMap = ojUtil.buildClassifierMap(elementType.getElementType());
		ClassifierMap collectionMap = ojUtil.buildClassifierMap(elementType);
		OJAnnotatedOperation getAtIndex = new OJAnnotatedOperation("getAtIndex" + myClass.getUniqueNumber(),collectionMap.javaElementTypePath());
		myClass.addToOperations(getAtIndex);
		getAtIndex.setVisibility(OJVisibilityKind.PRIVATE);
		getAtIndex.addParam("source", collectionMap.javaTypePath());
		getAtIndex.initializeResultVariable(elementMap.javaDefaultValue());
		OJIfStatement ifInSize=new OJIfStatement("source.size()>" + minSize, "result=source.get("+index+")");
		getAtIndex.getBody().addToStatements(ifInSize);
		return getAtIndex.getName()+"(" + source + ")";
				
	}
	// make copy of source => no side effects allowed
	private String makeCopyOfSource(OperationCallExp exp,String source){
		Classifier sourceType = exp.getSource().getType();
		ClassifierMap sourceMap = ojUtil.buildClassifierMap(sourceType);
		OJPathName implType = sourceMap.javaDefaultTypePath();
		source = "new " + implType.getTypeNameWithTypeArguments() + "(" + source + ")";
		myClass.addToImports(implType);
		return source;
	}
	private String buildIncluding(OperationCallExp exp,String source,List<String> args,boolean isStatic,List<OJParameter> params){
		Classifier elementType = (Classifier) exp.getReferredOperation().getType();
		String argument = expGeneratorHelper.makeListElem(myClass, elementType, (String) args.get(0));
		CollectionKind collectionKind = getCollectionKind(exp);
		ClassifierMap sourceMap = ojUtil.buildClassifierMap(exp.getType());
		String returnType = sourceMap.javaType();
		OJPathName returnTypePath = sourceMap.javaTypePath();
		// myClass.addToImports(sourceMap.javaTypePath());
		String operName = exp.getReferredOperation().getName() + myClass.getUniqueNumber();
		OJOperation oper = null;
		// make copy of source => no side effects allowed
		source = makeCopyOfSource(exp, source);
		if(collectionKind == CollectionKind.BAG_LITERAL || collectionKind == CollectionKind.SEQUENCE_LITERAL){
			oper = new OJOperation(operName);
			myClass.addToOperations(oper);
			oper.setReturnType(returnTypePath);
			
			oper.setVisibility(OJVisibilityKind.PRIVATE);
			oper.setStatic(isStatic);
			oper.setParameters(params);
			oper.setComment("implements " + exp.toString() + " on " + exp.getSource().toString());
			OJBlock body1 = new OJBlock();
			oper.setBody(body1);
			OJSimpleStatement exp1 = new OJSimpleStatement(returnType + " result = " + source);
			body1.addToStatements(exp1);
			OJSimpleStatement exp2 = new OJSimpleStatement("result.add( " + argument + " )");
			body1.addToStatements(exp2);
			OJSimpleStatement exp3 = new OJSimpleStatement("return result");
			body1.addToStatements(exp3);
		}else if(collectionKind == CollectionKind.SET_LITERAL || collectionKind == CollectionKind.ORDERED_SET_LITERAL){
			oper = new OJOperation(operName);
			myClass.addToOperations(oper);
			oper.setReturnType(returnTypePath);
			
			oper.setVisibility(OJVisibilityKind.PRIVATE);
			oper.setStatic(isStatic);
			oper.setParameters(params);
			oper.setComment("implements " + exp.toString() + " on " + exp.getSource().toString());
			OJBlock body2 = new OJBlock();
			oper.setBody(body2);
			OJSimpleStatement exp4 = new OJSimpleStatement(returnType + " result = " + source);
			body2.addToStatements(exp4);
			OJIfStatement if1 = new OJIfStatement();
			if1.setCondition("!result.contains(" + argument + ")");
			body2.addToStatements(if1);
			OJBlock then1 = new OJBlock();
			if1.setThenPart(then1);
			OJSimpleStatement exp5 = new OJSimpleStatement("result.add( " + argument + " )");
			then1.addToStatements(exp5);
			/* <then/> */
			/* <if/> */
			OJSimpleStatement exp6 = new OJSimpleStatement("return result");
			body2.addToStatements(exp6);
		}else{
			System.err.println("unexpected option in CollectionOperCallGenerator.buildIncluding");
		}
		// generate call to new oper
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	// source + ".removeAll( " + args.get(0) + " )";
	private String buildMinus(OperationCallExp exp,String source,List<String> args,boolean isStatic,List<OJParameter> params){
		String argument = StringHelpers.addBrackets((String) args.get(0));
		ClassifierMap sourceMap = ojUtil.buildClassifierMap(exp.getType());
		String returnType = sourceMap.javaType();
		OJPathName returnTypePath = sourceMap.javaTypePath();
		// myClass.addToImports(sourceMap.javaTypePath());
		String operName = "minus" + myClass.getUniqueNumber();
		OJOperation oper = null;
		// make copy of source => no side effects allowed
		source = makeCopyOfSource(exp, source);
		oper = new OJOperation(operName);
		myClass.addToOperations(oper);
		oper.setReturnType(returnTypePath);
		
		oper.setVisibility(OJVisibilityKind.PRIVATE);
		oper.setStatic(isStatic);
		oper.setParameters(params);
		oper.setComment("implements " + exp.toString() + " on " + exp.getSource().toString());
		OJBlock body3 = new OJBlock();
		oper.setBody(body3);
		OJSimpleStatement exp7 = new OJSimpleStatement(returnType + " result = " + source);
		body3.addToStatements(exp7);
		OJSimpleStatement exp8 = new OJSimpleStatement("result.removeAll( " + argument + " )");
		body3.addToStatements(exp8);
		OJSimpleStatement exp9 = new OJSimpleStatement("return result");
		body3.addToStatements(exp9);
		// generate call to new oper
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	private String buildExcluding(OperationCallExp exp,String source,List<String> args,boolean isStatic,List<OJParameter> params){
		CollectionKind collectionKind = getCollectionKind(exp);
		String argument = StringHelpers.addBrackets((String) args.get(0));
		ClassifierMap sourceMap = ojUtil.buildClassifierMap(exp.getType());
		String returnType = sourceMap.javaType();
		OJPathName returnTypePath = sourceMap.javaTypePath();
		// myClass.addToImports(sourceMap.javaTypePath());
		String operName = exp.getReferredOperation().getName() + myClass.getUniqueNumber();
		OJOperation oper = null;
		// make copy of source => no side effects allowed
		source = makeCopyOfSource(exp, source);
		if(collectionKind == CollectionKind.BAG_LITERAL || collectionKind == CollectionKind.SEQUENCE_LITERAL){
			oper = new OJOperation(operName);
			myClass.addToOperations(oper);
			oper.setReturnType(returnTypePath);
			
			oper.setVisibility(OJVisibilityKind.PRIVATE);
			oper.setStatic(isStatic);
			oper.setParameters(params);
			oper.setComment("implements " + exp.toString() + " on " + exp.getSource().toString());
			OJBlock body4 = new OJBlock();
			oper.setBody(body4);
			OJSimpleStatement exp10 = new OJSimpleStatement(returnType + " result = " + source);
			body4.addToStatements(exp10);
			/* <while> */
			OJWhileStatement while1 = new OJWhileStatement();
			body4.addToStatements(while1);
			while1.setCondition("result.contains(" + argument + ")");
			OJBlock body5 = new OJBlock();
			while1.setBody(body5);
			OJSimpleStatement exp11 = new OJSimpleStatement("result.remove( " + argument + " )");
			body5.addToStatements(exp11);
			/* <while/> */
			OJSimpleStatement exp12 = new OJSimpleStatement("return result");
			body4.addToStatements(exp12);
		}else if(collectionKind == CollectionKind.SET_LITERAL || collectionKind == CollectionKind.ORDERED_SET_LITERAL){
			oper = new OJOperation(operName);
			myClass.addToOperations(oper);
			oper.setReturnType(returnTypePath);
			
			oper.setVisibility(OJVisibilityKind.PRIVATE);
			oper.setStatic(isStatic);
			oper.setParameters(params);
			oper.setComment("implements " + exp.toString() + " on " + exp.getSource().toString());
			OJBlock body6 = new OJBlock();
			oper.setBody(body6);
			OJSimpleStatement exp13 = new OJSimpleStatement(returnType + " result = " + source);
			body6.addToStatements(exp13);
			OJSimpleStatement exp14 = new OJSimpleStatement("result.remove( " + argument + " )");
			body6.addToStatements(exp14);
			OJSimpleStatement exp15 = new OJSimpleStatement("return result");
			body6.addToStatements(exp15);
		}else{
			System.err.println("unexpected option in CollectionOperCallGenerator.buildIncluding");
		}
		// generate call to new oper
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	private String buildAppend(OperationCallExp exp,String source,List<String> args,boolean isStatic,List<OJParameter> params){
		ClassifierMap sourceMap = ojUtil.buildClassifierMap(exp.getType());
		String returnType = sourceMap.javaType();
		OJPathName returnTypePath = sourceMap.javaTypePath();
		// myClass.addToImports(sourceMap.javaTypePath());
		String operName = exp.getReferredOperation().getName() + myClass.getUniqueNumber();
		String argStr = StringHelpers.addBrackets((String) args.get(0));
		OJOperation oper = null;
		// make copy of source => no side effects allowed
		source = makeCopyOfSource(exp, source);
		oper = new OJOperation(operName);
		myClass.addToOperations(oper);
		oper.setReturnType(returnTypePath);
		
		oper.setVisibility(OJVisibilityKind.PRIVATE);
		oper.setStatic(isStatic);
		oper.setParameters(params);
		oper.setComment("implements " + exp.toString() + " on " + exp.getSource().toString());
		OJBlock body7 = new OJBlock();
		oper.setBody(body7);
		OJSimpleStatement exp16 = new OJSimpleStatement(returnType + " result = " + source);
		body7.addToStatements(exp16);
		OJSimpleStatement exp17 = new OJSimpleStatement("result.add( " + argStr + " )");
		body7.addToStatements(exp17);
		OJSimpleStatement exp18 = new OJSimpleStatement("return result");
		body7.addToStatements(exp18);
		// generate call to new oper
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	private String buildPrepend(OperationCallExp exp,String source,List<String> args,boolean isStatic,List<OJParameter> params){
		ClassifierMap sourceMap = ojUtil.buildClassifierMap(exp.getType());
		String returnType = sourceMap.javaType();
		OJPathName returnTypePath = sourceMap.javaTypePath();
		// myClass.addToImports(sourceMap.javaTypePath());
		String operName = exp.getReferredOperation().getName() + myClass.getUniqueNumber();
		String argStr = StringHelpers.addBrackets((String) args.get(0));
		OJOperation oper = null;
		// make copy of source => no side effects allowed
		source = makeCopyOfSource(exp, source);
		oper = new OJOperation(operName);
		myClass.addToOperations(oper);
		oper.setReturnType(returnTypePath);
		
		oper.setVisibility(OJVisibilityKind.PRIVATE);
		oper.setStatic(isStatic);
		oper.setParameters(params);
		oper.setComment("implements " + exp.toString() + " on " + exp.getSource().toString());
		OJBlock body8 = new OJBlock();
		oper.setBody(body8);
		OJSimpleStatement exp19 = new OJSimpleStatement(returnType + " result = " + source);
		body8.addToStatements(exp19);
		OJSimpleStatement exp20 = new OJSimpleStatement("result.add( 0, " + argStr + " )");
		body8.addToStatements(exp20);
		OJSimpleStatement exp21 = new OJSimpleStatement("return result");
		body8.addToStatements(exp21);
		// generate call to new oper
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	private String buildUnion(OperationCallExp exp,String source,List<String> args,boolean isStatic,List<OJParameter> params){
		ClassifierMap sourceMap = ojUtil.buildClassifierMap(exp.getType());
		// myClass.addToImports(returnTypePath);
		myClass.addToImports(sourceMap.javaElementTypePath());
		String operName = exp.getReferredOperation().getName() + myClass.getUniqueNumber();
		String argStr = StringHelpers.addBrackets((String) args.get(0));
		OJOperation oper = null;
		// make copy of source => no side effects allowed
		source = makeCopyOfSource(exp, source);
		oper = new OJOperation(operName);
		myClass.addToOperations(oper);
		oper.setReturnType(sourceMap.javaTypePath());
		
		oper.setVisibility(OJVisibilityKind.PRIVATE);
		oper.setStatic(isStatic);
		oper.setParameters(params);
		oper.setComment("implements " + exp.toString() + " on " + exp.getSource().toString());
		if(!exp.getReferredOperation().isUnique()){
			OJBlock body = new OJBlock();
			oper.setBody(body);
			OJSimpleStatement exp22 = new OJSimpleStatement(sourceMap.javaType() + " result = " + source);
			body.addToStatements(exp22);
			OJSimpleStatement exp23 = new OJSimpleStatement("result.addAll( " + argStr + " )");
			body.addToStatements(exp23);
			OJSimpleStatement exp24 = new OJSimpleStatement("return result");
			body.addToStatements(exp24);
		}
		if(exp.getReferredOperation().isUnique()){
			OJBlock body10 = new OJBlock();
			oper.setBody(body10);
			OJSimpleStatement exp25 = new OJSimpleStatement(sourceMap.javaType() + " result = " + source);
			body10.addToStatements(exp25);
			OJForStatement for1 = new OJForStatement();
			body10.addToStatements(for1);
			for1.setElemName("elem");
			for1.setElemType(sourceMap.javaElementTypePath());
			for1.setCollectionExpression(argStr);
			OJBlock body11 = new OJBlock();
			for1.setBody(body11);
			OJIfStatement if2 = new OJIfStatement();
			if2.setCondition("!result.contains(elem)");
			body11.addToStatements(if2);
			OJBlock then2 = new OJBlock();
			if2.setThenPart(then2);
			then2.addToStatements("result.add(elem)");
			body10.addToStatements("return result");
		}
		// generate call to new oper
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	private String buildInsertAt(OperationCallExp exp,String source,List<String> args){
		// TODO check whether the correct index number is used
		String index = (String) args.get(0) + " - 1";
		//
		String insertedElem = expGeneratorHelper.makeListElem(myClass, (Classifier) exp.getReferredOperation().getType(), (String) args.get(1));
		//
		String arguments = index + ", " + insertedElem;
		String result = OclUtilityCreator.getStdlibPath().getTypeName() + "." + "insertAt(" + source + ", " + arguments + ")";
		myClass.addToImports(OclUtilityCreator.getStdlibPath());
		return result;
	}
	private String buildIntersection(OperationCallExp exp,String source,List<String> args,boolean isStatic,List<OJParameter> params){
		Classifier elementType = (Classifier) exp.getReferredOperation().getType();
		// ClassifierMap elementMap = ojUtil.buildClassifierMap(elementType);
		ClassifierMap sourceMap = ojUtil.buildClassifierMap(exp.getType());
		// String myType = elementMap.javaType();
		String myDefault = sourceMap.javaDefaultValue();
		String returnType = sourceMap.javaType();
		OJPathName returnTypePath = sourceMap.javaTypePath();
		// myClass.addToImports(sourceMap.javaTypePath());
		OJPathName myListType = expGeneratorHelper.makeListType(elementType);
		String operName = exp.getReferredOperation().getName() + myClass.getUniqueNumber();
		String argStr = StringHelpers.addBrackets((String) args.get(0));
		OJOperation oper = null;
		oper = new OJOperation(operName);
		myClass.addToOperations(oper);
		oper.setReturnType(returnTypePath);
		
		oper.setVisibility(OJVisibilityKind.PRIVATE);
		oper.setStatic(isStatic);
		oper.setParameters(params);
		oper.setComment("implements " + exp.toString() + " on " + exp.getSource().toString());
		OJBlock body12 = new OJBlock();
		oper.setBody(body12);
		OJSimpleStatement exp28 = new OJSimpleStatement(returnType + " result = " + myDefault);
		body12.addToStatements(exp28);
		/* <for> */
		OJForStatement for2 = new OJForStatement();
		body12.addToStatements(for2);
		for2.setElemName("elem");
		for2.setElemType(myListType);
		/* <collection> */
		for2.setCollectionExpression(source);
		/* <collection/> */
		OJBlock body13 = new OJBlock();
		for2.setBody(body13);
		OJIfStatement if3 = new OJIfStatement();
		if3.setCondition(argStr + ".contains( elem )");
		body13.addToStatements(if3);
		OJBlock then3 = new OJBlock();
		if3.setThenPart(then3);
		OJSimpleStatement exp29 = new OJSimpleStatement("result.add( elem )");
		then3.addToStatements(exp29);
		OJSimpleStatement exp30 = new OJSimpleStatement("return result");
		body12.addToStatements(exp30);
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	private String buildFlatten(OperationCallExp exp,String source,List<String> args,boolean isStatic,List<OJParameter> params){
		CollectionKind collectionKind = getCollectionKind(exp);
		String operName = "";
		if(collectionKind == CollectionKind.SET_LITERAL){
			operName = "setFlatten";
		}else if(collectionKind == CollectionKind.BAG_LITERAL){
			operName = "bagFlatten";
		}else if(collectionKind == CollectionKind.SEQUENCE_LITERAL){
			operName = "sequenceFlatten";
		}else if(collectionKind == CollectionKind.ORDERED_SET_LITERAL){
			operName = "orderedsetFlatten";
		}
		myClass.addToImports(OclUtilityCreator.getStdlibPath());
		return "Stdlib." + operName + "(" + source + ")";
	}
	private String buildSubList(OperationCallExp exp,String source,List<String> args){
		String arguments = Util.collectionToString(args, "-1, ");
		return source + ".subList( " + arguments + " )";
	}
	private String buildSymmetricDifference(OperationCallExp exp,String source,List<String> args,boolean isStatic,List<OJParameter> params){
		ClassifierMap sourceMap = ojUtil.buildClassifierMap(exp.getType());
		Classifier elementType = (Classifier) exp.getReferredOperation().getType();
		String returnType = sourceMap.javaType();
		OJPathName returnTypePath = sourceMap.javaTypePath();
		// myClass.addToImports(sourceMap.javaTypePath());
		OJPathName myListType = expGeneratorHelper.makeListType(elementType);
		// myClass.addToImports(JavaPathNames.Iterator);
		String operName = exp.getReferredOperation().getName() + myClass.getUniqueNumber();
		String argStr = StringHelpers.addBrackets((String) args.get(0));
		OJOperation oper = null;
		// make copy of source => no side effects allowed
		source = makeCopyOfSource(exp, source);
		oper = new OJOperation(operName);
		myClass.addToOperations(oper);
		oper.setReturnType(returnTypePath);
		
		oper.setVisibility(OJVisibilityKind.PRIVATE);
		oper.setStatic(isStatic);
		oper.setParameters(params);
		oper.setComment("implements " + exp.toString() + " on " + exp.getSource().toString());
		OJBlock body14 = new OJBlock();
		oper.setBody(body14);
		OJSimpleStatement exp31 = new OJSimpleStatement(returnType + " result = " + source);
		body14.addToStatements(exp31);
		OJSimpleStatement exp32 = new OJSimpleStatement("result.removeAll(" + argStr + ")");
		body14.addToStatements(exp32);
		/* <for> */
		OJForStatement for3 = new OJForStatement();
		body14.addToStatements(for3);
		for3.setElemName("elem");
		for3.setElemType(myListType);
		/* <collection> */
		for3.setCollectionExpression(argStr);
		/* <collection/> */
		OJBlock body15 = new OJBlock();
		for3.setBody(body15);
		OJIfStatement if4 = new OJIfStatement();
		if4.setCondition("!" + source + ".contains(elem)");
		body15.addToStatements(if4);
		OJBlock then4 = new OJBlock();
		if4.setThenPart(then4);
		OJSimpleStatement exp33 = new OJSimpleStatement("result.add(elem)");
		then4.addToStatements(exp33);
		/* <then/> */
		/* <if/> */
		/* <for/> */
		OJSimpleStatement exp34 = new OJSimpleStatement("return result");
		body14.addToStatements(exp34);
		// generate call to new oper
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	private String buildSum(OperationCallExp exp,String source,boolean isStatic,List<OJParameter> params){
		Classifier elementType = (Classifier) exp.getReferredOperation().getType();
		ClassifierMap elementMap = ojUtil.buildClassifierMap(exp.getType());
		String myType = elementMap.javaType();
		// myClass.addToImports(elementMap.javaTypePath());
		String myDefault = elementMap.javaDefaultValue();
		String operName = exp.getReferredOperation().getName() + myClass.getUniqueNumber();
		OJOperation oper = null;
		// myClass.addToImports(JavaPathNames.Iterator);
		//
		String sumExp = "result + " + typeHelper.listElemToValue(elementType, "elem");
		if(!(elementType instanceof PrimitiveType)){ // the model class
			// defines the
			// '+' operation
			sumExp = "result." + OperationMap.javaPlusOperName() + "(elem)";
		}
		oper = new OJOperation(operName);
		myClass.addToOperations(oper);
		oper.setReturnType(elementMap.javaTypePath());
		
		oper.setVisibility(OJVisibilityKind.PRIVATE);
		oper.setStatic(isStatic);
		oper.setParameters(params);
		oper.setComment("implements " + exp.toString() + " on " + exp.getSource().toString());
		OJBlock body16 = new OJBlock();
		oper.setBody(body16);
		OJSimpleStatement exp35 = new OJSimpleStatement(myType + " result = " + myDefault);
		body16.addToStatements(exp35);
		OJForStatement for4 = new OJForStatement();
		body16.addToStatements(for4);
		for4.setElemName("elem");
		for4.setElemType(elementMap.javaTypePath());
		/* <collection> */
		for4.setCollectionExpression(source);
		/* <collection/> */
		OJBlock body17 = new OJBlock();
		for4.setBody(body17);
		OJSimpleStatement exp36 = new OJSimpleStatement("result = " + sumExp);
		body17.addToStatements(exp36);
		/* <for/> */
		OJSimpleStatement exp37 = new OJSimpleStatement("return result");
		body16.addToStatements(exp37);
		// generate call to new oper
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	/**
	 * @param exp
	 * @param source
	 * @param args
	 * @return
	 */
	private String buildExcludes(OperationCallExp exp,String source,List<String> args){
		Classifier type = ((OCLExpression) exp.getArgument().get(0)).getType();
		String argStr = expGeneratorHelper.makeListElem(myClass, type, (String) args.get(0));
		return "!" + source + ".contains(" + argStr + ")";
	}
	/**
	 * @param exp
	 * @param source
	 * @param args
	 * @return
	 */
	private String buildExcludesAll(OperationCallExp exp,String source,List<String> args){
		String operName = "excludesAll";
		myClass.addToImports(OclUtilityCreator.getStdlibPath());
		return "Stdlib." + operName + "(" + source + ", " + args.get(0) + ")";
	}
	/**
	 * @param exp
	 * @param source
	 * @param args
	 * @return
	 */
	private String buildIncludes(OperationCallExp exp,String source,List<String> args){
		Classifier type = ((OCLExpression) exp.getArgument().get(0)).getType();
		String argStr = expGeneratorHelper.makeListElem(myClass, type, (String) args.get(0));
		return source + ".contains(" + argStr + ")";
	}
	/**
	 * @param exp
	 * @param source
	 * @param args
	 * @return
	 */
	private String buildIncludesAll(OperationCallExp exp,String source,List<String> args){
		Classifier type = ((OCLExpression) exp.getArgument().get(0)).getType();
		String argStr = expGeneratorHelper.makeListElem(myClass, type, (String) args.get(0));
		return source + ".containsAll(" + argStr + ")";
	}
	/**
	 * @param exp
	 * @param source
	 * @param args
	 * @return
	 */
	private String buildCount(OperationCallExp exp,String source,List<String> args,boolean isStatic,List<OJParameter> params){
		// args should hold only one element
		Classifier elementType = (Classifier) exp.getReferredOperation().getType();
		OJPathName myType = expGeneratorHelper.makeListType(elementType);
		OJOperation oper = null;
		String operName = "count" + myClass.getUniqueNumber();
		String argStr = expGeneratorHelper.makeListElem(myClass, elementType, (String) args.get(0));
		// generate separate operation
		/**
		 * <octel var="myClass"> <method type="%JavaPathNames.Int%" name="%operName%" visibility="%OJVisibilityKind.PRIVATE%"
		 * static="%isStatic%" var="oper"> <comment> implements %exp.toString()% on %exp.getSource().toString()% </comment> <paramlist> %params
		 * </paramlist> <body> int result = 0; <for name="elem" type="%myType%"> <collection>%source%</collection> <body> <if> elem.equals(
		 * %argStr% ) <then> result = result + 1; </then></if> </body></for> return result; </body> </method> </octel>
		 */
		/* <method> */
		oper = new OJOperation(operName);
		myClass.addToOperations(oper);
		oper.setReturnType(JavaPathNames.Int);
		
		oper.setVisibility(OJVisibilityKind.PRIVATE);
		oper.setStatic(isStatic);
		oper.setComment("implements " + exp.toString() + " on " + exp.getSource().toString());
		oper.setParameters(params);
		OJBlock body18 = new OJBlock();
		oper.setBody(body18);
		OJSimpleStatement exp38 = new OJSimpleStatement("int result = 0");
		body18.addToStatements(exp38);
		/* <for> */
		OJForStatement for5 = new OJForStatement();
		body18.addToStatements(for5);
		for5.setElemName("elem");
		for5.setElemType(myType);
		/* <collection> */
		for5.setCollectionExpression(source);
		/* <collection/> */
		OJBlock body19 = new OJBlock();
		for5.setBody(body19);
		OJIfStatement if5 = new OJIfStatement();
		if5.setCondition("elem.equals( " + argStr + " )");
		body19.addToStatements(if5);
		OJBlock then5 = new OJBlock();
		if5.setThenPart(then5);
		OJSimpleStatement exp39 = new OJSimpleStatement("result = result + 1");
		then5.addToStatements(exp39);
		OJSimpleStatement exp40 = new OJSimpleStatement("return result");
		body18.addToStatements(exp40);
		oper.setParameters(params);
		// generate call to new oper
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
}
