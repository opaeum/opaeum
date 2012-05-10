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
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.AbstractEditorEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.part.UimDiagramEditorPlugin;

public class CreateAbstractActionBarDiagramCommand extends AbstractPapyrusGmfCreateDiagramCommandHandler{
	AbstractEditor editor;
	private String name;
	@Override
	protected String getDefaultDiagramName(){
		System.out.println("CreateAbstractActionBarDiagramCommand.getDefaultDiagramName()");
		return "Action Bar";
	}
	@Override
	protected String getDiagramNotationID(){
		System.out.println("CreateAbstractActionBarDiagramCommand.getDiagramNotationID()");
		return AbstractEditorEditPart.MODEL_ID;
	}
	@Override
	protected PreferencesHint getPreferenceHint(){
		System.out.println("CreateAbstractActionBarDiagramCommand.getPreferenceHint()");
		return UimDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
	}
	protected void initializeDiagram(EObject diagram){
		System.out.println("CreateAbstractActionBarDiagramCommand.initializeDiagram()");
		if(diagram instanceof Diagram){
			Diagram diag = (Diagram) diagram;
			if(editor != null){
				diag.setElement(editor);
				createView(diag);
			}
			diag.setName(getName());
		}
	}
	private void createView(Diagram diagram){
		System.out.println("CreateAbstractActionBarDiagramCommand.createView()");
		ViewService.getInstance().createView(Node.class, new EObjectAdapter(editor), diagram, null, ViewUtil.APPEND, true, getPreferenceHint());
	}
	/**
	 * {@inheritDoc}
	 */
	protected void initializeModel(EObject owner){
	}
	@Override
	protected Diagram createDiagram(Resource diagramResource,EObject owner,String name){
		System.out.println("CreateAbstractActionBarDiagramCommand.createDiagram()");
		Diagram diagram = null;
		editor = (AbstractEditor) owner;
		diagram = ViewService.createDiagram(editor, getDiagramNotationID(), getPreferenceHint());
		// create diagram
		if(diagram != null){
			setName(name);
			// initializeModel(owner);
			initializeDiagram(diagram);
			diagramResource.getContents().add(diagram);
		}
		return diagram;
	}
	protected void setName(String newName){
		System.out.println("CreateAbstractActionBarDiagramCommand.setName()");
		if(newName == null || newName.equals(name)){
			return;
		}
		name = newName;
	}
	protected String getName(){
		System.out.println("CreateAbstractActionBarDiagramCommand.getName()");
		return name;
	}
	public boolean isParentReassignable(){
		System.out.println("CreateAbstractActionBarDiagramCommand.isParentReassignable()");
		return false;
	}
}
