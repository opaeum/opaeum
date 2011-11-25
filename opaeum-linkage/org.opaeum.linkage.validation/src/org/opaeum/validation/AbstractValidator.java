package org.opaeum.validation;

import java.util.Collection;

import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedRootObject;
import org.opaeum.metamodel.validation.ErrorMap;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.visitor.NakedElementOwnerVisitor;

public abstract class AbstractValidator extends NakedElementOwnerVisitor implements ITransformationStep{
	@Override
	public void visitRecursively(INakedElementOwner o){
		if(!(o instanceof INakedElement && ((INakedElement) o).isMarkedForDeletion() || (o instanceof INakedRootObject && ((INakedRootObject) o).getStatus().isValidated()))){
			super.visitRecursively(o);
		}
	}
	protected INakedModelWorkspace workspace;
	protected OpaeumConfig config;
	@Override
	public Collection<? extends INakedElement> getChildren(INakedElementOwner root){
		if(root instanceof INakedModelWorkspace){
			return ((INakedModelWorkspace) root).getPrimaryRootObjects();
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
		// It is read-only
		return 12;
	}
	public void release(){
		super.release();
		this.workspace = null;
	}
}
