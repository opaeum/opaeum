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
package org.topcased.modeler.uml.internal.properties.sections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.PageBookView;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.common.edit.provider.IItemQualifiedTextProvider;
import org.eclipse.uml2.uml.Element;
import org.topcased.modeler.commands.RecordingChangeCommand;
import org.topcased.modeler.editor.Modeler;
import org.topcased.tabbedproperties.utils.ObjectAdapter;
import org.topcased.tabbedproperties.utils.TypeCacheAdapter;

/**
 * An abstract implementation of a section that enables to do multiple selection in a table viewer.
 * 
 * @author eperico
 */
public abstract class AbstractMultipleSelectionSection extends AbstractPropertySection
{
    /**
     * The current selected object or the first object in the selection when
     * multiple objects are selected.
     */
    private EObject eObject;

    private ILabelProvider availableElementsLabelProvider;

    private ILabelProvider appliedElementsLabelProvider;

    private TableViewer availableElementsTableViewer;

    private TableViewer selectedElementsTableViewer;

    private Button addButton;

    private Button removeButton;

    private IDoubleClickListener availableElementsTableDoubleClickListener = new IDoubleClickListener()
    {
        public void doubleClick(DoubleClickEvent event)
        {
            if (addButton.isEnabled())
            {
                addButton.notifyListeners(SWT.Selection, null);
            }
        }
    };

    private IDoubleClickListener selectedElementsTableDoubleClickListener = new IDoubleClickListener()
    {
        public void doubleClick(DoubleClickEvent event)
        {
            if (removeButton.isEnabled())
            {
                removeButton.notifyListeners(SWT.Selection, null);
            }
        }
    };

    private SelectionAdapter addButtonSelectionAdapter = new SelectionAdapter()
    {
        // event is null when availableElementsTableViewer is double clicked
        @SuppressWarnings("unchecked")
        public void widgetSelected(SelectionEvent event)
        {
            if (availableElementsTableViewer != null)
            {
                final List < Element > elementsToApply = new ArrayList < Element > ();
                IStructuredSelection selection = (IStructuredSelection) availableElementsTableViewer.getSelection();
                for (Iterator < Element > i = selection.iterator(); i.hasNext();)
                {
                    Element element = i.next();
                    if (!((ItemProvider) selectedElementsTableViewer.getInput()).getChildren().contains(
                            element))
                    {
                        ((ItemProvider) selectedElementsTableViewer.getInput()).getChildren().add(element);
                    }
                    ((ItemProvider) availableElementsTableViewer.getInput()).getChildren().remove(element);
                    elementsToApply.add(element);
                }

                Command cmd = new RecordingChangeCommand(eObject.eResource())
                {
                    /**
                     * @see org.topcased.modeler.commands.RecordingChangeCommand#doExecute()
                     */
                    protected void doExecute()
                    {
                        doExecuteAddAction(elementsToApply);
                    }
                    @Override
                    public boolean canUndo()
                    {
                       return false;
                    }
                };

                if (cmd != null && cmd.canExecute())
                {
                    ((CommandStack) ((Modeler) getPart().getAdapter(Modeler.class)).getAdapter(CommandStack.class)).execute(cmd);
                }
            }
        }
    };

    private SelectionAdapter removeButtonSelectionAdapter = new SelectionAdapter()
    {
        // event is null when selectedElementsTableViewer is double clicked
        @SuppressWarnings("unchecked")
        public void widgetSelected(SelectionEvent event)
        {
            IStructuredSelection selection = (IStructuredSelection) selectedElementsTableViewer.getSelection();
            Object firstValue = null;

            final List < Element > elementsToUnapply = new ArrayList < Element > ();
            for (Iterator < Element > i = selection.iterator(); i.hasNext();)
            {
                Element element = i.next();
                if (firstValue == null)
                {
                    firstValue = element;
                }

                if (!((ItemProvider) availableElementsTableViewer.getInput()).getChildren().contains(
                        element))
                {
                    ((ItemProvider) availableElementsTableViewer.getInput()).getChildren().add(element);
                }
                ((ItemProvider) selectedElementsTableViewer.getInput()).getChildren().remove(element);

                elementsToUnapply.add(element);
            }

            Command cmd = new RecordingChangeCommand(eObject.eResource())
            {
                /** @see org.topcased.modeler.commands.RecordingChangeCommand#doExecute() */
                protected void doExecute()
                {
                    doExecuteRemoveAction(elementsToUnapply);
                }

                public boolean canUndo()
                {
                    return false;
                }
            };

            if (cmd != null && cmd.canExecute())
            {
                ((CommandStack) ((Modeler) getPart().getAdapter(Modeler.class)).getAdapter(CommandStack.class)).execute(cmd);
            }
        }
    };

    /**
     * <b>Method to implement : </b> execute the modifications on the model after the add action.
     * 
     * @param elementsToApply the elements to apply
     */
    protected abstract void doExecuteAddAction(List < Element > elementsToApply);

    /**
     * <b>Method to implement : </b> execute the modifications on the model after the remove action.
     * 
     * @param elementsToUnapply the elements to unapply
     */
    protected abstract void doExecuteRemoveAction(List < Element > elementsToUnapply);

    /**
     * {@inheritDoc} 
     * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
     * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage, String name)
    {
        super.createControls(parent, aTabbedPropertySheetPage);
        Composite composite = getWidgetFactory().createFlatFormComposite(parent);
        composite.setLayout(new GridLayout(3, false));

        Composite choiceComposite = getWidgetFactory().createComposite(composite, SWT.NONE);
        choiceComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        choiceComposite.setLayout(new GridLayout());

        Label choiceLabel = getWidgetFactory().createLabel(choiceComposite, name, SWT.NONE);
        choiceLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        final Table choiceTable = getWidgetFactory().createTable(choiceComposite, SWT.MULTI | SWT.BORDER);
        choiceTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        availableElementsTableViewer = new TableViewer(choiceTable);

        Composite controlButtons = getWidgetFactory().createComposite(composite, SWT.NONE);
        controlButtons.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        GridLayout controlsButtonGridLayout = new GridLayout();
        controlButtons.setLayout(controlsButtonGridLayout);

        new Label(controlButtons, SWT.NONE);

        addButton = getWidgetFactory().createButton(controlButtons, "Add >", SWT.PUSH);
        addButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        removeButton = getWidgetFactory().createButton(controlButtons, "< Remove", SWT.PUSH);
        removeButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        Label spaceLabel = new Label(controlButtons, SWT.NONE);
        GridData spaceLabelGridData = new GridData();
        spaceLabelGridData.verticalSpan = 2;
        spaceLabel.setLayoutData(spaceLabelGridData);

        Composite featureComposite = getWidgetFactory().createComposite(composite, SWT.NONE);
        featureComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        featureComposite.setLayout(new GridLayout());

        Label featureLabel = getWidgetFactory().createLabel(featureComposite, "", SWT.NONE);
        featureLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        final Table featureTable = getWidgetFactory().createTable(featureComposite, SWT.MULTI | SWT.BORDER);
        featureTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        selectedElementsTableViewer = new TableViewer(featureTable);
    }

    /**
     * {@inheritDoc}
     * @see org.eclipse.ui.views.properties.tabbed.ISection#setInput(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @SuppressWarnings("unchecked")
    public void setInput(IWorkbenchPart part, ISelection selection)
    {
        super.setInput(part, selection);
        if (!(selection instanceof IStructuredSelection))
        {
            return;
        }
        Object ssel = ((IStructuredSelection) selection).getFirstElement();

        eObject = ObjectAdapter.adaptObject(ssel);

        AdapterFactory adapterFactory = new ComposedAdapterFactory(Collections.EMPTY_LIST);
        availableElementsTableViewer.setLabelProvider(getAvailableElementsLabelProvider());
        availableElementsTableViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
        availableElementsTableViewer.setInput(new ItemProvider(adapterFactory, getChoiceOfValues()));

        AdapterFactory adapterFactory2 = new ComposedAdapterFactory(Collections.EMPTY_LIST);
        selectedElementsTableViewer.setLabelProvider(getAppliedElementsLabelProvider());
        selectedElementsTableViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory2));
        selectedElementsTableViewer.setInput(new ItemProvider(adapterFactory2, getSelectedValues()));
    }

    /**
     * {@inheritDoc}
     * @see org.eclipse.ui.views.properties.tabbed.ISection#aboutToBeShown()
     */
    public void aboutToBeShown()
    {
        availableElementsTableViewer.addDoubleClickListener(availableElementsTableDoubleClickListener);
        selectedElementsTableViewer.addDoubleClickListener(selectedElementsTableDoubleClickListener);

        addButton.addSelectionListener(addButtonSelectionAdapter);
        removeButton.addSelectionListener(removeButtonSelectionAdapter);
    }

    /**
     * {@inheritDoc}
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#aboutToBeHidden()
     */
    public void aboutToBeHidden()
    {
        availableElementsTableViewer.removeDoubleClickListener(availableElementsTableDoubleClickListener);
        selectedElementsTableViewer.removeDoubleClickListener(selectedElementsTableDoubleClickListener);

        if (!addButton.isDisposed())
        {
            addButton.removeSelectionListener(addButtonSelectionAdapter);
            removeButton.removeSelectionListener(removeButtonSelectionAdapter);
        }
    }

    /**
     * Obtains the currently active workbench page.
     * 
     * @return the active page, or <code>null</code> if none is active
     */
    protected IWorkbenchPage getActivePage()
    {
        IWorkbenchPage result = null;

        IWorkbench bench = PlatformUI.getWorkbench();
        if (bench != null)
        {
            IWorkbenchWindow window = bench.getActiveWorkbenchWindow();

            if (window != null)
            {
                result = window.getActivePage();
            }
        }

        return result;
    }

    /**
     * This returns the editing domain as required by the
     * {@link IEditingDomainProvider} interface. This is important for
     * implementing the static methods of {@link AdapterFactoryEditingDomain}
     * and for supporting {@link org.eclipse.emf.edit.ui.action.CommandAction}.
     * 
     * @return The required editing domain
     */
    protected EditingDomain getEditingDomain()
    {
        IWorkbenchPart part = getPart();

        if (part.getAdapter(EditingDomain.class) != null)
        {
            return (EditingDomain) getPart().getAdapter(EditingDomain.class);
        }

        if (part instanceof IEditingDomainProvider)
        {
            return ((IEditingDomainProvider) part).getEditingDomain();
        }

        if (part.getAdapter(IEditingDomainProvider.class) != null)
        {
            return ((IEditingDomainProvider) part.getAdapter(IEditingDomainProvider.class)).getEditingDomain();
        }

        if (part instanceof PageBookView)
        {
            IPage page = ((PageBookView) part).getCurrentPage();
            if (page instanceof IEditingDomainProvider)
            {
                return ((IEditingDomainProvider) page).getEditingDomain();
            }
        }

        throw new IllegalArgumentException();
    }

    /**
     * {@inheritDoc}
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#shouldUseExtraSpace()
     */
    public boolean shouldUseExtraSpace()
    {
        return true;
    }

    /**
     * Get the LabelProvider to use to display the Object
     * 
     * @return ILabelProvider
     */
    protected ILabelProvider getAvailableElementsLabelProvider()
    {
        if (availableElementsLabelProvider == null)
        {
            availableElementsLabelProvider = new AdapterFactoryLabelProvider(((Modeler) getPart().getAdapter(
                    Modeler.class)).getAdapterFactory())
            {
                public String getText(Object object)
                {
                    IItemQualifiedTextProvider itemQualifiedTextProvider = (IItemQualifiedTextProvider) adapterFactory.adapt(
                            object, IItemQualifiedTextProvider.class);
                    return itemQualifiedTextProvider != null ? itemQualifiedTextProvider.getQualifiedText(object)
                            : super.getText(object);
                }
            };
        }
        return availableElementsLabelProvider;
    }

    /**
     * Get the LabelProvider to use to display the Object
     * 
     * @return ILabelProvider
     */
    protected ILabelProvider getAppliedElementsLabelProvider()
    {
        if (appliedElementsLabelProvider == null)
        {
            appliedElementsLabelProvider = new AdapterFactoryLabelProvider(((Modeler) getPart().getAdapter(
                    Modeler.class)).getAdapterFactory())
            {
                public String getText(Object object)
                {
                    IItemQualifiedTextProvider itemQualifiedTextProvider = (IItemQualifiedTextProvider) adapterFactory.adapt(
                            object, IItemQualifiedTextProvider.class);
                    return itemQualifiedTextProvider != null ? itemQualifiedTextProvider.getQualifiedText(object)
                            : super.getText(object);
                }
            };
        }
        return appliedElementsLabelProvider;
    }

    /**
     * Get the list of available elements for the current selected Element, then
     * we remove those which are already applied.
     * 
     * @return the list of elements that can be applied
     */
    protected abstract List < EObject > getChoiceOfValues();

    /**
     * Get the list of EObject that are applied on the current selected Element
     * 
     * @return the list of EObject already applied on actual property
     */
    protected abstract List < EObject > getSelectedValues();

    /**
     * Returns a list of all reachable objects of a given type from the current selection.
     * 
     * @param object current EObject selection
     * @param type Reachable object which have this type
     * @return An array of objects of the given type
     */
    protected List < EObject > getChoices(EObject object, EClassifier type)
    {
        List < EObject > choices = new ArrayList < EObject > ();
        choices.addAll(TypeCacheAdapter.getExistingTypeCacheAdapter(getEObject()).getReachableObjectsOfType(eObject, type));
        return choices;
    }

    /**
     * Gets the EObject.
     * 
     * @return the eObject
     */
    public EObject getEObject()
    {
        return eObject;
    }

    /**
     * Gets the available elements table viewer.
     * 
     * @return the availableElementsTableViewer
     */
    public TableViewer getAvailableElementsTableViewer()
    {
        return availableElementsTableViewer;
    }

    /**
     * Gets the selected elements table viewer.
     * 
     * @return the selectedElementsTableViewer
     */
    public TableViewer getSelectedElementsTableViewer()
    {
        return selectedElementsTableViewer;
    }
}
