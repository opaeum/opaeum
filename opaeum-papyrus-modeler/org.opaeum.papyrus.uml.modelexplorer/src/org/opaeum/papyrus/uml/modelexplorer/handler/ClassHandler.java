package org.opaeum.papyrus.uml.modelexplorer.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.common.command.Command;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.papyrus.commands.wrappers.GMFtoEMFCommandWrapper;
import org.eclipse.papyrus.uml.service.types.element.UMLElementTypes;

public class ClassHandler extends CreateCommandHandler implements IHandler {

	@Override
	protected Command buildCommand(){
		System.out.println("ClassHandler.buildCommand()");
		// TODO Auto-generated method stub
		return super.buildCommand();
	}

	@Override
	protected Command getCommand(){
		// TODO Auto-generated method stub
		System.out.println("ClassHandler.getCommand()");
		
		return super.getCommand();
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException{
		System.out.println("ClassHandler.execute()");
		// TODO Auto-generated method stub
		return super.execute(event);
	}

	@Override
	public boolean isVisible(){
		System.out.println("ClassHandler.isVisible()");
		// TODO Auto-generated method stub
		return super.isVisible();
	}

	@Override
	public boolean isEnabled(){
		System.out.println("ClassHandler.isEnabled()");
		// TODO Auto-generated method stub
		// Prepare and store the delete command only when trying to find out 
		// whether the command is enabled or not.
		// This assumes the isEnabled() method is called each time the contextual menu
		// opens. 
		
		// Temporary (customizable implementation to be provided) filter to avoid all
		// creation command to be visible (avoid to large set of possible children).
		if(!filter.getVisibleCommands().contains(getElementTypeToCreate())) {
			return false;
		}

		GMFtoEMFCommandWrapper command = (GMFtoEMFCommandWrapper) getCommand();
		
		boolean canExecute = command.canExecute();
		System.out.println(command.getGMFCommand().getClass());
		return canExecute;

	}

	protected IElementType getElementTypeToCreate() {
		System.out.println("ClassHandler.getElementTypeToCreate()");
		return UMLElementTypes.CLASS;
	}
}
