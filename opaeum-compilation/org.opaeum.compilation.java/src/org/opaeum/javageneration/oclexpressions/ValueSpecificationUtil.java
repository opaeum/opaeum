package org.opaeum.javageneration.oclexpressions;

import java.util.ArrayList;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.expgenerators.creators.ExpressionCreator;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.octopus.model.CollectionMetaType;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.internal.parser.parsetree.ParsedOclString;
import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.octopus.stdlib.internal.types.StdlibCollectionType;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.NakedClassifierMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedEnumerationLiteral;
import org.opaeum.metamodel.core.INakedInstanceSpecification;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedSlot;
import org.opaeum.metamodel.core.INakedValueSpecification;

public class ValueSpecificationUtil{
	public static String expressValue(OJClass ojOwner,INakedValueSpecification valueSpec,IClassifier expectedType,boolean isStatic){
		if(valueSpec.isValidOclValue()){
			String expression = null;
			ExpressionCreator ec = new ExpressionCreator(ojOwner);
			IOclContext value = (IOclContext) valueSpec.getValue();
			expression = ec.makeExpression(value.getExpression(), isStatic, new ArrayList<OJParameter>());
			expression = buildTypeCastIfNecessary(expression, value.getExpression(), expectedType);
			return expression;
		}
		return expressLiterals(valueSpec, ojOwner, null);
	}
	public static String expressValue(OJOperation operationContext,INakedValueSpecification valueSpec,INakedClassifier owner,IClassifier expectedType){
		if(valueSpec == null){
			if(expectedType == null){
				return "could not determine type of implicit object";
			}else{
				return expressDefaultOrImplicitObject(owner, expectedType);
			}
		}else if(valueSpec.isOclValue()){
			return expressOcl(operationContext, valueSpec, expectedType);
		}
		return expressLiterals(valueSpec, (OJClass) operationContext.getOwner(), operationContext);
	}
	private static String expressOcl(OJOperation operationContext,INakedValueSpecification valueSpec,IClassifier expectedType){
		if(valueSpec.isValidOclValue()){
			String expression = null;
			OJClass ojOwner = (OJClass) operationContext.getOwner();
			ExpressionCreator ec = new ExpressionCreator(ojOwner);
			IOclContext value = (IOclContext) valueSpec.getValue();
			List<OJParameter> parameters = buildContext(operationContext);
			addExtendedKeywords(operationContext, value);
			expression = ec.makeExpression(value.getExpression(), operationContext.isStatic(), parameters);
			expression = buildTypeCastIfNecessary(expression, value.getExpression(), expectedType);
			return expression;
		}else{
			return "ERROR IN OCL:" + valueSpec.getOclValue().getExpressionString();
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
			if(!f.getName().equals("result")){// Standard result variable for operations
				OJParameter fake = new OJParameter();
				fake.setName(f.getName());
				fake.setType(f.getType());
				parameters.add(fake);
			}
		}
	}
	public static void addExtendedKeywords(OJOperation operationContext,IOclContext value){
		if(value.getExpressionString().contains("now") && !hasLocal(operationContext, "now")){
			OJAnnotatedField now = new OJAnnotatedField("now", new OJPathName("java.util.Date"));
			now.setInitExp("new Date()");
			operationContext.getBody().addToLocals(now);
		}
		if(value.getExpressionString().contains("currentUser") && !hasLocal(operationContext, "currentUser")){
			OJAnnotatedField now = new OJAnnotatedField("currentUser", new OJPathName("org.opeum.runtime.bpm.BusinessRole"));
			now.setInitExp("null");
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
	private static String expressLiterals(INakedValueSpecification valueSpec,OJClass ojOwner,OJOperation operation){
		String expression = null;
		if(valueSpec.getValue() instanceof Boolean){
			expression = valueSpec.getValue().toString();
		}else if(valueSpec.getValue() instanceof String){
			expression = "\"" + valueSpec.getValue().toString() + "\"";
		}else if(valueSpec.getValue() instanceof INakedEnumerationLiteral){
			INakedEnumerationLiteral l = (INakedEnumerationLiteral) valueSpec.getValue();
			NakedClassifierMap map = new NakedClassifierMap(l.getEnumeration());
			expression = map.javaType() + "." + l.getName().toUpperCase();
		}else if(valueSpec.getValue() instanceof Number){
			expression = valueSpec.getValue().toString();
		}else if(valueSpec.getValue() instanceof ParsedOclString){
			return "OCL INVALID!: " + valueSpec.getValue();
			// TODO instancespecifications
		}else if(valueSpec.getValue() instanceof INakedInstanceSpecification){
			INakedInstanceSpecification spec = (INakedInstanceSpecification) valueSpec.getValue();
			NakedClassifierMap map = new NakedClassifierMap(spec.getClassifier());
			final OJAnnotatedOperation getInstance = new OJAnnotatedOperation("get" + spec.getName() + spec.getMappingInfo().getOpaeumId(), map.javaTypePath());
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
			for(INakedSlot s:spec.getSlots()){
				NakedStructuralFeatureMap featureMap = OJUtil.buildStructuralFeatureMap(s.getDefiningFeature());
				getInstance.getBody().addToStatements("result." + featureMap.setter() + "(" + expressSlot(operation, s) + ")");
			}
		}
		return expression;
	}
	static String buildTypeCastIfNecessary(String java,IOclExpression expression,IClassifier expectedType){
		if(expectedType.isCollectionKind()){
			if(expression.getExpressionType().isCollectionKind()){
				StdlibCollectionType is = (StdlibCollectionType) expression.getExpressionType();
				StdlibCollectionType shouldBe = (StdlibCollectionType) expectedType;
				if(!is.getElementType().equals(shouldBe.getElementType())){
					ClassifierMap classifierMap = new ClassifierMap(shouldBe);
					String defaultValue = classifierMap.javaDefaultValue();
					String typeCast = defaultValue.substring(0, defaultValue.length() - 1) + java + ")";
					return typeCast;
				}else{
					return java;
				}
			}else{
				if(((StdlibCollectionType) expectedType).getMetaType() == CollectionMetaType.SEQUENCE){
					return "java.util.Collections.singletonList(" + java + ")";
				}else{
					return "java.util.Collections.singleton(" + java + ")";
				}
			}
		}
		return java;
	}
	public static String expressDefaultOrImplicitObject(INakedClassifier owner,IClassifier expectedType){
		String expression;
		ClassifierMap map = new NakedClassifierMap(expectedType);
		if(expectedType.isCollectionKind()){
			throw new IllegalStateException("Implicit objects cannot be collections");
		}
		if(owner.conformsTo(expectedType)){
			expression = "this";
		}else if(owner instanceof INakedBehavior){
			INakedBehavior b = (INakedBehavior) owner;
			if(b.getContext() != null && b.getContext().conformsTo(expectedType)){
				if(BehaviorUtil.hasExecutionInstance(b)){
					expression = "getContextObject()";
				}else{
					expression = "this";
				}
			}else{
				expression = map.javaDefaultValue();
			}
		}else{
			expression = map.javaDefaultValue();
		}
		return expression;
	}
	public static String expressSlot(OJOperation oper,final INakedSlot slot){
		return "";
	} // Assume a static context
	public static String expressSlot(OJClass myClass,final INakedSlot slot){
		INakedProperty feat = slot.getDefiningFeature();
		String init = null;
		NakedStructuralFeatureMap mapper = OJUtil.buildStructuralFeatureMap(feat);
		final List<INakedValueSpecification> values = slot.getValues();
		if(mapper.isOne()){
			init = expressValue(myClass, values.get(0), feat.getType(), true);
		}else{
			StringBuilder sb = new StringBuilder(mapper.javaDefaultValue());
			sb.deleteCharAt(sb.length() - 1);
			sb.append("java.util.Arrays.asList(");
			for(INakedValueSpecification v:values){
				sb.append(expressValue(myClass, v, feat.getType(), true));
				sb.append(",");
			}
			if(sb.charAt(sb.length() - 1) == ','){
				sb.deleteCharAt(sb.length() - 1);
			}
			sb.append("))");
			init = sb.toString();
		}
		return init;
	}
	public static String replaceThisWith(String expr,String selfExpression){
		String string = " "+ expr+" ";
		String seperators="([\\s\\)=\\(\\.])";
		return string.replaceAll(seperators+ "(this)"+seperators, "$1"+selfExpression +"$3").replaceAll(seperators+ "(this)"+seperators, "$1"+selfExpression+"$3");
	}
}
