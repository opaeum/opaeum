package org.nakeduml.tinker.generator;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedProperty;

@StepDependency(phase = JavaTransformationPhase.class, after = { TinkerAttributeImplementor.class })
public class TinkerQualifierGenerator extends AbstractStructureVisitor {

	@Override
	protected void visitProperty(INakedClassifier owner, NakedStructuralFeatureMap map) {
		if (!map.getProperty().getQualifiers().isEmpty()) {
			// For each qualifier gen a method returning the default value, i.e.
			// jippo QualifierValue
			for (INakedProperty q : map.getProperty().getQualifiers()) {
				generateQualifierValue(q);
				generateQualifiedGetter(q);
			}
			generateQualifierGetter(findJavaClass(owner), map, map.getProperty().getQualifiers());
		}
	}

	private void generateQualifierGetter(OJAnnotatedClass ojClass, NakedStructuralFeatureMap map, List<INakedProperty> qualifiers) {
		OJAnnotatedOperation qualifierGetter = new OJAnnotatedOperation(TinkerGenerationUtil.contructNameForQualifiedGetter(map));
		qualifierGetter.addParam("context", map.javaBaseTypePath());
		ojClass.addToOperations(qualifierGetter);
		OJField result = new OJField();
		result.setName("result");
		result.setType(new OJPathName("java.util.List"));
		result.getType().addToElementTypes(TinkerGenerationUtil.TINKER_QUALIFIER_PATHNAME);
		result.setInitExp("new ArrayList<" + TinkerGenerationUtil.TINKER_QUALIFIER_PATHNAME.getLast() + ">()");
		qualifierGetter.setReturnType(result.getType());
		qualifierGetter.getBody().addToLocals(result);
		for (INakedProperty q : qualifiers) {
			NakedStructuralFeatureMap qualifierMap = new NakedStructuralFeatureMap(q);
			StringBuilder sb = new StringBuilder();
			sb.append("result.add(");
			sb.append("new ");
			sb.append(TinkerGenerationUtil.TINKER_QUALIFIER_PATHNAME.getLast());
			sb.append("(\"");
			sb.append(qualifierMap.fieldname());
			sb.append("\", ");
			sb.append("context.");
			sb.append(TinkerGenerationUtil.getQualifierValueGetterName(q));
			sb.append("(), ");
			sb.append(TinkerGenerationUtil.calculateMultiplcity(q.getMultiplicity()));
			sb.append("))");
			qualifierGetter.getBody().addToStatements(sb.toString());	
		}
		qualifierGetter.getBody().addToStatements("return result");
		ojClass.addToImports(TinkerGenerationUtil.TINKER_QUALIFIER_PATHNAME);
		ojClass.addToImports(TinkerGenerationUtil.tinkerMultiplicityPathName);
	}

	private void generateQualifiedGetter(INakedProperty qualifier) {
		INakedProperty ownerElement = (INakedProperty) qualifier.getOwnerElement();
		INakedClassifier qualifiedClassifier = ownerElement.getOwner();
		OJAnnotatedClass ojClass = findJavaClass(qualifiedClassifier);

		NakedStructuralFeatureMap ownerElementMap = OJUtil.buildStructuralFeatureMap(ownerElement);
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(qualifier);
		OJAnnotatedOperation qualifierValue = new OJAnnotatedOperation(ownerElementMap.getter() + "For" + StringUtils.capitalize(map.fieldname()));
		if (qualifier.getMultiplicity().isSingleObject()) {
			qualifierValue.setReturnType(ownerElementMap.javaTypePath().getElementTypes().get(0));
		} else {
			//This only return a Set for now, not sure how index behaves with duplicates
			OJPathName set = new OJPathName("java.util.Set");
			set.addToElementTypes(ownerElementMap.javaBaseTypePath());
			qualifierValue.setReturnType(set);
		}
		qualifierValue.addParam(map.fieldname(), (map.isOne() ? map.javaTypePath() : map.javaBaseTypePath()));

		ojClass.addToImports(TinkerGenerationUtil.tinkerIndexPathName);
		ojClass.addToImports(TinkerGenerationUtil.tinkerCloseableSequencePathName);
		qualifierValue.getBody().addToStatements("Index<Edge> index = GraphDb.getDb().getIndex(getUid() + \":::\" + \"" + TinkerGenerationUtil.getEdgeName(ownerElementMap) + "\", Edge.class)");
		OJIfStatement ifIndexNull = new OJIfStatement("index==null", "return null");
		ifIndexNull.addToElsePart("CloseableSequence<Edge> closeableSequence = index.get(\"" + map.fieldname() + "\", " + map.fieldname()
				+ "==null?\"___NULL___\":" + map.fieldname() + ")");
		OJIfStatement ifHasNext = new OJIfStatement("closeableSequence.hasNext()");
		if (map.isOne()) {
			ifHasNext.addToThenPart("return new " + ownerElementMap.javaBaseTypePath().getLast() + "(closeableSequence.next().getInVertex())");
			ifHasNext.addToElsePart("return null");
		} else {
			OJSimpleStatement ojSimpleStatement = new OJSimpleStatement("return new TinkerSetClosableSequenceImpl<" + ownerElementMap.javaBaseTypePath().getLast() + ">(closeableSequence");
			//Specify inverse boolean
			if (ownerElementMap.getProperty().isInverse() || ownerElementMap.getProperty().getOtherEnd()==null || !ownerElementMap.getProperty().getOtherEnd().isNavigable()) {
				ojSimpleStatement.setExpression(ojSimpleStatement.getExpression() + ", true)");
			} else {
				ojSimpleStatement.setExpression(ojSimpleStatement.getExpression() + ", false)");
			}
			ifHasNext.addToThenPart(ojSimpleStatement);
			ojClass.addToImports(TinkerGenerationUtil.tinkerSetClosableSequenceImplPathName);
			ifHasNext.addToElsePart("return Collections.<" + ownerElementMap.javaBaseTypePath().getLast() + ">emptySet()");
			ojClass.addToImports(new OJPathName("java.util.Collections"));
		}
		

		ifIndexNull.addToElsePart(ifHasNext);
		qualifierValue.getBody().addToStatements(ifIndexNull);
		ojClass.addToOperations(qualifierValue);
	}

	private void generateQualifierValue(INakedProperty qualifier) {
		INakedClassifier qualifierContext = (INakedClassifier) ((INakedProperty) qualifier.getOwnerElement()).getBaseType();
		OJAnnotatedClass ojClass = findJavaClass(qualifierContext);
		OJAnnotatedOperation qualifierValue = new OJAnnotatedOperation(TinkerGenerationUtil.getQualifierValueGetterName(qualifier));
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(qualifier);
		qualifierValue.setReturnType(map.isOne() ? map.javaTypePath() : map.javaBaseTypePath());
		qualifierValue.getBody().addToStatements(
				"return " + forceTinkerQualifierToBeOne(ValueSpecificationUtil.expressValue(qualifierValue, qualifier.getInitialValue(), qualifierContext, qualifier.getType())));
		ojClass.addToOperations(qualifierValue);
	}
	
	private String forceTinkerQualifierToBeOne(String expression) {
		if (expression.startsWith("java.util.Collections.singletonList(")) {
			expression = expression.replace("java.util.Collections.singletonList(", "");
			expression = expression.substring(0, expression.length()-1);
		} else if (expression.startsWith("java.util.Collections.singleton(")) {
			expression = expression.replace("java.util.Collections.singleton(", "");
			expression = expression.substring(0, expression.length()-1);
		}
		return expression;
	}

	@Override
	protected void visitComplexStructure(INakedComplexStructure umlOwner) {

	}

}
