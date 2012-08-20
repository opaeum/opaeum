package org.opaeum.javageneration.oclexpressions;

import java.util.ArrayList;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.expgenerators.creators.ExpressionCreator;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.uml.CollectionType;
import org.eclipse.ocl.uml.OCLExpression;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.OpaeumLibrary;
import org.opaeum.ocl.uml.AbstractOclContext;

public class ValueSpecificationUtil{
	OpaeumLibrary library;
	OJUtil ojUtil;
	public ValueSpecificationUtil(OJUtil ojUtil){
		this.library=ojUtil.getLibrary();
		this.ojUtil=ojUtil;
	}
	public String expressValue(OJClass ojOwner,ValueSpecification valueSpec,Classifier expectedType,boolean isStatic){
		if(valueSpec instanceof OpaqueExpression){
			AbstractOclContext oclExpression = library.getOclExpressionContext((OpaqueExpression) valueSpec);
			return expressOcl(oclExpression, ojOwner, expectedType, isStatic);
		}
		return expressLiterals(valueSpec, ojOwner, null);
	}
	private String expressOcl(AbstractOclContext oclExpression,OJClass ojOwner,Classifier expectedType,boolean isStatic){
		String expression;
		if(oclExpression.hasErrors()){
			return "ERROR IN OCL: " + oclExpression.getExpressionString();
		}
			
		ExpressionCreator ec = new ExpressionCreator(ojUtil, ojOwner);
		expression = ec.makeExpression(oclExpression, isStatic, new ArrayList<OJParameter>());
		expression = buildTypeCastIfNecessary(expression, oclExpression.getExpression(), expectedType);
		return expression;
	}
	public String expressValue(OJOperation operationContext,ValueSpecification valueSpec,Classifier owner,Classifier expectedType){
		if(valueSpec == null){
			if(expectedType == null){
				return "could not determine type of implicit object";
			}else{
				return expressDefaultOrImplicitObject(owner, expectedType);
			}
		}else if(valueSpec instanceof OpaqueExpression){
			return expressOcl(operationContext, (OpaqueExpression) valueSpec, expectedType);
		}
		return expressLiterals(valueSpec, (OJClass) operationContext.getOwner(), operationContext);
	}
	private String expressOcl(OJOperation operationContext,OpaqueExpression valueSpec,Classifier expectedType){
		AbstractOclContext value = library.getOclExpressionContext((OpaqueExpression) valueSpec);
		return expressOcl(value, operationContext, expectedType);
	}
	public String expressOcl(AbstractOclContext value,OJOperation operationContext,Classifier expectedType){
		String expression;
		if(value.hasErrors()){
			return "ERROR IN OCL:" + value.getExpressionString();
		}else{
			OJClass ojOwner = (OJClass) operationContext.getOwner();
			ExpressionCreator ec = new ExpressionCreator(ojUtil, ojOwner);
			List<OJParameter> parameters = buildContext(operationContext);
			addExtendedKeywords(operationContext, value);
			expression = ec.makeExpression(value, operationContext.isStatic(), parameters);
			if(expectedType != null){
				expression = buildTypeCastIfNecessary(expression, value.getExpression(), expectedType);
			}
			return expression;
		}
	}
	private static List<OJParameter> buildContext(OJOperation operationContext){
		List<OJParameter> parameters = new ArrayList<OJParameter>(operationContext.getParameters());
		OJBlock body = operationContext.getBody();
		buildContext(operationContext, parameters, body);
		return parameters;
	}
	public static void buildContext(OJOperation operationContext,List<OJParameter> parameters,OJBlock body){
		for(OJField f:body.getLocals()){
			if(!f.getName().equals("result")){// Standard result variable for
				// operations
				OJParameter fake = new OJParameter(f.getName(), f.getType());
				parameters.add(fake);
			}
		}
	}
	public static void addExtendedKeywords(OJOperation operationContext,AbstractOclContext expression){
		if(expression.getExpressionString().contains("now") && !hasLocal(operationContext, "now")){
			OJAnnotatedField now = new OJAnnotatedField("now", new OJPathName("java.util.Date"));
			now.setInitExp("new Date()");
			operationContext.getBody().addToLocals(now);
		}
	}
	private static boolean hasLocal(OJOperation o,String name){
		for(OJField ojField:o.getBody().getLocals()){
			if(ojField.getName().equals(name)){
				return true;
			}
		}
		return false;
	}
	private String expressLiterals(ValueSpecification valueSpec,OJClass ojOwner,OJOperation operation){
		String expression = null;
		if(valueSpec instanceof LiteralBoolean){
			expression = valueSpec.booleanValue() + "";
		}else if(valueSpec instanceof LiteralString){
			expression = "\"" + valueSpec.stringValue() + "\"";
		}else if(valueSpec instanceof InstanceValue){
			InstanceValue iv = (InstanceValue) valueSpec;
			if(iv.getInstance() instanceof EnumerationLiteral){
				EnumerationLiteral l = (EnumerationLiteral) iv.getInstance();
				ClassifierMap map = ojUtil.buildClassifierMap(l.getEnumeration(),(CollectionKind) null);
				expression = map.javaType() + "." + OJUtil.toJavaLiteral(l);
			}else{
				expression = "";
			}
		}else if(valueSpec instanceof LiteralInteger){
			expression = valueSpec.integerValue() + "";
		}else if(valueSpec instanceof InstanceValue){
			InstanceSpecification spec = (InstanceSpecification) ((InstanceValue) valueSpec).getInstance();
			ClassifierMap map = ojUtil.buildClassifierMap(spec.getClassifiers().get(0), (CollectionKind)null);
			final OJAnnotatedOperation getInstance = new OJAnnotatedOperation("get" + spec.getName() + EmfWorkspace.getOpaeumId(spec),
					map.javaTypePath());
			ojOwner.addToOperations(getInstance);
			final OJAnnotatedField result = new OJAnnotatedField("result", map.javaTypePath());
			getInstance.getBody().addToLocals(result);
			result.setInitExp(map.javaDefaultValue());
			StringBuilder sb = new StringBuilder();
			if(operation != null){
				List<OJParameter> params = buildContext(operation);
				for(OJParameter ojParameter:params){
					getInstance.addToParameters(ojParameter.getDeepCopy());
				}
				for(OJParameter p:params){
					sb.append(p.getName());
					sb.append(",");
				}
				sb.deleteCharAt(sb.length() - 1);
			}
			expression = getInstance.getName() + "(" + sb + ")";
			for(Slot s:spec.getSlots()){
				PropertyMap featureMap = ojUtil.buildStructuralFeatureMap((Property) s.getDefiningFeature());
				getInstance.getBody().addToStatements("result." + featureMap.setter() + "(" + expressSlot(operation, s) + ")");
			}
		}
		return expression;
	}
	String buildTypeCastIfNecessary(String java,OCLExpression expression,Classifier expectedType){
		if(expectedType instanceof CollectionType){
			CollectionKind collectionKind = ((CollectionType) expectedType).getKind();
			if(expression.getType() instanceof CollectionType){
				CollectionType is = (CollectionType) expression.getType();
				if(!is.getElementType().equals(expectedType)){
					ClassifierMap classifierMap = ojUtil.buildClassifierMap(((CollectionType) expectedType).getElementType(), collectionKind);
					String defaultValue = classifierMap.javaDefaultValue();
					String typeCast = defaultValue.substring(0, defaultValue.length() - 1) + java + ")";
					return typeCast;
				}else{
					return java;
				}
			}else{
				if(collectionKind == CollectionKind.SEQUENCE_LITERAL){
					return "StdLib.objectAsSequence(" + java + ")";
				}else{
					return "StdLib.objectAsSet(" + java + ")";
				}
			}
		}else{
			if(expression.getType() instanceof CollectionType){
				CollectionType type = (CollectionType) expression.getType();
				ClassifierMap classifierMap = ojUtil.buildClassifierMap(type.getElementType(), type.getKind());
				return "(" + classifierMap.javaType() + ")Stdlib.collectionAsSingleObject(" + java + ")";
			}
		}
		return java;
	}
	public String expressDefaultOrImplicitObject(Classifier owner,Classifier expectedType){
		String expression;
		ClassifierMap map = ojUtil.buildClassifierMap(expectedType, (CollectionKind) null);
		if(expectedType instanceof CollectionType){
			throw new IllegalStateException("Implicit objects cannot be collections");
		}
		if(owner.conformsTo(expectedType)){
			expression = "getSelf()";
		}else if(owner instanceof Behavior){
			Behavior b = (Behavior) owner;
			if(b.getContext() != null && b.getContext().conformsTo(expectedType)){
				if(EmfBehaviorUtil.hasExecutionInstance(b)){
					expression = "getContextObject()";
				}else{
					expression = "getSelf()";
				}
			}else{
				expression = map.javaDefaultValue();
			}
		}else{
			expression = map.javaDefaultValue();
		}
		return expression;
	}
	public static String expressSlot(OJOperation oper,final Slot slot){
		return "";
	} // Assume a static context
	public String expressSlot(OJClass myClass,final Slot slot){
		Property feat = (Property) slot.getDefiningFeature();
		String init = null;
		PropertyMap mapper = ojUtil.buildStructuralFeatureMap(feat);
		final List<ValueSpecification> values = slot.getValues();
		if(mapper.isOne()){
			init = expressValue(myClass, values.get(0), library.getActualType(feat), true);
		}else{
			StringBuilder sb = new StringBuilder(mapper.javaDefaultValue());
			sb.deleteCharAt(sb.length() - 1);
			StringBuilder valueExpressions = new StringBuilder();
			for(ValueSpecification v:values){
				String expressValue = expressValue(myClass, v, library.getActualType(feat), true);
				if(expressValue != null){
					valueExpressions.append(expressValue);
					valueExpressions.append(",");
				}
			}
			if(valueExpressions.length() > 0 && valueExpressions.charAt(valueExpressions.length() - 1) == ','){
				valueExpressions.deleteCharAt(valueExpressions.length() - 1);
			}
			if(valueExpressions.length() > 0){
				sb.append("java.util.Arrays.asList(");
				sb.append(valueExpressions);
				sb.append("))");
			}else{
				sb.append(valueExpressions);
				sb.append(")");
			}
			init = sb.toString();
		}
		return init;
	}

}
