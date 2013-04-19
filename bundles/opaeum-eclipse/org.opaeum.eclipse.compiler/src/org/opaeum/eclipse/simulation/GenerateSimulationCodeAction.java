package org.opaeum.eclipse.simulation;


import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.opaeum.eclipse.javasync.AbstractDirectoryReadingAction;
import org.opaeum.metamodels.simulation.simulation.SimulationModel;

public class GenerateSimulationCodeAction extends AbstractDirectoryReadingAction{
	public GenerateSimulationCodeAction(IStructuredSelection selection2){
		super(selection2, "Generate Simulation Code");
	}
	protected GenerateSimulationCodeAction(IStructuredSelection selection2,String string){
		super(selection2, string);
	}
	@Override
	public void run(){

		Object firstElement = selection.getFirstElement();
		final SimulationModel model;
		if(firstElement instanceof SimulationModel){
			model = (SimulationModel) firstElement;
		}else if(firstElement instanceof IAdaptable){
			model = (SimulationModel) ((IAdaptable) firstElement).getAdapter(EObject.class);
		}else{
			model = null;
		}
		if(model != null){
			new GenerateSimulationJob("Generating Simulation Code", model).schedule();
		}
	}
}
