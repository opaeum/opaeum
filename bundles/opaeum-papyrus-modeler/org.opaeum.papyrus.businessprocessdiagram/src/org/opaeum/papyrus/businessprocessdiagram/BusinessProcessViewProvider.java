package org.opaeum.papyrus.businessprocessdiagram;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateDiagramViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateNodeViewOperation;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.MeasurementUnit;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.papyrus.infra.extendedtypes.types.IExtendedHintedElementType;
import org.eclipse.papyrus.infra.extendedtypes.util.ElementTypeUtils;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.AcceptEventActionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ActionInputPinInCallBeActEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ActionInputPinInCallOpActAsTargetEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ActionInputPinInCallOpActEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ActionInputPinInOpaqueActEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ActionInputPinInSendObjActAsReqEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ActionInputPinInSendObjActAsTargetEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ActionInputPinInSendSigActAsTargetEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ActionInputPinInSendSigActEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ActivityDiagramEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ActivityEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ActivityEditPartCN;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ActivityFinalNodeEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ActivityParameterNodeEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ActivityPartitionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.AddStructuralFeatureValueActionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.AddVariableValueActionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.BroadcastSignalActionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.CallBehaviorActionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.CallOperationActionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.CentralBufferNodeEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.CommentEditPartCN;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ConditionalNodeEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ConstraintAsLocalPostcondEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ConstraintAsLocalPrecondEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ConstraintEditPartCN;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ConstraintInActivityAsPostcondEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ConstraintInActivityAsPrecondEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.CreateObjectActionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.DataStoreNodeEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.DecisionNodeEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.DestroyObjectActionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.DurationConstraintAsLocalPostcondEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.DurationConstraintAsLocalPrecondEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ExpansionNodeAsInEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ExpansionNodeAsOutEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ExpansionRegionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.FlowFinalNodeEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ForkNodeEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.InitialNodeEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.InputPinInAddStructuralFeatureValueActionAsObjectEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.InputPinInAddStructuralFeatureValueActionAsValueEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.InputPinInAddVariableValueActionAsInsertAtEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.InputPinInAddVariableValueActionAsValueEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.InputPinInBroadcastSignalActionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.InputPinInCallBeActEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.InputPinInCallOpActAsTargetEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.InputPinInCallOpActEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.InputPinInDestroyObjectActionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.InputPinInLoopNodeAsVariableEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.InputPinInOpaqueActEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.InputPinInReadStructuralFeatureAsObjectEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.InputPinInSendObjActAsReqEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.InputPinInSendObjActAsTargetEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.InputPinInSendSigActAsTargetEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.InputPinInSendSigActEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.InterruptibleActivityRegionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.IntervalConstraintAsLocalPostcondEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.IntervalConstraintAsLocalPrecondEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.JoinNodeEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.LoopNodeEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.MergeNodeEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.OpaqueActionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.OutputPinInAcceptEventActionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.OutputPinInAddStructuralFeatureValueActionAsResultEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.OutputPinInCallBeActEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.OutputPinInCallOpActEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.OutputPinInCreateObjectActionAsResultEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.OutputPinInLoopNodeAsBodyOutputEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.OutputPinInLoopNodeAsLoopVariableEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.OutputPinInLoopNodeAsResultEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.OutputPinInOpaqueActEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.OutputPinInReadStructuralFeatureAsResultEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.OutputPinInReadVariableActionAsResultEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.OutputPinInValSpecActEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ParameterEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ReadSelfActionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ReadSelfActionOutputPinEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ReadStructuralFeatureActionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ReadVariableActionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.SendObjectActionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.SendSignalActionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.SequenceNodeEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ShapeNamedElementEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.StructuredActivityNodeEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.TimeConstraintAsLocalPostcondEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.TimeConstraintAsLocalPrecondEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ValuePinInCallBeActEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ValuePinInCallOpActAsTargetEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ValuePinInCallOpActEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ValuePinInOpaqueActEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ValuePinInSendObjActAsReqEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ValuePinInSendObjActAsTargetEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ValuePinInSendSigActAsTargetEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ValuePinInSendSigActEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ValueSpecificationActionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.part.UMLVisualIDRegistry;
import org.eclipse.papyrus.uml.diagram.activity.providers.UMLElementTypes;
import org.eclipse.papyrus.uml.diagram.activity.providers.UMLViewProvider;

public class BusinessProcessViewProvider extends UMLViewProvider{
	protected boolean provides(CreateDiagramViewOperation op){
		return org.eclipse.papyrus.uml.diagram.activity.edit.parts.ActivityDiagramEditPart.MODEL_ID.equals(op.getSemanticHint());
	}
	@Override
	public Diagram createDiagram(IAdaptable semanticAdapter,String diagramKind,PreferencesHint preferencesHint){
		Diagram diagram = NotationFactory.eINSTANCE.createDiagram();
		diagram.getStyles().add(NotationFactory.eINSTANCE.createDiagramStyle());
		diagram.setType(org.eclipse.papyrus.uml.diagram.activity.edit.parts.ActivityDiagramEditPart.MODEL_ID);
		diagram.setElement(getSemanticElement(semanticAdapter));
		diagram.setMeasurementUnit(MeasurementUnit.PIXEL_LITERAL);
		return diagram;
	}
	protected boolean provides(CreateNodeViewOperation op){
		if(op.getContainerView() == null){
			return false;
		}
		IElementType elementType = getSemanticElementType(op.getSemanticAdapter());
		EObject domainElement = getSemanticElement(op.getSemanticAdapter());
		int visualID;
		if(op.getSemanticHint() == null){
			if(elementType != null || domainElement == null){
				return false;
			}
			visualID = UMLVisualIDRegistry.getNodeVisualID(op.getContainerView(), domainElement);
		}else{
			visualID = UMLVisualIDRegistry.getVisualID(op.getSemanticHint());
			if(elementType != null){
				if(elementType instanceof IExtendedHintedElementType){
					IElementType closestNonExtendedType = ElementTypeUtils.getClosestDiagramType(elementType);
					if(!UMLElementTypes.isKnownElementType(closestNonExtendedType) || (!(closestNonExtendedType instanceof IHintedType))){
						return false; // foreign element type.
					}
				}else{
					if(!UMLElementTypes.isKnownElementType(elementType) || (!(elementType instanceof IHintedType))){
						return false; // foreign element type
					}
				}
				String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
				if(!op.getSemanticHint().equals(elementTypeHint)){
					return false; // if semantic hint is specified it should be the same as in element type
				}
				if(domainElement != null && visualID != UMLVisualIDRegistry.getNodeVisualID(op.getContainerView(), domainElement)){
					return false; // visual id for node EClass should match visual id from element type
				}
			}else{
				if(!ActivityDiagramEditPart.MODEL_ID.equals(UMLVisualIDRegistry.getModelID(op.getContainerView()))){
					return false; // foreign diagram
				}
				switch(visualID){
				case ActivityEditPart.VISUAL_ID:
				case ParameterEditPart.VISUAL_ID:
				case ConstraintInActivityAsPrecondEditPart.VISUAL_ID:
				case InitialNodeEditPart.VISUAL_ID:
				case ActivityFinalNodeEditPart.VISUAL_ID:
				case FlowFinalNodeEditPart.VISUAL_ID:
				case OpaqueActionEditPart.VISUAL_ID:
				case ValuePinInOpaqueActEditPart.VISUAL_ID:
				case ActionInputPinInOpaqueActEditPart.VISUAL_ID:
				case InputPinInOpaqueActEditPart.VISUAL_ID:
				case OutputPinInOpaqueActEditPart.VISUAL_ID:
				case CallBehaviorActionEditPart.VISUAL_ID:
				case CallOperationActionEditPart.VISUAL_ID:
				case DurationConstraintAsLocalPrecondEditPart.VISUAL_ID:
				case TimeConstraintAsLocalPrecondEditPart.VISUAL_ID:
				case IntervalConstraintAsLocalPrecondEditPart.VISUAL_ID:
				case DecisionNodeEditPart.VISUAL_ID:
				case MergeNodeEditPart.VISUAL_ID:
				case ForkNodeEditPart.VISUAL_ID:
				case JoinNodeEditPart.VISUAL_ID:
				case DataStoreNodeEditPart.VISUAL_ID:
				case SendObjectActionEditPart.VISUAL_ID:
				case SendSignalActionEditPart.VISUAL_ID:
				case ActivityParameterNodeEditPart.VISUAL_ID:
				case AcceptEventActionEditPart.VISUAL_ID:
				case ValueSpecificationActionEditPart.VISUAL_ID:
				case ConditionalNodeEditPart.VISUAL_ID:
				case ExpansionRegionEditPart.VISUAL_ID:
				case ExpansionNodeAsInEditPart.VISUAL_ID:
				case LoopNodeEditPart.VISUAL_ID:
				case SequenceNodeEditPart.VISUAL_ID:
				case StructuredActivityNodeEditPart.VISUAL_ID:
				case ActivityPartitionEditPart.VISUAL_ID:
				case InterruptibleActivityRegionEditPart.VISUAL_ID:
				case CommentEditPartCN.VISUAL_ID:
				case ReadSelfActionEditPart.VISUAL_ID:
				case CreateObjectActionEditPart.VISUAL_ID:
				case ShapeNamedElementEditPart.VISUAL_ID:
				case ReadStructuralFeatureActionEditPart.VISUAL_ID:
				case AddStructuralFeatureValueActionEditPart.VISUAL_ID:
				case DestroyObjectActionEditPart.VISUAL_ID:
				case ReadVariableActionEditPart.VISUAL_ID:
				case AddVariableValueActionEditPart.VISUAL_ID:
				case BroadcastSignalActionEditPart.VISUAL_ID:
				case CentralBufferNodeEditPart.VISUAL_ID:
				case ConstraintEditPartCN.VISUAL_ID:
				case ConstraintInActivityAsPostcondEditPart.VISUAL_ID:
				case ValuePinInCallBeActEditPart.VISUAL_ID:
				case ActionInputPinInCallBeActEditPart.VISUAL_ID:
				case InputPinInCallBeActEditPart.VISUAL_ID:
				case OutputPinInCallBeActEditPart.VISUAL_ID:
				case ActionInputPinInCallOpActEditPart.VISUAL_ID:
				case ValuePinInCallOpActEditPart.VISUAL_ID:
				case InputPinInCallOpActEditPart.VISUAL_ID:
				case OutputPinInCallOpActEditPart.VISUAL_ID:
				case ValuePinInCallOpActAsTargetEditPart.VISUAL_ID:
				case ActionInputPinInCallOpActAsTargetEditPart.VISUAL_ID:
				case InputPinInCallOpActAsTargetEditPart.VISUAL_ID:
				case DurationConstraintAsLocalPostcondEditPart.VISUAL_ID:
				case TimeConstraintAsLocalPostcondEditPart.VISUAL_ID:
				case IntervalConstraintAsLocalPostcondEditPart.VISUAL_ID:
				case ConstraintAsLocalPrecondEditPart.VISUAL_ID:
				case ConstraintAsLocalPostcondEditPart.VISUAL_ID:
				case ValuePinInSendObjActAsReqEditPart.VISUAL_ID:
				case ActionInputPinInSendObjActAsReqEditPart.VISUAL_ID:
				case InputPinInSendObjActAsReqEditPart.VISUAL_ID:
				case ValuePinInSendObjActAsTargetEditPart.VISUAL_ID:
				case ActionInputPinInSendObjActAsTargetEditPart.VISUAL_ID:
				case InputPinInSendObjActAsTargetEditPart.VISUAL_ID:
				case ActionInputPinInSendSigActEditPart.VISUAL_ID:
				case ValuePinInSendSigActEditPart.VISUAL_ID:
				case InputPinInSendSigActEditPart.VISUAL_ID:
				case ValuePinInSendSigActAsTargetEditPart.VISUAL_ID:
				case ActionInputPinInSendSigActAsTargetEditPart.VISUAL_ID:
				case InputPinInSendSigActAsTargetEditPart.VISUAL_ID:
				case OutputPinInAcceptEventActionEditPart.VISUAL_ID:
				case OutputPinInValSpecActEditPart.VISUAL_ID:
				case ExpansionNodeAsOutEditPart.VISUAL_ID:
				case OutputPinInLoopNodeAsBodyOutputEditPart.VISUAL_ID:
				case OutputPinInLoopNodeAsLoopVariableEditPart.VISUAL_ID:
				case OutputPinInLoopNodeAsResultEditPart.VISUAL_ID:
				case InputPinInLoopNodeAsVariableEditPart.VISUAL_ID:
				case ReadSelfActionOutputPinEditPart.VISUAL_ID:
				case ActivityEditPartCN.VISUAL_ID:
				case OutputPinInCreateObjectActionAsResultEditPart.VISUAL_ID:
				case InputPinInReadStructuralFeatureAsObjectEditPart.VISUAL_ID:
				case OutputPinInReadStructuralFeatureAsResultEditPart.VISUAL_ID:
				case InputPinInAddStructuralFeatureValueActionAsObjectEditPart.VISUAL_ID:
				case InputPinInAddStructuralFeatureValueActionAsValueEditPart.VISUAL_ID:
				case OutputPinInAddStructuralFeatureValueActionAsResultEditPart.VISUAL_ID:
				case InputPinInDestroyObjectActionEditPart.VISUAL_ID:
				case OutputPinInReadVariableActionAsResultEditPart.VISUAL_ID:
				case InputPinInAddVariableValueActionAsInsertAtEditPart.VISUAL_ID:
				case InputPinInAddVariableValueActionAsValueEditPart.VISUAL_ID:
				case InputPinInBroadcastSignalActionEditPart.VISUAL_ID:
					if(domainElement == null || visualID != UMLVisualIDRegistry.getNodeVisualID(op.getContainerView(), domainElement)){
						return false; // visual id in semantic hint should match visual id for domain element
					}
					break;
				default:
					return false;
				}
			}
		}
		return ActivityEditPart.VISUAL_ID == visualID || ParameterEditPart.VISUAL_ID == visualID || ConstraintInActivityAsPrecondEditPart.VISUAL_ID == visualID
				|| ConstraintInActivityAsPostcondEditPart.VISUAL_ID == visualID || InitialNodeEditPart.VISUAL_ID == visualID
				|| ActivityFinalNodeEditPart.VISUAL_ID == visualID || FlowFinalNodeEditPart.VISUAL_ID == visualID || OpaqueActionEditPart.VISUAL_ID == visualID
				|| ValuePinInOpaqueActEditPart.VISUAL_ID == visualID || ActionInputPinInOpaqueActEditPart.VISUAL_ID == visualID
				|| InputPinInOpaqueActEditPart.VISUAL_ID == visualID || OutputPinInOpaqueActEditPart.VISUAL_ID == visualID
				|| CallBehaviorActionEditPart.VISUAL_ID == visualID || ValuePinInCallBeActEditPart.VISUAL_ID == visualID
				|| ActionInputPinInCallBeActEditPart.VISUAL_ID == visualID || InputPinInCallBeActEditPart.VISUAL_ID == visualID
				|| OutputPinInCallBeActEditPart.VISUAL_ID == visualID || CallOperationActionEditPart.VISUAL_ID == visualID
				|| ActionInputPinInCallOpActEditPart.VISUAL_ID == visualID || ValuePinInCallOpActEditPart.VISUAL_ID == visualID
				|| InputPinInCallOpActEditPart.VISUAL_ID == visualID || OutputPinInCallOpActEditPart.VISUAL_ID == visualID
				|| ValuePinInCallOpActAsTargetEditPart.VISUAL_ID == visualID || ActionInputPinInCallOpActAsTargetEditPart.VISUAL_ID == visualID
				|| InputPinInCallOpActAsTargetEditPart.VISUAL_ID == visualID || DurationConstraintAsLocalPrecondEditPart.VISUAL_ID == visualID
				|| DurationConstraintAsLocalPostcondEditPart.VISUAL_ID == visualID || TimeConstraintAsLocalPrecondEditPart.VISUAL_ID == visualID
				|| TimeConstraintAsLocalPostcondEditPart.VISUAL_ID == visualID || IntervalConstraintAsLocalPrecondEditPart.VISUAL_ID == visualID
				|| IntervalConstraintAsLocalPostcondEditPart.VISUAL_ID == visualID || ConstraintAsLocalPrecondEditPart.VISUAL_ID == visualID
				|| ConstraintAsLocalPostcondEditPart.VISUAL_ID == visualID || DecisionNodeEditPart.VISUAL_ID == visualID || MergeNodeEditPart.VISUAL_ID == visualID
				|| ForkNodeEditPart.VISUAL_ID == visualID || JoinNodeEditPart.VISUAL_ID == visualID || DataStoreNodeEditPart.VISUAL_ID == visualID
				|| SendObjectActionEditPart.VISUAL_ID == visualID || ValuePinInSendObjActAsReqEditPart.VISUAL_ID == visualID
				|| ActionInputPinInSendObjActAsReqEditPart.VISUAL_ID == visualID || InputPinInSendObjActAsReqEditPart.VISUAL_ID == visualID
				|| ValuePinInSendObjActAsTargetEditPart.VISUAL_ID == visualID || ActionInputPinInSendObjActAsTargetEditPart.VISUAL_ID == visualID
				|| InputPinInSendObjActAsTargetEditPart.VISUAL_ID == visualID || SendSignalActionEditPart.VISUAL_ID == visualID
				|| ActionInputPinInSendSigActEditPart.VISUAL_ID == visualID || ValuePinInSendSigActEditPart.VISUAL_ID == visualID
				|| InputPinInSendSigActEditPart.VISUAL_ID == visualID || ValuePinInSendSigActAsTargetEditPart.VISUAL_ID == visualID
				|| ActionInputPinInSendSigActAsTargetEditPart.VISUAL_ID == visualID || InputPinInSendSigActAsTargetEditPart.VISUAL_ID == visualID
				|| ActivityParameterNodeEditPart.VISUAL_ID == visualID || AcceptEventActionEditPart.VISUAL_ID == visualID
				|| OutputPinInAcceptEventActionEditPart.VISUAL_ID == visualID || ValueSpecificationActionEditPart.VISUAL_ID == visualID
				|| OutputPinInValSpecActEditPart.VISUAL_ID == visualID || ConditionalNodeEditPart.VISUAL_ID == visualID
				|| ExpansionRegionEditPart.VISUAL_ID == visualID || ExpansionNodeAsInEditPart.VISUAL_ID == visualID || ExpansionNodeAsOutEditPart.VISUAL_ID == visualID
				|| LoopNodeEditPart.VISUAL_ID == visualID || OutputPinInLoopNodeAsBodyOutputEditPart.VISUAL_ID == visualID
				|| OutputPinInLoopNodeAsLoopVariableEditPart.VISUAL_ID == visualID || OutputPinInLoopNodeAsResultEditPart.VISUAL_ID == visualID
				|| SequenceNodeEditPart.VISUAL_ID == visualID || StructuredActivityNodeEditPart.VISUAL_ID == visualID
				|| InputPinInLoopNodeAsVariableEditPart.VISUAL_ID == visualID || ActivityPartitionEditPart.VISUAL_ID == visualID
				|| InterruptibleActivityRegionEditPart.VISUAL_ID == visualID || CommentEditPartCN.VISUAL_ID == visualID || ReadSelfActionEditPart.VISUAL_ID == visualID
				|| ReadSelfActionOutputPinEditPart.VISUAL_ID == visualID || ActivityEditPartCN.VISUAL_ID == visualID
				|| CreateObjectActionEditPart.VISUAL_ID == visualID || OutputPinInCreateObjectActionAsResultEditPart.VISUAL_ID == visualID
				|| ShapeNamedElementEditPart.VISUAL_ID == visualID || ReadStructuralFeatureActionEditPart.VISUAL_ID == visualID
				|| InputPinInReadStructuralFeatureAsObjectEditPart.VISUAL_ID == visualID || OutputPinInReadStructuralFeatureAsResultEditPart.VISUAL_ID == visualID
				|| AddStructuralFeatureValueActionEditPart.VISUAL_ID == visualID || InputPinInAddStructuralFeatureValueActionAsObjectEditPart.VISUAL_ID == visualID
				|| InputPinInAddStructuralFeatureValueActionAsValueEditPart.VISUAL_ID == visualID
				|| OutputPinInAddStructuralFeatureValueActionAsResultEditPart.VISUAL_ID == visualID || DestroyObjectActionEditPart.VISUAL_ID == visualID
				|| InputPinInDestroyObjectActionEditPart.VISUAL_ID == visualID || ReadVariableActionEditPart.VISUAL_ID == visualID
				|| OutputPinInReadVariableActionAsResultEditPart.VISUAL_ID == visualID || AddVariableValueActionEditPart.VISUAL_ID == visualID
				|| InputPinInAddVariableValueActionAsInsertAtEditPart.VISUAL_ID == visualID || InputPinInAddVariableValueActionAsValueEditPart.VISUAL_ID == visualID
				|| BroadcastSignalActionEditPart.VISUAL_ID == visualID || InputPinInBroadcastSignalActionEditPart.VISUAL_ID == visualID
				|| CentralBufferNodeEditPart.VISUAL_ID == visualID || ConstraintEditPartCN.VISUAL_ID == visualID;
	}
}
