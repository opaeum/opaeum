package org.nakeduml.tinker.basicjava;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractStructuralFeatureVisitor;
import net.sf.nakeduml.javageneration.basicjava.AttributeImplementor;
import net.sf.nakeduml.javageneration.basicjava.AttributeImplementorStrategy;
import net.sf.nakeduml.metamodel.core.INakedClassifier;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class DefaultAttributeImplementorStrategy implements AttributeImplementorStrategy {

	@Override
	public void addSimpleSetterBody(INakedClassifier umlOwner, NakedStructuralFeatureMap map, OJAnnotatedClass owner, OJOperation setter) {
		setter.getBody().addToStatements("this." + map.umlName() + "=" + map.umlName());
	}

	@Override
	public OJOperation buildGetter(OJAnnotatedClass owner, NakedStructuralFeatureMap map, boolean returnDefault) {
		OJOperation getter = new OJAnnotatedOperation();
		getter.setName(map.getter());
		getter.setReturnType(map.javaTypePath());
		owner.addToOperations(getter);
		if (owner instanceof OJAnnotatedInterface) {
		} else if (returnDefault) {
			getter.getBody().addToStatements("return " + map.javaDefaultValue());
		} else {
			getter.getBody().addToStatements("return " + map.umlName());
		}
		getter.setStatic(map.isStatic());
		return getter;
	}

	@Override
	public void buildManyToOneSetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
		// remove "this" from existing reference
		OJIfStatement ifNotNull = new OJIfStatement();
		ifNotNull.setCondition("this." + map.umlName() + "!=null");
		ifNotNull.getThenPart().addToStatements("this." + map.umlName() + "." + otherMap.getter() + "().remove((" + owner.getName() + ")this)");
		setter.getBody().addToStatements(ifNotNull);
		// add "this" to new reference
		OJIfStatement ifParamNotNull = new OJIfStatement();
		ifParamNotNull.setName(AttributeImplementor.IF_PARAM_NOT_NULL);
		ifParamNotNull.setCondition(map.umlName() + "!=null");
		ifParamNotNull.getThenPart().addToStatements(map.umlName() + "." + otherMap.getter() + "().add((" + owner.getName() + ")this)");
		ifParamNotNull.getThenPart().addToStatements("this." + map.umlName() + "=" + map.umlName());
		ifParamNotNull.setElsePart(new OJBlock());
		ifParamNotNull.getElsePart().addToStatements("this." + map.umlName() + "=null");
		setter.getBody().addToStatements(ifParamNotNull);
	}

	@Override
	public void buildOneToOneSetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
		OJAnnotatedField oldValue = new OJAnnotatedField();
		oldValue.setName("oldValue");
		oldValue.setType(map.javaTypePath());
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
		OJForStatement forEachOld = new OJForStatement();
		forEachOld.setCollection("new " + map.javaDefaultTypePath().getLast() + "<" + map.javaBaseType() + ">(this." + map.umlName() + ")");
		forEachOld.setElemName("o");
		forEachOld.setElemType(map.javaBaseTypePath());
		forEachOld.setBody(new OJBlock());
		forEachOld.getBody().addToStatements("o." + otherMap.setter() + "(null)");
		setter.getBody().addToStatements(forEachOld);
		OJForStatement forEachNew = new OJForStatement();
		forEachNew.setCollection(map.umlName());
		forEachNew.setElemName("o");
		forEachNew.setElemType(map.javaBaseTypePath());
		forEachNew.setBody(new OJBlock());
		forEachNew.getBody().addToStatements("o." + otherMap.setter() + "((" + owner.getName() + ")this)");
		setter.getBody().addToStatements(forEachNew);
	}

	@Override
	public void buildManyToManySetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
		// remove from existing references and at
		OJForStatement forEach = new OJForStatement();
		forEach.setCollection("this." + map.umlName());
		forEach.setElemName("o");
		forEach.setElemType(map.javaBaseTypePath());
		OJBlock body = new OJBlock();
		forEach.setBody(body);
		body.addToStatements("o." + otherMap.getter() + "().remove((" + owner.getName() + ")this)");
		setter.getBody().addToStatements(forEach);
		OJForStatement forEachParam = new OJForStatement();
		forEachParam.setCollection(map.umlName());
		forEachParam.setElemName("o");
		forEachParam.setElemType(map.javaBaseTypePath());
		OJBlock forEachParamBody = new OJBlock();
		forEachParam.setBody(forEachParamBody);
		forEachParamBody.addToStatements("o." + otherMap.getter() + "().add((" + owner.getName() + ")this)");
		setter.getBody().addToStatements(forEachParam);
		setter.getBody().addToStatements("this." + map.umlName() + ".clear()");
		setter.getBody().addToStatements("this." + map.umlName() + ".addAll(" + map.umlName() + ")");
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
