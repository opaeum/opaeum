package org.nakeduml.projectgeneration;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.filegeneration.TextFileGenerator;
import net.sf.nakeduml.javageneration.CharArrayTextSource;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.pomgeneration.WarPomStep;

@StepDependency(phase = DefaultConfigGenerationPhase.class, requires = { WarPomStep.class, TextFileGenerator.class }, before = { TextFileGenerator.class })
public class AdaptorJbossConfigStep extends AbstractProjectGenerationStep {
	@VisitBefore
	public void visitModel(INakedModel model) {
		createConfig("beans.xml", CharArrayTextSource.OutputRootId.ADAPTOR_RESOURCE, "META-INF");
		createConfig("arquillian.xml", CharArrayTextSource.OutputRootId.ADAPTOR_TEST_RESOURCE);
		createConfig("log4j.properties", CharArrayTextSource.OutputRootId.ADAPTOR_TEST_RESOURCE);
		createConfig("hornetq-jms.xml", CharArrayTextSource.OutputRootId.ADAPTOR_TEST_RESOURCE_JBOSSAS);
		createConfig("jndi.properties", CharArrayTextSource.OutputRootId.ADAPTOR_TEST_RESOURCE_JBOSSAS);
	}
}
