/*****************************************************************************
 * Copyright (c) 2010 ATOS ORIGIN INTEGRATION.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Alexia ALLANIC (ATOS ORIGIN INTEGRATION) alexia.allanic@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/
package org.topcased.modeler.uml.classdiagram.util;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.internal.ordering.IOrderAlgorithm;

public class ClassOrderingAlgorithm implements IOrderAlgorithm
{
    public List<EObject> sortChildren(EObject eobject)
    {
        List<EObject> generalizations = new LinkedList<EObject>();
        List<EObject> dependencies = new LinkedList<EObject>();
        List<EObject> aggregCompos = new LinkedList<EObject>();
        List<EObject> sortedList = new LinkedList<EObject>();
        List<EObject> classes = new LinkedList<EObject>();
        if (eobject instanceof Package)
        {
            Package ePackage = (Package) eobject;
            add(ePackage, sortedList, UMLPackage.Literals.PACKAGE);
            add(ePackage, sortedList, UMLPackage.Literals.INTERFACE);
            addClasses(classes, generalizations, dependencies, aggregCompos, sortedList, ePackage);
            addOthersClassifier(ePackage, sortedList);
            addRelationShips(ePackage, sortedList);
            Set<EObject> resultSet = new HashSet<EObject>(sortedList);
            for (EObject content : ePackage.eContents())
            {
                if (!resultSet.contains(content))
                {
                    sortedList.add(content);
                }
            }
        }
        return sortedList;
    }

    /**
     * Add relation ships into sortedList
     * @param ePackage the container of relationships
     * @param sortedList the sorted list
     */
    private void addRelationShips(Package ePackage, List<EObject> sortedList)
    {
        for (EObject e : ePackage.eContents())
        {
            if (e instanceof Relationship && !sortedList.contains(e))
            {
                sortedList.add(e);
            }
        }        
    }

    /**
     * Add other classifiers contained in the ePackage into the sorted list
     * @param ePackage the container
     * @param sortedList the sorted list
     */
    private void addOthersClassifier(Package ePackage, List<EObject> sortedList)
    {
        for (EObject e : ePackage.eContents())
        {
            if (e instanceof Classifier && !sortedList.contains(e))
            {
                sortedList.add(e);
            }
        }
    }

    /**
     * Add classes into sorted list contained in ePackage
     * @param classes the list of classes
     * @param generalizations the list of generalizations
     * @param dependencies the list of dependencies
     * @param aggregCompos the list of aggregations and compositions
     * @param sortedList the sorted list
     * @param ePackage the container
     */
    private void addClasses(List<EObject> classes, List<EObject> generalizations, List<EObject> dependencies, List<EObject> aggregCompos, List<EObject> sortedList, Package ePackage)
    {
        for (EObject child : ePackage.eContents())
        {
            if (child instanceof Class)
            {
                classes.add(child);
                Class eclass = (Class) child;
                for (Generalization generalization : eclass.getGeneralizations())
                {
                    if (!generalizations.contains(generalization.getGeneral()))
                    {
                        generalizations.add(generalization.getGeneral());
                    }
                }
                for (Dependency dependency : eclass.getClientDependencies())
                {
                    if (!dependencies.contains(dependency.getSuppliers()))
                    {
                        dependencies.addAll(dependency.getSuppliers());
                    }
                }
                for (Association a : eclass.getAssociations())
                { 
                    for (Property p : a.getMemberEnds())
                    {
                        if (p.getAggregation().equals(AggregationKind.COMPOSITE_LITERAL) || p.getAggregation().equals(AggregationKind.SHARED_LITERAL))
                        {
                            if(!aggregCompos.contains(p.eContainer())) {
                                aggregCompos.add(p.eContainer());
                            }
                        }
                    }
                }
            }
        }
        addList(generalizations, sortedList);
        addList(dependencies, sortedList);
        addList(aggregCompos, sortedList);
        for (EObject eclass : classes)
        {
            if (!sortedList.contains(eclass))
            {
                sortedList.add(eclass);
            }
        }
    }


    public boolean canOrder(EObject context)
    {
        return context instanceof Package;
    }
    
    /**
     * Add list inList into outList
     * @param inList the in list
     * @param outList the out list
     */
    public void addList(List<EObject> inList, List<EObject> outList) {
        for(EObject e : inList) {
            if(!outList.contains(e)) {
                outList.add(e);
            }
        }
    }
    
    /**
     * Add all child of packageContainer in result witch is an eclass
     * @param packageContainer the package container
     * @param result the ordered list
     * @param eclass the type of eObject to insert
     */
    private void add(Package packageContainer, List<EObject> result, EClass eclass)
    {
        for (EObject e : packageContainer.eContents())
        {
            if (eclass.equals(e.eClass()))
            {
                result.add(e);
            }
        }
        
    }
    
    /**
     * Return true if element is contained in parent
     * @param parent
     * @param element
     * @return true if element is child of parent
     */
    public boolean isChild(EObject parent, EObject element) {
        return element.eContainer().equals(parent);
    }
    
}
