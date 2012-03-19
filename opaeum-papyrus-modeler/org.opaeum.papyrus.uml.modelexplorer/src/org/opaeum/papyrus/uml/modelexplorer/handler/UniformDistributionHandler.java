package org.opaeum.papyrus.uml.modelexplorer.handler;

import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.ecore.EObject;
import org.opaeum.metamodels.simulation.simulation.SimulationFactory;

public class UniformDistributionHandler extends AbstractValueSimulationHandler implements IHandler{
	@Override
	protected EObject getNewObject(){
		return SimulationFactory.eINSTANCE.createUniformDistribution();
	}
}
