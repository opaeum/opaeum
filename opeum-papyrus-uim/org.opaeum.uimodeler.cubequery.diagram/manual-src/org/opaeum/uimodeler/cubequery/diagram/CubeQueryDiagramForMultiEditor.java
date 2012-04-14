package org.opaeum.uimodeler.cubequery.diagram;

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
import org.opaeum.uimodeler.cubequery.diagram.part.UimCubeQueryDiagramEditorPlugin;
import org.opaeum.uimodeler.cubequery.diagram.part.UimDiagramEditor;

public class CubeQueryDiagramForMultiEditor extends UimDiagramEditor{
	private static final String DIAG_IMG_PATH = "icons/obj16/CubeQueryDiagramFile.gif";
	private static final ImageDescriptor DIAG_IMG_DESC = UimCubeQueryDiagramEditorPlugin.getBundledImageDescriptor(DIAG_IMG_PATH);
	private Composite splitter;
	public CubeQueryDiagramForMultiEditor(ServicesRegistry servicesRegistry,Diagram diagram) throws BackboneException,ServiceException{
		super(servicesRegistry, diagram);
	}
	@Override
	public void init(IEditorSite site,IEditorInput input) throws PartInitException{
		super.init(site, input);
		setPartName(getDiagram().getName());
		setTitleImage(DIAG_IMG_DESC.createImage());
	}
	@Override
	public void setInput(IEditorInput input){
		try{
			URIEditorInput uriInput = new URIEditorInput(EcoreUtil.getURI(getDiagram()));
			doSetInput(uriInput, true);
		}catch(CoreException x){
			String title = "Problem opening";
			String msg = "Cannot open input element:";
			Shell shell = getSite().getShell();
			ErrorDialog.openError(shell, title, msg, x.getStatus());
		}
	}
	@Override
	protected void createGraphicalViewer(Composite parent){
		splitter = parent;
		super.createGraphicalViewer(parent);
	}
	@Override
	public void setFocus(){
		splitter.setFocus();
		super.setFocus();
	}
	@Override
	public String getEditingDomainID(){
		return "org.opaeum.uimodeler.cubequery.diagram.EditingDomain";
	}
}
