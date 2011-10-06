package org.opeum.generation.features;

import org.opeum.feature.StepDependency;
import org.opeum.javageneration.JavaFeature;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.basicjava.FromXmlBuilder;
import org.opeum.javageneration.basicjava.ToXmlStringBuilder;
import org.opeum.javageneration.composition.ComponentInitializer;
import org.opeum.javageneration.composition.CompositionNodeImplementor;
import org.opeum.javageneration.composition.CopyMethodImplementor;
import org.opeum.linkage.CompositionEmulator;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		CompositionEmulator.class,CompositionNodeImplementor.class,ComponentInitializer.class,CopyMethodImplementor.class,
		BasicJava6Model.class,ToXmlStringBuilder.class,FromXmlBuilder.class
},after = {
	BasicJava6Model.class
})
public class ExtendedCompositionSemantics extends JavaFeature{
}
