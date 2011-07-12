package net.sf.nakeduml.uigeneration;

import java.util.Collection;

import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

public class GeneratingUserInteractionTransformationStep extends AbstractUimTransformationStep{
	@Override
	public Collection<? extends INakedElementOwner> getChildren(INakedElementOwner root){
		if(root instanceof INakedModelWorkspace){
			return ((INakedModelWorkspace) root).getGeneratingModelsOrProfiles();
		}else{
			return super.getChildren(root);
		}
	}
}
