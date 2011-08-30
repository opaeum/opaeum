package org.nakeduml.generation.features;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaFeature;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.hibernate.HibernateAnnotator;
import net.sf.nakeduml.javageneration.hibernate.HibernateConfigGenerator;
import net.sf.nakeduml.javageneration.hibernate.HibernatePackageAnnotator;
import net.sf.nakeduml.pomgeneration.BasicIntegratedAdaptorPomStep;
import net.sf.nakeduml.pomgeneration.HibernateDomainPomStep;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		HibernateDomainPomStep.class,HibernateConfigGenerator.class,HibernateAnnotator.class,HibernatePackageAnnotator.class,JavaPersistence.class,
		BasicIntegratedAdaptorPomStep.class
})
public class PersistenceUsingHibernate extends JavaFeature{
}
