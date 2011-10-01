package org.topcased.modeler.uml.classdiagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.EditPolicy;
import org.eclipse.uml2.uml.Event;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.policies.LabelDirectEditPolicy;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.uml.UMLLabel;
import org.topcased.modeler.uml.classdiagram.figures.EventFigure;
import org.topcased.modeler.utils.Utils;

public abstract class EventEditPart extends EMFGraphNodeEditPart
{

    /**
     * Constructor
     * 
     * @param obj the graph node
     * @generated
     */
    public EventEditPart(GraphNode obj)
    {
        super(obj);
    }

    /**
     * Creates edit policies and associates these with roles
     * 
     * @generated
     */
    protected void createEditPolicies()
    {
        super.createEditPolicies();

        ResizableEditPolicy resizableEditPolicy = new ResizableEditPolicy();
        resizableEditPolicy.setResizeDirections(PositionConstants.EAST_WEST);
        installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, resizableEditPolicy);

        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     * @generated
     */
    protected IFigure createFigure()
    {

        return new EventFigure();
    }

    /**
     * Set the name of the interface with the stereotype and the "from"
     * 
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshHeaderLabel()
     * @generated NOT
     */
    @Override
    protected void refreshHeaderLabel()
    {
        EventFigure fig = (EventFigure) getFigure();
        ComposedLabel lbl = (ComposedLabel) fig.getLabel();
        Event event = (Event) Utils.getElement(getGraphNode());

        String stereotype = UMLLabel.getStereotypesNotation(event, getPreferenceStore());
        if (stereotype != null && stereotype.length() != 0)
        {
            stereotype += " ";
        }
        lbl.setPrefix(stereotype + "<<" + event.eClass().getName() + ">>");

        if (event.getName() != null)
        {
            lbl.setMain(event.getName());
        }
    }
}
