package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedProperty;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class HibernateDerivedUnionImplStrategy implements DerivedUnionImplStrategy {

	@Override
	public void addSubsetToUnion(NakedStructuralFeatureMap subsettingMap, OJClass c, INakedProperty derivedUnion) {
		NakedStructuralFeatureMap derivedUnionMap = new NakedStructuralFeatureMap(derivedUnion);
		OJPathName type = derivedUnionMap.javaTypePath();
		OJOperation sgetter = OJUtil.findOperation(c, derivedUnionMap.getter());
		String returnParameterName = derivedUnionMap.umlName() + "Subsetting";
		if (sgetter == null) {
			sgetter = buildGetterForDerivedUnion(c, type, derivedUnionMap, returnParameterName);
		} else {
			// TODO investigate why this is necessary
			if (sgetter.getBody().getStatements().size() > 0) {
				// remove return statement
				sgetter.getBody().getStatements().remove(sgetter.getBody().getStatements().size() - 1);
			}
		}
		OJAnnotatedField returnParameter = getReturnParameter(sgetter);
		returnParameter.setName(returnParameterName);
		returnParameter.setType(type);
		if (returnParameter.getInitExp() == null) {
			// retain the possible derivation rule initialization
			returnParameter.setInitExp(derivedUnionMap.javaDefaultValue());
		}
		c.addToImports(type);
		c.addToImports(derivedUnionMap.javaDefaultTypePath());
		String expression = buildExpression(subsettingMap, derivedUnion);

		if (subsettingMap.isOne()) {
			if (derivedUnionMap.isOne()) {
				// TODO this could be problematic if multiple subsetting
				// properties are not null
				OJIfStatement ifNotNull = new OJIfStatement(expression + "!=null", returnParameterName + "=" + expression);
				sgetter.getBody().addToStatements(ifNotNull);
			} else {
				OJIfStatement ifNotNull = new OJIfStatement(expression + "!=null", returnParameterName + ".add(" + expression + ")");
				sgetter.getBody().addToStatements(ifNotNull);
			}
		} else {
			if (derivedUnionMap.isOne()) {
				// TODO implement validation. This should not be allowed
			} else {
				sgetter.getBody().addToStatements(returnParameterName + ".addAll(" + expression + ")");
			}
		}
		sgetter.getBody().addToStatements("return " + returnParameterName);
	}
	
	private OJOperation buildGetterForDerivedUnion(OJClass c, OJPathName type, NakedStructuralFeatureMap derivedUnionMap, String returnParameterName) {
		OJOperation sgetter;
		// we typically end up here when the subsetted property is not
		// defined in this class, but in a superclass or interface
		sgetter = new OJAnnotatedOperation();
		sgetter.setName(derivedUnionMap.getter());
		sgetter.setReturnType(type);
		c.addToOperations(sgetter);
		// retrieve the potentially subsetting superclass implementation of
		// the property
		if (derivedUnionMap.isMany()) {
			sgetter.getBody().addToStatements(returnParameterName + ".addAll(super." + derivedUnionMap.getter() + "())");
		} else {
			sgetter.getBody().addToStatements(returnParameterName + "=super." + derivedUnionMap.getter() + "()");
		}
		return sgetter;
	}
	
	private OJAnnotatedField getReturnParameter(OJOperation sgetter) {
		OJAnnotatedField init = null;
		if (sgetter.getBody().getLocals().size() == 1) {
			init = (OJAnnotatedField) sgetter.getBody().getLocals().get(0);
		} else {
			OJAnnotatedField sinit = new OJAnnotatedField();
			sgetter.getBody().addToLocals(sinit);
			init = sinit;
		}
		return init;
	}
	
	private String buildExpression(NakedStructuralFeatureMap mapOfSubsettingProperty, INakedProperty subsettedProperty) {
		String expression = mapOfSubsettingProperty.getter() + "()";
		if (subsettedProperty.getName().equals(mapOfSubsettingProperty.umlName())) {
			// we need to avoid recursion now
			if (mapOfSubsettingProperty.getFeature().isDerived()) {
				// do nothing the derivation rule will apply to the init
				// expression
			} else if (((INakedProperty) mapOfSubsettingProperty.getFeature()).isDerivedUnion()) {
				// TODO leverage the derivation here
				throw new IllegalStateException("DerivedUnion Subsetting with DerivedUnion Subsetted properties must have different names");
			} else {
				expression = "this." + mapOfSubsettingProperty.umlName();
			}
		}
		return expression;
	}	

}
