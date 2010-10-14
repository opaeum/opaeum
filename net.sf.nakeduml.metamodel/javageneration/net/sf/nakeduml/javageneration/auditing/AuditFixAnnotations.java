package net.sf.nakeduml.javageneration.auditing;

import java.util.List;
import java.util.Set;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationAttributeValue;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.metamodel.core.INakedClassifier;

public class AuditFixAnnotations extends AbstractJavaProducingVisitorForAudit {

	@VisitBefore(matchSubclasses = true)
	public void visitClasses(INakedClassifier classifier) {
		if (isPersistent(classifier) && hasOJClass(classifier)) {
			OJPathName path = OJUtil.classifierPathname(classifier);
			path.replaceTail(path.getLast()+"_Audit");
			OJAnnotatedClass c = (OJAnnotatedClass) this.javaModel.findIntfOrCls(path);
			if (c != null) {
				Set<OJAnnotationValue> annotations = c.getAnnotations();
				for (OJAnnotationValue ojAnnotationValue : annotations) {
					if (ojAnnotationValue.getType().equals(new OJPathName("org.jboss.seam.annotations.Role"))) {
						OJAnnotationAttributeValue name = ojAnnotationValue.findAttribute("name");
						String nameValue = (String) name.getValues().remove(0);
						name.getValues().add(nameValue + "Audit");
					} else if (ojAnnotationValue.getType().equals(new OJPathName("javax.persistence.NamedQueries"))) {
						OJAnnotationAttributeValue attr = ojAnnotationValue.findAttribute("value");
						List<OJAnnotationValue> namedQueries2 = attr.getAnnotationValues();
						for (OJAnnotationValue ojAnnotationValue2 : namedQueries2) {
							OJAnnotationAttributeValue name = ojAnnotationValue2.findAttribute("name");
							String nameValue = (String) name.getValues().remove(0);
							name.getValues().add(nameValue + "Audit");
						}
					}
				}
			}
		}
	}

	// @VisitBefore(matchSubclasses = true)
	public void visitClasses(OJAnnotatedClass c) {
		Set<OJAnnotationValue> annotations = c.getAnnotations();
		for (OJAnnotationValue ojAnnotationValue : annotations) {
			if (ojAnnotationValue.getType().equals(new OJPathName("org.jboss.seam.annotations.Role"))) {
				OJAnnotationAttributeValue name = ojAnnotationValue.findAttribute("name");
				String nameValue = (String) name.getValues().remove(0);
				name.getValues().add(nameValue + "Audit");
			} else if (ojAnnotationValue.getType().equals(new OJPathName("javax.persistence.NamedQueries"))) {
				OJAnnotationAttributeValue namedQueries = ojAnnotationValue.findAttribute("value");
				List<OJAnnotationValue> namedQueries2 = namedQueries.getAnnotationValues();
				for (OJAnnotationValue ojAnnotationValue2 : namedQueries2) {
					OJAnnotationAttributeValue name = ojAnnotationValue2.findAttribute("name");
					String nameValue = (String) name.getValues().remove(0);
					name.getValues().add(nameValue + "Audit");
				}
			}
		}
	}
}
