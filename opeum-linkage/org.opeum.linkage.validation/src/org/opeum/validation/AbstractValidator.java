package org.opeum.validation;

import java.util.Collection;

import org.opeum.feature.ITransformationStep;
import org.opeum.feature.OpeumConfig;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedElementOwner;
import org.opeum.metamodel.validation.ErrorMap;
import org.opeum.metamodel.visitor.NakedElementOwnerVisitor;
import org.opeum.metamodel.workspace.INakedModelWorkspace;

public abstract class AbstractValidator extends NakedElementOwnerVisitor implements ITransformationStep{
	protected INakedModelWorkspace workspace;
	protected OpeumConfig config;
	@Override
	public Collection<? extends INakedElement> getChildren(INakedElementOwner root){
		if(root instanceof INakedModelWorkspace){
			return ((INakedModelWorkspace) root).getGeneratingModelsOrProfiles();
		}else{
			return root.getOwnedElements();
		}
	}
	public void initialize(INakedModelWorkspace workspace,OpeumConfig config){
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
