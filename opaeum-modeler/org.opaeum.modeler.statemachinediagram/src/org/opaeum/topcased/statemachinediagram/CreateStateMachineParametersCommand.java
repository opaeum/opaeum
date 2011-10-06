package org.opaeum.topcased.statemachinediagram;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.commands.CompoundCommand;
import org.topcased.modeler.commands.EMFtoGEFCommandWrapper;
import org.topcased.modeler.editor.Modeler;

public class CreateStateMachineParametersCommand extends CompoundCommand{
	private boolean initialized;
	public CreateStateMachineParametersCommand (CallBehaviorAction callBehaviorAction,Behavior behavior){
		initialized = callBehaviorAction != null && init(callBehaviorAction, behavior);
	}
	@Override
	public boolean canExecute(){
		return initialized && super.canExecute();
	}
	private boolean init(CallBehaviorAction callBehaviorAction,Behavior behavior){
		if(callBehaviorAction == null || behavior == null){
			return false;
		}
		IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if(workbenchWindow == null){
			return false;
		}
		if(workbenchWindow.getActivePage() != null && workbenchWindow.getActivePage().getActiveEditor() instanceof Modeler){
			Modeler editor = (Modeler) workbenchWindow.getActivePage().getActiveEditor();
			// Create the parameters
			Collection<Parameter> parameters = new ArrayList<Parameter>();
			for(InputPin pin:callBehaviorAction.getInputs()){
				Parameter param = UMLFactory.eINSTANCE.createParameter();
				param.setName(pin.getName());
				param.setType(pin.getType());
				param.setDirection(ParameterDirectionKind.IN_LITERAL);
				parameters.add(param);
			}
			for(OutputPin pin:callBehaviorAction.getOutputs()){
				Parameter param = UMLFactory.eINSTANCE.createParameter();
				param.setName(pin.getName());
				param.setType(pin.getType());
				param.setDirection(ParameterDirectionKind.OUT_LITERAL);
				parameters.add(param);
			}
			if(!parameters.isEmpty()){
				add(new EMFtoGEFCommandWrapper(AddCommand.create(editor.getEditingDomain(), behavior, UMLPackage.BEHAVIOR__OWNED_PARAMETER, parameters)));
			}
			return true;
		}
		return false;
	}
}
