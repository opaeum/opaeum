package org.opaeum.rap;

import java.io.CharArrayWriter;
import java.util.HashMap;

import org.opaeum.bootstrap.AbstractBootstrapStep;
import org.opaeum.bootstrap.BootstrapGenerationPhase;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.workspace.ModelWorkspace;
import org.opaeum.textmetamodel.ISourceFolderIdentifier;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opaeum.textmetamodel.SourceFolder;
import org.opaeum.textmetamodel.TextSourceFolderIdentifier;

@StepDependency(phase = BootstrapGenerationPhase.class)
public class RapProjectBuilder extends AbstractBootstrapStep{
	@VisitBefore
	public void beforeWorkspace(ModelWorkspace w){
		if(transformationContext.isIntegrationPhase()){
			HashMap<String,Object> vars = new HashMap<String,Object>();
			vars.put("workspace", workspace);
			vars.put("config", config);
			vars.put("activator", ojUtil.utilClass(workspace, "Activator"));
			String po = config.getProjectNameOverride();
			if(po != null){
				vars.put("projectName", po);
			}else{
				vars.put("projectName", config.getMavenGroupId() + ".app");
			}
			SourceFolder sf = getSourceFolder(config.getSourceFolderDefinition(TextSourceFolderIdentifier.WEB_PROJECT_ROOT));
			vars.put("project", sf.getParent());
			ISourceFolderIdentifier outputRootId = TextSourceFolderIdentifier.WEB_PROJECT_ROOT;
			CharArrayWriter caw = new CharArrayWriter();
			caw.append("markerfile");
			findOrCreateTextFile(caw, JavaSourceFolderIdentifier.DOMAIN_SRC, "put.custom.src.here");
			findOrCreateTextFile(caw, JavaSourceFolderIdentifier.DOMAIN_TEST_SRC, "put.custom.src.here");
			processTemplate(workspace, "templates/Model/RapProjectXml.vsl", ".project", outputRootId, vars);
			if(config.isJpa2()){
				processTemplate(workspace, "templates/Model/MANIFESTMFJPA2.vsl", "META-INF/MANIFEST.MF", outputRootId, vars);
			}else{
				processTemplate(workspace, "templates/Model/MANIFESTMFJPA1.vsl", "META-INF/MANIFEST.MF", outputRootId, vars);
			}
			processTemplate(workspace, "templates/Model/RapProjectClasspath.vsl", ".classpath", outputRootId, vars);
			processTemplate(workspace, "templates/Model/RapLaunch.vsl", workspace.getName() + ".launch", outputRootId, vars);
			processTemplate(workspace, "templates/Model/RapBuildProperties.vsl", "build.properties", outputRootId, vars);
		}
	}
}
