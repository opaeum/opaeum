package org.opaeum.generation.features;

import org.opaeum.feature.StepDependency;
import org.opaeum.javageneration.JavaFeature;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AttributeImplementor;
import org.opaeum.javageneration.basicjava.DerivedUnionImplementor;
import org.opaeum.javageneration.basicjava.EnumerationLiteralImplementor;
import org.opaeum.javageneration.basicjava.HashcodeBuilder;
import org.opaeum.javageneration.basicjava.JavaMetaInfoMapGenerator;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.basicjava.RedefinitionImplementor;
import org.opaeum.javageneration.basicjava.SimpleActivityMethodImplementor;
import org.opaeum.javageneration.basicjava.SpecificationImplementor;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		OperationAnnotator.class,AttributeImplementor.class,EnumerationLiteralImplementor.class,
		SimpleActivityMethodImplementor.class,HashcodeBuilder.class,JavaMetaInfoMapGenerator.class,
		SpecificationImplementor.class,RedefinitionImplementor.class,DerivedUnionImplementor.class
})
public class BasicJava6Model extends JavaFeature{
}
