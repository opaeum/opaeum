package org.opaeum.javageneration.basicjava;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory.ISimpleTypeStrategy;

public interface AttributeStrategy extends ISimpleTypeStrategy{
	void applyTo(OJAnnotatedClass owner,AttributeInJava a,PropertyMap property);
}
