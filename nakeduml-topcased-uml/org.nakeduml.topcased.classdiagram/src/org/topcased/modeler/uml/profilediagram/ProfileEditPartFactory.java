/*******************************************************************************
 * Copyright (c) 2006, 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.profilediagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.topcased.modeler.ModelerPropertyConstants;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.di.model.util.DIUtils;
import org.topcased.modeler.edit.EListEditPart;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.GraphEdgeEditPart;
import org.topcased.modeler.editor.ModelerEditPartFactory;
import org.topcased.modeler.uml.alldiagram.edit.DependencyEditPart;
import org.topcased.modeler.uml.classdiagram.edit.PropertyEListEditPart;
import org.topcased.modeler.uml.classdiagram.edit.PropertyEditPart;
import org.topcased.modeler.uml.profilediagram.edit.ElementImportEditPart;
import org.topcased.modeler.uml.profilediagram.edit.ExtensionEditPart;
import org.topcased.modeler.uml.profilediagram.edit.GeneralizationEditPart;
import org.topcased.modeler.uml.profilediagram.edit.ProfileDiagramEditPart;
import org.topcased.modeler.uml.profilediagram.edit.StereotypeEditPart;
import org.topcased.modeler.utils.Utils;

/**
 * Part Factory : associates a model object to its controller. <br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ProfileEditPartFactory extends ModelerEditPartFactory
{
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,java.lang.Object)
     * @generated
     */
    public EditPart createEditPart(EditPart context, Object model)
    {
        if (model instanceof Diagram)
        {
            return new ProfileDiagramEditPart((Diagram) model);
        }
        else if (model instanceof GraphNode)
        {
            final GraphNode node = (GraphNode) model;
            EObject element = Utils.getElement(node);
            if (element != null)
            {
                if ("http://www.eclipse.org/uml2/3.0.0/UML".equals(element.eClass().getEPackage().getNsURI()))
                {
                    return new NodeUMLSwitch(node).doSwitch(element);
                }
            }
        }
        else if (model instanceof GraphEdge)
        {
            final GraphEdge edge = (GraphEdge) model;
            EObject element = Utils.getElement(edge);
            if (element != null)
            {
                if ("http://www.eclipse.org/uml2/3.0.0/UML".equals(element.eClass().getEPackage().getNsURI()))
                {
                    return new EdgeUMLSwitch(edge).doSwitch(element);
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
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseStereotype(org.eclipse.uml2.uml.Stereotype)
         * @generated NOT
         */
        public EditPart caseStereotype(org.eclipse.uml2.uml.Stereotype object)
        {
            String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
            if (!"".equals(feature))
            {
                int featureID = Integer.parseInt(feature);

                // add a customized PropertyEListEditPart to display Properties and filtering those associated with the
                // Metaclass(es)
                if (UMLPackage.STEREOTYPE__OWNED_ATTRIBUTE == featureID)
                {
                    return new PropertyEListEditPart(node, object.eClass().getEStructuralFeature(featureID));
                }

                return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
            }
            else
            {
                return new StereotypeEditPart(node);
            }
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
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseElementImport(org.eclipse.uml2.uml.ElementImport)
         * @generated
         */
        public EditPart caseElementImport(org.eclipse.uml2.uml.ElementImport object)
        {
            return new ElementImportEditPart(node);
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
    private class EdgeUMLSwitch extends UMLSwitch<GraphEdgeEditPart>
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
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseDependency(org.eclipse.uml2.uml.Dependency)
         * @generated
         */
        public GraphEdgeEditPart caseDependency(org.eclipse.uml2.uml.Dependency object)
        {
            return new DependencyEditPart(edge);
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseGeneralization(org.eclipse.uml2.uml.Generalization)
         * @generated
         */
        public GraphEdgeEditPart caseGeneralization(org.eclipse.uml2.uml.Generalization object)
        {
            return new GeneralizationEditPart(edge);
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseExtension(org.eclipse.uml2.uml.Extension)
         * @generated
         */
        public GraphEdgeEditPart caseExtension(org.eclipse.uml2.uml.Extension object)
        {
            return new ExtensionEditPart(edge);
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
         * @generated
         */
        public GraphEdgeEditPart defaultCase(EObject object)
        {
            return new EMFGraphEdgeEditPart(edge);
        }
    }

}