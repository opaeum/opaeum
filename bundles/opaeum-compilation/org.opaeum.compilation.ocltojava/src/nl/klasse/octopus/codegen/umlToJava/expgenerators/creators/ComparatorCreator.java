package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;

import nl.klasse.octopus.codegen.helpers.CommonNames;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Type;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.utilities.JavaPathNames;
import org.opaeum.javageneration.util.OJUtil;

public class ComparatorCreator{
	OJUtil ojUtil;
	public ComparatorCreator(OJUtil ojUtil){
		super();
		this.ojUtil = ojUtil;
	}
	private String buildComparatorName(Classifier elemType,Classifier exprType){
		String elemTypeName = ojUtil.buildClassifierMap(elemType).javaType();
		String exprTypeName = ojUtil.buildClassifierMap(exprType).javaType();
		return "Comp" + elemTypeName + "On" + exprTypeName;
	}
	public OJPathName makeComparator(Classifier elementType,Classifier exprType,String expStr,String iterVarName){
		OJClass created = new OJClass(buildComparatorName(elementType, exprType));
		OJPackage utilPack = UtilityCreator.getUtilPack();
		if(utilPack != null)
			utilPack.addToClasses(created);
		created.setComment(CommonNames.standardClassComment);
		created.addToImplementedInterfaces(JavaPathNames.Comparator);
		created.setVisibility(OJVisibilityKind.PUBLIC);
		makeCompareOp(created, elementType, exprType, expStr, iterVarName);
		created.addToImports(JavaPathNames.Comparator);
		created.addToImports(ojUtil.buildClassifierMap(elementType).javaTypePath());
		OJPathName result = created.getPathName();
		return result;
	}
	private void makeCompareOp(OJClass owner,Classifier elemType,Classifier exprType,String expString,String iterVarName){
		ClassifierMap elemTypeMap = ojUtil.buildClassifierMap(elemType);
		ClassifierMap exprTypeMap = ojUtil.buildClassifierMap(exprType);
		String elemTypeName = elemTypeMap.javaType();
		String exprTypeName = exprTypeMap.javaType();
		String exprDefault = exprTypeMap.javaDefaultValue();
		EList<Type> paramTypes = new BasicEList<Type>();
		paramTypes.add(exprType);
		owner.addToImports(elemTypeMap.javaTypePath());
		owner.addToImports(exprTypeMap.javaTypePath());
		Operation lessOper = exprType.getOperation("<", null, paramTypes);
		Operation moreOper = exprType.getOperation(">", null, paramTypes);
		String lessOperName = "<";
		String moreOperName = ">";
		if(lessOper != null && moreOper != null){
			if(lessOper.getType().getName().equals("Boolean") && moreOper.getType().getName().equals("Boolean")){
				OperationMap mapper = ojUtil.buildOperationMap(lessOper);
				lessOperName = mapper.javaOperName();
				mapper = ojUtil.buildOperationMap(moreOper);
				moreOperName = mapper.javaOperName();
			}
		}
		//
		OJBlock body = new OJBlock();
		OJOperation method1 = new OJOperation("compare");
		owner.addToOperations(method1);
		method1.setReturnType(JavaPathNames.Int);
		method1.setVisibility(OJVisibilityKind.PUBLIC);
		method1.addParam("arg0",JavaPathNames.Object);
		method1.addParam("arg1",JavaPathNames.Object);
		body = new OJBlock();
		method1.setBody(body);
		OJIfStatement if1 = new OJIfStatement();
		if1.setCondition("arg0 == null || arg1 == null");
		body.addToStatements(if1);
		OJBlock then1 = new OJBlock();
		if1.setThenPart(then1);
		OJSimpleStatement exp1 = new OJSimpleStatement("return 0");
		then1.addToStatements(exp1);
		/* <then/> */
		/* <if/> */
		OJSimpleStatement exp2 = new OJSimpleStatement("int result = 0");
		body.addToStatements(exp2);
		OJSimpleStatement exp3 = new OJSimpleStatement(exprTypeName + " value0 = " + exprDefault);
		body.addToStatements(exp3);
		OJSimpleStatement exp4 = new OJSimpleStatement(exprTypeName + " value1 = " + exprDefault);
		body.addToStatements(exp4);
		OJSimpleStatement exp5 = new OJSimpleStatement(elemTypeName + " " + iterVarName + " = null");
		body.addToStatements(exp5);
		OJIfStatement if2 = new OJIfStatement();
		if2.setCondition("arg0 instanceof " + elemTypeName);
		body.addToStatements(if2);
		OJBlock then2 = new OJBlock();
		if2.setThenPart(then2);
		OJSimpleStatement exp6 = new OJSimpleStatement(iterVarName + " = (" + elemTypeName + ") arg0");
		then2.addToStatements(exp6);
		OJSimpleStatement exp7 = new OJSimpleStatement("value0 = " + expString);
		then2.addToStatements(exp7);
		/* <then/> */
		/* <if/> */
		OJIfStatement if3 = new OJIfStatement();
		if3.setCondition("arg1 instanceof " + elemTypeName);
		body.addToStatements(if3);
		OJBlock then3 = new OJBlock();
		if3.setThenPart(then3);
		OJSimpleStatement exp8 = new OJSimpleStatement(iterVarName + " = (" + elemTypeName + ") arg1");
		then3.addToStatements(exp8);
		OJSimpleStatement exp9 = new OJSimpleStatement("value1 = " + expString);
		then3.addToStatements(exp9);
		/* <then/> */
		/* <if/> */
		if(exprTypeName.equals("boolean")){
			/**
			 * <octel var="body"> <if> value0 == value1 <then> return 0; </then></if> <if> value0 == true &amp;&amp; value1 == false <then> return
			 * -1; </then></if> <if> value0 == false &amp;&amp; value1 == true <then> return 1; </then></if> <exp>return result</exp> </octel>
			 */
			OJIfStatement if4 = new OJIfStatement();
			if4.setCondition("value0 == value1");
			body.addToStatements(if4);
			OJBlock then4 = new OJBlock();
			if4.setThenPart(then4);
			OJSimpleStatement exp10 = new OJSimpleStatement("return  0");
			then4.addToStatements(exp10);
			/* <then/> */
			/* <if/> */
			OJIfStatement if5 = new OJIfStatement();
			if5.setCondition("value0 == true && value1 == false");
			body.addToStatements(if5);
			OJBlock then5 = new OJBlock();
			if5.setThenPart(then5);
			OJSimpleStatement exp11 = new OJSimpleStatement("return  -1");
			then5.addToStatements(exp11);
			/* <then/> */
			/* <if/> */
			OJIfStatement if6 = new OJIfStatement();
			if6.setCondition("value0 == false && value1 == true");
			body.addToStatements(if6);
			OJBlock then6 = new OJBlock();
			if6.setThenPart(then6);
			OJSimpleStatement exp12 = new OJSimpleStatement("return  1");
			then6.addToStatements(exp12);
			/* <then/> */
			/* <if/> */
			OJSimpleStatement exp13 = new OJSimpleStatement("return result");
			body.addToStatements(exp13);
		}else if(exprTypeName.equals("String")){
			/**
			 * <octel var="body"> <exp>return value0.compareTo(value1)</exp> </octel>
			 */
			OJSimpleStatement exp14 = new OJSimpleStatement("return value0.compareTo(value1)");
			body.addToStatements(exp14);
		}else if(exprTypeName.equals("int") || exprTypeName.equals("float")){
			/**
			 * <octel var="body"> <if> value0 &lt; value1 <then> result = -1; </then></if> <if> value0 == value1 <then> result = 0; </then></if>
			 * <if> value0 > value1 <then> result = 1; </then></if> <exp>return result</exp> </octel>
			 */
			OJIfStatement if7 = new OJIfStatement();
			if7.setCondition("value0 < value1");
			body.addToStatements(if7);
			OJBlock then7 = new OJBlock();
			if7.setThenPart(then7);
			OJSimpleStatement exp15 = new OJSimpleStatement("result = -1");
			then7.addToStatements(exp15);
			/* <then/> */
			/* <if/> */
			OJIfStatement if8 = new OJIfStatement();
			if8.setCondition("value0 == value1");
			body.addToStatements(if8);
			OJBlock then8 = new OJBlock();
			if8.setThenPart(then8);
			OJSimpleStatement exp16 = new OJSimpleStatement("result = 0");
			then8.addToStatements(exp16);
			/* <then/> */
			/* <if/> */
			OJIfStatement if9 = new OJIfStatement();
			if9.setCondition("value0 > value1");
			body.addToStatements(if9);
			OJBlock then9 = new OJBlock();
			if9.setThenPart(then9);
			OJSimpleStatement exp17 = new OJSimpleStatement("result = 1");
			then9.addToStatements(exp17);
			/* <then/> */
			/* <if/> */
			OJSimpleStatement exp18 = new OJSimpleStatement("return result");
			body.addToStatements(exp18);
		}else{
			/**
			 * <octel var="body"> <if> value0.%moreOperName%( value1 )<then> result = 1; </then></if> <if> value0 == value1 <then> result = 0;
			 * </then></if> <if> value0.%lessOperName%( value1 )<then> result = -1; </then></if> <exp>return result</exp> </octel>
			 */
			OJIfStatement if10 = new OJIfStatement();
			if10.setCondition("value0." + moreOperName + "( value1 )");
			body.addToStatements(if10);
			OJBlock then10 = new OJBlock();
			if10.setThenPart(then10);
			OJSimpleStatement exp19 = new OJSimpleStatement("result = 1");
			then10.addToStatements(exp19);
			/* <then/> */
			/* <if/> */
			OJIfStatement if11 = new OJIfStatement();
			if11.setCondition("value0 == value1");
			body.addToStatements(if11);
			OJBlock then11 = new OJBlock();
			if11.setThenPart(then11);
			OJSimpleStatement exp20 = new OJSimpleStatement("result = 0");
			then11.addToStatements(exp20);
			/* <then/> */
			/* <if/> */
			OJIfStatement if12 = new OJIfStatement();
			if12.setCondition("value0." + lessOperName + "( value1 )");
			body.addToStatements(if12);
			OJBlock then12 = new OJBlock();
			if12.setThenPart(then12);
			OJSimpleStatement exp21 = new OJSimpleStatement("result = -1");
			then12.addToStatements(exp21);
			/* <then/> */
			/* <if/> */
			OJSimpleStatement exp22 = new OJSimpleStatement("return result");
			body.addToStatements(exp22);
		}
	}
}
