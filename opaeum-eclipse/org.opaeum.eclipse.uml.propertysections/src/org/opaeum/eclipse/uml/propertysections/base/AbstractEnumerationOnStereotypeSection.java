package org.opaeum.eclipse.uml.propertysections.base;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.ProfileApplier;
import org.opaeum.eclipse.commands.ApplyProfileCommand;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;

public abstract class AbstractEnumerationOnStereotypeSection extends AbstractEnumerationPropertySection{
	private List<String> literals;
	private EList<EEnumLiteral> eLiterals;
	public AbstractEnumerationOnStereotypeSection(){
		super();
	}
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
	@Override
	protected String getFeatureAsText(){
		Object oldFeatureValue = getOldFeatureValue();
		if(oldFeatureValue instanceof EEnumLiteral){
			return ((EEnumLiteral) oldFeatureValue).getName();
		}
		return "";
	}
	@Override
	protected String[] getEnumerationFeatureValues(){
		if(literals == null){
			literals = new ArrayList<String>();
			Profile p = ProfileApplier.getProfile(getElement(getEObject()), getProfileName());
			if(p != null){
				Stereotype s = p.getOwnedStereotype(getStereotypeName(getElement(getEObject())));
				if(s != null){
					EStructuralFeature feature = s.getDefinition().getEStructuralFeature(getAttributeName());
					if(feature != null && getFeature().getEType() instanceof EEnum){
						literals = new ArrayList<String>();
						eLiterals = ((EEnum) feature.getEType()).getELiterals();
						for(EEnumLiteral eEnumLiteral:eLiterals){
							literals.add(eEnumLiteral.getName());
						}
					}
				}
			}
		}
		return literals.toArray(new String[literals.size()]);
	}
	protected Object getOldFeatureValue(){
		return "";
	}
	protected LabelProvider getLabelProvider(){
		return new LabelProvider(){
			public String getText(Object element){
				return element == null ? "" : ((EEnumLiteral) element).getName();
			}
		};
	}
	@Override
	protected Object getFeatureValue(String name){
		for(EEnumLiteral e:eLiterals){
			if(e.getName().equalsIgnoreCase(name)){
				return e;
			}
		}
		return null;
	}
	@Override
	protected void handleComboModified(String name){
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
			cc.append(SetCommand.create(getEditingDomain(), element.getStereotypeApplication(stereotype), feature, getFeatureValue(name)));
		}
		getEditingDomain().getCommandStack().execute(cc);
	}
}