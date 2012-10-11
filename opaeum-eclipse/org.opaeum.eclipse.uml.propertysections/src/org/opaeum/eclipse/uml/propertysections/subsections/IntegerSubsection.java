package org.opaeum.eclipse.uml.propertysections.subsections;

import org.opaeum.eclipse.uml.propertysections.base.AbstractIntegerSubsection;
import org.opaeum.eclipse.uml.propertysections.base.IMultiPropertySection;

public class IntegerSubsection extends AbstractIntegerSubsection<Integer>{
	public IntegerSubsection(IMultiPropertySection section){
		super(section);
	}
	@Override
	protected Integer getNewValue(){
		return getNewIntValue();
	}
	protected void populateControls(){
		if(getCurrentValue() == null){
			if(getDefaultValue() == null){
				getControl().setText("");
			}else{
				getControl().setText(getDefaultValue()+ "");
			}
		}else if(getCurrentValue() == -1){
			getControl().setText("*");
		}else{
			getControl().setText(getCurrentValue().toString());
		}
	}
}