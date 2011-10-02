package org.opeum.topcased.bpmn2;

import org.eclipse.gef.EditPartFactory;
import org.topcased.modeler.uml.activitydiagram.ActivityConfiguration;
import org.topcased.modeler.uml.activitydiagram.ActivityEditPartFactory;

public class Bpmn2Configuration extends ActivityConfiguration{
    private Bpmn2EditPartFactory editPartFactory;

	public EditPartFactory getEditPartFactory()
    {
        if (editPartFactory == null)
        {
            editPartFactory = new Bpmn2EditPartFactory();
        }

        return editPartFactory;
    }

}