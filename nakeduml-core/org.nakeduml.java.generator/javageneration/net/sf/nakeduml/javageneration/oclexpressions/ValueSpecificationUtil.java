package net.sf.nakeduml.javageneration.oclexpressions;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.javageneration.maps.NakedClassifierMap;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import nl.klasse.octopus.codegen.umlToJava.expgenerators.creators.ExpressionCreator;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.internal.parser.parsetree.ParsedOclString;
import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.octopus.stdlib.internal.types.StdlibCollectionType;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;

public class ValueSpecificationUtil{
	public static String expressValue(OJClass ojOwner,INakedValueSpecification valueSpec,IClassifier expectedType, boolean isStatic){
		if(valueSpec.isValidOclValue()){
			String expression = null;
			ExpressionCreator ec = new ExpressionCreator(ojOwner);
			IOclContext value = (IOclContext) valueSpec.getValue();
			expression = ec.makeExpression(value.getExpression(), isStatic, new ArrayList<OJParameter>());
			expression = buildTypeCastIfNecessary(expression,value.getExpression(),expectedType);
			return expression;
		}
		return expressLiterals(valueSpec);
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
		return expressLiterals(valueSpec);
	}
	private static String expressOcl(OJOperation operationContext,INakedValueSpecification valueSpec,IClassifier expectedType){
		if(valueSpec.isValidOclValue()){
			String expression = null;
			OJClass ojOwner = (OJClass) operationContext.getOwner();
			ExpressionCreator ec = new ExpressionCreator(ojOwner);
			IOclContext value = (IOclContext) valueSpec.getValue();
			List<OJParameter> parameters = new ArrayList<OJParameter>(operationContext.getParameters());
			OJBlock body = operationContext.getBody();
			buildContext(operationContext, value, parameters, body);
			expression = ec.makeExpression(value.getExpression(), operationContext.isStatic(), parameters);
			expression = buildTypeCastIfNecessary( expression,value.getExpression(), expectedType);
			return expression;
		}else{
			return "ERROR IN OCL:" + valueSpec.getOclValue().getExpressionString();
		}
	}
	public static void buildContext(OJOperation operationContext,IOclContext value,List<OJParameter> parameters,OJBlock body){
		addExtendedKeywords(operationContext, value);
		for(OJField f:body.getLocals()){
			OJParameter fake = new OJParameter();
			fake.setName(f.getName());
			fake.setType(f.getType());
			parameters.add(fake);
		}
	}
	public static void addExtendedKeywords(OJOperation operationContext,IOclContext value){
		if(value.getExpressionString().contains("now") && !hasLocal(operationContext, "now")){
			OJAnnotatedField now = new OJAnnotatedField("now", new OJPathName("java.util.Date"));
			now.setInitExp("new Date()");
			operationContext.getBody().addToLocals(now);
		}
		if(value.getExpressionString().contains("currentUser") && !hasLocal(operationContext, "currentUser")){
			OJAnnotatedField now = new OJAnnotatedField("currentUser", new OJPathName("org.nakeduml.runtime.bpm.BusinessRole"));
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
	private static String expressLiterals(INakedValueSpecification valueSpec){
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
		}
		return expression;
	}
	static String buildTypeCastIfNecessary(String java,IOclExpression expression,IClassifier expectedType){
		if(expression.getExpressionType().isCollectionKind() && expectedType.isCollectionKind()){
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
			return java;
		}
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
}
