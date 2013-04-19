package org.opaeum.eclipse.commands;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.name.NameConverter;

public class AddStereotypeValueCommand extends StereotypeValueCommand{
	private boolean isCompositeFeature;
	private Object value;
	public AddStereotypeValueCommand(EditingDomain editingDomain,Element eOwner,StereotypeValueInformation inf,Object value){
		this(editingDomain,eOwner,inf.getFeatureName(),value,inf.isCompositeFeature());
		setStereotypeName(inf.getStereotypeName());
		super.setProfileName(inf.getProfileName());
	}
	public AddStereotypeValueCommand(EditingDomain editingDomain,Element eOwner,String featureName,Object value){
		this(editingDomain, eOwner, featureName, value, true);
	}
	public AddStereotypeValueCommand(EditingDomain editingDomain,Element eOwner,String featureName,Object value,boolean isCompositeFeature){
		super(editingDomain, eOwner, featureName);
		this.isCompositeFeature = isCompositeFeature;
		this.value = value;
	}
	@Override
	protected boolean prepareImpl(){
		maybeAddToAnnotation(editingDomain, value, isCompositeFeature);
		if(value instanceof NamedElement && ((NamedElement) value).getName() == null){
			NamedElement ne = (NamedElement) value;
			String name = NameConverter.decapitalize(ne.eClass().getName()) + "1";
			append(SetCommand.create(editingDomain, ne, UMLPackage.eINSTANCE.getNamedElement_Name(), name));
		}
		append(new AddCommand(editingDomain, new BasicEList<Object>(), value){
			@Override
			public boolean doCanExecute(){
				return true;
			}
			@SuppressWarnings("unchecked")
			@Override
			public void doExecute(){
				if(getStereotypeApplication() != null){
					super.ownerList = (EList<Object>) getStereotypeApplication().eGet(AddStereotypeValueCommand.this. getFeature());
					super.owner = getStereotypeApplication();
					super.isPrepared=false;
					if(super.doCanExecute()){
						super.doExecute();
					}
				}
			}
		});
		return true;
	}
}
