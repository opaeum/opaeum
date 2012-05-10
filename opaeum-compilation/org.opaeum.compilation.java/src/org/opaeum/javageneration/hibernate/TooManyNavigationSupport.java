package org.opaeum.javageneration.hibernate;

import java.util.Collection;

import nl.klasse.octopus.model.IClassifier;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.persistence.JpaAnnotator;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.StereotypeNames;

@StepDependency(phase = JavaTransformationPhase.class,requires = JpaAnnotator.class)
public class TooManyNavigationSupport extends AbstractJavaProducingVisitor{

	@VisitBefore(matchSubclasses = true)
	public void visitPropertyBefore(INakedProperty property) {
		if (property.hasStereotype(StereotypeNames.NAVIGATION_TOO_MANY)) {
			INakedClassifier c = property.getOwner();

			if (c.getIsAbstract()) {
				Collection<IClassifier> subClasses = c.getSubClasses();
				for (IClassifier subClass : subClasses) {
					createNamedQueryAnnotation(property, (INakedClassifier)subClass);
				}
			} else {
				createNamedQueryAnnotation(property, c);
			}

		}
	}

	private void createNamedQueryAnnotation(INakedProperty property, INakedClassifier classifier) {
		OJAnnotatedClass ojClass = findJavaClass((INakedClassifier) classifier);

		OJAnnotationValue namedQueries = null;
		OJAnnotationAttributeValue namedQueryValue = null;
		Collection<OJAnnotationValue> annotations = ojClass.getAnnotations();
		for (OJAnnotationValue ojAnnotationValue : annotations) {
			if (ojAnnotationValue.getType().equals(new OJPathName("javax.persistence.NamedQueries"))) {
				namedQueries = ojAnnotationValue;
				namedQueryValue = namedQueries.findAttribute("value");
			}
		}
		if (namedQueries == null) {
			namedQueries = new OJAnnotationValue(new OJPathName("javax.persistence.NamedQueries"));
			namedQueryValue = new OJAnnotationAttributeValue("value");
			namedQueries.putAttribute(namedQueryValue);
		}
		
		OJAnnotationValue namedQuerySelectCount = new OJAnnotationValue(new OJPathName("javax.persistence.NamedQuery"));
		namedQuerySelectCount.putAttribute(new OJAnnotationAttributeValue("name", classifier.getMappingInfo().getQualifiedJavaName() + "."
				+ property.getMappingInfo().getJavaName() + ".count"));
		namedQuerySelectCount.putAttribute(new OJAnnotationAttributeValue("query", "select count(*) from " + property.getNakedBaseType().getMappingInfo().getJavaName() + " a where a." + property.getOtherEnd().getMappingInfo().getJavaName() + " = ?1"));
		namedQueryValue.addAnnotationValue(namedQuerySelectCount);

		OJAnnotationValue namedQuerySelect = new OJAnnotationValue(new OJPathName("javax.persistence.NamedQuery"));
		namedQuerySelect.putAttribute(new OJAnnotationAttributeValue("name", classifier.getMappingInfo().getQualifiedJavaName() + "."
				+ property.getMappingInfo().getJavaName()));
		namedQuerySelect.putAttribute(new OJAnnotationAttributeValue("query", "from " + property.getNakedBaseType().getMappingInfo().getJavaName() + " a where a." + property.getOtherEnd().getMappingInfo().getJavaName() + " = ?1"));
		namedQueryValue.addAnnotationValue(namedQuerySelect);

		ojClass.addAnnotationIfNew(namedQueries);
	}

}
