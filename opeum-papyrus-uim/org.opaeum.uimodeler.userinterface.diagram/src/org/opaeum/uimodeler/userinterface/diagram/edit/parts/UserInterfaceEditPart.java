package org.opaeum.uimodeler.userinterface.diagram.edit.parts;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutAnimator;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.dnd.TransferDropTargetListener;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.layout.FreeFormLayoutEx;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.util.TransferDragSourceListener;
import org.eclipse.papyrus.infra.gmfdiag.common.editpart.PapyrusDiagramEditPart;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.DuplicatePasteEditPolicy;
import org.eclipse.papyrus.uml.diagram.common.providers.ViewInfo;
import org.eclipse.papyrus.uml.diagram.common.util.MDTUtil;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.opaeum.uimodeler.common.figures.CustomDiagramFigure;
import org.opaeum.uimodeler.common.figures.ISWTFigure;
import org.opaeum.uimodeler.userinterface.diagram.edit.policies.UserInterfaceItemSemanticEditPolicy;
import org.opaeum.uimodeler.userinterface.diagram.part.UimVisualIDRegistry;

/**
 * @generated
 */
public class UserInterfaceEditPart extends PapyrusDiagramEditPart{
	/**
	 * @generated
	 */
	public final static String MODEL_ID = "userinterface"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 1000;
	/**
	 * @generated
	 */
	public UserInterfaceEditPart(View view){
		super(view);
	}
	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies(){
		super.createDefaultEditPolicies();
		installEditPolicy(DuplicatePasteEditPolicy.PASTE_ROLE, new DuplicatePasteEditPolicy());
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new UserInterfaceItemSemanticEditPolicy());
		// in Papyrus diagrams are not strongly synchronised
		// installEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CANONICAL_ROLE, new
		// org.opaeum.uimodeler.userinterface.diagram.edit.policies.UserInterfaceCanonicalEditPolicy());
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.POPUPBAR_ROLE);
	}
	/**
	 * @generated
	 */
	/* package-local */static class NodeLabelDragPolicy extends NonResizableEditPolicy{
		/**
		 * @generated
		 */
		@SuppressWarnings("rawtypes")
		protected List createSelectionHandles(){
			MoveHandle h = new MoveHandle((GraphicalEditPart) getHost());
			h.setBorder(null);
			return Collections.singletonList(h);
		}
		/**
		 * @generated
		 */
		public Command getCommand(Request request){
			return null;
		}
		/**
		 * @generated
		 */
		public boolean understandsRequest(Request request){
			return false;
		}
	}
	/**
	 * @generated
	 */
	protected void handleNotificationEvent(Notification event){
		super.handleNotificationEvent(event);
		if(event.getNotifier() instanceof EAnnotation){
			EAnnotation eAnnotation = (EAnnotation) event.getNotifier();
			if(eAnnotation.getSource() != null && eAnnotation.getSource().equals(MDTUtil.FilterViewAndLabelsSource)){
				// modification form MOSKitt approach, canonical policies are not called
				MDTUtil.filterDiagramViews(this.getDiagramView());
			}
		}
	}
	/**
	 * @generated
	 */
	public Object getAdapter(Class adapter){
		if(adapter != null && adapter.equals(ViewInfo.class)){
			return UimVisualIDRegistry.getDiagramViewInfo();
		}
		return super.getAdapter(adapter);
	}
	/**
	 * @generated NOT
	 */
	protected IFigure createFigure(){
		// Override the containsPoint and findFigureAt methods
		// to treat this layer (Primary Layer) as if it were opaque.
		// This is for the grid layer so that it can be seen beneath the
		// figures.
		IFigure f = new CustomDiagramFigure();
		f.setLayoutManager(new FreeFormLayoutEx());
		f.addLayoutListener(LayoutAnimator.getDefault());
//		
//		getViewer().addDragSourceListener(new TransferDragSourceListener(){
//			@Override
//			public void dragStart(DragSourceEvent event){
//				UserInterfaceEditPart ep = UserInterfaceEditPart.this;
//				IFigure targetFigure = getFigure().findMouseEventTargetAt(event.x, event.y);
//				
//				GraphicalEditPart target = findChild(UserInterfaceEditPart.this,targetFigure);
//			}
//			@Override
//			public void dragSetData(DragSourceEvent event){
//				// TODO Auto-generated method stub
//				System.out.println();
//			}
//			@Override
//			public void dragFinished(DragSourceEvent event){
//			}
//			public GraphicalEditPart findChild(EditPart c2, IFigure targetFigure){
//				List<GraphicalEditPart> children2 = c2.getChildren();
//				GraphicalEditPart target = null;
//				for(GraphicalEditPart c:children2){
//					if(c.getFigure()==targetFigure){
//						target=c;
//						break;
//					}else{
//						target=findChild(c, targetFigure);
//						if(target!=null){
//							break;
//						}
//					}
//				}
//				return target;
//			}
//			@Override
//			public Transfer getTransfer(){
//				// TODO Auto-generated method stub
//				return null;
//			}
//		});
//		getViewer().addDropTargetListener(new org.eclipse.jface.util.TransferDropTargetListener(){
//			@Override
//			public void dropAccept(DropTargetEvent event){
//				// TODO Auto-generated method stub
//			}
//			@Override
//			public void drop(DropTargetEvent event){
//				UserInterfaceEditPart ep = UserInterfaceEditPart.this;
//				GraphicalEditPart target = findChild(event.x, event.y, ep);
//				System.out.println(target);
//				System.out.println(target);
//			}
//			public GraphicalEditPart findChild(int x,int y,GraphicalEditPart c2){
//				List<GraphicalEditPart> children2 = c2.getChildren();
//				GraphicalEditPart target = null;
//				for(GraphicalEditPart c:children2){
//					if(c.getFigure().containsPoint(x, y)){
//						target = c;
//						if(findChild(x, y, c) == null){
//							break;
//						}
//					}
//				}
//				return target;
//			}
//			@Override
//			public void dragOver(DropTargetEvent event){
//				// TODO Auto-generated method stub
//			}
//			@Override
//			public void dragOperationChanged(DropTargetEvent event){
//				// TODO Auto-generated method stub
//			}
//			@Override
//			public void dragLeave(DropTargetEvent event){
//				// TODO Auto-generated method stub
//			}
//			@Override
//			public void dragEnter(DropTargetEvent event){
//				// TODO Auto-generated method stub
//			}
//			@Override
//			public boolean isEnabled(DropTargetEvent event){
//				// TODO Auto-generated method stub
//				return true;
//			}
//			@Override
//			public Transfer getTransfer(){
//				// TODO Auto-generated method stub
//				return null;
//			}
//		});
		return f;
	}
}
