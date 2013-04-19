package org.opaeum.eclipse.uml.propertysections.subsections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.UMLFactory;
import org.opaeum.eclipse.uml.propertysections.base.AbstractIntegerSubsection;
import org.opaeum.eclipse.uml.propertysections.base.IMultiPropertySection;

public class LiteralIntegerSubsection extends AbstractIntegerSubsection<LiteralUnlimitedNatural>{
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
	@Override
	public LiteralUnlimitedNatural getCurrentValue(EObject featureOwner){
		Object eGet = featureOwner.eGet(getFeature());
		if(eGet instanceof LiteralUnlimitedNatural){
			return (LiteralUnlimitedNatural) eGet;
		}else{
			return null;
		}
	}
	protected void populateControls(){
		if(getCurrentValue() == null){
			if(getDefaultValue() == null){
				getControl().setText("");
			}else{
				getControl().setText(defaultValue + "");
			}
		}else if(getCurrentValue().getValue() == -1){
			getControl().setText("*");
		}else{
			getControl().setText(getCurrentValue().stringValue());
		}
	}
}