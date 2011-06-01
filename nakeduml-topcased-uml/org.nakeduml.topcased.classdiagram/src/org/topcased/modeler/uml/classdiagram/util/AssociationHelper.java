/*******************************************************************************
 * Copyright (c) 2005, 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *   Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware Technologies),
 *   Nicolas Lalevee (Anyware Technologies) - initial API and implementation,
 *   Thomas Szadel (Atos Origin)
 ******************************************************************************/

package org.topcased.modeler.uml.classdiagram.util;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditDomain;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.VisibilityKind;
import org.topcased.modeler.utils.LabelHelper;

/**
 * An helper class to handle Association parameters more easily
 * 
 * @author <a href="mailto:mathieu.garcia@anyware-tech.com">Mathieu Garcia</a>
 */
public class AssociationHelper
{
    private final Association association;

    /**
     * Constructor
     * 
     * @param association the Association
     */
    public AssociationHelper(Association association)
    {
        this.association = association;
    }

    /**
     * Check if the Association is completely created
     * 
     * @return true if the Association has its two MemberEnds
     */
    public boolean isAssociationReady()
    {
        return association.isBinary();
    }

    /** @return the Association name */
    public String getAssociationName()
    {
        return association.getName();
    }

    // --------------------------------------------
    // The helper methods of the firstEnd Property

    /** @return the Association First End or null if the association is not binary. */
    public Type getAssociationFirstEndClass()
    {
        if (isAssociationReady())
        {
            return association.getMemberEnds().get(0).getType();
        }
        else
        {
            return null;
        }
    }

    /** @return the Association First End role or null if the association is not binary. */
    public String getAssociationFirstEndRole()
    {
        if (isAssociationReady())
        {
            return association.getMemberEnds().get(0).getName();
        }
        else
        {
            return null;
        }
    }

    /** @return true whether the first end is navigable or false if the association is not binary. */
    public boolean getAssociationFirstEndIsNavigable()
    {
        if (isAssociationReady())
        {
            return association.getMemberEnds().get(0).isNavigable();
        }
        else
        {
            return false;
        }
    }

    /** @return true whether the first end is ordered or false if the association is not binary. */
    public boolean getAssociationFirstEndIsOrdered()
    {
        if (isAssociationReady())
        {
            return association.getMemberEnds().get(0).isOrdered();
        }
        else
        {
            return false;
        }
    }

    /** @return the first end visibility kind or null if the association is not binary. */
    public VisibilityKind getAssociationFirstEndVisibilityKind()
    {
        if (isAssociationReady())
        {
            return association.getMemberEnds().get(0).getVisibility();
        }
        else
        {
            return null;
        }
    }

    /** @return the first end aggregation kind or null if the association is not binary. */
    public AggregationKind getAssociationFirstEndAggregationKind()
    {
        if (isAssociationReady())
        {
            return association.getMemberEnds().get(0).getAggregation();
        }
        else
        {
            return null;
        }
    }

    /** @return the first end lower bound or null if the association is not binary. */
    public String getAssociationFirstEndLowerBound()
    {
        if (isAssociationReady())
        {
            return Integer.toString(association.getMemberEnds().get(0).getLower());
        }
        else
        {
            return null;
        }
    }

    /** @return the first end upper bound or null if the association is not binary. */
    public String getAssociationFirstEndUpperBound()
    {
        if (isAssociationReady())
        {
            int upperBound = association.getMemberEnds().get(0).getUpper();

            return LiteralUnlimitedNatural.UNLIMITED == upperBound ? "*" : Integer.toString(upperBound);
        }
        else
        {
            return null;
        }
    }

    /** @return the Association First Member End property */
    public Property getAssociationFirstMemberEnd()
    {
        if (isAssociationReady())
        {
            return association.getMemberEnds().get(0);
        }
        return null;
    }

    /**
     * @return true whether the association end is contained by the association or false if the association is not
     *         binary.
     */
    public boolean isAssociationFirstEndAssociationContained()
    {
        if (isAssociationReady())
        {
            return association.getOwnedEnds().contains(association.getMemberEnds().get(0));
        }
        else
        {
            return false;
        }
    }

    // ---------------------------------------------
    // The helper methods of the secondEnd Property

    /** @return the Association second end or null if the association is not binary. */
    public Type getAssociationSecondEndClass()
    {
        if (isAssociationReady())
        {
            return association.getMemberEnds().get(1).getType();
        }
        else
        {
            return null;
        }
    }

    /** @return the Association second end role or null if the association is not binary. */
    public String getAssociationSecondEndRole()
    {
        if (isAssociationReady())
        {
            return association.getMemberEnds().get(1).getName();
        }
        else
        {
            return null;
        }
    }

    /** @return true whether the second end is navigable or false if the association is not binary. */
    public boolean getAssociationSecondEndIsNavigable()
    {
        if (isAssociationReady())
        {
            return association.getMemberEnds().get(1).isNavigable();
        }
        else
        {
            return false;
        }
    }

    /** @return true whether the second end is ordered or false if the association is not binary. */
    public boolean getAssociationSecondEndIsOrdered()
    {
        if (isAssociationReady())
        {
            return association.getMemberEnds().get(1).isOrdered();
        }
        else
        {
            return false;
        }
    }

    /** @return the second end visibility kind or null if the association is not binary. */
    public VisibilityKind getAssociationSecondEndVisibilityKind()
    {
        if (isAssociationReady())
        {
            return association.getMemberEnds().get(1).getVisibility();
        }
        else
        {
            return null;
        }
    }

    /** @return the second end aggregation kind or null if the association is not binary. */
    public AggregationKind getAssociationSecondEndAggregationKind()
    {
        if (isAssociationReady())
        {
            return association.getMemberEnds().get(1).getAggregation();
        }
        else
        {
            return null;
        }
    }

    /** @return the second end lower bound or null if the association is not binary. */
    public String getAssociationSecondEndLowerBound()
    {
        if (isAssociationReady())
        {
            return Integer.toString(association.getMemberEnds().get(1).getLower());
        }
        else
        {
            return null;
        }
    }

    /** @return the second end upper bound or null if the association is not binary. */
    public String getAssociationSecondEndUpperBound()
    {
        if (isAssociationReady())
        {
            int upperBound = association.getMemberEnds().get(1).getUpper();

            return LiteralUnlimitedNatural.UNLIMITED == upperBound ? "*" : Integer.toString(upperBound);
        }
        else
        {
            return null;
        }
    }

    /** @return the Association First Member End property */
    public Property getAssociationSecondMemberEnd()
    {
        if (isAssociationReady())
        {
            return association.getMemberEnds().get(1);
        }
        return null;
    }

    /**
     * @return true whether the association end is contained by the association or false if the association is not
     *         binary.
     */
    public boolean isAssociationSecondEndAssociationContained()
    {
        if (isAssociationReady())
        {
            return association.getOwnedEnds().contains(association.getMemberEnds().get(1));
        }
        else
        {
            return false;
        }
    }

    public boolean isAssociationComposite()
    {
        return AggregationKind.COMPOSITE_LITERAL.equals(getAssociationSecondEndAggregationKind());
    }

    /**
     * This returns a number to give to the new association
     * 
     * @param editDomain the EditDomain
     * @param parentEObject the parent EObject
     * @param childEObject the new EObject to add
     * @return the number for the LabelFeaturel
     */
    public int findName(EditDomain editDomain, EObject parentEObject, EObject childEObject)
    {
        int cpt = 1;
        while (!isNameAvailable(editDomain, parentEObject, childEObject, cpt))
        {
            cpt++;
        }
        return cpt;
    }

    /**
     * Check if a name is available
     * 
     * @param editDomain the EditDomain
     * @param parentEObject the parent EObject
     * @param childEObject the new EObject to add
     * @param currentCpt the current cpt
     * @return true if the name is available
     */
    private boolean isNameAvailable(EditDomain editDomain, EObject parentEObject, EObject childEObject, int currentCpt)
    {
        EList<EObject> list = parentEObject.eContents();
        for (EObject eobject : list)
        {
            // check if the current child is the same type of the childEObject
            if (childEObject.eClass().getName().equals(eobject.eClass().getName()))
            {
                if (getAssociationName(currentCpt).equals(LabelHelper.INSTANCE.getName(editDomain, eobject)))
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * get a name of association
     * 
     * @param currentCpt the current currentCpt
     * @return the association name
     */
    public String getAssociationName(int currentCpt)
    {
        if (getAssociationFirstMemberEnd() != null && getAssociationFirstMemberEnd().getName() != null && getAssociationSecondMemberEnd() != null && getAssociationSecondMemberEnd().getName() != null)
        {
            String nameToMatch = "A_" + "<" + getAssociationFirstMemberEnd().getName() + ">" + "_" + "<" + getAssociationSecondMemberEnd().getName() + ">";
            if (currentCpt != 1)
            {
                return nameToMatch + "_" + currentCpt;
            }
            return nameToMatch;
        }
        return "Association" + currentCpt;
    }

}
