package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;

import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;

public interface AttributeImplementorStrategy {
	void addSimpleSetterBody(OJOperation setter, NakedStructuralFeatureMap map);
	OJOperation buildGetter(OJAnnotatedClass owner,NakedStructuralFeatureMap map,boolean returnDefault);
	void buildManyToOneSetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter);
	void buildOneToOneSetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter);
}
