package org.nakeduml.topcased.uml.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.editor.MixedEditDomain;
import org.topcased.modeler.uml.internal.customchildmenu.UMLEditorMenu;

public class NakedUmlEditorMenu extends UMLEditorMenu{
	private MixedEditDomain domain;
	private EObject selectedObject;
	private Collection<CommandParameter> descriptors;
	public void createMenuContents(){
		this.descriptors = new ArrayList<CommandParameter>();
		Set<EStructuralFeature> structurlFeatures = new HashSet<EStructuralFeature>();
		structurlFeatures.add(UMLPackage.eINSTANCE.getClass_OwnedOperation());
		structurlFeatures.add(UMLPackage.eINSTANCE.getStructuredClassifier_OwnedAttribute());
		structurlFeatures.add(UMLPackage.eINSTANCE.getPackage_OwnedType());
		structurlFeatures.add(UMLPackage.eINSTANCE.getBehavioredClassifier_OwnedBehavior());
		for(Object o:domain.getEMFEditingDomain().getNewChildDescriptors(selectedObject, null)){
			CommandParameter cp = (CommandParameter) o;
			if(NakedUmlFilter.isAllowedElement(cp.getValue())){
				this.descriptors.add(cp);
			}
		}
		Collection<IAction> createChildActions = generateCreateChildActions(descriptors, Workbench.getInstance().getActiveWorkbenchWindow().getSelectionService()
				.getSelection());
		Map<String,Collection<IAction>> createChildSubmenuActions = extractSubmenuActions(createChildActions);
		populateManager(this, createChildSubmenuActions, null);
		populateManager(this, createChildActions, null);
		this.update();
	}
	public void setMixedEditDomain(MixedEditDomain editDomain){
		super.setMixedEditDomain(editDomain);
		this.domain = editDomain;
	}
	public void setSelectedEObject(EObject object){
		super.setSelectedEObject(object);
		selectedObject = object;
	}
	protected Collection<IAction> generateCreateChildActionsGen(Collection<?> theDescriptors,ISelection selection){
		Collection<IAction> actions = new ArrayList<IAction>();
		if(theDescriptors != null){
			for(CommandParameter descriptor:(Collection<CommandParameter>) theDescriptors){
				CreateChildAction actio = new CreateChildAction(Workbench.getInstance().getActiveWorkbenchWindow().getPartService().getActivePart(), selection,
						descriptor);
				actions.add(actio);
				if(descriptor.getValue() instanceof Operation){
					Operation responsibility = UMLFactory.eINSTANCE.createOperation();
					CreateChildAction actio2=new CreateChildAction(Workbench.getInstance().getActiveWorkbenchWindow().getPartService().getActivePart(), selection,
							new CommandParameter(descriptor.getOwner(),descriptor.getFeature(), responsibility));
					actio2.setText("Owned Operation|Responsibility");
					StereotypesHelper.getNumlAnnotation(responsibility).getDetails().put(StereotypeNames.RESPONSIBILITY,"");
					actions.add(actio2);
				}else if(descriptor.getValue() instanceof Activity && descriptor.getFeature().equals(UMLPackage.eINSTANCE.getBehavioredClassifier_OwnedBehavior())){
					Activity businessProcess = UMLFactory.eINSTANCE.createActivity();
					CreateChildAction actio2=new CreateChildAction(Workbench.getInstance().getActiveWorkbenchWindow().getPartService().getActivePart(), selection,
							new CommandParameter(descriptor.getOwner(),descriptor.getFeature(),businessProcess));
					actio2.setText("Owned Behavior|Business Process");
					StereotypesHelper.getNumlAnnotation(businessProcess).getDetails().put(StereotypeNames.BUSINES_PROCESS,"");
					actions.add(actio2);
					Activity method = UMLFactory.eINSTANCE.createActivity();
					CreateChildAction actio3=new CreateChildAction(Workbench.getInstance().getActiveWorkbenchWindow().getPartService().getActivePart(), selection,
							new CommandParameter(descriptor.getOwner(),descriptor.getFeature(),method));
					actio3.setText("Owned Behavior|Method");
					StereotypesHelper.getNumlAnnotation(method).getDetails().put(StereotypeNames.METHOD,"");
					actions.add(actio3);
				}
			}
		}
		return actions;
	}
}
