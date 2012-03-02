package org.opaeum.topcased.propertysections.property;

import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.TypedElement;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public abstract class AbstractTypedAndMultiplicityElementPropertySection extends AbstractTabbedPropertySection{
	public AbstractTypedAndMultiplicityElementPropertySection(){
		super();
	}
	@Override
	protected void addListener(){
		super.addListener();
		if(getEObject() != null){
			TypedElement typedElement = getTypedElement();
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
		if(getEObject() != null && getTypedElement()!=null){
			TypedElement typedElement = getTypedElement();
			typedElement.eAdapters().remove(getModelListener());
			if(typedElement instanceof MultiplicityElement){
				MultiplicityElement me = (MultiplicityElement) typedElement;
				if(me.getUpperValue() != null){
					me.getUpperValue().eAdapters().remove(getModelListener());
				}
			}
		}
	}
	protected TypedElement getTypedElement(){
		TypedElement p = (TypedElement) getEObject();
		return p;
	}
}