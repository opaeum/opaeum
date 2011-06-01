/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.commands;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditDomain;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.utils.LabelHelper;
import org.topcased.modeler.utils.Utils;

/**
 * Instance specification link creation command<br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @author <a href="mailto:thomas.szadel@atosorigin.com">Thomas Szadel</a>
 */
public class InstanceSpecificationLinkEdgeCreationCommand extends CreateTypedEdgeCommand
{
    /** The link. */
    private InstanceSpecification instanceSpecification;

    /** The source object. */
    private InstanceSpecification sourceObject;

    /** The target object. */
    private InstanceSpecification targetObject;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param domain the edit domain
     * @param newObj the graph edge of the new connection
     * @param src the graph element of the source
     * @generated
     */
    public InstanceSpecificationLinkEdgeCreationCommand(EditDomain domain, GraphEdge newObj, GraphElement src)
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
    public InstanceSpecificationLinkEdgeCreationCommand(EditDomain domain, GraphEdge newObj, GraphElement src, boolean needModelUpdate)
    {
        super(domain, newObj, src, needModelUpdate);
    }

    /**
     * Create the model objects that will be associated with the Association
     * 
     * @see org.topcased.modeler.commands.CreateGraphEdgeCommand#execute()
     */
    @Override
    public void execute()
    {
        // Create the association and add it to the current package
        instanceSpecification = (InstanceSpecification) Utils.getElement(getEdge());

        // Retrieve source object and target object
        sourceObject = (InstanceSpecification) Utils.getElement(getSource());
        targetObject = (InstanceSpecification) Utils.getElement(getTarget());

        // Creates the 2 slots
        Slot srcSlot = instanceSpecification.createSlot();
        InstanceValue srcValue = (InstanceValue) srcSlot.createValue(null, null, UMLPackage.eINSTANCE.getInstanceValue());
        srcValue.setInstance(sourceObject);
        if (!sourceObject.getClassifiers().isEmpty())
        {
            srcValue.setType(sourceObject.getClassifiers().get(0));
        }
        Slot targetSlot = instanceSpecification.createSlot();
        InstanceValue targetValue = (InstanceValue) targetSlot.createValue(null, null, UMLPackage.eINSTANCE.getInstanceValue());
        targetValue.setInstance(targetObject);
        if (!targetObject.getClassifiers().isEmpty())
        {
            targetValue.setType(targetObject.getClassifiers().get(0));
        }

        super.execute();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected void redoModel()
    {
        // Add the instance to the current package
        EObject owner = getContainerEObject();
        Package pack = null;
        if (owner instanceof Package)
        {
            pack = (Package) owner;
        }
        else
        {
            pack =  sourceObject.getNearestPackage();
        }
        pack.getNearestPackage().getPackagedElements().add(instanceSpecification);

        // init the name of the association contained by the package
        String curName = LabelHelper.INSTANCE.getName(getEditDomain(), instanceSpecification);
        if (curName == null || curName.length() == 0)
        {
            LabelHelper.INSTANCE.initName(getEditDomain(), Utils.getElement(getEdge()).eContainer(), instanceSpecification);
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected void undoModel()
    {
        // Remove the instance from the package
        sourceObject.getNearestPackage().getPackagedElements().remove(instanceSpecification);
    }

}