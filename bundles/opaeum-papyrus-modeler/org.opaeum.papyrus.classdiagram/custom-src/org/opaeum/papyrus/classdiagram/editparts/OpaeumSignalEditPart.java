package org.opaeum.papyrus.classdiagram.editparts;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.SignalEditPart;
import org.eclipse.papyrus.uml.diagram.common.figure.node.IPapyrusNodeNamedElementFigure;
import org.eclipse.papyrus.uml.diagram.common.figure.node.SignalFigure;
import org.eclipse.uml2.uml.Element;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.papyrus.common.ImageUtil;

public final class OpaeumSignalEditPart extends SignalEditPart{
	private final class OpaeumClassifierFIgure extends SignalFigure{
		private String imagePath;
		private OpaeumClassifierFIgure(){
			super();
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
	OpaeumSignalEditPart(View view){
		super(view);
	}
	protected IFigure createNodeShape(){
		this.imageFigure = new OpaeumClassifierFIgure();
		primaryShape = imageFigure;
		return primaryShape;
	}
	public Command getCommand(Request request){
//		// HACK!!!! for bug in Papyrus 0.9
//		if(request instanceof EditCommandRequestWrapper){
//			EditCommandRequestWrapper w = (EditCommandRequestWrapper) request;
//			if(w.getEditCommandRequest() instanceof DestroyElementRequest){
//				return null;
//			}
//		}
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
			if(StereotypesHelper.hasStereotype(element, "Notification")){
				imageFigure.setImagePath("images/BusinessNotification.svg");
				l.getTaggedLabel().setText("<<Business Notification>>");
			}
		}
	}
}