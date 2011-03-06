package net.sf.nakeduml.javageneration.hibernate;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.javageneration.persistence.PersistenceStep;
import net.sf.nakeduml.linkage.InverseCalculator;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.pomgeneration.HibernatePomStep;
import net.sf.nakeduml.validation.namegeneration.PersistentNameGenerator;

@StepDependency(phase = JavaTransformationPhase.class, requires = { PersistenceStep.class, InverseCalculator.class,
		PersistentNameGenerator.class, HibernatePomStep.class }, after = { OclExpressionExecution.class, PersistenceStep.class }, before = {})
public class PersistenceUsingHibernateStep extends AbstractJavaTransformationStep {
	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		HibernateAnnotator ha = new HibernateAnnotator();
		ha.initialize(javaModel, config, textWorkspace, context);
		ha.startVisiting(workspace);
		HibernatePackageAnnotator mda = new HibernatePackageAnnotator(false);
		mda.initialize(javaModel, config, textWorkspace, context);
		mda.startVisiting(workspace);
		HibernateConfigGenerator hcg = new HibernateConfigGenerator(false);
		hcg.initialize(config, textWorkspace, context);
		hcg.startVisiting(workspace);
		//
		// OneToOneOptimiser otoo=new OneToOneOptimiser();
		// otoo.initialize(workspace, javaModel, config, textWorkspace);
		// otoo.startVisiting(workspace);
		// EnumerationLiteralNameAdder asdf = new
		// EnumerationLiteralNameAdder(javaModel, config);
		// asdf.startVisiting(workspace);
		// TooManyNavigationSupport tns=new TooManyNavigationSupport();
		// tns.initialize(workspace, javaModel, config, textWorkspace);
		// tns.startVisiting(workspace);
	}
}
