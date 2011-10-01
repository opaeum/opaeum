/*******************************************************************************
 * Copyright (c) 2006, 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.edit;

import java.util.Iterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.policies.LabelDirectEditPolicy;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.uml.UMLLabel;
import org.topcased.modeler.uml.alldiagram.edit.NamedElementEditPart;
import org.topcased.modeler.uml.classdiagram.ClassEditPolicyConstants;
import org.topcased.modeler.uml.classdiagram.commands.InstanceSpecificationRestoreConnectionCommand;
import org.topcased.modeler.uml.classdiagram.figures.InstanceSpecificationFigure;
import org.topcased.modeler.uml.classdiagram.policies.InstanceSpecificationLayoutEditPolicy;
import org.topcased.modeler.uml.classdiagram.policies.InstanceSpecificationLinkEdgeCreationEditPolicy;
import org.topcased.modeler.uml.classdiagram.preferences.ClassDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * The InstanceSpecification object controller <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class InstanceSpecificationEditPart extends NamedElementEditPart
{
    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param obj the graph node
     * @generated
     */
    public InstanceSpecificationEditPart(GraphNode obj)
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
                return new InstanceSpecificationRestoreConnectionCommand(getHost());
            }
        });

        ResizableEditPolicy resizableEditPolicy = new ResizableEditPolicy();
        resizableEditPolicy.setResizeDirections(PositionConstants.EAST_WEST);
        installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, resizableEditPolicy);

        installEditPolicy(EditPolicy.LAYOUT_ROLE, new InstanceSpecificationLayoutEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());

        // Allow link between instance specifications
        installEditPolicy(ClassEditPolicyConstants.INSTANCESPECIFICATION_LINK_EDITPOLICY, new InstanceSpecificationLinkEdgeCreationEditPolicy());

    }

    /**
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshHeaderLabel()
     */
    @Override
    protected void refreshHeaderLabel()
    {
        InstanceSpecificationFigure fig = (InstanceSpecificationFigure) getFigure();
        ComposedLabel lbl = (ComposedLabel) fig.getLabel();
        ComposedLabel mainLbl = (ComposedLabel) lbl.getMain();
        InstanceSpecification instanceSpecification = (InstanceSpecification) Utils.getElement(getGraphNode());

        lbl.setPrefix(UMLLabel.getStereotypesNotation(instanceSpecification, getPreferenceStore()));

        if (instanceSpecification.getName() != null && instanceSpecification.getName().length() != 0)
        {
            mainLbl.setMain(instanceSpecification.getName());

            StringBuffer suffix = new StringBuffer();

            suffix.append(" : ");

            Iterator<Classifier> itClassifiers = instanceSpecification.getClassifiers().iterator();
            while (itClassifiers.hasNext())
            {
                Classifier currentClassifier = itClassifiers.next();
                suffix.append(currentClassifier.getName());
                if (itClassifiers.hasNext())
                {
                    suffix.append(", ");
                }
            }

            mainLbl.setSuffix(suffix.toString());
        }
        // }
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

        return new InstanceSpecificationFigure();
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

        if (notifier instanceof InstanceSpecification)
        {
            if (notifier == getEObject())
            {
                switch (msg.getFeatureID(InstanceSpecification.class))
                {
                    case UMLPackage.INSTANCE_SPECIFICATION__CLASSIFIER:
                        updateModelListening(oldObject, newObject);
                        break;
                    default:
                        break;
                }
                super.handleModelChanged(msg);
            }
        }
        else if (notifier instanceof Classifier)
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

    }

    /**
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#activate()
     */
    @Override
    public void activate()
    {
        super.activate();
        EObject model = getEObject();
        if (model instanceof InstanceSpecification)
        {
            listenClassifiers((InstanceSpecification) getEObject());
        }
    }

    /**
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#deactivate()
     */
    @Override
    public void deactivate()
    {
        EObject model = getEObject();
        if (model instanceof InstanceSpecification)
        {
            unlistenClassifiers((InstanceSpecification) getEObject());
        }
        super.deactivate();
    }

    /**
     * Listen to all the classifiers of the instance
     * 
     * @param instance the model object
     */
    private void listenClassifiers(InstanceSpecification instance)
    {
        for (Classifier classifier : instance.getClassifiers())
        {
            // Only listen to object that are not yet listened
            if (!classifier.eAdapters().contains(getModelListener()))
            {
                classifier.eAdapters().add(getModelListener());
            }
        }
    }

    /**
     * Stop listening to all the classifiers of the instance
     * 
     * @param instance the model object
     */
    private void unlistenClassifiers(InstanceSpecification instance)
    {
        for (Classifier classifier : instance.getClassifiers())
        {
            classifier.eAdapters().remove(getModelListener());
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