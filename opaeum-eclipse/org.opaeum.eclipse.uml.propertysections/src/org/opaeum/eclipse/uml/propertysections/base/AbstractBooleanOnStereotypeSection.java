package org.opaeum.eclipse.uml.propertysections.base;

import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.ProfileApplier;
import org.opaeum.eclipse.commands.ApplyProfileCommand;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;
import org.opaeum.emf.extraction.StereotypesHelper;

public abstract class AbstractBooleanOnStereotypeSection extends AbstractBooleanSection{
	protected abstract Boolean getDefaultValue();
	protected abstract Element getElement(EObject eObject);
	protected abstract String getAttributeName();
	protected abstract String getProfileName();
	protected abstract String getStereotypeName(Element element);
	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
	}
	@Override
	public Control getPrimaryInput(){
		return check;
	}
	public void handleSelection(){
		check.setGrayed(false);
		CompoundCommand cc = new CompoundCommand();
		List<EObject> list = getEObjectList();
		for(EObject eObject:list){
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
			cc.append(SetCommand.create(getEditingDomain(), element.getStereotypeApplication(stereotype), feature, check.getSelection()));
		}
		getEditingDomain().getCommandStack().execute(cc);
	}
	protected Boolean getBooleanValue(EObject eObject){
		Element element = getElement(eObject);
		Stereotype stereotype = StereotypesHelper.getStereotype(element, getStereotypeName(element));
		if(stereotype != null){
			Boolean value = (Boolean) element.getValue(stereotype,getAttributeName());
			return value;
		}else{
			return null;
		}
	}
}