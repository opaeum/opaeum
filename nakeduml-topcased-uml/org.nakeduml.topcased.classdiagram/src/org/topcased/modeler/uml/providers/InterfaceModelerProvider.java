/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *   Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware Technologies),
 *   Nicolas Lalev�e (Anyware Technologies) - initial API and implementation,
 *   Ludi Akue (Atos Origin) - related object-partners deletion mechanism 
 ******************************************************************************/

package org.topcased.modeler.uml.providers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.providers.IDeletePartnerProvider;
import org.topcased.modeler.providers.ILabelFeatureProvider;

/**
 * This is the item provider adapter for a {@link org.eclipse.uml2.uml.Interface} object. <!-- begin-user-doc --> <!--
 * end-user-doc -->
 * 
 * @generated
 */

public class InterfaceModelerProvider extends ClassifierModelerProvider implements ILabelFeatureProvider, IDeletePartnerProvider
{

    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param adapterFactory the adapter factory
     * 
     * @generated
     */
    public InterfaceModelerProvider(AdapterFactory adapterFactory)
    {
        super(adapterFactory);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->.
     * 
     * @param object the object
     * 
     * @return the label feature
     * 
     * @see org.topcased.modeler.providers.ILabelFeatureProvider#getLabelFeature(java.lang.Object)
     * @generated
     */
    public EAttribute getLabelFeature(Object object)
    {
        return UMLPackage.eINSTANCE.getNamedElement_Name();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.topcased.modeler.providers.IDeletePartnerProvider#getDeletePartners(java.lang.Object)
     */
    public List<EObject> getDeletePartners(Object object)
    {
        List<EObject> lDeletePartners = new ArrayList<EObject>();
        Interface lInterface = (Interface) object;

        // handle dependencies from the interface
        for (Dependency lDependency : lInterface.getClientDependencies())
        {
            lDeletePartners.add(lDependency);
        }

        // handle associations from the interface
        for (Association lAssociation : lInterface.getAssociations())
        {
            lDeletePartners.add(lAssociation);
        }

        Element lElement = lInterface.getOwner();

        for (Element lSubElt : lElement.allOwnedElements())
        {
            // handleinterfaceRealizations that target the interface
            if (lSubElt instanceof InterfaceRealization)
            {
                Interface lInterfaceTemp = ((InterfaceRealization) lSubElt).getContract();
                if (lInterface.equals(lInterfaceTemp))
                {
                    lDeletePartners.add((InterfaceRealization) lSubElt);
                }
            }

            // handle dependencies that target the interface
            if (lSubElt instanceof Dependency)
            {
                for (Element lTemp : ((Dependency) lSubElt).getTargets())
                {
                    if (lTemp instanceof Interface)
                    {
                        if (lInterface.equals((Interface) lTemp))
                        {
                            lDeletePartners.add((Dependency) lSubElt);
                        }

                    }
                }

            }

        }

        return lDeletePartners;
    }
}