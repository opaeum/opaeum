/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.commands;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gef.EditDomain;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
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
 * @generated
 */
public class AssociationEdgeCreationCommand extends CreateTypedEdgeCommand
{
    protected Association association;

    protected Type sourceObject;

    protected Type targetObject;

    protected AssociationHelper associationHelper;

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
        associationHelper = new AssociationHelper(association);
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
	            manageSecondmemberEnd(lowTargetName);
	        }
	  
	        int cpt = associationHelper.findName(getEditDomain(), getContainerEObject(), association);
	        
	        association.setName(associationHelper.getAssociationName(cpt));
	        
	        // If the association is composite, then move the corresponding property to its classifier.
	        if(AggregationKind.COMPOSITE_LITERAL.equals(associationHelper.getAssociationSecondEndAggregationKind()) && sourceObject instanceof Classifier)
	        {
	            // The property to move from the association to the classifier
	            Property property = associationHelper.getAssociationSecondMemberEnd();
	            
	            // Remove the property from the association
	            association.getOwnedEnds().remove(property);
	            
	            // Add the property to the classifier
	            Classifier classifier = (Classifier)sourceObject;
	            EReference newContainmentFeature = getClassifierContainingFeature(classifier, property);
	            Utils.getOwnerList(classifier, newContainmentFeature).add(property);        
	        }
        }
        super.execute();
    }

    protected void manageSecondmemberEnd(String lowTargetName)
    {
        associationHelper.getAssociationSecondMemberEnd().setType(targetObject);     
        associationHelper.getAssociationSecondMemberEnd().setName(lowTargetName);
    }
    
    private EReference getClassifierContainingFeature(Classifier classifier, Property child)
    {
        EReference eReference = null;
        if (classifier != null && child != null)
        {
            for (EReference eRef : classifier.eClass().getEAllContainments())
            {
                EClassifier eclass = eRef.getEType();
                if (eclass.isInstance(child))
                {               
                    eReference = eRef;
                    break;            
                }
            }
        }
        return eReference;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    protected void redoModel()
    {
        // Add the Association to the current package
        getAssociationContainerList().add(association);
        
        if(AggregationKind.COMPOSITE_LITERAL.equals(associationHelper.getAssociationSecondEndAggregationKind()) && sourceObject instanceof Classifier)
        {
            // The property to move from the association to the classifier
            Property property = associationHelper.getAssociationSecondMemberEnd();
            
            // Add the property to the classifier
            Classifier classifier = (Classifier)sourceObject;
            EReference newContainmentFeature = getClassifierContainingFeature(classifier, property);
            Utils.getOwnerList(classifier, newContainmentFeature).add(property);        
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
        getAssociationContainerList().remove(association);
        
        if(AggregationKind.COMPOSITE_LITERAL.equals(associationHelper.getAssociationSecondEndAggregationKind()) && sourceObject instanceof Classifier)
        {
            // The property to move from the association to the classifier
            Property property = associationHelper.getAssociationSecondMemberEnd();
            
            // Add the property to the classifier
            Classifier classifier = (Classifier)sourceObject;
            EReference newContainmentFeature = getClassifierContainingFeature(classifier, property);
            Utils.getOwnerList(classifier, newContainmentFeature).remove(property);        
        }

    }

    /**
     * Get the container list in which the association will be owned
     * @return
     */
    protected EList getAssociationContainerList()
    {
    	// Add the Association to the current package
    	if (getContainerEObject() instanceof Package) {
			Package pack = (Package) getContainerEObject();
			return pack.getPackagedElements();
		}
    	else
    	{
    		return sourceObject.getPackage().getPackagedElements();
    	}
    }

}