package org.opaeum.generation.features;

import org.opaeum.feature.StepDependency;
import org.opaeum.javageneration.JavaFeature;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.oclexpressions.AttributeExpressionGenerator;
import org.opaeum.javageneration.oclexpressions.ConstrainedImplementor;
import org.opaeum.javageneration.oclexpressions.InvariantsGenerator;
import org.opaeum.javageneration.oclexpressions.PreAndPostConditionGenerator;
import org.opaeum.javageneration.oclexpressions.UtilCreator;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		AttributeExpressionGenerator.class,PreAndPostConditionGenerator.class,InvariantsGenerator.class,ConstrainedImplementor.class,UtilCreator.class
})
public class OclExpressionExecution extends JavaFeature{
}
