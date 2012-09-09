package org.opaeum.eclipse.newchild;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.CreateStereotypedChildAction;
import org.opaeum.eclipse.OpaeumEclipsePlugin;

public abstract class AbstractCreateChildAction implements ICreateChildAction{
	private MatchingOwner[] matchingOwners;
	protected EClass classOfChild;
	protected String featureName;
	protected String name;

	public AbstractCreateChildAction(MatchingOwner[] matchingOwners, String featureName, EClass classOfChild){
		super();
		this.matchingOwners = matchingOwners;
		this.classOfChild = classOfChild;
		this.featureName = featureName;

	}

	public boolean isPotentialParent(EObject e){
		MatchingOwner[] matchingOwners2 = matchingOwners;
		for(MatchingOwner matchingOwner:matchingOwners2){
			if(matchingOwner.matches(e)){
				return true;
			}
		}
		return false;
	}
	private EObject getCorrectOwner(EObject owner,String featureName){
		if(owner.eClass().getEStructuralFeature(featureName) != null){
			return owner;
		}else if(owner instanceof Element){
			for(EObject st:((Element) owner).getStereotypeApplications()){
				EStructuralFeature f = st.eClass().getEStructuralFeature(featureName);
				if(f != null){
					return st;
				}
			}
		}
		return null;
	}
	protected CommandParameter createCommandParameter(IStructuredSelection selection){
		EObject owner = (EObject) CreateStereotypedChildAction.getEObjectFromSelection(selection);
		EStructuralFeature feature = getEStructuralFeature(owner, featureName);
		CommandParameter descriptor=null;
		if(feature == null){
			OpaeumEclipsePlugin.logError(featureName + " not found on " + owner.eClass().getName(), new IllegalStateException());
		}else{
			EObject newChild = classOfChild.getEPackage().getEFactoryInstance().create(classOfChild);
			descriptor = new CommandParameter(getCorrectOwner(owner, featureName), feature, newChild);
		}
		return descriptor;
	}

	private EStructuralFeature getEStructuralFeature(EObject owner,String featureName){
		EStructuralFeature result = owner.eClass().getEStructuralFeature(featureName);
		if(result == null && owner instanceof Element){
			for(EObject st:((Element) owner).getStereotypeApplications()){
				EStructuralFeature f = st.eClass().getEStructuralFeature(featureName);
				if(f != null){
					result = f;
					break;
				}
			}
		}
		return result;
	}

}