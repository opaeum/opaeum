/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    David Sciamma (Anyware Technologies),
 *    Jacques Lescot (Anyware Technologies) - initial API and implementation
 *    Jeremie Belmudes (Atos Origin) - Fix Bug #3069
 *******************************************************************************/

package org.topcased.modeler.uml;

import java.util.Iterator;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.VisibilityKind;
import org.topcased.modeler.uml.alldiagram.preferences.AllDiagramPreferenceConstants;
import org.topcased.modeler.uml.internal.decorators.StereotypeDecorator;

/**
 * This class computes the text for the UML2 objects.<br>
 * These labels are compliant with the UML2 Superstructure Spec<br>
 * 
 * @author <a href="david.sciamma@anyware-tech.com">David Sciamma</a>
 */
public final class UMLLabel
{
    private UMLLabel()
    {
        // This class could not be instantiated
    }

    /**
     * Returns the multiplicity label
     * 
     * @param multiplicity the multiplicity element
     * @return the associated text
     */
    public static String getMultiplicityText(MultiplicityElement multiplicity)
    {
        StringBuffer text = new StringBuffer();

        int lower = multiplicity.getLower();
        int upper = multiplicity.getUpper();
        if (lower == upper)
        {
            if (lower != 1)
            {
                text.append("[");
                text.append(lower);
                text.append("]");
            }
        }
        else if (upper == -1)
        {
            if (lower == 0)
            {
                text.append("[*]");
            }
            else
            {
                text.append("[");
                text.append(lower);
                text.append("..*]");
            }
        }
        else
        {

            text.append("[");
            text.append(lower);
            text.append("..");
            text.append(upper);
            text.append("]");
        }

        return text.toString();
    }

    /**
     * Get a string representation of all the applied stereotypes on the given UML2 element.<br>
     * Optionally, a preference store can be given to allow the chevron to be disabled.
     * 
     * @param uml2Element an UML2 element
     * @param preferenceStore The preference store (or null if not used).
     * @return a fully qualified string representation or empty string if no applied stereotypes
     */
    public static String getStereotypesNotation(Element uml2Element, IPreferenceStore preferenceStore)
    {
        StringBuffer label = new StringBuffer();

        // A flag indicating that the chevron has to be displayed if
        // there is no icon
        boolean showOnlyChevronIfNoIcon = false;

        if (preferenceStore != null)
        {
            String lPref = preferenceStore.getString(AllDiagramPreferenceConstants.STEREOTYPE_DEFAULT_DISPLAY);
            if ("icon".equals(lPref))
            {
                // If there is no icon in the stereotype, show the chevron
                showOnlyChevronIfNoIcon = true;
            }
            else if (!"chevron".equals(lPref))
            {
                // disable chevron
                return label.toString();
            }
        }

        boolean hasStereotype = false;
        String sep = "";
        for (Stereotype stereotype : uml2Element.getAppliedStereotypes())
        {
            if (!showOnlyChevronIfNoIcon || stereotype.getIcons().isEmpty() || !isCorrectImage(stereotype))
            {
                label.append(sep);
                label.append(stereotype.getName());
                sep = ", ";
                hasStereotype = true;
            }
        }

        if (hasStereotype)
        {
            label.insert(0, "<<");
            label.append(">>");
        }

        return label.toString();
    }

    /**
     * Check the stereotype image
     * 
     * @param stereotype The stereotype
     * @return true if the image attributes and EAnnotation seems correct, else false
     */
    private static boolean isCorrectImage(Stereotype stereotype)
    {
        // if there is no icons there is no image
        if (stereotype.getIcons().isEmpty())
        {
            return false ;
        }
        // if there is no locations and no content, image creation not possible
        else if (stereotype.getIcons().get(0).getLocation() == null && stereotype.getIcons().get(0).getContent() == null)
        {
            return false ;
        }
        // if there is a location, the location has higher priority than content so true
        else if (stereotype.getIcons().get(0).getLocation() != null && stereotype.getIcons().get(0).getLocation().length() > 0)
        {
            return true ;
        }
        // finally check of the content, content only managed if it is a content created by papyrus v1
        else
        {
            return stereotype.getIcons().get(0).getContent() != null && stereotype.getIcons().get(0).getContent().length() > 0 &&  stereotype.getIcons().get(0).getEAnnotation(StereotypeDecorator.getImagePapyrus()) != null ;
        }
        
    }

    /**
     * Get a string representation of all the applied stereotypes on the given UML2 element.<br>
     * 
     * @param uml2Element an UML2 element
     * @return a fully qualified string representation or empty string if no applied stereotypes
     * @deprecated Use {@link UMLLabel#getStereotypesNotation(Element, IPreferenceStore)} instead.
     */
    @Deprecated
    public static String getStereotypesNotation(Element uml2Element)
    {
        return getStereotypesNotation(uml2Element, null);
    }

    /**
     * Return a representation for a given visibility kind
     * 
     * @param visibility the visibily
     * @return a string that represents the visibility kind
     */
    public static String getVisibilityNotation(VisibilityKind visibility)
    {
        String visibilityNotation = "";
        if (visibility != null)
        {
            switch (visibility.getValue())
            {
                case VisibilityKind.PUBLIC:
                    visibilityNotation = "+";
                    break;
                case VisibilityKind.PRIVATE:
                    visibilityNotation = "-";
                    break;
                case VisibilityKind.PROTECTED:
                    visibilityNotation = "#";
                    break;
                case VisibilityKind.PACKAGE:
                    visibilityNotation = "~";
                    break;
                default:
            }
        }
        return visibilityNotation;
    }

    // ---------------
    // Property Label
    // ---------------

    /**
     * Compute a String value used to represent the modifiers properties of a given Property
     * 
     * @param property the Property element
     * @return a string that represents the Property modifiers
     */
    public static String getPropertyModifiersText(Property property)
    {
        // <prop-modifier> ::= �readOnly� | �union� | �subsets� <property-name>
        // | �redefines� <property-name> | �ordered� | �unique� |
        // <prop-constraint>
        StringBuffer text = new StringBuffer();

        append(text, getReadonlyText(property));
        append(text, getUnionText(property));
        append(text, getSubsetsText(property));
        append(text, getRedefinesText(property));
        append(text, getOrderedText(property));
        append(text, getUniqueText(property));
        append(text, getConstraintText());

        return text.toString();
    }

    private static void append(StringBuffer text, String fragment)
    {
        if (fragment != null && !"".equals(fragment))
        {
            if (text.length() > 0)
            {
                text.append(", ");
            }
            text.append(fragment);
        }
    }

    private static String getReadonlyText(Property property)
    {
        StringBuffer text = new StringBuffer();

        if (property.isReadOnly())
        {
            text.append("readonly");
        }

        return text.toString();
    }

    private static String getUnionText(Property property)
    {
        StringBuffer text = new StringBuffer();

        if (property.isDerivedUnion())
        {
            text.append("union");
        }

        return text.toString();
    }

    private static String getSubsetsText(Property property)
    {
        StringBuffer text = new StringBuffer();

        if (property.getSubsettedProperties() != null)
        {
            Iterator<Property> itSubsets = property.getSubsettedProperties().iterator();
            while (itSubsets.hasNext())
            {
                Property currentProperty = itSubsets.next();
                text.append("subsets ");
                text.append(currentProperty.getName());

                if (itSubsets.hasNext())
                {
                    text.append(", ");
                }
            }
        }

        return text.toString();
    }

    private static String getRedefinesText(Property property)
    {
        StringBuffer text = new StringBuffer();

        if (property.getRedefinedProperties() != null)
        {
            Iterator<Property> itRedefines = property.getRedefinedProperties().iterator();
            while (itRedefines.hasNext())
            {
                Property redefinedProp = itRedefines.next();
                text.append("redefines ");
                text.append(redefinedProp.getName());

                if (itRedefines.hasNext())
                {
                    text.append(", ");
                }
            }
        }

        return text.toString();
    }

    private static String getOrderedText(Property property)
    {
        StringBuffer text = new StringBuffer();

        if (property.isOrdered())
        {
            text.append("ordered");
        }

        return text.toString();
    }

    private static String getUniqueText(Property property)
    {
        StringBuffer text = new StringBuffer();

        if (property.isMultivalued() && property.isUnique())
        {
            text.append("unique");
        }

        return text.toString();
    }

    private static String getConstraintText()
    {
        StringBuffer text = new StringBuffer();

        // TODO Display property constraint
        // ??? where is the constraint on a property ???

        return text.toString();
    }
}
