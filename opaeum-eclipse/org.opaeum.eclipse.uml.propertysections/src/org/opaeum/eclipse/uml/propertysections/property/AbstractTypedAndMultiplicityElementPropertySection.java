package org.opaeum.eclipse.uml.propertysections.property;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.uml.propertysections.base.AbstractOpaeumPropertySection;

public abstract class AbstractTypedAndMultiplicityElementPropertySection extends AbstractOpaeumPropertySection{
	public AbstractTypedAndMultiplicityElementPropertySection(){
		super();
	}
	@Override
	protected void addListener(){
		super.addListener();
		if(getEObject() != null){
			TypedElement typedElement = getTypedElementFrom(getEObject());
			typedElement.eAdapters().add(getModelListener());
			if(typedElement instanceof MultiplicityElement){
				MultiplicityElement me = (MultiplicityElement) typedElement;
				if(me.getUpperValue() != null){
					me.getUpperValue().eAdapters().add(getModelListener());
				}
			}
		}
	}
	@Override
	protected void removeListener(){
		super.removeListener();
		if(getEObject() != null && getTypedElementFrom(getEObject()) != null){
			TypedElement typedElement = getTypedElementFrom(getEObject());
			typedElement.eAdapters().remove(getModelListener());
			if(typedElement instanceof MultiplicityElement){
				MultiplicityElement me = (MultiplicityElement) typedElement;
				if(me.getUpperValue() != null){
					me.getUpperValue().eAdapters().remove(getModelListener());
				}
			}
		}
	}
	protected List<TypedElement> getTypedElementList(){
		List<TypedElement> result = new ArrayList<TypedElement>();
		for(EObject eObject:getEObjectList()){
			result.add(getTypedElementFrom(eObject));
		}
		return result;
	}
	protected TypedElement getTypedElementFrom(EObject eObject){
		TypedElement p = (TypedElement) eObject;
		return p;
	}
}