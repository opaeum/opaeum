/***********************************************************************
 * Copyright (c) 2008 Anyware Technologies
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Anyware Technologies - initial API and implementation
 * 	  Thibault Landre (Atos Origin) - refactor the way to restore dependency
 *    Sebastien Gabel (CS) - Bug fixes #3458 and #3459
 **********************************************************************/
package org.topcased.modeler.uml.alldiagram.commands;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Usage;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.utils.Utils;

/**
 * NamedElement restore connection command<br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class NamedElementRestoreConnectionCommand extends ElementRestoreConnectionCommand
{
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param part the EditPart that is restored
     * @generated
     */
    public NamedElementRestoreConnectionCommand(EditPart part)
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

        if (eObjectSrc instanceof NamedElement)
        {
            for (GraphElement graphElementTgt : getAllGraphElements())
            {
                EObject eObjectTgt = Utils.getElement(graphElementTgt);
                boolean autoRef = graphElementTgt.equals(graphElementSrc);
                if (eObjectTgt instanceof NamedElement)
                {
                    // if the element is the source of the edge or if it is
                    // the target and that the SourceTargetCouple is reversible
                    createUsageFromNamedElementToNamedElement(graphElementSrc, graphElementTgt);
                    createDependencyFromNamedElementToNamedElement(graphElementSrc, graphElementTgt);

                    if (!autoRef)
                    {
                        // if element is the target of the edge or if it is the
                        // source and that the SourceTargetCouple is reversible
                        createUsageFromNamedElementToNamedElement(graphElementTgt, graphElementSrc);
                        createDependencyFromNamedElementToNamedElement(graphElementTgt, graphElementSrc);
                    }
                }
            }
        }
    }

    /**
     * Creates {@link Usage} relationships.
     * 
     * @param srcNode the source node
     * @param targetNode the target node
     * @generated NOT
     */
    private void createUsageFromNamedElementToNamedElement(GraphElement srcNode, GraphElement targetNode)
    {
        NamedElement sourceObject = (NamedElement) Utils.getElement(srcNode);
        NamedElement targetObject = (NamedElement) Utils.getElement(targetNode);

        for (Usage usage : EcoreUtil.<Usage> getObjectsByType(sourceObject.getClientDependencies(), UMLPackage.Literals.USAGE))
        {
            if (usage.getSuppliers().contains(targetObject) && usage.getClients().contains(sourceObject))
            {
                // check if the relation does not exists yet
                List<GraphEdge> existing = getExistingEdges(srcNode, targetNode, Dependency.class);
                if (!isAlreadyPresent(existing, usage))
                {
                    ICreationUtils factory = getModeler().getActiveConfiguration().getCreationUtils();
                    // restore the link with its default presentation
                    GraphElement edge = factory.createGraphElement(usage);
                    if (edge instanceof GraphEdge)
                    {
                        UsageEdgeCreationCommand cmd = new UsageEdgeCreationCommand(getEditDomain(), (GraphEdge) edge, srcNode, false);
                        cmd.setTarget(targetNode);
                        add(cmd);
                    }
                }
            }
        }
    }

    /**
     * Creates {@link Dependency} relationships and only this kind (not Usage or InterfaceRealization).
     * 
     * @param srcNode the source node
     * @param targetNode the target node
     * @generated NOT
     */
    private void createDependencyFromNamedElementToNamedElement(GraphElement srcNode, GraphElement targetNode)
    {
        NamedElement sourceObject = (NamedElement) Utils.getElement(srcNode);
        NamedElement targetObject = (NamedElement) Utils.getElement(targetNode);

        for (Dependency dependency : sourceObject.getClientDependencies())
        {
            // we want to process only the uml:Dependency and not other subtypes (Usage, InterfaceRealization,...)
            if (dependency.eClass() == UMLPackage.Literals.DEPENDENCY)
            {
                if (dependency.getSuppliers().contains(targetObject) && dependency.getClients().contains(sourceObject))
                {
                    // check if the relation does not exists yet
                    List<GraphEdge> existing = getExistingEdges(srcNode, targetNode, Dependency.class);
                    if (!isAlreadyPresent(existing, dependency))
                    {
                        ICreationUtils factory = getModeler().getActiveConfiguration().getCreationUtils();
                        // restore the link with its default presentation
                        GraphElement edge = factory.createGraphElement(dependency);
                        if (edge instanceof GraphEdge)
                        {
                            DependencyEdgeCreationCommand cmd = new DependencyEdgeCreationCommand(getEditDomain(), (GraphEdge) edge, srcNode, false);
                            cmd.setTarget(targetNode);
                            add(cmd);
                        }
                    }
                }
            }
        }
    }
}