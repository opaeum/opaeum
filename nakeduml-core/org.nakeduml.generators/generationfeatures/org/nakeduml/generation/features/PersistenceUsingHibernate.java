package org.nakeduml.generation.features;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaFeature;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.hibernate.HibernateAnnotator;
import net.sf.nakeduml.javageneration.hibernate.HibernateConfigGenerator;
import net.sf.nakeduml.javageneration.hibernate.HibernatePackageAnnotator;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		HibernateConfigGenerator.class,HibernateAnnotator.class,HibernatePackageAnnotator.class,JavaPersistence.class
})
public class PersistenceUsingHibernate extends JavaFeature{
}
