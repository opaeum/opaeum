/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *   Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware Technologies),
 *   Nicolas Lalev�e (Anyware Technologies) - initial API and implementation 
 ******************************************************************************/

package org.topcased.modeler.uml.usecasediagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.ExtensionPoint;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.ValueSpecification;
import org.topcased.draw2d.figures.Label;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.EdgeObject;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.edit.policies.EdgeObjectOffsetEditPolicy;
import org.topcased.modeler.figures.IEdgeObjectFigure;
import org.topcased.modeler.uml.UMLLabel;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.usecasediagram.UseCaseEdgeObjectConstants;
import org.topcased.modeler.uml.usecasediagram.commands.update.ExtendEdgeUpdateCommand;
import org.topcased.modeler.uml.usecasediagram.dialogs.ExtendEditDialog;
import org.topcased.modeler.uml.usecasediagram.figures.ExtendFigure;
import org.topcased.modeler.uml.usecasediagram.policies.ExtendEdgeObjectUVEditPolicy;
import org.topcased.modeler.uml.usecasediagram.preferences.UseCaseDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * Extend controller <br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ExtendEditPart extends EMFGraphEdgeEditPart
{
    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param model the graph object
     * @generated
     */
    public ExtendEditPart(GraphEdge model)
    {
        super(model);
    }

    /**
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     * @generated
     */
    @Override
    protected void createEditPolicies()
    {
        super.createEditPolicies();
        installEditPolicy(ModelerEditPolicyConstants.EDGE_OBJECTS_UV_EDITPOLICY, new ExtendEdgeObjectUVEditPolicy());
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
        ExtendFigure connection = new ExtendFigure();
        createTargetDecoration(connection);

        return connection;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param connection the PolylineConnection
     * @generated
     */
    private void createTargetDecoration(PolylineConnection connection)
    {

        PolylineDecoration decoration = new PolylineDecoration();
        decoration.setScale(10, 5);
        connection.setTargetDecoration(decoration);

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
        if (UseCaseEdgeObjectConstants.NAME_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((ExtendFigure) getFigure()).getNameEdgeObjectFigure();
        }
        if (UseCaseEdgeObjectConstants.EXTENSION_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((ExtendFigure) getFigure()).getExtensionEdgeObjectFigure();
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#refreshEdgeObjects()
     * @generated
     */
    @Override
    protected void refreshEdgeObjects()
    {
        super.refreshEdgeObjects();
        updateNameLabel();
        updateExtensionLabel();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> Update the name Label
     * 
     * @generated NOT
     */
    private void updateNameLabel()
    {
        String stereotypes = UMLLabel.getStereotypesNotation((Element) getEObject(), getPreferenceStore());
        String textToDisplay;
        if (stereotypes != null && stereotypes.length() > 0)
        {
            textToDisplay = "<<extend>>, " + stereotypes;
        }
        else
        {
            textToDisplay = "<<extend>>";
        }
        ((Label) ((ExtendFigure) getFigure()).getNameEdgeObjectFigure()).setText(textToDisplay);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> Update the extension Label
     * 
     * @generated NOT
     */
    private void updateExtensionLabel()
    {
        Extend extend = (Extend) Utils.getElement(getGraphEdge());
        if (extend.getExtensionLocations().size() != 0)
        {
            String extensionPoint = "Extension point: " + extend.getExtensionLocations().get(0).getName();
            String condition = "";
            if (extend.getCondition() != null && extend.getCondition().getSpecification() != null)
            {
                condition = "\nCondition: {" + extend.getCondition().getSpecification().stringValue() + "}";
            }
            ((Label) ((ExtendFigure) getFigure()).getExtensionEdgeObjectFigure()).setText(extensionPoint + condition);
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#performRequest(org.eclipse.gef.Request)
     */
    @Override
    public void performRequest(Request request)
    {
        // REV the Dialog should be replaced by properties in the Tabbed
        // Properties View
        if (request.getType() == RequestConstants.REQ_OPEN)
        {
            Extend extend = (Extend) Utils.getElement(getGraphEdge());

            GraphNode target = (GraphNode) ((GraphEdge) getModel()).getAnchor().get(1).getGraphElement();

            UseCase useCaseTarget = (UseCase) Utils.getElement(target);

            ExtendEditDialog extendDlg = new ExtendEditDialog(extend, useCaseTarget, getViewer().getEditDomain(), UMLPlugin.getActiveWorkbenchShell());
            if (extendDlg.open() == Window.OK)
            {
                ExtendEdgeUpdateCommand command = new ExtendEdgeUpdateCommand(extend, extendDlg.getExtensionPoint(), extendDlg.getCondition());
                getViewer().getEditDomain().getCommandStack().execute(command);
            }
        }
        else
        {
            super.performRequest(request);
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

        if (notifier instanceof Extend && notifier == getEObject())
        {
            switch (msg.getFeatureID(Extend.class))
            {
                case UMLPackage.EXTEND__EXTENSION_LOCATION:
                case UMLPackage.EXTEND__CONDITION:
                    updateModelListening(oldObject, newObject);
                    break;
                default:
                    break;
            }
            super.handleModelChanged(msg);
        }
        else if (notifier instanceof ExtensionPoint)
        {
            switch (msg.getFeatureID(ExtensionPoint.class))
            {
                case UMLPackage.EXTENSION_POINT__NAME:
                    updateExtensionLabel();
                    break;
                default:
                    break;
            }
        }
        else if (notifier instanceof Constraint)
        {
            switch (msg.getFeatureID(Constraint.class))
            {
                case UMLPackage.CONSTRAINT__SPECIFICATION:
                    updateModelListening(oldObject, newObject);
                    break;
                default:
                    break;
            }
        }
        else if (notifier instanceof OpaqueExpression)
        {
            switch (msg.getFeatureID(OpaqueExpression.class))
            {
                case UMLPackage.OPAQUE_EXPRESSION__BODY:
                    updateExtensionLabel();
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
        if (model instanceof Extend)
        {
            listenExtensionLocations((Extend) model);
            listenCondition((Extend) model);
        }
    }

    /**
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#deactivate()
     */
    @Override
    public void deactivate()
    {
        EObject model = getEObject();
        if (model instanceof Extend)
        {
            unlistenExtensionLocations((Extend) model);
            unlistenCondition((Extend) model);
        }
        super.deactivate();
    }

    /**
     * Listen to the ExtensionLocation associated with the Extend element
     * 
     * @param extend the model object
     */
    private void listenExtensionLocations(Extend extend)
    {
        for (ExtensionPoint extensionPoint : extend.getExtensionLocations())
        {
            // Only listen to object that are not yet listened
            if (!extensionPoint.eAdapters().contains(getModelListener()))
            {
                extensionPoint.eAdapters().add(getModelListener());
            }
        }
    }

    /**
     * Listen to the Condition associated with the Extend element
     * 
     * @param extend the model object
     */
    private void listenCondition(Extend extend)
    {
        Constraint condition = extend.getCondition();
        if (condition != null)
        {
            // Only listen to object that are not yet listened
            if (!condition.eAdapters().contains(getModelListener()))
            {
                condition.eAdapters().add(getModelListener());
            }

            listenSpecification(condition);
        }
    }

    /**
     * Listen to the Specification associated with the Constraint element
     * 
     * @param constraint the model object
     */
    private void listenSpecification(Constraint constraint)
    {
        ValueSpecification spec = constraint.getSpecification();
        if (spec != null)
        {
            // Only listen to object that are not yet listened
            if (!spec.eAdapters().contains(getModelListener()))
            {
                spec.eAdapters().add(getModelListener());
            }
        }
    }

    /**
     * Stop listening the ExtensionLocation associated with the Extend element
     * 
     * @param extend the model object
     */
    private void unlistenExtensionLocations(Extend extend)
    {
        for (ExtensionPoint extensionPoint : extend.getExtensionLocations())
        {
            // Only listen to object that are not yet listened
            if (!extensionPoint.eAdapters().contains(getModelListener()))
            {
                extensionPoint.eAdapters().remove(getModelListener());
            }
        }
    }

    /**
     * Stop listening the condition associated with the Extend element
     * 
     * @param extend the model object
     */
    private void unlistenCondition(Extend extend)
    {
        Constraint condition = extend.getCondition();
        if (condition != null)
        {
            // Only listen to object that are not yet listened
            if (!condition.eAdapters().contains(getModelListener()))
            {
                condition.eAdapters().remove(getModelListener());
            }

            unlistenSpecification(condition);
        }
    }

    /**
     * Stop listening the specification associated with the Constraint element
     * 
     * @param constraint the model object
     */
    private void unlistenSpecification(Constraint constraint)
    {
        ValueSpecification spec = constraint.getSpecification();
        if (spec != null)
        {
            // Only listen to object that are not yet listened
            if (!spec.eAdapters().contains(getModelListener()))
            {
                spec.eAdapters().remove(getModelListener());
            }
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
        return getPreferenceStore().getString(UseCaseDiagramPreferenceConstants.EXTEND_EDGE_DEFAULT_ROUTER);
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultForegroundColor()
     * 
     * @generated
     */
    @Override
    protected Color getPreferenceDefaultForegroundColor()
    {
        String preferenceForeground = getPreferenceStore().getString(UseCaseDiagramPreferenceConstants.EXTEND_EDGE_DEFAULT_FOREGROUND_COLOR);
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
        String preferenceFont = getPreferenceStore().getString(UseCaseDiagramPreferenceConstants.EXTEND_EDGE_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;
    }

}