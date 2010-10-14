package net.sf.nakeduml.javageneration.hibernate;

import static net.sf.nakeduml.metamodel.core.internal.StereotypeNames.NAVIGATION_TOO_MANY;

import java.util.Collection;
import java.util.Set;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.composition.ExtendedCompositionSemantics;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationAttributeValue;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import nl.klasse.octopus.model.IClassifier;

@StepDependency(phase = JavaTransformationPhase.class,requires = {ExtendedCompositionSemantics.class},after = {ExtendedCompositionSemantics.class})
public class TooManyNavigationSupport extends AbstractJavaProducingVisitor {

	@VisitBefore(matchSubclasses = true)
	public void visitPropertyBefore(INakedProperty property) {
		if (property.hasStereotype(NAVIGATION_TOO_MANY)) {
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
		Set<OJAnnotationValue> annotations = ojClass.getAnnotations();
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
