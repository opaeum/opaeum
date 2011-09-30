package org.opeum.generation.features;

import org.opeum.feature.StepDependency;
import org.opeum.javageneration.JavaFeature;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.oclexpressions.AttributeExpressionGenerator;
import org.opeum.javageneration.oclexpressions.ConstrainedImplementor;
import org.opeum.javageneration.oclexpressions.InvariantsGenerator;
import org.opeum.javageneration.oclexpressions.OclTestGenerator;
import org.opeum.javageneration.oclexpressions.PreAndPostConditionGenerator;
import org.opeum.javageneration.oclexpressions.UtilCreator;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		AttributeExpressionGenerator.class,PreAndPostConditionGenerator.class,InvariantsGenerator.class,ConstrainedImplementor.class,UtilCreator.class,OclTestGenerator.class
})
public class OclExpressionExecution extends JavaFeature{
}
