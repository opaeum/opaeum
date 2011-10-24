package org.opaeum.linkage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.InputModel;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.metamodel.core.INakedAssociation;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedRootObject;
import org.opaeum.metamodel.validation.ErrorMap;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.metamodel.workspace.OpaeumLibrary;
import org.opaeum.visitor.NakedElementOwnerVisitor;

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
	@Override
	public Collection<INakedElementOwner> getChildren(INakedElementOwner root){
		Collection<INakedElementOwner> children = new ArrayList<INakedElementOwner>(super.getChildren(root));
		if(root instanceof INakedAssociation){
			//TODO fix the containment relationship of ownedEnds
			List<INakedProperty> ends = ((INakedAssociation) root).getEnds();
			for(INakedProperty end:ends){
				if(end!=null && end.getRootObject()!=((INakedAssociation) root).getRootObject()){
					children.add(end);
				}
			}
		}
		return children;
	}
	protected boolean shouldBeLinked(INakedElementOwner o){
		boolean shouldIgnoreBecauseItIsDeleted = o instanceof INakedElement && ((INakedElement) o).isMarkedForDeletion() && ignoreDeletedElements();
		// Link nonGeneratingRootObjects once only, since they would typically not be editable from the editor
		boolean isLinkedNonGeneratingObject = false;
		if(o instanceof INakedRootObject){
			isLinkedNonGeneratingObject = !workspace.getGeneratingModelsOrProfiles().contains(o) && ((INakedRootObject) o).getStatus().isLinked();
		}
		return !(shouldIgnoreBecauseItIsDeleted || isLinkedNonGeneratingObject);
	}
	protected boolean ignoreDeletedElements(){
		return true;
	}
}