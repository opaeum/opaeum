package org.opaeum.eclipse.uml.propertysections.base;

import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.UMLFactory;

public class LiteralIntegerSubsection extends AbstractIntegerSubsection <LiteralUnlimitedNatural>{
	public LiteralIntegerSubsection(IMultiPropertySection section){
		super(section);
	}
	@Override
	protected LiteralUnlimitedNatural getNewValue(){
		int parseInt = getNewIntValue();
		LiteralUnlimitedNatural result = UMLFactory.eINSTANCE.createLiteralUnlimitedNatural();
		result.setValue(parseInt);
		return result;
	}
	protected void populateControls(){
		if(getCurrentValue()==null){
			if(getDefaultValue()==null){
				getControl().setText("");
			}else{
				getControl().setText(defaultValue+"");
			}
		}else if(getCurrentValue().getValue()==-1){
			getControl().setText("*");
		}else{
			getControl().setText(getCurrentValue().stringValue());
		}
		
	}

}