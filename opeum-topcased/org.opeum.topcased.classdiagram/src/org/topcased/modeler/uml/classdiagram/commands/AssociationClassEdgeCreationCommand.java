/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.commands;

import org.eclipse.gef.EditDomain;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Type;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.uml.classdiagram.util.AssociationHelper;
import org.topcased.modeler.utils.LabelHelper;
import org.topcased.modeler.utils.Utils;

/**
 * AssociationClass edge creation command<br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class AssociationClassEdgeCreationCommand extends CreateTypedEdgeCommand
{
    /** The association class. */
    private AssociationClass associationClass;
    /** The source object. */
    private Type sourceObject;
    /** The target object. */
    private Type targetObject;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param domain the edit domain
     * @param newObj the graph edge of the new connection
     * @param src the graph element of the source
     * @generated
     */
    public AssociationClassEdgeCreationCommand(EditDomain domain, GraphEdge newObj, GraphElement src)
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
    public AssociationClassEdgeCreationCommand(EditDomain domain, GraphEdge newObj, GraphElement src, boolean needModelUpdate)
    {
        super(domain, newObj, src, needModelUpdate);
    }
    
    /**
     * To convert the first character to lower case
     * @param targetString the string where the first character has to be lowerCase
     * @return a string with the first character in lowerCase
     * */
    private String toLowcase(String targetString)
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
        associationClass = (AssociationClass) Utils.getElement(getEdge());
        AssociationHelper associationHelper = new AssociationHelper(associationClass);
        // Retrieve source object and target object
        sourceObject = (Type) Utils.getElement(getSource());
        targetObject = (Type) Utils.getElement(getTarget());

        //To fix #1781 #1782
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
        	
        	int cpt = associationHelper.findName(getEditDomain(), getContainerEObject(), associationClass);
   	       
	        associationClass.setName(associationHelper.getAssociationName(cpt));
        }
        super.execute();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void redoModel()
    {
        // Add the Association to the current package
        sourceObject.getPackage().getPackagedElements().add(associationClass);

        // init the name of the association contained by the package
        String curName = LabelHelper.INSTANCE.getName(getEditDomain(), associationClass);
        if (curName == null || curName.length() == 0)
        {
            LabelHelper.INSTANCE.initName(getEditDomain(), Utils.getElement(getEdge()).eContainer(), associationClass);
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void undoModel()
    {
        // Remove the association from the package
        sourceObject.getPackage().getPackagedElements().remove(associationClass);
    }

}