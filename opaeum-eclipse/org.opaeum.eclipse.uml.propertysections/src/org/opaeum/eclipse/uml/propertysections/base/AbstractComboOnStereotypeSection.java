package org.opaeum.eclipse.uml.propertysections.base;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.ProfileApplier;
import org.opaeum.eclipse.commands.ApplyProfileCommand;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;

public abstract class AbstractComboOnStereotypeSection extends AbstractComboPropertySection{
	protected abstract Element getElement();
	protected Element getElement(EObject e){
		return (Element) e;
	}
	protected abstract String getAttributeName();
	protected abstract String getProfileName();
	protected abstract String getStereotypeName(Element e);
	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}
	protected Object getOldFeatureValue(){
		return "";
	}
	protected void handleComboModified(){
		CompoundCommand cc = new CompoundCommand();
		for(EObject eObject:getEObjectList()){
			Element element = getElement(eObject);
			Profile p = ProfileApplier.getAppliedProfile(element.getModel(), getProfileName());
			if(p == null){
				Package pkg = element.getModel() == null ? element.getNearestPackage() : element.getModel();
				getEditingDomain().getCommandStack().execute(new ApplyProfileCommand(pkg, p = ProfileApplier.getProfile(element, getProfileName()), false));
			}
			Stereotype stereotype = p.getOwnedStereotype(getStereotypeName(element));
			EStructuralFeature feature = stereotype.getDefinition().getEStructuralFeature(getAttributeName());
			if(!element.isStereotypeApplied(stereotype)){
				getEditingDomain().getCommandStack().execute(new ApplyStereotypeCommand(element, stereotype));
			}
			cc.append(SetCommand.create(getEditingDomain(), element.getStereotypeApplication(stereotype), feature, getSelectedItem()));
		}
		getEditingDomain().getCommandStack().execute(cc);
	}
}
