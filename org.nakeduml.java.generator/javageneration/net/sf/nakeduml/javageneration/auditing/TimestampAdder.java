package net.sf.nakeduml.javageneration.auditing;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;

import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.runtime.domain.BaseAuditable;

public class TimestampAdder extends AbstractJavaProducingVisitor {
	public static final boolean DEVELOPMENT_MODE = true;

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedClassifier c) {
		if (isPersistent(c) && OJUtil.hasOJClass(c) && c.getSupertype() == null) {
			INakedComplexStructure umlClass = (INakedComplexStructure) c;
			OJAnnotatedClass ojClass = findJavaClass(umlClass);
			ojClass.setSuperclass(new OJPathName(BaseAuditable.class.getName()));
		}
	}
}
