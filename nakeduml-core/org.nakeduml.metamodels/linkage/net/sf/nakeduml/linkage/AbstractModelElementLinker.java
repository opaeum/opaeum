package net.sf.nakeduml.linkage;

import java.util.Collection;
import java.util.HashSet;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.ITransformationStep;
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
	protected NakedUmlLibrary getBuiltInTypes(){
		return workspace.getNakedUmlLibrary();
	}
	protected ErrorMap getErrorMap(){
		return workspace.getErrorMap();
	}
	public Collection<INakedElement> getAffectedElements(){
		return this.affectedElements;
	}
	@Override
	public void visitRecursively(INakedElementOwner o){
		if(!(o instanceof INakedElement && ((INakedElement) o).isMarkedForDeletion() && ignoreDeletedElements())){
			if(o instanceof INakedRootObject && workspace.getGeneratingModelsOrProfiles().contains(o)){
			}
			super.visitRecursively(o);
		}
	}
	protected boolean ignoreDeletedElements(){
		return true;
	}
}