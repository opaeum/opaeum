package org.opaeum.eclipse;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;
import org.opaeum.eclipse.newchild.CreateChildAndSelectAction;

public class CreateStereotypedChildAction extends CreateChildAndSelectAction{
	String[] stereotypes;
	public CreateStereotypedChildAction(IEditorPart editorPart,ISelection selection,CommandParameter descriptor,String...stereotypes){
		super(editorPart, selection, descriptor);
		this.stereotypes = stereotypes;
	}
	public CreateStereotypedChildAction(IWorkbenchPart workbenchPart,ISelection selection,CommandParameter descriptor,String...stereotypes){
		super(workbenchPart, selection, descriptor);
		this.stereotypes = stereotypes;
	}
	public CreateStereotypedChildAction(EditingDomain editingDomain,ISelection selection,CommandParameter descriptor,String...stereotypes){
		super(editingDomain, selection, descriptor);
		this.stereotypes = stereotypes;
	}
	public static EObject getEObjectFromSelection(IStructuredSelection ss){
		if(ss.getFirstElement() instanceof EObject){
			return (EObject) ss.getFirstElement();
		}else if(ss.getFirstElement() instanceof IAdaptable){
			return (EObject) ((IAdaptable) ss.getFirstElement()).getAdapter(EObject.class);
		}else{
			return null;
		}
	}
	@Override
	public void run(){
		super.run();
		if(editingDomain != null && command != null){
			boolean firstStereotypeApplied = false;
			for(Object object:command.getResult()){
				if(object instanceof Element){
					Element element = (Element) object;
					Package nearestPackage = EmfElementFinder.getNearestClassifier(element).getNearestPackage();
					for(Profile profile:nearestPackage.getAllAppliedProfiles()){
						for(String string:stereotypes){
							Stereotype stereotype = profile.getOwnedStereotype(string);
							if(stereotype != null){
								editingDomain.getCommandStack().execute(new ApplyStereotypeCommand(element, !firstStereotypeApplied, stereotype));
								firstStereotypeApplied = true;
							}
						}
					}
				}
			}
		}
	}
}
