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
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.ILabelFigure;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.policies.LabelDirectEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.uml.UMLLabel;
import org.topcased.modeler.uml.classdiagram.ClassElementsVisibilityConstants;
import org.topcased.modeler.uml.classdiagram.commands.PropertyRestoreConnectionCommand;
import org.topcased.modeler.uml.classdiagram.figures.PropertyFigure;
import org.topcased.modeler.uml.classdiagram.preferences.ClassDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * The Property object controller <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class PropertyEditPart extends EMFGraphNodeEditPart
{

    /** Show the type of the property. */
    private boolean showType = true;

    /** Show the default value of the property. */
    private boolean showDefaultValue = true;

    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param obj the graph node
     * @generated
     */
    public PropertyEditPart(GraphNode obj)
    {
        super(obj);
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

        installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy()
        {
            @Override
            protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request)
            {
                return new PropertyRestoreConnectionCommand(getHost());
            }
        });

        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
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

        return new PropertyFigure();
    }

    /**
     * Set the name of the property with it's type and the stereotypes
     * 
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshHeaderLabel()
     */
    @Override
    protected void refreshHeaderLabel()
    {
        // <property> ::= [<visibility>] [�/�] <name> [�:� <prop-type>] [�[�
        // <multiplicity> �]�] [�=� <default>]
        // [�{� <prop-property > [�,� <prop-property >]* �}�]
        ComposedLabel lbl = (ComposedLabel) ((ILabelFigure) getFigure()).getLabel();
        Property property = (Property) Utils.getElement(getGraphNode());
        if (property.getName() != null)
        {
            StringBuffer prefix = new StringBuffer();

            prefix.append(UMLLabel.getStereotypesNotation(property, getPreferenceStore()));

            prefix.append(UMLLabel.getVisibilityNotation(property.getVisibility()));

            if (property.isDerived())
            {
                prefix.append('/');
            }

            lbl.setPrefix(prefix.toString());

            lbl.setMain(property.getName());

            lbl.setSuffix(getPropertySuffix(property));
        }

        // Underline the label when the Property is declared as static
        ((WrappingLabel) lbl.getMain()).setTextUnderline(property.isStatic());
    }

    private String getPropertySuffix(Property property)
    {
        // [�:� <prop-type>] [�[� <multiplicity> �]�] [�=� <default>]
        // [�{� <prop-property > [�,� <prop-property >]* �}�]
        StringBuffer suffix = new StringBuffer();

        // [�:� <prop-type>]
        Type type = property.getType();
        if (showType && type != null)
        {
            suffix.append(" : ");
            suffix.append(type.getName());
        }

        // [�[�<multiplicity>�]�]
        suffix.append(UMLLabel.getMultiplicityText(property));

        // [�=� <default>]
        String defaultValue = property.getDefault();
        if (showDefaultValue && defaultValue != null && !"".equals(defaultValue))
        {
            suffix.append(" = ");
            suffix.append(defaultValue);
        }

        // [�{� <prop-property > [�,� <prop-property >]* �}�]
        String modifiersText = UMLLabel.getPropertyModifiersText(property);
        if (modifiersText != null && !"".equals(modifiersText))
        {
            suffix.append(" { ");
            suffix.append(modifiersText);
            suffix.append(" }");
        }

        return suffix.toString();
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

        if (notifier instanceof Property)
        {
            if (notifier == getEObject())
            {
                switch (msg.getFeatureID(Property.class))
                {
                    case UMLPackage.PROPERTY__TYPE:
                    case UMLPackage.PROPERTY__SUBSETTED_PROPERTY:
                    case UMLPackage.PROPERTY__REDEFINED_PROPERTY:
                    case UMLPackage.PROPERTY__LOWER_VALUE:
                    case UMLPackage.PROPERTY__UPPER_VALUE:
                    case UMLPackage.PROPERTY__DEFAULT_VALUE:
                        updateModelListening(oldObject, newObject);
                        break;
                    default:
                        break;
                }
                super.handleModelChanged(msg);
            }
            else
            {
                refreshHeaderLabel();
            }
        }
        else if (notifier instanceof Type)
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
     * Change the visibility of an element.
     * 
     * @param property The property.
     * @param isVisible True if visible, false otherwise.
     * @see ClassElementsVisibilityConstants
     */
    public void setElementVisible(String property, boolean isVisible)
    {
        if (ClassElementsVisibilityConstants.SHOW_PROPERTY_TYPE.equals(property))
        {
            showType = isVisible;
            refreshHeaderLabel();
        }
        else if (ClassElementsVisibilityConstants.SHOW_PROPERTY_DEFAULT_VALUE.equals(property))
        {
            showDefaultValue = isVisible;
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
        if (model instanceof Property)
        {
            listenType((Property) getEObject());
            listenValues((Property) getEObject());
            listenSubsettedProperties((Property) getEObject());
            listenRedefinedProperties((Property) getEObject());
        }
    }

    /**
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#deactivate()
     */
    @Override
    public void deactivate()
    {
        EObject model = getEObject();
        if (model instanceof Property)
        {
            unlistenRedefinedProperties((Property) getEObject());
            unlistenSubsettedProperties((Property) getEObject());
            unlistenValues((Property) getEObject());
            unlistenType((Property) getEObject());
        }
        super.deactivate();
    }

    /**
     * Listen to the bounds of the property
     * 
     * @param property the model object
     */
    private void listenValues(Property property)
    {
        // Only listen to object that are not yet listened
        if (property.getLowerValue() != null && !property.getLowerValue().eAdapters().contains(getModelListener()))
        {
            property.getLowerValue().eAdapters().add(getModelListener());
        }
        if (property.getUpperValue() != null && !property.getUpperValue().eAdapters().contains(getModelListener()))
        {
            property.getUpperValue().eAdapters().add(getModelListener());
        }
        if (property.getDefaultValue() != null && !property.getDefaultValue().eAdapters().contains(getModelListener()))
        {
            property.getDefaultValue().eAdapters().add(getModelListener());
        }
    }

    /**
     * Stop listening to the bounds of the property
     * 
     * @param property the model object
     */
    private void unlistenValues(Property property)
    {
        if (property.getLowerValue() != null)
        {
            property.getLowerValue().eAdapters().remove(getModelListener());
        }
        if (property.getUpperValue() != null)
        {
            property.getUpperValue().eAdapters().remove(getModelListener());
        }
        if (property.getDefaultValue() != null)
        {
            property.getDefaultValue().eAdapters().remove(getModelListener());
        }
    }

    /**
     * Listen to the type of the property
     * 
     * @param property the model object
     */
    private void listenType(Property property)
    {
        // Only listen to object that are not yet listened
        if (property.getType() != null && !property.getType().eAdapters().contains(getModelListener()))
        {
            property.getType().eAdapters().add(getModelListener());
        }
    }

    /**
     * Stop listening to the type of the property
     * 
     * @param property the model object
     */
    private void unlistenType(Property property)
    {
        if (property.getType() != null)
        {
            property.getType().eAdapters().remove(getModelListener());
        }
    }

    /**
     * Listen to all the subsetted properties of the property
     * 
     * @param property the model object
     */
    private void listenSubsettedProperties(Property property)
    {
        for (Property subsettedProp : property.getSubsettedProperties())
        {
            // Only listen to object that are not yet listened
            if (!subsettedProp.eAdapters().contains(getModelListener()))
            {
                subsettedProp.eAdapters().add(getModelListener());
            }
        }
    }

    /**
     * Stop listening to all the subsetted properties of the property
     * 
     * @param property the model object
     */
    private void unlistenSubsettedProperties(Property property)
    {
        for (Property subsettedProp : property.getSubsettedProperties())
        {
            subsettedProp.eAdapters().remove(getModelListener());
        }
    }

    /**
     * Listen to all the redefined properties of the property
     * 
     * @param property the model object
     */
    private void listenRedefinedProperties(Property property)
    {
        for (Property redefinedProp : property.getRedefinedProperties())
        {
            // Only listen to object that are not yet listened
            if (!redefinedProp.eAdapters().contains(getModelListener()))
            {
                redefinedProp.eAdapters().add(getModelListener());
            }
        }
    }

    /**
     * Stop listening to all the redefined properties of the property
     * 
     * @param property the model object
     */
    private void unlistenRedefinedProperties(Property property)
    {
        for (Property redefinedProp : property.getRedefinedProperties())
        {
            redefinedProp.eAdapters().remove(getModelListener());
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
        String backgroundColor = getPreferenceStore().getString(ClassDiagramPreferenceConstants.PROPERTY_DEFAULT_BACKGROUND_COLOR);
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
        String foregroundColor = getPreferenceStore().getString(ClassDiagramPreferenceConstants.PROPERTY_DEFAULT_FOREGROUND_COLOR);
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
        String preferenceFont = getPreferenceStore().getString(ClassDiagramPreferenceConstants.PROPERTY_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;

    }

}