package org.opaeum.eclipse.commands;

import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Element;

public class RemoveStereotypeValueCommand extends StereotypeValueCommand{
	Object oldValue;
	public RemoveStereotypeValueCommand(EditingDomain editingDomain,Element eOwner,StereotypeValueInformation inf,Object value){
		this(editingDomain,eOwner,inf.getFeatureName(),value,inf.isCompositeFeature());
		setStereotypeName(inf.getStereotypeName());
		super.setProfileName(inf.getProfileName());
	}
	public RemoveStereotypeValueCommand(EditingDomain editingDomain,Element eOwner,String featureName,Object oldValue){
		this(editingDomain, eOwner, featureName, oldValue, true);
	}
	public RemoveStereotypeValueCommand(EditingDomain editingDomain,Element eOwner,String featureName,Object oldValue,boolean isCompositeFeature){
		super(editingDomain, eOwner, featureName);
	}
	@Override
	protected boolean prepareImpl(){
		super.maybeRemoveFromAnnotation(editingDomain, oldValue);
		if(getStereotypeApplication() == null){
			return false;
		}else{
			append(new RemoveCommand(editingDomain, getStereotypeApplication(), getFeature(), oldValue));
			return true;
		}
	}
}
