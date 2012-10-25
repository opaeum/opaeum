package org.opaeum.topcased.activitydiagram;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.topcased.EditPartUtil;
import org.opaeum.topcased.activitydiagram.bpm.edit.SimpleTaskEditPart;
import org.opaeum.topcased.classdiagram.figure.Gradient;
import org.topcased.modeler.ModelerPropertyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.di.model.util.DIUtils;
import org.topcased.modeler.edit.EListEditPart;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.editor.ModelerGraphicalViewer;
import org.topcased.modeler.uml.activitydiagram.ActivityEditPolicyConstants;
import org.topcased.modeler.uml.activitydiagram.edit.AcceptCallActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.AcceptEventActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ActivityFinalNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ActivityNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ActivityParameterNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ActivityPartitionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.AddStructuralFeatureValueActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.AddVariableValueActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.BroadcastSignalActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.CallBehaviorActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.CallOperationActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.CentralBufferNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ClearAssociationActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ClearStructuralFeatureActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ClearVariableActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ConditionalNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ControlNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.CreateLinkActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.CreateLinkObjectActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.CreateObjectActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.DataStoreNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.DecisionNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.DestroyLinkActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.DestroyObjectActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ExecutableNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ExpansionNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ExpansionRegionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.FlowFinalNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ForkNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.InitialNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.InputPinEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.InterruptibleActivityRegionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.JoinNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.LoopNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.MergeNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.OpaqueActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.OutputPinEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.RaiseExceptionActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ReadExtentActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ReadIsClassifiedObjectActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ReadLinkActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ReadLinkObjectEndActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ReadLinkObjectEndQualifierActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ReadSelfActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ReadStructuralFeatureActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ReadVariableActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ReclassifyObjectActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ReduceActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.RemoveStructuralFeatureValueActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.RemoveVariableValueActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ReplyActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.SendObjectActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.SendSignalActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.SequenceNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.StartClassifierBehaviorActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.StructuredActivityNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.TestIdentityActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.UnmarshallActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ValueSpecificationActionEditPart;
import org.topcased.modeler.uml.activitydiagram.figures.CallBehaviorActionFigure;
import org.topcased.modeler.uml.activitydiagram.internal.structuredactivitynode.edit.ClauseEditPart;
import org.topcased.modeler.uml.alldiagram.edit.CommentEditPart;
import org.topcased.modeler.uml.alldiagram.edit.ConstraintEditPart;

public class OpaeumActivityNodeUMLSwitch extends UMLSwitch<Object>{
	private GraphNode node;
	public OpaeumActivityNodeUMLSwitch(GraphNode node){
		this.node = node;
	}
	public Object caseInterruptibleActivityRegion(org.eclipse.uml2.uml.InterruptibleActivityRegion object){
		return new InterruptibleActivityRegionEditPart(node);
	}
	public Object caseActivityNode(org.eclipse.uml2.uml.ActivityNode object){
		return new ActivityNodeEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseControlNode(org.eclipse.uml2.uml.ControlNode object){
		return new ControlNodeEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseInitialNode(org.eclipse.uml2.uml.InitialNode object){
		return new InitialNodeEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseDecisionNode(org.eclipse.uml2.uml.DecisionNode object){
		return new DecisionNodeEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseMergeNode(org.eclipse.uml2.uml.MergeNode object){
		return new MergeNodeEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseForkNode(org.eclipse.uml2.uml.ForkNode object){
		return new ForkNodeEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseJoinNode(org.eclipse.uml2.uml.JoinNode object){
		return new JoinNodeEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseActivityFinalNode(org.eclipse.uml2.uml.ActivityFinalNode object){
		return new ActivityFinalNodeEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseFlowFinalNode(org.eclipse.uml2.uml.FlowFinalNode object){
		return new FlowFinalNodeEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseCentralBufferNode(org.eclipse.uml2.uml.CentralBufferNode object){
		return new CentralBufferNodeEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseDataStoreNode(org.eclipse.uml2.uml.DataStoreNode object){
		return new DataStoreNodeEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseActivityParameterNode(org.eclipse.uml2.uml.ActivityParameterNode object){
		return new ActivityParameterNodeEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseExpansionNode(org.eclipse.uml2.uml.ExpansionNode object){
		return new ExpansionNodeEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseExecutableNode(org.eclipse.uml2.uml.ExecutableNode object){
		return new ExecutableNodeEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseAction(org.eclipse.uml2.uml.Action object){
		return new ActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseCallBehaviorAction(org.eclipse.uml2.uml.CallBehaviorAction object){
		return new CallBehaviorActionEditPart(node){
			@Override
			protected IFigure createFigure(){
				return new CallBehaviorActionFigure(){
					@Override
					public void paintChildren(Graphics graphics){
						Gradient.paintChildren(graphics, this);
						super.paintChildren(graphics);
					}
				};
			}
			@Override
			protected IAction createChangeDiagramAction(EObject object){
				CallBehaviorAction a = (CallBehaviorAction) object;
				if(StereotypesHelper.hasKeyword((Element) object, StereotypeNames.CALL_METHOD_ACTION)){
					return EditPartUtil.createDiagramAction(a.getBehavior(), object, ((ModelerGraphicalViewer) getViewer()).getModelerEditor(),
							"org.topcased.modeler.uml.activitydiagram.method");
				}else if(StereotypesHelper.hasKeyword((Element) object, StereotypeNames.CALL_BUSINES_PROCESS_ACTION)){
					return EditPartUtil.createDiagramAction(a.getBehavior(), object, ((ModelerGraphicalViewer) getViewer()).getModelerEditor(),
							"org.topcased.modeler.uml.activitydiagram.bpm");
				}else if(StereotypesHelper.hasKeyword((Element) object, StereotypeNames.CALL_BUSINESS_STATE_MACHINE_ACTION)){
					return EditPartUtil.createDiagramAction(a.getBehavior(), object, ((ModelerGraphicalViewer) getViewer()).getModelerEditor(),
							"org.opaeum.topcased.statemachinediagram.businessstatemachine");
				}else if(StereotypesHelper.hasKeyword((Element) object, StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK)){
					return EditPartUtil.createDiagramAction(a.getBehavior(), object, ((ModelerGraphicalViewer) getViewer()).getModelerEditor(),
							"org.opaeum.topcased.statemachinediagram.screenflow");
				}else{
					return super.createChangeDiagramAction(object);
				}
			}
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseCallOperationAction(org.eclipse.uml2.uml.CallOperationAction object){
		return new CallOperationActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseExpansionRegion(org.eclipse.uml2.uml.ExpansionRegion object){
		return new ExpansionRegionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseInputPin(org.eclipse.uml2.uml.InputPin object){
		return new InputPinEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseOutputPin(org.eclipse.uml2.uml.OutputPin object){
		return new OutputPinEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseActivityPartition(org.eclipse.uml2.uml.ActivityPartition object){
		return new ActivityPartitionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseComment(org.eclipse.uml2.uml.Comment object){
		return new CommentEditPart(node);
	}
	public Object caseSendSignalAction(org.eclipse.uml2.uml.SendSignalAction object){
		return new SendSignalActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseAcceptEventAction(org.eclipse.uml2.uml.AcceptEventAction object){
		return new AcceptEventActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseAcceptCallAction(org.eclipse.uml2.uml.AcceptCallAction object){
		return new AcceptCallActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseClearAssociationAction(org.eclipse.uml2.uml.ClearAssociationAction object){
		return new ClearAssociationActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseCreateObjectAction(org.eclipse.uml2.uml.CreateObjectAction object){
		return new CreateObjectActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseDestroyObjectAction(org.eclipse.uml2.uml.DestroyObjectAction object){
		return new DestroyObjectActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseBroadcastSignalAction(org.eclipse.uml2.uml.BroadcastSignalAction object){
		return new BroadcastSignalActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseSendObjectAction(org.eclipse.uml2.uml.SendObjectAction object){
		return new SendObjectActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseReadLinkAction(org.eclipse.uml2.uml.ReadLinkAction object){
		return new ReadLinkActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseCreateLinkAction(org.eclipse.uml2.uml.CreateLinkAction object){
		return new CreateLinkActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseCreateLinkObjectAction(org.eclipse.uml2.uml.CreateLinkObjectAction object){
		return new CreateLinkObjectActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseDestroyLinkAction(org.eclipse.uml2.uml.DestroyLinkAction object){
		return new DestroyLinkActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseOpaqueAction(org.eclipse.uml2.uml.OpaqueAction object){
		if(StereotypesHelper.hasKeyword(object, StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK)){
			return new SimpleTaskEditPart(node);
		}else{
			return new OpaqueActionEditPart(node){
				protected void createEditPolicies(){
					super.createEditPolicies();
					installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
					installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
				}
			};
		}
	}
	public Object caseRaiseExceptionAction(org.eclipse.uml2.uml.RaiseExceptionAction object){
		return new RaiseExceptionActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseReadExtentAction(org.eclipse.uml2.uml.ReadExtentAction object){
		return new ReadExtentActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseReadIsClassifiedObjectAction(org.eclipse.uml2.uml.ReadIsClassifiedObjectAction object){
		return new ReadIsClassifiedObjectActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseReadLinkObjectEndAction(org.eclipse.uml2.uml.ReadLinkObjectEndAction object){
		return new ReadLinkObjectEndActionEditPart(node);
	}
	public Object caseReadLinkObjectEndQualifierAction(org.eclipse.uml2.uml.ReadLinkObjectEndQualifierAction object){
		return new ReadLinkObjectEndQualifierActionEditPart(node);
	}
	public Object caseReadSelfAction(org.eclipse.uml2.uml.ReadSelfAction object){
		return new ReadSelfActionEditPart(node);
	}
	public Object caseReclassifyObjectAction(org.eclipse.uml2.uml.ReclassifyObjectAction object){
		return new ReclassifyObjectActionEditPart(node);
	}
	public Object caseReduceAction(org.eclipse.uml2.uml.ReduceAction object){
		return new ReduceActionEditPart(node);
	}
	public Object caseReplyAction(org.eclipse.uml2.uml.ReplyAction object){
		return new ReplyActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseStartClassifierBehaviorAction(org.eclipse.uml2.uml.StartClassifierBehaviorAction object){
		return new StartClassifierBehaviorActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseClearStructuralFeatureAction(org.eclipse.uml2.uml.ClearStructuralFeatureAction object){
		return new ClearStructuralFeatureActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseReadStructuralFeatureAction(org.eclipse.uml2.uml.ReadStructuralFeatureAction object){
		return new ReadStructuralFeatureActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseAddStructuralFeatureValueAction(org.eclipse.uml2.uml.AddStructuralFeatureValueAction object){
		return new AddStructuralFeatureValueActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseRemoveStructuralFeatureValueAction(org.eclipse.uml2.uml.RemoveStructuralFeatureValueAction object){
		return new RemoveStructuralFeatureValueActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseStructuredActivityNode(org.eclipse.uml2.uml.StructuredActivityNode object){
		return new StructuredActivityNodeEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseConditionalNode(org.eclipse.uml2.uml.ConditionalNode object){
		return new ConditionalNodeEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseLoopNode(org.eclipse.uml2.uml.LoopNode object){
		return new LoopNodeEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseSequenceNode(org.eclipse.uml2.uml.SequenceNode object){
		return new SequenceNodeEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseTestIdentityAction(org.eclipse.uml2.uml.TestIdentityAction object){
		return new TestIdentityActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseUnmarshallAction(org.eclipse.uml2.uml.UnmarshallAction object){
		return new UnmarshallActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseValueSpecificationAction(org.eclipse.uml2.uml.ValueSpecificationAction object){
		return new ValueSpecificationActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseClearVariableAction(org.eclipse.uml2.uml.ClearVariableAction object){
		return new ClearVariableActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseReadVariableAction(org.eclipse.uml2.uml.ReadVariableAction object){
		return new ReadVariableActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseAddVariableValueAction(org.eclipse.uml2.uml.AddVariableValueAction object){
		return new AddVariableValueActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseRemoveVariableValueAction(org.eclipse.uml2.uml.RemoveVariableValueAction object){
		return new RemoveVariableValueActionEditPart(node){
			protected void createEditPolicies(){
				super.createEditPolicies();
				installEditPolicy(ActivityEditPolicyConstants.OBJECTFLOW_EDITPOLICY, new OpaeumObjectFlowEdgeCreationEditPolicy());
				installEditPolicy(ActivityEditPolicyConstants.CONTROLFLOW_EDITPOLICY, new OpaeumControlFlowEdgeCreationEditPolicy());
			}
		};
	}
	public Object caseClause(org.eclipse.uml2.uml.Clause object){
		return new ClauseEditPart(node);
	}
	public Object defaultCase(EObject object){
		return new EMFGraphNodeEditPart(node);
	}
	public Object caseConstraint(org.eclipse.uml2.uml.Constraint object){
		String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
		if(!"".equals(feature)){
			int featureID = Integer.parseInt(feature);
			return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
		}else{
			return new ConstraintEditPart(node);
		}
	}
}