package org.nakeduml.generation.features;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaFeature;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.oclexpressions.AttributeExpressionGenerator;
import net.sf.nakeduml.javageneration.oclexpressions.ConstrainedImplementor;
import net.sf.nakeduml.javageneration.oclexpressions.InvariantsGenerator;
import net.sf.nakeduml.javageneration.oclexpressions.OclTestGenerator;
import net.sf.nakeduml.javageneration.oclexpressions.PreAndPostConditionGenerator;
import net.sf.nakeduml.javageneration.oclexpressions.UtilCreator;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		AttributeExpressionGenerator.class,PreAndPostConditionGenerator.class,InvariantsGenerator.class,ConstrainedImplementor.class,UtilCreator.class,OclTestGenerator.class
})
public class OclExpressionExecution extends JavaFeature{
}
