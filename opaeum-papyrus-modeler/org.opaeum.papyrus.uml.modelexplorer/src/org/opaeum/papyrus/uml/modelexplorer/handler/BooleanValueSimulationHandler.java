package org.opaeum.papyrus.uml.modelexplorer.handler;

import org.eclipse.emf.ecore.EObject;
import org.opaeum.metamodels.simulation.simulation.SimulationFactory;

public class BooleanValueSimulationHandler extends AbstractValueSimulationHandler{
	@Override
	protected EObject getNewObject(){
		return SimulationFactory.eINSTANCE.createBooleanValueSimulation();
	}
}
