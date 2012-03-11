package org.opaeum.metamodels.uim.actionbar.diagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.papyrus.infra.gmfdiag.common.AbstractPapyrusGmfCreateDiagramCommandHandler;
import org.opaeum.uim.AbstractActionBar;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.EditorFactory;
import org.opaeum.uim.editor.EditorPage;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.AbstractActionBarEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.part.UimDiagramEditorPlugin;

public class CreateAbstractActionBarDiagramCommand extends AbstractPapyrusGmfCreateDiagramCommandHandler{
	AbstractActionBar editorPage;
	private String name;
	@Override
	protected String getDefaultDiagramName(){
		return "Action Bar";
	}
	@Override
	protected String getDiagramNotationID(){
		return AbstractActionBarEditPart.MODEL_ID;
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
		if(owner instanceof AbstractActionBar){
			editorPage = (AbstractActionBar) owner;
		}else if( owner instanceof AbstractEditor){
			editorPage =  EditorFactory.eINSTANCE.createEditorActionBar();
			((AbstractEditor) owner).getPages().add((EditorPage) editorPage);
			editorPage.setName("New Action Bar");
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
