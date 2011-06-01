/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.edit;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.topcased.draw2d.figures.PackageFigure;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.uml.classdiagram.ClassEditPolicyConstants;
import org.topcased.modeler.uml.classdiagram.ClassElementsVisibilityConstants;
import org.topcased.modeler.uml.classdiagram.commands.ClassifierRestoreConnectionCommand;
import org.topcased.modeler.uml.classdiagram.figures.TemplateSignatureFigure;
import org.topcased.modeler.uml.classdiagram.policies.GeneralizationEdgeCreationEditPolicy;
import org.topcased.modeler.uml.classdiagram.util.ClassifierHelper;

/**
 * The Classifier object controller <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public abstract class ClassifierEditPart extends TypeEditPart
{

    private ClassifierHelper helper;

    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param obj the graph node
     * @generated
     */
    public ClassifierEditPart(GraphNode obj)
    {
        super(obj);
        this.getFigure().addFigureListener(new FigureListener()
        {
            public void figureMoved(IFigure source)
            {
                Figure classFigure = (Figure) ClassifierEditPart.this.getFigure();
                for (Object obj : ClassifierEditPart.this.getChildren())
                {
                    if (obj instanceof TemplateSignatureEditPart)
                    {
                        TemplateSignatureEditPart part = (TemplateSignatureEditPart) obj;
                        GraphNode node = (GraphNode) part.getModel();
                        node.setPosition(calculateChildPosition(classFigure, part));
                    }
                }
            }
        });
    }

    /**
     * Creates edit policies and associates these with roles <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected void createEditPolicies()
    {
        super.createEditPolicies();

        installEditPolicy(ClassEditPolicyConstants.GENERALIZATION_EDITPOLICY, new GeneralizationEdgeCreationEditPolicy());

        installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy()
        {
            @Override
            protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request)
            {
                return new ClassifierRestoreConnectionCommand(getHost());
            }
        });

        installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, new ResizableEditPolicy());

    }

    /**
     * Adds the child's Figure to the {@link #getContentPane() contentPane}.
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#addChildVisual(EditPart, int)
     */
    @Override
    protected void addChildVisual(EditPart childEditPart, int index)
    {
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        if (child instanceof TemplateSignatureFigure)
        {
            getFigure().getParent().add(child, -1);
        }
        else
        {
            super.addChildVisual(childEditPart, index);
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#removeChildVisual(org.eclipse.gef.EditPart)
     */
    @Override
    protected void removeChildVisual(EditPart childEditPart)
    {
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        if (child instanceof TemplateSignatureFigure)
        {
            getFigure().getParent().remove(child);
        }
        else
        {
            super.removeChildVisual(childEditPart);
        }
    }

    /**
     * Refresh the visibility of elements of the operations.
     */
    private void refreshOperations()
    {
        Collection<OperationEditPart> list = new HashSet<OperationEditPart>();
        getChildren(OperationEditPart.class, list, this);
        for (OperationEditPart part : list)
        {
            refreshOperationPart(part);
        }
    }

    /**
     * Refreshes the operation part.
     * 
     * @param part The operation part.
     */
    public void refreshOperationPart(OperationEditPart part)
    {
        part.setElementVisible(ClassElementsVisibilityConstants.SHOW_OPERATION_RETURN_TYPE, getClassHelper().showOperationReturnType());
        part.setElementVisible(ClassElementsVisibilityConstants.SHOW_OPERATION_PARAMETERS, getClassHelper().showOperationParameters());
        part.setElementVisible(ClassElementsVisibilityConstants.SHOW_OPERATION_PARAMETER_TYPE, getClassHelper().showOperationParametersType());
        part.setElementVisible(ClassElementsVisibilityConstants.SHOW_OPERATION_PARAMETER_DEFAULT_VALUE, getClassHelper().showOperationParameterDefaultValue());
    }

    /**
     * Refresh the visibility of elements of the properties.
     */
    private void refreshProperties()
    {
        Collection<PropertyEditPart> list = new HashSet<PropertyEditPart>();
        getChildren(PropertyEditPart.class, list, this);
        for (PropertyEditPart part : list)
        {
            refreshPropertyPart(part);
        }
    }

    /**
     * Refresh the property part.
     * 
     * @param part The part.
     */
    public void refreshPropertyPart(PropertyEditPart part)
    {
        part.setElementVisible(ClassElementsVisibilityConstants.SHOW_PROPERTY_DEFAULT_VALUE, getClassHelper().showPropertyDefaultValue());
        part.setElementVisible(ClassElementsVisibilityConstants.SHOW_PROPERTY_TYPE, getClassHelper().showPropertyType());
    }

    /**
     * Returns the list of parts that are assignable from a given type.
     * 
     * @param <T> The class of the editpart.
     * @param clazz The class.
     * @param returnList The list where all parts will be added.
     * @param parentPart The parent part.
     */
    private <T extends EditPart> void getChildren(java.lang.Class<T> clazz, Collection<T> returnList, EditPart parentPart)
    {
        for (EditPart part : (List<EditPart>) parentPart.getChildren())
        {
            if (clazz.isAssignableFrom(part.getClass()))
            {
                returnList.add((T) part);
            }
            else
            {
                getChildren(clazz, returnList, part);
            }
        }
    }

    /**
     * Returns the class helper.
     * 
     * @return The class helper.
     */
    private ClassifierHelper getClassHelper()
    {
        if (helper == null)
        {
            helper = new ClassifierHelper(getPreferenceStore(), getGraphNode(), getClass());
        }
        return helper;
    }

    /**
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals()
    {
        refreshOperations();
        refreshProperties();
        super.refreshVisuals();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshChildren()
     */
    @Override
    protected void refreshChildren()
    {
        super.refreshChildren();
        refreshOperations();
        refreshProperties();
    }

    /**
     * Calculate the template signature position when the figure moved
     * 
     * @param figure the figure
     * @param part the edit part of the template signature
     * 
     * @return the point
     */
    private Point calculateChildPosition(Figure figure, TemplateSignatureEditPart part)
    {
        Figure parentFigure = (Figure) ((GraphicalEditPart) getParent()).getFigure();
        Point parentLocation = parentFigure.getLocation();

        Point location = figure.getLocation();
        Point point = null;
        if (parentFigure instanceof PackageFigure)
        {
            point = new Point(location.x + figure.getSize().width - parentLocation.x - 12, location.y - parentLocation.y - 45);
        }
        else
        {
            point = new Point(location.x + figure.getSize().width - 10, location.y - 52);
        }
        return point;
    }

}