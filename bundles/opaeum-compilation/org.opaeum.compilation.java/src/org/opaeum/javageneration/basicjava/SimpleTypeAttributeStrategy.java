package org.opaeum.javageneration.basicjava;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory.ISimpleTypeStrategy;

public interface SimpleTypeAttributeStrategy extends ISimpleTypeStrategy{
	void applyTo(OJUtil ojUtil,OJAnnotatedClass owner,AttributeInJava a, PropertyMap property);
}
