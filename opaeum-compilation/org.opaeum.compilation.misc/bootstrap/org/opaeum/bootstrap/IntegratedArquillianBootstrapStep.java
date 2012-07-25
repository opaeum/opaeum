package org.opaeum.bootstrap;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.workspace.ModelWorkspace;
import org.opaeum.pomgeneration.IntegratedArquillianPomStep;
import org.opaeum.textmetamodel.TextSourceFolderIdentifier;

@StepDependency(phase = BootstrapGenerationPhase.class, requires = { IntegratedArquillianPomStep.class}, before = { })
public class IntegratedArquillianBootstrapStep extends AbstractBootstrapStep {
	@VisitBefore
	public void visitWorkspace(ModelWorkspace workspace) {
		createConfig("beans.xml", TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_RESOURCE, "META-INF");
		createConfig("arquillian.xml", TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_TEST_RESOURCE);
		createConfig("log4j.properties", TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_TEST_RESOURCE);
		createConfig("jndi.properties", TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_TEST_RESOURCE_JBOSSAS);
	}
}
