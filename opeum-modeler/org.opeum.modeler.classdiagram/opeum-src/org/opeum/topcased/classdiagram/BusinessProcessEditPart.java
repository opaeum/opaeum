package org.opeum.topcased.classdiagram;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.opeum.topcased.EditPartUtil;
import org.opeum.topcased.classdiagram.figure.Gradient;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.editor.ModelerGraphicalViewer;
import org.topcased.modeler.uml.classdiagram.edit.ClassEditPart;
import org.topcased.modeler.uml.classdiagram.figures.ClassFigure;

public class BusinessProcessEditPart extends ClassEditPart{
	public BusinessProcessEditPart(GraphNode obj){
		super(obj);
	}
	@Override
	protected IFigure createFigure(){
		return new ClassFigure(){
			@Override
			public void paintChildren(Graphics graphics){
				Gradient.paintChildren(graphics, this);
				super.paintChildren(graphics);
			}
		};
	}
	@Override
	protected IAction createChangeDiagramAction(EObject modelObject){
		return EditPartUtil.createDiagramAction(modelObject,modelObject, ((ModelerGraphicalViewer) getViewer()).getModelerEditor(), "org.topcased.modeler.uml.activitydiagram.bpm");
	}
}