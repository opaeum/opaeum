/*******************************************************************************
 * Copyright (c) 2006, 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors :  
 * Guojun SONG (Atos Origin) -  Fix #1783 #2306
 * 
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.Request;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.topcased.draw2d.figures.Label;
import org.topcased.modeler.ModelerColorConstants;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.EdgeObject;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.edit.policies.EdgeObjectOffsetEditPolicy;
import org.topcased.modeler.figures.IEdgeObjectFigure;
import org.topcased.modeler.uml.UMLLabel;
import org.topcased.modeler.uml.alldiagram.edit.AbstractStereotypableEdgeEditPart;
import org.topcased.modeler.uml.alldiagram.policies.CommentLinkEdgeCreationEditPolicy;
import org.topcased.modeler.uml.alldiagram.policies.ConstraintLinkEdgeCreationEditPolicy;
import org.topcased.modeler.uml.classdiagram.ClassEdgeObjectConstants;
import org.topcased.modeler.uml.classdiagram.ClassEditPolicyConstants;
import org.topcased.modeler.uml.classdiagram.figures.AssociationFigure;
import org.topcased.modeler.uml.classdiagram.policies.AssociationEdgeObjectUVEditPolicy;
import org.topcased.modeler.uml.classdiagram.preferences.ClassDiagramPreferenceConstants;
import org.topcased.modeler.uml.classdiagram.util.AssociationHelper;
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
     * Create a decoration for the composition
     * 
     * @return the new decoration
     */
    private PolygonDecoration createCompositionDecoration()
    {
        PolygonDecoration decoration = new PolygonDecoration();
        PointList decorationPointList = new PointList();
        decorationPointList.addPoint(0, 0);
        decorationPointList.addPoint(-1, 1);
        decorationPointList.addPoint(-2, 0);
        decorationPointList.addPoint(-1, -1);
        decoration.setTemplate(decorationPointList);
        decoration.setScale(10, 5);
        return decoration;
    }

    /**
     * Create a decoration for the composition with a dot
     * 
     * @return the new decoration
     */
    private PolygonDecoration createCompositionDecorationCircle()
    {
        double posX = 0.5;
        PolygonDecoration decoration = new PolygonDecoration();
        PointList decorationPointList = createCircleDecorationPointList();
        decorationPointList.addPoint((int) ((0 - posX) * 50), 0 * 25);
        decorationPointList.addPoint((int) ((-1 - posX) * 50), 1 * 25);
        decorationPointList.addPoint((int) ((-2 - posX) * 50), 0 * 25);
        decorationPointList.addPoint((int) ((-1 - posX) * 50), -1 * 25);
        decorationPointList.addPoint((int) ((0 - posX) * 50), 0 * 25);
        decoration.setTemplate(decorationPointList);
        decoration.setScale(0.2, 0.2);
        return decoration;
    }

    /**
     * Create a decoration for the aggregation
     * 
     * @return the new decoration
     */
    private PolygonDecoration createAggregationDecoration()
    {
        PolygonDecoration decoration = createCompositionDecoration();
        decoration.setBackgroundColor(ModelerColorConstants.white);
        return decoration;
    }

    /**
     * Create a decoration for the aggregation with a dot
     * 
     * @param posX the starting point.
     * @param pl the PointList
     * @return the new decoration
     */
    private PolygonDecoration createAggregationDecorationCircle()
    {
        PolygonDecoration decoration = createCompositionDecorationCircle();
        decoration.setBackgroundColor(ModelerColorConstants.white);
        return decoration;
    }

    /**
     * Create a decoration for the navigable association
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
     * Create a decoration for the navigable association with a dot
     * 
     * @return the new decoration
     */

    private PolygonDecoration createNavigableDecorationCircle()
    {
        double posX = 0.5;
        PolygonDecoration decoration = new PolygonDecoration();
        PointList decorationPointList = createCircleDecorationPointList();
        decorationPointList.addPoint((int) ((0 - posX) * 30), 0 * 30);
        decorationPointList.addPoint((int) ((-2 - posX) * 30), 1 * 30);
        decorationPointList.addPoint((int) ((0 - posX) * 30), 0 * 30);
        decorationPointList.addPoint((int) ((-2 - posX) * 30), -1 * 30);
        decorationPointList.addPoint((int) ((0 - posX) * 30), 0 * 30);
        decoration.setTemplate(decorationPointList);
        decoration.setScale(0.2, 0.2);
        return decoration;
    }

    /**
     * Create a polygon decoration for a dot
     * 
     * @return the new decoration
     */

    private PolygonDecoration createCircle()
    {
        PolygonDecoration decoration = new PolygonDecoration();
        decoration.setBackgroundColor(ModelerColorConstants.black);
        decoration.setTemplate(createCircleDecorationPointList());
        decoration.setScale(0.2, 0.2);
        return decoration;
    }

    /**
     * Create the point list decoration for a dot
     * 
     * @return the new decorationPointList
     */

    private PointList createCircleDecorationPointList()
    {
        PointList decorationPointList = new PointList();
        decorationPointList.addPoint(0, 0);
        decorationPointList.addPoint(-1, 5);
        decorationPointList.addPoint(-3, 7);
        decorationPointList.addPoint(-5, 9);
        decorationPointList.addPoint(-10, 10);
        decorationPointList.addPoint(-15, 9);
        decorationPointList.addPoint(-17, 7);
        decorationPointList.addPoint(-19, 5);
        decorationPointList.addPoint(-20, 0);
        decorationPointList.addPoint(-19, -5);
        decorationPointList.addPoint(-17, -7);
        decorationPointList.addPoint(-15, -9);
        decorationPointList.addPoint(-10, -10);
        decorationPointList.addPoint(-5, -9);
        decorationPointList.addPoint(-3, -7);
        decorationPointList.addPoint(-1, -5);
        decorationPointList.addPoint(0, 0);
        decorationPointList.addPoint(-10, 10);
        decorationPointList.addPoint(-10, -10);
        decorationPointList.addPoint(-20, 0);
        decorationPointList.addPoint(-10, 10);
        decorationPointList.addPoint(-5, -9);
        decorationPointList.addPoint(0, 0);
        return decorationPointList;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     * @generated NOT
     */
    @Override
    protected void createEditPolicies()
    {
        super.createEditPolicies();

        installEditPolicy(ClassEditPolicyConstants.COMMENTLINK_EDITPOLICY, new CommentLinkEdgeCreationEditPolicy());
        installEditPolicy(ClassEditPolicyConstants.CONSTRAINTLINK_EDITPOLICY, new ConstraintLinkEdgeCreationEditPolicy());
        installEditPolicy(ModelerEditPolicyConstants.EDGE_OBJECTS_UV_EDITPOLICY, new AssociationEdgeObjectUVEditPolicy());
        installEditPolicy(ModelerEditPolicyConstants.EDGE_OBJECTS_OFFSET_EDITPOLICY, new EdgeObjectOffsetEditPolicy());

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the Figure
     * @generated NOT
     */
    @Override
    protected IFigure createFigure()
    {
        return new AssociationFigure();
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
            return ((AssociationFigure) getFigure()).getSrcNameEdgeObjectFigure();
        }
        if (ClassEdgeObjectConstants.SRCCOUNT_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((AssociationFigure) getFigure()).getSrcCountEdgeObjectFigure();
        }
        if (ClassEdgeObjectConstants.TARGETNAME_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((AssociationFigure) getFigure()).getTargetNameEdgeObjectFigure();
        }
        if (ClassEdgeObjectConstants.TARGETCOUNT_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((AssociationFigure) getFigure()).getTargetCountEdgeObjectFigure();
        }
        if (ClassEdgeObjectConstants.MIDDLENAME_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((AssociationFigure) getFigure()).getMiddleNameEdgeObjectFigure();
        }
        if (ClassEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((AssociationFigure) getFigure()).getStereotypeEdgeObjectFigure();
        }
        // Fix #1780
        if (ClassEdgeObjectConstants.SRCPROPERTIES_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((AssociationFigure) getFigure()).getSrcPropertiesEdgeObjectFigure();
        }
        if (ClassEdgeObjectConstants.TARGETPROPERTIES_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((AssociationFigure) getFigure()).getTargetPropertiesEdgeObjectFigure();
        }
        // EndFix #1780
        return null;
    }

    /**
     * Handle the double-click to edit the association
     * 
     * @see org.eclipse.gef.EditPart#performRequest(org.eclipse.gef.Request)
     */
    @Override
    public void performRequest(Request request)
    {
        super.performRequest(request);
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

        Association association = (Association) Utils.getElement(getGraphEdge());

        // Check whether the association has its two Properties set
        if (association.isBinary())
        {
            if (((GraphEdge) getModel()).getAnchor().size() != 0)
            {
                updateSourceDecoration();
                updateTargetDecoration();
                updateSrcCountLabel();
                updateSrcNameLabel();
                // Fix #1780
                updateSrcPropertiesLabel();
                // EndFix #1780
                updateTargetCountLabel();
                updateTargetNameLabel();
                // Fix #1780
                updateTargetPropertiesLabel();
                // EndFix #1780
            }

            updateMiddleNameLabel();
            updateStereotypeLabel();
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> Update the srcName Label
     * 
     * @generated NOT
     */
    private void updateSrcNameLabel()
    {
        Label srcNameLbl = (Label) ((AssociationFigure) getFigure()).getSrcNameEdgeObjectFigure();
        // Fix #1929: Skip the refresh is the association is not binary
        if (!((Association) getEObject()).isBinary())
        {
            srcNameLbl.setText("");
        }
        else
        {
            Property prop = ((Association) getEObject()).getMemberEnds().get(0);
            srcNameLbl.setText(getAssociationEndName(prop));
            // FR 2250: add underline property
            srcNameLbl.setTextUnderline(prop.isStatic());
        }
    }

    // Fix #1780
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> Update the srcProperties Label
     * 
     * @generated NOT
     */
    private void updateSrcPropertiesLabel()
    {
        Label srcPropertiesLbl = (Label) ((AssociationFigure) getFigure()).getSrcPropertiesEdgeObjectFigure();
        // Fix #1929: Skip the refresh is the association is not binary
        if (!((Association) getEObject()).isBinary())
        {
            srcPropertiesLbl.setText("");
        }
        else
        {
            srcPropertiesLbl.setText(getAssociationEndProperties(((Association) getEObject()).getMemberEnds().get(0)));
        }
    }

    // endFix #1780

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> Update the srcCount Label
     * 
     * @generated NOT
     */
    private void updateSrcCountLabel()
    {
        Label srcCount = (Label) ((AssociationFigure) getFigure()).getSrcCountEdgeObjectFigure();
        // Fix #1929: Skip the refresh is the association is not binary
        if (!((Association) getEObject()).isBinary())
        {
            srcCount.setText("");
        }
        else
        {
            srcCount.setText(getMultiplicityLabel(((Association) getEObject()).getMemberEnds().get(0)));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> Update the targetName Label
     * 
     * @generated NOT
     */
    private void updateTargetNameLabel()
    {
        Label srcNameLbl = (Label) ((AssociationFigure) getFigure()).getTargetNameEdgeObjectFigure();
        // Fix #1929: Skip the refresh is the association is not binary
        if (!((Association) getEObject()).isBinary())
        {
            srcNameLbl.setText("");
        }
        else
        {
            Property prop = ((Association) getEObject()).getMemberEnds().get(1);
            srcNameLbl.setText(getAssociationEndName(prop));
            // FR 2250: add underline property
            srcNameLbl.setTextUnderline(prop.isStatic());
        }
    }

    // Fix #1780
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> Update the targetProperties Label
     * 
     * @generated NOT
     */
    private void updateTargetPropertiesLabel()
    {
        Label targetPropertiesLbl = (Label) ((AssociationFigure) getFigure()).getTargetPropertiesEdgeObjectFigure();
        // Fix #1929: Skip the refresh is the association is not binary
        if (!((Association) getEObject()).isBinary())
        {
            targetPropertiesLbl.setText("");
        }
        else
        {
            targetPropertiesLbl.setText(getAssociationEndProperties(((Association) getEObject()).getMemberEnds().get(1)));
        }
    }

    // endFix #1780

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> Update the targetCount Label
     * 
     * @generated NOT
     */
    private void updateTargetCountLabel()
    {
        Label targetCount = (Label) ((AssociationFigure) getFigure()).getTargetCountEdgeObjectFigure();
        // Fix #1929: Skip the refresh is the association is not binary
        if (!((Association) getEObject()).isBinary())
        {
            targetCount.setText("");
        }
        else
        {
            targetCount.setText(getMultiplicityLabel(((Association) getEObject()).getMemberEnds().get(1)));
        }
    }

    private String getAssociationEndName(Property property)
    {
        StringBuffer srcName = new StringBuffer();

        srcName.append(UMLLabel.getVisibilityNotation(property.getVisibility()));

        if (property.isDerived())
        {
            srcName.append(" /");
        }

        srcName.append(property.getName());

        return srcName.toString();
    }

    // Fix #1780
    /**
     * Retrieve the properties applied on a AssociationMemberEnd
     */
    private String getAssociationEndProperties(Property property)
    {
        StringBuffer srcProperties = new StringBuffer();

        String modifiersText = UMLLabel.getPropertyModifiersText(property);
        if (modifiersText != null && !"".equals(modifiersText))
        {
            srcProperties.append(" { ");
            srcProperties.append(modifiersText);
            srcProperties.append(" }");
        }

        return srcProperties.toString();
    }

    // EndFix #1780

    /**
     * Retrieve the multiplicity text that should be displayed
     * 
     * @param prop the Property on which the multiplicity label should be calculated
     * @return the string value to display
     */
    private String getMultiplicityLabel(Property prop)
    {
        StringBuffer text = new StringBuffer();

        int lower = prop.getLower();
        int upper = prop.getUpper();
        if (lower == upper)
        {
            text.append(lower);
        }
        else if (upper == -1)
        {
            if (lower == 0)
            {
                text.append("*");
            }
            else
            {
                text.append(lower);
                text.append("..*");
            }
        }
        else
        {
            text.append(lower);
            text.append("..");
            text.append(upper);
        }

        return text.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> Update the middleName Label
     * 
     * @generated NOT
     */
    private void updateMiddleNameLabel()
    {
        Association association = (Association) getEObject();
        String associationName = association.getName() == null ? "" : association.getName();
        if (association.isDerived())
        {
            // A slash appearing in front of the name of an association, or in place of the name if no name is shown,
            // marks the association as being derived.
            associationName = "/ ".concat(associationName);
        }
        ((Label) ((AssociationFigure) getFigure()).getMiddleNameEdgeObjectFigure()).setText(associationName);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> Update the middleName Label
     * 
     * @generated NOT
     */
    private void updateStereotypeLabel()
    {
        ((Label) ((AssociationFigure) getFigure()).getStereotypeEdgeObjectFigure()).setText(UMLLabel.getStereotypesNotation((Element) getEObject(), getPreferenceStore()));
    }

    /**
     * Update the source decoration
     */
    private void updateSourceDecoration()
    {
        Association association = (Association) Utils.getElement(getGraphEdge());
        AssociationHelper associationHelper = new AssociationHelper(association);

        // Fix #1929: Skip the refresh is the association is not binary
        if (!associationHelper.isAssociationReady())
        {
            srcDecor = null;
        }
        else
        {
            boolean isNavigable = associationHelper.getAssociationFirstEndIsNavigable();
            AggregationKind aggregationKind = associationHelper.getAssociationSecondEndAggregationKind();

            if (AggregationKind.COMPOSITE_LITERAL.equals(aggregationKind))
            {
                if (!associationHelper.isAssociationFirstEndAssociationContained())
                {
                    srcDecor = createCompositionDecorationCircle();
                }
                else
                {
                    srcDecor = createCompositionDecoration();
                }
            }
            else if (AggregationKind.SHARED_LITERAL.equals(aggregationKind))
            {
                if (!associationHelper.isAssociationFirstEndAssociationContained())
                {
                    srcDecor = createAggregationDecorationCircle();
                }
                else
                {
                    srcDecor = createAggregationDecoration();
                }
            }
            else if (isNavigable)
            {
                if (!associationHelper.isAssociationFirstEndAssociationContained())
                {
                    srcDecor = createNavigableDecorationCircle();
                }
                else
                {
                    srcDecor = createNavigableDecoration();
                }
            }
            else if (!associationHelper.isAssociationFirstEndAssociationContained())
            {
                srcDecor = createCircle();

            }
            else
            {
                srcDecor = null;
            }
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

        // Defect #1929: Skip the refresh is the association is not binary
        if (!associationHelper.isAssociationReady())
        {
            targetDecor = null;
        }
        else
        {
            boolean isNavigable = associationHelper.getAssociationSecondEndIsNavigable();
            AggregationKind aggregationKind = associationHelper.getAssociationFirstEndAggregationKind();

            if (AggregationKind.COMPOSITE_LITERAL.equals(aggregationKind))
            {
                if (!associationHelper.isAssociationSecondEndAssociationContained())
                {
                    targetDecor = createCompositionDecorationCircle();
                }
                else
                {
                    targetDecor = createCompositionDecoration();
                }

            }
            else if (AggregationKind.SHARED_LITERAL.equals(aggregationKind))
            {
                if (!associationHelper.isAssociationSecondEndAssociationContained())
                {
                    targetDecor = createAggregationDecorationCircle();
                }
                else
                {
                    targetDecor = createAggregationDecoration();
                }
            }
            else if (isNavigable)
            {
                if (!associationHelper.isAssociationSecondEndAssociationContained())
                {
                    targetDecor = createNavigableDecorationCircle();
                }
                else
                {
                    targetDecor = createNavigableDecoration();
                }
            }
            else if (!associationHelper.isAssociationSecondEndAssociationContained())
            {
                targetDecor = createCircle();
            }
            else
            {
                targetDecor = null;
            }
        }
        ((PolylineConnection) getFigure()).setTargetDecoration(targetDecor);
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
                case UMLPackage.ASSOCIATION__OWNED_END:
                    updateSourceDecoration();
                    updateTargetDecoration();
                    break;
                case UMLPackage.PROPERTY__AGGREGATION:
                    updateSourceDecoration();
                    updateTargetDecoration();
                    break;
                case UMLPackage.PROPERTY__VISIBILITY:
                case UMLPackage.PROPERTY__IS_DERIVED:
                case UMLPackage.PROPERTY__NAME:
                case UMLPackage.PROPERTY__IS_READ_ONLY:
                case UMLPackage.PROPERTY__IS_DERIVED_UNION:
                case UMLPackage.PROPERTY__SUBSETTED_PROPERTY:
                case UMLPackage.PROPERTY__REDEFINED_PROPERTY:
                case UMLPackage.PROPERTY__IS_ORDERED:
                case UMLPackage.PROPERTY__IS_UNIQUE:
                    // FR 2250: add underline property when static
                case UMLPackage.PROPERTY__IS_STATIC:
                    updateSrcNameLabel();
                    // Fix #1780
                    updateSrcPropertiesLabel();
                    // EndFix #1780
                    updateTargetNameLabel();
                    // Fix #1780
                    updateTargetPropertiesLabel();
                    // EndFix #1780
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
        else if (notifier instanceof EAnnotation)
        {
            updateMiddleNameLabel();
        }
        else if (notifier instanceof ValueSpecification)
        {
            updateSrcNameLabel();
            // Fix #1780
            updateSrcPropertiesLabel();
            // EndFix #1780
            updateTargetNameLabel();
            updateSrcCountLabel();
            updateTargetCountLabel();
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
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultRouter()
     * 
     * @generated
     */
    @Override
    protected String getPreferenceDefaultRouter()
    {
        return getPreferenceStore().getString(ClassDiagramPreferenceConstants.ASSOCIATION_EDGE_DEFAULT_ROUTER);
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultForegroundColor()
     * 
     * @generated
     */
    @Override
    protected Color getPreferenceDefaultForegroundColor()
    {
        String preferenceForeground = getPreferenceStore().getString(ClassDiagramPreferenceConstants.ASSOCIATION_EDGE_DEFAULT_FOREGROUND_COLOR);
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
        String preferenceFont = getPreferenceStore().getString(ClassDiagramPreferenceConstants.ASSOCIATION_EDGE_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;
    }

}