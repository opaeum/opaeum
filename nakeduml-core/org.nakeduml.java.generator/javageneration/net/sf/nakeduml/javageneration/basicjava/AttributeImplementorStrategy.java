package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.metamodel.core.INakedClassifier;

import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;

public interface AttributeImplementorStrategy {
	void addSimpleSetterBody(INakedClassifier umlOwner, NakedStructuralFeatureMap map, OJAnnotatedClass owner, OJOperation setter);

	OJOperation buildGetter(OJAnnotatedClass owner, NakedStructuralFeatureMap map, boolean returnDefault);

	void buildManyToOneSetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildOneToOneSetter(INakedClassifier umlOwner, NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildOneToOneSetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildManyToOneSetter(INakedClassifier umlOwner, NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildOneToManySetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildManyToManySetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildManyAdder(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJOperation adder);

	void buildManyRemover(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJOperation adder);

	void addSimpleAdder(NakedStructuralFeatureMap map, OJOperation adder);

	void buildSimpleRemover(NakedStructuralFeatureMap map, OJOperation remover);

}
