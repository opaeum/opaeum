package org.opaeum.eclipse.uml.propertysections.base;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.ProfileApplier;
import org.opaeum.eclipse.commands.ApplyProfileCommand;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.topcased.tabbedproperties.internal.utils.Messages;

public abstract class ChooserPropertyOnStereotypeSection extends OpaeumChooserPropertySection{
	private Stereotype stereotype;
	private EStructuralFeature feature;
	protected abstract Element getElement(EObject o);
	protected abstract String getAttributeName();
	protected abstract String getProfileName();
	protected abstract String getStereotypeName();
	public EStructuralFeature getFeature(){
		return feature;
	}
	protected void createCommand(Object oldValue,Object newValue){
		boolean equals = oldValue == null ? false : oldValue.equals(newValue);
		if(!equals){
			EditingDomain editingDomain = getEditingDomain();
			Object value = newValue;
			CompoundCommand compoundCommand = new CompoundCommand(Messages.AbstractTabbedPropertySection_CommandName);
			// apply the property change to all selected elements
			for(EObject nextObject:getEObjectList()){
				if(nextObject instanceof Element && StereotypesHelper.hasStereotype((Element) nextObject, getStereotypeName())){
					compoundCommand.append(SetCommand.create(editingDomain, ((Element) nextObject).getStereotypeApplication(stereotype),
							getFeature(), value));
				}
			}
			editingDomain.getCommandStack().execute(compoundCommand);
		}
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		Element element = getElement(getEObject());
		Profile p = ProfileApplier.getAppliedProfile(element.getModel(), getProfileName());
		if(p == null && element != null){
			Package pkg = element.getModel() == null ? element.getNearestPackage() : element.getModel();
			getEditingDomain().getCommandStack().execute(
					new ApplyProfileCommand(pkg, p = ProfileApplier.getProfile(element, getProfileName()), false));
		}
		if(p != null){
			this.stereotype = p.getOwnedStereotype(getStereotypeName());
			this.feature = this.stereotype.getDefinition().getEStructuralFeature(getAttributeName());
		}
	}
	@Override
	public void refresh(){
		super.refresh();
	}
	@Override
	protected Object getFeatureValue(){
		if(stereotype != null && getElement(getEObject()).isStereotypeApplied(stereotype)){
			return getElement(getEObject()).getValue(stereotype, getAttributeName());
		}else{
			return null;
		}
	}
}
