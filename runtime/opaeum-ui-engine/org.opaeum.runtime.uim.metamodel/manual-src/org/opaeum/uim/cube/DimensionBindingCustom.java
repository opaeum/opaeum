package org.opaeum.uim.cube;

import org.opaeum.uim.binding.FieldBindingCustom;

public class DimensionBindingCustom extends DimensionBindingImpl{
	public String getLastPropertyUuid(){
		return FieldBindingCustom.getLastePropertyUuid(this);
	}
}
