package org.opaeum.uimodeler.perspective.diagram.edit.parts;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.gmfdiag.common.editpart.PapyrusDiagramEditPart;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.DuplicatePasteEditPolicy;
import org.eclipse.papyrus.uml.diagram.common.providers.ViewInfo;
import org.eclipse.papyrus.uml.diagram.common.util.MDTUtil;
import org.opaeum.uimodeler.perspective.diagram.edit.policies.PerspectiveConfigurationItemSemanticEditPolicy;
import org.opaeum.uimodeler.perspective.diagram.part.UimVisualIDRegistry;

/**
 * @generated
 */
public class PerspectiveConfigurationEditPart extends PapyrusDiagramEditPart{
	/**
	 * @generated
	 */
	public final static String MODEL_ID = "perspective"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 1000;
	/**
	 * @generated
	 */
	public PerspectiveConfigurationEditPart(View view){
		super(view);
	}
	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies(){
		super.createDefaultEditPolicies();
		installEditPolicy(DuplicatePasteEditPolicy.PASTE_ROLE, new DuplicatePasteEditPolicy());
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new PerspectiveConfigurationItemSemanticEditPolicy());
		//in Papyrus diagrams are not strongly synchronised
		//installEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CANONICAL_ROLE, new org.opaeum.uimodeler.perspective.diagram.edit.policies.PerspectiveConfigurationCanonicalEditPolicy());
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.POPUPBAR_ROLE);
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
}
