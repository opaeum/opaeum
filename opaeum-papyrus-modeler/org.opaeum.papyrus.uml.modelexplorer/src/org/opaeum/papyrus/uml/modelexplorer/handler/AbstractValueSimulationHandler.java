package org.opaeum.papyrus.uml.modelexplorer.handler;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValuePin;

public abstract class AbstractValueSimulationHandler extends EmfCreateCommandHandler{
	public AbstractValueSimulationHandler(){
		super();
	}
	@Override
	final protected EReference getFeature(){
		if(getSelectedElement() instanceof ValuePin){
			return UMLPackage.eINSTANCE.getValuePin_Value();
		}else{
			return UMLPackage.eINSTANCE.getSlot_Value();
		}
	}
}