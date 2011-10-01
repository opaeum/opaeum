/*******************************************************************************
 * Copyright (c) 2006, 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.alldiagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
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
import org.topcased.modeler.uml.alldiagram.commands.PackageRestoreConnectionCommand;
import org.topcased.modeler.uml.alldiagram.figures.PackageFigure;
import org.topcased.modeler.uml.alldiagram.policies.PackageImportEdgeCreationEditPolicy;
import org.topcased.modeler.uml.alldiagram.policies.PackageLayoutEditPolicy;
import org.topcased.modeler.uml.alldiagram.policies.PackageMergeEdgeCreationEditPolicy;
import org.topcased.modeler.uml.alldiagram.preferences.AllDiagramPreferenceConstants;
import org.topcased.modeler.uml.classdiagram.ClassEditPolicyConstants;
import org.topcased.modeler.uml.classdiagram.edit.TemplateSignatureEditPart;
import org.topcased.modeler.utils.Utils;

/**
 * The Package object controller <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class PackageEditPart extends NamedElementEditPart
{
    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param obj the graph node
     * @generated
     */
    public PackageEditPart(GraphNode obj)
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

        installEditPolicy(ClassEditPolicyConstants.PACKAGEIMPORT_EDITPOLICY, new PackageImportEdgeCreationEditPolicy());

        installEditPolicy(ClassEditPolicyConstants.PACKAGEMERGE_EDITPOLICY, new PackageMergeEdgeCreationEditPolicy());

        installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy()
        {
            @Override
            protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request)
            {
                return new PackageRestoreConnectionCommand(getHost());
            }
        });

        installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, new ResizableEditPolicy());

        installEditPolicy(EditPolicy.LAYOUT_ROLE, new PackageLayoutEditPolicy());
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

        return new PackageFigure();
    }
    
    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#removeChildVisual(org.eclipse.gef.EditPart)
     */
    @Override
    protected void removeChildVisual(EditPart childEditPart)
    {
        IFigure child = ((GraphicalEditPart)childEditPart).getFigure();
        if (child instanceof org.topcased.draw2d.figures.ClassFigure)
        {
            for (Object obj: childEditPart.getChildren())
            {
                if (obj instanceof TemplateSignatureEditPart)
                {
                    child.getParent().remove(((TemplateSignatureEditPart) obj).getFigure());
                }
            }           
        }
        super.removeChildVisual(childEditPart);
    }

    /**
     * Set the label of the package, with the stereotypes and the "from"
     * 
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshHeaderLabel()
     */
    @Override
    protected void refreshHeaderLabel()
    {
        PackageFigure fig = (PackageFigure) getFigure();
        ComposedLabel lbl = (ComposedLabel) fig.getLabel();
        Package pakage = (Package) Utils.getElement(getGraphNode());

        lbl.setPrefix(UMLLabel.getStereotypesNotation(pakage, getPreferenceStore()));

        if (pakage.getName() != null)
        {
            lbl.setMain(pakage.getName());
        }

        lbl.setSuffix(UMLTools.getFromPackageNotation(pakage, (Element) Utils.getElement((GraphElement) getParent().getModel())));
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
     * 
     * @generated
     */
    @Override
    protected Color getPreferenceDefaultBackgroundColor()
    {
        String backgroundColor = getPreferenceStore().getString(AllDiagramPreferenceConstants.PACKAGE_DEFAULT_BACKGROUND_COLOR);
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
        String foregroundColor = getPreferenceStore().getString(AllDiagramPreferenceConstants.PACKAGE_DEFAULT_FOREGROUND_COLOR);
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
        String preferenceFont = getPreferenceStore().getString(AllDiagramPreferenceConstants.PACKAGE_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;

    }

}