package net.sf.nakeduml.javageneration.basicjava;

import java.util.List;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;

public class DerivedUnionImplementor extends AbstractJavaProducingVisitor {
	
	private DerivedUnionImplStrategy derivedUnionImplStrategy;
	
	@Override
	public void initialize(OJAnnotatedPackage javaModel, NakedUmlConfig config, TextWorkspace textWorkspace, TransformationContext context) {
		super.initialize(javaModel, config, textWorkspace, context);
		if (config.getAttributeImplementationStrategy().equals("HIBERNATE")) {
			derivedUnionImplStrategy = new HibernateDerivedUnionImplStrategy();
		} else if (config.getAttributeImplementationStrategy().equals("NEO4J")) {
			derivedUnionImplStrategy = new Neo4jDerivedUnionImplStrategy();
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void property(INakedProperty p) {
		visitProperty(p.getOwner(), p);
	}

	@VisitBefore(matchSubclasses = true,match={INakedEntity.class, INakedStructuredDataType.class})
	public void classifier(INakedClassifier c) {		
		for (INakedProperty p : c.getEffectiveAttributes()) {
			if(p.getOwner() instanceof INakedInterface){
				visitProperty(c, p);
				if (p.isDerivedUnion()) {
					ensureDerivedUnionImplementation((INakedEntity) c, p);
				}
			}
		}
	}

	private void visitProperty(INakedClassifier owner, INakedProperty p) {
		if (p.isNavigable() && !(owner instanceof INakedInterface)) {
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(p);
			OJClass c = findJavaClass(owner);
			if (p.isDerivedUnion()) {
				implementDefaultValueForDerivedUnion(map, c);
			}
			for (INakedProperty derivedUnion : p.getSubsettedProperties()) {
				derivedUnionImplStrategy.addSubsetToUnion(map, c, derivedUnion);
			}
		}
	}

/**
 * Ensures that a derived union is implemented for properties inherited from interfaces
 * @param entity
 * @param p
 */
	private void ensureDerivedUnionImplementation(INakedEntity entity, INakedProperty p) {
		
		OJClass ojClass = findJavaClass(entity);
		List<? extends INakedProperty> attrs = entity.getEffectiveAttributes();
		boolean implemented = false;

		for (INakedProperty attr : attrs) {
			if (attr != p) {
				boolean isSubsetted = attr.getSubsettedProperties().contains(p);
				boolean isOverRidden = attr.getName().equals(p.getName());
				if (isOverRidden || isSubsetted) {
					implemented = true;
					break;
				}
			}
		}
		if (!implemented) {
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(p);
			OJOperation getter = OJUtil.findOperation(ojClass, map.getter());
			getter.getBody().getStatements().clear();
			ojClass.addToImports(map.javaDefaultTypePath());
			getter.getBody().addToStatements("return " + map.javaDefaultValue());
		}

	}

	private void implementDefaultValueForDerivedUnion(NakedStructuralFeatureMap subsettedMap, OJClass c) {
		// just a default implementation so that all subclasses are not required
		// to implement it
		OJOperation getter = OJUtil.findOperation(c, subsettedMap.getter());
		// TODO check this logic - only execute if another propert has not yet
		// built this getter
		if (getter.getBody() == null || (getter.getBody().getStatements().size() == 1)) {
			if (subsettedMap.isOne()) {
				getter.setBody(new OJBlock());
				c.addToImports(subsettedMap.javaDefaultTypePath());
				getter.getBody().addToStatements("return " + subsettedMap.javaDefaultValue());
			} else {
				OJPathName type = subsettedMap.javaTypePath();
				OJAnnotatedField sinit = new OJAnnotatedField();
				String returnParameterName = subsettedMap.umlName() + "Subsetting";
				sinit.setName(returnParameterName);
				sinit.setType(type);
				getter.setBody(new OJBlock());
				getter.getBody().addToLocals(sinit);
				sinit.setInitExp(subsettedMap.javaDefaultValue());
				c.addToImports(subsettedMap.javaDefaultTypePath());
				getter.getBody().addToStatements("return " + subsettedMap.javaDefaultValue());
			}
		}
	}

//	private void addSubsetToUnion(NakedStructuralFeatureMap subsettingMap, OJClass c, INakedProperty derivedUnion) {
//		NakedStructuralFeatureMap derivedUnionMap = new NakedStructuralFeatureMap(derivedUnion);
//		OJPathName type = derivedUnionMap.javaTypePath();
//		OJOperation sgetter = OJUtil.findOperation(c, derivedUnionMap.getter());
//		String returnParameterName = derivedUnionMap.umlName() + "Subsetting";
//		if (sgetter == null) {
//			sgetter = buildGetterForDerivedUnion(c, type, derivedUnionMap, returnParameterName);
//		} else {
//			// TODO investigate why this is necessary
//			if (sgetter.getBody().getStatements().size() > 0) {
//				// remove return statement
//				sgetter.getBody().getStatements().remove(sgetter.getBody().getStatements().size() - 1);
//			}
//		}
//		OJAnnotatedField returnParameter = getReturnParameter(sgetter);
//		returnParameter.setName(returnParameterName);
//		returnParameter.setType(type);
//		if (returnParameter.getInitExp() == null) {
//			// retain the possible derivation rule initialization
//			returnParameter.setInitExp(derivedUnionMap.javaDefaultValue());
//		}
//		c.addToImports(type);
//		c.addToImports(derivedUnionMap.javaDefaultTypePath());
//		String expression = buildExpression(subsettingMap, derivedUnion);
//
//		if (subsettingMap.isOne()) {
//			if (derivedUnionMap.isOne()) {
//				// TODO this could be problematic if multiple subsetting
//				// properties are not null
//				OJIfStatement ifNotNull = new OJIfStatement(expression + "!=null", returnParameterName + "=" + expression);
//				sgetter.getBody().addToStatements(ifNotNull);
//			} else {
//				OJIfStatement ifNotNull = new OJIfStatement(expression + "!=null", returnParameterName + ".add(" + expression + ")");
//				sgetter.getBody().addToStatements(ifNotNull);
//			}
//		} else {
//			if (derivedUnionMap.isOne()) {
//				// TODO implement validation. This should not be allowed
//			} else {
//				sgetter.getBody().addToStatements(returnParameterName + ".addAll(" + expression + ")");
//			}
//		}
//		sgetter.getBody().addToStatements("return " + returnParameterName);
//	}

//	private OJOperation buildGetterForDerivedUnion(OJClass c, OJPathName type, NakedStructuralFeatureMap derivedUnionMap, String returnParameterName) {
//		OJOperation sgetter;
//		// we typically end up here when the subsetted property is not
//		// defined in this class, but in a superclass or interface
//		sgetter = new OJAnnotatedOperation();
//		sgetter.setName(derivedUnionMap.getter());
//		sgetter.setReturnType(type);
//		c.addToOperations(sgetter);
//		// retrieve the potentially subsetting superclass implementation of
//		// the property
//		if (derivedUnionMap.isMany()) {
//			sgetter.getBody().addToStatements(returnParameterName + ".addAll(super." + derivedUnionMap.getter() + "())");
//		} else {
//			sgetter.getBody().addToStatements(returnParameterName + "=super." + derivedUnionMap.getter() + "()");
//		}
//		return sgetter;
//	}

//	private String buildExpression(NakedStructuralFeatureMap mapOfSubsettingProperty, INakedProperty subsettedProperty) {
//		String expression = mapOfSubsettingProperty.getter() + "()";
//		if (subsettedProperty.getName().equals(mapOfSubsettingProperty.umlName())) {
//			// we need to avoid recursion now
//			if (mapOfSubsettingProperty.getFeature().isDerived()) {
//				// do nothing the derivation rule will apply to the init
//				// expression
//			} else if (((INakedProperty) mapOfSubsettingProperty.getFeature()).isDerivedUnion()) {
//				// TODO leverage the derivation here
//				throw new IllegalStateException("DerivedUnion Subsetting with DerivedUnion Subsetted properties must have different names");
//			} else {
//				expression = "this." + mapOfSubsettingProperty.umlName();
//			}
//		}
//		return expression;
//	}

//	private OJAnnotatedField getReturnParameter(OJOperation sgetter) {
//		OJAnnotatedField init = null;
//		if (sgetter.getBody().getLocals().size() == 1) {
//			init = (OJAnnotatedField) sgetter.getBody().getLocals().get(0);
//		} else {
//			OJAnnotatedField sinit = new OJAnnotatedField();
//			sgetter.getBody().addToLocals(sinit);
//			init = sinit;
//		}
//		return init;
//	}
}
