package org.opaeum.uimodeler.page.diagram.edit.commands;

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
import org.opaeum.uim.Page;
import org.opaeum.uim.panel.PanelFactory;
import org.opaeum.uim.panel.VerticalPanel;

/**
 * @generated
 */
public class VerticalPanelCreateCommand extends EditElementCommand {

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
	public VerticalPanelCreateCommand(CreateElementRequest req, EObject eObject) {
		super(req.getLabel(), null, req);
		this.eObject = eObject;
		this.eClass = eObject != null ? eObject.eClass() : null;
	}

	/**
	 * @generated
	 */
	public static VerticalPanelCreateCommand create(CreateElementRequest req,
			EObject eObject) {
		return new VerticalPanelCreateCommand(req, eObject);
	}

	/**
	 * @generated
	 */
	public VerticalPanelCreateCommand(CreateElementRequest req) {
		super(req.getLabel(), null, req);
	}

	/**
	 * FIXME: replace with setElementToEdit()
	 * @generated
	 */
	protected EObject getElementToEdit() {

		EObject container = ((CreateElementRequest) getRequest())
				.getContainer();
		if (container instanceof View) {
			container = ((View) container).getElement();
		}
		if (container != null) {
			return container;
		}
		return eObject;
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		Page container = (Page) getElementToEdit();
		if (container.getPanel() != null) {
			return false;
		}

		return true;

	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {

		VerticalPanel newElement = PanelFactory.eINSTANCE.createVerticalPanel();

		Page owner = (Page) getElementToEdit();
		owner.setPanel(newElement);

		doConfigure(newElement, monitor, info);

		((CreateElementRequest) getRequest()).setNewElement(newElement);
		return CommandResult.newOKCommandResult(newElement);
	}

	/**
	 * @generated
	 */
	protected void doConfigure(VerticalPanel newElement,
			IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		IElementType elementType = ((CreateElementRequest) getRequest())
				.getElementType();
		ConfigureRequest configureRequest = new ConfigureRequest(
				getEditingDomain(), newElement, elementType);
		configureRequest.setClientContext(((CreateElementRequest) getRequest())
				.getClientContext());
		configureRequest.addParameters(getRequest().getParameters());
		ICommand configureCommand = elementType
				.getEditCommand(configureRequest);
		if (configureCommand != null && configureCommand.canExecute()) {
			configureCommand.execute(monitor, info);
		}
	}

}
