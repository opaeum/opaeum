package org.nakeduml.tinker.generator;

import java.util.List;
import java.util.Set;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.OJWorkspace;
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

	public void setJavaModel(OJWorkspace javaModel) {
		this.javaModel = javaModel;
	}
	
	@VisitAfter(matchSubclasses = true)
	public void visitProperty(INakedActivity c) {
		if (OJUtil.hasOJClass(c)) {
			List<INakedParameter> parameters = c.getArgumentParameters();
			for (INakedParameter p : parameters) {
				if (c.getSpecification() == null) {
					TypedElementPropertyBridge propertyBridge = new TypedElementPropertyBridge(c, p);
					NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(propertyBridge);
					if (!map.getProperty().isDerived() && map.isMany()) {
						visitManyProperty(findJavaClass(c), map);
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
							visitManyProperty(findJavaClass(c), map);
						}
					}
				}
			}
		}
	}

	public void visitManyProperty(OJAnnotatedClass ojClass, NakedStructuralFeatureMap map) {
		for (OJConstructor constructor : ojClass.getConstructors()) {
			if (constructor.getParameters().isEmpty()) {
				// Skip default constructor
				continue;
			}
			OJPathName collectionPathName = TinkerGenerationUtil.getDefaultTinkerCollection(map);

			ojClass.addToImports(collectionPathName);

			OJSimpleStatement ojSimpleStatement = new OJSimpleStatement(map.fieldname() + " = ");
			ojSimpleStatement.setExpression(ojSimpleStatement.getExpression() + TinkerGenerationUtil.getDefaultTinkerCollectionInitalisation(map, collectionPathName).getExpression());
			constructor.getBody().addToStatements(ojSimpleStatement);
		}
	}
}
