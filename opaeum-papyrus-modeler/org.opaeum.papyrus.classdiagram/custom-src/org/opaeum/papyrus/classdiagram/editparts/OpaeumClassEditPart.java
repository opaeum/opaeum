package org.opaeum.papyrus.classdiagram.editparts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.GradientStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.GradientData;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ClassEditPart;
import org.eclipse.papyrus.uml.diagram.common.figure.node.ClassifierFigure;
import org.eclipse.papyrus.uml.diagram.common.figure.node.IPapyrusNodeNamedElementFigure;
import org.eclipse.uml2.uml.Element;
import org.opaeum.emf.extraction.StereotypesHelper;

public final class OpaeumClassEditPart extends ClassEditPart{
	private final class OpaeumClassifierFIgure extends ClassifierFigure{
		private String imagePath;
		private OpaeumClassifierFIgure(String tagLabel){
			super(tagLabel);
		}
		public void setImagePath(String string){
			this.imagePath = string;
		}
		@Override
		protected void paintClientArea(Graphics graphics){
			Figure f = this;
			ImageUtil.paintBackgroundImage(graphics, f,imagePath);
			super.paintClientArea(graphics);
		}
	}
	private OpaeumClassifierFIgure imageFigure;
	OpaeumClassEditPart(View view){
		super(view);
	}
	protected IFigure createNodeShape(){
		this.imageFigure = new OpaeumClassifierFIgure("Business Entity");
		primaryShape = imageFigure;
		setGradient(new GradientData(FigureUtilities.RGBToInteger(ColorConstants.blue.getRGB()),
				FigureUtilities.RGBToInteger(ColorConstants.black.getRGB()), GradientStyle.VERTICAL));
		return primaryShape;
	}
	public Command getCommand(Request request){
		// HACK!!!! for bug in Papyrus 0.9
		if(request instanceof EditCommandRequestWrapper){
			EditCommandRequestWrapper w = (EditCommandRequestWrapper) request;
			if(w.getEditCommandRequest() instanceof DestroyElementRequest){
				return null;
			}
		}
		return super.getCommand(request);
	}
	@Override
	protected void handleNotificationEvent(Notification event){
		if(event.getNewValue() instanceof DynamicEObjectImpl){
			// stereotype;
			refreshVisuals();
		}
		super.handleNotificationEvent(event);
	}
	@Override
	protected void refreshVisuals(){
		super.refreshVisuals();
		if((IPapyrusNodeNamedElementFigure) getPrimaryShape() != null && resolveSemanticElement() != null){
			IPapyrusNodeNamedElementFigure l = (IPapyrusNodeNamedElementFigure) getPrimaryShape();
			Element element = (Element) getAdapter(Element.class);
			if(isBusinessRole(element)){
				imageFigure.setImagePath("images/BusinessRole.jpg");
				l.getTaggedLabel().setText("<<Business Role>>");
			}else if(StereotypesHelper.hasStereotype(element, "BusinessDocument")){
				imageFigure.setImagePath("images/BusinessDocument.jpg");
				l.getTaggedLabel().setText("<<Business Document>>");
			}else{
				imageFigure.setImagePath("images/BusinessEntity.jpg");
				l.getTaggedLabel().setText("<<Business Entity>>");
			}
		}
		// ((PapyrusNodeFigure) getPrimaryShape()).setIsUsingGradient(true);
	}
	private boolean isBusinessRole(Element element){
		return StereotypesHelper.hasStereotype(element, "BusinessRole");
	}
}