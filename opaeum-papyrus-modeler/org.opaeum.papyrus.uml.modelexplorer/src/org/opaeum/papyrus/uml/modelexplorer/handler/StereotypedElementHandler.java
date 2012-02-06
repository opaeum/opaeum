package org.opaeum.papyrus.uml.modelexplorer.handler;

import java.util.Collection;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.utils.ServiceUtilsForActionHandlers;
import org.eclipse.papyrus.uml.modelexplorer.handler.CreateCommandHandler;
import org.eclipse.papyrus.uml.service.types.element.UMLElementTypes;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Profile;
import org.opaeum.eclipse.ProfileApplier;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public abstract class StereotypedElementHandler extends CreateCommandHandler implements IHandler{
	protected abstract String getStereotype();
	protected abstract String getProfile();
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException{
		final Object execute = super.execute(event);
		ServiceUtilsForActionHandlers util = new ServiceUtilsForActionHandlers();
		try{
			util.getTransactionalEditingDomain().getCommandStack().execute(new AbstractCommand(){
				@Override
				public boolean canExecute(){
					return true;
				}
				public void execute(){
					if(execute instanceof Collection){
						Collection<?> result = (Collection<?>) execute;
						Object next = result.iterator().next();
						if(next instanceof Activity){
							Activity a = (Activity) next;
							Profile applyProfile = ProfileApplier.applyProfile(a.getModel(), getProfile());
							a.applyStereotype(applyProfile.getOwnedStereotype(getStereotype()));
						}
					}
				}
				public void redo(){
				}
			});
		}catch(ServiceException e){
			e.printStackTrace();
		}
		return execute;
	}
}
