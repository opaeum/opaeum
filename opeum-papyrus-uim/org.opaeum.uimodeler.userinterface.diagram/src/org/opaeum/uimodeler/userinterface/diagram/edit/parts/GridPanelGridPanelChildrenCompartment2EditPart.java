package org.opaeum.uimodeler.userinterface.diagram.edit.parts;

import javax.swing.text.StyleConstants.ColorConstants;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutAnimator;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.figures.ShapeCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.DuplicatePasteEditPolicy;
import org.eclipse.swt.widgets.Composite;
import org.opaeum.uimodeler.common.figures.UimFigureUtil;
import org.opaeum.uimodeler.userinterface.diagram.edit.policies.GridPanelGridPanelChildrenCompartment2CanonicalEditPolicy;
import org.opaeum.uimodeler.userinterface.diagram.edit.policies.GridPanelGridPanelChildrenCompartment2ItemSemanticEditPolicy;
import org.opaeum.uimodeler.userinterface.diagram.part.Messages;

/**
 * @generated
 */
public class GridPanelGridPanelChildrenCompartment2EditPart extends ShapeCompartmentEditPart{
	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 7005;
	/**
	 * @generated
	 */
	public GridPanelGridPanelChildrenCompartment2EditPart(View view){
		super(view);
	}
	/**
	 * @generated
	 */
	public String getCompartmentName(){
		return Messages.GridPanelGridPanelChildrenCompartment2EditPart_title;
	}
	/**
	 * @generated NOT
	 */
	public IFigure createFigure(){
		final Composite comp = UimFigureUtil.getNearestComposite(getParent());
		ShapeCompartmentFigure scf = new ShapeCompartmentFigure(getCompartmentName(), getMapMode()){
			@Override
			public Rectangle getBounds(){
				return super.getBounds();
			}
			@Override
			protected void layout(){
				super.layout();
				Rectangle bnds = super.getBounds().getCopy();
				//				bnds.x+=1;
				//				bnds.y+=1;
				//				bnds.width-=16;
				//				bnds.height-=16;
				scrollPane.setBounds(bnds);
			}
		};
		comp.setData(UimFigureUtil.FIGURE, scf);
		scf.getContentPane().setLayoutManager(getLayoutManager());
		scf.getContentPane().addLayoutListener(LayoutAnimator.getDefault());
		ResizableCompartmentFigure result = (ResizableCompartmentFigure) scf;
		result.setTitleVisibility(false);
		result.setBackgroundColor(org.eclipse.draw2d.ColorConstants.blue);
		return result;
	}
	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies(){
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new GridPanelGridPanelChildrenCompartment2ItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CreationEditPolicy());
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new DragDropEditPolicy());
		installEditPolicy(DuplicatePasteEditPolicy.PASTE_ROLE, new DuplicatePasteEditPolicy());
		//in Papyrus diagrams are not strongly synchronised
		//installEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CANONICAL_ROLE, new org.opaeum.uimodeler.userinterface.diagram.edit.policies.GridPanelGridPanelChildrenCompartment2CanonicalEditPolicy());
	}
	/**
	 * @generated
	 */
	protected void setRatio(Double ratio){
		if(getFigure().getParent().getLayoutManager() instanceof ConstrainedToolbarLayout){
			super.setRatio(ratio);
		}
	}
	/**
	 * @generated
	 */
	protected void handleNotificationEvent(Notification notification){
		Object feature = notification.getFeature();
		if(NotationPackage.eINSTANCE.getSize_Width().equals(feature) || NotationPackage.eINSTANCE.getSize_Height().equals(feature)
				|| NotationPackage.eINSTANCE.getLocation_X().equals(feature) || NotationPackage.eINSTANCE.getLocation_Y().equals(feature)){
			refreshBounds();
		}
		super.handleNotificationEvent(notification);
	}
	/**
	 * @generated
	 */
	protected void refreshBounds(){
		int width = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Width())).intValue();
		int height = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height())).intValue();
		Dimension size = new Dimension(width, height);
		int x = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_X())).intValue();
		int y = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_Y())).intValue();
		Point loc = new Point(x, y);
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), new Rectangle(loc, size));
	}
	/**
	 * @generated
	 */
	protected void refreshVisuals(){
		super.refreshVisuals();
		refreshBounds();
	}
}
