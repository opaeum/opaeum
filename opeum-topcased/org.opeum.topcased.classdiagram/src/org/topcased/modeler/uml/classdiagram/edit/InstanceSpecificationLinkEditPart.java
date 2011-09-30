/*******************************************************************************
 * Copyright (c) 2006, 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.topcased.draw2d.figures.Label;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.EdgeObject;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.edit.policies.EdgeObjectOffsetEditPolicy;
import org.topcased.modeler.figures.IEdgeObjectFigure;
import org.topcased.modeler.uml.alldiagram.policies.CommentLinkEdgeCreationEditPolicy;
import org.topcased.modeler.uml.alldiagram.policies.ConstraintLinkEdgeCreationEditPolicy;
import org.topcased.modeler.uml.classdiagram.ClassEdgeObjectConstants;
import org.topcased.modeler.uml.classdiagram.ClassEditPolicyConstants;
import org.topcased.modeler.uml.classdiagram.figures.InstanceSpecificationLinkFigure;
import org.topcased.modeler.uml.classdiagram.policies.InstanceSpecificationLinkEdgeObjectUVEditPolicy;
import org.topcased.modeler.uml.classdiagram.preferences.ClassDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * The InstanceSpecification object controller linking 2 instances specification.
 * 
 * @author <a href="mailto:thomas.szadel@atosorigin.com">Thomas Szadel</a>
 */
public class InstanceSpecificationLinkEditPart extends EMFGraphEdgeEditPart
{
    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param obj the graph object
     * @generated
     */
    public InstanceSpecificationLinkEditPart(GraphEdge obj)
    {
        super(obj);
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure()
    {
        return new InstanceSpecificationLinkFigure();
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

        installEditPolicy(ClassEditPolicyConstants.COMMENTLINK_EDITPOLICY, new CommentLinkEdgeCreationEditPolicy());
        installEditPolicy(ClassEditPolicyConstants.CONSTRAINTLINK_EDITPOLICY, new ConstraintLinkEdgeCreationEditPolicy());
        installEditPolicy(ModelerEditPolicyConstants.EDGE_OBJECTS_UV_EDITPOLICY, new InstanceSpecificationLinkEdgeObjectUVEditPolicy());
        installEditPolicy(ModelerEditPolicyConstants.EDGE_OBJECTS_OFFSET_EDITPOLICY, new EdgeObjectOffsetEditPolicy());
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getEdgeObjectFigure(org.topcased.modeler.di.model.EdgeObject)
     * @generated
     */
    @Override
    public IEdgeObjectFigure getEdgeObjectFigure(EdgeObject edgeObject)
    {
        if (ClassEdgeObjectConstants.SRCNAME_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((InstanceSpecificationLinkFigure) getFigure()).getSrcNameEdgeObjectFigure();
        }
        if (ClassEdgeObjectConstants.TARGETNAME_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((InstanceSpecificationLinkFigure) getFigure()).getTargetNameEdgeObjectFigure();
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    protected void refreshEdgeObjects()
    {
        super.refreshEdgeObjects();

        InstanceSpecification instanceSpecification = (InstanceSpecification) Utils.getElement(getGraphEdge());

        // Check whether the link has its two Slots set
        if (instanceSpecification.getSlots().size() == 2)
        {
            if (((GraphEdge) getModel()).getAnchor().size() != 0)
            {
                updateSrcNameLabel();
                updateTargetNameLabel();
            }
        }
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#refresh()
     */
    @Override
    public void refresh()
    {
        super.refresh();
        refreshAssociation();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> Update the srcName Label
     * 
     * @generated NOT
     */
    private void updateSrcNameLabel()
    {
        Label srcNameLbl = (Label) ((InstanceSpecificationLinkFigure) getFigure()).getSrcNameEdgeObjectFigure();
        InstanceValue value = getRelatedInstanceValue(getSourceSlot());
        if (value != null)
        {
            srcNameLbl.setText(value.getName());
        }
        else
        {
            srcNameLbl.setText("");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> Update the targetName Label
     * 
     * @generated NOT
     */
    private void updateTargetNameLabel()
    {
        Label targetNameLbl = (Label) ((InstanceSpecificationLinkFigure) getFigure()).getTargetNameEdgeObjectFigure();
        InstanceValue value = getRelatedInstanceValue(getTargetSlot());
        if (value != null)
        {
            targetNameLbl.setText(value.getName());
        }
        else
        {
            targetNameLbl.setText("");
        }
    }

    /**
     * Returns the EObject.
     * 
     * @return The EObject.
     */
    private InstanceSpecification getInstanceSpecification()
    {
        return (InstanceSpecification) Utils.getElement(getGraphEdge());
    }

    /**
     * @see org.topcased.modeler.edit.EMFGraphEdgeEditPart#handleModelChanged(org.eclipse.emf.common.notify.Notification)
     */
    @Override
    protected void handleModelChanged(Notification msg)
    {
        Object notifier = msg.getNotifier();
        Object newObject = msg.getNewValue();

        if (notifier instanceof InstanceSpecification)
        {
            // The connected InstanceSpecification have changed.
            switch (msg.getFeatureID(InstanceSpecification.class))
            {
                case UMLPackage.INSTANCE_SPECIFICATION__CLASSIFIER:
                    // Source or target ?
                    Slot slot = getSourceSlot();
                    if (slot != null && getRelatedInstanceSpecification(slot) == notifier)
                    {
                        // Source (cannot be null)
                        getRelatedInstanceValue(slot).setType((Type) newObject);
                    }
                    else
                    {
                        slot = getTargetSlot();
                        if (slot != null && getRelatedInstanceSpecification(slot) == notifier)
                        {
                            // Target
                            getRelatedInstanceValue(slot).setType((Type) newObject);
                        }
                    }

                    break;
                default:
                    break;
            }
        }
        else if (notifier instanceof InstanceValue)
        {
            switch (msg.getFeatureID(InstanceValue.class))
            {
                case UMLPackage.INSTANCE_VALUE__INSTANCE:
                case UMLPackage.INSTANCE_VALUE__TYPE:
                    // Recompute the correct association
                    refreshAssociation();
                    break;
                case UMLPackage.INSTANCE_VALUE__NAME:
                    updateSrcNameLabel();
                    updateTargetNameLabel();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Refresh the association of that instance.
     */
    private void refreshAssociation()
    {
        InstanceSpecification spec = getInstanceSpecification();
        if (!spec.getClassifiers().isEmpty())
        {
            if (!(spec.getClassifiers().get(0) instanceof Association))
            {
                // Not an association => remove
                setRelatedAssociation(null);
            }
            else
            {
                if (isCorrectAssociation((Association) spec.getClassifiers().get(0)))
                {
                    // Association is correct => nothing to do
                    return;
                }
                else
                {
                    // Not an association => remove
                    setRelatedAssociation(null);
                }
            }
        }

        // Now find the correct association
        Type sourceType = getRelatedType(getSourceSlot());
        Type targetType = getRelatedType(getTargetSlot());
        if (sourceType != null && targetType != null)
        {
            for (Association assoc : sourceType.getAssociations())
            {
                if (isCorrectAssociation(assoc))
                {
                    setRelatedAssociation(assoc);
                    break;
                }
            }
        }
    }

    /**
     * Defines the association in the current instance specification and set the definingfeature of the slots. If null,
     * remove the association (if any).
     * 
     * @param association The association.
     */
    private void setRelatedAssociation(Association association)
    {
        InstanceSpecification spec = getInstanceSpecification();
        if (association == null)
        {
            spec.getClassifiers().clear();
            // Removes the association
            Slot slot = getSourceSlot();
            if (slot != null)
            {
                slot.setDefiningFeature(null);
            }

            slot = getTargetSlot();
            if (slot != null)
            {
                slot.setDefiningFeature(null);
            }
        }
        else if (!spec.getClassifiers().contains(association))
        {
            spec.getClassifiers().add(association);
            Property sourceEnd = association.getMemberEnds().get(0);
            Property targetEnd = association.getMemberEnds().get(1);
            if (getRelatedType(getSourceSlot()) == sourceEnd.getType())
            {
                getSourceSlot().setDefiningFeature(sourceEnd);
                getTargetSlot().setDefiningFeature(targetEnd);
            }
            else
            {
                getSourceSlot().setDefiningFeature(targetEnd);
                getTargetSlot().setDefiningFeature(sourceEnd);
            }
        }
    }

    /**
     * Check if the given association is correct. That's to say if the instanceSpecification are linked to the same
     * classes.
     * 
     * @param assoc The association to check.
     * @return True if correct, false otherwise.
     */
    private boolean isCorrectAssociation(Association assoc)
    {
        // Check the types
        Type sourceType = getRelatedType(getSourceSlot());
        Type targetType = getRelatedType(getTargetSlot());
        if (assoc.isBinary())
        {
            Type sourceEnd = assoc.getMemberEnds().get(0).getType();
            Type targetEnd = assoc.getMemberEnds().get(1).getType();
            return sourceEnd == sourceType && targetEnd == targetType || sourceEnd == targetType && targetEnd == sourceType;
        }
        return false;
    }

    /**
     * Returns the related type of the slot.
     * 
     * @param slot The slot.
     * @return The type or null if not set.
     */
    private Type getRelatedType(Slot slot)
    {
        InstanceValue value = getRelatedInstanceValue(slot);
        if (value != null)
        {
            return value.getType();
        }
        return null;
    }

    /**
     * Returns the source slot.
     * 
     * @return The source slot or null if not found.
     */
    private Slot getSourceSlot()
    {
        if (getInstanceSpecification().getSlots().size() >= 1)
        {
            return getInstanceSpecification().getSlots().get(0);
        }
        return null;
    }

    /**
     * Returns the related instance specification of a slot.
     * 
     * @param slot The slot.
     * @return The related instance specification or null if not found.
     */
    private InstanceSpecification getRelatedInstanceSpecification(Slot slot)
    {
        InstanceValue value = getRelatedInstanceValue(slot);
        if (value != null)
        {
            return value.getInstance();
        }
        return null;
    }

    /**
     * Returns the related instance specification of a slot.
     * 
     * @param slot The slot.
     * @return The related instance specification or null if not found.
     */
    private InstanceValue getRelatedInstanceValue(Slot slot)
    {
        if (slot != null)
        {
            for (ValueSpecification value : slot.getValues())
            {
                if (value instanceof InstanceValue)
                {
                    return (InstanceValue) value;
                }
            }
        }
        return null;
    }

    /**
     * Returns the target slot.
     * 
     * @return The target slot or null if not found.
     */
    private Slot getTargetSlot()
    {
        if (getInstanceSpecification().getSlots().size() >= 2)
        {
            return getInstanceSpecification().getSlots().get(1);
        }
        return null;
    }

    /**
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#activate()
     */
    @Override
    public void activate()
    {
        super.activate();
        listenSlots();
    }

    /**
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#deactivate()
     */
    @Override
    public void deactivate()
    {
        unlistenSlots();
        super.deactivate();
    }

    /**
     * Listen to all the slots associated with the instance.
     */
    private void listenSlots()
    {
        for (Slot slot : getInstanceSpecification().getSlots())
        {
            InstanceValue value = getRelatedInstanceValue(slot);
            if (value != null)
            {
                if (!value.eAdapters().contains(getModelListener()))
                {
                    value.eAdapters().add(getModelListener());
                }
                // Listen to the instance change
                InstanceSpecification spec = value.getInstance();
                if (spec != null)
                {
                    if (!spec.eAdapters().contains(getModelListener()))
                    {
                        spec.eAdapters().add(getModelListener());
                    }
                }
            }
        }
    }

    /**
     * Stop listening to all the slots of the instance.
     */
    private void unlistenSlots()
    {
        for (Slot slot : getInstanceSpecification().getSlots())
        {
            slot.eAdapters().remove(getModelListener());
            InstanceValue value = getRelatedInstanceValue(slot);
            if (value != null)
            {
                value.eAdapters().remove(getModelListener());
                if (value.getInstance() != null)
                {
                    value.getInstance().eAdapters().remove(getModelListener());
                }
            }
        }
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
     * 
     * @generated
     */
    protected Color getPreferenceDefaultBackgroundColor()
    {
        String backgroundColor = getPreferenceStore().getString(ClassDiagramPreferenceConstants.INSTANCESPECIFICATION_DEFAULT_BACKGROUND_COLOR);
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
        String foregroundColor = getPreferenceStore().getString(ClassDiagramPreferenceConstants.INSTANCESPECIFICATION_DEFAULT_FOREGROUND_COLOR);
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
        String preferenceFont = getPreferenceStore().getString(ClassDiagramPreferenceConstants.INSTANCESPECIFICATION_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;

    }

}