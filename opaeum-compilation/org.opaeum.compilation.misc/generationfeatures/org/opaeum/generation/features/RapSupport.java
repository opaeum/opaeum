package org.opaeum.generation.features;

import org.opaeum.feature.StepDependency;
import org.opaeum.javageneration.JavaFeature;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.composition.FactoryMethodCreator;
import org.opaeum.linkage.QualifierLogicCalculator;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		FactoryMethodCreator.class, QualifierLogicCalculator.class})
public class RapSupport extends JavaFeature{
}
