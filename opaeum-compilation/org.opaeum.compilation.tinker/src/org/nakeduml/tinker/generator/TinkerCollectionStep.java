package org.nakeduml.tinker.generator;

import java.util.List;
import java.util.Set;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;

@StepDependency(phase = JavaTransformationPhase.class, requires = { TinkerImplementNodeStep.class }, after = { TinkerImplementNodeStep.class })
public class TinkerCollectionStep extends StereotypeAnnotator {

	@VisitAfter(matchSubclasses = true)
	public void visitProperty(INakedActivity c) {
		if (OJUtil.hasOJClass(c)) {
			List<INakedParameter> parameters = c.getArgumentParameters();
			for (INakedParameter p : parameters) {
				if (c.getSpecification() == null) {
					TypedElementPropertyBridge propertyBridge = new TypedElementPropertyBridge(c, p);
					NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(propertyBridge);
					if (!map.getProperty().isDerived() && map.isMany()) {
						visitProperty(findJavaClass(c), map);
					}
				}
			}
		}
	}

	@VisitAfter(matchSubclasses = true)
	public void visitProperty(INakedEntity c) {
		if (OJUtil.hasOJClass(c)) {
			Set<INakedProperty> directlyImplementedAttributes = c.getDirectlyImplementedAttributes();
			for (INakedProperty p : directlyImplementedAttributes) {
				if (p.isNavigable()) {
					if (OJUtil.hasOJClass((INakedClassifier) p.getAssociation())) {
						// visitAssociationClassProperty(c, new
						// AssociationClassEndMap(p));
					} else {
						NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(p);
						if (!map.getProperty().isDerived() && map.isMany()) {
							visitProperty(findJavaClass(c), map);
						}
					}
				}
			}
		}
	}

	public void visitProperty(OJAnnotatedClass ojClass, NakedStructuralFeatureMap map) {
		boolean inverse = map.getProperty().isInverse();
		for (OJConstructor constructor : ojClass.getConstructors()) {
			if (constructor.getParameters().isEmpty()) {
				// Skip default constructor
				continue;
			}
			OJPathName collectionPathName;
			if (map.getProperty().isOrdered() && map.getProperty().isUnique()) {
				if (map.getProperty().hasQualifiers()) {
					collectionPathName = TinkerGenerationUtil.tinkerQualifiedOrderedSetImpl.getCopy();
				} else {
					collectionPathName = TinkerGenerationUtil.tinkerOrderedSetImpl.getCopy();
				}
			} else if (map.getProperty().isOrdered() && !map.getProperty().isUnique()) {
				if (map.getProperty().hasQualifiers()) {
					collectionPathName = TinkerGenerationUtil.tinkerQualifiedSequenceImpl.getCopy();
				} else {
					collectionPathName = TinkerGenerationUtil.tinkerSequenceImpl.getCopy();
				}
			} else if (!map.getProperty().isOrdered() && !map.getProperty().isUnique()) {
				if (map.getProperty().hasQualifiers()) {
					collectionPathName = TinkerGenerationUtil.tinkerQualifiedBagImpl.getCopy();
				} else {
					collectionPathName = TinkerGenerationUtil.tinkerBagImpl.getCopy();
				}
			} else if (!map.getProperty().isOrdered() && map.getProperty().isUnique()) {
				if (map.getProperty().hasQualifiers()) {
					collectionPathName = TinkerGenerationUtil.tinkerQualifiedSetImpl.getCopy();
				} else {
					collectionPathName = TinkerGenerationUtil.tinkerSetImpl.getCopy();
				}
			} else {
				throw new RuntimeException("wtf");
			}

			ojClass.addToImports(collectionPathName);
			collectionPathName.addToElementTypes(map.javaBaseTypePath());

			OJSimpleStatement initCollection;
			OJSimpleStatement ojSimpleStatement = initCollection = new OJSimpleStatement(map.umlName() + " = new " + collectionPathName.getCollectionTypeName() + "(this, \""
					+ TinkerGenerationUtil.getEdgeName(map, inverse) + "\"");
			if (map.getProperty().getQualifiers().isEmpty() && map.getProperty().isOrdered()) {
				ojSimpleStatement.setExpression(ojSimpleStatement.getExpression() + ", getUid()");
			} else if (!map.getProperty().getQualifiers().isEmpty()) {
				ojSimpleStatement.setExpression(ojSimpleStatement.getExpression() + ", getUid()");
			}
			// Specify inverse boolean
			if (map.getProperty().isInverse() || map.getProperty().getOtherEnd() == null || !map.getProperty().getOtherEnd().isNavigable()) {
				ojSimpleStatement.setExpression(ojSimpleStatement.getExpression() + ", true");
			} else {
				ojSimpleStatement.setExpression(ojSimpleStatement.getExpression() + ", false");
			}
			// Specify manyToMany boolean
			if (!map.isManyToMany() || map.getProperty().getOtherEnd() == null || !map.getProperty().getOtherEnd().isNavigable()) {
				ojSimpleStatement.setExpression(ojSimpleStatement.getExpression() + ", false");
			} else {
				ojSimpleStatement.setExpression(ojSimpleStatement.getExpression() + ", true");
			}
			// Specify composite boolean
			ojSimpleStatement.setExpression(ojSimpleStatement.getExpression() + ", " + map.getProperty().isComposite());
			ojSimpleStatement.setExpression(ojSimpleStatement.getExpression() + ")");
			constructor.getBody().addToStatements(initCollection);
		}
	}

}
