package org.opeum.linkage;

import java.util.Collection;
import java.util.HashSet;

import org.opeum.feature.ITransformationStep;
import org.opeum.feature.InputModel;
import org.opeum.feature.OpeumConfig;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedElementOwner;
import org.opeum.metamodel.core.INakedRootObject;
import org.opeum.metamodel.validation.ErrorMap;
import org.opeum.metamodel.visitor.NakedElementOwnerVisitor;
import org.opeum.metamodel.workspace.INakedModelWorkspace;
import org.opeum.metamodel.workspace.OpeumLibrary;

public abstract class AbstractModelElementLinker extends NakedElementOwnerVisitor implements ITransformationStep{
	@InputModel
	protected INakedModelWorkspace workspace;
	protected OpeumConfig config;
	private Collection<INakedElement> affectedElements;
	public void initialize(INakedModelWorkspace workspace,OpeumConfig config){
		affectedElements = new HashSet<INakedElement>();
		this.workspace = workspace;
		this.config = config;
	}
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}

	protected OpeumLibrary getBuiltInTypes(){
		return workspace.getOpeumLibrary();
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