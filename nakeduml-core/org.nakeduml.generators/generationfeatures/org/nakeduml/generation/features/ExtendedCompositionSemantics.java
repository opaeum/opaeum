package org.nakeduml.generation.features;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaFeature;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.FromXmlBuilder;
import net.sf.nakeduml.javageneration.basicjava.ToXmlStringBuilder;
import net.sf.nakeduml.javageneration.composition.ComponentInitializer;
import net.sf.nakeduml.javageneration.composition.CompositionNodeImplementor;
import net.sf.nakeduml.javageneration.composition.CopyMethodImplementor;
import net.sf.nakeduml.javageneration.composition.FactoryMethodCreator;
import net.sf.nakeduml.linkage.CompositionEmulator;
import net.sf.nakeduml.pomgeneration.BasicJavaAdaptorPomStep;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		CompositionEmulator.class,BasicJavaAdaptorPomStep.class,CompositionNodeImplementor.class,ComponentInitializer.class,CopyMethodImplementor.class,
		FactoryMethodCreator.class,BasicJava6Model.class,ToXmlStringBuilder.class,FromXmlBuilder.class
},after = {
	BasicJava6Model.class
})
public class ExtendedCompositionSemantics extends JavaFeature{
}
