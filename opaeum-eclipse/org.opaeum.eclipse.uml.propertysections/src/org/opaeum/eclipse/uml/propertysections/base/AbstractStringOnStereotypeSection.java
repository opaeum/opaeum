package org.opaeum.eclipse.uml.propertysections.base;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.ProfileApplier;
import org.opaeum.eclipse.commands.ApplyProfileCommand;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;
import org.opaeum.emf.extraction.StereotypesHelper;

public abstract class AbstractStringOnStereotypeSection extends AbstractStringPropertySection{
	public AbstractStringOnStereotypeSection(){
		super();
	}
	protected abstract Element getElement(EObject o);
	protected abstract String getAttributeName();
	protected abstract String getProfileName();
	protected abstract String getStereotypeName(Element e);
	protected String getFeatureAsString(){
		Element element = getElement(getEObject());
		if(element != null){
			Stereotype st = StereotypesHelper.getStereotype(element, getStereotypeName(element));
			if(st != null){
				String string = element == null ? null : (String) element.getStereotypeApplication(st).eGet(getFeature());
				if(string == null){
					return "";
				}else{
					return string;
				}
			}
		}
		return "";
	}
	@Override
	protected EStructuralFeature getFeature(){
		if(getEObject() != null){
			Element element = getElement(getEObject());
			Profile p = ProfileApplier.getAppliedProfile(element.getModel(), getProfileName());
			if(p != null){
				Stereotype stereotype = p.getOwnedStereotype(getStereotypeName(element));
				if(stereotype != null && element.isStereotypeApplied(stereotype)){
					return stereotype.getDefinition().getEStructuralFeature(getAttributeName());
				}
			}
		}
		return null;
	}
	@Override
	protected void handleTextModified(){
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
			cc.append(SetCommand.create(getEditingDomain(), element.getStereotypeApplication(stereotype), feature, text.getText()));
		}
		getEditingDomain().getCommandStack().execute(cc);
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
	}
	@Override
	public void refresh(){
		super.refresh();
		Set<String> values = new HashSet<String>();
		List<EObject> eObjectList = getEObjectList();
		for(EObject eObject:eObjectList){
			Element element = getElement(eObject);
			Stereotype st = StereotypesHelper.getStereotype(element, getStereotypeName(element));
			if(st != null){
				String value = (String) getElement(eObject).getValue(st, getAttributeName());
				values.add(value == null ? "" : value);
			}else{
				values.add("");
			}
		}
		if(values.size() == 1){
			text.setText(values.iterator().next());
		}else{
			text.setText("");
		}
	}
}