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
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Operation;
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
				if(descriptor.getValue() instanceof Operation){
					actio.setText("Owned Responsibility|Responsibility");
					StereotypesHelper.getNumlAnnotation((Element) descriptor.getEValue()).getDetails().put(StereotypeNames.RESPONSIBILITY,"");
					descriptor.getEValue();
				}
				actions.add(actio);
			}
		}
		return actions;
	}
}
