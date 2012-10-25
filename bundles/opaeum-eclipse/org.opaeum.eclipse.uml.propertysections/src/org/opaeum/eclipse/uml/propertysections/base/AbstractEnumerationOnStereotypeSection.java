package org.opaeum.eclipse.uml.propertysections.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.ProfileApplier;
import org.opaeum.eclipse.commands.LazySetTagValueCommand;
import org.opaeum.emf.extraction.StereotypesHelper;

public abstract class AbstractEnumerationOnStereotypeSection extends AbstractEnumerationPropertySection{
	private List<String> literals;
	private Map<String,EEnumLiteral> eLiterals;
	public AbstractEnumerationOnStereotypeSection(){
		super();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}
	@Override
	protected String getFeatureAsText(EObject e){
		Object oldFeatureValue = e.eGet(getFeature(e));
		if(oldFeatureValue instanceof EEnumLiteral){
			return ((EEnumLiteral) oldFeatureValue).getName();
		}
		return "";
	}
	@Override
	protected Object getFeatureValue(String name){
		return eLiterals.get(name);
	}
	@Override
	protected String[] getEnumerationFeatureValues(){
		if(literals == null){
			literals = new ArrayList<String>();
			eLiterals=new HashMap<String,EEnumLiteral>();
			Profile p = ProfileApplier.getProfile(getElement(getSelectedObject()), getProfileName());
			if(p != null){
				Stereotype s = p.getOwnedStereotype(getStereotypeName(getElement(getSelectedObject())));
				if(s != null){
					EStructuralFeature feature = s.getDefinition().getEStructuralFeature(getAttributeName());
					if(feature != null && getFeature().getEType() instanceof EEnum){
						literals = new ArrayList<String>();
						EList<EEnumLiteral> tmp = ((EEnum) feature.getEType()).getELiterals();
						for(EEnumLiteral eEnumLiteral:tmp){
							literals.add(eEnumLiteral.getName());
							eLiterals.put(eEnumLiteral.getName(),eEnumLiteral);
						}
					}
				}
			}
		}
		return literals.toArray(new String[literals.size()]);
	}
	protected LabelProvider getLabelProvider(){
		return new LabelProvider(){
			public String getText(Object element){
				return element == null ? "" : ((EEnumLiteral) element).getName();
			}
		};
	}
	protected abstract String getAttributeName();
	protected abstract String getProfileName();
	protected abstract String getStereotypeName(Element e);
	protected Element getElement(EObject o){
		return (Element)o;
	}	@Override
	protected EStructuralFeature getFeature(EObject e){
		return e.eClass().getEStructuralFeature(getAttributeName());
	}
	@Override
	protected EObject getFeatureOwner(EObject e){
		Stereotype st = StereotypesHelper.getStereotype((Element) e, getStereotypeName((Element) e));
		if(st != null){
			return getElement(e).getStereotypeApplication(st);
		}else{
			return null;
		}
	}
	protected void maybeAppendCommand(EditingDomain editingDomain,CompoundCommand compoundCommand,Object selectedObject,EObject featureOwner,EStructuralFeature f,
			Object oldValue, Object newValue){
		Element element = getElement((EObject) selectedObject);
		compoundCommand.append(new LazySetTagValueCommand(element, getProfileName(), getStereotypeName(element), getAttributeName(), newValue));
	}}