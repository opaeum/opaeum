package org.nakeduml.uml2uim;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.nakeduml.uim.util.UmlUimLinks;

@PhaseDependency()
public class UimSynchronizationPhase implements TransformationPhase<AbstractUimSynchronizer>{
	@InputModel
	EmfWorkspace workspace;
	private NakedUmlConfig config;
	@Override
	public void initialize(NakedUmlConfig config){
		this.config=config;
	}
	@Override
	public Object[] execute(List<AbstractUimSynchronizer> features,TransformationContext context){
		ResourceSet resourceSet = new ResourceSetImpl();
		UmlUimLinks.associate(resourceSet, workspace.getUmlElementMap());
		for(AbstractUimSynchronizer s:features){
			s.init(workspace, resourceSet,false);
			s.startVisiting(workspace);
		}
		try{
			save(workspace.getDirectoryUri(),resourceSet);
		}catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Object[]{
			workspace
		};
	}
	public static void save(URI rootDir,ResourceSet r) throws IOException{
		String rootString = toString(rootDir);
		for(Resource resource:r.getResources()){
			String string = toString(resource.getURI());
			if(string != null && string.startsWith(rootString)){
				resource.save(new HashMap());
			}
		}
	}
	private static String toString(URI rootDir){
		return rootDir.isFile() ? rootDir.toFileString() : rootDir.toPlatformString(true);
	}


}
