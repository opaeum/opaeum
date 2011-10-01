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

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IOvalAnchorableFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableOvalAnchor;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.UseCase;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.policies.LabelDirectEditPolicy;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.uml.UMLLabel;
import org.topcased.modeler.uml.UMLTools;
import org.topcased.modeler.uml.usecasediagram.UseCaseEditPolicyConstants;
import org.topcased.modeler.uml.usecasediagram.commands.UseCaseRestoreConnectionCommand;
import org.topcased.modeler.uml.usecasediagram.figures.UseCaseFigure;
import org.topcased.modeler.uml.usecasediagram.policies.ExtendEdgeCreationEditPolicy;
import org.topcased.modeler.uml.usecasediagram.policies.IncludeEdgeCreationEditPolicy;
import org.topcased.modeler.uml.usecasediagram.preferences.UseCaseDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * The UseCase object controller <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class UseCaseEditPart extends ClassifierEditPart
{
    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param obj the graph node
     * @generated
     */
    public UseCaseEditPart(GraphNode obj)
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

        installEditPolicy(UseCaseEditPolicyConstants.EXTEND_EDITPOLICY, new ExtendEdgeCreationEditPolicy());

        installEditPolicy(UseCaseEditPolicyConstants.INCLUDE_EDITPOLICY, new IncludeEdgeCreationEditPolicy());

        installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy()
        {
            @Override
            protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request)
            {
                return new UseCaseRestoreConnectionCommand(getHost());
            }
        });

        installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, new ResizableEditPolicy());

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

        return new UseCaseFigure();
    }

    /**
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshHeaderLabel()
     */
    @Override
    protected void refreshHeaderLabel()
    {
        ComposedLabel lbl = (ComposedLabel) getLabel();

        UseCase useCase = (UseCase) Utils.getElement(getGraphNode());

        lbl.setPrefix(UMLLabel.getStereotypesNotation(useCase, getPreferenceStore()));

        lbl.setMain(useCase.getName());

        lbl.setSuffix(UMLTools.getFromPackageNotation(useCase, (Element) Utils.getElement((GraphElement) getParent().getModel())));

    }

    /**
     * Use SlidableOvalAnchor anchor
     * 
     * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection)
    {
        IOvalAnchorableFigure useCaseFigure = (IOvalAnchorableFigure) getFigure();
        return new SlidableOvalAnchor(useCaseFigure);
    }

    /**
     * Use SlidableOvalAnchor anchor
     * 
     * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.Request)
     */
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(Request request)
    {
        IOvalAnchorableFigure useCaseFigure = (IOvalAnchorableFigure) getFigure();
        return new SlidableOvalAnchor(useCaseFigure);
    }

    /**
     * Use SlidableOvalAnchor anchor
     * 
     * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection)
    {
        IOvalAnchorableFigure useCaseFigure = (IOvalAnchorableFigure) getFigure();
        return new SlidableOvalAnchor(useCaseFigure);
    }

    /**
     * Use SlidableOvalAnchor anchor
     * 
     * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.Request)
     */
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(Request request)
    {
        IOvalAnchorableFigure useCaseFigure = (IOvalAnchorableFigure) getFigure();
        return new SlidableOvalAnchor(useCaseFigure);
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#refreshTextAndFont()
     */
    @Override
    protected void refreshTextAndFont()
    {
        super.refreshTextAndFont();

        // When the UseCase is abstract, the name has an Italic style
        if (((Classifier) getEObject()).isAbstract())
        {
            ((ComposedLabel) getLabel()).getMain().setFont(getStyledFont(SWT.ITALIC));
        }
        else
        {
            ((ComposedLabel) getLabel()).getMain().setFont(getLabel().getFont());
        }
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
     * @generated
     */
    @Override
    protected Color getPreferenceDefaultBackgroundColor()
    {
        String backgroundColor = getPreferenceStore().getString(UseCaseDiagramPreferenceConstants.USECASE_DEFAULT_BACKGROUND_COLOR);
        if (backgroundColor.length() != 0)
        {
            return Utils.getColor(backgroundColor);
        }
        return null;
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultForegroundColor()
     * @generated
     */
    @Override
    protected Color getPreferenceDefaultForegroundColor()
    {
        String foregroundColor = getPreferenceStore().getString(UseCaseDiagramPreferenceConstants.USECASE_DEFAULT_FOREGROUND_COLOR);
        if (foregroundColor.length() != 0)
        {
            return Utils.getColor(foregroundColor);
        }
        return null;
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultFont()
     * @generated
     */
    @Override
    protected Font getPreferenceDefaultFont()
    {
        String preferenceFont = getPreferenceStore().getString(UseCaseDiagramPreferenceConstants.USECASE_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;

    }

    /**
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#createChangeDiagramAction(org.eclipse.emf.ecore.EObject)
     * @custom
     */
    @Override
    protected IAction createChangeDiagramAction(EObject modelObject)
    {
        IAction action = null;
        // if a diagram exist open it else create one
        if (((UseCase) getEObject()).getClassifierBehavior() != null)
        {
            action = super.createChangeDiagramAction(((UseCase) getEObject()).getClassifierBehavior());
        }
        else {
        	action = super.createChangeDiagramAction(modelObject);
        }
        return action;
    }

}