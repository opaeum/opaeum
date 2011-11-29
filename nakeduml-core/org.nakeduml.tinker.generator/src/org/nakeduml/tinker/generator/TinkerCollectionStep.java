package org.nakeduml.tinker.generator;

import java.util.Set;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedProperty;

import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;

@StepDependency(phase = JavaTransformationPhase.class, requires = { TinkerImplementNodeStep.class }, after = { TinkerImplementNodeStep.class })
public class TinkerCollectionStep extends StereotypeAnnotator {

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
						visitProperty(c, OJUtil.buildStructuralFeatureMap(p));
					}
				}
			}
		}
	}

	private void visitProperty(INakedEntity c, NakedStructuralFeatureMap map) {
		if (!map.getProperty().isDerived() && map.isMany()) {
			OJAnnotatedClass ojClass = findJavaClass(c);
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
						+ TinkerGenerationUtil.getEdgeName(map) + "\"");
				if (map.getProperty().getQualifiers().isEmpty() && map.getProperty().isOrdered()) {
					ojSimpleStatement.setExpression(ojSimpleStatement.getExpression() + ", getUid()");
				} else if (!map.getProperty().getQualifiers().isEmpty()) {
					ojSimpleStatement.setExpression(ojSimpleStatement.getExpression() + ", getUid()");
				}
				//Specify inverse boolean
				if (map.getProperty().isInverse() || map.getProperty().getOtherEnd()==null || !map.getProperty().getOtherEnd().isNavigable()) {
					ojSimpleStatement.setExpression(ojSimpleStatement.getExpression() + ", true");
				} else {
					ojSimpleStatement.setExpression(ojSimpleStatement.getExpression() + ", false");
				}
				//Specify manyToMany boolean
				if (!map.isManyToMany() || map.getProperty().getOtherEnd()==null || !map.getProperty().getOtherEnd().isNavigable()) {
					ojSimpleStatement.setExpression(ojSimpleStatement.getExpression() + ", false");
				} else {
					ojSimpleStatement.setExpression(ojSimpleStatement.getExpression() + ", true");
				}
				ojSimpleStatement.setExpression(ojSimpleStatement.getExpression() + ")");
				constructor.getBody().addToStatements(initCollection);
			}
		}
	}

}
