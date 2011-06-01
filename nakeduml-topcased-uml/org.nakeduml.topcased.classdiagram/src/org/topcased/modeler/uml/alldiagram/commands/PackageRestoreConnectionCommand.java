/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.alldiagram.commands;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.PackageMerge;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.utils.Utils;

/**
 * Package restore connection command<br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class PackageRestoreConnectionCommand extends NamedElementRestoreConnectionCommand
{
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param part the EditPart that is restored
     * @generated
     */
    public PackageRestoreConnectionCommand(EditPart part)
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

        if (eObjectSrc instanceof Package)
        {
        	for(GraphElement graphElementTgt : getAllGraphElements())
        	{
        		EObject eObjectTgt = Utils.getElement(graphElementTgt);

        		if (eObjectTgt instanceof Package)
        		{
        			// if the elt is the source of the edge or if it is
        			// the target and that the SourceTargetCouple is
        			// reversible
        			createPackageImportFromPackageToPackage(graphElementSrc, graphElementTgt);
        			// if elt is the target of the edge or if it is the
        			// source and that the SourceTargetCouple is
        			// reversible
        			createPackageImportFromPackageToPackage(graphElementTgt, graphElementSrc);
        		}

        		if (eObjectTgt instanceof Package)
        		{
        			// if the elt is the source of the edge or if it is
        			// the target and that the SourceTargetCouple is
        			// reversible
        			createPackageMergeFromPackageToPackage(graphElementSrc, graphElementTgt);
        			// if elt is the target of the edge or if it is the
        			// source and that the SourceTargetCouple is
        			// reversible
        			createPackageMergeFromPackageToPackage(graphElementTgt, graphElementSrc);
        		}


        	}
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param srcElt the source element
     * @param targetElt the target element
     * @generated NOT
     */
    private void createPackageImportFromPackageToPackage(GraphElement srcElt, GraphElement targetElt)
    {
        Package sourceObject = (Package) Utils.getElement(srcElt);
        Package targetObject = (Package) Utils.getElement(targetElt);

        for (PackageImport edgeObject : sourceObject.getPackageImports())
        {
            if (targetObject.equals(edgeObject.getImportedPackage()))
            {
                // check if the relation does not exists yet
                List<GraphEdge> existing = getExistingEdges(srcElt, targetElt, PackageImport.class);
                if (!isAlreadyPresent(existing, edgeObject))
                {
                    ICreationUtils factory = getModeler().getActiveConfiguration().getCreationUtils();
                    // restore the link with its default presentation
                    GraphElement edge = factory.createGraphElement(edgeObject);
                    if (edge instanceof GraphEdge)
                    {
                        PackageImportEdgeCreationCommand cmd = new PackageImportEdgeCreationCommand(getEditDomain(), (GraphEdge) edge, srcElt, false);
                        cmd.setTarget(targetElt);
                        add(cmd);
                    }
                }
            }
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param srcElt the source element
     * @param targetElt the target element
     * @generated NOT
     */
    private void createPackageMergeFromPackageToPackage(GraphElement srcElt, GraphElement targetElt)
    {
        Package sourceObject = (Package) Utils.getElement(srcElt);
        Package targetObject = (Package) Utils.getElement(targetElt);

        for (PackageMerge edgeObject : sourceObject.getPackageMerges())
        {
            if (targetObject.equals(edgeObject.getMergedPackage()) && sourceObject.equals(edgeObject.getReceivingPackage()))
            {
                // check if the relation does not exists yet
                List<GraphEdge> existing = getExistingEdges(srcElt, targetElt, PackageMerge.class);
                if (!isAlreadyPresent(existing, edgeObject))
                {
                    ICreationUtils factory = getModeler().getActiveConfiguration().getCreationUtils();
                    // restore the link with its default presentation
                    GraphElement edge = factory.createGraphElement(edgeObject);
                    if (edge instanceof GraphEdge)
                    {
                        PackageMergeEdgeCreationCommand cmd = new PackageMergeEdgeCreationCommand(getEditDomain(), (GraphEdge) edge, srcElt, false);
                        cmd.setTarget(targetElt);
                        add(cmd);
                    }
                }
            }
        }
    }

}