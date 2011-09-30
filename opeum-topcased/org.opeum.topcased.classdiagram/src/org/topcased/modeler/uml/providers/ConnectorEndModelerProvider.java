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

package org.topcased.modeler.uml.providers;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.providers.IDeletePartnerProvider;
import org.topcased.modeler.providers.ILabelFeatureProvider;

/**
 * This is the item provider adpater for a {@link org.eclipse.uml2.uml.ConnectorEnd} object. 
 * 
 * <!-- begin-user-doc -->
 * Add the IDeletePartnerProvider to delete the object linked with the ConnectorEnd
 * <!-- end-user-doc -->
 * 
 * @generated NOT
 */

public class ConnectorEndModelerProvider extends MultiplicityElementModelerProvider implements ILabelFeatureProvider, IDeletePartnerProvider
{
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param adapterFactory the adapter factory
     * 
     * @generated
     */
    public ConnectorEndModelerProvider(AdapterFactory adapterFactory)
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
        return UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered();
    }

    /* (non-Javadoc)
     * @see org.topcased.modeler.providers.IDeletePartnerProvider#getDeletePartners(java.lang.Object)
     */
    public List<EObject> getDeletePartners(Object object)
    {
        List<EObject> lEObjectPartners = new LinkedList<EObject>();
        ConnectorEnd lConnectorEnd = (ConnectorEnd)object;
        Connector lConnector = getConnectorFromConnectorEnd(lConnectorEnd);
        
        if(lConnector !=null)
        {
            lEObjectPartners.add(lConnector);
        }
        
        return lEObjectPartners;
    }
    
    /**
     * Get the connector associated with the given connectorEnd.
     * 
     * @param pConnectorEnd the connectorEnd
     * 
     * @return the connector
     */
    private Connector getConnectorFromConnectorEnd(ConnectorEnd pConnectorEnd)
    {
        Connector lConnector = null;

        if (pConnectorEnd.eContainer() instanceof Connector)
        {
            lConnector = (Connector) pConnectorEnd.eContainer();
        }
        return lConnector;
    }
    
    
}