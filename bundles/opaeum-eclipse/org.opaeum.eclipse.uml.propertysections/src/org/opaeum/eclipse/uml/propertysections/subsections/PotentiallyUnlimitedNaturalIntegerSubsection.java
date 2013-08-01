package org.opaeum.eclipse.uml.propertysections.subsections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralSpecification;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.UMLFactory;
import org.opaeum.eclipse.uml.propertysections.base.AbstractIntegerSubsection;
import org.opaeum.eclipse.uml.propertysections.base.IMultiPropertySection;

public class PotentiallyUnlimitedNaturalIntegerSubsection extends AbstractIntegerSubsection<LiteralSpecification>{
	public PotentiallyUnlimitedNaturalIntegerSubsection(IMultiPropertySection section){
		super(section);
	}
	@Override
	protected LiteralSpecification getNewValue(){
		int parseInt = getNewIntValue();
		if(parseInt == -1){
			LiteralUnlimitedNatural result = UMLFactory.eINSTANCE.createLiteralUnlimitedNatural();
			result.setValue(parseInt);
			return result;
		}else{
			LiteralInteger result = UMLFactory.eINSTANCE.createLiteralInteger();
			result.setValue(parseInt);
			return result;
		}
	}
	@Override
	public LiteralSpecification getCurrentValue(EObject featureOwner){
		Object eGet = featureOwner.eGet(getFeature());
		if(eGet instanceof LiteralUnlimitedNatural || eGet instanceof LiteralInteger){
			return (LiteralSpecification) eGet;
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
		}else if(getCurrentValue() instanceof LiteralUnlimitedNatural && getCurrentValue().unlimitedValue()  == -1){
			getControl().setText("*");
		}else{
			getControl().setText(getCurrentValue().stringValue());
		}
	}
}