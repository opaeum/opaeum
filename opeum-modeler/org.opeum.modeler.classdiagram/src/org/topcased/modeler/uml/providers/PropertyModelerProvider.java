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

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.providers.IDeletePartnerProvider;
import org.topcased.modeler.providers.ILabelFeatureProvider;
import org.topcased.modeler.utils.Utils;

/**
 * This is the item provider adpater for a {@link org.eclipse.uml2.uml.Property} object. <!-- begin-user-doc --> Add the
 * IDeletePartnerProvider to delete the object linked with the Property <!-- end-user-doc -->
 * 
 * @generated NOT
 */

public class PropertyModelerProvider extends StructuralFeatureModelerProvider implements ILabelFeatureProvider, IDeletePartnerProvider
{
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param adapterFactory the adapter factory
     * 
     * @generated
     */
    public PropertyModelerProvider(AdapterFactory adapterFactory)
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

    /*
     * (non-Javadoc)
     * 
     * @see org.topcased.modeler.providers.IDeletePartnerProvider#getDeletePartners(java.lang.Object)
     */
    public List<EObject> getDeletePartners(Object object)
    {
        List<EObject> lEObjectPartners = new LinkedList<EObject>();
        Property property = (Property) object;
        // Delete the connector associated with this property
        if (property.getEnds() != null)
        {
            for (ConnectorEnd lConnectorEnd : property.getEnds())
            {
                Connector lConnector = getConnectorFromConnectorEnd(lConnectorEnd);
                if (lConnector != null)
                {
                    lEObjectPartners.add(lConnector);
                }
            }
        }
        // Delete connectors which are related to this property through
        // partWithPort attribut of the connectorEnd
        Collection<Setting> references = Utils.getCrossReferences(property);
        for (Setting setting : references)
        {
            if (setting.getEObject() instanceof ConnectorEnd)
            {
                ConnectorEnd connectorEnd = (ConnectorEnd) setting.getEObject();
                Connector lConnector = getConnectorFromConnectorEnd(connectorEnd);
                /*
                 * Connector end may have been deleted with connector. Hence, it may have not null lConnector as parent,
                 * which is itself a phantom reference and which has already been deleted. In such a case, it would have
                 * no container, then just ignore it, or it will make the remove command unexecutable and not add it.
                 */
                if (lConnector != null && lConnector.eContainer() != null)
                {
                    lEObjectPartners.add(lConnector);
                }
            }
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