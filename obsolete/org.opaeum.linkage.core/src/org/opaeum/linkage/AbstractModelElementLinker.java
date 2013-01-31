package org.opaeum.linkage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.uml2.uml.INakedAssociation;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedElementOwner;
import org.eclipse.uml2.uml.INakedProperty;
import org.eclipse.uml2.uml.INakedRootObject;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.InputModel;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.metamodel.validation.ErrorMap;
import org.opaeum.metamodel.workspace.IPropertyEmulation;
import org.opaeum.metamodel.workspace.ModelWorkspace;
import org.opaeum.visitor.NakedElementOwnerVisitor;

public abstract class AbstractModelElementLinker extends NakedElementOwnerVisitor implements ITransformationStep{
	@InputModel
	protected ModelWorkspace workspace;
	protected OpaeumConfig config;
	private Collection<INakedElement> affectedElements;
	public void initialize(ModelWorkspace workspace,OpaeumConfig config){
		affectedElements = new HashSet<INakedElement>();
		this.workspace = workspace;
		this.config = config;
	}
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
	protected IPropertyEmulation getLibrary(){
		return workspace.getOpaeumLibrary();
	}
	protected ErrorMap getErrorMap(){
		return workspace.getErrorMap();
	}
	protected synchronized void addAffectedElement(INakedElement a){
		affectedElements.add(a);
	}
	Collection<INakedElement> getAffectedElements(){
		if(affectedElements==null){
			return Collections.emptySet();
		}
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
			List<INakedRootObject> generatingModelsOrProfiles = workspace.getGeneratingModelsOrProfiles();
			boolean isPrimaryModel = generatingModelsOrProfiles.size()==1 && generatingModelsOrProfiles.contains(o);
			isLinkedNonGeneratingObject = !isPrimaryModel && ((INakedRootObject) o).getStatus().isLinked();
		}
		boolean result= !(shouldIgnoreBecauseItIsDeleted || isLinkedNonGeneratingObject);
		return result;
	}
	protected boolean ignoreDeletedElements(){
		return true;
	}
	public void release(){
		this.affectedElements=null;
		this.workspace=null;
		
	}
}