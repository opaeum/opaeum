package org.opaeum.rap;

import org.opaeum.bootstrap.AbstractBootstrapStep;
import org.opaeum.bootstrap.BootstrapGenerationPhase;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.StepDependency;
import org.opaeum.javageneration.bpm.ProcessStepResolverImplementor;
import org.opaeum.javageneration.composition.FactoryMethodCreator;
import org.opaeum.javageneration.hibernate.EnumResolverImplementor;
import org.opaeum.javageneration.rap.OpaeumApplicationGenerator;
import org.opaeum.javageneration.rap.RapAttributeImplementor;
import org.opaeum.olap.MondrianCubeGenerator;

@StepDependency(phase = BootstrapGenerationPhase.class,requires = {RapProjectBuilder.class,EnumResolverImplementor.class,
		ProcessStepResolverImplementor.class,RapAttributeImplementor.class,FactoryMethodCreator.class,
		MondrianCubeGenerator.class, OpaeumApplicationGenerator.class})
public class RapCapabilities extends AbstractBootstrapStep implements ITransformationStep{
	public RapCapabilities(){
	}
}
