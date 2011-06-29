package org.nakeduml.bootstrap;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.filegeneration.TextFileGenerator;
import net.sf.nakeduml.javageneration.CharArrayTextSource;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.pomgeneration.IntegratedArquillianPomStep;

@StepDependency(phase = BootstrapGenerationPhase.class, requires = { IntegratedArquillianPomStep.class, TextFileGenerator.class }, before = { })
public class IntegratedArquillianBootstrapStep extends AbstractBootstrapStep {
	@VisitBefore
	public void visitWorkspace(INakedModelWorkspace workspace) {
		createConfig("beans.xml", CharArrayTextSource.OutputRootId.INTEGRATED_ADAPTOR_RESOURCE, "META-INF");
		createConfig("arquillian.xml", CharArrayTextSource.OutputRootId.INTEGRATED_ADAPTOR_TEST_RESOURCE);
		createConfig("log4j.properties", CharArrayTextSource.OutputRootId.INTEGRATED_ADAPTOR_TEST_RESOURCE);
		createConfig("jndi.properties", CharArrayTextSource.OutputRootId.INTEGRATED_ADAPTOR_TEST_RESOURCE_JBOSSAS);
	}
}
