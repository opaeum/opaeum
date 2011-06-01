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
 *   Ludi Akue (Atos Origin) - related object-partners deletion mechanism
 ******************************************************************************/

package org.topcased.modeler.uml.providers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;
import org.topcased.modeler.providers.IDeletePartnerProvider;
import org.topcased.modeler.providers.ILabelFeatureProvider;

/**
 * This is the item provider adpater for a {@link org.eclipse.uml2.uml.UseCase} object. <!-- begin-user-doc --> <!--
 * end-user-doc -->
 * 
 * @generated
 */

public class UseCaseModelerProvider extends BehavioredClassifierModelerProvider implements ILabelFeatureProvider, IDeletePartnerProvider
{
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param adapterFactory the adapter factory
     * 
     * @generated
     */
    public UseCaseModelerProvider(AdapterFactory adapterFactory)
    {
        super(adapterFactory);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.providers.ILabelFeatureProvider#getLabelFeature(java.lang.Object)
     * @generated
     */
    public EAttribute getLabelFeature(Object object)
    {
        return UMLPackage.eINSTANCE.getNamedElement_Name();
    }

    public List<EObject> getDeletePartners(Object object)
    {
        List<EObject> lDeletePartners = new ArrayList<EObject>();
        UseCase lUseCase = (UseCase) object;
        Element lElement = lUseCase.getOwner();

        for (Element lSubElt : lElement.allOwnedElements())
        {
            // if extended use case, handle extend relationships that target it
            if (lSubElt instanceof Extend)
            {
                UseCase lTemp = ((Extend) lSubElt).getExtendedCase();
                if (lUseCase.equals(lTemp))
                {
                    lDeletePartners.add((Extend) lSubElt);
                }
            }
            // if addition use case, handle include relationships that target it
            if (lSubElt instanceof Include)
            {
                UseCase lTemp = ((Include) lSubElt).getAddition();
                if (lUseCase.equals(lTemp))
                {
                    lDeletePartners.add((Include) lSubElt);
                }
            }
            // if use case with associations? handle associations that target it
            if (lSubElt instanceof Association)
            {
                for (Element lTemp : ((Association) lSubElt).getRelatedElements())
                {

                    if ((lTemp instanceof UseCase) && lUseCase.equals(lTemp))
                    {
                        lDeletePartners.add((Association) lSubElt);
                    }
                }
            }
        }

        return lDeletePartners;
    }
}