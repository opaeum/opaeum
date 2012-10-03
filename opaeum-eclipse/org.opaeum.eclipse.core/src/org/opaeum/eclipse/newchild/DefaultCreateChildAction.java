package org.opaeum.eclipse.newchild;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.opaeum.eclipse.CreateStereotypedChildAction;
import org.opaeum.metamodel.workspace.OpaeumLibrary;
import org.opaeum.name.NameConverter;

public class DefaultCreateChildAction extends AbstractCreateChildAction implements ICreateChildAction{
	public String stereotypeOfChild;
	public DefaultCreateChildAction(MatchingOwner[] matchingOwners,String featureName,EClass classOfChild){
		super(matchingOwners, featureName, classOfChild);
	}
	public DefaultCreateChildAction(MatchingOwner[] matchingOwners,String featureName,EClass classOfChild,String stereotypeOfChild){
		this(matchingOwners, featureName, classOfChild);
		this.stereotypeOfChild = stereotypeOfChild;
	}
	public DefaultCreateChildAction(MatchingOwner s,String fromExpression,EClass opaqueExpression,String recipientExpression){
		this(new MatchingOwner[]{s}, fromExpression, opaqueExpression, recipientExpression);
	}
	public DefaultCreateChildAction(MatchingOwner s,String fromExpression,EClass opaqueExpression){
		this(new MatchingOwner[]{s}, fromExpression, opaqueExpression);
	}
	@Override
	public CreateChildAction createAction(IWorkbenchPart workbenchPart,IStructuredSelection selection,OpaeumLibrary lib){
		CommandParameter descriptor = createCommandParameter(selection);
		if(descriptor == null){
			return null;
		}
		CreateChildAction result;
		String childName = null;
		if(stereotypeOfChild != null){
			result = new CreateStereotypedChildAction(workbenchPart, selection, descriptor, stereotypeOfChild);
			childName = NameConverter.capitalize(NameConverter.separateWords(stereotypeOfChild));
		}else{
			result = new CreateChildAndSelectAction(workbenchPart, selection, descriptor);
			childName = NameConverter.capitalize(NameConverter.separateWords(classOfChild.getName()));
		}
		if(name == null){
			result.setText(NameConverter.capitalize(NameConverter.separateWords(featureName)) + "|" + childName);
		}else{
			result.setText(name);
		}
		if(!descriptor.getEStructuralFeature().isMany() && descriptor.getEOwner().eGet(descriptor.getEStructuralFeature())!=null){
			result.setEnabled(false);
		}
		return result;
	}
}
