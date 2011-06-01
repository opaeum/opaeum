/*******************************************************************************
 * 
 * ParameterIsExceptionSection - Parameter isException property for the model
 * view
 * 
 * Copyright (c) 2008 TOPCASED consortium.
 * 
 * Contributors: Emilien Perico - [Atos Origin] emilien.perico@atosorigin.com
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * implement feature request #865 : Unable to create a Parameter
 ******************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.tabbedproperties.sections.AbstractBooleanPropertySection;

/**
 * Parameter isException property for the model view
 * 
 * @author eperico
 */
public class ParameterIsExceptionSection extends AbstractBooleanPropertySection
{

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
     */
    protected EStructuralFeature getFeature()
    {
        return UMLPackage.eINSTANCE.getParameter_IsException();
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractBooleanPropertySection#handleCheckButtonModified()
     */
    @Override
    protected void handleCheckButtonModified()
    {
        super.handleCheckButtonModified();
        Parameter parameter = (Parameter) getEObject();
        Activity activity = (Activity) parameter.getOwner();
        for (ActivityParameterNode node : getParameterNodes(activity, parameter))
        {
            node.setParameter(parameter);
        }
    }

    /**
     * @param activity the specified activity owned the parameter
     * @param parameter the specified parameter
     * @return the ActivityParameterNodes with this specified associated
     *         parameter
     */
    private List<ActivityParameterNode> getParameterNodes(Activity activity, Parameter parameter)
    {
        List<ActivityParameterNode> list = new ArrayList<ActivityParameterNode>();
        for (ActivityNode node : activity.getNodes())
        {
            if (node instanceof ActivityParameterNode)
            {
                ActivityParameterNode apn = (ActivityParameterNode) node;
                if (parameter.equals(apn.getParameter()))
                {
                    list.add(apn);
                }
            }
        }
        return list;
    }

}
