package org.opaeum.papyrus.classdiagram.editparts;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.clazz.custom.policies.itemsemantic.CustomInterfaceItemSemanticEditPolicy;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.InterfaceEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.providers.UMLElementTypes;
import org.eclipse.papyrus.uml.diagram.common.figure.node.IPapyrusNodeNamedElementFigure;
import org.eclipse.papyrus.uml.diagram.common.figure.node.InterfaceFigure;
import org.eclipse.uml2.uml.Element;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.papyrus.classdiagram.custom.command.CAssociationClassCreateCommand;
import org.opaeum.papyrus.common.ImageUtil;

public final class OpaeumInterfaceEditPart extends InterfaceEditPart{
	private final class OpaeumInterfaceFigure extends InterfaceFigure{
		private String imagePath;
		private OpaeumInterfaceFigure(){
			super();
		}
		public void setImagePath(String string){
			this.imagePath = string;
		}
		@Override
		protected void paintClientArea(Graphics graphics){
			super.paintClientArea(graphics);
			if(imagePath != null){
				ImageUtil.paintBackgroundImage(graphics, this, imagePath);
			}
		}
	}
	private OpaeumInterfaceFigure imageFigure;
	OpaeumInterfaceEditPart(View view){
		super(view);
	}
	@Override
	protected void createDefaultEditPolicies(){
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new CustomInterfaceItemSemanticEditPolicy(){
			protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req) {
				if(UMLElementTypes.AssociationClass_4017 == req.getElementType()) {
					return getGEFWrapper(new CAssociationClassCreateCommand(req, req.getSource(), req.getTarget()));
				}
				return super.getCompleteCreateRelationshipCommand(req);
			}

		});

	}
	protected IFigure createNodeShape(){
		this.imageFigure = new OpaeumInterfaceFigure();
		primaryShape = imageFigure;
		return primaryShape;
	}
	public Command getCommand(Request request){
		// HACK!!!! for bug in Papyrus 0.9
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
			if(StereotypesHelper.hasStereotype(element, "BusinessService")){
				imageFigure.setImagePath("images/BusinessService.svg");
				l.getTaggedLabel().setText("<<Business Service>>");
			}
		}
		// ((PapyrusNodeFigure) getPrimaryShape()).setIsUsingGradient(true);
	}
	private boolean isBusinessRole(Element element){
		return StereotypesHelper.hasStereotype(element, "BusinessRole");
	}
}