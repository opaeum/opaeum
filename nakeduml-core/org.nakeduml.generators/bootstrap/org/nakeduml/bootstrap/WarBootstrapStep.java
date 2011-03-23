package org.nakeduml.bootstrap;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.filegeneration.TextFileGenerator;
import net.sf.nakeduml.javageneration.CharArrayTextSource;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.pomgeneration.WarPomStep;

@StepDependency(phase = BootstrapGenerationPhase.class, requires = { WarPomStep.class,
		TextFileGenerator.class }, before = { })
public class WarBootstrapStep extends AbstractBootstrapStep{

	@VisitBefore
	public void visitWorkspace(INakedModelWorkspace workspace) {
		createConfig("beans.xml", CharArrayTextSource.OutputRootId.WEBAPP_RESOURCE, "WEB-INF");
		createConfig("faces-config.xml", CharArrayTextSource.OutputRootId.WEBAPP_RESOURCE, "WEB-INF");
		createConfig("web.xml", CharArrayTextSource.OutputRootId.WEBAPP_RESOURCE, "WEB-INF");
		createConfig("arquillian.xml", CharArrayTextSource.OutputRootId.WEB_TEST_RESOURCE);
		createConfig("log4j.properties", CharArrayTextSource.OutputRootId.WEB_TEST_RESOURCE);
		createConfig("hornetq-jms.xml", CharArrayTextSource.OutputRootId.WEB_TEST_RESOURCE_JBOSSAS);
		createConfig("jndi.properties", CharArrayTextSource.OutputRootId.WEB_TEST_RESOURCE_JBOSSAS);
		createDefaultHtmlPages("home.xhtml");
		createDefaultHtmlPages("index.html");
		createDefaultHtmlPages("template.xhtml");
	}

}
