package org.opeum.bootstrap;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.javageneration.TextSourceFolderIdentifier;
import org.opeum.metamodel.workspace.INakedModelWorkspace;
import org.opeum.pomgeneration.BasicWarPomStep;

@StepDependency(phase = BootstrapGenerationPhase.class, requires = { BasicWarPomStep.class}, before = { })
public class WarBootstrapStep extends AbstractBootstrapStep{

	@VisitBefore
	public void visitWorkspace(INakedModelWorkspace workspace) {
		createConfig("beans.xml", TextSourceFolderIdentifier.WEBAPP_RESOURCE, "WEB-INF");
		createConfig("faces-config.xml", TextSourceFolderIdentifier.WEBAPP_RESOURCE, "WEB-INF");
		createConfig("web.xml", TextSourceFolderIdentifier.WEBAPP_RESOURCE, "WEB-INF");
		createConfig("arquillian.xml", TextSourceFolderIdentifier.WEB_TEST_RESOURCE);
		createConfig("log4j.properties", TextSourceFolderIdentifier.WEB_TEST_RESOURCE);
		createConfig("jndi.properties", TextSourceFolderIdentifier.WEB_TEST_RESOURCE_JBOSSAS);
		createDefaultHtmlPages("home.xhtml");
		createDefaultHtmlPages("index.html");
		createDefaultHtmlPages("template.xhtml");
	}

}
