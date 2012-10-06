package org.opaeum.uimodeler.modelexplorer.handler;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.LinkItem;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.utils.BusinessModelResolver;
import org.eclipse.papyrus.infra.core.utils.ServiceUtilsForActionHandlers;
import org.eclipse.papyrus.views.modelexplorer.CommandContext;
import org.eclipse.papyrus.views.modelexplorer.ICommandContext;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.opaeum.eclipse.OpaeumEclipsePlugin;

public abstract class AbstractCommandHandler extends AbstractHandler{
	protected abstract Command getCommand();
	protected EObject getSelectedElement(){
		EObject eObject = null;
		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		Object selection = (activeWorkbenchWindow != null) ? activeWorkbenchWindow.getSelectionService().getSelection() : null;
		if(selection != null){
			if(selection instanceof IStructuredSelection){
				IStructuredSelection structuredSelection = (IStructuredSelection) selection;
				selection = structuredSelection.getFirstElement();
			}
			if(selection instanceof IAdaptable){
				selection = ((IAdaptable) selection).getAdapter(EObject.class);
			}
			Object businessObject = BusinessModelResolver.getInstance().getBusinessModel(selection);
			if(businessObject instanceof EObject){
				eObject = (EObject) businessObject;
			}
		}
		return eObject;
	}
	protected List<EObject> getSelectedElements(){
		List<EObject> selectedEObjects = new ArrayList<EObject>();
		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		Object selection = (activeWorkbenchWindow != null) ? activeWorkbenchWindow.getSelectionService().getSelection() : null;
		if(selection != null){
			if(selection instanceof IStructuredSelection){
				IStructuredSelection structuredSelection = (IStructuredSelection) selection;
				for(Object current:structuredSelection.toArray()){
					if(current instanceof IAdaptable){
						selectedEObjects.add((EObject) ((IAdaptable) current).getAdapter(EObject.class));
					}
				}
			}else{
				if(selection instanceof IAdaptable){
					selectedEObjects.add((EObject) ((IAdaptable) selection).getAdapter(EObject.class));
				}
			}
		}
		return selectedEObjects;
	}
	protected ICommandContext getCommandContext(){
		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		Object selection = (activeWorkbenchWindow != null) ? activeWorkbenchWindow.getSelectionService().getSelection() : null;
		if(selection == null){
			return null;
		}
		if(selection instanceof IStructuredSelection){
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			selection = structuredSelection.getFirstElement();
		}
		EObject container = null;
		EReference reference = null;
		if(selection instanceof IAdaptable){
			container = (EObject) ((IAdaptable) selection).getAdapter(EObject.class);
			if(container == null){
				reference = (EReference) ((IAdaptable) selection).getAdapter(EReference.class);
				if((reference != null) && (selection instanceof LinkItem)){
					container = ((LinkItem) selection).getParent();
				}
			}
		}
		ICommandContext context = null;
		if(container != null){
			if(reference != null){
				context = new CommandContext(container, reference);
			}else{
				context = new CommandContext(container);
			}
		}
		return context;
	}
	public Object execute(ExecutionEvent event) throws ExecutionException{
		Command creationcommand = null;
		try{
			ServiceUtilsForActionHandlers util = new ServiceUtilsForActionHandlers();
			creationcommand = getCommand();
			util.getTransactionalEditingDomain().getCommandStack().execute(creationcommand);
			return creationcommand.getResult();
		}catch(ServiceException e){
			OpaeumEclipsePlugin.logError("Unexpected error while executing command.", e);
		}
		return null;
	}
	public boolean isEnabled(){
		return getCommand().canExecute();
	}
	public boolean isVisible(){
		return getCommand().canExecute();
	}
}
