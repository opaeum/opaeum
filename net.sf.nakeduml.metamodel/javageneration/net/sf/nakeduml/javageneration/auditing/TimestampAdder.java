package net.sf.nakeduml.javageneration.auditing;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;

public class TimestampAdder extends AbstractJavaProducingVisitor{
	public static final boolean DEVELOPMENT_MODE = true;
	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedClassifier c){
		if(isPersistent(c) && hasOJClass(c) && c.getSupertype()==null){
			INakedComplexStructure umlClass = (INakedComplexStructure) c;
			OJAnnotatedClass ojClass = findJavaClass(umlClass);
			ojClass.setSuperclass(new OJPathName("net.sf.nakeduml.util.BaseAuditable"));
		}
	}
}
