/******************************************************************************************
 * Copyright (c) 2007 Communication & System (C-S).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Sebastien Gabel (CS), Tristan Fayre (Atos Origin Integration) 
 *    
 *******************************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections.operation.behavior;

import java.util.Arrays;
import java.util.LinkedList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.editor.MixedEditDomain;
import org.topcased.modeler.utils.Utils;

/**
 * This Composite contains the necessary widgets for editing a OpaqueBehavior in the UML editor it references languages
 * defined via extension point : org.topcased.modeler.uml.operationBodyLanguage Creation 10 april 2007
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 * @author <a href="mailto:tristan.faure@atosorigin.com">Tristan FAURE</a>
 */
public class OperationBehaviorComposite extends Composite implements CallBackElement
{
    /** The extenstion point referencing languages */
    private static String extensionPointID = "org.topcased.modeler.uml.operationBodyLanguage";

    /** The OpaqueBehavior that is currently edited */
    private OpaqueBehavior opaqueBehavior;

    /** The Operation that is currently edited */
    private Operation operation;

    private MixedEditDomain mixedEditDomain;

    /** The widgetFactory to use to create the widgets */
    private TabbedPropertySheetWidgetFactory widgetFactory;

    /** widgets */
    private CCombo languageCombo;

    private Button removeBtn;

    private Button addBtn;

    private LinkedList<Language> languages = new LinkedList<Language>();

    private Composite editComposite = null;

    private Composite top;

    private Composite btnsComposite;

    /**
     * context
     */
    private Language currentLanguage = null;

    private IConfigurationElement[] configElements;

    /**
     * Enumeration of available languages.
     */
    final class Language
    {

        private String name;

        private BodyCompositeFactory factory = null;

        /**
         * Constructor
         * 
         * @param languageName The language name
         */
        private Language(String languageName, BodyCompositeFactory afactory)
        {
            name = languageName;
            factory = afactory;
        }

        public BodyCompositeFactory getFactory()
        {
            return factory;
        }

        /**
         * Getter for the language name
         * 
         * @return name of the language
         */
        public String getName()
        {
            return name;
        }
    }

    /**
     * The constructor
     * 
     * @param parent a widget which will be the parent of the new instance (cannot be null)
     * @param style the style of widget to construct
     * @param wFactory the widgetFactory to use to create the different widgets
     */
    OperationBehaviorComposite(Composite parent, int style, TabbedPropertySheetWidgetFactory wFactory)
    {
        super(parent, style);
        initLanguages();
        widgetFactory = wFactory;
        setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        final GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        setLayout(layout);
        widgetFactory.adapt(this);
        createContents(this);
    }

    /**
     * Init the languages from extension point
     */
    private void initLanguages()
    {
        languages.clear();
        if (configElements == null)
        {
            configElements = Platform.getExtensionRegistry().getConfigurationElementsFor(extensionPointID);
        }
        for (IConfigurationElement e : configElements)
        {
            String name = e.getAttribute("name");
            try
            {
                BodyCompositeFactory factory = (BodyCompositeFactory) e.createExecutableExtension("bodyCompositeFactory");
                Language l = new Language(name, factory);
                languages.add(l);
            }
            catch (CoreException e1)
            {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Creates the Composite composed of a set of widgets necessary to edit an Opaquebehavior
     * 
     * @param parent the parent Composite
     */
    protected void createContents(Composite parent)
    {
        top = parent;
        createComboComposite(top);

        createEditionAreaComposite(parent, null);

        createButtonsComposite(top);
    }

    /**
     * Set the Operation and update the content of the table
     * 
     * @param operation the Operation model object
     */
    public void setOperation(Operation operation)
    {
        this.operation = operation;
        manageCombo();
        assignContext();
        setOpaqueBehavior();
        checkParameters();
    }

    /**
     * Gets the current operation
     */
    public Operation getOperation()
    {
        return operation;
    }

    protected void checkParameters()
    {
        if (this.operation != null && opaqueBehavior != null)
        {
            EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(opaqueBehavior);
            if (domain.isReadOnly(opaqueBehavior.eResource()))
            {
                return;
            }
            boolean change = operation.getOwnedParameters().size() != opaqueBehavior.getOwnedParameters().size();
            if (!change)
            {
                int i = 0;
                for (Parameter p : operation.getOwnedParameters())
                {
                    // just check attributes configurable from property view
                    Parameter parameter2 = opaqueBehavior.getOwnedParameters().get(i);
                    change |= !(equals(parameter2.getName(), p.getName()) && equals(parameter2.getType(), p.getType()) && parameter2.getLower() == p.getLower()
                            && parameter2.getUpper() == p.getUpper() && equals(parameter2.getVisibility(), p.getVisibility()) && equals(parameter2.getDirection(), p.getDirection()) && equals(
                            parameter2.getEffect(), p.getEffect()));
                    i++;
                }
            }
            if (change)
            {
                if (MessageDialog.openQuestion(getShell(), "Parameters conflict",
                        "The operation and the behavior linked does not have compatible parameters.\nDo you want to override the parameters of your behavior by the parameters of the operation ?"))
                {
                    CompoundCommand com = new CompoundCommand("Override parameters");
                    com.append(new RemoveCommand(mixedEditDomain.getEMFEditingDomain(), opaqueBehavior, UMLPackage.Literals.BEHAVIOR__OWNED_PARAMETER, opaqueBehavior.getOwnedParameters()));
                    for (Parameter p : operation.getOwnedParameters())
                    {
                        Parameter newParameter = copy(p);
                        // creates EAnnotation if needed
                        com.append(new AddCommand(mixedEditDomain.getEMFEditingDomain(), opaqueBehavior, UMLPackage.Literals.BEHAVIOR__OWNED_PARAMETER, newParameter));
                    }
                    mixedEditDomain.getEMFEditingDomain().getCommandStack().execute(com);
                }
            }
        }
    }

    private boolean equals(Object o1, Object o2)
    {
        if (o1 == o2)
        {
            return true;
        }
        else
        {
            if (o1 != null)
            {
                return o1.equals(o2);
            }
            else
            {
                return o2 == null;
            }
        }
    }

    private void assignContext()
    {
        if (operation != null && currentLanguage != null)
        {
            currentLanguage.getFactory().setOperation(operation);
        }
    }

    /**
     * Sets an OpaqueBehavior when switching from an operation to another one (and vice-versa)
     */
    private void setOpaqueBehavior()
    {
        opaqueBehavior = getOpaqueBehavior();
        manageCombo();
        updateWidgets();
    }

    /**
     * Allows to update fields and buttons.
     */
    private void updateWidgets()
    {
        updateComposite();
        if (opaqueBehavior != null && opaqueBehavior.getLanguages().contains(languageCombo.getText()))
        {
            int index = opaqueBehavior.getLanguages().indexOf(languageCombo.getText());
            if (currentLanguage != null && !opaqueBehavior.getBodies().isEmpty())
            {
                currentLanguage.getFactory().setCompositeValue(opaqueBehavior.getBodies().get(index));
                canRemove();
            }
            else
            {
                canNothing();
            }
        }
        else
        {
            if (currentLanguage != null)
            {
                currentLanguage.getFactory().setCompositeValue("");
                canAdd();
            }
            else
            {
                canNothing();
            }
        }
    }

    /**
     * Update the composite containing the textarea
     */
    private void updateComposite()
    {
        if (editComposite != null)
        {
            for (Language l : languages)
            {
                if (l.getName().equals(languageCombo.getText()))
                {
                    currentLanguage = l;
                    for (Control c : editComposite.getChildren())
                    {
                        c.dispose();
                    }
                    Composite c = l.getFactory().createComposite(editComposite, widgetFactory);
                    l.getFactory().registerCallBackForModification(this);
                    assignContext();
                    c.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
                    Composite parent = c;
                    do
                    {
                        parent.layout(true);
                        parent.redraw();
                        parent.update();
                        parent = parent.getParent();
                    }
                    while (parent != null);
                }
            }
        }
    }

    /**
     * Sets the MixedEditDomain
     * 
     * @param editDomain the MixedEditDomain
     */
    public void setMixedEditDomain(MixedEditDomain editDomain)
    {
        mixedEditDomain = editDomain;
    }

    /**
     * Creates the last composite containing the Add button and the Remove button.
     * 
     * @param parent The parent composite
     */
    private void createButtonsComposite(Composite parent)
    {
        final GridLayout layout = new GridLayout(2, false);
        layout.marginLeft = 0;
        layout.marginHeight = 0;

        btnsComposite = widgetFactory.createComposite(parent, SWT.NONE);
        btnsComposite.setLayout(layout);
        btnsComposite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));

        addBtn = widgetFactory.createButton(btnsComposite, "Add", SWT.PUSH);
        addBtn.setLayoutData(new GridData(75, SWT.DEFAULT));
        addBtn.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                addCodeAndLanguage();
            }
        });

        removeBtn = widgetFactory.createButton(btnsComposite, "Remove", SWT.PUSH);
        removeBtn.setLayoutData(new GridData(75, SWT.DEFAULT));
        removeBtn.setEnabled(false);
        removeBtn.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                removeCodeAndLanguage();
            }
        });
    }

    /**
     * Creates the first line containing a Label and a Combo Box.
     * 
     * @param parent The parent composite
     */
    private void createComboComposite(Composite parent)
    {
        final GridLayout layout = new GridLayout(2, false);
        layout.marginLeft = 0;

        final Composite comboComposite = widgetFactory.createComposite(parent, SWT.NONE);
        comboComposite.setLayout(layout);
        comboComposite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        // just if there is no languages
        final Label comboLabel = widgetFactory.createLabel(comboComposite, "Language : ", SWT.NONE);
        comboLabel.setLayoutData(new GridData(100, SWT.DEFAULT));

        languageCombo = widgetFactory.createCCombo(comboComposite, SWT.NONE);
        languageCombo.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        languageCombo.setEditable(true);
        languageCombo.setToolTipText("Press Enter to validate you custom language");
        manageCombo();
        languageCombo.select(0);
        languageCombo.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyReleased(KeyEvent e)
            {
                if (e.keyCode == SWT.CR)
                {
                    manageCombo();
                    updateWidgets();
                }
            }
        });
        languageCombo.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                updateWidgets();
            }
        });
    }

    private void manageCombo()
    {
        boolean found = false;
        initLanguages();
        String input = languageCombo.getText();
        languageCombo.removeAll();
        for (Language current : languages)
        {
            found = found || current.getName().equals(input);
            languageCombo.add(current.getName());
        }
        if (opaqueBehavior != null)
        {
            for (String s : opaqueBehavior.getLanguages())
            {
                if (s.length() > 0 && !Arrays.asList(languageCombo.getItems()).contains(s))
                {
                    found = found || s.equals(input);
                    languageCombo.add(s);
                    languages.add(new Language(s, new DefaultBodyCompositeFactory()));
                }
            }
        }
        if (!found && input.length() > 0)
        {
            languages.add(new Language(input, new DefaultBodyCompositeFactory()));
            languageCombo.add(input);
            languageCombo.select(languageCombo.getItemCount() - 1);
        }
        else if (input.length() > 0)
        {
            languageCombo.select(Arrays.asList(languageCombo.getItems()).indexOf(input));
        }
        else
        {
            languageCombo.select(0);
        }
    }

    /**
     * Enables to add a Language and the associated code if necessary.<br>
     * First of all, the OpaqueBehavior is created if it does not exist. Then the language is added to the Language
     * list. The code in the Body list.
     */
    private void addCodeAndLanguage()
    {
        if (!opaqueBehaviorAlreadyExists())
        {
            createOpaqueBehavior();
        }

        // adds the existing code at the specific index
        opaqueBehavior.getBodies().add(currentLanguage.getFactory().getCompositeValue());
        opaqueBehavior.getLanguages().add(languageCombo.getText());
        canRemove();
        addOpaqueBehavior();
    }

    /**
     * Removes an existing code and language stored inside an OpaqueBehavior Object.
     */
    private void removeCodeAndLanguage()
    {
        // remove the existing code at the specific index
        removeCodeAndLanguage(languageCombo.getText());

        // fields are reseted
        currentLanguage.getFactory().setCompositeValue("");
        canAdd();

        if (opaqueBehavior.getBodies().isEmpty() && opaqueBehavior.getLanguages().isEmpty())
        {
            // opaque action is removed if empty (no more body, no more langugae)
            removeOpaqueBehavior();
        }
    }

    /**
     * Creates the code area where the user can enter its own code related to a given language.
     * 
     * @param parent The parent composite
     */
    private void createEditionAreaComposite(Composite parent, Language l)
    {
        Composite topEdit = widgetFactory.createComposite(parent, SWT.NONE);
        topEdit.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        topEdit.setLayout(new GridLayout(1, true));

        widgetFactory.createLabel(topEdit, "Body : ", SWT.NONE);
        editComposite = widgetFactory.createComposite(topEdit);
        editComposite.setLayout(new GridLayout(1, false));
        editComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        widgetFactory.createLabel(editComposite, "Please fill a language in the combo and press enter.");
    }

    /**
     * Indicates if an OpaqueBehavior already exists for this operation.
     * 
     * @return <code>true</code> if it exists, <code>false</code> otherwise.
     */
    private boolean opaqueBehaviorAlreadyExists()
    {
        if (operation != null)
        {
            return operation.getMethod(null, false, UMLPackage.Literals.OPAQUE_BEHAVIOR) != null;
        }
        return false;
    }

    /**
     * Gets the OpaqueBehavior object associated to the current Operation<br>
     * If the current operation does not exist, the method returns <code>null</code>.
     * 
     * @return an OpaqueBehavior object execpt if the current operation has no Behavior at this time.
     */
    protected OpaqueBehavior getOpaqueBehavior()
    {
        OpaqueBehavior behavior = null;
        if (operation != null && !operation.getMethods().isEmpty())
        {
            behavior = (OpaqueBehavior) operation.getMethod(operation.getName(), false, UMLPackage.Literals.OPAQUE_BEHAVIOR);
            // if the behavior with the operation name is not found then the first of the list is returned
            if (behavior == null)
            {
                behavior = (OpaqueBehavior) operation.getMethod(null, false, UMLPackage.Literals.OPAQUE_BEHAVIOR);
            }
        }
        return behavior;
    }

    /**
     * Creates a new OpaqueBehavior related to the current Operation Object
     */
    private void createOpaqueBehavior()
    {
        opaqueBehavior = UMLFactory.eINSTANCE.createOpaqueBehavior();

        // Sets the default name : which of the operation
        opaqueBehavior.setName(operation.getName());

        // Sets the relation with the current Operation
        opaqueBehavior.setSpecification(operation);

        // Set parameters corresponding to the operation
        for (Parameter p : operation.getOwnedParameters())
        {
            Parameter copy = copy(p);
            opaqueBehavior.getOwnedParameters().add(copy);
        }
    }

    private Parameter copy(Parameter p)
    {
        Parameter par = EcoreUtil.copy(p);
        // creates EAnnotation if needed
        Utils.createAuthorEAnnotation(par);
        return par;
    }

    /**
     * Adds an new OpaqueBehavior to the model.
     */
    private void addOpaqueBehavior()
    {
        // Object is added to the model
        AbstractCommand cmd = (AbstractCommand) AddCommand.create(mixedEditDomain.getEMFEditingDomain(), operation.getClass_(), UMLPackage.eINSTANCE.getBehavioredClassifier_OwnedBehavior(),
                opaqueBehavior);
        cmd.setLabel("Add Implementation");
        mixedEditDomain.getEMFEditingDomain().getCommandStack().execute(cmd);
    }

    /**
     * Removes an existing OpaqueBehavior attached to an operation.
     */
    private void removeOpaqueBehavior()
    {
        // Object is removed from the model
        AbstractCommand cmd = (AbstractCommand) RemoveCommand.create(mixedEditDomain.getEMFEditingDomain(), operation.getClass_(), UMLPackage.eINSTANCE.getBehavioredClassifier_OwnedBehavior(),
                opaqueBehavior);
        cmd.setLabel("Remove Implementation");
        mixedEditDomain.getEMFEditingDomain().getCommandStack().execute(cmd);
    }

    /**
     * Updates an existing OpaqueBehavior attached to an operation.
     * 
     * @param language The language to remove.
     * @param code The body to remove
     */
    private void removeCodeAndLanguage(String language)
    {
        int index = opaqueBehavior.getLanguages().indexOf(languageCombo.getText());
        String body = opaqueBehavior.getBodies().get(index);

        CompoundCommand cc = new CompoundCommand("Remove " + language + " code");
        // Object is removed from the model
        cc.append(RemoveCommand.create(mixedEditDomain.getEMFEditingDomain(), opaqueBehavior, UMLPackage.eINSTANCE.getOpaqueBehavior_Language(), language));
        cc.append(RemoveCommand.create(mixedEditDomain.getEMFEditingDomain(), opaqueBehavior, UMLPackage.eINSTANCE.getOpaqueBehavior_Body(), body));
        mixedEditDomain.getEMFEditingDomain().getCommandStack().execute(cc);
    }

    /**
     * Updates an existing OpaqueBehavior attached to an operation.
     * 
     * @param language The language to remove.
     * @param code The body to remove
     */
    private void updateCodeForLanguage(int index)
    {
        String body = currentLanguage.getFactory().getCompositeValue();

        SetCommand setCmd = (SetCommand) SetCommand.create(mixedEditDomain.getEMFEditingDomain(), opaqueBehavior, UMLPackage.eINSTANCE.getOpaqueBehavior_Body(), body, index);
        setCmd.setLabel("Update " + languageCombo.getText() + " code");
        mixedEditDomain.getEMFEditingDomain().getCommandStack().execute(setCmd);
    }

    /**
     * Implements the text area listener aims to enter information about language.
     */
    public void hookListeners()
    {
        // The OpaqueBehavior object must already exist
        if (opaqueBehaviorAlreadyExists())
        {
            // The current language must at least being edited in past
            if (opaqueBehavior.getLanguages().contains(languageCombo.getText()))
            {
                int index = opaqueBehavior.getLanguages().indexOf(languageCombo.getText());

                // The content of the text area must be different from the content store inside the object
                if (currentLanguage != null && !opaqueBehavior.getBodies().get(index).equals(currentLanguage.getFactory().getCompositeValue()))
                {
                    updateCodeForLanguage(index);
                }
            }
        }
    }

    /**
     * Handles the buttons Add and Remove in case where it is nothing to do.
     */
    private void canNothing()
    {
        addBtn.setEnabled(false);
        removeBtn.setEnabled(false);
    }

    /**
     * Handles the buttons Add and Remove in case where it is possible to add code and language.
     */
    private void canAdd()
    {
        addBtn.setEnabled(true);
        removeBtn.setEnabled(false);
    }

    /**
     * Handles the buttons Add and Remove in case where it is possible to remove code and language.
     */
    private void canRemove()
    {
        addBtn.setEnabled(false);
        removeBtn.setEnabled(true);
    }

    @Override
    public void setEnabled(boolean enabled)
    {
        super.setEnabled(enabled);
        addBtn.setEnabled(enabled);
        removeBtn.setEnabled(enabled);
        languageCombo.setEnabled(enabled);
    }
}
