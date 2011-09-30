package org.opeum.generation.features;

import org.opeum.feature.StepDependency;
import org.opeum.javageneration.JavaFeature;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.basicjava.AttributeImplementor;
import org.opeum.javageneration.basicjava.DerivedUnionImplementor;
import org.opeum.javageneration.basicjava.EnumerationLiteralImplementor;
import org.opeum.javageneration.basicjava.HashcodeBuilder;
import org.opeum.javageneration.basicjava.JavaMetaInfoMapGenerator;
import org.opeum.javageneration.basicjava.OperationAnnotator;
import org.opeum.javageneration.basicjava.RedefinitionImplementor;
import org.opeum.javageneration.basicjava.SimpleActivityMethodImplementor;
import org.opeum.javageneration.basicjava.SpecificationImplementor;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		OperationAnnotator.class,AttributeImplementor.class,EnumerationLiteralImplementor.class,
		SimpleActivityMethodImplementor.class,HashcodeBuilder.class,JavaMetaInfoMapGenerator.class,
		SpecificationImplementor.class,RedefinitionImplementor.class,DerivedUnionImplementor.class
})
public class BasicJava6Model extends JavaFeature{
}
