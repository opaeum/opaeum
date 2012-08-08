package org.nakeduml.tinker.generator;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.name.NameConverter;

@StepDependency(phase = JavaTransformationPhase.class, after = { TinkerAttributeImplementor.class })
public class TinkerQualifierGenerator extends AbstractStructureVisitor {

	@Override
	protected void visitProperty(Classifier owner, StructuralFeatureMap map) {
		if (!map.getProperty().getQualifiers().isEmpty()) {
			// For each qualifier gen a method returning the default value, i.e.
			// jippo QualifierValue
			for (Property q : map.getProperty().getQualifiers()) {
				generateQualifiedGetter(q);
			}
		}
	}

	private void generateQualifiedGetter(Property qualifier) {
		Property ownerElement = (Property) qualifier.getOwner();
		Classifier qualifiedClassifier = (Classifier) EmfElementFinder.getContainer(ownerElement);
		OJAnnotatedClass ojClass = findJavaClass(qualifiedClassifier);

		StructuralFeatureMap ownerElementMap = ojUtil.buildStructuralFeatureMap(ownerElement);
		StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(qualifier);
		OJAnnotatedOperation qualifierValue = new OJAnnotatedOperation(ownerElementMap.getter() + "For" + NameConverter.capitalize(map.fieldname()));
		if (!qualifier.isMultivalued()) {
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
			if (EmfPropertyUtil.isInverse( ownerElementMap.getProperty()) || ownerElementMap.getProperty().getOtherEnd()==null || !ownerElementMap.getProperty().getOtherEnd().isNavigable()) {
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



	@Override
	protected void visitComplexStructure(Classifier umlOwner) {

	}

}
