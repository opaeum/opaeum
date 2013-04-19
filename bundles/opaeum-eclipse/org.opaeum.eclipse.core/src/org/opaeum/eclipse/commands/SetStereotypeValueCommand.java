package org.opaeum.eclipse.commands;

import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.name.NameConverter;

public class SetStereotypeValueCommand extends StereotypeValueCommand{
	private Object value;
	private boolean isCompositeFeature;
	public SetStereotypeValueCommand(EditingDomain editingDomain,Element eOwner,StereotypeValueInformation inf,Object value){
		this(editingDomain,eOwner,inf.getFeatureName(),value,inf.isCompositeFeature());
		setStereotypeName(inf.getStereotypeName());
		super.setProfileName(inf.getProfileName());
	}

	public SetStereotypeValueCommand(EditingDomain editingDomain,Element eOwner,String featureName,Object value){
		this(editingDomain, eOwner, featureName, value, true);
	}
	public SetStereotypeValueCommand(EditingDomain editingDomain,Element eOwner,String featureName,Object value,boolean isCompositeFeature){
		super(editingDomain ,eOwner, featureName);
		this.value=value;
		this.isCompositeFeature=isCompositeFeature;
	}
	@Override
	protected boolean prepareImpl(){
		if(getStereotypeApplication()!=null){
			Object oldValue = getStereotypeApplication().eGet(getFeature());
			maybeRemoveFromAnnotation(editingDomain, oldValue);
		}
		maybeAddToAnnotation(editingDomain, value, isCompositeFeature);
		if(value instanceof NamedElement && ((NamedElement) value).getName() == null){
			NamedElement ne = (NamedElement) value;
			String name = NameConverter.decapitalize(ne.eClass().getName()) + "1";
			append(SetCommand.create(editingDomain, ne, UMLPackage.eINSTANCE.getNamedElement_Name(), name));
		}
		append(new SetCommand(editingDomain, owner, getFeature(), value){
			@Override
			public boolean doCanExecute(){
				return true;
			}
			// Need to lazily get the stereotype application
			@Override
			public void doExecute(){
				super.owner = getStereotypeApplication();
				super.isPrepared=false;
				if(super.doCanExecute()){
					super.doExecute();
				}
			}
		});
		return true;
	}
}
