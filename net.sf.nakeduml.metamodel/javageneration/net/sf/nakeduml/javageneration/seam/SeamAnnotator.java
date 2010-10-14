package net.sf.nakeduml.javageneration.seam;

import java.util.ArrayList;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;

public class SeamAnnotator extends AbstractJavaProducingVisitor {
	@VisitAfter(matchSubclasses=true)
	public void visitClass(INakedClassifier c) {
		if (hasOJClass(c)&& isPersistent(c) && c instanceof INakedClassifier && !c.getIsAbstract()) {
			OJAnnotatedClass ojClass = findJavaClass(c);
			INakedClassifier nc = c;
			OJAnnotationValue name = new OJAnnotationValue(new OJPathName("org.jboss.seam.annotations.Name"), nc.getMappingInfo().getJavaName()
					.getDecapped().toString());
			ojClass.putAnnotation(name);
			
			//Make sure seam components are Serializable
			OJPathName serializableNodeInterface = new OJPathName("java.io.Serializable");
			ojClass.addToImplementedInterfaces(serializableNodeInterface);
			
			for(INakedConstraint rule:c.getOwnedRules()){
				if(rule.getConstrainedElement() instanceof INakedTypedElement){
				}else{
					OJAnnotatedOperation oper=(OJAnnotatedOperation) ojClass.findOperation("is" + rule.getMappingInfo().getJavaName().getCapped(), new ArrayList());
					oper.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.hibernate.validator.AssertTrue")));
				}
			}
		}
	}
}
