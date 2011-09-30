package org.opeum.generation.features;

import org.opeum.feature.StepDependency;
import org.opeum.javageneration.JavaFeature;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.hibernate.HibernateAnnotator;
import org.opeum.javageneration.hibernate.HibernatePackageAnnotator;
import org.opeum.javageneration.hibernate.JpaPersistenceXmlGenerator;
import org.opeum.pomgeneration.JpaDomainPomStep;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		JpaDomainPomStep.class,HibernateAnnotator.class,HibernatePackageAnnotator.class,JavaPersistence.class,JpaPersistenceXmlGenerator.class})
public class PersistenceUsingJpa extends JavaFeature{
}
