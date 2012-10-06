package org.opaeum.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Profile;
import org.opaeum.EmfElementVisitor;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfElementUtil;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.TransformationContext;
import org.opaeum.feature.visit.VisitSpec;
import org.opaeum.metamodel.validation.ErrorMap;
import org.opaeum.metamodel.workspace.IPropertyEmulation;
import org.opaeum.metamodel.workspace.ModelWorkspace;
import org.opaeum.metamodel.workspace.OpaeumLib;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public abstract class AbstractValidator extends EmfElementVisitor implements ITransformationStep{
	protected ModelWorkspace workspace;
	protected OpaeumConfig config;
	protected TransformationContext transformationContext;
	protected OpaeumLibrary getLibrary(){
		return workspace.getOpaeumLibrary();
	}
	protected String getPathNameInModel(Classifier stereotype){
		StringBuilder pn = new StringBuilder();
		appendNames(stereotype, pn);
		pn.append(stereotype.getName());
		return pn.toString();
	}
	protected void appendNames(EObject p,StringBuilder pn){
		EObject parent = EmfElementFinder.getContainer(p);
		if(parent != null){
			appendNames(parent, pn);
		}
		if(p instanceof NamedElement){
			pn.append(((NamedElement) p).getName());
			pn.append("::");
		}
	}
	protected void visitParentsRecursively(Element parent){
		if(EmfElementFinder.getContainer(parent) instanceof Element){
			visitParentsRecursively((Element) EmfElementFinder.getContainer(parent));
			for(VisitSpec v:methodInvokers.beforeMethods){
				maybeVisit(parent, v);
			}
		}
	}
	public void visitUpThenDown(Element e){
		visitParentsRecursively(e);
		visitRecursively(e);
	}
	@Override
	public void visitRecursively(Element o){
		boolean b = !(o instanceof Profile);
		if(o instanceof Model && !(workspace.getGeneratingModelsOrProfiles().contains(o) || EmfPackageUtil.isRegeneratingLibrary((Model) o))){
			b = false;
		}
		if(!EmfElementUtil.isMarkedForDeletion(o) && b){
			if(EmfPackageUtil.isRootObject(o)){
				Package pkg = (Package) o;
				this.setCurrentRootObject(pkg);
			}
			visitBeforeMethods(o);
			visitChildren(o);
			visitAfterMethods(o);
			if(EmfPackageUtil.isRootObject(o)){
				setCurrentRootObject(null);// NB!! needs to be cleared from every thread
			}
		}
	}
	@Override
	public void visitOnly(Element o){
		if(!EmfElementUtil.isMarkedForDeletion(o)){
			if(o instanceof Element){
				setCurrentRootObject(EmfElementFinder.getRootObject((Element) o));
			}
			super.visitOnly(o);
			setCurrentRootObject(null);
		}
	}
	protected Collection<Package> getModelInScope(){
		Set<Package> result = new HashSet<Package>(EmfPackageUtil.getAllDependencies(getCurrentRootObject()));
		result.add(getCurrentRootObject());
		return result;
	}
	public final void setTransformationContext(TransformationContext c){
		this.transformationContext = c;
	}
	public void release(){
		this.workspace = null;
		setCurrentRootObject(null);// NB! Not enough. Needs to be done from every thread
	}
	@Override
	public Collection<Element> getChildren(Element root){
		if(root instanceof ModelWorkspace){
			return new ArrayList<Element>(((ModelWorkspace) root).getRootObjects());
		}else{
			return EmfElementFinder.getCorrectOwnedElements(root);
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
		return 1;
	}
	protected boolean canAcceptInputFrom(ObjectNode target,MultiplicityElement returnParam){
		if(target instanceof Pin){
			MultiplicityElement tm = (Pin) target;
			return canAcceptInputFromImpl(tm, returnParam);
		}else if(target instanceof ActivityParameterNode && ((ActivityParameterNode) target).getParameter() != null){
			Parameter p = ((ActivityParameterNode) target).getParameter();
			return canAcceptInputFromImpl(p, returnParam);
		}
		return true;
	}
	protected boolean canDeliverOutputTo(ObjectNode source,MultiplicityElement structuralFeature){
		if(source instanceof Pin){
			MultiplicityElement tm = (Pin) source;
			return canAcceptInputFromImpl(structuralFeature, tm);
		}else if(source instanceof ActivityParameterNode && ((ActivityParameterNode) source).getParameter() != null){
			Parameter p = ((ActivityParameterNode) source).getParameter();
			return canAcceptInputFromImpl(structuralFeature, p);
		}
		return true;
	}
	protected boolean canDeliverOutputToObjectNode(ObjectNode source,ObjectNode structuralFeature){
		if(structuralFeature instanceof Pin){
			return canDeliverOutputTo(source, (Pin) structuralFeature);
		}else if(structuralFeature instanceof ActivityParameterNode && ((ActivityParameterNode) structuralFeature).getParameter() != null){
			Parameter p = ((ActivityParameterNode) structuralFeature).getParameter();
			return canDeliverOutputTo(source, p);
		}
		return true;
	}
	protected boolean canAcceptInputFromImpl(MultiplicityElement tm,MultiplicityElement returnParam){
		if(tm.isMultivalued()){
			if(returnParam.isMultivalued()){
				return returnParam.isUnique() == tm.isUnique() && returnParam.isOrdered() == tm.isOrdered();
			}else{
				return true;
			}
		}else if(returnParam.isMultivalued()){
			return false;
		}else{
			return true;
		}
	}
}
