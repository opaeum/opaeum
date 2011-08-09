package org.nakeduml.bootstrap;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.filegeneration.TextFileGenerator;
import net.sf.nakeduml.javageneration.TextSourceFolderIdentifier;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.pomgeneration.AdaptorArquillianPomStep;

@StepDependency(phase = BootstrapGenerationPhase.class, requires = { AdaptorArquillianPomStep.class}, before = {})
public class AdaptorArquillianBootstrapStep extends AbstractBootstrapStep {
	@VisitBefore
	public void visitModel(INakedModel model) {
		createConfig("beans.xml", TextSourceFolderIdentifier.ADAPTOR_RESOURCE, "META-INF");
		createConfig("arquillian.xml", TextSourceFolderIdentifier.ADAPTOR_TEST_RESOURCE);
		createConfig("log4j.properties", TextSourceFolderIdentifier.ADAPTOR_TEST_RESOURCE);
		createConfig("jndi.properties", TextSourceFolderIdentifier.ADAPTOR_TEST_RESOURCE_JBOSSAS);
	}
}
