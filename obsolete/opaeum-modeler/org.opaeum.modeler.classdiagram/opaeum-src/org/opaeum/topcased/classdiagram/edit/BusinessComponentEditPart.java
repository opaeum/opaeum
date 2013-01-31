package org.opaeum.topcased.classdiagram.edit;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.opaeum.topcased.classdiagram.figure.Gradient;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.uml.classdiagram.edit.ClassEditPart;
import org.topcased.modeler.uml.classdiagram.figures.ClassFigure;

public class BusinessComponentEditPart extends ClassEditPart{
	private final class BusinessComponentFigure extends ClassFigure{
		@Override
		public void paintChildren(Graphics graphics){
			Gradient.paintChildren(graphics, this);
			super.paintChildren(graphics);
		}
	}
	public BusinessComponentEditPart(GraphNode obj){
		super(obj);
	}
	protected void refreshHeaderLabel(){
		super.refreshHeaderLabel();
		ClassFigure fig = (ClassFigure) getFigure();
		ComposedLabel lbl = (ComposedLabel) fig.getLabel();
		lbl.setPrefix("<<businessComponent>>");
//		lbl.setPrefixIcon(OpaeumPlugin.getDefault().getImageRegistry().get("Actor"));
	}
	@Override
	protected IFigure createFigure(){
		BusinessComponentFigure f = new BusinessComponentFigure();
		return f;
	}
	protected void createEditPolicies(){
		super.createEditPolicies();
		EditPartUtil.installEditPolicies(this);
	}

}