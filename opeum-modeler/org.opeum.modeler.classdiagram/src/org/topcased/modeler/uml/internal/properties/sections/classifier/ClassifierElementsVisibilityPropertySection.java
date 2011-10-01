/*****************************************************************************
 * Copyright (c) 2008 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Thomas Szadel (Atos Origin) - Initial API and implementation
 *
 *****************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections.classifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.topcased.modeler.di.model.DiagramElement;
import org.topcased.modeler.di.model.Property;
import org.topcased.modeler.di.model.util.DIUtils;
import org.topcased.modeler.editor.MixedEditDomain;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.editor.properties.sections.graphics.AbstractGraphicPropertySection;
import org.topcased.modeler.internal.ModelerImageRegistry;
import org.topcased.modeler.uml.classdiagram.ClassElementsVisibilityConstants;
import org.topcased.modeler.uml.classdiagram.annotation.UseElementsVisibility;

/**
 * The visibility section on the graphics tab. Here you can choose to show/hide an EdgeObject
 * 
 * Creation 13 Feb. 07 Updated 08 Jan. 08
 * 
 * @author <a href="mailto:thomas.szadel@atosorigin.com">Thomas SZADEL</a>
 */
public class ClassifierElementsVisibilityPropertySection extends AbstractGraphicPropertySection
{
    /** Properties that can be used. */
    private static final String[] VISIBILITY_PROPERTIES = new String[] {ClassElementsVisibilityConstants.SHOW_OPERATION_PARAMETER_DEFAULT_VALUE,
            ClassElementsVisibilityConstants.SHOW_OPERATION_PARAMETER_TYPE, ClassElementsVisibilityConstants.SHOW_OPERATION_PARAMETERS, ClassElementsVisibilityConstants.SHOW_OPERATION_RETURN_TYPE,
            ClassElementsVisibilityConstants.SHOW_PROPERTY_DEFAULT_VALUE, ClassElementsVisibilityConstants.SHOW_PROPERTY_TYPE};

    /** The label provider. */
    private ILabelProvider labelProvider;

    /** The visible elements table viewer. */
    private TableViewer visibleElementsTableViewer;

    /** The hidden elements table viewer. */
    private TableViewer hiddenElementsTableViewer;

    /** The hide button. */
    private Button hideButton;

    /** The show button. */
    private Button showButton;

    /** The visible elements table double click listener. */
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

    /** The hidden elements table double click listener. */
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

    /** The hide button selection adapter. */
    private SelectionAdapter hideButtonSelectionAdapter = new SelectionAdapter()
    {
        // event is null when availableElementsTableViewer is double clicked
        @SuppressWarnings("unchecked")
        @Override
        public void widgetSelected(SelectionEvent event)
        {
            final List<String> newElements = new ArrayList<String>();
            List<String> visibleElements = (List<String>) visibleElementsTableViewer.getInput();
            List<String> hiddenElements = (List<String>) hiddenElementsTableViewer.getInput();

            IStructuredSelection selection = (IStructuredSelection) visibleElementsTableViewer.getSelection();
            for (Iterator<String> i = selection.iterator(); i.hasNext();)
            {
                String value = i.next();
                visibleElements.remove(value);
                hiddenElements.add(value);

                newElements.add(value);
            }
            // Force table to be refreshed
            hiddenElementsTableViewer.refresh();
            visibleElementsTableViewer.refresh();

            // Iterate on all the selected elements
            MixedEditDomain mixedEditDomain = (MixedEditDomain) ((Modeler) getPart().getAdapter(Modeler.class)).getAdapter(MixedEditDomain.class);
            for (DiagramElement selectedElt : getDiagEltList())
            {
                ChangeVisibilityCommand cmd = new ChangeVisibilityCommand(selectedElt, newElements, false);
                if (cmd.canExecute())
                {
                    mixedEditDomain.getEMFEditingDomain().getCommandStack().execute(cmd);
                }
            }
        }
    };

    /** The show button selection adapter. */
    private SelectionAdapter showButtonSelectionAdapter = new SelectionAdapter()
    {
        // event is null when selectedElementsTableViewer is double clicked
        @SuppressWarnings("unchecked")
        @Override
        public void widgetSelected(SelectionEvent event)
        {
            final List<String> newElements = new ArrayList<String>();
            List<String> visibleElements = (List<String>) visibleElementsTableViewer.getInput();
            List<String> hiddenElements = (List<String>) hiddenElementsTableViewer.getInput();

            IStructuredSelection selection = (IStructuredSelection) hiddenElementsTableViewer.getSelection();
            for (Iterator<String> i = selection.iterator(); i.hasNext();)
            {
                String value = i.next();
                visibleElements.add(value);
                hiddenElements.remove(value);

                newElements.add(value);
            }
            // Force table to be refreshed
            hiddenElementsTableViewer.refresh();
            visibleElementsTableViewer.refresh();

            // Iterate on all the selected elements
            MixedEditDomain mixedEditDomain = (MixedEditDomain) ((Modeler) getPart().getAdapter(Modeler.class)).getAdapter(MixedEditDomain.class);
            for (DiagramElement selectedElt : getDiagEltList())
            {
                ChangeVisibilityCommand cmd = new ChangeVisibilityCommand(selectedElt, newElements, true);
                if (cmd.canExecute())
                {
                    mixedEditDomain.getEMFEditingDomain().getCommandStack().execute(cmd);
                }
            }
        }
    };

    /**
     * Creates the controls.
     * 
     * @param parent the parent
     * @param aTabbedPropertySheetPage the a tabbed property sheet page
     * 
     * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage)
    {
        super.createControls(parent, aTabbedPropertySheetPage);
        Composite mainComposite = getWidgetFactory().createFlatFormComposite(parent);
        mainComposite.setLayout(new GridLayout(3, false));

        Composite choiceComposite = getWidgetFactory().createComposite(mainComposite, SWT.NONE);
        choiceComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        choiceComposite.setLayout(new GridLayout());

        Label choiceLabel = getWidgetFactory().createLabel(choiceComposite, "Visible Elements", SWT.NONE);
        choiceLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        final Table choiceTable = getWidgetFactory().createTable(choiceComposite, SWT.MULTI | SWT.BORDER);
        choiceTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        visibleElementsTableViewer = new TableViewer(choiceTable);

        Composite controlButtons = getWidgetFactory().createComposite(mainComposite, SWT.NONE);
        controlButtons.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        GridLayout controlsButtonGridLayout = new GridLayout();
        controlButtons.setLayout(controlsButtonGridLayout);

        new Label(controlButtons, SWT.NONE);

        hideButton = getWidgetFactory().createButton(controlButtons, "Hide >", SWT.PUSH);
        hideButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        showButton = getWidgetFactory().createButton(controlButtons, "< Show", SWT.PUSH);
        showButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        Label spaceLabel = new Label(controlButtons, SWT.NONE);
        GridData spaceLabelGridData = new GridData();
        spaceLabelGridData.verticalSpan = 2;
        spaceLabel.setLayoutData(spaceLabelGridData);

        Composite featureComposite = getWidgetFactory().createComposite(mainComposite, SWT.NONE);
        featureComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        featureComposite.setLayout(new GridLayout());

        Label featureLabel = getWidgetFactory().createLabel(featureComposite, "Hidden Elements", SWT.NONE);
        featureLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        final Table featureTable = getWidgetFactory().createTable(featureComposite, SWT.MULTI | SWT.BORDER);
        featureTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        hiddenElementsTableViewer = new TableViewer(featureTable);
    }

    /**
     * Sets the input.
     * 
     * @param part the part
     * @param selection the selection
     * 
     * @see org.eclipse.ui.views.properties.tabbed.ISection#setInput(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(IWorkbenchPart part, ISelection selection)
    {
        super.setInput(part, selection);

        visibleElementsTableViewer.setLabelProvider(getLabelProvider());
        visibleElementsTableViewer.setContentProvider(new ArrayContentProvider());
        visibleElementsTableViewer.setInput(getVisibleElements());

        hiddenElementsTableViewer.setLabelProvider(getLabelProvider());
        hiddenElementsTableViewer.setContentProvider(new ArrayContentProvider());
        hiddenElementsTableViewer.setInput(getHiddenElements());
    }

    /**
     * About to be shown.
     * 
     * @see org.eclipse.ui.views.properties.tabbed.ISection#aboutToBeShown()
     */
    @Override
    public void aboutToBeShown()
    {
        super.aboutToBeShown();

        visibleElementsTableViewer.addDoubleClickListener(visibleElementsTableDoubleClickListener);
        hiddenElementsTableViewer.addDoubleClickListener(hiddenElementsTableDoubleClickListener);

        hideButton.addSelectionListener(hideButtonSelectionAdapter);
        showButton.addSelectionListener(showButtonSelectionAdapter);
    }

    /**
     * About to be hidden.
     * 
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#aboutToBeHidden()
     */
    @Override
    public void aboutToBeHidden()
    {
        super.aboutToBeHidden();

        visibleElementsTableViewer.removeDoubleClickListener(visibleElementsTableDoubleClickListener);
        hiddenElementsTableViewer.removeDoubleClickListener(hiddenElementsTableDoubleClickListener);

        if (!hideButton.isDisposed())
        {
            hideButton.removeSelectionListener(hideButtonSelectionAdapter);
            showButton.removeSelectionListener(showButtonSelectionAdapter);
        }
    }

    /**
     * Should use extra space.
     * 
     * @return true, if should use extra space
     * 
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#shouldUseExtraSpace()
     */
    @Override
    public boolean shouldUseExtraSpace()
    {
        return true;
    }

    /**
     * Get the LabelProvider to use to display the Object.
     * 
     * @return ILabelProvider
     */
    protected ILabelProvider getLabelProvider()
    {
        if (labelProvider == null)
        {
            labelProvider = new LabelProvider()
            {
                /** @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object) */
                @Override
                public Image getImage(Object element)
                {
                    return ModelerImageRegistry.getImage("EDGE_OBJECT");
                }
            };
        }

        return labelProvider;
    }

    /**
     * Get the list of visible EdgeObject IDs for the current selected GraphElement.
     * 
     * @return List of visible elements.
     */
    protected List<String> getVisibleElements()
    {
        List<String> choiceOfValues = new ArrayList<String>();
        DiagramElement selElt = getDiagElt();
        if (selElt != null)
        {
            for (String prop : VISIBILITY_PROPERTIES)
            {
                if (showElement(selElt, prop, getSelectedEditPart()))
                {
                    choiceOfValues.add(prop);
                }
            }
        }
        return choiceOfValues;
    }

    /**
     * Get the list of EdgeObject IDs contained by the current selected GraphElement which are hidden.
     * 
     * @return List of hidden elements.
     */
    protected List<String> getHiddenElements()
    {
        List<String> choiceOfValues = new ArrayList<String>();
        DiagramElement selElt = getDiagElt();
        if (selElt != null)
        {
            for (String prop : VISIBILITY_PROPERTIES)
            {
                if (!showElement(selElt, prop, getSelectedEditPart()))
                {
                    choiceOfValues.add(prop);
                }
            }
        }
        return choiceOfValues;
    }

    /**
     * Check if an element has to be visible or not.
     * 
     * @param graphElement The graph element.
     * @param property The property.
     * @param editPart The editpart.
     * 
     * @return True if it has to be visible, false otherwise.
     * 
     * @see ClassElementsVisibilityConstants
     */
    private boolean showElement(DiagramElement graphElement, String property, EditPart editPart)
    {
        Property prop = DIUtils.getProperty(graphElement, property);
        if (prop == null)
        {
            // Get the preference
            String prefix = getPreferencePrefix(editPart);
            String prefixedProp = prefix + property;
            IPreferenceStore store = ((Modeler) getPart().getAdapter(Modeler.class)).getPreferenceStore();
            return store.getBoolean(prefixedProp);
        }
        else
        {
            return "true".equals(prop.getValue());
        }
    }

    
    
    @Override
    protected void setEnabled(boolean enabled)
    {
        super.setEnabled(enabled);
        if (hiddenElementsTableViewer != null && hiddenElementsTableViewer.getControl() != null)
        {
            hiddenElementsTableViewer.getControl().setEnabled(enabled);
        }
        if (visibleElementsTableViewer != null && visibleElementsTableViewer.getControl() != null)
        {
            visibleElementsTableViewer.getControl().setEnabled(enabled);
        }
        if (hideButton != null)
        {
            hideButton.setEnabled(enabled);
        }
        if (showButton != null)
        {
            showButton.setEnabled(enabled);
        }
    }

    /**
     * Returns the preference prefix.
     * 
     * @param editPart The editpart.
     * @return The preference prefix.
     */
    protected String getPreferencePrefix(EditPart editPart)
    {
        UseElementsVisibility annot = editPart != null ? editPart.getClass().getAnnotation(UseElementsVisibility.class) : null;
        return annot != null ? annot.preferencePrefix() : "";
    }

    /**
     * Handle model changed.
     * 
     * @param msg the msg
     * 
     * @see org.topcased.modeler.editor.properties.sections.graphics.AbstractGraphicPropertySection#handleModelChanged(org.eclipse.emf.common.notify.Notification)
     */
    @Override
    protected void handleModelChanged(Notification msg)
    {
        // Do nothing

    }

    /**
     * Command that changes the visibility of given elements.
     * 
     * @author <a href="mailto:thomas.szadel@atosorigin.com">Thomas Szadel</a>
     * 
     */
    private class ChangeVisibilityCommand extends AbstractCommand
    {
        private DiagramElement graphElement;

        private Collection<String> newVisibilities;

        private boolean visibility;

        public ChangeVisibilityCommand(DiagramElement graphElement, Collection<String> visibilities, boolean newVisibilty)
        {
            this.graphElement = graphElement;
            this.newVisibilities = visibilities;
            this.visibility = newVisibilty;

        }

        /**
         * @see org.eclipse.emf.common.command.AbstractCommand#canExecute()
         */
        @Override
        public boolean canExecute()
        {
            return !newVisibilities.isEmpty();
        }

        /**
         * @see org.eclipse.emf.common.command.Command#execute()
         */
        public void execute()
        {
            redo();
        }

        /**
         * @see org.eclipse.emf.common.command.Command#redo()
         */
        public void redo()
        {
            changeVisibility(visibility);
        }

        /**
         * Change the visibility of the elements.
         * 
         * @param pVisible True if visible, false otherwise.
         */
        private void changeVisibility(boolean pVisible)
        {
            String lVisStr = Boolean.toString(pVisible);
            for (String prop : newVisibilities)
            {
                DIUtils.setProperty(graphElement, prop, lVisStr);
            }

            // Refresh the visuals
            EditPart editPart = (EditPart) ((GraphicalViewer) getPart().getAdapter(GraphicalViewer.class)).getEditPartRegistry().get(graphElement);
            editPart.refresh();
        }

        /**
         * @see org.eclipse.emf.common.command.AbstractCommand#undo()
         */
        @Override
        public void undo()
        {
            changeVisibility(!visibility);
        }
    }
}
