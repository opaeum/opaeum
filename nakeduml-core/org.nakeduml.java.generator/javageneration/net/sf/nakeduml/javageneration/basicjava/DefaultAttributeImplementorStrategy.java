package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;

import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class DefaultAttributeImplementorStrategy implements AttributeImplementorStrategy {

	@Override
	public void addSimpleSetterBody(INakedClassifier umlOwner, NakedStructuralFeatureMap map, OJAnnotatedClass owner, OJOperation setter) {
	}

	@Override
	public OJOperation buildGetter(OJAnnotatedClass owner, NakedStructuralFeatureMap map, boolean returnDefault) {
		OJAnnotatedOperation getter = new OJAnnotatedOperation(map.getter());
		getter.setReturnType(map.javaTypePath());
		owner.addToOperations(getter);
		if (owner instanceof OJAnnotatedInterface) {
		} else if (returnDefault) {
			getter.getBody().addToStatements("return " + map.javaDefaultValue());
		} else {
			getter.getBody().addToStatements("return " + map.umlName());
		}
		getter.setStatic(map.isStatic());
		INakedElement property = map.getProperty();
		OJUtil.addMetaInfo(getter, property);
		return getter;
	}

	@Override
	public void buildManyToOneSetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
	}

	@Override
	public void buildOneToOneSetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
		OJAnnotatedField oldValue = new OJAnnotatedField("oldValue",map.javaTypePath());
		oldValue.setInitExp("this." + map.umlName());
		setter.getBody().addToLocals(oldValue);
		// If oldValue==null then set the new Value unconditionally
		OJIfStatement ifNull = new OJIfStatement();
		ifNull.setName(AttributeImplementor.IF_OLD_VALUE_NULL);
		ifNull.setCondition("oldValue==null");// && );
		ifNull.getThenPart().addToStatements("this." + map.umlName() + "=" + map.umlName());
		setter.getBody().addToStatements(ifNull);
		OJIfStatement ifNotSame = new OJIfStatement();
		ifNotSame.setCondition("!oldValue.equals(" + map.umlName() + ")");
		ifNull.addToElsePart(ifNotSame);
		ifNotSame.getThenPart().addToStatements("this." + map.umlName() + "=" + map.umlName());
		// remove "this" from existing reference
		ifNotSame.getThenPart().addToStatements("oldValue." + otherMap.internalRemover() + "(this)");
		// add "this" to new reference\
		OJIfStatement ifParamNotNull = new OJIfStatement();
		ifParamNotNull.setCondition(map.umlName() + "!=null");
		ifParamNotNull.getThenPart().addToStatements(map.umlName() + "." + otherMap.internalAdder() + "((" + owner.getName() + ")this)");
		ifNotSame.getThenPart().addToStatements(ifParamNotNull);
		ifNull.getThenPart().addToStatements(ifParamNotNull);
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
