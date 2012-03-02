package org.opaeum.rap;

import org.opaeum.bootstrap.AbstractBootstrapStep;
import org.opaeum.bootstrap.BootstrapGenerationPhase;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.StepDependency;
import org.opaeum.javageneration.composition.FactoryMethodCreator;
import org.opaeum.javageneration.hibernate.EnumResolverImplementor;
import org.opaeum.javageneration.hibernate.HibernateAttributeImplementor;
import org.opaeum.javageneration.jbpm5.ProcessStepResolverImplementor;
import org.opaeum.javageneration.organization.OrganizationImplementor;
import org.opaeum.linkage.QualifierLogicCalculator;

@StepDependency(phase = BootstrapGenerationPhase.class,requires = {RapProjectBuilder.class,EnumResolverImplementor.class,
		ProcessStepResolverImplementor.class, HibernateAttributeImplementor.class,FactoryMethodCreator.class,OrganizationImplementor.class,QualifierLogicCalculator.class})
public class RapCapabilities extends AbstractBootstrapStep implements ITransformationStep{
	public RapCapabilities(){
	}
}
