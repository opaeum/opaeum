package org.opaeum.topcased.propertysections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.tabbedproperties.sections.AbstractBooleanPropertySection;

public class PropertyIsNavigableSection extends AbstractBooleanPropertySection{
	// TODO put in OperationBooleanFeatures section
	protected void handleCheckButtonModified(){
		EditingDomain editingDomain = (EditingDomain) getPart().getAdapter(EditingDomain.class);
		if(getEObjectList().size() == 1){
			editingDomain.getCommandStack().execute(getNavigationCommand(editingDomain, getProperty().getAssociation()));
		}else{
			CompoundCommand compoundCommand = new CompoundCommand();
			for(EObject nextObject:getEObjectList()){
				compoundCommand.append(getNavigationCommand(editingDomain, ((Property) nextObject).getAssociation()));
			}
			editingDomain.getCommandStack().execute(compoundCommand);
		}
	}
	private Command getNavigationCommand(EditingDomain editingDomain,Association association){
		Command command = null;
		EList<Property> navList = new BasicEList<Property>();
		navList.addAll(association.getNavigableOwnedEnds());
		if(getCheckButton().getSelection()){
			if(association.getOwnedEnds().contains(getProperty())){
				navList.add(getProperty());
				command = SetCommand.create(editingDomain, association, UMLPackage.eINSTANCE.getAssociation_NavigableOwnedEnd(), navList);
			}
		}else{
			if(association.getOwnedEnds().contains(getProperty())){
				navList.remove(getProperty());
				command = SetCommand.create(editingDomain, association, UMLPackage.eINSTANCE.getAssociation_NavigableOwnedEnd(), navList);
			}
		}
		return command;
	}
	@Override
	public void refresh(){
		super.refresh();
		Association association = (Association) getProperty().getAssociation();
		if(association.getOwnedEnds().contains(getProperty())){
			getCheckButton().setEnabled(true);
		}else{
			getCheckButton().setEnabled(false);
		}
	}
	protected EStructuralFeature getFeature(){
		return null;
	}
	protected boolean getFeatureValue(){
		return getProperty().isNavigable();
	}
	protected String getLabelText(){
		return "isNavigable";
	}
	protected Property getProperty(){
		return (Property) getEObject();
	}
}