package org.opaeum.javageneration.basicjava;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Classifier;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;

public class DefaultAttributeImplementorStrategy implements AttributeImplementorStrategy {

	@Override
	public void addSimpleSetterBody(Classifier umlOwner, PropertyMap map, OJAnnotatedClass owner, OJOperation setter) {
	}

	@Override
	public OJOperation buildGetter(OJAnnotatedClass owner, PropertyMap map, boolean returnDefault) {
		OJAnnotatedOperation getter = new OJAnnotatedOperation(map.getter());
		return getter;
	}

	@Override
	public void buildManyToOneSetter(PropertyMap map, PropertyMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
	}

	@Override
	public void buildOneToOneSetter(PropertyMap map, PropertyMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
	}


	@Override
	public void buildManyToOneSetter(Classifier umlOwner, PropertyMap map, PropertyMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
		buildManyToOneSetter(map, otherMap, owner, setter);
	}

	@Override
	public void buildOneToOneSetter(Classifier umlOwner, PropertyMap map, PropertyMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
		buildOneToOneSetter(map, otherMap, owner, setter);
	}

	@Override
	public void buildOneToManySetter(PropertyMap map, PropertyMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
		// Delegate to the setter of the non-inverse end which will do all
		// the work
	}

	@Override
	public void buildManyToManySetter(PropertyMap map, PropertyMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
	}

	@Override
	public void buildManyAdder(PropertyMap map, PropertyMap otherMap, OJOperation adder) {
		adder.getBody().addToStatements(map.fieldname() + "." + otherMap.getter() + "().add(this)");
		adder.getBody().addToStatements(map.getter() + "().add(" + map.fieldname() + ")");
	}

	@Override
	public void buildManyRemover(PropertyMap map, PropertyMap otherMap, OJOperation adder) {
		adder.getBody().addToStatements(map.fieldname() + "." + otherMap.getter() + "().remove(this)");
		adder.getBody().addToStatements(map.getter() + "().remove(" + map.fieldname() + ")");
	}

	@Override
	public void addSimpleAdder(PropertyMap map, OJOperation adder) {
		adder.getBody().addToStatements(map.getter() + "().add(" + map.fieldname() + ")");
	}

	@Override
	public void buildSimpleRemover(PropertyMap map, OJOperation remover) {
		remover.getBody().addToStatements(map.getter() + "().remove(" + map.fieldname() + ")");
	}

}
