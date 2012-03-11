package org.opaeum.uimodeler.modelexplorer.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.utils.ServiceUtilsForActionHandlers;
import org.eclipse.papyrus.infra.services.edit.service.ElementEditServiceUtils;
import org.eclipse.papyrus.infra.services.edit.service.IElementEditService;
import org.eclipse.papyrus.views.modelexplorer.ICommandContext;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerView;
import org.eclipse.papyrus.views.modelexplorer.NavigatorUtils;

public abstract class CreateCommandHandler extends AbstractCommandHandler{
	protected abstract EReference getFeature();
	protected abstract EObject getNewObject();
	private Command createCommand;
	protected Command buildCommand() throws ServiceException{
		ICommandContext commandContext = getCommandContext();
		if(commandContext == null){
			return UnexecutableCommand.INSTANCE;
		}
		EObject container = commandContext.getContainer();
		EReference reference = commandContext.getReference();
		IElementEditService provider = ElementEditServiceUtils.getCommandProvider(container);
		if(provider == null){
			return UnexecutableCommand.INSTANCE;
		}
	  TransactionalEditingDomain domain = ServiceUtilsForActionHandlers.getInstance().getTransactionalEditingDomain();
		Command emfCommand = AddCommand.create(domain, getSelectedElement(), getFeature(), getNewObject());
		return emfCommand;
	}
	protected Command getCommand(){
			try{
				createCommand = buildCommand();
			}catch(ServiceException e){
				e.printStackTrace();
			}
		return createCommand;
	}
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException{
		Object result = super.execute(event);
		EObject newElement = null;
		if(result instanceof Collection<?>){
			Collection<?> results = (Collection<?>) result;
			if((!results.isEmpty()) && (results.toArray()[0] instanceof EObject)){
				newElement = (EObject) results.toArray()[0];
			}
		}
		ModelExplorerView modelExplorerView = null;
		ModelExplorerPageBookView bookViewPart = (ModelExplorerPageBookView) NavigatorUtils
				.findViewPart("org.eclipse.papyrus.views.modelexplorer.modelexplorer"); //$NON-NLS-0$
		if(bookViewPart != null){
			modelExplorerView = (ModelExplorerView) ((ModelExplorerPageBookView) bookViewPart).getActiveView();
		}
		// Set selection on new element in the model explorer
		if((modelExplorerView != null) && (newElement != null)){
			List<EObject> semanticElementList = new ArrayList<EObject>();
			semanticElementList.add(newElement);
			modelExplorerView.revealSemanticElement(semanticElementList);
		}
		return result;
	}
	@Override
	public boolean isVisible(){
		return super.isVisible();
	}
}
