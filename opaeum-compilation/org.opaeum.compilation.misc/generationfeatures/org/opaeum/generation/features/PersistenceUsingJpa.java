package org.opaeum.generation.features;

import org.opaeum.feature.StepDependency;
import org.opaeum.javageneration.JavaFeature;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.hibernate.HibernateAnnotator;
import org.opaeum.javageneration.hibernate.HibernateAttributeImplementor;
import org.opaeum.javageneration.hibernate.HibernatePackageAnnotator;
import org.opaeum.javageneration.hibernate.JpaPersistenceXmlGenerator;
import org.opaeum.pomgeneration.JpaDomainPomStep;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		JpaDomainPomStep.class,HibernateAnnotator.class,HibernatePackageAnnotator.class,JavaPersistence.class,JpaPersistenceXmlGenerator.class,HibernateAttributeImplementor.class})
public class PersistenceUsingJpa extends JavaFeature{
}
