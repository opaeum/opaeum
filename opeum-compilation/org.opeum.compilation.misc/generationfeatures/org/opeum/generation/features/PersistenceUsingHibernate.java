package org.opeum.generation.features;

import org.opeum.feature.StepDependency;
import org.opeum.javageneration.JavaFeature;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.hibernate.HibernateAnnotator;
import org.opeum.javageneration.hibernate.HibernateConfigGenerator;
import org.opeum.javageneration.hibernate.HibernatePackageAnnotator;
import org.opeum.pomgeneration.BasicIntegratedAdaptorPomStep;
import org.opeum.pomgeneration.HibernateDomainPomStep;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		HibernateDomainPomStep.class,HibernateConfigGenerator.class,HibernateAnnotator.class,HibernatePackageAnnotator.class,JavaPersistence.class,
		BasicIntegratedAdaptorPomStep.class
})
public class PersistenceUsingHibernate extends JavaFeature{
}
