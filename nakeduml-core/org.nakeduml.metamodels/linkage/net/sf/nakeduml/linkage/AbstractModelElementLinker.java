package net.sf.nakeduml.linkage;

import java.util.Collection;
import java.util.HashSet;

import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.validation.ErrorMap;
import net.sf.nakeduml.metamodel.visitor.NakedElementOwnerVisitor;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;

public abstract class AbstractModelElementLinker extends NakedElementOwnerVisitor implements ITransformationStep{
	@InputModel
	protected INakedModelWorkspace workspace;
	protected NakedUmlConfig config;
	private Collection<INakedElement> affectedElements;
	public void initialize(INakedModelWorkspace workspace,NakedUmlConfig config){
		affectedElements = new HashSet<INakedElement>();
		this.workspace = workspace;
		this.config = config;
	}
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}

	protected NakedUmlLibrary getBuiltInTypes(){
		return workspace.getNakedUmlLibrary();
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