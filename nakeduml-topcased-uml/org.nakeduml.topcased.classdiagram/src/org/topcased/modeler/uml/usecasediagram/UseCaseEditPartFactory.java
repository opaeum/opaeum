/*******************************************************************************
 * Copyright (c) 2005, 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *   Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware Technologies),
 *   Nicolas Lalev�e (Anyware Technologies) - initial API and implementation 
 *   Maxime Nauleau (Atos Origin) - Fix #792
 ******************************************************************************/

package org.topcased.modeler.uml.usecasediagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.PackageMerge;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.di.model.SimpleSemanticModelElement;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.DynamicInstanceEditPartController;
import org.topcased.modeler.editor.ModelerEditPartFactory;
import org.topcased.modeler.uml.alldiagram.edit.CommentEditPart;
import org.topcased.modeler.uml.alldiagram.edit.CommentLinkEditPart;
import org.topcased.modeler.uml.alldiagram.edit.ConstraintEditPart;
import org.topcased.modeler.uml.alldiagram.edit.ConstraintLinkEditPart;
import org.topcased.modeler.uml.alldiagram.edit.DependencyEditPart;
import org.topcased.modeler.uml.alldiagram.edit.PackageEditPart;
import org.topcased.modeler.uml.alldiagram.edit.PackageImportEditPart;
import org.topcased.modeler.uml.alldiagram.edit.PackageMergeEditPart;
import org.topcased.modeler.uml.usecasediagram.edit.ActorEditPart;
import org.topcased.modeler.uml.usecasediagram.edit.AssociationEditPart;
import org.topcased.modeler.uml.usecasediagram.edit.BoundaryBoxEditPart;
import org.topcased.modeler.uml.usecasediagram.edit.ExtendEditPart;
import org.topcased.modeler.uml.usecasediagram.edit.GeneralizationEditPart;
import org.topcased.modeler.uml.usecasediagram.edit.IncludeEditPart;
import org.topcased.modeler.uml.usecasediagram.edit.UseCaseDiagramEditPart;
import org.topcased.modeler.uml.usecasediagram.edit.UseCaseEditPart;
import org.topcased.modeler.utils.Utils;

/**
 * Part Factory : associates a model object to its controller. <br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class UseCaseEditPartFactory extends ModelerEditPartFactory
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
            return new UseCaseDiagramEditPart((Diagram) model);
        }
        else if (model instanceof GraphNode)
        {
            final GraphNode node = (GraphNode) model;
            EObject element = Utils.getElement(node);
            if (element != null)
            {
                EditPart editPart = new UMLSwitch<EditPart>()
                {
                    public EditPart casePackage(Package object)
                    {
                        return new PackageEditPart(node);
                    }

                    public EditPart caseActor(Actor object)
                    {
                        return new ActorEditPart(node);
                    }

                    public EditPart caseUseCase(UseCase object)
                    {
                        return new UseCaseEditPart(node);
                    }

                    public EditPart caseComment(Comment object)
                    {
                        return new CommentEditPart(node);
                    }
                    public EditPart caseConstraint(Constraint object)
                    {
                        return new ConstraintEditPart(node);
                    }

                    public EditPart defaultCase(EObject object)
                    {
                        // This is for the extension point org.topcased.modeler.customEditPart
                        EditPart editPart = null;

                        editPart = DynamicInstanceEditPartController.instance.getInstanceEditPart(Utils.getElement(node), node, this.getClass());
                        if (editPart == null)
                        {
                            editPart = new EMFGraphNodeEditPart(node);
                        }
                        return editPart;
                    }
                }.doSwitch(element);
                return editPart;
            }

            //Fix #792
            if (node.getSemanticModel() instanceof SimpleSemanticModelElement)
            {
                // Manage the Element that are not associated with a model object 
                if (((SimpleSemanticModelElement) node.getSemanticModel()).getTypeInfo().equals(UseCaseSimpleObjectConstants.SIMPLE_OBJECT_BOUNDARYBOX))
                {
                    return new BoundaryBoxEditPart(node);
                }
            }
            //EndFix #792
        }
        else if (model instanceof GraphEdge)
        {
            final GraphEdge edge = (GraphEdge) model;
            EObject element = Utils.getElement(edge);
            if (element != null)
            {
                EditPart editPart = new UMLSwitch<EditPart>()
                {
                    public EditPart caseExtend(Extend object)
                    {
                        return new ExtendEditPart(edge);
                    }

                    public EditPart caseInclude(Include object)
                    {
                        return new IncludeEditPart(edge);
                    }

                    public EditPart caseAssociation(Association object)
                    {
                        return new AssociationEditPart(edge);
                    }

                    public EditPart caseGeneralization(Generalization object)
                    {
                        return new GeneralizationEditPart(edge);
                    }

                    public EditPart casePackageImport(PackageImport object)
                    {
                    	return new PackageImportEditPart(edge);
                    }
                    
                    public EditPart casePackageMerge(PackageMerge object)
                    {
                    	return new PackageMergeEditPart(edge);
                    }
                    
                    public EditPart caseDependency(Dependency object)
                    {
                    	return new DependencyEditPart(edge);
                    }
                    
                    
                    public EditPart defaultCase(EObject object)
                    {
                        // This is for the extension point org.topcased.modeler.customEditPart
                        EditPart editPart = null;

                        editPart = DynamicInstanceEditPartController.instance.getInstanceEditPart(Utils.getElement(edge), edge, this.getClass());
                        if (editPart == null)
                        {
                            editPart = new EMFGraphEdgeEditPart(edge);
                        }
                        return editPart;
                    }
                }.doSwitch(element);
                return editPart;
            }
            if (edge.getSemanticModel() instanceof SimpleSemanticModelElement)
            {
                if (((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo().equals(UseCaseSimpleObjectConstants.SIMPLE_OBJECT_COMMENTLINK))
                {
                    return new CommentLinkEditPart(edge);
                }
                if (((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo().equals(UseCaseSimpleObjectConstants.SIMPLE_OBJECT_CONSTRAINTLINK))
                {
                    return new ConstraintLinkEditPart(edge);
                }
                
            }
        }

        return super.createEditPart(context, model);
    }

}