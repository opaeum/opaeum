/*
 * Created on Jun 4, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;
/**<octel>
<java>
	%import org.opeum.javametamodel.OJSimpleStatement%;
	%inet.sfsf.opeum.javametamodelametamodel.model.OJBlocknet.sf.sf.opeum.javametamodell.javametamodel.model.OJVisibilityKind%;
</java>
</octel>*/

/* <octel> */ 

/* <java> */ 

/* <inline> */ 
import java.util.List;

import nl.klasse.octopus.codegen.helpers.GenerationHelpers;
import nl.klasse.octopus.codegen.umlToJava.common.ExpGeneratorHelper;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.expressions.IIfExp;
import nl.klasse.octopus.expressions.ILetExp;
import nl.klasse.octopus.expressions.ILiteralExp;
import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.octopus.expressions.IOclMessageExp;
import nl.klasse.octopus.expressions.IPropertyCallExp;
import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.expressions.IVariableExp;
import nl.klasse.octopus.expressions.internal.types.LetExp;
import nl.klasse.octopus.expressions.internal.types.OclExpression;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.tools.common.StringHelpers;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.OJParameter;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.OJSimpleStatement;
import org.opeum.java.metamodel.OJVisibilityKind;

/**
 * ExpressionGenerator : 
 */
public class ExpressionCreator {
	private OJClass myClass = null;
	/**
	 * 
	 */
	public ExpressionCreator(OJClass myOwner) {
		super();
		this.myClass = myOwner;
	}
	/**
	 * @param expression
	 * @param isStatic
	 * @param list
	 * @return
	 */
	public String makeExpression(IOclExpression in, boolean isStatic, List<OJParameter> params) {
		StringBuffer thisNode = new StringBuffer();
		StringBuffer source = new StringBuffer();
		if (in instanceof IIfExp) {
			source.append(makeIfExpression((IIfExp)in, isStatic, params));
		} else if (in instanceof ILetExp) {
			source.append(makeLetExpression((LetExp)in, isStatic, params));
		} else if (in instanceof ILiteralExp) {
			LiteralExpCreator maker = new LiteralExpCreator(myClass);
			source.append(maker.makeExpression((ILiteralExp)in, isStatic, params));
		} else if (in instanceof IOclMessageExp) {
			// TODO OclMessageExp
		} else if (in instanceof IPropertyCallExp) {
			// only if the first node is a call to a class property
			PropCallCreator maker = new PropCallCreator(myClass);
			source.append(maker.makeExpressionNode((IPropertyCallExp)in, isStatic, params));
		} else if (in instanceof IVariableExp) {
			source.append(makeVariableExp((IVariableExp)in)); 
		} else {
			System.err.println("unspecified option in ExpressionGenerator.makeExpression");
		}
		if (in.getAppliedProperty() != null) {
			PropCallCreator maker = new PropCallCreator(myClass);
			thisNode.append(maker.makeExpression(in.getAppliedProperty(), source, isStatic, params));
		} else {
			thisNode.append(source);
		}
		return StringHelpers.addBrackets(thisNode.toString());
	}
	
	private String makeVariableExp(IVariableExp in) {
		String result = "";
		if (in.getName().equals("self")) {
			result = "this";				
		} else {
			IVariableDeclaration varDecl = in.getReferredVariable();
			if (varDecl.isIteratorVar()) {
				IClassifier type = varDecl.getType();
				if (type.getName().equals(IOclLibrary.BooleanTypeName)) {
					result = in.getName() + ".booleanValue()";						
				} else if (type.getName().equals(IOclLibrary.IntegerTypeName)) {
					result = in.getName() + ".intValue()";						
				} else if (type.getName().equals(IOclLibrary.RealTypeName)) {
					result = in.getName() + ".floatValue()";						
				} else {
					result = in.getName();						
				}				
			} else {
				result = in.getName();						
			}
		}
		return result;
	}

	public String makeVarDecl(IVariableDeclaration exp, boolean isStatic, List<OJParameter> params) {
		OclExpression initExpression = exp.getInitExpression();
		ClassifierMap mapper = new ClassifierMap(exp.getType());
		OJPathName myType = mapper.javaFacadeTypePath();
		myClass.addToImports(myType);
		String myInitExp = mapper.javaDefaultValue();
		// create init expression
		if( initExpression != null ) {
			myInitExp = makeExpression(initExpression, isStatic, params).toString();
		}
		// take care of facade
		if (GenerationHelpers.hasFacade(exp.getType())) {
			myInitExp = "(" + myType.getTypeName() + ")" + myInitExp;
		}
		//
		return (myType == null ? "void" : myType.getTypeName()) + " " + exp.getName() + 
				" = " + myInitExp;
	}
	
	private StringBuffer makeLetExpression(LetExp in, boolean isStatic, List<OJParameter> params) {
		// generate a separate operation
		ClassifierMap inMap = new ClassifierMap(in.getNodeType());
		String myType = inMap.javaType();
		String myDefault = inMap.javaDefaultValue();
		myClass.addToImports(inMap.javaTypePath());
		myClass.addToImports(inMap.javaDefaultTypePath());
		String operName = "letExpression" + myClass.getUniqueNumber();
		OJOperation oper = null;
		ClassifierMap variableMap = new ClassifierMap(in.getVariable().getType());
		myClass.addToImports(variableMap.javaTypePath());
		List<OJParameter> bodyParams = ExpGeneratorHelper.addVarToParams(in.getVariable(), params);
//		String bodyExp = createLetExpBody(in, myType, myDefault, isStatic, params);
		//
		/**<octel var="myClass">
		 	<method var="oper" type="%inMap.javaTypePath()%" name="%operName%" static="%isStatic%" visibility="%OJVisibilityKind.PRIVATE%">
		 	<comment> implements %in.toString()% </comment>		 	
			<body>
				%myType% result = %myDefault%;
				%makeVarDecl( in.getVariable(), isStatic, params)%;
				result = %makeExpression( in.getIn(), isStatic, bodyParams)%;
				return result;
			</body>
		 	</method>
		 </octel>*/

/* <octel> */ 

/* <method> */ 
oper = new OJOperation();
myClass.addToOperations(oper);

/* <type> */ 
oper.setReturnType(inMap.javaTypePath());

/* <type/> */ 

/* <name> */ 
oper.setName(operName);

/* <name/> */ 

/* <static> */ 
oper.setStatic(isStatic);

/* <static/> */ 

/* <visibility> */ 
oper.setVisibility(OJVisibilityKind.PRIVATE);

/* <visibility/> */ 

/* <comment> */ 
oper.setComment("implements " + in.toString());

/* <comment/> */ 

/* <body> */ 
OJBlock body1 = new OJBlock();
oper.setBody(body1);

/* <exp> */ 
OJSimpleStatement exp1 = new OJSimpleStatement(myType + " result = " + myDefault);
body1.addToStatements( exp1 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp2 = new OJSimpleStatement(makeVarDecl( in.getVariable(), isStatic, params));
body1.addToStatements( exp2 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp3 = new OJSimpleStatement("result = " + makeExpression( in.getIn(), isStatic, bodyParams));
body1.addToStatements( exp3 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp4 = new OJSimpleStatement("return result");
body1.addToStatements( exp4 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 


		oper.setParameters(params);
		 
		// generate the call to the created operation
		StringBuffer callOper = new StringBuffer();
		callOper.append(operName + "(" + OJOperation.paramsToActuals(oper)+ ")");
		return callOper; 
	}

	private StringBuffer makeIfExpression(IIfExp in, boolean isStatic, List<OJParameter> params) {
		StringBuffer created = new StringBuffer();
		ExpressionCreator myExpMaker = new ExpressionCreator(myClass);
		IClassifier thenType = in.getThenExpression().getExpressionType();
		IClassifier elseType = in.getElseExpression().getExpressionType();
		String typecast = "";
		if (thenType != elseType) {
			IClassifier commonSuperType = thenType.findCommonSuperType(elseType); 
			ClassifierMap mapper = new ClassifierMap(commonSuperType);
			typecast = "(" + mapper.javaType() + ")";
			myClass.addToImports(mapper.javaTypePath());
		}
		String condition = StringHelpers.addBrackets(myExpMaker.makeExpression(in.getCondition(), isStatic, params));
		StringBuffer thenPart = new StringBuffer();
		StringBuffer elsePart = new StringBuffer();
		thenPart.append(typecast);
		elsePart.append(typecast);
		thenPart.append(StringHelpers.addBrackets(myExpMaker.makeExpression(in.getThenExpression(), isStatic, params)));
		elsePart.append(StringHelpers.addBrackets(myExpMaker.makeExpression(in.getElseExpression(), isStatic, params)));
		created.append("(" + condition + " ?\n");
		created.append(StringHelpers.indent(thenPart,1) + " :\n");
		created.append(StringHelpers.indent(elsePart,1) + ")");
		return created;
	}

}
