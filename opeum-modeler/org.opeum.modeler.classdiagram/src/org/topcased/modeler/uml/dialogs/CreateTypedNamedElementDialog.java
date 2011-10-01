/*****************************************************************************
 * Copyright (c) 2010 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Maxime Leray (Atos Origin) maxime.leray@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/
package org.topcased.modeler.uml.dialogs;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.uml2.common.edit.provider.IItemQualifiedTextProvider;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.topcased.modeler.utils.Utils;

/**
 * The Class CreateTypedNamedElementDialog. The dialog contains a "name" field, a "type" combo and a "container"
 * browser. It allows the user to create a new instance of a subclass of the typeClass parameter.
 */
public class CreateTypedNamedElementDialog extends TitleAreaDialog
{
    private static String userSelectedContainer = "";

    private static String userSelectedResourceURI = "";

    private static EClass userSelectedClass = EcorePackage.Literals.ECLASS;

    /** The name text field. */
    private Text name;

    /** The type combo. */
    private ComboViewer type;

    /** The container viewer. */
    private ListViewer container;

    /** The browse btn. */
    private Button browseBtn;

    /** The combo input clazz. */
    private Collection<EClass> comboInputClazz;

    /** The list input parent container. */
    private EObject listInputParentContainer;

    /** The type class. */
    private final EClass typeClass;

    /** The containment feature. */
    private final EStructuralFeature containmentFeature;

    /** The result. */
    private EObject result;

    /**
     * Instantiates a new dialog.
     * 
     * @param parentShell the parent shell
     * @param eClass the EClass of the new element
     * @param containmentFeature the containment feature
     */
    public CreateTypedNamedElementDialog(Shell parentShell, EClass eClass, EStructuralFeature containmentFeature)
    {
        super(parentShell);
        this.typeClass = eClass;
        setComboInput(getTypes(eClass));
        this.containmentFeature = containmentFeature;
    }

    /**
     * Gets the subtypes of the given EClass.
     * 
     * @param eType the e type
     * 
     * @return the types
     */
    private Collection<EClass> getTypes(EClass eType)
    {
        SortedSet<EClass> clazz = new TreeSet<EClass>(new Comparator<EClass>()
        {

            public int compare(EClass eClass1, EClass eClass2)
            {
                return eClass1.getName().compareTo(eClass2.getName());
            }
        });
        for (EClassifier c : UMLPackage.eINSTANCE.getEClassifiers())
        {
            if (c instanceof EClass)
            {
                EClass eclass = (EClass) c;
                if (!eclass.isAbstract() && eType.isSuperTypeOf(eclass))
                {
                    clazz.add(eclass);
                }
            }
        }
        return clazz;
    }

    @Override
    protected Control createContents(Composite parent)
    {
        Control control = super.createContents(parent);
        setTitle("Create a new " + typeClass.getName());
        setMessage("Create a new " + typeClass.getName() + " and store it in the selected container.");
        return control;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent)
    {
        GridDataFactory labelGdf = GridDataFactory.swtDefaults();
        GridDataFactory controlGdf = GridDataFactory.fillDefaults();
        controlGdf.grab(true, false);
        // main composite
        Composite content = new Composite((Composite) super.createDialogArea(parent), SWT.NONE);
        GridLayout gridLayout = new GridLayout(2, false);
        gridLayout.marginWidth = 5;
        gridLayout.marginHeight = 5;
        gridLayout.verticalSpacing = 5;
        gridLayout.horizontalSpacing = 5;
        content.setLayout(gridLayout);
        content.setLayoutData(controlGdf.create());

        // name text field
        Label labelForName = new Label(content, SWT.NONE);
        labelForName.setLayoutData(labelGdf.create());
        labelForName.setText("Name:");
        name = new Text(content, SWT.BORDER);
        name.setLayoutData(controlGdf.create());
        name.setText(getDefaultName());

        // type combo box
        Label labelForType = new Label(content, SWT.NONE);
        labelForType.setLayoutData(labelGdf.create());
        labelForType.setText(typeClass.getName() + " type:");
        type = new ComboViewer(content, SWT.DROP_DOWN | SWT.READ_ONLY);
        type.setContentProvider(new ArrayContentProvider());
        type.setLabelProvider(new ClassLabelProvider(new UMLItemProviderAdapterFactory()));
        type.getCombo().setLayoutData(controlGdf.create());
        type.setInput(comboInputClazz);
        if (userSelectedClass != null && typeClass.isSuperTypeOf(userSelectedClass) && !typeClass.equals(userSelectedClass))
        {
            type.setSelection(new StructuredSelection(userSelectedClass));
        }
        else
        {
            userSelectedClass = EcorePackage.Literals.ECLASS;
        }

        // container text field
        Label label = new Label(content, SWT.NONE);
        label.setLayoutData(labelGdf.create());
        label.setText("Container:");
        Composite containerComposite = new Composite(content, SWT.NONE);
        GridLayout containerLayout = new GridLayout(2, false);
        containerLayout.marginWidth = 0;
        containerLayout.marginHeight = 0;
        containerLayout.verticalSpacing = 0;
        containerLayout.horizontalSpacing = 5;
        containerComposite.setLayout(containerLayout);
        containerComposite.setLayoutData(controlGdf.create());
        container = new ListViewer(containerComposite, SWT.BORDER | SWT.READ_ONLY);
        container.setContentProvider(new ArrayContentProvider());
        container.setLabelProvider(new ClassLabelProvider(new UMLItemProviderAdapterFactory()));
        container.getList().setLayoutData(controlGdf.create());
        container.setInput(new Object[] {listInputParentContainer});
        browseBtn = new Button(containerComposite, SWT.PUSH);
        browseBtn.setText("...");
        browseBtn.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                handleBrowse();
            }
        });

        return content;
    }

    /**
     * Handle browse action. It opens a new dialog to choose the package.
     */
    private void handleBrowse()
    {
        AdapterFactory factory = Utils.getCurrentModeler().getAdapterFactory();
        AdapterFactoryLabelProvider labelProvider = new AdapterFactoryLabelProvider(factory);
        AdapterFactoryContentProvider contentProvider = new AdapterFactoryContentProvider(factory);
        final ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(getParentShell(), labelProvider, contentProvider);
        dialog.addFilter(new ViewerFilter()
        {
            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element)
            {
                return element instanceof Package;
            }
        });
        dialog.setTitle("Select a container for the new " + typeClass.getName());
        dialog.setAllowMultiple(false);
        dialog.setInput(getResource());
        dialog.setInitialSelection(listInputParentContainer);
        if (dialog.open() == Dialog.OK && dialog.getFirstResult() instanceof Package)
        {
            Package chosenPackage = (Package) dialog.getFirstResult();
            if (chosenPackage.eResource() != null)
            {
                userSelectedResourceURI = chosenPackage.eResource().getURI().toString();
                userSelectedContainer = chosenPackage.eResource().getURIFragment(chosenPackage);
            }
            listInputParentContainer = chosenPackage;
            container.setInput(new Object[] {listInputParentContainer});
        }
    }

    private Resource getResource()
    {
        Resource result = null;
        EObject element = listInputParentContainer;
        if (element != null)
        {
            while (element.eContainer() != null)
            {
                element = element.eContainer();
            }
            result = element.eResource();
        }
        return result;
    }

    /**
     * Sets the combo input.
     * 
     * @param inputClazz the new combo input
     */
    public void setComboInput(Collection<EClass> inputClazz)
    {
        this.comboInputClazz = inputClazz;
    }

    /**
     * Sets the browser input.
     * 
     * @param parentContainer the new browser input
     */
    public void setBrowserInput(EObject parentContainer)
    {
        Resource modelResource = parentContainer.eResource();
        EObject targetPackage = null;
        if (modelResource != null && modelResource.getURI().toString().equals(userSelectedResourceURI))
        {
            targetPackage = modelResource.getEObject(userSelectedContainer);
        }
        if (targetPackage == null)
        {
            this.listInputParentContainer = parentContainer;
        }
        else
        {
            this.listInputParentContainer = targetPackage;
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed()
    {
        Package thePackage = (Package) listInputParentContainer;
        String objectName = name.getText();
        // control that the name has been filled...
        if (objectName.length() == 0)
        {
            MessageDialog.openWarning(getShell(), "Empty name", "Name field cannot be empty.");
            return;
        }
        else
        {
            // ... and that it is not in use
            EList<PackageableElement> list = thePackage.getPackagedElements();
            for (PackageableElement element : list)
            {
                if (objectName.equals(element.getName()))
                {
                    MessageDialog.openWarning(getShell(), "Used name", "The name is already used for the selected package.");
                    return;
                }
            }
        }
        EClass targetClass = typeClass;
        if (type.getSelection() instanceof StructuredSelection)
        {
            StructuredSelection selec = (StructuredSelection) type.getSelection();
            if (!selec.isEmpty())
            {
                targetClass = (EClass) selec.getFirstElement();
            }
            else
            {
                MessageDialog.openWarning(getShell(), "Empty type", "Select a type.");
                return;
            }
        }
        EObject anObject = EcoreUtil.create(targetClass);
        userSelectedClass = targetClass;
        if (anObject instanceof NamedElement)
        {
            NamedElement aNamedObject = (NamedElement) anObject;
            aNamedObject.setName(objectName);
        }
        if (containmentFeature.isMany())
        {
            Collection collec = (Collection) thePackage.eGet(containmentFeature);
            collec.add(anObject);
        }
        else
        {
            thePackage.eSet(containmentFeature, anObject);
        }
        setResult(anObject);
        super.okPressed();
    }

    /**
     * Gets the default name.
     * 
     * @return the default name
     */
    private String getDefaultName()
    {
        String defaultName = typeClass.getName();
        boolean nameAlreadyUsed = false;
        Package thePackage = (Package) listInputParentContainer;
        for (PackageableElement element : thePackage.getPackagedElements())
        {
            if (defaultName.equals(element.getName()))
            {
                nameAlreadyUsed = true;
                break;
            }
        }
        if (nameAlreadyUsed)
        {
            Pattern pattern = Pattern.compile(defaultName + "((\\d)*)");
            SortedSet<String> matchingNames = new TreeSet<String>();
            for (PackageableElement element : thePackage.getPackagedElements())
            {
                if (element.getName() != null)
                {
                    if (pattern.matcher(element.getName()).matches())
                    {
                        matchingNames.add(element.getName());
                    }
                }
            }
            String lastName = matchingNames.last();
            Matcher matcher = pattern.matcher(lastName);
            matcher.matches();
            String suffix = "1";
            if (matcher.groupCount() > 1)
            {
                try
                {
                    suffix = String.valueOf(Integer.parseInt(matcher.group(1)) + 1);
                }
                catch (NumberFormatException e)
                {
                    // do nothing
                }
            }
            defaultName += suffix;
        }
        return defaultName;
    }

    /**
     * The Class ClassLabelProvider.
     */
    private class ClassLabelProvider extends AdapterFactoryLabelProvider
    {

        /**
         * Instantiates a new class label provider.
         * 
         * @param adapterFactory the adapter factory
         */
        public ClassLabelProvider(AdapterFactory adapterFactory)
        {
            super(adapterFactory);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getText(java.lang.Object)
         */
        @Override
        public String getText(Object element)
        {
            if (element instanceof EClass)
            {
                EClass eclass = (EClass) element;
                return eclass.getName();
            }
            else
            {
                IItemQualifiedTextProvider itemQualifiedTextProvider = (IItemQualifiedTextProvider) adapterFactory.adapt(element, IItemQualifiedTextProvider.class);
                return itemQualifiedTextProvider != null ? itemQualifiedTextProvider.getQualifiedText(element) : super.getText(element);
            }
        }

    }

    /**
     * Gets the result.
     * 
     * @return the result
     */
    public EObject getResult()
    {
        return result;
    }

    /**
     * Sets the result.
     * 
     * @param result the new result
     */
    private void setResult(EObject result)
    {
        this.result = result;
    }

}
