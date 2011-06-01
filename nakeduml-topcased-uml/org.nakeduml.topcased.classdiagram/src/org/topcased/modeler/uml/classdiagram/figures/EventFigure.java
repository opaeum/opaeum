package org.topcased.modeler.uml.classdiagram.figures;

import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;

public class EventFigure extends InterfaceFigure {
    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EventFigure()
    {
        super();
    }

    /**
     * @see org.topcased.draw2d.figures.ClassFigure#createLabel()
     */
    protected ILabel createLabel()
    {
        return new ComposedLabel(new Label(), new EditableLabel(), new Label(), false);
    }
}
