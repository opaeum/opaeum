/*******************************************************************************
 * Copyright (c) 2006, 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.StructuralFeature;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.ILabelFigure;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.uml.UMLLabel;
import org.topcased.modeler.uml.classdiagram.commands.SlotRestoreConnectionCommand;
import org.topcased.modeler.uml.classdiagram.figures.SlotFigure;
import org.topcased.modeler.uml.classdiagram.preferences.ClassDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * The Slot object controller <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class SlotEditPart extends EMFGraphNodeEditPart
{
    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param obj the graph node
     * @generated
     */
    public SlotEditPart(GraphNode obj)
    {
        super(obj);
    }

    /**
     * Creates edit policies and associates these with roles <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    protected void createEditPolicies()
    {
        super.createEditPolicies();

        installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy()
        {
            @Override
            protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request)
            {
                return new SlotRestoreConnectionCommand(getHost());
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     * @generated
     */
    @Override
    protected IFigure createFigure()
    {

        return new SlotFigure();
    }

    /**
     * Prevent the Slot to be editable in the editor
     * 
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getEditableLabel()
     */
    @Override
    public ILabel getEditableLabel()
    {
        return null;
    }

    /**
     * Set the label for the slot
     * 
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshHeaderLabel()
     */
    @Override
    protected void refreshHeaderLabel()
    {
        ComposedLabel lbl = (ComposedLabel) ((ILabelFigure) getFigure()).getLabel();
        Slot slot = (Slot) getEObject();

        StringBuffer prefix = new StringBuffer();
        prefix.append(UMLLabel.getStereotypesNotation(slot, getPreferenceStore()));
        if (slot.getDefiningFeature() != null)
        {
            prefix.append(slot.getDefiningFeature().getName());
        }
        prefix.append(" = ");
        lbl.setPrefix(prefix.toString());

        if (slot.getValues().size() != 0)
        {
            lbl.setMain(slot.getValues().get(0).stringValue());
        }
        else
        {
            lbl.setMain("valueUndefined");
        }
    }

    /**
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#handleModelChanged(org.eclipse.emf.common.notify.Notification)
     */
    @Override
    protected void handleModelChanged(Notification msg)
    {
        Object notifier = msg.getNotifier();
        Object newObject = msg.getNewValue();
        Object oldObject = msg.getOldValue();

        if (notifier instanceof Slot)
        {
            if (notifier == getEObject())
            {
                switch (msg.getFeatureID(Slot.class))
                {
                    case UMLPackage.SLOT__DEFINING_FEATURE:
                    case UMLPackage.SLOT__VALUE:
                        updateModelListening(oldObject, newObject);
                        refreshHeaderLabel();
                        break;
                    default:
                        break;
                }
            }
        }
        else if (notifier instanceof StructuralFeature)
        {
            switch (msg.getFeatureID(NamedElement.class))
            {
                case UMLPackage.NAMED_ELEMENT__NAME:
                    refreshHeaderLabel();
                    break;
                default:
                    break;
            }
        }
        else if (notifier instanceof ValueSpecification)
        {
            refreshHeaderLabel();
        }
    }

    /**
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#activate()
     */
    @Override
    public void activate()
    {
        super.activate();
        EObject model = getEObject();
        if (model instanceof Slot)
        {
            listenFeature((Slot) getEObject());
            listenValues((Slot) getEObject());
        }
    }

    /**
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#deactivate()
     */
    @Override
    public void deactivate()
    {
        EObject model = getEObject();
        if (model instanceof Slot)
        {
            unlistenValues((Slot) getEObject());
            unlistenFeature((Slot) getEObject());
        }
        super.deactivate();
    }

    /**
     * Listen to the values of the slot
     * 
     * @param slot the model object
     */
    private void listenValues(Slot slot)
    {
        for (ValueSpecification value : slot.getValues())
        {
            // Only listen to object that are not yet listened
            if (!value.eAdapters().contains(getModelListener()))
            {
                value.eAdapters().add(getModelListener());
            }
        }
    }

    /**
     * Stop listening to the values of the slot
     * 
     * @param slot the model object
     */
    private void unlistenValues(Slot slot)
    {
        for (ValueSpecification value : slot.getValues())
        {
            value.eAdapters().remove(getModelListener());
        }
    }

    /**
     * Listen to the Feature of the property
     * 
     * @param slot the model object
     */
    private void listenFeature(Slot slot)
    {
        // Only listen to object that are not yet listened
        if (slot.getDefiningFeature() != null && !slot.getDefiningFeature().eAdapters().contains(getModelListener()))
        {
            slot.getDefiningFeature().eAdapters().add(getModelListener());
        }
    }

    /**
     * Stop listening to the Feature of the property
     * 
     * @param slot the model object
     */
    private void unlistenFeature(Slot slot)
    {
        if (slot.getDefiningFeature() != null)
        {
            slot.getDefiningFeature().eAdapters().remove(getModelListener());
        }
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
     * 
     * @generated
     */
    @Override
    protected Color getPreferenceDefaultBackgroundColor()
    {
        String backgroundColor = getPreferenceStore().getString(ClassDiagramPreferenceConstants.SLOT_DEFAULT_BACKGROUND_COLOR);
        if (backgroundColor.length() != 0)
        {
            return Utils.getColor(backgroundColor);
        }
        return null;
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultForegroundColor()
     * 
     * @generated
     */
    @Override
    protected Color getPreferenceDefaultForegroundColor()
    {
        String foregroundColor = getPreferenceStore().getString(ClassDiagramPreferenceConstants.SLOT_DEFAULT_FOREGROUND_COLOR);
        if (foregroundColor.length() != 0)
        {
            return Utils.getColor(foregroundColor);
        }
        return null;
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultFont()
     * 
     * @generated
     */
    @Override
    protected Font getPreferenceDefaultFont()
    {
        String preferenceFont = getPreferenceStore().getString(ClassDiagramPreferenceConstants.SLOT_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;

    }

}