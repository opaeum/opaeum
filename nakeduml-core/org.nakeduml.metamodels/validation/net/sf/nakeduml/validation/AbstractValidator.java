package net.sf.nakeduml.validation;

import java.util.Collection;

import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.validation.ErrorMap;
import net.sf.nakeduml.metamodel.visitor.NakedElementOwnerVisitor;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

public abstract class AbstractValidator extends NakedElementOwnerVisitor implements ITransformationStep{
	protected INakedModelWorkspace workspace;
	protected NakedUmlConfig config;
	@Override
	public Collection<? extends INakedElement> getChildren(INakedElementOwner root){
		if(root instanceof INakedModelWorkspace){
			return ((INakedModelWorkspace) root).getGeneratingModelsOrProfiles();
		}else{
			return root.getOwnedElements();
		}
	}
	public void initialize(INakedModelWorkspace workspace,NakedUmlConfig config){
		this.workspace = workspace;
		this.config = config;
	}
	protected ErrorMap getErrorMap(){
		return workspace.getErrorMap();
	}
	@Override
	protected int getThreadPoolSize(){
		//It is read-only
		return 12;
	}
}
