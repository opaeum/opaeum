/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jacques Lescot (Anyware Technologies) - initial API and
 * implementation
 *     - Benoit MAGGI (Atos Origin) feature 2222 : using seteotype section on many element
 * 	
 ******************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
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
import org.eclipse.uml2.uml.Stereotype;
import org.topcased.modeler.commands.CompoundCommand;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.uml.commands.ApplyProfileCommand;
import org.topcased.modeler.uml.commands.UnapplyProfileCommand;
import org.topcased.modeler.utils.Utils;
import org.topcased.tabbedproperties.utils.ObjectAdapter;

/**
 * The section used to Apply/Unapply a Stereotype on a given Element
 * 
 * Creation 21 nov. 06
 * 
 * @author <a href="mailto:jacques.lescot@anyware-tech.com">Jacques LESCOT</a>
 */
public class StereotypesSection extends AbstractPropertySection
{

    private List<EObject> eObjectList;

    private ILabelProvider labelProvider;

    private TableViewer availableElementsTableViewer;

    private TableViewer selectedElementsTableViewer;

    private Button addButton;

    private Button removeButton;

    private Button upButton;

    private Button downButton;

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
        public void widgetSelected(SelectionEvent event)
        {
            if (availableElementsTableViewer != null)
            {
                final List<Stereotype> newElements = new ArrayList<Stereotype>();
                IStructuredSelection selection = (IStructuredSelection) availableElementsTableViewer.getSelection();
                for (Iterator< ? > i = selection.iterator(); i.hasNext();)
                {
                    Stereotype value = (Stereotype) i.next();

                    EList<Object> children = ((ItemProvider) selectedElementsTableViewer.getInput()).getChildren();
                    if (!children.contains(value))
                    {
                        children.add(value);
                    }
                    ((ItemProvider) availableElementsTableViewer.getInput()).getChildren().remove(value);

                    newElements.add(value);
                }

                CompoundCommand cmd = new CompoundCommand();

                // iteration on each object selected
                for (EObject tEObject : eObjectList)
                {

                    // create the real command
                    List<Stereotype> tStereotypeListEffectivlyApplied = new ArrayList<Stereotype>();

                    // refresh
                    GraphElement graphElement = Utils.getGraphElement(((Modeler) getPart().getAdapter(Modeler.class)).getActiveDiagram(), tEObject, true);
                    Object editPart = ((GraphicalViewer) ((Modeler) getPart().getAdapter(Modeler.class)).getAdapter(GraphicalViewer.class)).getEditPartRegistry().get(graphElement);

                    for (Stereotype tStereotype : newElements)
                    {
                        if (!((Element) tEObject).isStereotypeApplied(tStereotype))
                        {
                            tStereotypeListEffectivlyApplied.add(tStereotype);
                        }
                    }

                    if (tStereotypeListEffectivlyApplied != null && !tStereotypeListEffectivlyApplied.isEmpty())
                    {
                        Command lCommand = new ApplyProfileCommand(tEObject, tStereotypeListEffectivlyApplied, (EditPart) editPart);
                        cmd.add(lCommand);
                    }

                }

                if (cmd != null && cmd.canExecute())
                {
                    ((CommandStack) ((Modeler) getPart().getAdapter(Modeler.class)).getAdapter(CommandStack.class)).execute(cmd);
                }
            }
        }
    };

    @Override
    public void refresh()
    {
        super.refresh();
        Collection<EObject> eobjects = new ArrayList<EObject>(eObjectList.size() + 1);
        eobjects.addAll(eObjectList);
        eobjects.remove(null);
        boolean isEnabled = true;
        for (EObject e : eobjects)
        {
            EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(e);
            if (domain != null)
            {
                isEnabled &= !domain.isReadOnly(e.eResource());
            }
        }
        availableElementsTableViewer.getControl().setEnabled(isEnabled);
        selectedElementsTableViewer.getControl().setEnabled(isEnabled);
        addButton.setEnabled(isEnabled);
        removeButton.setEnabled(isEnabled);
        upButton.setEnabled(isEnabled);
        downButton.setEnabled(isEnabled);
    }

    private SelectionAdapter removeButtonSelectionAdapter = new SelectionAdapter()
    {
        // event is null when selectedElementsTableViewer is double clicked
        public void widgetSelected(SelectionEvent event)
        {
            IStructuredSelection selection = (IStructuredSelection) selectedElementsTableViewer.getSelection();

            final List<Stereotype> oldElements = new ArrayList<Stereotype>();
            for (Iterator< ? > i = selection.iterator(); i.hasNext();)
            {
                Stereotype value = (Stereotype) i.next();

                EList<Object> children = ((ItemProvider) availableElementsTableViewer.getInput()).getChildren();

                if (!children.contains(value))
                {
                    children.add(value);
                }

                ((ItemProvider) selectedElementsTableViewer.getInput()).getChildren().remove(value);

                oldElements.add(value);
            }

            CompoundCommand cmd = new CompoundCommand();

            for (EObject tEObject : eObjectList)
            {

                List<Stereotype> tStereotypeListEffectivlyUnApplied = new ArrayList<Stereotype>();

                // refresh
                GraphElement graphElement = Utils.getGraphElement(((Modeler) getPart().getAdapter(Modeler.class)).getActiveDiagram(), tEObject, true);
                Object editPart = ((GraphicalViewer) ((Modeler) getPart().getAdapter(Modeler.class)).getAdapter(GraphicalViewer.class)).getEditPartRegistry().get(graphElement);

                for (Stereotype tStereotype : oldElements)
                {
                    if (((Element) tEObject).isStereotypeApplied(tStereotype))
                    {
                        tStereotypeListEffectivlyUnApplied.add(tStereotype);
                    }
                }

                if (tStereotypeListEffectivlyUnApplied != null && !tStereotypeListEffectivlyUnApplied.isEmpty())
                {
                    Command lCommand = new UnapplyProfileCommand(tEObject, tStereotypeListEffectivlyUnApplied, (EditPart) editPart);
                    cmd.add(lCommand);
                }

            }

            if (cmd != null && cmd.canExecute())
            {
                ((CommandStack) ((Modeler) getPart().getAdapter(Modeler.class)).getAdapter(CommandStack.class)).execute(cmd);
            }
        }
    };

    private SelectionAdapter upButtonSelectionAdapter = new SelectionAdapter()
    {
        public void widgetSelected(SelectionEvent event)
        {
            IStructuredSelection selection = (IStructuredSelection) selectedElementsTableViewer.getSelection();
            int minIndex = 0;
            for (Iterator< ? > i = selection.iterator(); i.hasNext();)
            {
                Object value = i.next();
                int index = ((ItemProvider) selectedElementsTableViewer.getInput()).getChildren().indexOf(value);
                ((ItemProvider) selectedElementsTableViewer.getInput()).getChildren().move(Math.max(index - 1, minIndex++), value);
            }
        }
    };

    private SelectionAdapter downButtonSelectionAdapter = new SelectionAdapter()
    {
        public void widgetSelected(SelectionEvent event)
        {
            IStructuredSelection selection = (IStructuredSelection) selectedElementsTableViewer.getSelection();
            int maxIndex = ((ItemProvider) selectedElementsTableViewer.getInput()).getChildren().size() - selection.size();
            for (Iterator< ? > i = selection.iterator(); i.hasNext();)
            {
                Object value = i.next();
                int index = ((ItemProvider) selectedElementsTableViewer.getInput()).getChildren().indexOf(value);
                ((ItemProvider) selectedElementsTableViewer.getInput()).getChildren().move(Math.min(index + 1, maxIndex++), value);
            }
        }
    };

    /**
     * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage)
    {
        super.createControls(parent, aTabbedPropertySheetPage);
        Composite composite = getWidgetFactory().createFlatFormComposite(parent);
        composite.setLayout(new GridLayout(3, false));

        Composite choiceComposite = getWidgetFactory().createComposite(composite, SWT.NONE);
        choiceComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        choiceComposite.setLayout(new GridLayout());

        Label choiceLabel = getWidgetFactory().createLabel(choiceComposite, "Available Stereotypes", SWT.NONE);
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

        upButton = getWidgetFactory().createButton(controlButtons, "Up", SWT.PUSH);
        upButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        downButton = getWidgetFactory().createButton(controlButtons, "Down", SWT.PUSH);
        downButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        Composite featureComposite = getWidgetFactory().createComposite(composite, SWT.NONE);
        featureComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        featureComposite.setLayout(new GridLayout());

        Label featureLabel = getWidgetFactory().createLabel(featureComposite, "Applied Stereotypes", SWT.NONE);
        featureLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        final Table featureTable = getWidgetFactory().createTable(featureComposite, SWT.MULTI | SWT.BORDER);
        featureTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        selectedElementsTableViewer = new TableViewer(featureTable);
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.ISection#setInput(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public void setInput(IWorkbenchPart part, ISelection selection)
    {
        super.setInput(part, selection);
        if (!(selection instanceof IStructuredSelection))
        {
            return;
        }

        eObjectList = new ArrayList<EObject>();
        IStructuredSelection lIStructuredSelection = (IStructuredSelection) selection;

        for (Object lObject : lIStructuredSelection.toList())
        {
            eObjectList.add(ObjectAdapter.adaptObject(lObject));
        }

        AdapterFactory adapterFactory = new ComposedAdapterFactory(Collections.EMPTY_LIST);
        availableElementsTableViewer.setLabelProvider(getLabelProvider());
        availableElementsTableViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
        availableElementsTableViewer.setInput(new ItemProvider(adapterFactory, getChoiceOfValues()));

        AdapterFactory adapterFactory2 = new ComposedAdapterFactory(Collections.EMPTY_LIST);
        selectedElementsTableViewer.setLabelProvider(getLabelProvider());
        selectedElementsTableViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory2));
        selectedElementsTableViewer.setInput(new ItemProvider(adapterFactory2, getAppliedStereotypes()));
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.ISection#aboutToBeShown()
     */
    public void aboutToBeShown()
    {
        availableElementsTableViewer.addDoubleClickListener(availableElementsTableDoubleClickListener);
        selectedElementsTableViewer.addDoubleClickListener(selectedElementsTableDoubleClickListener);

        addButton.addSelectionListener(addButtonSelectionAdapter);
        removeButton.addSelectionListener(removeButtonSelectionAdapter);
        upButton.addSelectionListener(upButtonSelectionAdapter);
        downButton.addSelectionListener(downButtonSelectionAdapter);
    }

    /**
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
            upButton.removeSelectionListener(upButtonSelectionAdapter);
            downButton.removeSelectionListener(downButtonSelectionAdapter);
        }
    }

    /**
     * Obtains the currently active workbench page.
     * 
     * @return the active page, or <code>null</code> if none is active
     */
    public IWorkbenchPage getActivePage()
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
     * This returns the editing domain as required by the {@link IEditingDomainProvider} interface. This is important
     * for implementing the static methods of {@link AdapterFactoryEditingDomain} and for supporting
     * {@link org.eclipse.emf.edit.ui.action.CommandAction}.
     * 
     * @return The required editing domain
     * @throws IllegalArgumentException There is an error when the part cannot be adapted in any EditingDomain.
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
    protected ILabelProvider getLabelProvider()
    {
        if (labelProvider == null)
        {
            labelProvider = new AdapterFactoryLabelProvider(((Modeler) getPart().getAdapter(Modeler.class)).getAdapterFactory())
            {
                public String getColumnText(Object object, int columnIndex)
                {
                    IItemQualifiedTextProvider itemQualifiedTextProvider = (IItemQualifiedTextProvider) adapterFactory.adapt(object, IItemQualifiedTextProvider.class);

                    return itemQualifiedTextProvider != null ? itemQualifiedTextProvider.getQualifiedText(object) : super.getColumnText(object, columnIndex);
                }

                public String getText(Object object)
                {
                    IItemQualifiedTextProvider itemQualifiedTextProvider = (IItemQualifiedTextProvider) adapterFactory.adapt(object, IItemQualifiedTextProvider.class);

                    return itemQualifiedTextProvider != null ? itemQualifiedTextProvider.getQualifiedText(object) : super.getText(object);
                }
            };
        }

        return labelProvider;
    }

    /**
     * Get the list of available Stereotypes for the current selected Element, then we remove those which are already
     * applied.
     * 
     * @return List the list of Stereotypes that can be applied
     */
    protected List<Stereotype> getChoiceOfValues()
    {
        List<Stereotype> rChoiceStereotypeList = new ArrayList<Stereotype>();
        EList<Stereotype> applicableStereotypes = getApplicableStereotypes();

        for (EObject tEObject : eObjectList)
        {
            for (Stereotype tStereotype : applicableStereotypes)
            {
                if (!((Element) tEObject).isStereotypeApplied(tStereotype) && !rChoiceStereotypeList.contains(tStereotype))
                {
                    rChoiceStereotypeList.add(tStereotype);
                }
            }
        }
        return rChoiceStereotypeList;
    }

    /**
     * Get the list of stereotypes that are applied on the current selected Element
     * 
     * @return List the list of Stereotypes already applied
     */
    protected List<Stereotype> getAppliedStereotypes()
    {
        List<Stereotype> rChoiceStereotypeList = new ArrayList<Stereotype>();
        EList<Stereotype> applicableStereotypes = getApplicableStereotypes();

        for (EObject tEObject : eObjectList)
        {
            for (Stereotype tStereotype : applicableStereotypes)
            {
                if (((Element) tEObject).isStereotypeApplied(tStereotype) && !rChoiceStereotypeList.contains(tStereotype))
                {
                    rChoiceStereotypeList.add(tStereotype);
                }
            }
        }
        return rChoiceStereotypeList;
    }

    // private void refreshModeler()
    // {
    // for (EObject tEObject : eObjectList){
    // List<GraphElement> graphElts = Utils.getGraphElements(((Modeler)
    // getPart().getAdapter(Modeler.class)).getActiveDiagram(), tEObject, true);
    // for (GraphElement graphElt : graphElts)
    // {
    // Object editPart = ((GraphicalViewer) ((Modeler)
    // getPart().getAdapter(Modeler.class)).getAdapter(GraphicalViewer.class)).getEditPartRegistry().get(graphElt);
    // if (editPart instanceof EditPart)
    // {
    // ((EditPart) editPart).refresh();
    // }
    // }
    // }
    // }

    /**
     * return the union of applicable stereotype
     */
    private EList<Stereotype> getApplicableStereotypes()
    {

        if (eObjectList != null && eObjectList.size() > 0)
        {

            EList<Stereotype> rApplicableStereotypes = new BasicEList<Stereotype>();
            rApplicableStereotypes.addAll(((Element) eObjectList.get(0)).getApplicableStereotypes());

            for (EObject tEObject : eObjectList)
            {
                EList<Stereotype> applicableStereotypes = ((Element) tEObject).getApplicableStereotypes();
                rApplicableStereotypes.retainAll(applicableStereotypes);
            }
            return rApplicableStereotypes;
        }

        return new BasicEList<Stereotype>();
    }

}