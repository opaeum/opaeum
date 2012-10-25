package org.opaeum.javageneration.basicjava;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Classifier;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;

public interface AttributeImplementorStrategy {
	void addSimpleSetterBody(Classifier umlOwner, PropertyMap map, OJAnnotatedClass owner, OJOperation setter);

	OJOperation buildGetter(OJAnnotatedClass owner, PropertyMap map, boolean returnDefault);

	void buildManyToOneSetter(PropertyMap map, PropertyMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildOneToOneSetter(Classifier umlOwner, PropertyMap map, PropertyMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildOneToOneSetter(PropertyMap map, PropertyMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildManyToOneSetter(Classifier umlOwner, PropertyMap map, PropertyMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildOneToManySetter(PropertyMap map, PropertyMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildManyToManySetter(PropertyMap map, PropertyMap otherMap, OJAnnotatedClass owner, OJOperation setter);

	void buildManyAdder(PropertyMap map, PropertyMap otherMap, OJOperation adder);

	void buildManyRemover(PropertyMap map, PropertyMap otherMap, OJOperation adder);

	void addSimpleAdder(PropertyMap map, OJOperation adder);

	void buildSimpleRemover(PropertyMap map, OJOperation remover);

}
