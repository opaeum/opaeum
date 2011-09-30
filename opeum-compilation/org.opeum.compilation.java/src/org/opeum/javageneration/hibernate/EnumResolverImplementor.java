package org.opeum.javageneration.hibernate;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.java.metamodel.annotation.OJEnum;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.basicjava.EnumerationLiteralImplementor;
import org.opeum.javageneration.persistence.AbstractEnumResolverImplementor;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedEnumeration;

@StepDependency(phase = JavaTransformationPhase.class,requires = HibernateAnnotator.class,after = EnumerationLiteralImplementor.class)
public class EnumResolverImplementor extends AbstractEnumResolverImplementor{
	@VisitBefore
	public void visitEnumeration(INakedEnumeration e){
		if(!e.getCodeGenerationStrategy().isNone()){
			createResolver((OJEnum) findJavaClass(e), e.getOwnedLiterals(),e.getMappingInfo().requiresJavaRename()?e.getMappingInfo().getOldQualifiedJavaName():null);
		}
	}

	@Override
	protected String getLiteralName(INakedElement l){
		return l.getName().toUpperCase();
	}
}
