package org.opaeum.papyrus.uml.modelexplorer.handler;

import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.metamodels.simulation.simulation.SimulationFactory;

public class SimulatedSlotHandler extends EmfCreateCommandHandler implements IHandler{

	@Override
	protected EReference getFeature(){
		return UMLPackage.eINSTANCE.getInstanceSpecification_Slot();
	}

	@Override
	protected EObject getNewObject(){
		return SimulationFactory.eINSTANCE.createSimulatingSlot();
	}
}
