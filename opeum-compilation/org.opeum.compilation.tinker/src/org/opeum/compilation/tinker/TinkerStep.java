package org.opeum.compilation.tinker;

import nl.klasse.octopus.model.internal.types.AttributeImpl;

import org.opeum.feature.StepDependency;
import org.opeum.javageneration.AbstractJavaProducingVisitor;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.basicjava.AttributeImplementor;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	AttributeImplementor.class
},after = {
	AttributeImplementor.class
})
public class TinkerStep extends AbstractJavaProducingVisitor{
}
