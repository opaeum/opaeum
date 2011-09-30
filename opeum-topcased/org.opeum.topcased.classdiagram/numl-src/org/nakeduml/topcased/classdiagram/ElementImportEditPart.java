package org.opeum.topcased.classdiagram;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.VisibilityKind;
import org.topcased.draw2d.figures.Label;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.EdgeObject;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.edit.policies.EdgeObjectOffsetEditPolicy;
import org.topcased.modeler.figures.IEdgeObjectFigure;
import org.topcased.modeler.uml.alldiagram.AllEdgeObjectConstants;
import org.topcased.modeler.uml.alldiagram.figures.PackageImportFigure;
import org.topcased.modeler.uml.alldiagram.preferences.AllDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

public class ElementImportEditPart extends EMFGraphEdgeEditPart{
	public ElementImportEditPart(GraphEdge model){
		super(model);
	}
	protected void createEditPolicies(){
		super.createEditPolicies();
		installEditPolicy(ModelerEditPolicyConstants.EDGE_OBJECTS_OFFSET_EDITPOLICY, new EdgeObjectOffsetEditPolicy());
	}
	protected IFigure createFigure(){
		PackageImportFigure connection = new PackageImportFigure();
		createTargetDecoration(connection);
		return connection;
	}
	private void createTargetDecoration(PolylineConnection connection){
		PolylineDecoration decoration = new PolylineDecoration();
		decoration.setScale(10, 5);
		connection.setTargetDecoration(decoration);
	}
	public IEdgeObjectFigure getEdgeObjectFigure(EdgeObject edgeObject){
		if(AllEdgeObjectConstants.VISIBILITY_EDGE_OBJECT_ID.equals(edgeObject.getId())){
			return ((PackageImportFigure) getFigure()).getVisibilityEdgeObjectFigure();
		}
		return null;
	}
	protected void refreshEdgeObjects(){
		super.refreshEdgeObjects();
		updateVisibilityLabel();
	}
	private void updateVisibilityLabel(){
		Label lbl = (Label) ((PackageImportFigure) getFigure()).getVisibilityEdgeObjectFigure();
		VisibilityKind visibilityKind = ((ElementImport) getEObject()).getVisibility();
		if(VisibilityKind.PUBLIC == visibilityKind.getValue()){
			lbl.setText("<<import>>");
		}else if(VisibilityKind.PRIVATE == visibilityKind.getValue()){
			lbl.setText("<<access>>");
		}else{
			lbl.setText("");
		}
	}
	protected String getPreferenceDefaultRouter(){
		return getPreferenceStore().getString(AllDiagramPreferenceConstants.PACKAGEIMPORT_EDGE_DEFAULT_ROUTER);
	}
	protected Color getPreferenceDefaultForegroundColor(){
		String preferenceForeground = getPreferenceStore().getString(AllDiagramPreferenceConstants.PACKAGEIMPORT_EDGE_DEFAULT_FOREGROUND_COLOR);
		if(preferenceForeground.length() != 0){
			return Utils.getColor(preferenceForeground);
		}
		return null;
	}
	protected Font getPreferenceDefaultFont(){
		String preferenceFont = getPreferenceStore().getString(AllDiagramPreferenceConstants.PACKAGEIMPORT_EDGE_DEFAULT_FONT);
		if(preferenceFont.length() != 0){
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;
	}
}