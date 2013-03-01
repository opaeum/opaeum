package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;

import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.common.ExpGeneratorHelper;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StateMap;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.tools.common.StringHelpers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.uml.CallExp;
import org.eclipse.ocl.uml.IfExp;
import org.eclipse.ocl.uml.LetExp;
import org.eclipse.ocl.uml.LiteralExp;
import org.eclipse.ocl.uml.MessageExp;
import org.eclipse.ocl.uml.OCLExpression;
import org.eclipse.ocl.uml.StateExp;
import org.eclipse.ocl.uml.TypeExp;
import org.eclipse.ocl.uml.Variable;
import org.eclipse.ocl.uml.VariableExp;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.TimeExpression;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.ValuePin;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;
import org.opaeum.ocl.uml.AbstractOclContext;
import org.opaeum.ocl.uml.PropertyOfImplicitObject;
import org.opaeum.runtime.environment.Environment;

public class ExpressionCreator{
	private OJClass myClass = null;
	OJUtil ojUtil;
	ExpGeneratorHelper expGeneratorHelper;
	private AbstractOclContext context;
	ExpressionCreator(OJUtil ojUtil,OJClass myOwner,AbstractOclContext context){
		super();
		this.context = context;
		this.ojUtil = ojUtil;
		this.expGeneratorHelper = new ExpGeneratorHelper(ojUtil);
		this.myClass = myOwner;
	}
	public ExpressionCreator(OJUtil ojUtil2,OJClass myClass2){
		this.ojUtil = ojUtil2;
		this.expGeneratorHelper = new ExpGeneratorHelper(ojUtil);
		this.myClass = myClass2;
	}
	public String makeExpression(AbstractOclContext in,boolean isStatic,List<OJParameter> params){
		context = in;
		return makeExpression(in.getExpression(), isStatic, params);
	}
	String makeExpression(OCLExpression in,boolean isStatic,List<OJParameter> params){
		StringBuilder thisNode = new StringBuilder();
		if(in instanceof TypeExp){
			Classifier type = ((TypeExp) in).getReferredType();
			OJPathName javaType = ojUtil.classifierPathname(type);
			myClass.addToImports(javaType);
			thisNode.append(javaType.getLast());
		}else if(in instanceof IfExp){
			thisNode.append(makeIfExpression((IfExp) in, isStatic, params));
		}else if(in instanceof LetExp){
			thisNode.append(makeLetExpression((LetExp) in, isStatic, params));
		}else if(in instanceof LiteralExp){
			LiteralExpCreator maker = new LiteralExpCreator(ojUtil, myClass, context);
			thisNode.append(maker.makeExpression((LiteralExp) in, isStatic, params));
		}else if(in instanceof MessageExp){
			// TODO OclMessageExp
		}else if(in instanceof CallExp){
			// only if the first node is a call to a class property
			PropCallCreator maker = new PropCallCreator(expGeneratorHelper, myClass, context);
			CallExp ce = (CallExp) in;
			if(ce.getSource() == null){
				thisNode.append(maker.makeExpressionNode((CallExp) in, isStatic, params));
			}else{
				StringBuilder source = new StringBuilder();
				if(ce.getSource() instanceof TypeExp){
					TypeExp typeExp = (TypeExp) ce.getSource();
					ClassifierMap map = ojUtil.buildClassifierMap(typeExp.getReferredType());
					myClass.addToImports(map.javaTypePath());
					source.append(map.javaType());
				}else{
					source.append(makeExpression((OCLExpression) ce.getSource(), isStatic, params));
				}
				thisNode.append(maker.makeExpression(ce, source, isStatic, params));
			}
		}else if(in instanceof VariableExp){
			thisNode.append(makeVariableExp((VariableExp) in));
		}else if(in instanceof StateExp){
			StateMap stateMap = ojUtil.buildStateMap(((StateExp) in).getReferredState());
			myClass.addToImports(stateMap.javaType());
			thisNode.append("isStepActive(" + stateMap.javaType().getLast() + ".class)");
		}else if(in instanceof TypeExp){
			ClassifierMap mapper = ojUtil.buildClassifierMap(((TypeExp) in).getReferredType());
			myClass.addToImports(mapper.javaTypePath());
			thisNode.append(mapper.javaType());
		}else{
			System.err.println("unspecified option in ExpressionGenerator.makeExpression");
		}
		if(thisNode.length()==0){
			System.out.println();
		}
		return StringHelpers.addBrackets(thisNode.toString());
	}
	private String makeVariableExp(VariableExp in){
		String result = null;
		org.eclipse.ocl.expressions.Variable<Classifier,Parameter> referredVariable = in.getReferredVariable();
		if(referredVariable instanceof PropertyOfImplicitObject){
			PropertyOfImplicitObject poio = (PropertyOfImplicitObject) referredVariable;
			PropertyMap map = ojUtil.buildStructuralFeatureMap((Property) poio.getOriginalElement());
			if(poio.getImplicitVar().getName().equals("responsibility") || poio.getImplicitVar().getName().equals("contextObject")){
				result = "get" + NameConverter.capitalize(poio.getImplicitVar().getName()) + "()." + map.getter() + "()";
			}else if(poio.getImplicitVar().getName().equals("self")){
				Classifier type = poio.getImplicitVar().getType();
				result = calcSelfExpression(result, type)+"." +map.getter()+"()";
			}else{
				result = poio.getImplicitVar().getName() + "." + map.getter() + "()";
			}
		}else if(in.getName().equals("self")){
			Classifier type = in.getType();
			result = calcSelfExpression(result, type);
		}else if(in.getName().equals("currentUser")){
			myClass.addToImports(new OJPathName(Environment.class.getName()));
			result = ojUtil.environmentPathname()+ ".INSTANCE.getCurrentUser()";
			Class pn = this.ojUtil.getLibrary().getPersonNode();
			if(pn != null){
				OJPathName pnpn = ojUtil.classifierPathname(pn);
				myClass.addToImports(pnpn);
				result = "(" + pnpn.getLast() + ")" + result;
			}
		}else if(in.getName().equals("currentRole")){
			myClass.addToImports(new OJPathName(Environment.class.getName()));
			result = ojUtil.environmentPathname()+ ".INSTANCE.getCurrentRole()";
			Interface pn = this.ojUtil.getLibrary().getParticipant();
			if(pn != null){
				OJPathName pnpn = ojUtil.classifierPathname(pn);
				myClass.addToImports(pnpn);
				result = "(" + pnpn.getLast() + ")" + result;
			}
		}else if(in.getName().equals("contextObject")){
			result = "getContextObject()";
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
		return NameConverter.decapitalize(result);
	}
	protected String calcSelfExpression(String result,Classifier type){
		if(type instanceof StateMachine){
			if(context.getBodyContainer() instanceof OpaqueExpression){
				if(context.getBodyContainer().getOwner().getOwner() instanceof Transition){
					result = "getStateMachineExecution()";
				}else if(context.getBodyContainer().getOwner() instanceof ValuePin){
					ValuePin vp = (ValuePin) context.getBodyContainer().getOwner();
					Activity a = EmfActivityUtil.getContainingActivity(vp);
					if(a.getOwner() instanceof State || a.getOwner() instanceof Transition){
						result = "getStateMachineExecution()";
					}
				}else if(context.getBodyContainer().getOwner() instanceof TimeExpression){
					result = "getStateMachineExecution()";
				}
			}
		}
		EObject element = context.getBodyContainer();
		while(!(element == null || element instanceof Operation)){
			element = EmfElementFinder.getContainer(element);
			// OCL Expressions on stereotype attributes
		}
		if(element instanceof Operation && EmfBehaviorUtil.hasExecutionInstance((Operation) element)){
			result = "getContextObject()";
		}
		if(result == null){
			result = "this";
		}
		return result;
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
	private StringBuilder makeLetExpression(LetExp in,boolean isStatic,List<OJParameter> params){
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
		oper = new OJOperation(operName);
		myClass.addToOperations(oper);
		oper.setReturnType(inMap.javaTypePath());
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
		StringBuilder callOper = new StringBuilder();
		callOper.append(operName + "(" + OJOperation.paramsToActuals(oper) + ")");
		return callOper;
	}
	private StringBuilder makeIfExpression(IfExp in,boolean isStatic,List<OJParameter> params){
		StringBuilder created = new StringBuilder();
		ExpressionCreator myExpMaker = new ExpressionCreator(ojUtil, myClass, context);
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
		StringBuilder thenPart = new StringBuilder();
		StringBuilder elsePart = new StringBuilder();
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
