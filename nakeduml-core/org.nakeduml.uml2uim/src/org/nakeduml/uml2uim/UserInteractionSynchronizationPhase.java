package org.nakeduml.uml2uim;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.nakeduml.uim.UserInteractionModel;
import org.topcased.modeler.diagrams.model.Diagrams;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
@PhaseDependency()
public class UserInteractionSynchronizationPhase implements TransformationPhase<AbstractUimSynchronizer>{
	@InputModel
	EmfWorkspace workspace;
	@Override
	public void initialize(NakedUmlConfig config){
	}

	@Override
	public Object[] execute(List<AbstractUimSynchronizer> features,TransformationContext context){
		for(Resource r:resources){
		}
		Diagrams diagrams=null;
		UserInteractionModel model=null;
		for(AbstractUimSynchronizer s:features){
			s.init(model, diagrams, false);
			s.startVisiting(workspace);
		}
		return new Object[]{diagrams,model};
	}
}
