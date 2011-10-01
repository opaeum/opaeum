package org.topcased.modeler.uml.classdiagram.preferences;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.topcased.facilities.preferences.AbstractTopcasedPreferencePage;
import org.topcased.modeler.uml.UMLPlugin;

public class EventsPreferencePage extends AbstractTopcasedPreferencePage {

    /**
     * Initializes this preference page for the given workbench.
     * 
     * @param workbench the workbench
     * 
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     * @generated
     */
    public void init(IWorkbench workbench)
    {
        // Do nothing
    }

    /**
     * Creates and returns the SWT control for the customized body of this preference page under the given parent
     * composite.
     * 
     * @param parent the parent composite
     * @return the new control
     * 
     * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     * @generated
     */
    protected Control createContents(Composite parent)
    {
        return null;
    }

    /**
     * @see org.topcased.facilities.preferences.AbstractTopcasedPreferencePage#getBundleId()
     */
    protected String getBundleId()
    {
        return UMLPlugin.getId();
    }

}
