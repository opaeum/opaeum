/*******************************************************************************
 * Copyright (c) 2005 Anyware Technologies
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    David Sciamma (Anyware Technologies) - initial API and implementation
 * 	  Guojun Song (Atos Origin) - Fixed #2306
 *******************************************************************************/

package org.topcased.modeler.uml.editor.outline;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Profile;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.editor.outline.IOutlineMenuConstants;
import org.topcased.modeler.editor.outline.ModelNavigator;
import org.topcased.modeler.editor.outline.OutlineDragAdapter;
import org.topcased.modeler.editor.outline.OutlineToOutlineDropAdapter;
import org.topcased.modeler.extensions.OutlineManager;
import org.topcased.modeler.extensions.OutlineManager.CreateChildMenuConfiguration;
import org.topcased.modeler.preferences.ModelerPreferenceConstants;
import org.topcased.modeler.uml.actions.ApplyProfileAction;
import org.topcased.modeler.uml.actions.ApplyStereotypeAction;
import org.topcased.modeler.uml.actions.DefineProfileAction;
import org.topcased.modeler.uml.actions.GenerateJavaPrimitiveTypesAction;
import org.topcased.modeler.uml.actions.GenerateUMLPrimitiveTypesAction;
import org.topcased.modeler.uml.actions.ImportPrimitiveTypesAction;
import org.topcased.modeler.uml.actions.UnapplyProfileAction;
import org.topcased.modeler.uml.actions.UnapplyStereotypeAction;
import org.topcased.modeler.uml.internal.customchildmenu.UMLEditorMenu;

/**
 * This custom navigator changes the context menu and adds some UML actions.
 * 
 * @author <a href="david.sciamma@anyware-tech.com">David Sciamma</a>
 */
public class UMLNavigator extends ModelNavigator
{
    /**
     * Constructor
     * 
     * @param parent the parent composite
     * @param editor the modeler
     * @param pageSite the outline site
     */
    public UMLNavigator(Composite parent, Modeler editor, IPageSite pageSite)
    {
        super(parent, editor, pageSite);
        setUMLEditorMenuToDefault();
    }

    /**
     * @see org.topcased.modeler.editor.outline.ModelNavigator#createSingleSelectionMenu(org.eclipse.jface.action.IMenuManager,
     *      java.lang.Object)
     */
    protected void createSingleSelectionMenu(IMenuManager manager, Object selection)
    {
        super.createSingleSelectionMenu(manager, selection);

        EObject selectedObject = null;

        if (selection instanceof EObject)
        {
            selectedObject = (EObject) selection;
        }
        else if (selection instanceof IWrapperItemProvider || selection instanceof FeatureMap.Entry)
        {
            selectedObject = (EObject) AdapterFactoryEditingDomain.unwrap(selection);
        }

        if (selectedObject instanceof Model)
        {
            IMenuManager generateTypesMenu = new MenuManager("Generate Primitive Types");

            generateTypesMenu.add(new GenerateUMLPrimitiveTypesAction(getModeler(), (Model) selectedObject));
            generateTypesMenu.add(new GenerateJavaPrimitiveTypesAction(getModeler(), (Model) selectedObject));

            manager.appendToGroup(IOutlineMenuConstants.NEW_GROUP, generateTypesMenu);
        }
        if (selectedObject instanceof org.eclipse.uml2.uml.Package)
        {
            manager.appendToGroup(IOutlineMenuConstants.NEW_GROUP, new ImportPrimitiveTypesAction(getModeler(), (org.eclipse.uml2.uml.Package) selectedObject));

            manager.appendToGroup(IOutlineMenuConstants.EDIT_GROUP, new ApplyProfileAction(getModeler(), (org.eclipse.uml2.uml.Package) selectedObject));
            manager.appendToGroup(IOutlineMenuConstants.EDIT_GROUP, new UnapplyProfileAction(getModeler(), (org.eclipse.uml2.uml.Package) selectedObject));
        }
        if (selectedObject instanceof Element)
        {
            manager.appendToGroup(IOutlineMenuConstants.EDIT_GROUP, new ApplyStereotypeAction(getModeler(), (Element) selectedObject));
            manager.appendToGroup(IOutlineMenuConstants.EDIT_GROUP, new UnapplyStereotypeAction(getModeler(), (Element) selectedObject));
        }
        if (selectedObject instanceof Profile)
        {
            manager.appendToGroup(IOutlineMenuConstants.EDIT_GROUP, new DefineProfileAction(getModeler(), (Profile) selectedObject));
        }
    }

    
    /**
     * Sets the uml editor menu to UMLEditorMenu if there is no preferences
     */
    private void setUMLEditorMenuToDefault()
    {
        // setting by default the UML Editor Outline
        Modeler currentModeler = this.getModeler();
        IPreferenceStore ps = currentModeler.getPreferenceStore();
        if (ps != null)
        {
            if (ps.getString(ModelerPreferenceConstants.CREATE_CHILD_MENU_PREF).equals(""))
            {
                Collection<CreateChildMenuConfiguration> configs = OutlineManager.getInstance().getCreateChildMenus(currentModeler.getSite().getId());
                CreateChildMenuConfiguration config = OutlineManager.getInstance().getCreateChildMenuConfiguration(currentModeler.getId());
                for (Iterator<CreateChildMenuConfiguration> it = configs.iterator(); it.hasNext();)
                {
                    CreateChildMenuConfiguration configtmp = it.next();
                    if (configtmp.getMenu() instanceof UMLEditorMenu)
                    {
                        ps.setValue(ModelerPreferenceConstants.CREATE_CHILD_MENU_PREF, configtmp.getId());
                    }
                }
            }
        }
    }

    @Override
    protected OutlineDragAdapter getOutlineDragAdapter(TreeViewer viewer)
    {
        return new UMLOutlineDragAdapter(viewer);
    }

    @Override
    protected OutlineToOutlineDropAdapter getOutlineToOutlineDropAdapter(Modeler modeler, TreeViewer viewer)
    {
        return new UMLOutlineToOutlineDropAdapter(modeler, viewer);
    }

    

}
