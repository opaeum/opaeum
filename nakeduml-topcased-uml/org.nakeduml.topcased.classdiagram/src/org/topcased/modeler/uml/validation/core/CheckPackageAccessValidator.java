/*****************************************************************************
 * 
 * CheckPackageAccessValidator.java
 * 
 * Copyright (c) 2008 Atos Origin.
 *
 * Contributors:
 *  Maxime Nauleau (Atos Origin) maxime.nauleau@atosorigin.com
 *  initial API and implementation to Fix #1557
 *  Vincent Hemery (Atos Origin) vincent.hemery@atosorigin.com
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *****************************************************************************/

package org.topcased.modeler.uml.validation.core;

import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.topcased.validation.core.IValidator;

/**
 * Fix #1557
 * 
 * Check if relations between Classes are valid with relations between Packages. If a Class (source) has some relations
 * with another one (target), at least one of the source's container must have a PackageImport with one of the target's
 * container
 * 
 * @author maxime.nauleau
 */
public class CheckPackageAccessValidator implements IValidator
{

    public static final String DIAGNOSTIC_SOURCE = "org.topcased.validation.core.CheckPackageAccessValidator";

    private static final String DIAGNOSTIC_NO_VISIBILITY_MESSAGE = "A PackageImport is needed from %1s or one of its parents to %2s, because of the %3s relation between %4s and %5s";

    private static final String DIAGNOSTIC_NO_PACKAGE_PARENT_MESSAGE = "The element %s should have a package as a parent, even if not directly.";

    private static final String DIAGNOSTIC_NO_NAVIGABLE_END = "Semantic not defined. The relation %s has no navigability. (Analysis level)";

    /**
     * @see org.topcased.validation.core.IValidator#validate(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.common.util.DiagnosticChain, org.eclipse.core.runtime.IProgressMonitor)
     */
    public boolean validate(EObject object, DiagnosticChain diagnostics, IProgressMonitor monitor)
    {
        Diagnostic lDiagnostic = validate(object);
        diagnostics.addAll(lDiagnostic);
        return true;
    }

    /**
     * Validate the package access on a UML Model
     * 
     * @param pModel
     * @param pProgressMonitor
     * @return
     */
    public Diagnostic validate(EObject pModel)
    {

        BasicDiagnostic lDiagnostic = new BasicDiagnostic(EObjectValidator.DIAGNOSTIC_SOURCE, 0, "Root diagnostic", new Object[] {pModel});

        if (pModel instanceof Package)
        {
            // Check content elements
            try
            {
                validatePackageContent((Package) pModel, pModel, lDiagnostic);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                // not stop
            }
        }

        return getUniqueDiagnostics(lDiagnostic);
    }

    /**
     * Validate the package access on a the elements of a UML Model
     * 
     * @param pRootPackage the UML Model's root package to validate
     * @param pElementToValidate the EObject (and its content) to validate
     * @param pDiagnostic contains errors to report
     * @return
     */
    public Diagnostic validatePackageContent(Package pRootPackage, EObject pElementToValidate, BasicDiagnostic pDiagnostic)
    {
        EList<EObject> lContainedElements = pElementToValidate.eContents();

        // Check if it contains some elements to validate
        for (EObject lContentElt : lContainedElements)
        {
            // The Check is only made for class diagrams' elements
            if (lContentElt instanceof Class || lContentElt instanceof Interface)
            {

                // Check the Associations
                EList<Association> lAssociations = ((Type) lContentElt).getAssociations();
                associationValidAccess(lAssociations, pRootPackage, pDiagnostic);

                // Check the Dependencies
                EList<Dependency> lDependencies = ((Type) lContentElt).getClientDependencies();
                dependencyValidAccess(lDependencies, pRootPackage, pDiagnostic);

                // Check the Generalizations
                EList<Generalization> lGeneralizations = ((Classifier) lContentElt).getGeneralizations();
                generalizationValidAccess(lGeneralizations, pRootPackage, pDiagnostic);

                // Check the InterfaceRealizations
                if (lContentElt instanceof Class)
                {
                    EList<InterfaceRealization> lInterfaceRealizations = ((Class) lContentElt).getInterfaceRealizations();
                    interfaceRealizationValidAccess(lInterfaceRealizations, pRootPackage, pDiagnostic);
                }

                // Check the Operations (Parameters' type)
                EList<Operation> lOperations = ((Classifier) lContentElt).getOperations();
                operationsValidAccess(lOperations, pRootPackage, pDiagnostic);
            }
            else if (lContentElt instanceof Enumeration)
            {
                // Check the Type of the EnumerationLiterals
                EList<EnumerationLiteral> enumLiterals = ((Enumeration) lContentElt).getOwnedLiterals();
                enumerationsValidAccess(enumLiterals, pRootPackage, pDiagnostic);

            }

            // Check on element Properties' Type
            if (lContentElt instanceof Type && !(lContentElt instanceof Enumeration))
            {
                // we do not want a warning on an association property, except if it is an AssociationClass
                if (!(lContentElt instanceof Association) || lContentElt instanceof AssociationClass)
                {
                    EList<Element> ownedElements = ((Type) lContentElt).getOwnedElements();
                    propertiesValidAccess(ownedElements, pRootPackage, pDiagnostic);
                }
            }

            if (!lContentElt.eContents().isEmpty())
            {
                // validate also contained sub elements (even if not directly in a package)
                validatePackageContent(pRootPackage, lContentElt, pDiagnostic);

            }
        }

        return pDiagnostic;
    }

    /**
     * Check the Package access for Associations of a model element. Each navigable end's element is needed and the
     * other end needs visibility on it.
     * 
     * @param pAssociations the list of all Associations (to check) of the element
     * @param pRootPackage the package element containing all the others
     * @param pDiagnostics contains errors to report
     */
    private void associationValidAccess(EList<Association> pAssociations, Package pRootPackage, DiagnosticChain pDiagnostics)
    {

        for (Association assoc : pAssociations)
        {
            EList<Property> lMemberEnds = assoc.getMemberEnds();
            EList<Property> lNavigableEnds = assoc.getNavigableOwnedEnds();

            if (lNavigableEnds.isEmpty())
            {
                // No navigability
                String lMessage = String.format(DIAGNOSTIC_NO_NAVIGABLE_END, assoc.getLabel());
                pDiagnostics.add(new BasicDiagnostic(Diagnostic.INFO, DIAGNOSTIC_SOURCE, 1, lMessage, new Object[] {assoc}));
            }
            for (Property lNeededEnd : lNavigableEnds)
            {
                // Get the Classes/Interfaces which participate to the Association
                Type lNeeded = lNeededEnd.getType();
                Type lNeeder;
                int lIndex = lMemberEnds.indexOf(lNeededEnd);
                if (lIndex == 0)
                {
                    lNeeder = lMemberEnds.get(1).getType();
                }
                else
                {
                    lNeeder = lMemberEnds.get(0).getType();
                }
                checkPackageAccessVisibility(lNeeder, lNeeded, assoc, pRootPackage, pDiagnostics);
            }
        }
    }

    /**
     * Check the Package access for Dependencies of a model element. Clients need to have visibility on the suppliers.
     * 
     * @param pDependencies the list of all Dependencies (to check) of the element
     * @param pRootPackage the package element containing all the others
     * @param pDiagnostics contains errors to report
     */
    private void dependencyValidAccess(EList<Dependency> pDependencies, Package pRootPackage, DiagnosticChain pDiagnostics)
    {

        for (Dependency lDependency : pDependencies)
        {
            EList<NamedElement> lClients = lDependency.getClients();
            EList<NamedElement> lSuppliers = lDependency.getSuppliers();

            for (NamedElement lClient : lClients)
            {
                for (NamedElement lSupplier : lSuppliers)
                {
                    checkPackageAccessVisibility(lClient, lSupplier, lDependency, pRootPackage, pDiagnostics);

                }
            }
        }
    }

    /**
     * Check the Package access for Generalizations of a model element. The specific classifier needs to have visibility
     * on the general classifier
     * 
     * @param pGeneralizations the list of all Generalizations (to check) of the element
     * @param pRootPackage the package element containing all the others
     * @param pDiagnostics contains errors to report
     */
    private void generalizationValidAccess(EList<Generalization> pGeneralizations, Package pRootPackage, DiagnosticChain pDiagnostics)
    {

        for (Generalization lGeneralization : pGeneralizations)
        {
            Classifier lGeneral = lGeneralization.getGeneral();
            Classifier lSpecific = lGeneralization.getSpecific();

            // Generalization is only made between two elements of the same type (Class or Interface)
            if (lGeneral instanceof Class && lSpecific instanceof Class)
            {
                checkPackageAccessVisibility((Class) lSpecific, (Class) lGeneral, lGeneralization, pRootPackage, pDiagnostics);
            }
            else if (lGeneral instanceof Interface && lSpecific instanceof Interface)
            {
                checkPackageAccessVisibility((Interface) lSpecific, (Interface) lGeneral, lGeneralization, pRootPackage, pDiagnostics);
            }
        }
    }

    /**
     * Check the Package access for InterfaceRealizations of a model element. Clients need to have visibility on the
     * suppliers.
     * 
     * @param pInterfaceRealizations the list of all InterfaceRealizations (to check) of the element
     * @param pRootPackage the package element containing all the others
     * @param pDiagnostics contains errors to report
     */
    private void interfaceRealizationValidAccess(EList<InterfaceRealization> pInterfaceRealizations, Package pRootPackage, DiagnosticChain pDiagnostics)
    {

        for (InterfaceRealization lRealization : pInterfaceRealizations)
        {
            EList<NamedElement> lClients = lRealization.getClients();
            EList<NamedElement> lSuppliers = lRealization.getSuppliers();

            for (NamedElement lClientRealization : lClients)
            {
                for (NamedElement lSupplierRealization : lSuppliers)
                {
                    checkPackageAccessVisibility(lClientRealization, lSupplierRealization, lRealization, pRootPackage, pDiagnostics);
                }
            }
        }
    }

    /**
     * Check the Package access for Properties' type of a model element. An element must have visibility on its
     * Properties types.
     * 
     * 
     * @param pOwnedElements the list of all elements owned by the checked element
     * @param pRootPackage the package element containing all the others
     * @param pDiagnostics contains errors to report
     */
    private void propertiesValidAccess(EList<Element> pOwnedElements, Package pRootPackage, DiagnosticChain pDiagnostics)
    {

        for (Element lOwnedElt : pOwnedElements)
        {
            if (lOwnedElt instanceof Property)
            {
                // The Property Type
                Type lPropertyType = ((Property) lOwnedElt).getType();

                if (lPropertyType != null)
                {
                    Type lPropertyContainerElement = (Type) lOwnedElt.getOwner();

                    checkPackageAccessVisibility(lPropertyContainerElement, lPropertyType, lOwnedElt, pRootPackage, pDiagnostics);
                }
            }
        }
    }

    /**
     * Check the Package access for Operations of a model element. An element must have visibility on all its
     * operation's parameters' types.
     * 
     * @param pOperations the list of all Operations (to check) of the element
     * @param pRootPackage the package element containing all the others
     * @param pDiagnostics contains errors to report
     */
    private void operationsValidAccess(EList<Operation> pOperations, Package pRootPackage, DiagnosticChain pDiagnostics)
    {

        for (Operation lOperation : pOperations)
        {
            Type lOperationContainerElement = (Type) lOperation.getOwner();

            EList<Parameter> lOperationParams = lOperation.getOwnedParameters();

            for (Parameter lParameter : lOperationParams)
            {
                if (lParameter.getType() != null)
                {
                    checkPackageAccessVisibility(lOperationContainerElement, lParameter.getType(), lOperation, pRootPackage, pDiagnostics);
                }
            }

        }
    }

    /**
     * Check the Package access for EnumerationLiterals of a model element. An Enumeration must have visibility on each
     * of its literals' types.
     * 
     * @param pEnumLiterals the list of all EnumerationLiteral (to check) of the element
     * @param pRootPackage the package element containing all the others
     * @param pDiagnostics contains errors to report
     */
    private void enumerationsValidAccess(EList<EnumerationLiteral> pEnumLiterals, Package pRootPackage, DiagnosticChain pDiagnostics)
    {

        for (EnumerationLiteral lEnumLiteral : pEnumLiterals)
        {

            Type lEnumLiteralContainerElt = (Type) lEnumLiteral.getOwner();

            for (Classifier lEnumLiteralClassifier : lEnumLiteral.getClassifiers())
            {
                checkPackageAccessVisibility(lEnumLiteralContainerElt, lEnumLiteralClassifier, lEnumLiteral, pRootPackage, pDiagnostics);
            }
        }

    }

    /**
     * Needer package hierarchy imports needed package. Search for a PackageImport from the needer or one of its parents
     * to the needed package.
     * 
     * @param pNeederPackage the needer package
     * @param pNeededPackage the needed package
     * @param pRootPackage the root package containing all the others
     * 
     * @return true, if successful
     */
    private boolean neederPackageHierarchyImportsNeededPackage(Package pNeederPackage, Package pNeededPackage, Package pRootPackage)
    {

        Package lNeederParentPackage = pNeederPackage;

        while (lNeederParentPackage != null && lNeederParentPackage != pRootPackage)
        {
            // Search a PackageImport with target
            PackageImport lImport = lNeederParentPackage.getPackageImport(pNeededPackage);

            if (lImport != null)
            {
                // import found in the needer hierarchy
                return true;
            }

            lNeederParentPackage =  lNeederParentPackage.getOwner() == null ? null : lNeederParentPackage.getOwner().getNearestPackage();
        }

        return false;
    }

    /**
     * Check package access visibility. If the needer doesn't have visibility on the needed element, add a waring
     * message to the diagnostics.
     * 
     * Visibility from an element A is given on an element B only if a parent package of A imports B's direct parent
     * package, or if A and B are in the same package.
     * 
     * @param pNeeder the needer
     * @param pNeeded the needed
     * @param pRelation the relation
     * @param pRootPackage the model root
     * @param pDiagnostics the diagnostics
     */
    private void checkPackageAccessVisibility(NamedElement pNeeder, NamedElement pNeeded, EObject pRelation, Package pRootPackage, DiagnosticChain pDiagnostics)
    {
    	if (pNeeder != null && pNeeder.getOwner() != null && pNeeded != null && pNeeded.getOwner() != null) 
    	{

			// Get the package containers of the Classes/Interfaces
			Package lNeederParentPackage = pNeeder.getOwner()
					.getNearestPackage();
			Package lNeededParentPackage = pNeeded.getOwner()
					.getNearestPackage();

			// Check if Classes and Packages relations are valid
			if (lNeededParentPackage == null) 
			{
				// element should be in a package
				String lMessage = String
						.format(DIAGNOSTIC_NO_PACKAGE_PARENT_MESSAGE, pNeeded
								.getName());
				pDiagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE, 1, lMessage,
						new Object[] { pNeeded }));
			} 
			else if (lNeederParentPackage == null) 
			{
				// element should be in a package
				String lMessage = String
						.format(DIAGNOSTIC_NO_PACKAGE_PARENT_MESSAGE, pNeeder
								.getName());
				pDiagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE, 1, lMessage,
						new Object[] { pNeeder }));
			} 
			else if (lNeederParentPackage != lNeededParentPackage) 
			{
				// A parent of the Needer package must import the Needed package
				if (!neederPackageHierarchyImportsNeededPackage(
						lNeederParentPackage, lNeededParentPackage,
						pRootPackage)) {
					String lMessage = String.format(
							DIAGNOSTIC_NO_VISIBILITY_MESSAGE,
							lNeederParentPackage.getName(),
							lNeededParentPackage.getName(), pRelation.eClass()
									.getName(), pNeeder.getName(), pNeeded
									.getName());
					pDiagnostics.add(new BasicDiagnostic(Diagnostic.WARNING,
							DIAGNOSTIC_SOURCE, 1, lMessage,
							new Object[] { pRelation }));
				}
			}
		}
    }

    /**
     * Iterates on all the Diagnostics contained by the BasicDiagnostic and removes those which appears more than once.
     * 
     * @param basicDiagnostic The initial list of Diagnostics
     * @return a Diagnostic with only unique Diagnostics
     */
    private Diagnostic getUniqueDiagnostics(BasicDiagnostic basicDiagnostic)
    {
        BasicDiagnostic result = new BasicDiagnostic(EObjectValidator.DIAGNOSTIC_SOURCE, 0, "Root diagnostic", basicDiagnostic.getData().toArray());

        // Iterate on all the diagnostics found
        Iterator<Diagnostic> itDiag = basicDiagnostic.getChildren().iterator();
        while (itDiag.hasNext())
        {
            Diagnostic currentDiag = (Diagnostic) itDiag.next();
            boolean alreadyExist = false;

            // check if the Diagnostic has not been already added
            for (Iterator<Diagnostic> itUniqueDiag = result.getChildren().iterator(); itUniqueDiag.hasNext() && !alreadyExist;)
            {
                Diagnostic uniqueDiag = (Diagnostic) itUniqueDiag.next();

                // check the equality of the two Diagnostics
                if (currentDiag.getSeverity() == uniqueDiag.getSeverity() && currentDiag.getCode() == uniqueDiag.getCode() && currentDiag.getSource().equals(uniqueDiag.getSource())
                        && currentDiag.getMessage().equals(uniqueDiag.getMessage()))
                {
                    alreadyExist = true;
                }
            }

            if (!alreadyExist)
            {
                result.add(currentDiag);
            }
        }

        return result;
    }

}
