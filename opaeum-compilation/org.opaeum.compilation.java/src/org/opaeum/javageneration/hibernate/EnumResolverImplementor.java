package org.opaeum.javageneration.hibernate;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.annotation.OJEnum;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.EnumerationLiteralImplementor;
import org.opaeum.javageneration.persistence.AbstractEnumResolverImplementor;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedEnumeration;

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
