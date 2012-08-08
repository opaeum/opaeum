package org.opaeum.generation.features;

import org.opaeum.feature.StepDependency;
import org.opaeum.javageneration.JavaFeature;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.FromXmlBuilder;
import org.opaeum.javageneration.basicjava.ToXmlStringBuilder;
import org.opaeum.javageneration.composition.ComponentInitializer;
import org.opaeum.javageneration.composition.CompositionNodeImplementor;
import org.opaeum.javageneration.composition.CopyMethodImplementor;

@StepDependency(phase = JavaTransformationPhase.class,requires = {CompositionNodeImplementor.class,ComponentInitializer.class,CopyMethodImplementor.class,
		BasicJava6Model.class,ToXmlStringBuilder.class,FromXmlBuilder.class
},after = {
	BasicJava6Model.class
})
public class ExtendedCompositionSemantics extends JavaFeature{
}
