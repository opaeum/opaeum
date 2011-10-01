/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.commands;

import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.uml.alldiagram.commands.NamedElementRestoreConnectionCommand;
import org.topcased.modeler.utils.Utils;

/**
 * Type restore connection command<br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class TypeRestoreConnectionCommand extends NamedElementRestoreConnectionCommand
{
	
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param part the EditPart that is restored
     * @generated
     */
    public TypeRestoreConnectionCommand(EditPart part)
    {
        super(part);    
    }
    
   

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.commands.AbstractRestoreConnectionCommand#initializeCommands()
     * @generated NOT
     */
    protected void initializeCommands()
    {

        super.initializeCommands();

        GraphElement graphElementSrc = getGraphElement();
        EObject eObjectSrc = Utils.getElement(graphElementSrc);
       
        if (eObjectSrc instanceof Type)
        {
        	Type type = (Type)eObjectSrc;

        	// Get the association of the type
        	System.out.println("");
        	EList<Association> associations = type.getAssociations();
        	
        	// type.getAssociations returns a lot of duplicate association after a DND action on the outline on the association. 
        	// TODO : Find a way to reproduce the bug on EMF UML2 and open a bug against Eclipse.
        	associations = cleanAssociationList(associations);

        	

        	for(Association association : associations)
        	{
        		if(association.isBinary())
        		{
        			boolean isCreated = false;
        			
        			for (GraphElement graphElementTgt : getAllGraphElements())
        			{
        				EObject eObjectTgt = Utils.getElement(graphElementTgt);
        				if(eObjectTgt instanceof Type)
        				{
        					isCreated = createAssociation(graphElementSrc, association,
        							graphElementTgt);	
        					if(isCreated)
        					{
        						break;
        					}
        				}
        			}

        		}
        	}
        	
        }
    }
    
    
    private EList<Association> cleanAssociationList(EList<Association> associationList)
    {
        EList<Association> cleanAssociationList = new BasicEList<Association>();
        for(Association associationSrc : associationList)
        {
           boolean isNotPresent = true;
           for(Association associationTgt : cleanAssociationList)
           {
               if(associationEquals(associationSrc, associationTgt))
               {
                   isNotPresent=false;
               }
           }
           if(isNotPresent)
           {
               cleanAssociationList.add(associationSrc);
           }
        }
        return cleanAssociationList;
    }
    
    /**
     * Test if two associations are equals
     * @param assocSrc the association source to test
     * @param assocTgt the association target to test
     * @return true if the two associations are equals.
     */
    private boolean associationEquals(Association assocSrc, Association assocTgt)
    {
    	// The two associations have 2 members ends
        if(assocSrc.getMemberEnds().size() == 2 && assocTgt.getMemberEnds().size() == 2)
        {
        	// the names of the associations are defined for both association
        	if(assocSrc.getName()!= null && assocTgt.getName() != null)
        	{
        		if("".equals(assocSrc.getName()) && "".equals(assocTgt.getName()))
        		{
        			//The name is empty for both associations
        			return rolesEquals(assocSrc, assocTgt);
        		}
        		else if(!"".equals(assocSrc.getName()) && !"".equals(assocTgt.getName()))
        		{
        			//The name is not empty for both associations
        			return assocSrc.getName().equals(assocTgt.getName())
        			&& rolesEquals(assocSrc, assocTgt);
        		}
        		else
        		{
        			// The name is empty for one association and not for the other
        			return false;
        		}
        	}
        	// The name is defined for one association and not for the other
        	else if(assocSrc.getName()!= null || assocTgt.getName() != null)
        	{
        		return true;
        	}
        	else
        	{
        		// The names are not defined so we will relay on roles comparaison
        		return rolesEquals(assocSrc, assocTgt);
        	}
        }
        return false;
    }


    /**
     * Test if the roles of two associations are equals : 
     * if a memberEnd of an association is equals to one of the members ends of the other associations
     * and if the other memberEnd of an association is equals to one of the members ends of the other associations.
     * @param assocSrc the association source to test
     * @param assocTgt the association target to test
     * @return true if the roles are equals
     */
	private boolean rolesEquals(Association assocSrc, Association assocTgt) {
		return (assocSrc.getMemberEnds().get(0).equals(assocTgt.getMemberEnds().get(0)) ||
				assocSrc.getMemberEnds().get(0).equals(assocTgt.getMemberEnds().get(1)))
		        && assocSrc.getMemberEnds().get(1).equals(assocTgt.getMemberEnds().get(0)) ||
		        assocSrc.getMemberEnds().get(1).equals(assocTgt.getMemberEnds().get(1));
	}



    private boolean createAssociation(GraphElement graphElementSrc,
			Association association, GraphElement graphElementTgt) 
    {
    	boolean isCreated = false; 
    	isCreated = createAssociationFromTypeToType(association, graphElementSrc, graphElementTgt);
		// if the ends of the association have different type, call again the method to be sure to draw the association.
		if(!association.getMemberEnds().get(0).getType().equals(association.getMemberEnds().get(1).getType()))
		{
			isCreated = createAssociationFromTypeToType(association, graphElementTgt, graphElementSrc);
		}
		
		return isCreated;
	}
    
    
    private boolean createAssociationFromTypeToType(Association association, GraphElement graphElementSrc, GraphElement graphElementTgt)
    { 	
    	Type eObjectSrc = (Type) Utils.getElement(graphElementSrc);
        Type eObjectTgt = (Type) Utils.getElement(graphElementTgt);
    	
    	if (eObjectSrc.equals(association.getMemberEnds().get(0).getType()) 
				&& eObjectTgt.equals(association.getMemberEnds().get(1).getType()))
    	{	
			// check if the relation does not exist yet
			List<GraphEdge> existing = getExistingEdges(graphElementSrc, graphElementTgt, Association.class);
			if (!isAlreadyPresent(existing, association))
			{
				ICreationUtils factory = getModeler().getActiveConfiguration().getCreationUtils();
				GraphElement edge = factory.createGraphElement(association);
				if (edge instanceof GraphEdge)
				{
					AssociationEdgeCreationCommand cmd = new AssociationEdgeCreationCommand(getEditDomain(), (GraphEdge) edge, graphElementSrc, false);
					cmd.setTarget(graphElementTgt);
					add(cmd);
					return true;
				}

			}
		
		}
    	return false;
    }


}