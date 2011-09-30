/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware
 * Technologies), Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware
 * Technologies), Nicolas Lalev�e (Anyware Technologies) - initial API and
 * implementation
 ******************************************************************************/

package org.topcased.modeler.uml.usecasediagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.topcased.draw2d.figures.Label;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.EdgeObject;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.edit.policies.EdgeObjectOffsetEditPolicy;
import org.topcased.modeler.figures.EdgeObjectOffsetEditableLabel;
import org.topcased.modeler.figures.IEdgeObjectFigure;
import org.topcased.modeler.uml.UMLLabel;
import org.topcased.modeler.uml.alldiagram.edit.AbstractStereotypableEdgeEditPart;
import org.topcased.modeler.uml.alldiagram.policies.CommentLinkEdgeCreationEditPolicy;
import org.topcased.modeler.uml.alldiagram.policies.ConstraintLinkEdgeCreationEditPolicy;
import org.topcased.modeler.uml.classdiagram.ClassEditPolicyConstants;
import org.topcased.modeler.uml.classdiagram.util.AssociationHelper;
import org.topcased.modeler.uml.usecasediagram.UseCaseEdgeObjectConstants;
import org.topcased.modeler.uml.usecasediagram.figures.AssociationFigure;
import org.topcased.modeler.uml.usecasediagram.policies.AssociationEdgeObjectUVEditPolicy;
import org.topcased.modeler.uml.usecasediagram.preferences.UseCaseDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * Association controller <br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class AssociationEditPart extends AbstractStereotypableEdgeEditPart
{
    private RotatableDecoration srcDecor;

    private RotatableDecoration targetDecor;

    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param model the graph object
     * @generated
     */
    public AssociationEditPart(GraphEdge model)
    {
        super(model);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     * @generated
     */
    @Override
    protected void createEditPolicies()
    {
        super.createEditPolicies();

        installEditPolicy(ClassEditPolicyConstants.COMMENTLINK_EDITPOLICY, new CommentLinkEdgeCreationEditPolicy());
        installEditPolicy(ClassEditPolicyConstants.CONSTRAINTLINK_EDITPOLICY, new ConstraintLinkEdgeCreationEditPolicy());
        installEditPolicy(ModelerEditPolicyConstants.CHANGE_FONT_EDITPOLICY, null);
        installEditPolicy(ModelerEditPolicyConstants.EDGE_OBJECTS_UV_EDITPOLICY, new AssociationEdgeObjectUVEditPolicy());
        installEditPolicy(ModelerEditPolicyConstants.EDGE_OBJECTS_OFFSET_EDITPOLICY, new EdgeObjectOffsetEditPolicy());

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the Figure
     * @generated
     */
    @Override
    protected IFigure createFigure()
    {
        AssociationFigure connection = new AssociationFigure();

        return connection;
    }

    /**
     * Create a decoration for the navigeable association
     * 
     * @return the new decoration
     */
    private PolylineDecoration createNavigableDecoration()
    {
        PolylineDecoration decoration = new PolylineDecoration();
        decoration.setScale(10, 5);
        return decoration;
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
        if (UseCaseEdgeObjectConstants.SRCCOUNT_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((AssociationFigure) getFigure()).getSrcCountEdgeObjectFigure();
        }

        if (UseCaseEdgeObjectConstants.TARGETCOUNT_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((AssociationFigure) getFigure()).getTargetCountEdgeObjectFigure();
        }

        if (UseCaseEdgeObjectConstants.NAME_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((AssociationFigure) getFigure()).getNameEdgeObjectFigure();
        }

        if (UseCaseEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((AssociationFigure) getFigure()).getStereotypeEdgeObjectFigure();
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#refreshEdgeObjects()
     * @generated NOT
     */
    @Override
    protected void refreshEdgeObjects()
    {
        super.refreshEdgeObjects();

        Association association = (Association) Utils.getElement(getGraphEdge());

        // Check whether the association has its two Properties set
        if (association.isBinary())
        {
            if (((GraphEdge) getModel()).getAnchor().size() != 0)
            {
                updateSourceDecoration();
                updateTargetDecoration();
                updateSrcCountLabel();
                updateTargetCountLabel();
            }

            updateNameLabel();
            updateStereotypeLabel();
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> Update the middleName Label
     * 
     * @generated NOT
     */
    private void updateNameLabel()
    {
        Association association = (Association) getEObject();
        String associationName = association.getName() == null ? "" : association.getName();
        if (association.isDerived())
        {
            // A slash appearing in front of the name of an association, or in place of the name if no name is shown,
            // marks the association as being derived.
            associationName = "/ ".concat(associationName);
        }
        ((EdgeObjectOffsetEditableLabel) ((AssociationFigure) getFigure()).getNameEdgeObjectFigure()).setText(associationName);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> Update the middleName Label
     * 
     * @generated
     */
    private void updateStereotypeLabel()
    {
        Association association = (Association) Utils.getElement(getGraphEdge());

        AssociationFigure fig = (AssociationFigure) getFigure();
        EdgeObjectOffsetEditableLabel label = (EdgeObjectOffsetEditableLabel) fig.getStereotypeEdgeObjectFigure();
        label.setText(UMLLabel.getStereotypesNotation(association, getPreferenceStore()));
    }

    /**
     * Update the source decoration
     */
    private void updateSourceDecoration()
    {
        Association association = (Association) Utils.getElement(getGraphEdge());
        AssociationHelper associationHelper = new AssociationHelper(association);

        boolean isNavigable = associationHelper.getAssociationFirstEndIsNavigable();

        if (isNavigable)
        {
            srcDecor = createNavigableDecoration();
        }
        else
        {
            srcDecor = null;
        }
        ((PolylineConnection) getFigure()).setSourceDecoration(srcDecor);
    }

    /**
     * Update the target decoration
     */
    private void updateTargetDecoration()
    {
        Association association = (Association) Utils.getElement(getGraphEdge());
        AssociationHelper associationHelper = new AssociationHelper(association);

        boolean isNavigable = associationHelper.getAssociationSecondEndIsNavigable();

        if (isNavigable)
        {
            targetDecor = createNavigableDecoration();
        }
        else
        {
            targetDecor = null;
        }
        ((PolylineConnection) getFigure()).setTargetDecoration(targetDecor);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> Update the srcCount Label
     * 
     * @generated NOT
     */
    private void updateSrcCountLabel()
    {
        Label srcCount = (Label) ((AssociationFigure) getFigure()).getSrcCountEdgeObjectFigure();

        Association association = (Association) Utils.getElement(getGraphEdge());
        AssociationHelper associationHelper = new AssociationHelper(association);

        String lowerBound = associationHelper.getAssociationFirstEndLowerBound();
        String upperBound = associationHelper.getAssociationFirstEndUpperBound();
        boolean isOrdered = associationHelper.getAssociationFirstEndIsOrdered();

        if (isOrdered)
        {
            srcCount.setText("{" + getMultiplicityLabel(lowerBound, upperBound) + "}");
        }
        else
        {
            srcCount.setText(getMultiplicityLabel(lowerBound, upperBound));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> Update the targetCount Label
     * 
     * @generated NOT
     */
    private void updateTargetCountLabel()
    {
        Label targetCount = (Label) ((AssociationFigure) getFigure()).getTargetCountEdgeObjectFigure();

        Association association = (Association) Utils.getElement(getGraphEdge());
        AssociationHelper associationHelper = new AssociationHelper(association);

        String lowerBound = associationHelper.getAssociationSecondEndLowerBound();
        String upperBound = associationHelper.getAssociationSecondEndUpperBound();
        boolean isOrdered = associationHelper.getAssociationSecondEndIsOrdered();

        if (isOrdered)
        {
            targetCount.setText("{" + getMultiplicityLabel(lowerBound, upperBound) + "}");
        }
        else
        {
            targetCount.setText(getMultiplicityLabel(lowerBound, upperBound));
        }
    }

    /**
     * Retrieve the multiplicity text that should be displayed
     * 
     * @param lowerBound the LowerBound text value
     * @param upperBound the UpperBound text value ("*" represent an Unbounded value)
     * @return the string value to display
     */
    private String getMultiplicityLabel(String lowerBound, String upperBound)
    {
        if (lowerBound.equals(upperBound))
        {
            return lowerBound;
        }
        else if ("0".equals(lowerBound) && "*".equals(upperBound))
        {
            return "*";
        }
        return lowerBound + ".." + upperBound;
    }

    /**
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#activate()
     */
    @Override
    public void activate()
    {
        super.activate();
        EObject model = getEObject();
        if (model instanceof Association)
        {
            listenProperties((Association) model);
            listenEAnnotations((Association) model);
        }
    }

    /**
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#deactivate()
     */
    @Override
    public void deactivate()
    {
        EObject model = getEObject();
        if (model instanceof Association)
        {
            unlistenEAnnotations((Association) model);
            unlistenProperties((Association) model);
        }
        super.deactivate();
    }

    /**
     * Listen to all the properties associated with the association
     * 
     * @param association the model object
     */
    private void listenProperties(Association association)
    {
        for (Property prop : association.getMemberEnds())
        {
            // Only listen to object that are not yet listened
            if (!prop.eAdapters().contains(getModelListener()))
            {
                prop.eAdapters().add(getModelListener());
            }
            listenValues(prop);
        }
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
     * Stop listening to all the redefined operations of the operation
     * 
     * @param association the model object
     */
    private void unlistenProperties(Association association)
    {
        for (Property prop : association.getMemberEnds())
        {
            unlistenValues(prop);
            prop.eAdapters().remove(getModelListener());
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
     * Listen to all the EAnnotations associated with the association
     * 
     * @param association the model object
     */
    private void listenEAnnotations(Association association)
    {
        for (EAnnotation eAnnot : association.getEAnnotations())
        {
            // Only listen to object that are not yet listened
            if (!eAnnot.eAdapters().contains(getModelListener()))
            {
                eAnnot.eAdapters().add(getModelListener());
            }
        }
    }

    /**
     * Stop listening to all the EAnnotations of the association
     * 
     * @param association the model object
     */
    private void unlistenEAnnotations(Association association)
    {
        for (EAnnotation eAnnot : association.getEAnnotations())
        {
            eAnnot.eAdapters().remove(getModelListener());
        }
    }

    /**
     * @see org.topcased.modeler.edit.EMFGraphEdgeEditPart#handleModelChanged(org.eclipse.emf.common.notify.Notification)
     */
    @Override
    protected void handleModelChanged(Notification msg)
    {

        Object notifier = msg.getNotifier();
        Object newObject = msg.getNewValue();
        Object oldObject = msg.getOldValue();

        if (notifier instanceof Association)
        {
            if (notifier == getEObject())
            {
                switch (msg.getFeatureID(Association.class))
                {
                    case UMLPackage.ASSOCIATION__MEMBER_END:
                    case UMLPackage.ASSOCIATION__EANNOTATIONS:
                        updateModelListening(oldObject, newObject);
                        break;
                    default:
                        break;
                }
                super.handleModelChanged(msg);
            }
        }
        else if (notifier instanceof Property)
        {
            switch (msg.getFeatureID(Property.class))
            {
                case UMLPackage.PROPERTY__IS_ORDERED:
                    updateSrcCountLabel();
                    updateTargetCountLabel();
                    break;
                case UMLPackage.PROPERTY__LOWER_VALUE:
                case UMLPackage.PROPERTY__UPPER_VALUE:
                case UMLPackage.PROPERTY__DEFAULT_VALUE:
                    updateModelListening(oldObject, newObject);
                    break;
                default:
                    break;
            }
        }
        else if (notifier instanceof ValueSpecification)
        {
            updateSrcCountLabel();
            updateTargetCountLabel();
        }
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultRouter()
     * 
     * @generated
     */
    @Override
    protected String getPreferenceDefaultRouter()
    {
        return getPreferenceStore().getString(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_EDGE_DEFAULT_ROUTER);
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultForegroundColor()
     * 
     * @generated
     */
    @Override
    protected Color getPreferenceDefaultForegroundColor()
    {
        String preferenceForeground = getPreferenceStore().getString(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_EDGE_DEFAULT_FOREGROUND_COLOR);
        if (preferenceForeground.length() != 0)
        {
            return Utils.getColor(preferenceForeground);
        }
        return null;

    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultFont()
     * 
     * @generated
     */
    @Override
    protected Font getPreferenceDefaultFont()
    {
        String preferenceFont = getPreferenceStore().getString(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_EDGE_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;
    }

}