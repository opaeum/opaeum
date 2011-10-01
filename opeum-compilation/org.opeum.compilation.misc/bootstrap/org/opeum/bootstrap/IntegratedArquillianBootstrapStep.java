package org.opeum.bootstrap;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.metamodel.workspace.INakedModelWorkspace;
import org.opeum.pomgeneration.IntegratedArquillianPomStep;
import org.opeum.textmetamodel.TextSourceFolderIdentifier;

@StepDependency(phase = BootstrapGenerationPhase.class, requires = { IntegratedArquillianPomStep.class}, before = { })
public class IntegratedArquillianBootstrapStep extends AbstractBootstrapStep {
	@VisitBefore
	public void visitWorkspace(INakedModelWorkspace workspace) {
		createConfig("beans.xml", TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_RESOURCE, "META-INF");
		createConfig("arquillian.xml", TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_TEST_RESOURCE);
		createConfig("log4j.properties", TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_TEST_RESOURCE);
		createConfig("jndi.properties", TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_TEST_RESOURCE_JBOSSAS);
	}
}
