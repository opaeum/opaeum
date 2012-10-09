package org.opaeum.uimodeler.actionbar.diagram.edit.parts;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutAnimator;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.layout.FreeFormLayoutEx;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.gmfdiag.common.editpart.PapyrusDiagramEditPart;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.DuplicatePasteEditPolicy;
import org.eclipse.papyrus.uml.diagram.common.providers.ViewInfo;
import org.eclipse.papyrus.uml.diagram.common.util.MDTUtil;
import org.opaeum.uimodeler.actionbar.diagram.edit.policies.AbstractEditorItemSemanticEditPolicy;
import org.opaeum.uimodeler.actionbar.diagram.part.UimVisualIDRegistry;
import org.opaeum.uimodeler.common.figures.CustomDiagramFigure;

/**
 * @generated
 */
public class AbstractEditorEditPart extends PapyrusDiagramEditPart{
	/**
	 * @generated
	 */
	public final static String MODEL_ID = "abstractactionbar"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 1000;
	/**
	 * @generated
	 */
	public AbstractEditorEditPart(View view){
		super(view);
	}
	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies(){
		super.createDefaultEditPolicies();
		installEditPolicy(DuplicatePasteEditPolicy.PASTE_ROLE, new DuplicatePasteEditPolicy());
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new AbstractEditorItemSemanticEditPolicy());
		//in Papyrus diagrams are not strongly synchronised
		//installEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CANONICAL_ROLE, new org.opaeum.uimodeler.actionbar.diagram.edit.policies.AbstractEditorCanonicalEditPolicy());
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.POPUPBAR_ROLE);
	}
	/**
	 * @generated
	 */
	/*package-local*/static class NodeLabelDragPolicy extends NonResizableEditPolicy{
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
				//modification form MOSKitt approach, canonical policies are not called
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
	@Override
	protected IFigure createFigure(){
		CustomDiagramFigure f = new CustomDiagramFigure();
		f.setLayoutManager(new FreeFormLayoutEx());
		f.addLayoutListener(LayoutAnimator.getDefault());
		return f;
	}
}
