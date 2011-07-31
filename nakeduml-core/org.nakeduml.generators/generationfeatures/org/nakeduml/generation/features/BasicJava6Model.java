package org.nakeduml.generation.features;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaFeature;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.AttributeImplementor;
import net.sf.nakeduml.javageneration.basicjava.EnumerationLiteralImplementor;
import net.sf.nakeduml.javageneration.basicjava.HashcodeBuilder;
import net.sf.nakeduml.javageneration.basicjava.HierarchicalSourcePopulationImplementor;
import net.sf.nakeduml.javageneration.basicjava.JavaMetaInfoMapGenerator;
import net.sf.nakeduml.javageneration.basicjava.OperationAnnotator;
import net.sf.nakeduml.javageneration.basicjava.SimpleActivityMethodImplementor;
import net.sf.nakeduml.javageneration.basicjava.SpecificationImplementor;
import net.sf.nakeduml.javageneration.basicjava.ToStringBuilder;
import net.sf.nakeduml.javageneration.basicjava.ToXmlStringBuilder;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		OperationAnnotator.class,AttributeImplementor.class,ToXmlStringBuilder.class,ToStringBuilder.class,EnumerationLiteralImplementor.class,
		SimpleActivityMethodImplementor.class,HierarchicalSourcePopulationImplementor.class,HashcodeBuilder.class,JavaMetaInfoMapGenerator.class,
		SpecificationImplementor.class
})
public class BasicJava6Model extends JavaFeature{
}
