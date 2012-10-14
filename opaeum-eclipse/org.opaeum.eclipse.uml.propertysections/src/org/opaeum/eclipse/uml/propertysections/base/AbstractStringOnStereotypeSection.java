package org.opaeum.eclipse.uml.propertysections.base;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.commands.LazySetTagValueCommand;
import org.opaeum.emf.extraction.StereotypesHelper;

public abstract class AbstractStringOnStereotypeSection extends AbstractStringPropertySection{
	public AbstractStringOnStereotypeSection(){
		super();
	}
	protected Element getElement(EObject o){
		return (Element) o;
	}
	protected EStructuralFeature getFeature(){
		return null;
	}
	protected abstract String getAttributeName();
	protected abstract String getProfileName();
	protected abstract String getStereotypeName(Element e);
	@Override
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
	}
}