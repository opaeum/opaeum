package org.opaeum.eclipse.newchild;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.CreateStereotypedChildAction;
import org.opaeum.name.NameConverter;

public class DefaultCreateChildAction implements ICreateChildAction{
	MatchingOwner[] matchingOwners;
	EClass classOfChild;
	public String stereotypeOfChild;
	String featureName;
	private String name;
	public DefaultCreateChildAction(MatchingOwner[] matchingOwners,String featureName,EClass classOfChild){
		super();
		this.matchingOwners = matchingOwners;
		this.classOfChild = classOfChild;
		this.featureName = featureName;
	}
	public DefaultCreateChildAction(MatchingOwner[] matchingOwners,String featureName,EClass classOfChild,
			String stereotypeOfChild){
		super();
		this.matchingOwners = matchingOwners;
		this.classOfChild = classOfChild;
		this.stereotypeOfChild = stereotypeOfChild;
		this.featureName = featureName;
	}
	public DefaultCreateChildAction(MatchingOwner s,String fromExpression,EClass opaqueExpression,
			String recipientExpression){
		this(new MatchingOwner[]{s},fromExpression,opaqueExpression,recipientExpression);
	}
	public DefaultCreateChildAction(MatchingOwner s,String fromExpression,EClass opaqueExpression){
		this(new MatchingOwner[]{s},fromExpression,opaqueExpression);
	}
	@Override
	public boolean isPotentialParent(EObject e){
		MatchingOwner[] matchingOwners2 = matchingOwners;
		for(MatchingOwner matchingOwner:matchingOwners2){
			if(matchingOwner.matches(e)){
				return true;
			}
		}
		return false;
	}
	@Override
	public CreateChildAction createAction(IWorkbenchPart workbenchPart,IStructuredSelection selection){
		EObject owner = (EObject) CreateStereotypedChildAction.getEObjectFromSelection(selection);
		EStructuralFeature feature = matchingOwners[0].getEStructuralFeature(owner, featureName);
		if(feature == null){
			if(owner instanceof Element){
				for(EObject st:((Element) owner).getStereotypeApplications()){
					if((feature = st.eClass().getEStructuralFeature(featureName)) != null){
						break;
					}
				}
			}
		}
		if(feature == null){
			System.out.println(featureName + " not found on " + owner.eClass().getName()); 
			throw new IllegalStateException();
		}
		EObject newChild = classOfChild.getEPackage().getEFactoryInstance().create(classOfChild);
		CommandParameter descriptor = new CommandParameter(owner, feature, newChild);
		CreateChildAction result;
		String childName = null;
		if(stereotypeOfChild != null){
			result = new CreateStereotypedChildAction(workbenchPart, selection, descriptor, stereotypeOfChild);
			childName = NameConverter.capitalize(NameConverter.separateWords(stereotypeOfChild));
		}else{
			result = new CreateChildAction(workbenchPart, selection, descriptor);
			childName = NameConverter.capitalize(NameConverter.separateWords(classOfChild.getName()));
		}
		if(name == null){
			result.setText(NameConverter.capitalize(NameConverter.separateWords(featureName)) + "|" + childName);
		}else{
			result.setText(name);
		}
		return result;
	}
}
