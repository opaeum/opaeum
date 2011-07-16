package org.nakeduml.topcased.classdiagram;

import java.util.ArrayList;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.nakeduml.topcased.classdiagram.edit.BusinessComponentEditPart;
import org.nakeduml.topcased.classdiagram.edit.BusinessRoleEditPart;
import org.nakeduml.topcased.classdiagram.edit.BusinessServiceEditPart;
import org.nakeduml.topcased.classdiagram.figure.Gradient;
import org.topcased.modeler.ModelerPropertyConstants;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.di.model.SimpleSemanticModelElement;
import org.topcased.modeler.di.model.util.DIUtils;
import org.topcased.modeler.dialogs.DiagramSelectionDialog;
import org.topcased.modeler.edit.EListEditPart;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.editor.ModelerEditPartFactory;
import org.topcased.modeler.extensions.DiagramDescriptor;
import org.topcased.modeler.uml.alldiagram.AllSimpleObjectConstants;
import org.topcased.modeler.uml.alldiagram.edit.CommentEditPart;
import org.topcased.modeler.uml.alldiagram.edit.CommentLinkEditPart;
import org.topcased.modeler.uml.alldiagram.edit.ConstraintEditPart;
import org.topcased.modeler.uml.alldiagram.edit.ConstraintLinkEditPart;
import org.topcased.modeler.uml.alldiagram.edit.DependencyEditPart;
import org.topcased.modeler.uml.alldiagram.edit.PackageEditPart;
import org.topcased.modeler.uml.alldiagram.edit.PackageImportEditPart;
import org.topcased.modeler.uml.alldiagram.edit.PackageMergeEditPart;
import org.topcased.modeler.uml.alldiagram.edit.UsageEditPart;
import org.topcased.modeler.uml.alldiagram.figures.PackageFigure;
import org.topcased.modeler.uml.classdiagram.ClassSimpleObjectConstants;
import org.topcased.modeler.uml.classdiagram.edit.AnyReceiveEventEditPart;
import org.topcased.modeler.uml.classdiagram.edit.AssociationClassEditPart;
import org.topcased.modeler.uml.classdiagram.edit.AssociationEditPart;
import org.topcased.modeler.uml.classdiagram.edit.CallEventEditPart;
import org.topcased.modeler.uml.classdiagram.edit.ChangeEventEditPart;
import org.topcased.modeler.uml.classdiagram.edit.ClassDiagramEditPart;
import org.topcased.modeler.uml.classdiagram.edit.ClassEditPart;
import org.topcased.modeler.uml.classdiagram.edit.ClassFromAssociationClassEditPart;
import org.topcased.modeler.uml.classdiagram.edit.ClassifierTemplateParameterEditPart;
import org.topcased.modeler.uml.classdiagram.edit.CreationEventEditPart;
import org.topcased.modeler.uml.classdiagram.edit.DataTypeEditPart;
import org.topcased.modeler.uml.classdiagram.edit.DestructionEventEditPart;
import org.topcased.modeler.uml.classdiagram.edit.EnumerationEditPart;
import org.topcased.modeler.uml.classdiagram.edit.EnumerationLiteralEditPart;
import org.topcased.modeler.uml.classdiagram.edit.ExecutionEventEditPart;
import org.topcased.modeler.uml.classdiagram.edit.GeneralizationEditPart;
import org.topcased.modeler.uml.classdiagram.edit.InstanceSpecificationEditPart;
import org.topcased.modeler.uml.classdiagram.edit.InstanceSpecificationLinkEditPart;
import org.topcased.modeler.uml.classdiagram.edit.InterfaceEditPart;
import org.topcased.modeler.uml.classdiagram.edit.InterfaceRealizationEditPart;
import org.topcased.modeler.uml.classdiagram.edit.OperationEditPart;
import org.topcased.modeler.uml.classdiagram.edit.PrimitiveTypeEditPart;
import org.topcased.modeler.uml.classdiagram.edit.PropertyEListEditPart;
import org.topcased.modeler.uml.classdiagram.edit.PropertyEditPart;
import org.topcased.modeler.uml.classdiagram.edit.ReceiveOperationEventEditPart;
import org.topcased.modeler.uml.classdiagram.edit.ReceiveSignalEventEditPart;
import org.topcased.modeler.uml.classdiagram.edit.ReceptionEditPart;
import org.topcased.modeler.uml.classdiagram.edit.RedefinableTemplateSignatureEditPart;
import org.topcased.modeler.uml.classdiagram.edit.SendOperationEventEditPart;
import org.topcased.modeler.uml.classdiagram.edit.SendSignalEventEditPart;
import org.topcased.modeler.uml.classdiagram.edit.SignalEditPart;
import org.topcased.modeler.uml.classdiagram.edit.SignalEventEditPart;
import org.topcased.modeler.uml.classdiagram.edit.SlotEditPart;
import org.topcased.modeler.uml.classdiagram.edit.TemplateBindingEditPart;
import org.topcased.modeler.uml.classdiagram.edit.TemplateSignatureEditPart;
import org.topcased.modeler.uml.classdiagram.edit.TimeEventEditPart;
import org.topcased.modeler.uml.classdiagram.figures.AssociationFigure;
import org.topcased.modeler.uml.classdiagram.figures.ClassFigure;
import org.topcased.modeler.uml.classdiagram.figures.EnumerationFigure;
import org.topcased.modeler.utils.Utils;

public class ClassEditPartFactory extends ModelerEditPartFactory{
	public EditPart createEditPart(EditPart context,Object model){
		if(model instanceof Diagram){
			return new ClassDiagramEditPart((Diagram) model);
		}else if(model instanceof GraphNode){
			final GraphNode node = (GraphNode) model;
			EObject element = Utils.getElement(node);
			if(element != null){
				return new NodeUMLSwitch(node).doSwitch(element);
			}
		}else if(model instanceof GraphEdge){
			final GraphEdge edge = (GraphEdge) model;
			EObject element = Utils.getElement(edge);
			if(element != null){
				return new EdgeUMLSwitch(edge).doSwitch(element);
			}
			if(edge.getSemanticModel() instanceof SimpleSemanticModelElement){
				if(ClassSimpleObjectConstants.SIMPLE_OBJECT_COMMENTLINK.equals(((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo())){
					return new CommentLinkEditPart(edge);
				}
				if(AllSimpleObjectConstants.SIMPLE_OBJECT_CONSTRAINTLINK.equals(((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo())){
					return new ConstraintLinkEditPart(edge);
				}
			}
		}
		return super.createEditPart(context, model);
	}
	private class NodeUMLSwitch extends UMLSwitch<EditPart>{
		@Override
		public EditPart caseComponent(Component object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				return caseClass(object);
			}else if(StereotypesHelper.hasKeyword(object, StereotypeNames.BUSINESS_COMPONENT)){
				return new BusinessComponentEditPart(node);
			}
			return caseClass(object);
		}
		@Override
		public EditPart caseStateMachine(StateMachine object){
			return caseClass(object);
		}
		@Override
		public EditPart caseActivity(Activity object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				return caseClass(object);
			}else if(StereotypesHelper.hasKeyword(object, StereotypeNames.BUSINES_PROCESS)){
				return new BusinessProcessEditPart(node);
			}else{
				return createClassEditPart();
			}
		}
		private GraphNode node;
		public NodeUMLSwitch(GraphNode node){
			this.node = node;
		}
		public EditPart caseTemplateSignature(org.eclipse.uml2.uml.TemplateSignature object){
			return new TemplateSignatureEditPart(node);
		}
		public EditPart caseRedefinableTemplateSignature(org.eclipse.uml2.uml.RedefinableTemplateSignature object){
			return new RedefinableTemplateSignatureEditPart(node);
		}
		public EditPart caseClassifierTemplateParameter(org.eclipse.uml2.uml.ClassifierTemplateParameter object){
			return new ClassifierTemplateParameterEditPart(node);
		}
		public EditPart casePackage(org.eclipse.uml2.uml.Package object){
			return new PackageEditPart(node){
				@Override
				protected IFigure createFigure(){
					return new PackageFigure(){
						@Override
						public void paintChildren(Graphics graphics){
							getContentPane().setOpaque(false);
							Gradient.paintChildren(graphics, (Figure) this.getContentPane());
							super.paintChildren(graphics);
						}
					};
				}
			};
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseClass(org.eclipse.uml2.uml.Class)
		 * @generated NOT
		 */
		public EditPart caseClass(org.eclipse.uml2.uml.Class object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				EStructuralFeature[] features = Utils.getEStructuralFeatures(feature, object.eClass());
				if(features.length == 1){
					// add a customized PropertyEListEditPart to display
					// Properties and filtering those associated with an
					// Association
					if(UMLPackage.CLASS__OWNED_ATTRIBUTE == features[0].getFeatureID()){
						return new PropertyEListEditPart(node, features[0]);
					}
				}
				return new EListEditPart(node, features);
			}else if(StereotypesHelper.hasKeyword(object, StereotypeNames.BUSINESS_ROLE)){
				return new BusinessRoleEditPart(node){
					@Override
					protected IFigure createFigure(){
						return new ClassFigure(){
							@Override
							public void paintChildren(Graphics graphics){
								Gradient.paintChildren(graphics, this);
								super.paintChildren(graphics);
							}
						};
					}
				};
			}else{
				return createClassEditPart();
			}
		}
		private EditPart createClassEditPart(){
			return new ClassEditPart(node){
				@Override
				protected IFigure createFigure(){
					return new ClassFigure(){
						@Override
						public void paintChildren(Graphics graphics){
							Gradient.paintChildren(graphics, this);
							super.paintChildren(graphics);
						}
					};
				}
			};
		}
		public EditPart caseInterface(org.eclipse.uml2.uml.Interface object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				return new EListEditPart(node, Utils.getEStructuralFeatures(feature, object.eClass()));
			}else{
				if(StereotypesHelper.hasKeyword(object, StereotypeNames.BUSINESS_SERVICE)){
					return new BusinessServiceEditPart(node);
				}else{
					return new InterfaceEditPart(node);
				}
			}
		}
		public EditPart caseDataType(org.eclipse.uml2.uml.DataType object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new DataTypeEditPart(node);
			}
		}
		public EditPart caseOperation(org.eclipse.uml2.uml.Operation object){
			return new OperationEditPart(node){
				@Override
				public void performRequest(Request request){
					if(request.getType() == RequestConstants.REQ_OPEN){
						MessageBox messageBox = new MessageBox(getModeler().getSite().getShell());
						messageBox.setMessage("open UIM diagrams here");
						messageBox.open();
					}
					super.performRequest(request);
				}
				@Override
				protected IAction createChangeDiagramAction(EObject modelObject){
					Operation oper = (Operation) modelObject;
					if(oper.getMethods().size() > 0){
						return super.createChangeDiagramAction(oper.getMethods().get(0));
					}
					return super.createChangeDiagramAction(oper);
				}
			};
		}
		public EditPart caseProperty(org.eclipse.uml2.uml.Property object){
			return new PropertyEditPart(node);
		}
		public EditPart caseInstanceSpecification(org.eclipse.uml2.uml.InstanceSpecification object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new InstanceSpecificationEditPart(node);
			}
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseComment(org.eclipse.uml2.uml.Comment)
		 * @generated
		 */
		public EditPart caseComment(org.eclipse.uml2.uml.Comment object){
			return new CommentEditPart(node);
		}
		public EditPart caseSlot(org.eclipse.uml2.uml.Slot object){
			return new SlotEditPart(node);
		}
		public EditPart caseEnumeration(org.eclipse.uml2.uml.Enumeration object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new EnumerationEditPart(node){
					@Override
					protected IFigure createFigure(){
						return new EnumerationFigure(){
							@Override
							public void paintChildren(Graphics graphics){
								Gradient.paintChildren(graphics, this);
								super.paintChildren(graphics);
							}
						};
					}
				};
			}
		}
		public EditPart caseEnumerationLiteral(org.eclipse.uml2.uml.EnumerationLiteral object){
			return new EnumerationLiteralEditPart(node);
		}
		public EditPart casePrimitiveType(org.eclipse.uml2.uml.PrimitiveType object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new PrimitiveTypeEditPart(node);
			}
		}
		public EditPart caseAssociationClass(org.eclipse.uml2.uml.AssociationClass object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				EStructuralFeature[] features = Utils.getEStructuralFeatures(feature, object.eClass());
				if(features.length == 1){
					// add a customized PropertyEListEditPart to display
					// Properties and filtering those associated with an
					// Association
					if(UMLPackage.ASSOCIATION_CLASS__OWNED_ATTRIBUTE == features[0].getFeatureID()){
						return new PropertyEListEditPart(node, features[0]);
					}
				}
				return new EListEditPart(node, features);
			}else{
				return new ClassFromAssociationClassEditPart(node);
			}
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
		public EditPart caseSignal(org.eclipse.uml2.uml.Signal object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new SignalEditPart(node);
			}
		}
		public EditPart caseReception(org.eclipse.uml2.uml.Reception object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new ReceptionEditPart(node);
			}
		}
		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseExecutionEvent(org.eclipse.uml2.uml.ExecutionEvent)
		 * @generated
		 */
		public EditPart caseExecutionEvent(org.eclipse.uml2.uml.ExecutionEvent object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new ExecutionEventEditPart(node);
			}
		}
		public EditPart caseCreationEvent(org.eclipse.uml2.uml.CreationEvent object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new CreationEventEditPart(node);
			}
		}
		public EditPart caseDestructionEvent(org.eclipse.uml2.uml.DestructionEvent object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new DestructionEventEditPart(node);
			}
		}
		public EditPart caseSendOperationEvent(org.eclipse.uml2.uml.SendOperationEvent object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new SendOperationEventEditPart(node);
			}
		}
		public EditPart caseSendSignalEvent(org.eclipse.uml2.uml.SendSignalEvent object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new SendSignalEventEditPart(node);
			}
		}
		public EditPart caseReceiveOperationEvent(org.eclipse.uml2.uml.ReceiveOperationEvent object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new ReceiveOperationEventEditPart(node);
			}
		}
		public EditPart caseReceiveSignalEvent(org.eclipse.uml2.uml.ReceiveSignalEvent object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new ReceiveSignalEventEditPart(node);
			}
		}
		public EditPart caseCallEvent(org.eclipse.uml2.uml.CallEvent object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new CallEventEditPart(node);
			}
		}
		public EditPart caseAnyReceiveEvent(org.eclipse.uml2.uml.AnyReceiveEvent object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new AnyReceiveEventEditPart(node);
			}
		}
		public EditPart caseChangeEvent(org.eclipse.uml2.uml.ChangeEvent object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new ChangeEventEditPart(node);
			}
		}
		public EditPart caseSignalEvent(org.eclipse.uml2.uml.SignalEvent object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new SignalEventEditPart(node);
			}
		}
		public EditPart caseTimeEvent(org.eclipse.uml2.uml.TimeEvent object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new TimeEventEditPart(node);
			}
		}
		public EditPart defaultCase(EObject object){
			return new EMFGraphNodeEditPart(node);
		}
	}
	private class EdgeUMLSwitch extends UMLSwitch<EditPart>{
		private final class NakedAssociationFigure extends AssociationFigure{
		}
		private GraphEdge edge;
		public EdgeUMLSwitch(GraphEdge edge){
			this.edge = edge;
		}
		public EditPart caseDependency(org.eclipse.uml2.uml.Dependency object){
			return new DependencyEditPart(edge);
		}
		public EditPart caseUsage(org.eclipse.uml2.uml.Usage object){
			return new UsageEditPart(edge);
		}
		public EditPart caseAssociation(org.eclipse.uml2.uml.Association object){
			return new AssociationEditPart(edge);
		}
		public EditPart caseGeneralization(org.eclipse.uml2.uml.Generalization object){
			return new GeneralizationEditPart(edge);
		}
		public EditPart caseInterfaceRealization(org.eclipse.uml2.uml.InterfaceRealization object){
			return new InterfaceRealizationEditPart(edge);
		}
		public EditPart caseTemplateBinding(org.eclipse.uml2.uml.TemplateBinding object){
			return new TemplateBindingEditPart(edge);
		}
		public EditPart casePackageImport(org.eclipse.uml2.uml.PackageImport object){
			return new PackageImportEditPart(edge);
		}
		public EditPart casePackageMerge(org.eclipse.uml2.uml.PackageMerge object){
			return new PackageMergeEditPart(edge);
		}
		public EditPart caseAssociationClass(org.eclipse.uml2.uml.AssociationClass object){
			return new AssociationClassEditPart(edge){
				@Override
				protected IFigure createFigure(){
					return new NakedAssociationFigure();
				}
			};
		}
		public EditPart caseInstanceSpecification(org.eclipse.uml2.uml.InstanceSpecification object){
			return new InstanceSpecificationLinkEditPart(edge);
		}
		public EditPart defaultCase(EObject object){
			return new EMFGraphEdgeEditPart(edge);
		}
	}
}