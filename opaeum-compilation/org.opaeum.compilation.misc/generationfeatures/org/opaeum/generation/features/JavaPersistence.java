package org.opaeum.generation.features;

import org.opaeum.feature.StepDependency;
import org.opaeum.javageneration.JavaFeature;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AttributeImplementor;
import org.opaeum.javageneration.persistence.JpaAnnotator;
import org.opaeum.javageneration.persistence.PersistentObjectImplementor;
import org.opaeum.validation.namegeneration.PersistentNameGenerator;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		AttributeImplementor.class,PersistentNameGenerator.class,JpaAnnotator.class,PersistentObjectImplementor.class
})
public class JavaPersistence extends JavaFeature{
}
