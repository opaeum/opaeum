package org.nakeduml.generation.features;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaFeature;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.AttributeImplementor;
import net.sf.nakeduml.javageneration.persistence.JpaAnnotator;
import net.sf.nakeduml.javageneration.persistence.PersistentObjectImplementor;
import net.sf.nakeduml.validation.namegeneration.PersistentNameGenerator;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		AttributeImplementor.class,PersistentNameGenerator.class,JpaAnnotator.class,PersistentObjectImplementor.class
})
public class JavaPersistence extends JavaFeature{
}
