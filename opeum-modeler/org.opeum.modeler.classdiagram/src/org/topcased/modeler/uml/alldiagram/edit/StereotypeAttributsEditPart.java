/*****************************************************************************
 * Copyright (c) 2009 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Caroline Bourdeu d'Aguerre (2009) caroline.bourdeudaguerre@atosorigin.com - Initial API and implementation
 *
  *****************************************************************************/

package org.topcased.modeler.uml.alldiagram.edit;

import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.commands.DeleteGraphElementCommand;
import org.topcased.modeler.commands.GEFtoEMFCommandWrapper;
import org.topcased.modeler.di.model.EMFSemanticModelBridge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.di.model.SemanticModelBridge;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.uml.alldiagram.figures.CommentFigure;
import org.topcased.modeler.uml.classdiagram.edit.AssociationFromAssociationClassEditPart;
import org.topcased.modeler.utils.Utils;

public class StereotypeAttributsEditPart extends CommentEditPart
{

    /** Constant <code>Color</code> representing black. */
    public static final Color diagramBlack = new Color(null, 0, 0, 0);

    /** Constant <code>Color</code> representing light blue. */
    public static final Color diagramLightBlue = new Color(null, 232, 253, 255);

    /** The association between the namedElement and that callout. */
    private AssociationFromAssociationClassEditPart associationEditPart;

    private Element target;

    /**
     * Constructor.
     * 
     * @param obj the graph node
     */
    public StereotypeAttributsEditPart(GraphNode obj)
    {
        super(obj);
    }

    /**
     * Set the element text
     * 
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshHeaderLabel()
     */
    protected void refreshHeaderLabel()
    {
        ILabel label = ((CommentFigure) getFigure()).getLabel();
        label.setText(getLabelText());
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#createEditPolicies()
     */
    protected void createEditPolicies()
    {
        super.createEditPolicies();
        ResizableEditPolicy resizableEditPolicy = new ResizableEditPolicy();
        resizableEditPolicy.setResizeDirections(PositionConstants.NSEW);
        installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, resizableEditPolicy);

//        installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, new ResizableEditPolicy());
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure()
    {
        return new CommentFigure();
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getEditableLabel()
     */
    public ILabel getEditableLabel()
    {
        return ((CommentFigure) getFigure()).getLabel();
    }

    /**
     * Handle the comment edition The note element is not editable
     * 
     * @see org.topcased.modeler.edit.GraphNodeEditPart#performDirectEdit()
     */
    protected void performDirectEdit()
    {
        // The perform edit is disable
    }

    @Override
    public void activate()
    {
        super.activate();
        setTarget(UMLUtil.getBaseElement(getEObject()));
        EditPart targetEditPart = getAssociatedEditPart();
        if (targetEditPart == null && getAssociationEditPart() != null)
        {
            getAssociationEditPart().getFigure().getParent().remove(getAssociationEditPart().getFigure());
            getAssociationEditPart().deactivate();
        }
        else if (getAssociationEditPart() == null)
        {
            setAssociationEditPart(new AssociationFromAssociationClassEditPart());
            IFigure assocFigure = getAssociationEditPart().getFigure();
            assocFigure.setParent(getFigure());
            getFigure().add(assocFigure);
            assocFigure.setForegroundColor(getFigure().getForegroundColor());
            getAssociationEditPart().setSource(this);
            getAssociationEditPart().setTarget(targetEditPart);
        }

        // Listening the associated element
        updateModelListening(null, ((EObject) getModel()).eContainer());
    }

    /**
     * @see org.topcased.modeler.uml.classdiagram.edit.AssociationEditPart#deactivate()
     */
    @Override
    public void deactivate()
    {
        if (associationEditPart != null)
        {
            associationEditPart.getFigure().getParent().remove(associationEditPart.getFigure());
            associationEditPart.deactivate();
        }
        super.deactivate();
    }

    protected void unlistenElement()
    {
        // Unlistening the associated element
        updateModelListening(((EObject) getModel()).eContainer(), null);
    }

    protected void handleModelChanged(Notification msg)
    {
        Object notifier = msg.getNotifier();
        Object oldObject = msg.getOldValue();

        if (notifier instanceof EObject)
        {
            if (notifier == ((EObject) getModel()).eContainer())
            {
                if (msg.getEventType() == Notification.REMOVE)
                {
                    if (oldObject != null && oldObject instanceof GraphNode)
                    {
                        if (Utils.getElement((GraphNode) oldObject).equals(getTarget()))
                        {
                            GEFtoEMFCommandWrapper wrapper = new GEFtoEMFCommandWrapper((Command) new DeleteGraphElementCommand(this.getGraphNode(), false));
                            AdapterFactoryEditingDomain.getEditingDomainFor(notifier).getCommandStack().execute(wrapper);
                        }
                    }
                }
            }
        }
    }

    // Get the editpart associated to the childGraphNode
    /**
     * Gets the associated edit part.
     * 
     * @param elem the elem
     * 
     * @return the associated edit part
     */
    public EditPart getAssociatedEditPart()
    {
        return getEditPart(getTarget());
    }

    /**
     * Gets the edits the part.
     * 
     * @param element the element
     * 
     * @return the edits the part
     */
    private EditPart getEditPart(Element element)
    {
        EditPart result = null;
        if (element != null && getViewer() != null)
        {
            Map registry = getViewer().getEditPartRegistry();
            for (Object o : registry.keySet())
            {
                if (o instanceof GraphElement)
                {
                    GraphElement graphElement = (GraphElement) o;
                    SemanticModelBridge semantic = graphElement.getSemanticModel();
                    if (semantic instanceof EMFSemanticModelBridge)
                    {
                        EMFSemanticModelBridge bridge = (EMFSemanticModelBridge) semantic;
                        if (bridge != null && element.equals(bridge.getElement()))
                        {
                            Object get = registry.get(o);
                            if (get instanceof EditPart)
                            {
                                result = (EditPart) get;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    protected void refreshChildren()
    {
        super.refreshChildren();
    }

    /**
     * Refresh text value
     * 
     * @see org.topcased.modeler.edit.GraphNodeEditPart#refreshVisuals()
     */
    protected void refreshVisuals()
    {

        super.refreshVisuals();
        ((CommentFigure) getFigure()).getLabel().setText(getLabelText());
        if (getTarget() == null)
        {
            setTarget(UMLUtil.getBaseElement(getEObject()));
        }
        EditPart target = getAssociatedEditPart();
        if (target == null)
        {
            if (getAssociationEditPart() != null)
            {
                getAssociationEditPart().getFigure().getParent().remove(getAssociationEditPart().getFigure());
                getAssociationEditPart().deactivate();
            }
        }
    }

    public Element getTarget()
    {
        return target;
    }

    public void setTarget(Element elem)
    {

        this.target = elem;
    }

    /**
     * Get the Label text
     * 
     * @return the associated edit part
     * 
     * @see org.topcased.modeler.sysml.multidiagram.edit.CalloutEditPart#getLabelText()
     */
    protected String getLabelText()
    {
        Element elem = UMLUtil.getBaseElement(getEObject());

        String label = "";
        EList<Stereotype> appliedStereotypes = elem.getAppliedStereotypes();
        if (appliedStereotypes != null && appliedStereotypes.size() > 0)
        {
            for (Stereotype s : appliedStereotypes)
            {
                boolean hasAttribute = false;
                
                EObject stereoAppl = elem.getStereotypeApplication(s);
                EList<EStructuralFeature> eAllAttributes = stereoAppl.eClass().getEAllStructuralFeatures();
                if (eAllAttributes != null && eAllAttributes.size() - 1 > 0) // Always have one structural feature
                {
                    hasAttribute = true;
                }
                
                // The note is displayed only if one of the applied stereotypes have at least one attribute
                if (hasAttribute)
                {
                    label += "<<" + s.getName() + ">>\n";
                    for (EAttribute att : stereoAppl.eClass().getEAllAttributes())
                    {
                        label += att.getName() + " = " + elem.getValue(s, att.getName()) + "\n";
                    }
                }
            }
        }
        return label;
    }

    /**
     * Stop listening the associated Element
     * 
     */
    // protected abstract void unlistenElement();

    public AssociationFromAssociationClassEditPart getAssociationEditPart()
    {
        return associationEditPart;
    }

    public void setAssociationEditPart(AssociationFromAssociationClassEditPart associationEditPart)
    {
        this.associationEditPart = associationEditPart;
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
     */
    protected Color getPreferenceDefaultBackgroundColor()
    {
        return diagramLightBlue;
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultForegroundColor()
     */
    protected Color getPreferenceDefaultForegroundColor()
    {
        return diagramBlack;
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultFont()
     */
    protected Font getPreferenceDefaultFont()
    {
        return null;
    }

}
