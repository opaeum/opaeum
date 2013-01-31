package org.opaeum.metamodel.activities.internal;

import org.eclipse.uml2.uml.INakedAction;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedPin;
import org.eclipse.uml2.uml.INakedTypedElement;

public class NakedPinImpl extends NakedObjectNodeImpl implements INakedPin{
	private static final long serialVersionUID = 771929693745616164L;
	INakedTypedElement linkedTypedElement;
	public INakedTypedElement getLinkedTypedElement(){
		return this.linkedTypedElement;
	}
	public void setLinkedTypedElement(INakedTypedElement linkedParameter){
		this.linkedTypedElement = linkedParameter;
	}
	@Override
	public INakedClassifier getNakedBaseType(){
		if(this.linkedTypedElement == null || super.getNakedBaseType()!=null){
			return super.getNakedBaseType();
		}else{
			return this.linkedTypedElement.getNakedBaseType();
		}
	}
	@Override
	public INakedAction getAction() {
		return (INakedAction) getOwnerElement();
	}
}