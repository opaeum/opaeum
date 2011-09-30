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
 *   eperico (Atos Origin) - fix bug 2421
 ******************************************************************************/

package org.topcased.modeler.uml.usecasediagram.commands;

import org.eclipse.gef.EditDomain;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Type;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.uml.classdiagram.util.AssociationHelper;
import org.topcased.modeler.utils.LabelHelper;
import org.topcased.modeler.utils.Utils;

/**
 * Association edge creation command<br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated NOT
 */
public class AssociationEdgeCreationCommand extends CreateTypedEdgeCommand
{

    private Association association;

    private Type sourceObject;

    private Type targetObject;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param domain the edit domain
     * @param newObj the graph edge of the new connection
     * @param src the graph element of the source
     * @generated
     */
    public AssociationEdgeCreationCommand(EditDomain domain, GraphEdge newObj, GraphElement src)
    {
        this(domain, newObj, src, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param domain the edit domain
     * @param newObj the graph edge of the new connection
     * @param src the graph element of the source
     * @param needModelUpdate set it to true if the model need to be updated
     * @generated
     */
    public AssociationEdgeCreationCommand(EditDomain domain, GraphEdge newObj, GraphElement src, boolean needModelUpdate)
    {
        super(domain, newObj, src, needModelUpdate);
    }

    /**
     * To convert the first character to lower case
     * @param targetString the string where the first character has to be lowerCase
     * @return a string with the first character in lowerCase
     * */
    public String toLowcase(String targetString)
    {
        if(targetString.length()!=0)
        {
            String lowString = targetString.toLowerCase();
            char chartoLow = lowString.charAt(0);
            return  chartoLow+targetString.substring(1, targetString.length());
        }
        return null;
    }
    
    /**
     * Create the model objects that will be associated with the Association
     * 
     * @see org.topcased.modeler.commands.CreateGraphEdgeCommand#execute()
     */
    public void execute()
    {
        // Create the association and add it to the current package
        association = (Association) Utils.getElement(getEdge());
        AssociationHelper associationHelper = new AssociationHelper(association);
        // Retrieve source object and target object
        sourceObject = (Type) Utils.getElement(getSource());
        targetObject = (Type) Utils.getElement(getTarget());
        
        if(isUpdateModel())
        {
            String lowSourceName = toLowcase(sourceObject.getName());
            if( associationHelper.getAssociationFirstMemberEnd()!=null)
            {
                associationHelper.getAssociationFirstMemberEnd().setType(sourceObject);
                associationHelper.getAssociationFirstMemberEnd().setName(lowSourceName);         
            }
            
            String lowTargetName = toLowcase(targetObject.getName());
            if(associationHelper.getAssociationSecondMemberEnd()!=null)
            {
                associationHelper.getAssociationSecondMemberEnd().setType(targetObject);     
                associationHelper.getAssociationSecondMemberEnd().setName(lowTargetName);
            }
            
            int cpt = associationHelper.findName(getEditDomain(), getContainerEObject(), association);
            
            association.setName(associationHelper.getAssociationName(cpt));            
        }
        
        super.execute();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    protected void redoModel()
    {
        // Add the Association to the current package
    	if (getContainerEObject() instanceof Package) {
			Package pack = (Package) getContainerEObject();
			pack.getPackagedElements().add(association);
		}
    	else
    	{
    		sourceObject.getPackage().getPackagedElements().add(association);
    	}

        // init the name of the association contained by the package
        String curName = LabelHelper.INSTANCE.getName(getEditDomain(), association);
        if (curName == null || curName.length() == 0)
        {
            LabelHelper.INSTANCE.initName(getEditDomain(), Utils.getElement(getEdge()).eContainer(), association);
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    protected void undoModel()
    {
        // Remove the association from the package
        sourceObject.getPackage().getPackagedElements().remove(association);
    }

}