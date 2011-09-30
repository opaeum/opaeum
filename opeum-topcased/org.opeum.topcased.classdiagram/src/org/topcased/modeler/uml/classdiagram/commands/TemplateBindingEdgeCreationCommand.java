/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.commands;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditDomain;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.TemplateSignature;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.utils.Utils;

/**
 * TemplateBinding edge creation command
 * 
 * @generated
 */
public class TemplateBindingEdgeCreationCommand extends CreateTypedEdgeCommand
{

    /**
     * @param domain the edit domain
     * @param newObj the graph edge of the new connection
     * @param src the graph element of the source
     * @generated
     */
    public TemplateBindingEdgeCreationCommand(EditDomain domain, GraphEdge newObj, GraphElement src)
    {
        this(domain, newObj, src, true);
    }

    /**
     * @param domain the edit domain
     * @param newObj the graph edge of the new connection
     * @param src the graph element of the source
     * @param needModelUpdate set it to true if the model need to be updated
     * @generated
     */
    public TemplateBindingEdgeCreationCommand(EditDomain domain, GraphEdge newObj, GraphElement src,
            boolean needModelUpdate)
    {
        super(domain, newObj, src, needModelUpdate);
    }

    /**
     * @generated NOT
     */
    @SuppressWarnings("unchecked")
    protected void redoModel()
    {
        EObject containerEObject = getContainerEObject();

        // Handle the storing of the edge
        if (containerEObject != null && !"".equals(getSrcTgtData().getContainerRef()))
        {
            EStructuralFeature feature = containerEObject.eClass().getEStructuralFeature(getSrcTgtData().getContainerRef());
            if (feature.isMany())
            {
                EList containerList = Utils.getOwnerList(containerEObject, feature);
                if (containerList != null)
                {
                    containerList.add(Utils.getElement(getEdge()));
                }
            }
            else
            {
                containerEObject.eSet(feature, Utils.getElement(getEdge()));
            }
        }
        
        EObject targetObject = Utils.getElement(getTarget());
        TemplateSignature targetSignature = ((Classifier) targetObject).getOwnedTemplateSignature();

        setReference(Utils.getElement(getEdge()), Utils.getElement(getSource()), getSrcTgtData().getRefEdgeToSource());
        setReference(Utils.getElement(getEdge()), targetSignature, getSrcTgtData().getRefEdgeToTarget());
        setReference(Utils.getElement(getSource()), Utils.getElement(getEdge()), getSrcTgtData().getRefSourceToEdge());
        //setReference(Utils.getElement(getSource()), targetSignature, getSrcTgtData().getRefSourceToTarget());
        setReference(Utils.getElement(getTarget()), Utils.getElement(getEdge()), getSrcTgtData().getRefTargetToEdge());
        //setReference(targetSignature, Utils.getElement(getSource()), getSrcTgtData().getRefTargetToSource());

        if (containerEObject != null && !"".equals(getSrcTgtData().getContainerRef()))
        {
            initName(containerEObject);
        }
    }

    /**
     * @generated NOT
     */
    @SuppressWarnings("unchecked")
    protected void undoModel()
    {
        EObject containerEObject = getContainerEObject();

        // Remove the edge from the container
        if (containerEObject != null && getSrcTgtData().getContainerRef() != null)
        {
            EStructuralFeature feature = containerEObject.eClass().getEStructuralFeature(getSrcTgtData().getContainerRef());
            if (feature.isMany())
            {
                EList containerList = Utils.getOwnerList(containerEObject, feature);
                if (containerList != null)
                {
                    containerList.remove(Utils.getElement(getEdge()));
                }
            }
            else
            {
                containerEObject.eUnset(feature);
            }
        }
        
        EObject targetObject = Utils.getElement(getTarget());
        TemplateSignature targetSignature = ((Classifier) targetObject).getOwnedTemplateSignature();
        
        unsetReference(Utils.getElement(getEdge()), Utils.getElement(getSource()), getSrcTgtData().getRefEdgeToSource());
        unsetReference(Utils.getElement(getEdge()), targetSignature, getSrcTgtData().getRefEdgeToTarget());
        unsetReference(Utils.getElement(getSource()), Utils.getElement(getEdge()), getSrcTgtData().getRefSourceToEdge());
        //unsetReference(Utils.getElement(getSource()), Utils.getElement(getTarget()), getSrcTgtData().getRefSourceToTarget());
        unsetReference(Utils.getElement(getTarget()), Utils.getElement(getEdge()), getSrcTgtData().getRefTargetToEdge());
        //unsetReference(Utils.getElement(getTarget()), Utils.getElement(getSource()), getSrcTgtData().getRefTargetToSource());
    }
    
    @SuppressWarnings("unchecked")
    @Override
    protected void unsetReference(EObject objectUpdated, EObject objectReferenced, String featureName)
    {
        if (featureName != null && !"".equals(featureName))
        {
            EStructuralFeature sourceFeature = objectUpdated.eClass().getEStructuralFeature(featureName);
            if (sourceFeature.isMany())
            {
                EList sourceList = Utils.getOwnerList(objectUpdated, sourceFeature);
                Collection newValue = new ArrayList();
                if (sourceList != null)
                {
                    newValue.addAll(sourceList);
                }
                newValue.remove(objectReferenced);
                objectUpdated.eSet(sourceFeature, newValue);
            }
            else
            {
                objectUpdated.eUnset(sourceFeature);
            }
            //restoreReferenceContainer(objectReferenced);
        }
    }

}