package org.opaeum;

import java.util.Collection;
import java.util.List;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.visit.VisitorAdapter;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public abstract class EmfElementVisitor extends VisitorAdapter<Element,EmfWorkspace>{
	private ThreadLocal<Package> currentRootObject = new ThreadLocal<Package>();
	public final Package getCurrentRootObject(){
		return currentRootObject.get();
	}
	protected final void setCurrentRootObject(Package currentRootObject){
		this.currentRootObject.set(currentRootObject);
	}
	@Override
	public void visitOnly(Element o){
		setCurrentRootObject(EmfElementFinder.getRootObject(o));
		super.visitOnly(o);
		setCurrentRootObject(null);
	}
	@Override
	public void visitRecursively(Element o){
		if(EmfPackageUtil.isRootObject(o)){
			this.setCurrentRootObject((Package) o);
		}
		super.visitRecursively(o);
		if(EmfPackageUtil.isRootObject(o)){
			setCurrentRootObject(null);// NB!! needs to be cleared from every
			// thread
		}
	}

	@Override
	public Collection<Element> getChildren(Element root){
		return getChildrenAndRetryIfFailed(root, 0);
	}
	@SuppressWarnings("unchecked")
	protected Collection<Element> getChildrenAndRetryIfFailed(Element root,int count){
		Collection<Element> elements = EmfElementFinder.getCorrectOwnedElements(root);
		if(!(root instanceof EmfWorkspace) && root.getEAnnotation(StereotypeNames.NUML_ANNOTATION) != null){
			@SuppressWarnings("rawtypes")
			List contents = root.getEAnnotation(StereotypeNames.NUML_ANNOTATION).getContents();
			elements.addAll((Collection<? extends Element>) contents);
		}
		try{
			if(root instanceof Classifier){
				Classifier c = (Classifier) root;
				for(Association association:c.getAssociations()){
					for(Property property:association.getNavigableOwnedEnds()){
						if(property.getOtherEnd() != null && c.equals(property.getOtherEnd().getType())){
							// property.getOtherEnd() could be null during deletion
							elements.add(property);
						}
					}
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			if(count < 5){
				try{
					Thread.sleep(2000);
				}catch(InterruptedException e1){
				}
				return getChildrenAndRetryIfFailed(root, ++count);
			}
			// HACK weird bug in:
			// org.eclipse.emf.ecore.util.ECrossReferenceAdapter.getInverseReferences(ECrossReferenceAdapter.java:332)
		}
		return elements;
	}
}
