package net.sf.nakeduml.detachment;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.metamodel.workspace.internal.NakedModelWorkspaceImpl;

@PhaseDependency()
public class DetachmentPhase implements TransformationPhase<NakedElementDetachor,INakedElement>{
	@InputModel(implementationClass = NakedModelWorkspaceImpl.class)
	INakedModelWorkspace modelWorkspace;
	private NakedUmlConfig config;
	public Object[] execute(List<NakedElementDetachor> detachors,TransformationContext context){
		for(INakedPackage p:new ArrayList<INakedPackage>(modelWorkspace.getGeneratingModelsOrProfiles())){
			for(NakedElementDetachor d:detachors){
				d.initialize(modelWorkspace);
				d.startVisiting(p);
			}
		}
		return new Object[]{};
	}
	public void initialize(NakedUmlConfig config){
		this.config = config;
	}
	@Override
	public Object processSingleElement(List<NakedElementDetachor> features,TransformationContext context,INakedElement element){
		return null;
	}
}
