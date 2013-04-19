package org.opaeum.uimodeler.perspective.diagram;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.papyrus.infra.gmfdiag.common.AbstractPapyrusGmfCreateDiagramCommandHandler;
import org.opaeum.uim.perspective.PerspectiveConfiguration;
import org.opaeum.uimodeler.perspective.diagram.edit.parts.PerspectiveConfigurationEditPart;
import org.opaeum.uimodeler.perspective.diagram.part.PerspectiveConfigurationDiagramEditorPlugin;

public class CreatePerspectiveDiagramCommand extends AbstractPapyrusGmfCreateDiagramCommandHandler{
	PerspectiveConfiguration perspective;
	private String name;
	@Override
	public boolean isEnabled(){
		return true;
	}
	@Override
	protected String getDefaultDiagramName(){
		return "Perspective";
	}
	@Override
	protected String getDiagramNotationID(){
		return PerspectiveConfigurationEditPart.MODEL_ID;
	}
	@Override
	protected PreferencesHint getPreferenceHint(){
		return PerspectiveConfigurationDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
	}
	protected void initializeDiagram(EObject diagram){
		if(diagram instanceof Diagram){
			Diagram diag = (Diagram) diagram;
			if(perspective != null){
				diag.setElement(perspective);
				createView(diag);
			}
			diag.setName(getName());
		}
	}
	private void createView(Diagram diagram){
		ViewService.getInstance().createView(Node.class, new EObjectAdapter(perspective), diagram, null, ViewUtil.APPEND, true, getPreferenceHint());
	}
	/**
	 * {@inheritDoc}
	 */
	protected void initializeModel(EObject owner){
	}
	@Override
	protected Diagram createDiagram(Resource diagramResource,EObject owner,String name){
		Diagram diagram = null;
		if(owner instanceof PerspectiveConfiguration){
			perspective = (PerspectiveConfiguration) owner;
		}
		diagram = ViewService.createDiagram(perspective, getDiagramNotationID(), getPreferenceHint());
		if(diagram != null){
			setName(name);
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
