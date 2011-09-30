/*******************************************************************************
 * Copyright (c) 2006 Anyware Technologies. All rights reserved. This program
 * and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.alldiagram.commands;

import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.topcased.modeler.commands.ReconnectGraphEdgeCommand;
import org.topcased.modeler.di.model.GraphConnector;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.uml.classdiagram.util.AssociationHelper;
import org.topcased.modeler.utils.Utils;

/**
 * This command handles the reconnection of associations links. Indeed they connect Type by updating their properties.
 * 
 * @Created 2 févr. 07
 * @author <a href="mailto:alfredo@anyware-tech.com">Jose Alfredo SERRANO</a>
 * @author <a href="mailto:maxime.leray@atosorigin.com">Maxime Leray</a>
 */
public class ReconnectAssociationEdgeCommand extends ReconnectGraphEdgeCommand
{

    /** Reconnecting the source end. */
    public static final int SOURCE = 1;

    /** Reconnecting the target end. */
    public static final int TARGET = 2;

    /** Memorize the end which is reconnected. */
    private int reconnectionEnd = 0;

    /** Keeping a trace of the old referenced type. */
    private Type oldType;

    /**
     * Constructor.
     * 
     * @param connection The connection EditPart
     * @param reconnectionEnd The type of end to be reconnected
     */
    public ReconnectAssociationEdgeCommand(ConnectionEditPart connection, int reconnectionEnd)
    {
        super(connection);
        this.reconnectionEnd = reconnectionEnd;
    }

    /**
     * Gets the old graph element.
     * 
     * @param connection the connection
     * 
     * @return the old graph element
     * 
     * @see org.topcased.modeler.commands.ReconnectGraphEdgeCommand#getOldGraphElement(org.eclipse.gef.ConnectionEditPart)
     */
    protected GraphElement getOldGraphElement(ConnectionEditPart connection)
    {
        return null;
    }

    /**
     * Redo model.
     * 
     * @see org.topcased.modeler.commands.ReconnectGraphEdgeCommand#redoModel()
     */
    protected void redoModel()
    {
        // Create the association and add it to the current package
        Association association = (Association) Utils.getElement(getEdge());

        Type type = (Type) Utils.getElement(getNewElement());
        if (reconnectionEnd == SOURCE)
        {
            oldType = association.getMemberEnds().get(0).getType();
            association.getMemberEnds().get(0).setType(type);
            AssociationHelper helper = new AssociationHelper(association);
            if (helper.isAssociationComposite())
            {
                Element element = getCompositeAssociationSourceProperty(association, oldType);
                if (element instanceof Property)
                {
                    Property property = (Property) element;
                    movePropertyTo(property, type);
                }
            }
        }
        else if (reconnectionEnd == TARGET)
        {
            oldType = association.getMemberEnds().get(1).getType();
            association.getMemberEnds().get(1).setType(type);
        }
    }

    /**
     * In case of a composite association, it returns the property owned by the given that matches the second end of the
     * association.
     * 
     * @param association the association
     * @param type the type
     * 
     * @return the delegated property
     */
    private Element getCompositeAssociationSourceProperty(Association association, Type type)
    {
        for (Element element : type.getOwnedElements())
        {
            if (element instanceof TypedElement)
            {
                TypedElement typedElement = (TypedElement) element;
                if (typedElement.getType() != null && typedElement.getType().equals(association.getMemberEnds().get(1).getType()))
                {
                    return typedElement;
                }

            }
        }
        return null;
    }

    /**
     * Move property to the the given type. It shall be an instance of Class, Interface, DataType or Signal.
     * 
     * @param property the property
     * @param hostType the type
     */
    private void movePropertyTo(Property property, Type hostType)
    {
        if (hostType instanceof Class)
        {
            Class aClass = (Class) hostType;
            aClass.getOwnedAttributes().add(property);
        }
        else if (hostType instanceof Interface)
        {
            Interface anInterface = (Interface) hostType;
            anInterface.getOwnedAttributes().add(property);
        }
        else if (hostType instanceof DataType)
        {
            DataType aDataType = (DataType) hostType;
            aDataType.getOwnedAttributes().add(property);
        }
        else if (hostType instanceof Signal)
        {
            Signal aSignal = (Signal) hostType;
            aSignal.getOwnedAttributes().add(property);
        }
    }

    /**
     * Undo model.
     * 
     * @see org.topcased.modeler.commands.ReconnectGraphEdgeCommand#undoModel()
     */
    protected void undoModel()
    {
        // Create the association and add it to the current package
        Association association = (Association) Utils.getElement(getEdge());
        if (reconnectionEnd == SOURCE)
        {
            Type newType = association.getMemberEnds().get(0).getType();
            association.getMemberEnds().get(0).setType(oldType);
            AssociationHelper helper = new AssociationHelper(association);
            if (helper.isAssociationComposite())
            {
                Element element = getCompositeAssociationSourceProperty(association, newType);
                if (element instanceof Property)
                {
                    Property property = (Property) element;
                    movePropertyTo(property, oldType);
                }
            }
        }
        else if (reconnectionEnd == TARGET)
        {
            association.getMemberEnds().get(1).setType(oldType);
        }
    }

    /**
     * Update connectors.
     * 
     * @param newConnector the new connector
     * @param oldConnector the old connector
     * @param attachedConnector the attached connector
     * 
     * @see org.topcased.modeler.commands.ReconnectGraphEdgeCommand#updateConnectors(org.topcased.modeler.di.model.GraphConnector,
     *      org.topcased.modeler.di.model.GraphConnector, org.topcased.modeler.di.model.GraphConnector)
     */
    protected void updateConnectors(GraphConnector newConnector, GraphConnector oldConnector, GraphConnector attachedConnector)
    {
        GraphEdge edge = getEdge();

        if (reconnectionEnd == SOURCE)
        {
            oldConnector.getGraphEdge().remove(edge);
            attachedConnector.getGraphEdge().remove(edge);
            edge.getAnchor().clear();
            newConnector.getGraphEdge().add(edge);
            attachedConnector.getGraphEdge().add(edge);
        }
        else if (reconnectionEnd == TARGET)
        {
            oldConnector.getGraphEdge().remove(edge);
            newConnector.getGraphEdge().add(edge);
        }
    }
}
