package org.opaeum.javageneration.basicjava;

import org.eclipse.uml2.uml.Classifier;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;

public interface AttributeImplementorStrategy {
	void addSimpleSetterBody(Classifier umlOwner, NakedStructuralFeatureMap map, OJAnnotatedClass owner, OJOperation setter);

	OJOperation buildGetter(OJAnnotatedClass owner, NakedStructuralFeatureMap map, boolean returnDefault);

	void buildManyToOneSetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildOneToOneSetter(Classifier umlOwner, NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildOneToOneSetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildManyToOneSetter(Classifier umlOwner, NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildOneToManySetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildManyToManySetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildManyAdder(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJOperation adder);

	void buildManyRemover(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJOperation adder);

	void addSimpleAdder(NakedStructuralFeatureMap map, OJOperation adder);

	void buildSimpleRemover(NakedStructuralFeatureMap map, OJOperation remover);

}
