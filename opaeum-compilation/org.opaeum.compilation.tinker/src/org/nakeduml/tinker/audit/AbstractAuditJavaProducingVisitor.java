package org.nakeduml.tinker.audit;

import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.metamodel.core.INakedClassifier;

public abstract class AbstractAuditJavaProducingVisitor extends StereotypeAnnotator {

	protected OJAnnotatedClass findAuditJavaClass(INakedClassifier classifier){
		OJPathName path = OJAuditUtil.classifierAuditPathname(classifier);
		OJAnnotatedClass owner = (OJAnnotatedClass) this.javaModel.findClass(path);
		if(owner == null){
			owner = (OJAnnotatedClass) this.javaModel.findClass(path);
		}
		return owner;
	}
	
}
