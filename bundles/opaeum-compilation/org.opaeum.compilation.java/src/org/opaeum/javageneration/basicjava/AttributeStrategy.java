package org.opaeum.javageneration.basicjava;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Classifier;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;

public interface AttributeStrategy{
	void init(AbstractAttributeImplementer source);
	OJAnnotatedOperation buildGetter(Classifier umlOwner,OJAnnotatedClass owner,PropertyMap map,boolean derived);
	OJAnnotatedOperation buildInternalRemover(OJAnnotatedClass owner,PropertyMap map);
	OJAnnotatedOperation buildInternalAdder(OJAnnotatedClass owner,PropertyMap map);
	OJAnnotatedField buildField(OJAnnotatedClass owner,PropertyMap map);
	void beforeProperty(OJAnnotatedClass owner,Classifier umlOwner,PropertyMap map);
	void startSetter(OJAnnotatedClass owner, OJAnnotatedOperation setter, PropertyMap map);
}
