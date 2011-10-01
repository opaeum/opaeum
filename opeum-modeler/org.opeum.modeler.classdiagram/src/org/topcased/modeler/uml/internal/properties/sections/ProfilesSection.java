/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jacques Lescot (Anyware Technologies) - initial API and
 * implementation
 ******************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
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
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.ProfileApplication;
import org.eclipse.uml2.uml.UMLPlugin;
import org.eclipse.uml2.uml.edit.providers.ProfileItemProvider;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.topcased.modeler.commands.RecordingChangeCommand;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.uml.actions.ApplyProfileAction;
import org.topcased.tabbedproperties.utils.ObjectAdapter;

/**
 * The section used to Apply/Unapply a Profile on a given Element
 * 
 * Creation 21 nov. 06
 * 
 * @author <a href="mailto:jacques.lescot@anyware-tech.com">Jacques LESCOT</a>
 */
public class ProfilesSection extends AbstractPropertySection
{
    /**
     * The current selected object or the first object in the selection when multiple objects are selected.
     */
    private EObject eObject;

    private ILabelProvider availableProfilesLabelProvider;

    private ILabelProvider appliedProfilesLabelProvider;

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
                final List<Profile> profilesToApply = new ArrayList<Profile>();
                List<Profile> untypedPropertiesProfiles = new ArrayList<Profile>();
                IStructuredSelection selection = (IStructuredSelection) availableElementsTableViewer.getSelection();
                for (Iterator<Profile> i = selection.iterator(); i.hasNext();)
                {
                    Profile profile = i.next();

                    if (ApplyProfileAction.hasUntypedAttributes(profile))
                    {
	                    if (!((ItemProvider) selectedElementsTableViewer.getInput()).getChildren().contains(profile))
	                    {
	                        ((ItemProvider) selectedElementsTableViewer.getInput()).getChildren().add(profile);
	                    }
	                    ((ItemProvider) availableElementsTableViewer.getInput()).getChildren().remove(profile);
	                    profilesToApply.add(profile);
                    }
                    else
                    {
                    	untypedPropertiesProfiles.add(profile);
                    }
                }

                ApplyProfileAction.openInformationDialog(untypedPropertiesProfiles);

                Command cmd = new RecordingChangeCommand(eObject.eResource())
                {
                    /** @see org.topcased.modeler.commands.RecordingChangeCommand#doExecute() */
                    protected void doExecute()
                    {
                        for (Iterator< ? > itNewElements = profilesToApply.iterator(); itNewElements.hasNext();)
                        {
                            ((Package) eObject).applyProfile((Profile) itNewElements.next());
                        }
                    }

                    /**
                     * This command is not Undoable. The Unapply Profile action should be used instead.
                     * 
                     * @see org.topcased.modeler.commands.RecordingChangeCommand#canUndo()
                     */
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
        public void widgetSelected(SelectionEvent event)
        {
            IStructuredSelection selection = (IStructuredSelection) selectedElementsTableViewer.getSelection();
            Object firstValue = null;

            final List<Profile> profilesToUnapply = new ArrayList<Profile>();
            for (Iterator<Profile> i = selection.iterator(); i.hasNext();)
            {
                Profile profile = i.next();
                if (firstValue == null)
                {
                    firstValue = profile;
                }

                if (!((ItemProvider) availableElementsTableViewer.getInput()).getChildren().contains(profile))
                {
                    ((ItemProvider) availableElementsTableViewer.getInput()).getChildren().add(profile);
                }
                ((ItemProvider) selectedElementsTableViewer.getInput()).getChildren().remove(profile);

                profilesToUnapply.add(profile);
            }

            Command cmd = new RecordingChangeCommand(eObject.eResource())
            {
                /** @see org.topcased.modeler.commands.RecordingChangeCommand#doExecute() */
                protected void doExecute()
                {
                    for (Iterator< ? > itOldElements = profilesToUnapply.iterator(); itOldElements.hasNext();)
                    {
                        ((Package) eObject).unapplyProfile((Profile) itOldElements.next());
                    }
                }

                /**
                 * This command is not Undoable. The Unapply Profile action should be used instead.
                 * 
                 * @see org.topcased.modeler.commands.RecordingChangeCommand#canUndo()
                 */
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

        Label choiceLabel = getWidgetFactory().createLabel(choiceComposite, "Available Profiles", SWT.NONE);
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

        Label featureLabel = getWidgetFactory().createLabel(featureComposite, "Applied Profiles", SWT.NONE);
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
        Object ssel = ((IStructuredSelection) selection).getFirstElement();

        eObject = ObjectAdapter.adaptObject(ssel);

        AdapterFactory adapterFactory = new ComposedAdapterFactory(Collections.EMPTY_LIST);
        availableElementsTableViewer.setLabelProvider(getAvailableProfilesLabelProvider());
        availableElementsTableViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
        availableElementsTableViewer.setInput(new ItemProvider(adapterFactory, getChoiceOfValues()));

        AdapterFactory adapterFactory2 = new ComposedAdapterFactory(Collections.EMPTY_LIST);
        selectedElementsTableViewer.setLabelProvider(getAppliedProfilesLabelProvider());
        selectedElementsTableViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory2));
        selectedElementsTableViewer.setInput(new ItemProvider(adapterFactory2, getAppliedProfiles()));
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
    protected ILabelProvider getAvailableProfilesLabelProvider()
    {
        if (availableProfilesLabelProvider == null)
        {
            availableProfilesLabelProvider = new AdapterFactoryLabelProvider(((Modeler) getPart().getAdapter(Modeler.class)).getAdapterFactory())
            {
                public String getColumnText(Object object, int columnIndex)
                {
                    IItemQualifiedTextProvider itemQualifiedTextProvider = (IItemQualifiedTextProvider) adapterFactory.adapt(object, IItemQualifiedTextProvider.class);
                    if (itemQualifiedTextProvider instanceof ProfileItemProvider)
                    {
                        // The profile to be considered
                        Profile profile = (Profile) object;// ((ProfileItemProvider)
                        // itemQualifiedTextProvider).getTarget();
                        // Retrieve the Profile Application on this profile in the current Package
                        ProfileApplication profApp = ((Package) eObject).getProfileApplication(profile);
                        // Check whether the profile has a newer version of the already applied profile
                        if (profApp != null && profApp.getAppliedDefinition() != profile.getDefinition())
                        {
                            return itemQualifiedTextProvider != null ? itemQualifiedTextProvider.getQualifiedText(object) + " (new version)" : super.getColumnText(object, columnIndex);
                        }
                    }
                    return itemQualifiedTextProvider != null ? itemQualifiedTextProvider.getQualifiedText(object) : super.getColumnText(object, columnIndex);
                }

                public String getText(Object object)
                {
                    IItemQualifiedTextProvider itemQualifiedTextProvider = (IItemQualifiedTextProvider) adapterFactory.adapt(object, IItemQualifiedTextProvider.class);
                    return itemQualifiedTextProvider != null ? itemQualifiedTextProvider.getQualifiedText(object) : super.getText(object);
                }
            };
        }

        return availableProfilesLabelProvider;
    }

    /**
     * Get the LabelProvider to use to display the Object
     * 
     * @return ILabelProvider
     */
    protected ILabelProvider getAppliedProfilesLabelProvider()
    {
        if (appliedProfilesLabelProvider == null)
        {
            appliedProfilesLabelProvider = new AdapterFactoryLabelProvider(((Modeler) getPart().getAdapter(Modeler.class)).getAdapterFactory())
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

        return appliedProfilesLabelProvider;
    }

    /**
     * Get the list of available Profiles for the current selected Element, then we remove those which are already
     * applied.
     * 
     * @return List the list of Profiles that can be applied
     */
    protected List<Profile> getChoiceOfValues()
    {
        final List<Profile> choiceOfValues = new ArrayList<Profile>();

        Resource eResource = eObject.eResource();
        ResourceSet resourceSet = eResource == null ? null : eResource.getResourceSet();

        if (resourceSet != null)
        {

            try
            {
                // fix bug #2422
                for (URI profileURI : UMLPlugin.getEPackageNsURIToProfileLocationMap().values())
                {
                    resourceSet.getResource(profileURI.trimFragment(), true);
                }
                // BUG #2422
                // resourceSet.getResource(URI.createURI(UMLResource.STANDARD_PROFILE_URI), true);
                //
                // resourceSet.getResource(URI.createURI(UMLResource.ECORE_PROFILE_URI), true);
            }
            catch (Exception e)
            {
                // ignore
            }

            for (Resource resource : resourceSet.getResources())
            {

                Iterator<EObject> allContents = resource.getAllContents();

                while (allContents.hasNext())
                {

                    new UMLSwitch<Profile>()
                    {

                        public Profile caseProfile(Profile profile)
                        {

                            if (profile.isDefined())
                            {
                                ProfileApplication profileApplication = ((Package) eObject).getProfileApplication(profile);

                                if (profileApplication == null || profileApplication.getAppliedDefinition() != profile.getDefinition())
                                {

                                    choiceOfValues.add(profile);
                                }
                            }

                            return profile;
                        }
                    }.doSwitch(allContents.next());
                }
            }
        }
        return choiceOfValues;
    }

    @Override
    public void refresh()
    {
        super.refresh();
        boolean isEnabled = true;
        if (eObject != null)
        {
            EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(eObject);
            if (domain != null)
            {
                isEnabled = !domain.isReadOnly(eObject.eResource());
                addButton.setEnabled(isEnabled);
                availableElementsTableViewer.getControl().setEnabled(isEnabled);
                downButton.setEnabled(isEnabled);
                removeButton.setEnabled(isEnabled);
                selectedElementsTableViewer.getControl().setEnabled(isEnabled);
                upButton.setEnabled(isEnabled);
            }
        }
    }

    /**
     * Get the list of profiles that are applied on the current selected Element
     * 
     * @return List the list of Profiles already applied
     */
    protected List<Profile> getAppliedProfiles()
    {
        List<Profile> choiceOfValues = new ArrayList<Profile>(((Package) eObject).getAppliedProfiles());
        return choiceOfValues;
    }

}