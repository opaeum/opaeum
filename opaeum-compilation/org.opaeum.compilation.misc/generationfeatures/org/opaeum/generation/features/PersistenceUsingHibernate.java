package org.opaeum.generation.features;

import org.opaeum.feature.StepDependency;
import org.opaeum.javageneration.JavaFeature;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.hibernate.HibernateAnnotator;
import org.opaeum.javageneration.hibernate.HibernateAttributeImplementor;
import org.opaeum.javageneration.hibernate.HibernateConfigGenerator;
import org.opaeum.javageneration.hibernate.HibernatePackageAnnotator;
import org.opaeum.pomgeneration.BasicIntegratedAdaptorPomStep;
import org.opaeum.pomgeneration.HibernateDomainPomStep;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		HibernateDomainPomStep.class,HibernateConfigGenerator.class,HibernateAnnotator.class,HibernatePackageAnnotator.class,JavaPersistence.class,
		BasicIntegratedAdaptorPomStep.class
})
public class PersistenceUsingHibernate extends JavaFeature{
}
