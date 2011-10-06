package org.opeum.generation.features;

import org.opeum.feature.StepDependency;
import org.opeum.javageneration.JavaFeature;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.basicjava.AttributeImplementor;
import org.opeum.javageneration.persistence.JpaAnnotator;
import org.opeum.javageneration.persistence.PersistentObjectImplementor;
import org.opeum.validation.namegeneration.PersistentNameGenerator;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		AttributeImplementor.class,PersistentNameGenerator.class,JpaAnnotator.class,PersistentObjectImplementor.class
})
public class JavaPersistence extends JavaFeature{
}
