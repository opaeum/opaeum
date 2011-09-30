/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.uml2.uml.UMLPackage;
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
import org.topcased.modeler.editor.ModelerEditPartFactory;
import org.topcased.modeler.uml.UMLPlugin;
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
import org.topcased.modeler.utils.Utils;

/**
 * Part Factory : associates a model object to its controller. <br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ClassEditPartFactory extends ModelerEditPartFactory
{
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,java.lang.Object)
     * @generated NOT
     */
    public EditPart createEditPart(EditPart context, Object model)
    {
        if (model instanceof Diagram)
        {
            return new ClassDiagramEditPart((Diagram) model);
        }
        else if (model instanceof GraphNode)
        {
            final GraphNode node = (GraphNode) model;
            EObject element = Utils.getElement(node);
            if (element != null)
            {
                if (UMLPlugin.UML_URI.equals(element.eClass().getEPackage().getNsURI()))
                {
                    return new NodeUMLSwitch(node).doSwitch(element);
                }
                else
                {
                    // This is for the extension point org.topcased.modeler.customEditPart
                    return DynamicInstanceEditPartController.instance.getInstanceEditPart(element, node, this.getClass());
                }
            }
        }
        else if (model instanceof GraphEdge)
        {
            final GraphEdge edge = (GraphEdge) model;
            EObject element = Utils.getElement(edge);
            if (element != null)
            {
                if (UMLPlugin.UML_URI.equals(element.eClass().getEPackage().getNsURI()))
                {
                    return new EdgeUMLSwitch(edge).doSwitch(element);
                }
                else
                {
                    // This is for the extension point org.topcased.modeler.customEditPart
                    return DynamicInstanceEditPartController.instance.getInstanceEditPart(element, edge, this.getClass());
                }
            }
            if (edge.getSemanticModel() instanceof SimpleSemanticModelElement)
            {
                if (ClassSimpleObjectConstants.SIMPLE_OBJECT_COMMENTLINK.equals(((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo()))
                {
                    return new CommentLinkEditPart(edge);
                }
                if (AllSimpleObjectConstants.SIMPLE_OBJECT_CONSTRAINTLINK.equals(((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo()))
                {
                    return new ConstraintLinkEditPart(edge);
                }
            }
        }
        return super.createEditPart(context, model);

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private class NodeUMLSwitch extends UMLSwitch<EditPart>
    {
        /** The graphical node */
        private GraphNode node;

        /**
         * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @param node the graphical node
         * @generated
         */
        public NodeUMLSwitch(GraphNode node)
        {
            this.node = node;
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseTemplateSignature(org.eclipse.uml2.uml.TemplateSignature)
         */
        public EditPart caseTemplateSignature(org.eclipse.uml2.uml.TemplateSignature object)
        {
            return new TemplateSignatureEditPart(node);
        }
        
        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseRedefinableTemplateSignature(org.eclipse.uml2.uml.RedefinableTemplateSignature)
         */
        public EditPart caseRedefinableTemplateSignature(org.eclipse.uml2.uml.RedefinableTemplateSignature object)
        {
            return new RedefinableTemplateSignatureEditPart(node);
        }
        
        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseClassifierTemplateParameter(org.eclipse.uml2.uml.ClassifierTemplateParameter)
         */
        public EditPart caseClassifierTemplateParameter(org.eclipse.uml2.uml.ClassifierTemplateParameter object)
        {
            return new ClassifierTemplateParameterEditPart(node);
        }
        
        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#casePackage(org.eclipse.uml2.uml.Package)
         * @generated
         */
        public EditPart casePackage(org.eclipse.uml2.uml.Package object)
        {
            return new PackageEditPart(node);
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseClass(org.eclipse.uml2.uml.Class)
         * @generated NOT
         */
        public EditPart caseClass(org.eclipse.uml2.uml.Class object)
        {
            String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
            if (!"".equals(feature))
            {
                EStructuralFeature[] features = Utils.getEStructuralFeatures(feature, object.eClass());
                if (features.length == 1)
                {
                    // add a customized PropertyEListEditPart to display
                    // Properties and filtering those associated with an
                    // Association
                    if (UMLPackage.CLASS__OWNED_ATTRIBUTE == features[0].getFeatureID())
                    {
                        return new PropertyEListEditPart(node, features[0]);
                    }
                }
                return new EListEditPart(node, features);
            }
            else
            {
                return new ClassEditPart(node);
            }
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseInterface(org.eclipse.uml2.uml.Interface)
         * @generated NOT
         */
        public EditPart caseInterface(org.eclipse.uml2.uml.Interface object)
        {
            String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
            if (!"".equals(feature))
            {
        		return new EListEditPart(node, Utils.getEStructuralFeatures(feature, object.eClass()));
            }
            else
            {
                return new InterfaceEditPart(node);
            }
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseDataType(org.eclipse.uml2.uml.DataType)
         * @generated
         */
        public EditPart caseDataType(org.eclipse.uml2.uml.DataType object)
        {
            String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
            if (!"".equals(feature))
            {
                int featureID = Integer.parseInt(feature);
                return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
            }
            else
            {
                return new DataTypeEditPart(node);
            }
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseOperation(org.eclipse.uml2.uml.Operation)
         * @generated
         */
        public EditPart caseOperation(org.eclipse.uml2.uml.Operation object)
        {
            return new OperationEditPart(node);
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseProperty(org.eclipse.uml2.uml.Property)
         * @generated
         */
        public EditPart caseProperty(org.eclipse.uml2.uml.Property object)
        {
            return new PropertyEditPart(node);
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseInstanceSpecification(org.eclipse.uml2.uml.InstanceSpecification)
         * @generated
         */
        public EditPart caseInstanceSpecification(org.eclipse.uml2.uml.InstanceSpecification object)
        {
            String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
            if (!"".equals(feature))
            {
                int featureID = Integer.parseInt(feature);
                return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
            }
            else
            {
                return new InstanceSpecificationEditPart(node);
            }
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseComment(org.eclipse.uml2.uml.Comment)
         * @generated
         */
        public EditPart caseComment(org.eclipse.uml2.uml.Comment object)
        {
            return new CommentEditPart(node);
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseSlot(org.eclipse.uml2.uml.Slot)
         * @generated
         */
        public EditPart caseSlot(org.eclipse.uml2.uml.Slot object)
        {
            return new SlotEditPart(node);
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseEnumeration(org.eclipse.uml2.uml.Enumeration)
         * @generated
         */
        public EditPart caseEnumeration(org.eclipse.uml2.uml.Enumeration object)
        {
            String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
            if (!"".equals(feature))
            {
                int featureID = Integer.parseInt(feature);
                return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
            }
            else
            {
                return new EnumerationEditPart(node);
            }
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseEnumerationLiteral(org.eclipse.uml2.uml.EnumerationLiteral)
         * @generated
         */
        public EditPart caseEnumerationLiteral(org.eclipse.uml2.uml.EnumerationLiteral object)
        {
            return new EnumerationLiteralEditPart(node);
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#casePrimitiveType(org.eclipse.uml2.uml.PrimitiveType)
         * @generated
         */
        public EditPart casePrimitiveType(org.eclipse.uml2.uml.PrimitiveType object)
        {
            String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
            if (!"".equals(feature))
            {
                int featureID = Integer.parseInt(feature);
                return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
            }
            else
            {
                return new PrimitiveTypeEditPart(node);
            }
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseAssociationClass(org.eclipse.uml2.uml.AssociationClass)
         * @generated NOT
         */
        public EditPart caseAssociationClass(org.eclipse.uml2.uml.AssociationClass object)
        {
            String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
            if (!"".equals(feature))
            {
                EStructuralFeature[] features = Utils.getEStructuralFeatures(feature, object.eClass());
                if (features.length == 1)
                {
                    // add a customized PropertyEListEditPart to display
                    // Properties and filtering those associated with an
                    // Association
                    if (UMLPackage.ASSOCIATION_CLASS__OWNED_ATTRIBUTE == features[0].getFeatureID())
                    {
                        return new PropertyEListEditPart(node, features[0]);
                    }
                }
                return new EListEditPart(node, features);
            }
            else
            {
                return new ClassFromAssociationClassEditPart(node);
            }
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseConstraint(org.eclipse.uml2.uml.Constraint)
         * @generated
         */
        public EditPart caseConstraint(org.eclipse.uml2.uml.Constraint object)
        {
            String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
            if (!"".equals(feature))
            {
                int featureID = Integer.parseInt(feature);
                return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
            }
            else
            {
                return new ConstraintEditPart(node);
            }
        }

		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseSignal(org.eclipse.uml2.uml.Signal)
		 * @generated
		 */
		public EditPart caseSignal(org.eclipse.uml2.uml.Signal object) {
			String feature = DIUtils.getPropertyValue(node,
					ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass()
						.getEStructuralFeature(featureID));
			} else {
				return new SignalEditPart(node);
			}
		}

		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseReception(org.eclipse.uml2.uml.Reception)
		 * @generated
		 */
		public EditPart caseReception(org.eclipse.uml2.uml.Reception object) {
			String feature = DIUtils.getPropertyValue(node,
					ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass()
						.getEStructuralFeature(featureID));
			} else {
				return new ReceptionEditPart(node);
			}
		}
		
		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseExecutionEvent(org.eclipse.uml2.uml.ExecutionEvent)
		 * @generated
		 */
		public EditPart caseExecutionEvent(
				org.eclipse.uml2.uml.ExecutionEvent object) {
			String feature = DIUtils.getPropertyValue(node,
					ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass()
						.getEStructuralFeature(featureID));
			} else {
				return new ExecutionEventEditPart(node);
			}
		}

		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseCreationEvent(org.eclipse.uml2.uml.CreationEvent)
		 * @generated
		 */
		public EditPart caseCreationEvent(
				org.eclipse.uml2.uml.CreationEvent object) {
			String feature = DIUtils.getPropertyValue(node,
					ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass()
						.getEStructuralFeature(featureID));
			} else {
				return new CreationEventEditPart(node);
			}
		}

		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseDestructionEvent(org.eclipse.uml2.uml.DestructionEvent)
		 * @generated
		 */
		public EditPart caseDestructionEvent(
				org.eclipse.uml2.uml.DestructionEvent object) {
			String feature = DIUtils.getPropertyValue(node,
					ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass()
						.getEStructuralFeature(featureID));
			} else {
				return new DestructionEventEditPart(node);
			}
		}

		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseSendOperationEvent(org.eclipse.uml2.uml.SendOperationEvent)
		 * @generated
		 */
		public EditPart caseSendOperationEvent(
				org.eclipse.uml2.uml.SendOperationEvent object) {
			String feature = DIUtils.getPropertyValue(node,
					ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass()
						.getEStructuralFeature(featureID));
			} else {
				return new SendOperationEventEditPart(node);
			}
		}

		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseSendSignalEvent(org.eclipse.uml2.uml.SendSignalEvent)
		 * @generated
		 */
		public EditPart caseSendSignalEvent(
				org.eclipse.uml2.uml.SendSignalEvent object) {
			String feature = DIUtils.getPropertyValue(node,
					ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass()
						.getEStructuralFeature(featureID));
			} else {
				return new SendSignalEventEditPart(node);
			}
		}

		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseReceiveOperationEvent(org.eclipse.uml2.uml.ReceiveOperationEvent)
		 * @generated
		 */
		public EditPart caseReceiveOperationEvent(
				org.eclipse.uml2.uml.ReceiveOperationEvent object) {
			String feature = DIUtils.getPropertyValue(node,
					ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass()
						.getEStructuralFeature(featureID));
			} else {
				return new ReceiveOperationEventEditPart(node);
			}
		}

		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseReceiveSignalEvent(org.eclipse.uml2.uml.ReceiveSignalEvent)
		 * @generated
		 */
		public EditPart caseReceiveSignalEvent(
				org.eclipse.uml2.uml.ReceiveSignalEvent object) {
			String feature = DIUtils.getPropertyValue(node,
					ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass()
						.getEStructuralFeature(featureID));
			} else {
				return new ReceiveSignalEventEditPart(node);
			}
		}

		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseCallEvent(org.eclipse.uml2.uml.CallEvent)
		 * @generated
		 */
		public EditPart caseCallEvent(org.eclipse.uml2.uml.CallEvent object) {
			String feature = DIUtils.getPropertyValue(node,
					ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass()
						.getEStructuralFeature(featureID));
			} else {
				return new CallEventEditPart(node);
			}
		}

		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseAnyReceiveEvent(org.eclipse.uml2.uml.AnyReceiveEvent)
		 * @generated
		 */
		public EditPart caseAnyReceiveEvent(
				org.eclipse.uml2.uml.AnyReceiveEvent object) {
			String feature = DIUtils.getPropertyValue(node,
					ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass()
						.getEStructuralFeature(featureID));
			} else {
				return new AnyReceiveEventEditPart(node);
			}
		}

		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseChangeEvent(org.eclipse.uml2.uml.ChangeEvent)
		 * @generated
		 */
		public EditPart caseChangeEvent(org.eclipse.uml2.uml.ChangeEvent object) {
			String feature = DIUtils.getPropertyValue(node,
					ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass()
						.getEStructuralFeature(featureID));
			} else {
				return new ChangeEventEditPart(node);
			}
		}

		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseSignalEvent(org.eclipse.uml2.uml.SignalEvent)
		 * @generated
		 */
		public EditPart caseSignalEvent(org.eclipse.uml2.uml.SignalEvent object) {
			String feature = DIUtils.getPropertyValue(node,
					ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass()
						.getEStructuralFeature(featureID));
			} else {
				return new SignalEventEditPart(node);
			}
		}

		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseTimeEvent(org.eclipse.uml2.uml.TimeEvent)
		 * @generated
		 */
		public EditPart caseTimeEvent(org.eclipse.uml2.uml.TimeEvent object) {
			String feature = DIUtils.getPropertyValue(node,
					ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass()
						.getEStructuralFeature(featureID));
			} else {
				return new TimeEventEditPart(node);
			}
		}
        
        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
         * @generated
         */
        public EditPart defaultCase(EObject object)
        {
            return new EMFGraphNodeEditPart(node);
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private class EdgeUMLSwitch extends UMLSwitch<EditPart>
    {
        /** The graphical edge */
        private GraphEdge edge;

        /**
         * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @param edge the graphical edge
         * @generated
         */
        public EdgeUMLSwitch(GraphEdge edge)
        {
            this.edge = edge;
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseDependency(org.eclipse.uml2.uml.Dependency)
         * @generated
         */
        public EditPart caseDependency(org.eclipse.uml2.uml.Dependency object)
        {
            return new DependencyEditPart(edge);
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseUsage(org.eclipse.uml2.uml.Usage)
         * @generated
         */
        public EditPart caseUsage(org.eclipse.uml2.uml.Usage object)
        {
            return new UsageEditPart(edge);
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseAssociation(org.eclipse.uml2.uml.Association)
         * @generated
         */
        public EditPart caseAssociation(org.eclipse.uml2.uml.Association object)
        {
            return new AssociationEditPart(edge);
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseGeneralization(org.eclipse.uml2.uml.Generalization)
         * @generated
         */
        public EditPart caseGeneralization(org.eclipse.uml2.uml.Generalization object)
        {
            return new GeneralizationEditPart(edge);
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseInterfaceRealization(org.eclipse.uml2.uml.InterfaceRealization)
         * @generated
         */
        public EditPart caseInterfaceRealization(org.eclipse.uml2.uml.InterfaceRealization object)
        {
            return new InterfaceRealizationEditPart(edge);
        }
        
        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseTemplateBinding(org.eclipse.uml2.uml.TemplateBinding)
         */
        public EditPart caseTemplateBinding(org.eclipse.uml2.uml.TemplateBinding object)
        {
            return new TemplateBindingEditPart(edge);
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#casePackageImport(org.eclipse.uml2.uml.PackageImport)
         * @generated
         */
        public EditPart casePackageImport(org.eclipse.uml2.uml.PackageImport object)
        {
            return new PackageImportEditPart(edge);
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#casePackageMerge(org.eclipse.uml2.uml.PackageMerge)
         * @generated
         */
        public EditPart casePackageMerge(org.eclipse.uml2.uml.PackageMerge object)
        {
            return new PackageMergeEditPart(edge);
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseAssociationClass(org.eclipse.uml2.uml.AssociationClass)
         * @generated
         */
        public EditPart caseAssociationClass(org.eclipse.uml2.uml.AssociationClass object)
        {
            return new AssociationClassEditPart(edge);
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseInstanceSpecification(org.eclipse.uml2.uml.InstanceSpecification)
         * @generated
         */
        public EditPart caseInstanceSpecification(org.eclipse.uml2.uml.InstanceSpecification object)
        {
            return new InstanceSpecificationLinkEditPart(edge);
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
         * @generated
         */
        public EditPart defaultCase(EObject object)
        {
            return new EMFGraphEdgeEditPart(edge);
        }
    }

}