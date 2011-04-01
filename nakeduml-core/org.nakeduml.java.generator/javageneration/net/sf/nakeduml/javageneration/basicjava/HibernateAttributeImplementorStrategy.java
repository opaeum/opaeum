package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class HibernateAttributeImplementorStrategy implements AttributeImplementorStrategy {

	@Override
	public void addSimpleSetterBody(OJOperation setter, NakedStructuralFeatureMap map) {
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

}
