package net.sf.nakeduml.tests;

import java.util.Set;

import net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.javageneration.hibernate.HibernateTestsStep;
import net.sf.nakeduml.javageneration.hibernate.PersistenceUsingHibernateStep;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.pomgeneration.HibernatePomStep;

public class EntityModelCodeGenerator extends net.sf.nakeduml.pomgeneration.MavenProjectTransformationConfiguration {
	protected EntityModelCodeGenerator(String outputRoot, String modelDirectory) {
		super(outputRoot, modelDirectory);
	}

	public static void main(String[] args) throws Exception {
		new EntityModelCodeGenerator("../nakedumltest/entitymodel", "testmodels").transformSingleModel("entitymodel.uml");
	}

	@Override
	protected Set<Class<? extends TransformationStep>> getSteps() {
		return toSet(OclExpressionExecution.class, StereotypeApplicationExtractor.class/*, BusinessProcessManagementStep.class*/,
				PersistenceUsingHibernateStep.class, HibernatePomStep.class, HibernateTestsStep.class);
	}
}
