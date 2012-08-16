package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.codegen.helpers.EmfPropertyCallHelper;
import nl.klasse.octopus.codegen.umlToJava.common.ExpGeneratorHelper;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StdlibMap;
import nl.klasse.tools.common.StringHelpers;

import org.eclipse.emf.common.util.EList;
import org.eclipse.ocl.uml.CollectionType;
import org.eclipse.ocl.uml.FeatureCallExp;
import org.eclipse.ocl.uml.IterateExp;
import org.eclipse.ocl.uml.IteratorExp;
import org.eclipse.ocl.uml.LoopExp;
import org.eclipse.ocl.uml.OCLExpression;
import org.eclipse.ocl.uml.PropertyCallExp;
import org.eclipse.ocl.uml.Variable;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Parameter;
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
import org.opaeum.java.metamodel.utilities.JavaPathNames;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.ocl.uml.AbstractOclContext;

public class LoopExpCreator{
	private OJClass myClass = null;
	private OJVisibilityKind priv = OJVisibilityKind.PRIVATE;
	private String[] iterVarNames = {"it"};
	private String resultType = "";
	private OJPathName resultTypePath = null;
	private String resultDefault = "";
	private String myListType = "";
	private OJPathName myListTypePath = null;
	private String myListDefault = "";
	private String expStr = "";
	private String expType = "";
	private String operName = "";
	private Classifier elementType = null;
	ExpGeneratorHelper expGeneratorHelper;
	private OJUtil ojUtil;
	private AbstractOclContext context;
	public LoopExpCreator(ExpGeneratorHelper h,OJClass myClass,AbstractOclContext context){
		super();
		this.expGeneratorHelper = h;
		this.ojUtil = h.ojUtil;
		this.myClass = myClass;
		this.context = context;
	}
	public String iteratorExp(IteratorExp exp,StringBuilder source,boolean isStatic,List<OJParameter> params){
		String result = "";
		setVariables(exp, isStatic, params);
		String iteratorName = exp.getName();
		if(iteratorName.equals("forAll")){
			result = createForAll(exp, source, isStatic, params);
		}else if(iteratorName.equals("exists")){
			result = createExists(exp, source, isStatic, params);
		}else if(iteratorName.equals("isUnique")){
			result = createIsUnique(exp, source, isStatic, params);
		}else if(iteratorName.equals("one")){
			result = createOne(exp, source, isStatic, params);
		}else if(iteratorName.equals("any")){
			result = createAny(exp, source, isStatic, params);
		}else if(iteratorName.equals("reject")){
			result = createReject(exp, source, isStatic, params);
		}else if(iteratorName.equals("select")){
			result = createSelect(exp, source, isStatic, params);
		}else if(iteratorName.equals("sortedBy")){
			result = createSortedBy(exp, source, isStatic, params);
		}else if(iteratorName.equals("collectNested")){
			result = createCollectNested(exp, source, isStatic, params);
		}else if(iteratorName.equals("collect")){
			result = createCollect(exp, source, isStatic, params);
		}
		// add myListTypePath to the imports, except for the sortedBy which does
		// not use it.
		if(!iteratorName.equals("sortedBy")){
			myClass.addToImports(myListTypePath);
		}
		return result;
	}
	/**
	 * @param object
	 * @return
	 */
	public String iterateExp(IterateExp exp,StringBuilder source,boolean isStatic,List<OJParameter> params){
		// get the result variable of the expression
		ExpressionCreator maker = new ExpressionCreator(ojUtil, myClass, context);
		String varDeclStr = maker.makeVarDecl((Variable) exp.getResult(), isStatic, params);
		List<OJParameter> bodyParams = expGeneratorHelper.addVarToParams((Variable) exp.getResult(), params);
		String resultName = ExpGeneratorHelper.javaFieldName((Variable) exp.getResult());
		// get info
		setVariables(exp, isStatic, bodyParams);
		// generate a separate operation
		OJOperation oper = null;
		if(exp.getIterator().size() == 2){
			oper = new OJOperation(operName);
			myClass.addToOperations(oper);
			oper.setReturnType(resultTypePath);
			
			oper.setVisibility(priv);
			oper.setStatic(isStatic);
			oper.setComment("implements " + exp.toString());
			oper.setParameters(params);
			OJBlock body1 = new OJBlock();
			oper.setBody(body1);
			OJSimpleStatement exp1 = new OJSimpleStatement(varDeclStr);
			body1.addToStatements(exp1);
			OJSimpleStatement exp2 = new OJSimpleStatement("Iterator it = " + source + ".iterator()");
			body1.addToStatements(exp2);
			/* <while> */
			OJWhileStatement while1 = new OJWhileStatement();
			body1.addToStatements(while1);
			while1.setCondition("it.hasNext()");
			OJBlock body2 = new OJBlock();
			while1.setBody(body2);
			OJSimpleStatement exp3 = new OJSimpleStatement(myListType + " " + iterVarNames[0] + " = (" + myListType + ") it.next()");
			body2.addToStatements(exp3);
			/* <while> */
			OJWhileStatement while2 = new OJWhileStatement();
			body2.addToStatements(while2);
			while2.setCondition("it.hasNext()");
			OJBlock body3 = new OJBlock();
			while2.setBody(body3);
			OJSimpleStatement exp4 = new OJSimpleStatement(myListType + " " + iterVarNames[1] + " = (" + myListType + ") it.next()");
			body3.addToStatements(exp4);
			OJSimpleStatement exp5 = new OJSimpleStatement(resultName + " = " + expStr);
			body3.addToStatements(exp5);
			/* <while/> */
			/* <while/> */
			OJSimpleStatement exp6 = new OJSimpleStatement("return " + resultName);
			body1.addToStatements(exp6);
			myClass.addToImports(JavaPathNames.Iterator);
		}else if(exp.getIterator().size() == 1){
			oper = new OJOperation(operName);
			myClass.addToOperations(oper);
			oper.setReturnType(resultTypePath);
			
			oper.setVisibility(priv);
			oper.setStatic(isStatic);
			oper.setComment("implements " + exp.toString());
			oper.setParameters(params);
			OJBlock body4 = new OJBlock();
			oper.setBody(body4);
			OJSimpleStatement exp7 = new OJSimpleStatement(varDeclStr);
			body4.addToStatements(exp7);
			/* <for> */
			OJForStatement for1 = new OJForStatement();
			body4.addToStatements(for1);
			for1.setElemName(iterVarNames[0]);
			for1.setElemType(myListTypePath);
			/* <collection> */
			for1.setCollectionExpression(source.toString());
			/* <collection/> */
			OJBlock body5 = new OJBlock();
			for1.setBody(body5);
			OJSimpleStatement exp8 = new OJSimpleStatement(resultName + " = " + expStr);
			body5.addToStatements(exp8);
			/* <for/> */
			OJSimpleStatement exp9 = new OJSimpleStatement("return " + resultName);
			body4.addToStatements(exp9);
		}
		// generate the call to the created operation
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	private String createCollect(IteratorExp exp,StringBuilder source,boolean isStatic,List<OJParameter> params){
		OCLExpression body = (OCLExpression) exp.getBody();
		expStr = expGeneratorHelper.makeListElem(myClass, body.getType(), expStr);
		Classifier argType = body.getType();
		// flatten
		String myBodyExp = "";
		if(argType instanceof CollectionType || EmfPropertyCallHelper.resultsInMany(body)){
			myBodyExp = "result.addAll( bodyExpResult )";
		}else{
			myBodyExp = "if ( bodyExpResult != null ) result.add( bodyExpResult )";
		}
		OJOperation oper = new OJOperation(operName);
		myClass.addToOperations(oper);
		oper.setReturnType(resultTypePath);
		
		oper.setVisibility(priv);
		oper.setStatic(isStatic);
		oper.setComment("implements " + exp.toString());
		oper.setParameters(params);
		OJBlock body6 = new OJBlock();
		oper.setBody(body6);
		OJSimpleStatement exp10 = new OJSimpleStatement(resultType + " result = " + resultDefault);
		body6.addToStatements(exp10);
		OJForStatement forEach = new OJForStatement();
		body6.addToStatements(forEach);
		forEach.setElemName(iterVarNames[0]);
		forEach.setElemType(myListTypePath);
		forEach.setCollectionExpression(source.toString());
		OJBlock body7 = new OJBlock();
		forEach.setBody(body7);
		OJSimpleStatement exp11 = new OJSimpleStatement(expType + " bodyExpResult = " + expStr);
		body7.addToStatements(exp11);
		OJSimpleStatement exp12 = new OJSimpleStatement(myBodyExp);
		body7.addToStatements(exp12);
		OJSimpleStatement exp13 = new OJSimpleStatement("return result");
		body6.addToStatements(exp13);
		// generate the call to the created operation
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	private String createCollectNested(IteratorExp exp,StringBuilder source,boolean isStatic,List<OJParameter> params){
		// make sure expressions of primitive type can be added to a collection
		expStr = expGeneratorHelper.makeListElem(myClass, exp.getBody().getType(), expStr);
		// generate operation
		OJOperation oper = null;
		/**
		 * <octel var="myClass"> <method type="%resultTypePath%" name="%operName%" visibility="%priv%" static="%isStatic%" var="oper"> <comment>
		 * implements %exp.toString()% <paramlist> <body> %resultType% result = %resultDefault%; <for name="%iterVarNames[0]%"
		 * type="%myListTypePath%"> <collection>%source.toString()%</collection> <body> %expType% bodyExpResult = %expStr%; if ( bodyExpResult
		 * != null ) result.add( bodyExpResult ); </body></for> return result; </body> </method> </octel>
		 */
		/* <method> */
		oper = new OJOperation(operName);
		myClass.addToOperations(oper);
		oper.setReturnType(resultTypePath);
		
		oper.setVisibility(priv);
		oper.setStatic(isStatic);
		oper.setComment("implements " + exp.toString());
		oper.setParameters(params);
		OJBlock body8 = new OJBlock();
		oper.setBody(body8);
		OJSimpleStatement exp14 = new OJSimpleStatement(resultType + " result = " + resultDefault);
		body8.addToStatements(exp14);
		/* <for> */
		OJForStatement for3 = new OJForStatement();
		body8.addToStatements(for3);
		for3.setElemName(iterVarNames[0]);
		for3.setElemType(myListTypePath);
		/* <collection> */
		for3.setCollectionExpression(source.toString());
		/* <collection/> */
		OJBlock body9 = new OJBlock();
		for3.setBody(body9);
		OJSimpleStatement exp15 = new OJSimpleStatement(expType + " bodyExpResult = " + expStr);
		body9.addToStatements(exp15);
		OJSimpleStatement exp16 = new OJSimpleStatement("if ( bodyExpResult != null ) result.add( bodyExpResult )");
		body9.addToStatements(exp16);
		/* <for/> */
		OJSimpleStatement exp17 = new OJSimpleStatement("return result");
		body8.addToStatements(exp17);
		// generate the call to the created operation
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	private String createSortedBy(IteratorExp exp,StringBuilder source,boolean isStatic,List<OJParameter> params){
		// TODO: Iterator not needed in this implementation but cannot remove in
		// case others are using it
		// myClass.removeFromImports(%JavaPathNames.Iterator%);
		//
		String iterVarName = iterVarNames[0];
		OJPathName comparatorClassName = new ComparatorCreator(ojUtil)
				.makeComparator(elementType, exp.getBody().getType(), expStr, iterVarName);
		myClass.addToImports(JavaPathNames.Comparator);
		myClass.addToImports(JavaPathNames.Collections);
		// myClass.addToImports("utils.Util");
		myClass.addToImports(comparatorClassName);
		// generate operation
		OJOperation oper = null;
		oper = new OJOperation(operName);
		myClass.addToOperations(oper);
		oper.setReturnType(resultTypePath);
		
		oper.setVisibility(priv);
		oper.setStatic(isStatic);
		oper.setComment("implements " + exp.toString());
		oper.setParameters(params);
		OJBlock body10 = new OJBlock();
		oper.setBody(body10);
		OJSimpleStatement exp18 = new OJSimpleStatement(resultType + " result = " + resultDefault);
		body10.addToStatements(exp18);
		OJSimpleStatement exp19 = new OJSimpleStatement("result.addAll(" + source + ")");
		body10.addToStatements(exp19);
		OJSimpleStatement exp20 = new OJSimpleStatement("// System.out.println(\"BEFORE SORTING ON " + expStr + "\")");
		body10.addToStatements(exp20);
		OJSimpleStatement exp21 = new OJSimpleStatement("// System.out.println(Util.collectionToString(result, \"\\n\"))");
		body10.addToStatements(exp21);
		OJSimpleStatement exp22 = new OJSimpleStatement("// create a new Comparator instance");
		body10.addToStatements(exp22);
		OJSimpleStatement exp23 = new OJSimpleStatement("Comparator comp = new " + comparatorClassName.getLast() + "()");
		body10.addToStatements(exp23);
		OJSimpleStatement exp24 = new OJSimpleStatement("// sort the result collection");
		body10.addToStatements(exp24);
		OJSimpleStatement exp25 = new OJSimpleStatement("Collections.sort(result, comp)");
		body10.addToStatements(exp25);
		OJSimpleStatement exp26 = new OJSimpleStatement("// System.out.println(\"AFTER SORTING ON " + expStr + "\")");
		body10.addToStatements(exp26);
		OJSimpleStatement exp27 = new OJSimpleStatement("// System.out.println(Util.collectionToString(result, \"\\n\"))");
		body10.addToStatements(exp27);
		OJSimpleStatement exp28 = new OJSimpleStatement("return result");
		body10.addToStatements(exp28);
		// generate the call to the created operation
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	private String createSelect(IteratorExp exp,StringBuilder source,boolean isStatic,List<OJParameter> params){
		// generate operation
		OJOperation oper = null;
		oper = new OJOperation(operName);
		myClass.addToOperations(oper);
		oper.setReturnType(resultTypePath);
		
		oper.setVisibility(priv);
		oper.setStatic(isStatic);
		oper.setComment("implements " + exp.toString());
		oper.setParameters(params);
		OJBlock body11 = new OJBlock();
		oper.setBody(body11);
		OJSimpleStatement exp29 = new OJSimpleStatement(resultType + " result = " + resultDefault);
		body11.addToStatements(exp29);
		OJForStatement for4 = new OJForStatement();
		body11.addToStatements(for4);
		for4.setElemName(iterVarNames[0]);
		for4.setElemType(myListTypePath);
		for4.setCollectionExpression(source.toString());
		OJBlock body12 = new OJBlock();
		for4.setBody(body12);
		OJIfStatement if1 = new OJIfStatement();
		if1.setCondition(expStr);
		body12.addToStatements(if1);
		OJBlock then1 = new OJBlock();
		if1.setThenPart(then1);
		OJSimpleStatement exp30 = new OJSimpleStatement("result.add( " + iterVarNames[0] + " )");
		then1.addToStatements(exp30);
		OJSimpleStatement exp31 = new OJSimpleStatement("return result");
		body11.addToStatements(exp31);
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	private String createReject(IteratorExp exp,StringBuilder source,boolean isStatic,List<OJParameter> params){
		expStr = StringHelpers.addBrackets(expStr);
		// generate operation
		OJOperation oper = null;
		oper = new OJOperation(operName);
		myClass.addToOperations(oper);
		oper.setReturnType(resultTypePath);
		
		oper.setVisibility(priv);
		oper.setStatic(isStatic);
		oper.setComment("implements " + exp.toString());
		oper.setParameters(params);
		OJBlock body13 = new OJBlock();
		oper.setBody(body13);
		OJSimpleStatement exp32 = new OJSimpleStatement(resultType + " result = " + resultDefault);
		body13.addToStatements(exp32);
		OJForStatement for5 = new OJForStatement();
		body13.addToStatements(for5);
		for5.setElemName(iterVarNames[0]);
		for5.setElemType(myListTypePath);
		for5.setCollectionExpression(source.toString());
		OJBlock body14 = new OJBlock();
		for5.setBody(body14);
		OJIfStatement if2 = new OJIfStatement();
		if2.setCondition("!" + expStr);
		body14.addToStatements(if2);
		OJBlock then2 = new OJBlock();
		if2.setThenPart(then2);
		OJSimpleStatement exp33 = new OJSimpleStatement("result.add( " + iterVarNames[0] + " )");
		then2.addToStatements(exp33);
		OJSimpleStatement exp34 = new OJSimpleStatement("return result");
		body13.addToStatements(exp34);
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	private String createAny(IteratorExp exp,StringBuilder source,boolean isStatic,List<OJParameter> params){
		OJOperation oper = null;
		oper = new OJOperation(operName);
		myClass.addToOperations(oper);
		oper.setReturnType(myListTypePath);
		
		oper.setVisibility(priv);
		oper.setStatic(isStatic);
		oper.setComment("implements " + exp.toString());
		oper.setParameters(params);
		OJBlock body15 = new OJBlock();
		oper.setBody(body15);
		OJSimpleStatement exp35 = new OJSimpleStatement(myListType + " result = " + myListDefault);
		body15.addToStatements(exp35);
		OJForStatement for6 = new OJForStatement();
		body15.addToStatements(for6);
		for6.setElemName(iterVarNames[0]);
		for6.setElemType(myListTypePath);
		for6.setCollectionExpression(source.toString());
		OJBlock body16 = new OJBlock();
		for6.setBody(body16);
		OJIfStatement if3 = new OJIfStatement();
		if3.setCondition(expStr);
		body16.addToStatements(if3);
		OJBlock then3 = new OJBlock();
		if3.setThenPart(then3);
		OJSimpleStatement exp36 = new OJSimpleStatement("return " + iterVarNames[0]);
		then3.addToStatements(exp36);
		OJSimpleStatement exp37 = new OJSimpleStatement("return result");
		body15.addToStatements(exp37);
		// generate the call to the created operation
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	private String createOne(IteratorExp exp,StringBuilder source,boolean isStatic,List<OJParameter> params){
		// generate operation
		OJOperation oper = null;
		/**
		 * <octel var="myClass"> <method type="%resultTypePath%" name="%operName%" visibility="%priv%" static="%isStatic%" var="oper"> <comment>
		 * implements %exp.toString()% <paramlist> <body> int _genNr = 0; <for name="%iterVarNames[0]%" type="%myListTypePath%">
		 * <collection>%source.toString()%<if> <octel>
		 */
		/* <method> */
		oper = new OJOperation(operName);
		myClass.addToOperations(oper);
		oper.setReturnType(resultTypePath);
		
		oper.setVisibility(priv);
		oper.setStatic(isStatic);
		oper.setComment("implements " + exp.toString());
		oper.setParameters(params);
		OJBlock body17 = new OJBlock();
		oper.setBody(body17);
		OJSimpleStatement exp38 = new OJSimpleStatement("int _genNr = 0");
		body17.addToStatements(exp38);
		/* <for> */
		OJForStatement for7 = new OJForStatement();
		body17.addToStatements(for7);
		for7.setElemName(iterVarNames[0]);
		for7.setElemType(myListTypePath);
		/* <collection> */
		for7.setCollectionExpression(source.toString());
		/* <collection/> */
		OJBlock body18 = new OJBlock();
		for7.setBody(body18);
		OJIfStatement if4 = new OJIfStatement();
		if4.setCondition(expStr);
		body18.addToStatements(if4);
		OJBlock then4 = new OJBlock();
		if4.setThenPart(then4);
		OJSimpleStatement exp39 = new OJSimpleStatement("_genNr++");
		then4.addToStatements(exp39);
		/* <then/> */
		/* <if/> */
		/* <for/> */
		OJIfStatement if5 = new OJIfStatement();
		if5.setCondition("_genNr != 1");
		body17.addToStatements(if5);
		OJBlock then5 = new OJBlock();
		if5.setThenPart(then5);
		OJSimpleStatement exp40 = new OJSimpleStatement("return false");
		then5.addToStatements(exp40);
		/* <then/> */
		/* <if/> */
		OJSimpleStatement exp41 = new OJSimpleStatement("return true");
		body17.addToStatements(exp41);
		// generate the call to the created operation
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	private String createIsUnique(IteratorExp exp,StringBuilder source,boolean isStatic,List<OJParameter> params){
		expStr = expGeneratorHelper.makeListElem(myClass, exp.getBody().getType(), expStr);
		ClassifierMap map = ojUtil.buildClassifierMap(exp.getBody().getType());
		OJPathName valueType = JavaPathNames.List.getCopy();
		valueType.addToElementTypes(map.javaDefaultTypePath());
		OJPathName valueImplType = JavaPathNames.ArrayList.getCopy();
		valueImplType.addToElementTypes(map.javaDefaultTypePath());
		// generate operation
		OJOperation oper = null;
		/**
		 * <octel var="myClass"> <method type="%resultTypePath%" name="%operName%" visibility="%priv%" static="%isStatic%" var="oper"> <comment>
		 * implements %exp.toString()% <paramlist> <body> %valueType.getCollectionTypeName()% _valuesInSource = new
		 * %valueImplType.getCollectionTypeName()%(); <for name="%iterVarNames[0]%" type="%myListTypePath%"> <collection>%source.toString()%<if>
		 * _valuesInSource.add(%expStr%); </body></for> return true; </body> </method> </octel>
		 */
		/* <method> */
		oper = new OJOperation(operName);
		myClass.addToOperations(oper);
		oper.setReturnType(resultTypePath);
		
		oper.setVisibility(priv);
		oper.setStatic(isStatic);
		oper.setComment("implements " + exp.toString());
		oper.setParameters(params);
		OJBlock body19 = new OJBlock();
		oper.setBody(body19);
		OJSimpleStatement exp42 = new OJSimpleStatement(valueType.getTypeNameWithTypeArguments() + " _valuesInSource = new "
				+ valueImplType.getTypeNameWithTypeArguments() + "()");
		body19.addToStatements(exp42);
		/* <for> */
		OJForStatement for8 = new OJForStatement();
		body19.addToStatements(for8);
		for8.setElemName(iterVarNames[0]);
		for8.setElemType(myListTypePath);
		/* <collection> */
		for8.setCollectionExpression(source.toString());
		/* <collection/> */
		OJBlock body20 = new OJBlock();
		for8.setBody(body20);
		OJIfStatement if6 = new OJIfStatement();
		if6.setCondition("_valuesInSource.contains(" + expStr + ")");
		body20.addToStatements(if6);
		OJBlock then6 = new OJBlock();
		if6.setThenPart(then6);
		OJSimpleStatement exp43 = new OJSimpleStatement("return false");
		then6.addToStatements(exp43);
		/* <then/> */
		/* <if/> */
		OJSimpleStatement exp44 = new OJSimpleStatement("_valuesInSource.add(" + expStr + ")");
		body20.addToStatements(exp44);
		/* <for/> */
		OJSimpleStatement exp45 = new OJSimpleStatement("return true");
		body19.addToStatements(exp45);
		// generate the call to the created operation
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	private String createExists(IteratorExp exp,StringBuilder source,boolean isStatic,List<OJParameter> params){
		// generate operation
		OJOperation oper = null;
		/**
		 * <octel var="myClass"> <method type="%resultTypePath%" name="%operName%" visibility="%priv%" static="%isStatic%" var="oper"> <comment>
		 * implements %exp.toString()% <paramlist> <body> <for name="%iterVarNames[0]%" type="%myListTypePath%">
		 * <collection>%source.toString()%<if> </body></for> return false; </body> </method> </octel>
		 */
		/* <method> */
		oper = new OJOperation(operName);
		myClass.addToOperations(oper);
		oper.setReturnType(resultTypePath);
		
		oper.setVisibility(priv);
		oper.setStatic(isStatic);
		oper.setComment("implements " + exp.toString());
		oper.setParameters(params);
		OJBlock body21 = new OJBlock();
		oper.setBody(body21);
		/* <for> */
		OJForStatement for9 = new OJForStatement();
		body21.addToStatements(for9);
		for9.setElemName(iterVarNames[0]);
		for9.setElemType(myListTypePath);
		/* <collection> */
		for9.setCollectionExpression(source.toString());
		/* <collection/> */
		OJBlock body22 = new OJBlock();
		for9.setBody(body22);
		OJIfStatement if7 = new OJIfStatement();
		if7.setCondition(expStr);
		body22.addToStatements(if7);
		OJBlock then7 = new OJBlock();
		if7.setThenPart(then7);
		OJSimpleStatement exp46 = new OJSimpleStatement("return true");
		then7.addToStatements(exp46);
		/* <then/> */
		/* <if/> */
		/* <for/> */
		OJSimpleStatement exp47 = new OJSimpleStatement("return false");
		body21.addToStatements(exp47);
		// generate the call to the created operation
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	private String createForAll(IteratorExp exp,StringBuilder source,boolean isStatic,List<OJParameter> params){
		expStr = StringHelpers.addBrackets(expStr);
		OJOperation oper = null;
		if(iterVarNames.length == 1){
			// generate operation
			/**
			 * <octel var="myClass"> <method type="%JavaPathNames.Bool%" name="%operName%" visibility="%priv%" static="%isStatic%" var="oper">
			 * <comment> implements %exp.toString()% <paramlist> <body> <for name="%iterVarNames[0]%" type="%myListTypePath%">
			 * <collection>%source.toString()%<if> </body></for> return true; </body> </method> </octel>
			 */
			/* <method> */
			oper = new OJOperation(operName);
			myClass.addToOperations(oper);
			oper.setReturnType(JavaPathNames.Bool);
			
			oper.setVisibility(priv);
			oper.setStatic(isStatic);
			oper.setComment("implements " + exp.toString());
			oper.setParameters(params);
			OJBlock body23 = new OJBlock();
			oper.setBody(body23);
			/* <for> */
			OJForStatement for10 = new OJForStatement();
			body23.addToStatements(for10);
			for10.setElemName(iterVarNames[0]);
			for10.setElemType(myListTypePath);
			/* <collection> */
			for10.setCollectionExpression(source.toString());
			/* <collection/> */
			OJBlock body24 = new OJBlock();
			for10.setBody(body24);
			OJIfStatement if8 = new OJIfStatement();
			if8.setCondition("!" + expStr);
			body24.addToStatements(if8);
			OJBlock then8 = new OJBlock();
			if8.setThenPart(then8);
			OJSimpleStatement exp48 = new OJSimpleStatement("return false");
			then8.addToStatements(exp48);
			/* <then/> */
			/* <if/> */
			/* <for/> */
			OJSimpleStatement exp49 = new OJSimpleStatement("return true");
			body23.addToStatements(exp49);
		}else if(iterVarNames.length == 2){
			// generate operation
			/**
			 * <octel var="myClass"> <method type="%JavaPathNames.Bool%" name="%operName%" visibility="%priv%" static="%isStatic%" var="oper">
			 * <comment> implements %exp.toString()% <paramlist> <body> <for name="%iterVarNames[0]%" type="%myListTypePath%">
			 * <collection>%source.toString()%</collection> <body> <for name="%iterVarNames[1]%" type="%myListTypePath%">
			 * <collection>%source.toString()%<for> return true; </body> </method> </octel>
			 */
			/* <method> */
			oper = new OJOperation(operName);
			myClass.addToOperations(oper);
			oper.setReturnType(JavaPathNames.Bool);
			
			oper.setVisibility(priv);
			oper.setStatic(isStatic);
			oper.setComment("implements " + exp.toString());
			oper.setParameters(params);
			OJBlock body25 = new OJBlock();
			oper.setBody(body25);
			/* <for> */
			OJForStatement for11 = new OJForStatement();
			body25.addToStatements(for11);
			for11.setElemName(iterVarNames[0]);
			for11.setElemType(myListTypePath);
			/* <collection> */
			for11.setCollectionExpression(source.toString());
			/* <collection/> */
			OJBlock body26 = new OJBlock();
			for11.setBody(body26);
			/* <for> */
			OJForStatement for12 = new OJForStatement();
			body26.addToStatements(for12);
			for12.setElemName(iterVarNames[1]);
			for12.setElemType(myListTypePath);
			/* <collection> */
			for12.setCollectionExpression(source.toString());
			OJBlock body27 = new OJBlock();
			for12.setBody(body27);
			OJIfStatement if9 = new OJIfStatement();
			if9.setCondition("!" + expStr);
			body27.addToStatements(if9);
			OJBlock then9 = new OJBlock();
			if9.setThenPart(then9);
			OJSimpleStatement exp50 = new OJSimpleStatement("return false");
			then9.addToStatements(exp50);
			OJSimpleStatement exp51 = new OJSimpleStatement("return true");
			body25.addToStatements(exp51);
		}
		// generate the call to the created operation
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	private void setVariables(LoopExp exp,boolean isStatic,List<OJParameter> params){
		ClassifierMap nodeMap = ojUtil.buildClassifierMap(exp.getType());
		// the return type of the loop operation
		resultType = nodeMap.javaFacadeType();
		resultTypePath = nodeMap.javaTypePath();
		// the default value that will be returned if all else fails
		resultDefault = nodeMap.javaDefaultValue();
		// the type of the elements in source
		CollectionType sourceType = (CollectionType) exp.getSource().getType();
		elementType = sourceType.getElementType(); // in UML
		ClassifierMap elementMap = ojUtil.buildClassifierMap(elementType);
		myListTypePath = expGeneratorHelper.makeListType(elementType); // in
		// Java
		myListType = myListTypePath.getTypeName(); // in Java
		myListDefault = elementMap.javaDefaultValue();
		// TODO do we still need to import the types for these references
		// myClass.addToImports(nodeMap.javaFacadeTypePath());
		// myClass.addToImports(nodeMap.javaDefaultTypePath());
		// myClass.addToImports(elementMap.javaDefaultTypePath());
		// import the type for the Iterator
		// the names of the iterator variables
		iterVarNames = getIterVarNames(exp);
		// the string that holds the body of the loop exp
		ExpressionCreator myExpMaker = new ExpressionCreator(ojUtil, myClass, context);
		List<OJParameter> loopParams = addItersToParams(exp.getIterator(), params);
		expStr = myExpMaker.makeExpression((OCLExpression) exp.getBody(), isStatic, loopParams);
		ClassifierMap map = ojUtil.buildClassifierMap(exp.getBody().getType());
		if(!(exp.getBody().getType() instanceof CollectionType) && EmfPropertyCallHelper.resultsInMany((OCLExpression) exp.getBody())){
			expType = "Collection<" + map.javaType() + ">";
		}else if(map.isJavaPrimitive()){
			expType = map.javaObjectType();
		}else{
			expType = map.javaFacadeType();
		}
		// the name of the loop operation
		operName = exp.getName() + myClass.getUniqueNumber();
	}
	/**
	 * The iterator variable(s) needs to be added to any generated operation implementing the body expression. This method add the iterator
	 * variable(s) and replaces any variables that will be eclipsed by another name in the scope of the body expression.
	 * 
	 * @param params
	 * @return
	 */
	// TODO find out how the operation ExpGenerationHelper.addVarToParams can be
	// used
	private List<OJParameter> addItersToParams(EList<org.eclipse.ocl.expressions.Variable<Classifier,Parameter>> eList,
			List<OJParameter> params){
		List<OJParameter> result = new ArrayList<OJParameter>(params);
		Iterator<?> outerIt = eList.iterator();
		while(outerIt.hasNext()){
			Variable elem = (Variable) outerIt.next();
			Iterator<?> innerIt = params.iterator();
			while(innerIt.hasNext()){
				OJParameter par = (OJParameter) innerIt.next();
				if(ExpGeneratorHelper.javaFieldName(elem).equals(par.getName())){
					result.remove(par);
				}
			}
			result.add(expGeneratorHelper.varDeclToOJPar(elem));
		}
		return result;
	}
	private String[] getIterVarNames(LoopExp exp){
		String[] iterVarNames = new String[exp.getIterator().size()];
		Iterator<?> it = exp.getIterator().iterator();
		int i = 0;
		while(it.hasNext()){
			Variable iterVar = (Variable) it.next();
			iterVarNames[i] = ExpGeneratorHelper.javaFieldName(iterVar);
			i++;
		}
		return iterVarNames;
	}
}
