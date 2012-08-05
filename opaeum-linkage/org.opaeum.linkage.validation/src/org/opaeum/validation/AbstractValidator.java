package org.opaeum.validation;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.uml2.uml.Element;
import org.opaeum.EmfElementVisitor;
import org.opaeum.eclipse.EmfElementUtil;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.metamodel.validation.ErrorMap;
import org.opaeum.metamodel.workspace.ModelWorkspace;

public abstract class AbstractValidator extends EmfElementVisitor implements ITransformationStep{
	@Override
	public void visitRecursively(Element o){
		if(!(EmfElementUtil.isMarkedForDeletion(o) || (EmfPackageUtil.isRootObject(o ) && workspace.getGeneratingModelsOrProfiles().contains(o)))){
			super.visitRecursively(o);
		}
	}
	protected ModelWorkspace workspace;
	protected OpaeumConfig config;
	@Override
	public Collection<Element> getChildren(Element root){
		if(root instanceof ModelWorkspace){
			return new ArrayList<Element>(((ModelWorkspace) root).getPrimaryRootObjects());
		}else{
			return root.getOwnedElements();
		}
	}
	public void initialize(ModelWorkspace workspace,OpaeumConfig config){
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
		this.workspace = null;
	}
}
