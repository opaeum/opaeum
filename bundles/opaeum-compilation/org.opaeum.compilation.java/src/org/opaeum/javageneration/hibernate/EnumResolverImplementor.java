package org.opaeum.javageneration.hibernate;

import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.NamedElement;
import org.opaeum.eclipse.CodeGenerationStrategy;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.annotation.OJEnum;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.EnumerationLiteralImplementor;
import org.opaeum.javageneration.persistence.AbstractEnumResolverImplementor;
import org.opaeum.javageneration.util.OJUtil;

@StepDependency(phase = JavaTransformationPhase.class,requires = HibernateAnnotator.class,after = EnumerationLiteralImplementor.class)
public class EnumResolverImplementor extends AbstractEnumResolverImplementor{
	@VisitBefore
	public void visitEnumeration(Enumeration e){
		if(ojUtil.getCodeGenerationStrategy( e)!=CodeGenerationStrategy.NO_CODE){
			boolean requiresJavaRename = ojUtil.requiresJavaRename(e);
			createResolver((OJEnum) findJavaClass(e), e.getOwnedLiterals(),requiresJavaRename?ojUtil.getOldClassifierPathname(e).toJavaString():null);
		}
	}

	@Override
	protected String getLiteralName(NamedElement l){
		return OJUtil.toJavaLiteral((EnumerationLiteral) l);
	}
}
