package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;

import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.common.ExpGeneratorHelper;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.tools.common.StringHelpers;

import org.eclipse.ocl.uml.CallExp;
import org.eclipse.ocl.uml.IfExp;
import org.eclipse.ocl.uml.LetExp;
import org.eclipse.ocl.uml.LiteralExp;
import org.eclipse.ocl.uml.MessageExp;
import org.eclipse.ocl.uml.OCLExpression;
import org.eclipse.ocl.uml.Variable;
import org.eclipse.ocl.uml.VariableExp;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.javageneration.util.OJUtil;

public class ExpressionCreator{
	private OJClass myClass = null;
	OJUtil ojUtil;
	ExpGeneratorHelper expGeneratorHelper; 
	public ExpressionCreator(OJUtil ojUtil,OJClass myOwner){
		super();
		this.ojUtil=ojUtil;
		this.expGeneratorHelper=new ExpGeneratorHelper(ojUtil);
		this.myClass = myOwner;
	}
	public String makeExpression(OCLExpression in,boolean isStatic,List<OJParameter> params){
		StringBuffer thisNode = new StringBuffer();
		if(in instanceof IfExp){
			thisNode.append(makeIfExpression((IfExp) in, isStatic, params));
		}else if(in instanceof LetExp){
			thisNode.append(makeLetExpression((LetExp) in, isStatic, params));
		}else if(in instanceof LiteralExp){
			LiteralExpCreator maker = new LiteralExpCreator(ojUtil, myClass);
			thisNode.append(maker.makeExpression((LiteralExp) in, isStatic, params));
		}else if(in instanceof MessageExp){
			// TODO OclMessageExp
		}else if(in instanceof CallExp){
			// only if the first node is a call to a class property
			PropCallCreator maker = new PropCallCreator(expGeneratorHelper, myClass);
			CallExp ce = (CallExp) in;
			if(ce.getSource() == null){
				thisNode.append(maker.makeExpressionNode((CallExp) in, isStatic, params));
			}else{
				StringBuffer source = new StringBuffer();
				source.append(makeExpression((OCLExpression) ce.getSource(), isStatic, params));
				thisNode.append(maker.makeExpression(ce, source, isStatic, params));
			}
		}else if(in instanceof VariableExp){
			thisNode.append(makeVariableExp((VariableExp) in));
		}else{
			System.err.println("unspecified option in ExpressionGenerator.makeExpression");
		}
		return StringHelpers.addBrackets(thisNode.toString());
	}
	private String makeVariableExp(VariableExp in){
		String result = "";
		if(in.getReferredVariable() instanceof Property){
			// implicit attributes
			return ojUtil.buildStructuralFeatureMap((Property) in.getReferredVariable()).getter() + "()";
		}else if(in.getName().equals("self") || in.getName().equals("this")){
			result = "this";
		}else{
			Variable varDecl = (Variable) in.getReferredVariable();
			Classifier type = varDecl.getType();
			if(type.getName().equals(IOclLibrary.BooleanTypeName)){
				result = in.getName() + ".booleanValue()";
			}else if(type.getName().equals(IOclLibrary.IntegerTypeName)){
				result = in.getName() + ".intValue()";
			}else if(type.getName().equals(IOclLibrary.RealTypeName)){
				result = in.getName() + ".floatValue()";
			}else{
				result = in.getName();
			}
		}
		return StringHelpers.firstCharToLower(result);
	}
	public String makeVarDecl(Variable exp,boolean isStatic,List<OJParameter> params){
		OCLExpression initExpression = (OCLExpression) exp.getInitExpression();
		ClassifierMap mapper = ojUtil.buildClassifierMap(exp.getType());
		OJPathName myType = mapper.javaTypePath();
		myClass.addToImports(myType);
		String myInitExp = mapper.javaDefaultValue();
		// create init expression
		if(initExpression != null){
			myInitExp = makeExpression(initExpression, isStatic, params).toString();
		}
		//
		return (myType == null ? "void" : myType.getTypeName()) + " " + ExpGeneratorHelper.javaFieldName(exp) + " = " + myInitExp;
	}
	private StringBuffer makeLetExpression(LetExp in,boolean isStatic,List<OJParameter> params){
		// generate a separate operation
		ClassifierMap inMap = ojUtil.buildClassifierMap(in.getType());
		String myType = inMap.javaType();
		String myDefault = inMap.javaDefaultValue();
		myClass.addToImports(inMap.javaTypePath());
		myClass.addToImports(inMap.javaDefaultTypePath());
		String operName = "letExpression" + myClass.getUniqueNumber();
		OJOperation oper = null;
		ClassifierMap variableMap = ojUtil.buildClassifierMap(in.getVariable().getType());
		myClass.addToImports(variableMap.javaTypePath());
		List<OJParameter> bodyParams = expGeneratorHelper.addVarToParams((Variable) in.getVariable(), params);
		oper = new OJOperation();
		myClass.addToOperations(oper);
		oper.setReturnType(inMap.javaTypePath());
		oper.setName(operName);
		oper.setStatic(isStatic);
		oper.setVisibility(OJVisibilityKind.PRIVATE);
		oper.setComment("implements " + in.toString());
		OJBlock body1 = new OJBlock();
		oper.setBody(body1);
		OJSimpleStatement exp1 = new OJSimpleStatement(myType + " result = " + myDefault);
		body1.addToStatements(exp1);
		OJSimpleStatement exp2 = new OJSimpleStatement(makeVarDecl((Variable) in.getVariable(), isStatic, params));
		body1.addToStatements(exp2);
		OJSimpleStatement exp3 = new OJSimpleStatement("result = " + makeExpression((OCLExpression) in.getIn(), isStatic, bodyParams));
		body1.addToStatements(exp3);
		OJSimpleStatement exp4 = new OJSimpleStatement("return result");
		body1.addToStatements(exp4);
		oper.setParameters(params);
		// generate the call to the created operation
		StringBuffer callOper = new StringBuffer();
		callOper.append(operName + "(" + OJOperation.paramsToActuals(oper) + ")");
		return callOper;
	}
	private StringBuffer makeIfExpression(IfExp in,boolean isStatic,List<OJParameter> params){
		StringBuffer created = new StringBuffer();
		ExpressionCreator myExpMaker = new ExpressionCreator(ojUtil, myClass);
		Classifier thenType = in.getThenExpression().getType();
		Classifier elseType = in.getElseExpression().getType();
		String typecast = "";
		if(thenType != elseType){
			Classifier commonSuperType = EmfClassifierUtil.findCommonSuperType(thenType, elseType);
			ClassifierMap mapper = ojUtil.buildClassifierMap(commonSuperType);
			typecast = "(" + mapper.javaType() + ")";
			myClass.addToImports(mapper.javaTypePath());
		}
		String condition = StringHelpers.addBrackets(myExpMaker.makeExpression((OCLExpression) in.getCondition(), isStatic, params));
		StringBuffer thenPart = new StringBuffer();
		StringBuffer elsePart = new StringBuffer();
		thenPart.append(typecast);
		elsePart.append(typecast);
		thenPart.append(StringHelpers.addBrackets(myExpMaker.makeExpression((OCLExpression) in.getThenExpression(), isStatic, params)));
		elsePart.append(StringHelpers.addBrackets(myExpMaker.makeExpression((OCLExpression) in.getElseExpression(), isStatic, params)));
		created.append("(" + condition + " ?\n");
		created.append(StringHelpers.indent(thenPart, 1) + " :\n");
		created.append(StringHelpers.indent(elsePart, 1) + ")");
		return created;
	}
}
