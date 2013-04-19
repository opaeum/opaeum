package org.opaeum.uim.binding;

public class TableBindingCustom extends TableBindingImpl{
	public String getLastPropertyUuid() {
		return FieldBindingCustom.getLastePropertyUuid(this);
	}
}
