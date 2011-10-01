/*******************************************************************************
 * Copyright (c) 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.preferences;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.topcased.modeler.preferences.AbstractNodePreferencePage;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.classdiagram.ClassElementsVisibilityConstants;

/**
 * This class represents a preference page that is contributed to the Preferences or Property dialog. This page is used
 * to modify preferences only. They are stored in the preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 * 
 * @generated NOT
 */
public abstract class ClassifierPreferencePage extends AbstractNodePreferencePage
{
    /**
     * Properties that can be used. NB: the values are the same in the preference pages that do not override {@link #getAvailableVisibilityProperties()}
     */
    private static final String[] VISIBILITY_PROPERTIES = new String[] {ClassElementsVisibilityConstants.SHOW_OPERATION_PARAMETER_DEFAULT_VALUE,
            ClassElementsVisibilityConstants.SHOW_OPERATION_PARAMETER_TYPE, ClassElementsVisibilityConstants.SHOW_OPERATION_PARAMETERS, ClassElementsVisibilityConstants.SHOW_OPERATION_RETURN_TYPE,
            ClassElementsVisibilityConstants.SHOW_PROPERTY_DEFAULT_VALUE, ClassElementsVisibilityConstants.SHOW_PROPERTY_TYPE};

    private Button hideButton;

    private Button showButton;

    private TableViewer visibleElementsTableViewer;

    private TableViewer hiddenElementsTableViewer;

    /**
     * @see org.topcased.facilities.preferences.AbstractTopcasedPreferencePage#getBundleId()
     */
    @Override
    protected String getBundleId()
    {
        return UMLPlugin.getId();
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractNodePreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createDisplay(Composite parent)
    {
        super.createDisplay(parent);

        Group groupElements = new Group(parent, SWT.SHADOW_ETCHED_OUT);
        groupElements.setText("Elements");
        groupElements.setLayout(new GridLayout(3, false));
        groupElements.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Composite choiceComposite = new Composite(groupElements, SWT.NONE);
        choiceComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        choiceComposite.setLayout(new GridLayout());

        Label choiceLabel = new Label(choiceComposite, SWT.NONE);
        choiceLabel.setText("Visible Elements");
        choiceLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Table choiceTable = new Table(choiceComposite, SWT.MULTI | SWT.BORDER);
        choiceTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        visibleElementsTableViewer = new TableViewer(choiceTable);

        Composite controlButtons = new Composite(groupElements, SWT.NONE);
        controlButtons.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        GridLayout controlsButtonGridLayout = new GridLayout();
        controlButtons.setLayout(controlsButtonGridLayout);

        new Label(controlButtons, SWT.NONE);

        hideButton = new Button(controlButtons, SWT.PUSH);
        hideButton.setText("Hide >");
        hideButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        showButton = new Button(controlButtons, SWT.PUSH);
        showButton.setText("< Show");
        showButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        Label spaceLabel = new Label(controlButtons, SWT.NONE);
        GridData spaceLabelGridData = new GridData();
        spaceLabelGridData.verticalSpan = 2;
        spaceLabel.setLayoutData(spaceLabelGridData);

        Composite featureComposite = new Composite(groupElements, SWT.NONE);
        featureComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        featureComposite.setLayout(new GridLayout());

        Label featureLabel = new Label(featureComposite, SWT.NONE);
        featureLabel.setText("Hidden Elements");
        featureLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        Table featureTable = new Table(featureComposite, SWT.MULTI | SWT.BORDER);
        featureTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        hiddenElementsTableViewer = new TableViewer(featureTable);

        visibleElementsTableViewer.addDoubleClickListener(visibleElementsTableDoubleClickListener);
        hiddenElementsTableViewer.addDoubleClickListener(hiddenElementsTableDoubleClickListener);

        hideButton.addSelectionListener(hideButtonSelectionAdapter);
        showButton.addSelectionListener(showButtonSelectionAdapter);
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractNodePreferencePage#loadPreferences()
     */
    @Override
    protected void loadPreferences()
    {
        super.loadPreferences();
        visibleElementsTableViewer.setContentProvider(new ArrayContentProvider());
        visibleElementsTableViewer.setInput(getVisibleElements());

        hiddenElementsTableViewer.setContentProvider(new ArrayContentProvider());
        hiddenElementsTableViewer.setInput(getHiddenElements());

    }

    /**
     * Returns the property with prefix to add to store a property.
     * 
     * @param The property.
     * @return The property with prefix.
     */
    protected String getPropertyWithPrefix(String property)
    {
        return getPreferencePrefix() + property;
    }

    /**
     * Returns the prefix to used to get the preference of the visibility of the operation and property content.
     * 
     * @return The prefix to use.
     */
    protected abstract String getPreferencePrefix();

    /**
     * Returns the visible elements.
     * 
     * @return the visible elements.
     */
    private List<String> getVisibleElements()
    {
        List<String> choiceOfValues = new ArrayList<String>();
        for (String prop : getAvailableVisibilityProperties())
        {
            String prefixedProp = getPropertyWithPrefix(prop);
            if (getPreferenceStore().getBoolean(prefixedProp))
            {
                choiceOfValues.add(prefixedProp);
            }
        }
        return choiceOfValues;
    }

    /**
     * Returns the hidden elements.
     * 
     * @return the hidden elements.
     */
    private List<String> getHiddenElements()
    {
        List<String> choiceOfValues = new ArrayList<String>();
        for (String prop : getAvailableVisibilityProperties())
        {
            String prefixedProp = getPropertyWithPrefix(prop);
            if (!getPreferenceStore().getBoolean(prefixedProp))
            {
                choiceOfValues.add(prefixedProp);
            }
        }
        return choiceOfValues;
    }

    /**
     * Returns the Default visible elements.
     * 
     * @return the Default visible elements.
     */
    private List<String> getDefaultVisibleElements()
    {
        List<String> choiceOfValues = new ArrayList<String>();

        for (String prop : getAvailableVisibilityProperties())
        {
            String prefixedProp = getPropertyWithPrefix(prop);
            if (getPreferenceStore().getDefaultBoolean(prefixedProp))
            {
                choiceOfValues.add(prefixedProp);
            }
        }
        return choiceOfValues;
    }

    /**
     * Returns the Default hidden elements.
     * 
     * @return the Default hidden elements.
     */
    private List<String> getDefaultHiddenElements()
    {
        List<String> choiceOfValues = new ArrayList<String>();
        for (String prop : getAvailableVisibilityProperties())
        {
            String prefixedProp = getPropertyWithPrefix(prop);
            if (!getPreferenceStore().getDefaultBoolean(prefixedProp))
            {
                choiceOfValues.add(prefixedProp);
            }
        }
        return choiceOfValues;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractNodePreferencePage#loadDefaultPreferences()
     */
    @Override
    protected void loadDefaultPreferences()
    {
        super.loadDefaultPreferences();
        visibleElementsTableViewer.setContentProvider(new ArrayContentProvider());
        visibleElementsTableViewer.setInput(getDefaultVisibleElements());

        hiddenElementsTableViewer.setContentProvider(new ArrayContentProvider());
        hiddenElementsTableViewer.setInput(getDefaultHiddenElements());
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractNodePreferencePage#storePreferences()
     */
    @Override
    protected void storePreferences()
    {
        super.storePreferences();

        List<String> visibleElements = (List<String>) visibleElementsTableViewer.getInput();
        for (String prop : getAvailableVisibilityProperties())
        {
            String prefixedProp = getPropertyWithPrefix(prop);
            getPreferenceStore().setValue(prefixedProp, visibleElements.contains(prefixedProp));
        }
    }

    /**
     * Perform the action when the Visible table is clicked.
     */
    private IDoubleClickListener visibleElementsTableDoubleClickListener = new IDoubleClickListener()
    {
        public void doubleClick(DoubleClickEvent event)
        {
            if (hideButton.isEnabled())
            {
                hideButton.notifyListeners(SWT.Selection, null);
            }
        }
    };

    /**
     * Perform the action when the Hide table is clicked.
     */
    private IDoubleClickListener hiddenElementsTableDoubleClickListener = new IDoubleClickListener()
    {
        public void doubleClick(DoubleClickEvent event)
        {
            if (showButton.isEnabled())
            {
                showButton.notifyListeners(SWT.Selection, null);
            }
        }
    };

    /**
     * Perform the action when the Hide button is clicked.
     */
    private SelectionAdapter hideButtonSelectionAdapter = new SelectionAdapter()
    {
        // event is null when availableElementsTableViewer is double clicked
        @SuppressWarnings("unchecked")
        @Override
        public void widgetSelected(SelectionEvent event)
        {
            if (visibleElementsTableViewer != null)
            {
                List<String> hiddenElements = (List<String>) hiddenElementsTableViewer.getInput();
                List<String> visibleElements = (List<String>) visibleElementsTableViewer.getInput();
                IStructuredSelection selection = (IStructuredSelection) visibleElementsTableViewer.getSelection();
                for (Iterator<String> i = selection.iterator(); i.hasNext();)
                {
                    String value = i.next();
                    hiddenElements.add(value);
                    visibleElements.remove(value);
                }
                // Force table to be refreshed
                hiddenElementsTableViewer.refresh();
                visibleElementsTableViewer.refresh();
            }
        }
    };

    /**
     * Perform the action when the Show button is clicked.
     */
    private SelectionAdapter showButtonSelectionAdapter = new SelectionAdapter()
    {
        // event is null when selectedElementsTableViewer is double clicked
        @SuppressWarnings("unchecked")
        @Override
        public void widgetSelected(SelectionEvent event)
        {
            IStructuredSelection selection = (IStructuredSelection) hiddenElementsTableViewer.getSelection();
            List<String> hiddenElements = (List<String>) hiddenElementsTableViewer.getInput();
            List<String> visibleElements = (List<String>) visibleElementsTableViewer.getInput();

            for (Iterator<String> i = selection.iterator(); i.hasNext();)
            {
                String value = i.next();
                visibleElements.add(value);
                hiddenElements.remove(value);
            }
            // Force table to be refreshed
            hiddenElementsTableViewer.refresh();
            visibleElementsTableViewer.refresh();
        }
    };

    /**
     * Get the visibility Properties that can be used. This method can be overridden in case some classic Properties
     * must not be displayed.
     * 
     * @return available visibility Properties
     */
    protected String[] getAvailableVisibilityProperties()
    {
        return VISIBILITY_PROPERTIES;
    }

}
