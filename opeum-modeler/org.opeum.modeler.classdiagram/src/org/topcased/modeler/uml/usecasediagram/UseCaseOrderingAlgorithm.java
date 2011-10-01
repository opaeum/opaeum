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
package org.topcased.modeler.uml.usecasediagram;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;
import org.topcased.modeler.internal.ordering.IOrderAlgorithm;

public class UseCaseOrderingAlgorithm implements IOrderAlgorithm
{

    public List<EObject> sortChildren(EObject eobject)
    {
        List<EObject> result = new LinkedList<EObject>();
        UseCaseComparator ucComparator = new UseCaseComparator();
        if (eobject instanceof Package)
        {
            Package ePackage = (Package) eobject;
            add(ePackage, result, UMLPackage.Literals.PACKAGE);
            add(ePackage, result, UMLPackage.Literals.ACTOR);
            addUseCasesWithNoLinks(ePackage, result);
            List<UseCase> useCasesList = getOthersUseCases(ePackage, result);
            Collections.sort(useCasesList, ucComparator);
            for(UseCase uc : useCasesList) {
                result.add(uc);
            }
            // Add others eobject
            Set<EObject> resultSet = new HashSet<EObject>(result);
            for (EObject content : ePackage.eContents())
            {
                if (!resultSet.contains(content))
                {
                    result.add(content);
                }
            }
        }
        return result;
    }

    /**
     * Get use cases with extends and/or includes
     * @param ePackage the parent
     * @param result the sorted list
     * @return a use cases list
     */
    private List<UseCase> getOthersUseCases(Package ePackage, List<EObject> result)
    {
        List<UseCase> useCasesList = new LinkedList<UseCase>();
        for(EObject e : ePackage.eContents()){
            if(e instanceof UseCase && !result.contains(e)) {
                UseCase useCase = (UseCase) e;
                useCasesList.add(useCase);
            }
        }
        return useCasesList;
    }
    
    /**
     * This class compare use cases by number of extends and includes 
     */
    private class UseCaseComparator implements Comparator<UseCase>{

        public int compare(UseCase o1, UseCase o2)
        {
            return getNbLinks(o2).compareTo(getNbLinks(o1));
        }
        
        private Integer getNbLinks(UseCase uc) {
            return uc.getExtends().size() + uc.getIncludes().size();
        }
        
    }

    /**
     * Add to the result list all use cases without links
     * @param ePackage the container
     * @param result the ordered list
     */
    private void addUseCasesWithNoLinks(Package ePackage, List<EObject> result)
    {
        for(EObject e : ePackage.eContents()) {
            if(e instanceof UseCase) {
                UseCase useCase = (UseCase) e;
                if(useCase.getExtends().isEmpty() && useCase.getIncludes().isEmpty()) {
                    result.add(useCase);
                }
            }
        }
    }

    /**
     * Add all child of ePackage in result witch is an eclass
     * @param ePackage the package container
     * @param result the ordered list
     * @param eclass the type of eObject to insert
     */
    private void add(Package ePackage, List<EObject> result, EClass eclass)
    {
        for (EObject e : ePackage.eContents())
        {
            if (eclass.equals(e.eClass()))
            {
                result.add(e);
            }
        }
        
    }
    
    public boolean canOrder(EObject context)
    {
        return context instanceof Package;
    }

}
