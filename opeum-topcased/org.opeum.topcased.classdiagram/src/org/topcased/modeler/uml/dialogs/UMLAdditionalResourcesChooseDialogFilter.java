/*****************************************************************************
 * Copyright (c) 2009 ATOS ORIGIN INTEGRATION.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Tristan FAURE (ATOS ORIGIN INTEGRATION) tristan.faure@atosorigin.com - Initial API and implementation
 *
  *****************************************************************************/
package org.topcased.modeler.uml.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.facilities.extensions.IChooseDialogFilter;
import org.topcased.modeler.uml.editor.UMLEditor;
import org.topcased.tabbedproperties.utils.ITypeCacheAdapter;
import org.topcased.tabbedproperties.utils.TypeCacheAdapter;

/**
 * The Class UMLAdditionalResourcesChooseDialogFilter.
 */
public class UMLAdditionalResourcesChooseDialogFilter implements IChooseDialogFilter
{

    /**
     * This is the list of all the additional resource standard. Any elements contained in it will be visible only if an element import or a package import is created
     */
    private String[] URIS = new String []{
            "pathmap://UML_PROFILES/Ecore.profile.uml",
            "pathmap://UML_PROFILES/Standard.profile.uml",
            "pathmap://UML_PROFILES/UML2.profile.uml",
            "pathmap://UML_LIBRARIES/JavaPrimitiveTypes.library.uml",
            "pathmap://UML_LIBRARIES/EcorePrimitiveTypes.library.uml",
            "pathmap://UML_LIBRARIES/XMLPrimitiveTypes.library.uml",
            "pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml",
            "pathmap://UML_METAMODELS/UML.metamodel.uml",
            "pathmap://UML_METAMODELS/Ecore.metamodel.uml"
    }; 
    
    private List<String> urisList = null ;
    
    
    public UMLAdditionalResourcesChooseDialogFilter ()
    {
        urisList = new LinkedList<String>();
        urisList.addAll(getURIList());
    }

    protected List<String> getURIList()
    {
        List<String> list = new ArrayList<String>(URIS.length);
        Collections.addAll(list, URIS);
        return list ;
    }
    
    /* (non-Javadoc)
     * @see org.topcased.facilities.extensions.IChooseDialogFilter#filter(java.lang.Object)
     */
    public boolean filter(Object o)
    {
        boolean filter = true ;
        if (o instanceof String && ((String)o).equals(""))
        {
            filter = false ;
        }
        // only filter for the case we are concerned in other case we don't filter
        else if (isCorrectCurrentEditor() &&  o instanceof EObject)
        {
            EObject eobject = (EObject) o;
            // if the current object is contained in uri list, 
            // the process checks if the element is referenced in element import or contained in package imported
            if (eobject.eResource() != null && urisList.contains(eobject.eResource().getURI().toString()))
            {
                
                // to get the elements typed Element import or PackageImport => use of TypeCacheAdapter, the model is parsed only one time
                ITypeCacheAdapter adapter = TypeCacheAdapter.getExistingTypeCacheAdapter(eobject);
                
                // check if the current element is an element import
                Collection<EObject> objects = adapter.getReachableObjectsOfType(eobject, UMLPackage.Literals.ELEMENT_IMPORT);
                for (EObject e : objects)
                {
                    // the element import mustn't be in a resource listed in uri list
                    if (e instanceof ElementImport && e.eResource() != null && !urisList.contains(e.eResource().getURI().toString()))
                    {
                        ElementImport importElement = (ElementImport) e;
                        if (importElement.getImportedElement() == eobject)
                        {
                           filter = false ;
                           break ;
                        }
                    }
                }
                // check if the element is a child of package import
                objects = adapter.getReachableObjectsOfType(eobject, UMLPackage.Literals.PACKAGE_IMPORT);
                for (EObject e : objects)
                {
                    // the package import mustn't be in a resource listed in uri list
                    if (e instanceof PackageImport && e.eResource() != null && !urisList.contains(e.eResource().getURI().toString()))
                    {
                        PackageImport importPackage = (PackageImport) e ;
                        if (EcoreUtil.isAncestor(importPackage.getImportedPackage(), eobject))
                        {
                            filter = false ;
                            break ;
                        }
                    }
                }
            }
            else
            {
                filter = false ;
            }
        }
        else
        {
            filter = false ;
        }
        return filter;
    }
    
    /**
     * The filtering process must be triggered only in UML editor
     * @return true if UML is the current editor
     */
    public boolean isCorrectCurrentEditor ()
    {
        IEditorPart editor = getActiveEditor();
        return editor instanceof UMLEditor ;
        
    }
    
    /**
     * Method returning the current editor
     * @return null if there is no current editor
     */
    public static IEditorPart getActiveEditor()
    {
        IEditorPart result = null;
        IWorkbench workbench = PlatformUI.getWorkbench();
        if (workbench != null)
        {
            IWorkbenchWindow activeWorkbenchWindow = workbench.getActiveWorkbenchWindow();
            if (activeWorkbenchWindow != null)
            {
                IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
                if (activePage != null)
                {
                    IEditorPart activeEditor = activePage.getActiveEditor();
                    if (activeEditor != null)
                    {
                        result = activeEditor;
                    }
                }
            }
        }
        return result;
    }

}
