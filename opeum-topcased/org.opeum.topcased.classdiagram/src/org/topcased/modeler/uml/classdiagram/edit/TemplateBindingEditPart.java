/*****************************************************************************
 * Copyright (c) 2009 atos origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  eperico (atos origin) emilien.perico@atosorigin.com - Initial API and implementation
 *
  *****************************************************************************/
package org.topcased.modeler.uml.classdiagram.edit;

import java.util.Iterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.TemplateParameterSubstitution;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.draw2d.figures.Label;
import org.topcased.modeler.ModelerColorConstants;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.EdgeObject;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.edit.policies.EdgeObjectOffsetEditPolicy;
import org.topcased.modeler.figures.IEdgeObjectFigure;
import org.topcased.modeler.uml.UMLLabel;
import org.topcased.modeler.uml.classdiagram.ClassEdgeObjectConstants;
import org.topcased.modeler.uml.classdiagram.figures.TemplateBindingFigure;
import org.topcased.modeler.uml.classdiagram.preferences.ClassDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * TemplateBinding controller
 * 
 * @generated
 */
public class TemplateBindingEditPart extends EMFGraphEdgeEditPart
{

    /**
     * Constructor
     *
     * @param model the graph object
     * @generated
     */
    public TemplateBindingEditPart(GraphEdge model)
    {
        super(model);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     * @generated
     */
    protected void createEditPolicies()
    {
        super.createEditPolicies();

		//installEditPolicy(ClassEditPolicyConstants.COMMENTLINK_EDITPOLICY, new CommentLinkEdgeCreationEditPolicy());
        //installEditPolicy(ClassEditPolicyConstants.CONSTRAINTLINK_EDITPOLICY, new ConstraintLinkEdgeCreationEditPolicy());
        installEditPolicy(ModelerEditPolicyConstants.EDGE_OBJECTS_OFFSET_EDITPOLICY, new EdgeObjectOffsetEditPolicy());
    }

    /**
     * @return the Figure
     * @generated
     */
    protected IFigure createFigure()
    {
        TemplateBindingFigure connection = new TemplateBindingFigure();

        createTargetDecoration(connection);

        return connection;
    }

    /**
     * @param connection the PolylineConnection
     * @generated
     */
    private void createTargetDecoration(PolylineConnection connection)
    {

        PolygonDecoration decoration = new PolygonDecoration();
        decoration.setScale(14, 6);
        decoration.setBackgroundColor(ModelerColorConstants.white);
        connection.setTargetDecoration(decoration);

    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getEdgeObjectFigure(org.topcased.modeler.di.model.EdgeObject)
     * @generated
     */
    public IEdgeObjectFigure getEdgeObjectFigure(EdgeObject edgeObject)
    {
        if (ClassEdgeObjectConstants.MIDDLENAME_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((TemplateBindingFigure) getFigure()).getMiddleNameEdgeObjectFigure();
        }
        if (ClassEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((TemplateBindingFigure) getFigure()).getStereotypeEdgeObjectFigure();
        }
        return null;
    }
    
    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#refreshEdgeObjects()
     * @generated
     */
    protected void refreshEdgeObjects()
    {
        super.refreshEdgeObjects();
        updateMiddleNameLabel();
        updateStereotypeLabel();
    }

    /**
     * Update the middleName Label
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    private void updateMiddleNameLabel()
    {
        TemplateBinding templateBinding = (TemplateBinding) getEObject();
        String prefix = "<<bind>>";
        StringBuffer main = new StringBuffer();
        
        Iterator iterator = templateBinding.getParameterSubstitutions().iterator();
        if (iterator.hasNext())
        {
            TemplateParameterSubstitution tps1 = (TemplateParameterSubstitution) iterator.next();
            main.append(getSubstitutionName(tps1));            
        }
        while (iterator.hasNext())
        {
            TemplateParameterSubstitution tps = (TemplateParameterSubstitution) iterator.next();
            if (tps.getFormal() != null && tps.getActual() != null)
            {                
                // the name depends on the kind of ParameteredElement - only manage classifier here
                main.append(',');
                main.append(getSubstitutionName(tps));
            }
        }
        ((Label) ((TemplateBindingFigure) getFigure()).getMiddleNameEdgeObjectFigure()).setText(prefix + '<' + main.toString() + '>');
    }
    
    /**
     * Gets the substitution name of a template parameter substitution.
     * 
     * @param tps the template parameter substitution
     * 
     * @return the template parameter substitution name
     */
    @SuppressWarnings("unchecked")
    private String getSubstitutionName(TemplateParameterSubstitution tps)
    {
        StringBuffer label = new StringBuffer();
        if (tps.getFormal() != null && tps.getActual() != null)
        {
        	label.append(((NamedElement)tps.getFormal().getParameteredElement()).getName());
            label.append("->");
            ParameterableElement elt = tps.getActual();
            if (elt instanceof NamedElement && ((NamedElement)elt).getName() != null)
            {
                label.append(((NamedElement)elt).getName());                    
            }     
        }
        return label.toString();
    }

    /**
     * Update the stereotype Label
     *
     * @generated
     */
    private void updateStereotypeLabel()
    {
    	if (getPreferenceStore() != null)
    	{
    		((Label) ((TemplateBindingFigure) getFigure()).getStereotypeEdgeObjectFigure()).setText(UMLLabel.getStereotypesNotation((Element) getEObject(), getPreferenceStore()));    		
    	}
    	else
    	{
    		((Label) ((TemplateBindingFigure) getFigure()).getStereotypeEdgeObjectFigure()).setText(UMLLabel.getStereotypesNotation((Element) getEObject(), null));
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
        
        if (newObject instanceof TemplateParameterSubstitution)
        {
            switch (msg.getFeatureID(TemplateParameterSubstitution.class))
            {
                case UMLPackage.NAMESPACE:
                    updateModelListening(oldObject, newObject);
                    break;
                default:
                    break;
            }
        }
        
        if (notifier instanceof TemplateParameterSubstitution)
        {
            switch (msg.getFeatureID(TemplateParameterSubstitution.class))
            {
                case UMLPackage.TEMPLATE_PARAMETER_SUBSTITUTION__FORMAL:
                case UMLPackage.TEMPLATE_PARAMETER_SUBSTITUTION__ACTUAL:
                    updateMiddleNameLabel();
                    break;
                default:
                    break;
            }
            super.handleModelChanged(msg);
        }
        super.handleModelChanged(msg);
    }
    
    /**
     * @see org.topcased.modeler.edit.EMFGraphEdgeEditPart#activate()
     */
    @Override
    public void activate()
    {
        super.activate();
        EObject templateBinding = getEObject();
        if (templateBinding instanceof TemplateBinding)
        {
            for (TemplateParameterSubstitution tps: ((TemplateBinding) templateBinding).getParameterSubstitutions())
            {
                listenTemplateParameterSubstitution(tps);
            }
        }
    }
    
    /**
     * @see org.topcased.modeler.edit.EMFGraphEdgeEditPart#deactivate()
     */
    public void deactivate()
    {
        super.deactivate();
        EObject templateBinding = getEObject();
        if (templateBinding instanceof TemplateBinding)
        {
            for (TemplateParameterSubstitution tps: ((TemplateBinding) templateBinding).getParameterSubstitutions())
            {                
                unlistenTemplateParameterSubstitution(tps);
            }
        }
    }
     
    /**
     * Listen template parameter substitution.
     * 
     * @param parameter the parameter
     */
    private void listenTemplateParameterSubstitution(TemplateParameterSubstitution parameter)
    {
        if (parameter != null && !parameter.eAdapters().contains(getModelListener()))
        {
            parameter.eAdapters().add(getModelListener());
        }
    }
    
    /**
     * Unlisten template parameter substitution.
     * 
     * @param parameter the parameter
     */
    private void unlistenTemplateParameterSubstitution(TemplateParameterSubstitution parameter)
    {
        if (parameter != null && parameter.eAdapters().contains(getModelListener()))
        {
            parameter.eAdapters().remove(getModelListener());
        }
    } 

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultRouter()
     * 
     * @generated
     */
    protected String getPreferenceDefaultRouter()
    {
        return getPreferenceStore().getString(ClassDiagramPreferenceConstants.TEMPLATEBINDING_EDGE_DEFAULT_ROUTER);
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultForegroundColor()
     * 
     * @generated
     */
    protected Color getPreferenceDefaultForegroundColor()
    {
        String preferenceForeground = getPreferenceStore().getString(
                ClassDiagramPreferenceConstants.TEMPLATEBINDING_EDGE_DEFAULT_FOREGROUND_COLOR);
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
    protected Font getPreferenceDefaultFont()
    {
        String preferenceFont = getPreferenceStore().getString(
                ClassDiagramPreferenceConstants.TEMPLATEBINDING_EDGE_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;
    }
}