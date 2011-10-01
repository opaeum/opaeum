/*****************************************************************************
 * 
 * UMLEditorMenu.java
 * 
 * Copyright (c) 2008 Atos Origin.
 *
 * Contributors:
 *  Tristan Faure (Atos Origin) tristan.faure@atosorigin.com - Initial API and implementation
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *****************************************************************************/
package org.topcased.modeler.uml.internal.customchildmenu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.internal.Workbench;
import org.topcased.modeler.editor.MixedEditDomain;
import org.topcased.modeler.editor.outline.ICreateChildMenu;

/**
 * The Class UMLEditorMenu. based on org.eclipse.uml plugin's outline
 */
public class UMLEditorMenu extends MenuManager implements ICreateChildMenu
{

    /** The domain. */
    private MixedEditDomain domain;

    /** The selected object. */
    private EObject selectedObject;

    /** The descriptors. */
    private Collection< ? > descriptors;

    /**
     * Constructor.
     * 
     * @generated
     */
    public UMLEditorMenu()
    {
        super("Create child");
    }

    /**
     * The Constructor.
     * 
     * @param text the text
     */
    public UMLEditorMenu(String text)
    {
        super(text);
    }

    /**
     * Populate manager.
     * 
     * @param manager the manager
     * @param submenuActions the submenu actions
     * @param contributionID the contribution id
     */
    protected void populateManager(IContributionManager manager, Map<String, Collection<IAction>> submenuActions, String contributionID)
    {
        if (submenuActions != null)
        {
            for (Map.Entry<String, Collection<IAction>> entry : submenuActions.entrySet())
            {
                MenuManager submenuManager = new MenuManager(entry.getKey());
                if (contributionID != null)
                {
                    manager.insertBefore(contributionID, submenuManager);
                }
                else
                {
                    manager.add(submenuManager);
                }
                populateManager(submenuManager, entry.getValue(), null);
            }
        }
    }

    /**
     * Create menu contents
     * 
     * @see org.topcased.modeler.editor.outline.ICreateChildMenu#createMenuContents()
     */
    public void createMenuContents()
    {
        this.descriptors = domain.getEMFEditingDomain().getNewChildDescriptors(selectedObject, null);
        Collection<IAction> createChildActions = generateCreateChildActions(descriptors, Workbench.getInstance().getActiveWorkbenchWindow().getSelectionService().getSelection());
        Map<String, Collection<IAction>> createChildSubmenuActions = extractSubmenuActions(createChildActions);
        populateManager(this, createChildSubmenuActions, null);
        populateManager(this, createChildActions, null);
        this.update();
    }

    /**
     * Generate create child actions.
     * 
     * @param theDescriptors the descriptors
     * @param selection the selection
     * 
     * @return the collection< i action>
     */
    protected Collection<IAction> generateCreateChildActions(Collection< ? > theDescriptors, ISelection selection)
    {
        List<IAction> createChildActions = (List<IAction>) generateCreateChildActionsGen(theDescriptors, selection);
        Collections.<IAction> sort(createChildActions, new Comparator<IAction>()
        {
            public int compare(IAction a1, IAction a2)
            {
                return CommonPlugin.INSTANCE.getComparator().compare(a1.getText(), a2.getText());
            }
        });
        return createChildActions;
    }

    /**
     * Generate create child actions gen.
     * 
     * @param theDescriptors the descriptors
     * @param selection the selection
     * 
     * @return the collection< i action>
     */
    protected Collection<IAction> generateCreateChildActionsGen(Collection< ? > theDescriptors, ISelection selection)
    {
        Collection<IAction> actions = new ArrayList<IAction>();
        if (theDescriptors != null)
        {
            for (Object descriptor : theDescriptors)
            {
                actions.add(new CreateChildAction(Workbench.getInstance().getActiveWorkbenchWindow().getPartService().getActivePart(), selection, descriptor));
            }
        }
        return actions;
    }

    /**
     * Populate manager.
     * 
     * @param manager the manager
     * @param actions the actions
     * @param contributionID the contribution id
     */
    protected void populateManager(IContributionManager manager, Collection< ? extends IAction> actions, String contributionID)
    {
        if (actions != null)
        {
            for (IAction action : actions)
            {
                if (contributionID != null)
                {
                    manager.insertBefore(contributionID, action);
                }
                else
                {
                    manager.add(action);
                }
            }
        }
    }

    /**
     * Extract submenu actions.
     * 
     * @param createActions the create actions
     * 
     * @return the map< string, collection< i action>>
     */
    protected Map<String, Collection<IAction>> extractSubmenuActions(Collection<IAction> createActions)
    {
        Map<String, Collection<IAction>> createSubmenuActions = new LinkedHashMap<String, Collection<IAction>>();
        if (createActions != null)
        {
            for (Iterator<IAction> actions = createActions.iterator(); actions.hasNext();)
            {
                IAction action = actions.next();
                StringTokenizer st = new StringTokenizer(action.getText(), "|"); //$NON-NLS-1$
                if (st.countTokens() == 2)
                {
                    String text = st.nextToken().trim();
                    Collection<IAction> submenuActions = createSubmenuActions.get(text);
                    if (submenuActions == null)
                    {
                        submenuActions = new ArrayList<IAction>();
                        createSubmenuActions.put(text, submenuActions);
                    }
                    action.setText(st.nextToken().trim());
                    submenuActions.add(action);
                    actions.remove();
                }
            }
        }
        return createSubmenuActions;
    }

    /**
     * Sets the mixed edit domain.
     * 
     * @param editDomain the edit domain
     * 
     * @see org.topcased.modeler.editor.outline.ICreateChildMenu#setMixedEditDomain(org.topcased.modeler.editor.MixedEditDomain)
     */
    public void setMixedEditDomain(MixedEditDomain editDomain)
    {
        this.domain = editDomain;
    }

    /**
     * Set the selected EObject
     * 
     * @see org.topcased.modeler.editor.outline.ICreateChildMenu#setSelectedEObject(org.eclipse.emf.ecore.EObject)
     */
    public void setSelectedEObject(EObject object)
    {
        selectedObject = object;
    }

}
