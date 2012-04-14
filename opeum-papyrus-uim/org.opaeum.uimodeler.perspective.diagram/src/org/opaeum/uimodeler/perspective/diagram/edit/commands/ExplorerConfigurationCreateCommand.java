package org.opaeum.uimodeler.perspective.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.opaeum.uim.perspective.ExplorerConfiguration;
import org.opaeum.uim.perspective.PerspectiveFactory;
import org.opaeum.uim.perspective.UimPerspective;

/**
 * @generated
 */
public class ExplorerConfigurationCreateCommand extends EditElementCommand{
	/**
	 * @generated
	 */
	private EClass eClass = null;
	/**
	 * @generated
	 */
	private EObject eObject = null;
	/**
	 * @generated
	 */
	public ExplorerConfigurationCreateCommand(CreateElementRequest req,EObject eObject){
		super(req.getLabel(), null, req);
		this.eObject = eObject;
		this.eClass = eObject != null ? eObject.eClass() : null;
	}
	/**
	 * @generated
	 */
	public static ExplorerConfigurationCreateCommand create(CreateElementRequest req,EObject eObject){
		return new ExplorerConfigurationCreateCommand(req, eObject);
	}
	/**
	 * @generated
	 */
	public ExplorerConfigurationCreateCommand(CreateElementRequest req){
		super(req.getLabel(), null, req);
	}
	/**
	 * FIXME: replace with setElementToEdit()
	 * @generated
	 */
	protected EObject getElementToEdit(){
		EObject container = ((CreateElementRequest) getRequest()).getContainer();
		if(container instanceof View){
			container = ((View) container).getElement();
		}
		if(container != null){
			return container;
		}
		return eObject;
	}
	/**
	 * @generated
	 */
	public boolean canExecute(){
		UimPerspective container = (UimPerspective) getElementToEdit();
		if(container.getExplorerConfiguration() != null){
			return false;
		}
		return true;
	}
	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,IAdaptable info) throws ExecutionException{
		ExplorerConfiguration newElement = PerspectiveFactory.eINSTANCE.createExplorerConfiguration();
		UimPerspective owner = (UimPerspective) getElementToEdit();
		owner.setExplorerConfiguration(newElement);
		doConfigure(newElement, monitor, info);
		((CreateElementRequest) getRequest()).setNewElement(newElement);
		return CommandResult.newOKCommandResult(newElement);
	}
	/**
	 * @generated
	 */
	protected void doConfigure(ExplorerConfiguration newElement,IProgressMonitor monitor,IAdaptable info) throws ExecutionException{
		IElementType elementType = ((CreateElementRequest) getRequest()).getElementType();
		ConfigureRequest configureRequest = new ConfigureRequest(getEditingDomain(), newElement, elementType);
		configureRequest.setClientContext(((CreateElementRequest) getRequest()).getClientContext());
		configureRequest.addParameters(getRequest().getParameters());
		ICommand configureCommand = elementType.getEditCommand(configureRequest);
		if(configureCommand != null && configureCommand.canExecute()){
			configureCommand.execute(monitor, info);
		}
	}
}
