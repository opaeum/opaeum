package org.opaeum.linkage;

import java.util.Collection;
import java.util.HashSet;

import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.InputModel;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedRootObject;
import org.opaeum.metamodel.validation.ErrorMap;
import org.opaeum.metamodel.visitor.NakedElementOwnerVisitor;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public abstract class AbstractModelElementLinker extends NakedElementOwnerVisitor implements ITransformationStep{
	@InputModel
	protected INakedModelWorkspace workspace;
	protected OpaeumConfig config;
	private Collection<INakedElement> affectedElements;
	public void initialize(INakedModelWorkspace workspace,OpaeumConfig config){
		affectedElements = new HashSet<INakedElement>();
		this.workspace = workspace;
		this.config = config;
	}
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}

	protected OpaeumLibrary getBuiltInTypes(){
		return workspace.getOpaeumLibrary();
	}
	protected ErrorMap getErrorMap(){
		return workspace.getErrorMap();
	}
	protected synchronized void addAffectedElement(INakedElement a){
		affectedElements.add(a);
	}
	Collection<INakedElement> getAffectedElements(){
		return this.affectedElements;
	}
	public void setCurrentRootObject(INakedRootObject a){
		super.setCurrentRootObject(a);
	}
	@Override
	public void visitRecursively(INakedElementOwner o){
		if(shouldBeLinked(o)){
			super.visitRecursively(o);
		}
	}
	protected boolean shouldBeLinked(INakedElementOwner o){
		boolean shouldIgnoreBecauseItIsDeleted = o instanceof INakedElement && ((INakedElement) o).isMarkedForDeletion() && ignoreDeletedElements();
		//Link nonGeneratingRootObjects once only, since they would typically not be editable from the editor
		boolean isLinkedNonGeneratingObject =  false;
		if(o instanceof INakedRootObject){
			isLinkedNonGeneratingObject =!workspace.getGeneratingModelsOrProfiles().contains(o) && ((INakedRootObject)o).getStatus().isLinked();
		}
		return !(shouldIgnoreBecauseItIsDeleted || isLinkedNonGeneratingObject);
	}
	protected boolean ignoreDeletedElements(){
		return true;
	}
}