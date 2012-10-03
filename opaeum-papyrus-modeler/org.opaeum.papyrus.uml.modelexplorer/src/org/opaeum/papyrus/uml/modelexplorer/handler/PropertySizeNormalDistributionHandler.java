package org.opaeum.papyrus.uml.modelexplorer.handler;

import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.metamodels.simulation.simulation.SimulationFactory;
import org.opaeum.metamodels.simulation.simulation.SimulationPackage;

public class PropertySizeNormalDistributionHandler extends EmfCreateCommandHandler implements IHandler{

	@Override
	protected EReference getFeature(){
		return SimulationPackage.eINSTANCE.getSimulatingSlot_SizeDistribution();
	}

	@Override
	protected EObject getNewObject(){
		return SimulationFactory.eINSTANCE.createNormalDistribution();
	}
}
