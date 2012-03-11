package org.opaeum.uimodeler.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.papyrus.infra.gmfdiag.common.AbstractPapyrusGmfCreateDiagramCommandHandler;
import org.opaeum.uim.UserInterface;
import org.opaeum.uim.action.ActionFactory;
import org.opaeum.uim.action.OperationAction;
import org.opaeum.uim.action.OperationActionPopup;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.EditorFactory;
import org.opaeum.uim.editor.EditorPage;
import org.opaeum.uim.wizard.AbstractWizard;
import org.opaeum.uim.wizard.WizardFactory;
import org.opaeum.uim.wizard.WizardPage;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UserInterfaceEditPart;
import org.opaeum.uimodeler.userinterface.diagram.part.UimDiagramEditorPlugin;

public class CreateUserInterfaceDiagramCommand extends AbstractPapyrusGmfCreateDiagramCommandHandler{
	UserInterface editorPage;
	private String name;
	@Override
	protected String getDefaultDiagramName(){
		return "User Interface";
	}
	@Override
	protected String getDiagramNotationID(){
		return UserInterfaceEditPart.MODEL_ID;
	}
	@Override
	protected PreferencesHint getPreferenceHint(){
		return UimDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
	}
	protected void initializeDiagram(EObject diagram){
		if(diagram instanceof Diagram){
			Diagram diag = (Diagram) diagram;
			if(editorPage != null){
				diag.setElement(editorPage);
				createView(diag);
			}
			diag.setName(getName());
		}
	}
	private void createView(Diagram diagram){
		ViewService.getInstance().createView(Node.class, new EObjectAdapter(editorPage), diagram, null, ViewUtil.APPEND, true,
				getPreferenceHint());
	}
	/**
	 * {@inheritDoc}
	 */
	protected void initializeModel(EObject owner){
	}
	@Override
	protected Diagram createDiagram(Resource diagramResource,EObject owner,String name){
		Diagram diagram = null;
		if(owner instanceof EditorPage){
			editorPage = (EditorPage) owner;
		}else	if(owner instanceof WizardPage){
			editorPage = (WizardPage) owner;
		}else	if(owner instanceof OperationActionPopup){
			editorPage = (OperationActionPopup) owner;
		}else if( owner instanceof AbstractEditor){
			editorPage =  EditorFactory.eINSTANCE.createEditorPage();
			((AbstractEditor) owner).getPages().add((EditorPage) editorPage);
			editorPage.setName("NewPage");
		}else if( owner instanceof OperationAction){
			editorPage = ActionFactory.eINSTANCE.createOperationActionPopup();
			((OperationAction) owner).setPopup((OperationActionPopup) editorPage);
			editorPage.setName("NewPage");
		}else if( owner instanceof AbstractWizard){
			editorPage = WizardFactory.eINSTANCE.createWizardPage();
			((AbstractWizard) owner).getPages().add((WizardPage) editorPage);
			editorPage.setName("NewPage");
		}
		diagram = ViewService.createDiagram(editorPage, getDiagramNotationID(), getPreferenceHint());
		// create diagram
		if(diagram != null){
			setName(name);
//			initializeModel(owner);
			initializeDiagram(diagram);
			diagramResource.getContents().add(diagram);
		}
		return diagram;
	}
	protected void setName(String newName){
		if(newName == null || newName.equals(name)){
			return;
		}
		name = newName;
	}
	protected String getName(){
		return name;
	}
	public boolean isParentReassignable(){
		return false;
	}
}
