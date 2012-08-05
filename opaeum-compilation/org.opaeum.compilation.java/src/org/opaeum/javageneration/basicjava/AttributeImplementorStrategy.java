package org.opaeum.javageneration.basicjava;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.Classifier;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;

public interface AttributeImplementorStrategy {
	void addSimpleSetterBody(Classifier umlOwner, StructuralFeatureMap map, OJAnnotatedClass owner, OJOperation setter);

	OJOperation buildGetter(OJAnnotatedClass owner, StructuralFeatureMap map, boolean returnDefault);

	void buildManyToOneSetter(StructuralFeatureMap map, StructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildOneToOneSetter(Classifier umlOwner, StructuralFeatureMap map, StructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildOneToOneSetter(StructuralFeatureMap map, StructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildManyToOneSetter(Classifier umlOwner, StructuralFeatureMap map, StructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildOneToManySetter(StructuralFeatureMap map, StructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildManyToManySetter(StructuralFeatureMap map, StructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildManyAdder(StructuralFeatureMap map, StructuralFeatureMap otherMap, OJOperation adder);

	void buildManyRemover(StructuralFeatureMap map, StructuralFeatureMap otherMap, OJOperation adder);

	void addSimpleAdder(StructuralFeatureMap map, OJOperation adder);

	void buildSimpleRemover(StructuralFeatureMap map, OJOperation remover);

}
