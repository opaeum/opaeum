/*******************************************************************************
 * Copyright (c) 2006, 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.usecasediagram.preferences;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;
import org.topcased.modeler.preferences.AbstractEdgePreferencePage;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.usecasediagram.UseCaseEdgeObjectConstants;

/**
 * This class represents a preference page that is contributed to the Preferences dialog. This page is used to modify
 * preferences only. They are stored in the preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 * 
 * @generated
 */
public class AssociationPreferencePage extends AbstractEdgePreferencePage
{
    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getEdgeFont()
     * @generated
     */
    protected String getEdgeFont()
    {
        return UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_EDGE_DEFAULT_FONT;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getEdgeForegroundColor()
     * @generated
     */
    protected String getEdgeForegroundColor()
    {
        return UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_EDGE_DEFAULT_FOREGROUND_COLOR;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getEdgeRouter()
     * @generated
     */
    protected String getEdgeRouter()
    {
        return UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_EDGE_DEFAULT_ROUTER;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getHiddenElements()
     * @generated
     */
    protected List<String> getHiddenElements()
    {
        List<String> choiceOfValues = new ArrayList<String>();
        IPreferenceStore ps = getPreferenceStore();
        if (!ps.getBoolean(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_SRCCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(UseCaseEdgeObjectConstants.SRCCOUNT_EDGE_OBJECT_ID);
        }
        if (!ps.getBoolean(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_TARGETCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(UseCaseEdgeObjectConstants.TARGETCOUNT_EDGE_OBJECT_ID);
        }
        if (!ps.getBoolean(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(UseCaseEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID);
        }
        if (!ps.getBoolean(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_NAME_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(UseCaseEdgeObjectConstants.NAME_EDGE_OBJECT_ID);
        }
        return choiceOfValues;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getDefaultHiddenElements()
     * @generated
     */
    protected List<String> getDefaultHiddenElements()
    {
        List<String> choiceOfValues = new ArrayList<String>();
        IPreferenceStore ps = getPreferenceStore();
        if (!ps.getDefaultBoolean(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_SRCCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(UseCaseEdgeObjectConstants.SRCCOUNT_EDGE_OBJECT_ID);
        }
        if (!ps.getDefaultBoolean(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_TARGETCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(UseCaseEdgeObjectConstants.TARGETCOUNT_EDGE_OBJECT_ID);
        }
        if (!ps.getDefaultBoolean(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(UseCaseEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID);
        }
        if (!ps.getDefaultBoolean(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_NAME_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(UseCaseEdgeObjectConstants.NAME_EDGE_OBJECT_ID);
        }
        return choiceOfValues;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getVisibleElements()
     * @generated
     */
    protected List<String> getVisibleElements()
    {
        List<String> choiceOfValues = new ArrayList<String>();
        IPreferenceStore ps = getPreferenceStore();
        if (ps.getBoolean(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_SRCCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(UseCaseEdgeObjectConstants.SRCCOUNT_EDGE_OBJECT_ID);
        }
        if (ps.getBoolean(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_TARGETCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(UseCaseEdgeObjectConstants.TARGETCOUNT_EDGE_OBJECT_ID);
        }
        if (ps.getBoolean(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(UseCaseEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID);
        }
        if (ps.getBoolean(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_NAME_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(UseCaseEdgeObjectConstants.NAME_EDGE_OBJECT_ID);
        }
        return choiceOfValues;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getDefaultVisibleElements()
     * @generated
     */
    protected List<String> getDefaultVisibleElements()
    {
        List<String> choiceOfValues = new ArrayList<String>();
        IPreferenceStore ps = getPreferenceStore();
        if (ps.getDefaultBoolean(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_SRCCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(UseCaseEdgeObjectConstants.SRCCOUNT_EDGE_OBJECT_ID);
        }
        if (ps.getDefaultBoolean(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_TARGETCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(UseCaseEdgeObjectConstants.TARGETCOUNT_EDGE_OBJECT_ID);
        }
        if (ps.getDefaultBoolean(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(UseCaseEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID);
        }
        if (ps.getDefaultBoolean(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_NAME_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(UseCaseEdgeObjectConstants.NAME_EDGE_OBJECT_ID);
        }
        return choiceOfValues;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#storeEdgeObjectVisibility(java.util.List)
     * @generated
     */
    protected void storeEdgeObjectVisibility(List<String> visibleElement)
    {
        IPreferenceStore ps = getPreferenceStore();
        if (visibleElement.contains(UseCaseEdgeObjectConstants.SRCCOUNT_EDGE_OBJECT_ID))
        {
            ps.setValue(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_SRCCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY, true);
        }
        else
        {
            ps.setValue(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_SRCCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY, false);
        }
        if (visibleElement.contains(UseCaseEdgeObjectConstants.TARGETCOUNT_EDGE_OBJECT_ID))
        {
            ps.setValue(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_TARGETCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY, true);
        }
        else
        {
            ps.setValue(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_TARGETCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY, false);
        }
        if (visibleElement.contains(UseCaseEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID))
        {
            ps.setValue(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY, true);
        }
        else
        {
            ps.setValue(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY, false);
        }
        if (visibleElement.contains(UseCaseEdgeObjectConstants.NAME_EDGE_OBJECT_ID))
        {
            ps.setValue(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_NAME_EDGE_OBJECT_DEFAULT_VISIBILITY, true);
        }
        else
        {
            ps.setValue(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_NAME_EDGE_OBJECT_DEFAULT_VISIBILITY, false);
        }
    }

    /**
     * @see org.topcased.facilities.preferences.AbstractTopcasedPreferencePage#getBundleId()
     * @generated
     */
    protected String getBundleId()
    {
        return UMLPlugin.getId();
    }

}
