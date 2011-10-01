/*******************************************************************************
 * 
 * Copyright AIRBUS FRANCE, 2005. All rights reserved.
 * 
 * This document and all information contained herein is the sole property of
 * AIRBUS FRANCE. No intellectual property rights are granted by the delivery of
 * this document or the disclosure of its content.
 * 
 ******************************************************************************/
package org.topcased.modeler.uml.internal.customchildmenu;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.topcased.modeler.actions.CreateChildAction;
import org.topcased.modeler.editor.MixedEditDomain;
import org.topcased.modeler.editor.outline.ICreateChildMenu;

/**
 * A customized 'Create child' menu manager. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class CreateChildMenu extends MenuManager implements ICreateChildMenu
{
    /**
     * @generated
     */
    private static final String UNAPPLIED_ACTIONS_GROUP = "unappliedActions";

    /**
     * @generated
     */
    private MixedEditDomain domain;

    /**
     * @generated
     */
    private EObject selectedObject;

    /**
     * @generated
     */
    private Collection<?> descriptors;

    /**
     * The 'Classifier' menu manager.
     * 
     * @generated
     */
    private MenuManager classifierMenu;

    /**
     * The 'Constraint' menu manager.
     * 
     * @generated
     */
    private MenuManager constraintMenu;

    /**
     * The 'Trigger' menu manager.
     * 
     * @generated
     */
    private MenuManager triggerMenu;

    /**
     * The 'Behavior' menu manager.
     * 
     * @generated
     */
    private MenuManager behaviorMenu;

    /**
     * The 'Dependency' menu manager.
     * 
     * @generated
     */
    private MenuManager dependencyMenu;

    /**
     * The 'Value' menu manager.
     * 
     * @generated
     */
    private MenuManager valueMenu;

    /**
     * The 'Packageable' menu manager.
     * 
     * @generated
     */
    private MenuManager packageableMenu;

    /**
     * The 'All' menu manager.
     * 
     * @generated
     */
    private MenuManager allMenu;

    /**
     * Constructor.
     * 
     * @generated
     */
    public CreateChildMenu()
    {
        super("Create child");
    }

    /**
     * @see org.topcased.modeler.editor.outline.ICreateChildMenu#setMixedEditDomain(org.topcased.modeler.editor.MixedEditDomain)
     */
    public void setMixedEditDomain(MixedEditDomain editDomain)
    {
        this.domain = editDomain;
    }

    /**
     * @see org.topcased.modeler.editor.outline.ICreateChildMenu#setSelectedEObject(org.eclipse.emf.ecore.EObject)
     */
    public void setSelectedEObject(EObject object)
    {
        selectedObject = object;
    }

    /**
     * Creates this menu contents.<br>
     * It creates the menu structure and add all the create child actions.<br>
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void createMenuContents()
    {
        this.descriptors = domain.getEMFEditingDomain().getNewChildDescriptors(selectedObject, null);
        createMenus(this);

        for (Iterator<?> it = descriptors.iterator(); it.hasNext();)
        {
            CommandParameter descriptor = (CommandParameter) it.next();
            EObject child = (EObject) descriptor.getValue();
            CreateChildAction action = new CreateChildAction(domain, selectedObject, descriptor);

            boolean added = false;

            added = addToclassifierMenu(child, action) || added;
            added = addToconstraintMenu(child, action) || added;
            added = addTotriggerMenu(child, action) || added;
            added = addTobehaviorMenu(child, action) || added;
            added = addTodependencyMenu(child, action) || added;
            added = addTovalueMenu(child, action) || added;
            added = addTopackageableMenu(child, action) || added;
            added = addToallMenu(child, action) || added;

            if (!added)
            {
                appendToGroup(UNAPPLIED_ACTIONS_GROUP, action);
            }
        }
    }

    /**
     * Create this menu structure. <br>
     * It creates all the menus and groups.<br>
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param menu the create child menu manager
     * @generated
     */
    protected void createMenus(MenuManager menu)
    {

        classifierMenu = new MenuManager("Classifier");

        menu.add(classifierMenu);

        constraintMenu = new MenuManager("Constraint");

        menu.add(constraintMenu);

        triggerMenu = new MenuManager("Trigger");

        menu.add(triggerMenu);

        behaviorMenu = new MenuManager("Behavior");

        menu.add(behaviorMenu);

        dependencyMenu = new MenuManager("Dependency");

        menu.add(dependencyMenu);

        valueMenu = new MenuManager("Value");

        menu.add(valueMenu);

        packageableMenu = new MenuManager("Packageable");

        menu.add(packageableMenu);

        allMenu = new MenuManager("All");

        menu.add(allMenu);

        // Group containing non sorted actions.
        menu.add(new Separator(UNAPPLIED_ACTIONS_GROUP));
    }

    /**
     * Tries to add for the given child object the associated action to the 'classifier' menu.<br>
     * If this menu has groups or submenus, it also tries to add the action in each of them.<br>
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param child the child object for the one the action must be added
     * @param childAction the action to add
     * @return <code>true</code> if the given action has been added to this menu or one of its groups or submenus, or
     *         <code>false</code> otherwise.
     * @generated
     */
    private boolean addToclassifierMenu(EObject child, CreateChildAction childAction)
    {
        boolean added = false;
        final CreateChildAction action = childAction;

        Object hierarchicalResult = new UMLSwitch<Object>()
        {

            public Object caseClassifier(org.eclipse.uml2.uml.Classifier object)
            {

                classifierMenu.add(action);

                return action;
            }

        }.doSwitch(child);
        added = added || (hierarchicalResult == action);

        return added;
    }

    /**
     * Tries to add for the given child object the associated action to the 'constraint' menu.<br>
     * If this menu has groups or submenus, it also tries to add the action in each of them.<br>
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param child the child object for the one the action must be added
     * @param childAction the action to add
     * @return <code>true</code> if the given action has been added to this menu or one of its groups or submenus, or
     *         <code>false</code> otherwise.
     * @generated
     */
    private boolean addToconstraintMenu(EObject child, CreateChildAction childAction)
    {
        boolean added = false;
        final CreateChildAction action = childAction;

        Object hierarchicalResult = new UMLSwitch<Object>()
        {

            public Object caseConstraint(org.eclipse.uml2.uml.Constraint object)
            {

                constraintMenu.add(action);

                return action;
            }

        }.doSwitch(child);
        added = added || (hierarchicalResult == action);

        return added;
    }

    /**
     * Tries to add for the given child object the associated action to the 'trigger' menu.<br>
     * If this menu has groups or submenus, it also tries to add the action in each of them.<br>
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param child the child object for the one the action must be added
     * @param childAction the action to add
     * @return <code>true</code> if the given action has been added to this menu or one of its groups or submenus, or
     *         <code>false</code> otherwise.
     * @generated
     */
    private boolean addTotriggerMenu(EObject child, CreateChildAction childAction)
    {
        boolean added = false;
        final CreateChildAction action = childAction;

        Object hierarchicalResult = new UMLSwitch<Object>()
        {

            public Object caseTrigger(org.eclipse.uml2.uml.Trigger object)
            {

                triggerMenu.add(action);

                return action;
            }

        }.doSwitch(child);
        added = added || (hierarchicalResult == action);

        return added;
    }

    /**
     * Tries to add for the given child object the associated action to the 'behavior' menu.<br>
     * If this menu has groups or submenus, it also tries to add the action in each of them.<br>
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param child the child object for the one the action must be added
     * @param childAction the action to add
     * @return <code>true</code> if the given action has been added to this menu or one of its groups or submenus, or
     *         <code>false</code> otherwise.
     * @generated
     */
    private boolean addTobehaviorMenu(EObject child, CreateChildAction childAction)
    {
        boolean added = false;
        final CreateChildAction action = childAction;

        Object hierarchicalResult = new UMLSwitch<Object>()
        {

            public Object caseBehavioralFeature(org.eclipse.uml2.uml.BehavioralFeature object)
            {

                behaviorMenu.add(action);

                return action;
            }

        }.doSwitch(child);
        added = added || (hierarchicalResult == action);

        return added;
    }

    /**
     * Tries to add for the given child object the associated action to the 'dependency' menu.<br>
     * If this menu has groups or submenus, it also tries to add the action in each of them.<br>
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param child the child object for the one the action must be added
     * @param childAction the action to add
     * @return <code>true</code> if the given action has been added to this menu or one of its groups or submenus, or
     *         <code>false</code> otherwise.
     * @generated
     */
    private boolean addTodependencyMenu(EObject child, CreateChildAction childAction)
    {
        boolean added = false;
        final CreateChildAction action = childAction;

        Object hierarchicalResult = new UMLSwitch<Object>()
        {

            public Object caseDependency(org.eclipse.uml2.uml.Dependency object)
            {

                dependencyMenu.add(action);

                return action;
            }

        }.doSwitch(child);
        added = added || (hierarchicalResult == action);

        return added;
    }

    /**
     * Tries to add for the given child object the associated action to the 'value' menu.<br>
     * If this menu has groups or submenus, it also tries to add the action in each of them.<br>
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param child the child object for the one the action must be added
     * @param childAction the action to add
     * @return <code>true</code> if the given action has been added to this menu or one of its groups or submenus, or
     *         <code>false</code> otherwise.
     * @generated
     */
    private boolean addTovalueMenu(EObject child, CreateChildAction childAction)
    {
        boolean added = false;
        final CreateChildAction action = childAction;

        Object hierarchicalResult = new UMLSwitch<Object>()
        {

            public Object caseValueSpecification(org.eclipse.uml2.uml.ValueSpecification object)
            {

                valueMenu.add(action);

                return action;
            }

        }.doSwitch(child);
        added = added || (hierarchicalResult == action);

        return added;
    }

    /**
     * Tries to add for the given child object the associated action to the 'packageable' menu.<br>
     * If this menu has groups or submenus, it also tries to add the action in each of them.<br>
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param child the child object for the one the action must be added
     * @param childAction the action to add
     * @return <code>true</code> if the given action has been added to this menu or one of its groups or submenus, or
     *         <code>false</code> otherwise.
     * @generated
     */
    private boolean addTopackageableMenu(EObject child, CreateChildAction childAction)
    {
        boolean added = false;
        final CreateChildAction action = childAction;

        Object hierarchicalResult = new UMLSwitch<Object>()
        {

            public Object casePackage(org.eclipse.uml2.uml.Package object)
            {

                packageableMenu.add(action);

                return action;
            }

        }.doSwitch(child);
        added = added || (hierarchicalResult == action);

        return added;
    }

    /**
     * Tries to add for the given child object the associated action to the 'all' menu.<br>
     * If this menu has groups or submenus, it also tries to add the action in each of them.<br>
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param child the child object for the one the action must be added
     * @param childAction the action to add
     * @return <code>true</code> if the given action has been added to this menu or one of its groups or submenus, or
     *         <code>false</code> otherwise.
     * @generated
     */
    private boolean addToallMenu(EObject child, CreateChildAction childAction)
    {
        boolean added = false;
        final CreateChildAction action = childAction;

        Object hierarchicalResult = new UMLSwitch<Object>()
        {

            public Object caseElement(org.eclipse.uml2.uml.Element object)
            {

                allMenu.add(action);

                return action;
            }

        }.doSwitch(child);
        added = added || (hierarchicalResult == action);

        return added;
    }

}
