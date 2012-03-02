package org.opaeum.metamodel.activities.internal;

import java.util.Collection;

import org.opaeum.metamodel.activities.INakedValuePin;
import org.opaeum.metamodel.activities.ObjectNodeType;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedValueSpecification;

public class NakedValuePinImpl extends NakedInputPinImpl implements INakedValuePin {
	private static final long serialVersionUID = 9125417030702683972L;
	private INakedValueSpecification value;

	public INakedValueSpecification getValue() {
		return this.value;
	}
	@Override
	public void addOwnedElement(INakedElement element) {
		super.addOwnedElement(element);
	}
	@Override
	public boolean hasValidInput() {
		if(getValue()==null || getValue().getValue()==null){
			return false;
		}else if(getValue().isOclValue()){
			return getValue().isValidOclValue();
		}else{
			return true;
		}
	}

	public void setValue(INakedValueSpecification value) {
		this.value = value;
	}

	@Override
	public String getMetaClass() {
		return "pin";
	}

	@Override
	public ObjectNodeType getObjectNodeType() {
		return ObjectNodeType.VALUE_PIN;
	}

	@Override
	public Collection<INakedElement> getOwnedElements() {
		Collection<INakedElement> out = super.getOwnedElements();
		if (this.value != null) {
			out.add(value);
		}
		return out;
	}
}
