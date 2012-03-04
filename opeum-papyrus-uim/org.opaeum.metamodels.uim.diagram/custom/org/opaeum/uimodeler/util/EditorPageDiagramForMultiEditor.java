package org.opaeum.uimodeler.util;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.papyrus.infra.core.editor.BackboneException;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.opaeum.uim.diagram.part.UimDiagramEditor;
import org.opaeum.uim.diagram.part.UimDiagramEditorPlugin;

public class EditorPageDiagramForMultiEditor extends UimDiagramEditor {

	private static final String DIAG_IMG_PATH = "icons/obj16/EditorDiagramFile.gif";

	private static final ImageDescriptor DIAG_IMG_DESC = UimDiagramEditorPlugin.getBundledImageDescriptor(DIAG_IMG_PATH);

	/** The editor splitter. */
	private Composite splitter;

	/**
	 * Constructor for SashSystem v2. Context and required objects are retrieved from the
	 * ServiceRegistry.
	 * 
	 * @throws BackboneException
	 * @throws ServiceException
	 * 
	 */
	public EditorPageDiagramForMultiEditor(ServicesRegistry servicesRegistry, Diagram diagram) throws BackboneException, ServiceException {
		super(servicesRegistry, diagram);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);
		setPartName(getDiagram().getName());
		setTitleImage(DIAG_IMG_DESC.createImage());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setInput(IEditorInput input) {
		try {
			// Provide an URI with fragment in order to reuse the same Resource
			// and set the diagram to the fragment.
			URIEditorInput uriInput = new URIEditorInput(EcoreUtil.getURI(getDiagram()));
			doSetInput(uriInput, true);
		} catch (CoreException x) {
			String title = "Problem opening";
			String msg = "Cannot open input element:";
			Shell shell = getSite().getShell();
			ErrorDialog.openError(shell, title, msg, x.getStatus());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createGraphicalViewer(Composite parent) {
		splitter = parent;
		super.createGraphicalViewer(parent);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFocus() {
		splitter.setFocus();
		super.setFocus();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getEditingDomainID() {
		return "org.eclipse.papyrus.uml.diagram.clazz.EditingDomain";
	}
}
