package net.sf.nakeduml.javageneration.hibernate;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.BasicJavaModelStep;
import net.sf.nakeduml.javageneration.persistence.AbstractEnumResolverImplementor;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;

import org.nakeduml.java.metamodel.annotation.OJEnum;

@StepDependency(phase = JavaTransformationPhase.class,requires = HibernateAnnotator.class,after = BasicJavaModelStep.class)
public class EnumResolverImplementor extends AbstractEnumResolverImplementor{
	@VisitBefore
	public void visitEnumeration(INakedEnumeration e){
		createResolver((OJEnum) findJavaClass(e), e.getOwnedLiterals());
	}
}
