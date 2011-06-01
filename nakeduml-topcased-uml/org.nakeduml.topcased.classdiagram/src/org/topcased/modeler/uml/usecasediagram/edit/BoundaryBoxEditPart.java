/*****************************************************************************
 * 
 * BoundaryBoxEditPart.java
 * 
 * Copyright (c) 2008 Atos Origin.
 *
 * Contributors:
 *  Maxime Nauleau (Atos Origin) maxime.nauleau@atosorigin.com - initial API and implementation
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
  *****************************************************************************/

package org.topcased.modeler.uml.usecasediagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.editor.ModelerGraphicalViewer;
import org.topcased.modeler.graphconf.DiagramGraphConf;
import org.topcased.modeler.graphconf.NodeGraphConf;
import org.topcased.modeler.uml.alldiagram.edit.ElementEditPart;
import org.topcased.modeler.uml.usecasediagram.figures.BoundaryBoxFigure;
import org.topcased.modeler.uml.usecasediagram.preferences.UseCaseDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * BoundaryBox controller <br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 */
public class BoundaryBoxEditPart extends ElementEditPart
{
    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param obj the graph object
     */
    public BoundaryBoxEditPart(GraphNode obj)
    {
        super(obj);
    }
    
    /**
     * Creates edit policies and associates these with roles <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     */
    protected void createEditPolicies()
    {
        super.createEditPolicies();
       
        installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, new ResizableEditPolicy());
    }
    
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure()
    {        
        return new BoundaryBoxFigure();
    }

    /**
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshHeaderLabel()
     */
    protected void refreshHeaderLabel()
    {
    
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
     */
    protected Color getPreferenceDefaultBackgroundColor()
    {
        String backgroundColor = getPreferenceStore().getString(UseCaseDiagramPreferenceConstants.BOUNDARY_BOX_DEFAULT_BACKGROUND_COLOR);
        if (backgroundColor.length() != 0)
        {
            return Utils.getColor(backgroundColor);
        }
        return null;
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultForegroundColor()
     */
    protected Color getPreferenceDefaultForegroundColor()
    {
        String foregroundColor = getPreferenceStore().getString(UseCaseDiagramPreferenceConstants.BOUNDARY_BOX_DEFAULT_FOREGROUND_COLOR);
        if (foregroundColor.length() != 0)
        {
            return Utils.getColor(foregroundColor);
        }
        return null;
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultFont()
     */
    protected Font getPreferenceDefaultFont()
    {
        String preferenceFont = getPreferenceStore().getString(UseCaseDiagramPreferenceConstants.BOUNDARY_BOX_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;

    }
            
    /**
     * @override
     * Return the NodeGraphConf corresponding to the activeDiagram and the model object associated with the controller
     * 
     * @return NodeGraphConf
     */
    protected NodeGraphConf getNodeGraphConf()
    {

        DiagramGraphConf diagGraphConf = ((ModelerGraphicalViewer) getViewer()).getModelerEditor().getActiveConfiguration().getDiagramGraphConf();
        if (diagGraphConf != null)
        {
            // REV PRESENTATION : is it normal to use the "default"
            // presentation ?
            
            //We know that we need the BoundaryBox GraphNodeConf, so we can use "BoundaryBox" for first argument (type) directly
            return diagGraphConf.getNodeGraphConf("BoundaryBox", getPresentation());
        }
        else {
            return null;
        }
       
    }
}
