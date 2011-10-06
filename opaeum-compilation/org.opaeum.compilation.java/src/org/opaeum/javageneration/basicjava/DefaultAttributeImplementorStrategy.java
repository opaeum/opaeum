package org.opaeum.javageneration.basicjava;

import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.metamodel.core.INakedClassifier;

public class DefaultAttributeImplementorStrategy implements AttributeImplementorStrategy {

	@Override
	public void addSimpleSetterBody(INakedClassifier umlOwner, NakedStructuralFeatureMap map, OJAnnotatedClass owner, OJOperation setter) {
	}

	@Override
	public OJOperation buildGetter(OJAnnotatedClass owner, NakedStructuralFeatureMap map, boolean returnDefault) {
		OJAnnotatedOperation getter = new OJAnnotatedOperation(map.getter());
		return getter;
	}

	@Override
	public void buildManyToOneSetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
	}

	@Override
	public void buildOneToOneSetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
	}


	@Override
	public void buildManyToOneSetter(INakedClassifier umlOwner, NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
		buildManyToOneSetter(map, otherMap, owner, setter);
	}

	@Override
	public void buildOneToOneSetter(INakedClassifier umlOwner, NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
		buildOneToOneSetter(map, otherMap, owner, setter);
	}

	@Override
	public void buildOneToManySetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
		// Delegate to the setter of the non-inverse end which will do all
		// the work
	}

	@Override
	public void buildManyToManySetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
	}

	@Override
	public void buildManyAdder(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJOperation adder) {
		adder.getBody().addToStatements(map.umlName() + "." + otherMap.getter() + "().add(this)");
		adder.getBody().addToStatements(map.getter() + "().add(" + map.umlName() + ")");
	}

	@Override
	public void buildManyRemover(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJOperation adder) {
		adder.getBody().addToStatements(map.umlName() + "." + otherMap.getter() + "().remove(this)");
		adder.getBody().addToStatements(map.getter() + "().remove(" + map.umlName() + ")");
	}

	@Override
	public void addSimpleAdder(NakedStructuralFeatureMap map, OJOperation adder) {
		adder.getBody().addToStatements(map.getter() + "().add(" + map.umlName() + ")");
	}

	@Override
	public void buildSimpleRemover(NakedStructuralFeatureMap map, OJOperation remover) {
		remover.getBody().addToStatements(map.getter() + "().remove(" + map.umlName() + ")");
	}

}
