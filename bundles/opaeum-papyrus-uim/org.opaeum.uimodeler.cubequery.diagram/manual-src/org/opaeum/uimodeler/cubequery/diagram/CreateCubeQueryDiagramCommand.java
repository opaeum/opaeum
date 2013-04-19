package org.opaeum.uimodeler.cubequery.diagram;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.papyrus.infra.gmfdiag.common.AbstractPapyrusGmfCreateDiagramCommandHandler;
import org.opaeum.uim.cube.CubeQuery;
import org.opaeum.uimodeler.cubequery.diagram.edit.parts.CubeQueryEditPart;
import org.opaeum.uimodeler.cubequery.diagram.part.UimCubeQueryDiagramEditorPlugin;

public class CreateCubeQueryDiagramCommand extends AbstractPapyrusGmfCreateDiagramCommandHandler{
	CubeQuery query;
	private String name;
	@Override
	public boolean isEnabled(){
		return true;
	}
	@Override
	protected String getDefaultDiagramName(){
		return "Cube Query";
	}
	@Override
	protected String getDiagramNotationID(){
		return CubeQueryEditPart.MODEL_ID;
	}
	@Override
	protected PreferencesHint getPreferenceHint(){
		return UimCubeQueryDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
	}
	protected void initializeDiagram(EObject diagram){
		if(diagram instanceof Diagram){
			Diagram diag = (Diagram) diagram;
			if(query != null){
				diag.setElement(query);
				createView(diag);
			}
			diag.setName(getName());
		}
	}
	private void createView(Diagram diagram){
		ViewService.getInstance().createView(Node.class, new EObjectAdapter(query), diagram, null, ViewUtil.APPEND, true, getPreferenceHint());
	}
	/**
	 * {@inheritDoc}
	 */
	protected void initializeModel(EObject owner){
	}
	@Override
	protected Diagram createDiagram(Resource diagramResource,EObject owner,String name){
		Diagram diagram = null;
		if(owner instanceof CubeQuery){
			query = (CubeQuery) owner;
		}
		diagram = ViewService.createDiagram(query, getDiagramNotationID(), getPreferenceHint());
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
