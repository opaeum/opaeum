/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *   Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware Technologies),
 *   Nicolas Lalev�e (Anyware Technologies) - initial API and implementation 
 ******************************************************************************/

package org.topcased.modeler.uml.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.topcased.modeler.wizards.DiagramsPage;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class UMLDiagramsPage extends DiagramsPage
{
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param pageName
     * @param selection
     * @generated
     */
    public UMLDiagramsPage(String pageName, IStructuredSelection selection)
    {
        super(pageName, selection, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.wizards.DiagramsPage#getEditorID()
     * @generated
     */
    public String getEditorID()
    {
        return "org.topcased.modeler.uml.editor.UMLEditor";
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.wizards.DiagramsPage#getFileExtension()
     * @generated
     */
    public String getFileExtension()
    {
        return "uml";
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.wizards.DiagramsPage#getAdapterFactory()
     * @generated
     */
    public ComposedAdapterFactory getAdapterFactory()
    {
        List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
        factories.add(new UMLItemProviderAdapterFactory());
        factories.add(new ResourceItemProviderAdapterFactory());
        factories.add(new ReflectiveItemProviderAdapterFactory());

        return new ComposedAdapterFactory(factories);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.wizards.DiagramsPage#getDefaultTemplateId()
     * @return String
     * @generated
     */
    public String getDefaultTemplateId()
    {

        return "org.topcased.modeler.uml.templates.classdiagram";

    }
    
    public String getDefaultTemplateModelId()
    {
        return "org.topcased.modeler.uml.templates.common";
    }

}
