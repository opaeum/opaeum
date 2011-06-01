/*****************************************************************************
 * 
 * UMLMetamodelFilter.java
 * 
 * Copyright (c) 2008 Atos Origin.
 *
 * Contributors:
 *  Thibault Landré (Atos Origin) thibault.landre@atosorigin.com
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
  *****************************************************************************/
package org.topcased.modeler.uml.editor.outline;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.editor.outline.filters.GenericMetamodelFilter;

/**
 * The Class UMLMetamodelFilter.
 */
public class UMLMetamodelFilter extends GenericMetamodelFilter {

	

	/* (non-Javadoc)
	 * @see org.topcased.modeler.editor.outline.filters.GenericMetamodelFilter#getMetamodelElements()
	 */
	@Override
	public EList<EClassifier> getMetamodelElements() 
	{
		return UMLPackage.eINSTANCE.getEClassifiers();
	}
	
	/* (non-Javadoc)
	 * @see org.topcased.modeler.editor.outline.filters.GenericMetamodelFilter#getClass(java.lang.String)
	 */
	@Override
    public Class getClass(String elementName)
    {
    	try
        {
            return Class.forName(elementName.toString());
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
