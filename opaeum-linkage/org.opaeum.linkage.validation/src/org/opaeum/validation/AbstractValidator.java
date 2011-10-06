package org.opaeum.validation;

import java.util.Collection;

import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.validation.ErrorMap;
import org.opaeum.metamodel.visitor.NakedElementOwnerVisitor;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;

public abstract class AbstractValidator extends NakedElementOwnerVisitor implements ITransformationStep{
	protected INakedModelWorkspace workspace;
	protected OpaeumConfig config;
	@Override
	public Collection<? extends INakedElement> getChildren(INakedElementOwner root){
		if(root instanceof INakedModelWorkspace){
			return ((INakedModelWorkspace) root).getGeneratingModelsOrProfiles();
		}else{
			return root.getOwnedElements();
		}
	}
	public void initialize(INakedModelWorkspace workspace,OpaeumConfig config){
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
