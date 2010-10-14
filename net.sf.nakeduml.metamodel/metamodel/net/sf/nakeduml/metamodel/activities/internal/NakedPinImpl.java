package net.sf.nakeduml.metamodel.activities.internal;

import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;

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
}
