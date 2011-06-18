package org.nakeduml.uml2uim;

import java.io.File;
import java.util.List;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nakeduml.uim.folder.UserInteractionModel;
import org.nakeduml.uim.util.UmlUimLinks;

@PhaseDependency()
public class UserInteractionSynchronizationPhase implements TransformationPhase<AbstractUimSynchronizer>{
	@InputModel
	EmfWorkspace workspace;
	@Override
	public void initialize(NakedUmlConfig config){
	}
	@Override
	public Object[] execute(List<AbstractUimSynchronizer> features,TransformationContext context){
		ResourceSet resourceSet = workspace.getResourceSet();
		File dir;
		if(workspace.getDirectoryUri().isPlatform()){
			dir = new File(workspace.getDirectoryUri().toPlatformString(true));
		}else{
			dir = new File(workspace.getDirectoryUri().toFileString());
		}
		for(File file:dir.listFiles()){
			if(file.getName().endsWith(".uim")){
				resourceSet.getResource(URI.createFileURI(file.getAbsolutePath()), true);
			}
		}
		UserInteractionModel model = null;
		UmlUimLinks.init(resourceSet);
		for(AbstractUimSynchronizer s:features){
			s.init(resourceSet, false);
			s.startVisiting(workspace);
		}
		return new Object[]{
			model
		};
	}
}
