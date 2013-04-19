package org.opaeum.modeler.compositestructurediagram;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.CollaborationUse;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorKind;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Usage;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.topcased.modeler.ModelerPropertyConstants;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.di.model.SimpleSemanticModelElement;
import org.topcased.modeler.di.model.util.DIUtils;
import org.topcased.modeler.edit.DynamicInstanceEditPartController;
import org.topcased.modeler.edit.EListEditPart;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.alldiagram.AllSimpleObjectConstants;
import org.topcased.modeler.uml.alldiagram.edit.CommentEditPart;
import org.topcased.modeler.uml.alldiagram.edit.CommentLinkEditPart;
import org.topcased.modeler.uml.alldiagram.edit.ConstraintEditPart;
import org.topcased.modeler.uml.alldiagram.edit.ConstraintLinkEditPart;
import org.topcased.modeler.uml.compositestructuresdiagram.CompositeStructuresEditPartFactory;
import org.topcased.modeler.uml.compositestructuresdiagram.CompositeStructuresEditPolicyConstants;
import org.topcased.modeler.uml.compositestructuresdiagram.edit.CollaborationUseEditPart;
import org.topcased.modeler.uml.compositestructuresdiagram.edit.CompositeStructuresDiagramEditPart;
import org.topcased.modeler.uml.compositestructuresdiagram.edit.ConnectorEditPart;
import org.topcased.modeler.uml.compositestructuresdiagram.edit.DependencyEditPart;
import org.topcased.modeler.uml.compositestructuresdiagram.edit.InterfaceEditPart;
import org.topcased.modeler.uml.compositestructuresdiagram.edit.InterfaceRealizationEditPart;
import org.topcased.modeler.uml.compositestructuresdiagram.edit.PortEditPart;
import org.topcased.modeler.uml.compositestructuresdiagram.edit.PropertyEditPart;
import org.topcased.modeler.uml.compositestructuresdiagram.edit.UsageEditPart;
import org.topcased.modeler.uml.compositestructuresdiagram.figures.ConnectorFigure;
import org.topcased.modeler.utils.Utils;

public class BusinessStructureEditPartFactory extends CompositeStructuresEditPartFactory{
	public EditPart createEditPart(EditPart context,Object model){
		if(model instanceof Diagram){
			return new CompositeStructuresDiagramEditPart((Diagram) model){
				@Override
				protected EditPolicy getLayoutEditPolicy(){
					// Override this to bypass all the Topcased creation commands
					return new ModelerLayoutEditPolicy(){
						protected boolean isAttachedToBorder(GraphNode node){
							if(Utils.getElement(node) instanceof Port){
								return true;
							}
							return super.isAttachedToBorder(node);
						}
						@Override
						protected boolean isSeveralDisplayAllowed(GraphNode parent,GraphNode child,boolean needModelUpdate){
							return false;
						}
					};
				}
			};
		}else if(model instanceof GraphNode){
			final GraphNode node = (GraphNode) model;
			EObject element = Utils.getElement(node);
			if(element != null){
				if(UMLPlugin.UML_URI.equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new BusinessStructureNodeSwitch(node).doSwitch(element);
				}else{
					// This is for the extension point org.topcased.modeler.customEditPart
					return DynamicInstanceEditPartController.instance.getInstanceEditPart(element, node, this.getClass());
				}
			}
		}else if(model instanceof GraphEdge){
			final GraphEdge edge = (GraphEdge) model;
			EObject element = Utils.getElement(edge);
			if(element != null){
				if(UMLPlugin.UML_URI.equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new BusinessStruturesEdgeUMLSwitch(edge).doSwitch(element);
				}else{
					return DynamicInstanceEditPartController.instance.getInstanceEditPart(element, edge, this.getClass());
				}
			}
			if(edge.getSemanticModel() instanceof SimpleSemanticModelElement){
				if(AllSimpleObjectConstants.SIMPLE_OBJECT_COMMENTLINK.equals(((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo())){
					return new CommentLinkEditPart(edge);
				}
				if(AllSimpleObjectConstants.SIMPLE_OBJECT_CONSTRAINTLINK.equals(((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo())){
					return new ConstraintLinkEditPart(edge);
				}
			}
		}
		return super.createEditPart(context, model);
	}
	public static class BusinessStruturesEdgeUMLSwitch extends UMLSwitch<Object>{
		private GraphEdge edge;
		public BusinessStruturesEdgeUMLSwitch(GraphEdge edge){
			this.edge = edge;
		}
		public Object caseConnector(final Connector object){
			return new ConnectorEditPart(edge){
				@Override
				protected void refreshEdgeObjects(){
					super.refreshEdgeObjects();
					if(object.getKind() == ConnectorKind.DELEGATION_LITERAL){
						PolylineDecoration src = new PolylineDecoration();
						src.setScale(10, 5);
						((PolylineConnection) getFigure()).setSourceDecoration(src);
						PolylineDecoration tgt = new PolylineDecoration();
						tgt.setScale(10, 5);
						((PolylineConnection) getFigure()).setTargetDecoration(tgt);
					}
				}
				@Override
				protected IFigure createFigure(){
					return new ConnectorFigure(){
						@Override
						protected void outlineShape(Graphics g){
							if(object.getKind() == ConnectorKind.ASSEMBLY_LITERAL){
								super.outlineShape(g);
								Point midpoint = null;
								int angle = 180;
								Point point1;
								Point point2;
								if(getPolygonPoints().size() % 2 == 1){
									midpoint = getPolygonPoints().getMidpoint();
									int mid = (getPolygonPoints().size() / 2);
									point1 = getPolygonPoints().getPoint(mid - 1);
									point2 = getPolygonPoints().getPoint(mid + 1);
								}else{
									int mid = getPolygonPoints().size() / 2;
									point1 = getPolygonPoints().getPoint(mid - 1);
									point2 = getPolygonPoints().getPoint(mid);
									midpoint = new Point((point1.x + point2.x) / 2, (point1.y + point2.y) / 2);
								}
								int opp = Math.abs(point1.y - point2.y);
								int adj = Math.abs(point1.x - point2.x);
								double c = Math.sqrt(Math.pow(opp, 2) + Math.pow(adj, 2));
								angle = (int) Math.toDegrees(Math.acos(adj / c));
								// Remember y works top to bottom
								if(point1.y > point2.y){
									// top
									if(point2.x >= point1.x){
										// right
										angle = 90 + angle;
									}else{
										// left
										angle = 270 - angle;
									}
								}else{
									// bottom
									if(point2.x >= point1.x){
										// right
										angle = 90 - angle;
									}else{
										// left
										angle = 270 + angle;
									}
								}
								g.fillOval(new Rectangle(midpoint.x - 6, midpoint.y - 6, 12, 12));
								g.drawArc(new Rectangle(midpoint.x - 6, midpoint.y - 6, 12, 12), angle, 200);
								g.drawOval(new Rectangle(midpoint.x - 4, midpoint.y - 4, 8, 8));
							}else{
								super.outlineShape(g);
							}
						}
					};
				}
			};
		}
		public Object caseInterfaceRealization(InterfaceRealization object){
			return new InterfaceRealizationEditPart(edge);
		}
		public Object caseUsage(Usage object){
			return new UsageEditPart(edge);
		}
		public Object caseDependency(Dependency object){
			return new DependencyEditPart(edge);
		}
		public Object defaultCase(EObject object){
			return new EMFGraphEdgeEditPart(edge);
		}
	}
	public static class BusinessStructureNodeSwitch extends UMLSwitch<Object>{
		private GraphNode node;
		public BusinessStructureNodeSwitch(GraphNode node){
			this.node = node;
		}
		public Object caseComment(Comment object){
			return new CommentEditPart(node);
		}
		public Object casePort(Port object){
			return new PortEditPart(node){
				protected void createEditPolicies(){
					super.createEditPolicies();
					installEditPolicy(CompositeStructuresEditPolicyConstants.CONNECTOR_EDITPOLICY, new DelegationEdgeCreationPolicy());
					installEditPolicy(CompositeStructuresEditPolicyConstants.CONNECTOR_EDITPOLICY + "1", new AssemblyEdgeCreationPolicy());
					installEditPolicy(CompositeStructuresEditPolicyConstants.INTERFACEREALIZATION_EDITPOLICY,
							new BusinessStructureInterfaceRealizationEdgeCreationPolicy());
				}
			};
		}
		public Object caseProperty(final Property object){
			return new PropertyEditPart(node){
				protected void createEditPolicies(){
					super.createEditPolicies();
					installEditPolicy(CompositeStructuresEditPolicyConstants.CONNECTOR_EDITPOLICY, new DelegationEdgeCreationPolicy());
					installEditPolicy(CompositeStructuresEditPolicyConstants.CONNECTOR_EDITPOLICY + "1", new AssemblyEdgeCreationPolicy());
					installEditPolicy(CompositeStructuresEditPolicyConstants.INTERFACEREALIZATION_EDITPOLICY,
							new BusinessStructureInterfaceRealizationEdgeCreationPolicy());
					installEditPolicy(EditPolicy.LAYOUT_ROLE, new ModelerLayoutEditPolicy(){
						protected boolean isAttachedToBorder(GraphNode node){
							if(Utils.getElement(node) instanceof Port){
								return true;
							}
							return super.isAttachedToBorder(node);
						}
						@Override
						protected boolean isSeveralDisplayAllowed(GraphNode parent,GraphNode child,boolean needModelUpdate){
							return false;
						}
					});
				}
				@Override
				protected IFigure createFigure(){
					return new BusinessStructurePropertyFigure();
				}
				@Override
				public void deactivate(){
					super.deactivate();
					if(object.getType() != null && object.getType().eAdapters().contains(getModelListener())){
						object.getType().eAdapters().remove(getModelListener());
					}
				}
				@Override
				public void activate(){
					super.activate();
					if(object.getType() != null && !object.getType().eAdapters().contains(getModelListener())){
						object.getType().eAdapters().add(getModelListener());
					}
				}
				@Override
				public void refresh(){
					super.refresh();
					if(object.getType() instanceof BehavioredClassifier){
						BehavioredClassifier b = (BehavioredClassifier) object.getType();
						for(Interface in:b.getImplementedInterfaces()){
							((BusinessStructurePropertyFigure) getFigure()).addProvidedInterface(in.getName());
						}
					}
				}
				@Override
				protected void handleModelChanged(Notification msg){
					if(msg.getEventType() == Notification.ADD){
						if(msg.getNewValue() instanceof InterfaceRealization){
							InterfaceRealization ir = (InterfaceRealization) msg.getNewValue();
							Interface contract = ir.getContract();
							if(contract != null){
								((BusinessStructurePropertyFigure) getFigure()).addProvidedInterface(contract.getName());
							}
						}
					}else if(msg.getEventType() == Notification.REMOVE){
						if(msg.getOldValue() instanceof InterfaceRealization){
							InterfaceRealization ir = (InterfaceRealization) msg.getOldValue();
							Interface contract = ir.getContract();
							if(contract != null){
								((BusinessStructurePropertyFigure) getFigure()).removeProvidedInterface(contract.getName());
							}
						}
					}
					super.handleModelChanged(msg);
				}
			};
		}
		public Object caseInterface(Interface object){
			return new InterfaceEditPart(node){
				protected void createEditPolicies(){
					super.createEditPolicies();
					installEditPolicy(CompositeStructuresEditPolicyConstants.INTERFACEREALIZATION_EDITPOLICY,
							new BusinessStructureInterfaceRealizationEdgeCreationPolicy());
				}
			};
		}
		public Object caseCollaborationUse(CollaborationUse object){
			return new CollaborationUseEditPart(node);
		}
		public Object defaultCase(EObject object){
			return new EMFGraphNodeEditPart(node);
		}
		public EditPart caseConstraint(org.eclipse.uml2.uml.Constraint object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new ConstraintEditPart(node);
			}
		}
	}
}