package org.opaeum.rap;

import java.util.HashMap;

import org.opaeum.bootstrap.AbstractBootstrapStep;
import org.opaeum.bootstrap.BootstrapGenerationPhase;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.workspace.ModelWorkspace;
import org.opaeum.textmetamodel.ISourceFolderIdentifier;
import org.opaeum.textmetamodel.SourceFolder;
import org.opaeum.textmetamodel.TextSourceFolderIdentifier;

@StepDependency(phase = BootstrapGenerationPhase.class)
public class RapProjectBuilder extends AbstractBootstrapStep{
	@VisitBefore
	public void beforeWorkspace(ModelWorkspace w){
		HashMap<String,Object> vars = new HashMap<String,Object>();
		vars.put("workspace", workspace);
		vars.put("config", config);
		SourceFolder sf = getSourceFolder(config.getSourceFolderDefinition(TextSourceFolderIdentifier.WEB_PROJECT_ROOT));
		vars.put("project", sf.getParent());
		ISourceFolderIdentifier outputRootId=TextSourceFolderIdentifier.WEB_PROJECT_ROOT;
		processTemplate(workspace, "templates/Model/RapProjectXml.vsl", ".project", outputRootId, vars);
		processTemplate(workspace, "templates/Model/MANIFESTMF.vsl", "META-INF/MANIFEST.MF", outputRootId, vars);
		processTemplate(workspace, "templates/Model/RapProjectClasspath.vsl", ".classpath", outputRootId, vars);
		processTemplate(workspace, "templates/Model/RapLaunch.vsl", workspace.getName() +".launch", outputRootId, vars);
		processTemplate(workspace, "templates/Model/RapBuildProperties.vsl", "build.properties", outputRootId, vars);
	}
}
