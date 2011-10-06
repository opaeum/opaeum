package org.opaeum.compilation.tinker;

import nl.klasse.octopus.model.internal.types.AttributeImpl;

import org.opaeum.feature.StepDependency;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AttributeImplementor;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	AttributeImplementor.class
},after = {
	AttributeImplementor.class
})
public class TinkerStep extends AbstractJavaProducingVisitor{
}
