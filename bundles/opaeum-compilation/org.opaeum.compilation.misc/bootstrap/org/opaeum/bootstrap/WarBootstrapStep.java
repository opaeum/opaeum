package org.opaeum.bootstrap;

import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.pomgeneration.BasicWarPomStep;
import org.opaeum.textmetamodel.TextSourceFolderIdentifier;

@StepDependency(phase = BootstrapGenerationPhase.class, requires = { BasicWarPomStep.class}, before = { })
public class WarBootstrapStep extends AbstractBootstrapStep{

	@VisitBefore
	public void visitWorkspace(EmfWorkspace workspace) {
		createConfig("beans.xml", TextSourceFolderIdentifier.WEBAPP_RESOURCE, "WEB-INF");
		createConfig("faces-config.xml", TextSourceFolderIdentifier.WEBAPP_RESOURCE, "WEB-INF");
		createConfig("web.xml", TextSourceFolderIdentifier.WEBAPP_RESOURCE, "WEB-INF");
		createDefaultHtmlPages("home.xhtml");
		createDefaultHtmlPages("index.html");
		createDefaultHtmlPages("template.xhtml");
	}

}
