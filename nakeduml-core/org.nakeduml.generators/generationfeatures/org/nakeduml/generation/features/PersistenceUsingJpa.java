package org.nakeduml.generation.features;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaFeature;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.hibernate.HibernateAnnotator;
import net.sf.nakeduml.javageneration.hibernate.HibernatePackageAnnotator;
import net.sf.nakeduml.pomgeneration.JpaDomainPomStep;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		JpaDomainPomStep.class,HibernateAnnotator.class,HibernatePackageAnnotator.class,JavaPersistence.class})
public class PersistenceUsingJpa extends JavaFeature{
}
