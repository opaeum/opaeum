/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *   Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware Technologies),
 *   Nicolas Lalevée (Anyware Technologies) - initial API and implementation 
 *   Benoit MAGGI (Atos Origin)
 ******************************************************************************/

package org.topcased.modeler.uml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.VisibilityKind;

/**
 * Class for generic operation <br>
 * creation : 8 avr. 2005
 * 
 * @author <a href="mailto:mathieu.garcia@anyware-tech.com">Mathieu Garcia</a>
 */
public final class UMLTools
{
    
    /** The Constant BOOLEAN_PRIMITIVE_TYPE_NAME. */
    private static final String BOOLEAN_PRIMITIVE_TYPE_NAME = "Boolean";

    /** The Constant INTEGER_PRIMITIVE_TYPE_NAME. */
    private static final String INTEGER_PRIMITIVE_TYPE_NAME = "Integer";

    /** The Constant STRING_PRIMITIVE_TYPE_NAME. */
    private static final String STRING_PRIMITIVE_TYPE_NAME = "String";

    /**
     * no default constructor : it's an utility class.
     */
    private UMLTools()
    {
        // no default constructor : it's an utility class
    }

    /**
     * Return the complete name, ie with the owner names.
     * 
     * @param elem the element
     * 
     * @return the complete name
     */
    public static String getCompleteName(Element elem)
    {
        String name;
        if (elem instanceof NamedElement && ((NamedElement) elem).getQualifiedName() != null)
        {
            return ((NamedElement) elem).getQualifiedName();
        }
        else if (elem instanceof NamedElement)
        {
            name = ((NamedElement) elem).getName();
        }
        else
        {
            name = elem.getClass().getName();
        }

        if (elem.getOwner() != null)
        {
            name = getCompleteName(elem.getOwner()) + NamedElement.SEPARATOR + name;
        }
        return name;
    }

    /**
     * Return the element correspong to the specified path. The path should be a path return by getCompleteName
     * 
     * @param root the UML2 root element
     * @param completePath the path of the element
     * 
     * @return the found element
     */
    public static NamedElement getElementFromPath(Model root, String completePath)
    {
        NamedElement element = root;

        String[] path = completePath.split("\\.");

        if (path.length == 0 || !path[0].equals(root.getName()))
        {
            return null;
        }

        int i = 1;

        while (element != null && i < path.length)
        {
            Iterator<Element> itElements = element.getOwnedElements().iterator();
            while (itElements.hasNext())
            {
                Element elem = itElements.next();
                if (elem instanceof NamedElement)
                {
                    if (path[i].equals(((NamedElement) elem).getName()))
                    {
                        element = (NamedElement) elem;
                        break;
                    }
                }

                if (!itElements.hasNext())
                {
                    element = null;
                }
            }
            i++;
        }

        return element;
    }

    /**
     * if primitive types do not exist, create them.
     * 
     * @param model current edited model
     */
    public static void initializePrimitiveTypes(Model model)
    {
        boolean initialized = false;
        Iterator<Type> iterator = model.getOwnedTypes().iterator();
        while (iterator.hasNext() && !initialized)
        {
            Object current = iterator.next();
            if (current instanceof PrimitiveType)
            {
                initialized = true;
            }
        }

        // if (!initialized)
        // {
        // PrimitiveType stringType =
        // UML2Factory.eINSTANCE.createPrimitiveType();
        // stringType.setName(STRING_PRIMITIVE_TYPE_NAME);
        // model.getOwnedMembers().add(stringType);
        //
        // PrimitiveType integerType =
        // UML2Factory.eINSTANCE.createPrimitiveType();
        // integerType.setName(INTEGER_PRIMITIVE_TYPE_NAME);
        // model.getOwnedMembers().add(integerType);
        //
        // PrimitiveType booleanType =
        // UML2Factory.eINSTANCE.createPrimitiveType();
        // booleanType.setName(BOOLEAN_PRIMITIVE_TYPE_NAME);
        // model.getOwnedMembers().add(booleanType);
        // }
    }

    /**
     * Return a collection of all types contained in the model associated to a given element.
     * 
     * @param element the element
     * 
     * @return types (data type, class) including primitive types
     */
    public static Collection<Type> getAllAvailableTypes(Element element)
    {
        // element.eClass().eGet(UML2Package.PROPERTY__TYPE);
        // EClassifier classif =
        // element.eClass().getEStructuralFeature(UML2Package.PROPERTY__TYPE).getEType();
        // return ItemPropertyDescriptor.getReachableObjectsOfType(element,
        // classif);
        Model model = element.getModel();
        initializePrimitiveTypes(model);
        ArrayList<Type> allUserTypes = new ArrayList<Type>();
        for (NamedElement current : model.getOwnedMembers())
        {
            if (current instanceof Package)
            {
                allUserTypes = getAllObjects(current, Type.class);
            }
        }
        allUserTypes.addAll(0, model.getOwnedTypes());
        return allUserTypes;
    }

    /**
     * Search in the given model every elements.
     * 
     * @param element the element to search in
     * 
     * @return an ArrayList containing the wanted model objects
     */
    public static ArrayList<? extends Element> getAllObjects(Package element)
    {
        return getAllObjects(element, null);
    }

    /**
     * Search in the given model every occurrence of a type.
     * 
     * @param element the element to search in
     * @param clazz the wanted type
     * 
     * @return an ArrayList containing the wanted model objects
     */
    public static <T extends Element> ArrayList<T> getAllObjects(Element element, java.lang.Class<T> clazz)
    {
        ArrayList<T> result = new ArrayList<T>();

        if (clazz == null || clazz.isInstance(element))
        {
            result.add((T) element);
        }

        Iterator<Element> iterator = element.getOwnedElements().iterator();

        while (iterator.hasNext())
        {
            result.addAll(getAllObjects(iterator.next(), clazz));
        }

        return result;

    }

    /**
     * Retrieve the root Element of an UML model that contains a given Element.
     * 
     * @param elt an Element in the model
     * 
     * @return the root Element of the model
     */
    public static Element findRootElement(Element elt)
    {
        return elt.getOwner() == null ? elt : findRootElement(elt.getOwner());
    }

    /**
     * Remove the relation ships from the collection.
     * 
     * @param c the collection to filter
     * 
     * @return the filtered list
     */
    public static ArrayList<Element> removeRelationship(Collection<Element> c)
    {
        ArrayList<Element> result = new ArrayList<Element>();
        Iterator<Element> iterator = c.iterator();
        while (iterator.hasNext())
        {
            Element current = iterator.next();
            if (!(current instanceof Relationship))
            {
                result.add(current);
            }
        }
        return result;
    }

    /**
     * Customized type name.
     * 
     * @param type type to customize name
     * 
     * @return customized name
     */
    public static String getTypeFullName(Type type)
    {
        String fullName = getCompleteName(type);
        if (type instanceof Class)
        {
            fullName = "Class " + fullName;
        }
        else if (type instanceof PrimitiveType)
        {
            fullName = "Primitive Type " + fullName;
        }
        else if (type instanceof DataType)
        {
            fullName = "Data Type " + fullName;
        }
        else if (type instanceof Interface)
        {
            fullName = "Interface " + fullName;
        }

        return fullName == null ? "" : fullName;
    }

    /**
     * Return an array of type names.
     * 
     * @param types a Collection of Type
     * 
     * @return array of type names
     * 
     * @see #getAllAvailableTypes(Element)
     */
    public static String[] getAllAvailableTypeNames(Collection<Type> types)
    {
        String[] result = new String[types.size()];
        int index = 0;
        for (Type currentType : types)
        {
            result[index] = getTypeFullName(currentType);
            index++;
        }
        return result;
    }

    /**
     * Return a representation for a given visibility kind.
     * 
     * @param visibility the visibily
     * 
     * @return a string that represents the visibility kind
     * 
     * @deprecated Use the method declared in the org.topcased.modeler.uml.UMLLabel class
     */
    public static String getVisibilityNotation(VisibilityKind visibility)
    {
        if (visibility != null)
        {
            switch (visibility.getValue())
            {
                case VisibilityKind.PUBLIC:
                    // Public
                    return "+";
                case VisibilityKind.PRIVATE:
                    // Private
                    return "-";
                case VisibilityKind.PROTECTED:
                    // Protected
                    return "#";
                case VisibilityKind.PACKAGE:
                    // Package
                    return "~";
                default:
                    return "";

            }
        }
        return "";
    }

    /**
     * Return a representation for a given multiplicity.
     * 
     * @param lowerBound the lower bound of the multiplicity
     * @param upperBound the upper bound of the multiplicity
     * 
     * @return a string that represents the multiplicity
     */
    public static String getMultiplicityNotation(int lowerBound, int upperBound)
    {
        return Integer.toString(lowerBound) + ".." + Integer.toString(upperBound);
    }

    /**
     * Get a string representation of all the applied stereoptypes on the given UML2 element.<br>
     * 
     * @param uml2Element an UML2 element
     * 
     * @return a fully qualified string representation or empty string if no applied stereotypes
     * 
     * @deprecated Use the method declared in the org.topcased.modeler.uml.UMLLabel class
     */
    public static String getStereotypesNotation(Element uml2Element)
    {
        StringBuffer label = new StringBuffer();

        Iterator<Stereotype> itStereotypes = uml2Element.getAppliedStereotypes().iterator();

        if (itStereotypes.hasNext())
        {
            label.append("<<");
        }

        while (itStereotypes.hasNext())
        {
            Stereotype stereotype = itStereotypes.next();

            label.append(stereotype.getName());

            if (itStereotypes.hasNext())
            {
                label.append(", ");
            }
            else
            {
                label.append(">>");
            }
        }

        return label.toString();
    }

    /**
     * Get a string representation of all the applied stereoptypes on the given UML2 element.<br>
     * 
     * @param element an UML2 element
     * @param parent the element which graphically contains the UML2 Type element
     * 
     * @return a fully qualified string representation or empty string if no applied stereotypes
     */
    public static String getFromPackageNotation(Element element, Element parent)
    {
        String name = null;

        if (element instanceof Type)
        {
            Type type = (Type) element;
            if (type.getPackage() != null && type.getPackage() != parent)
            {
                name = type.getPackage().getName();
            }
        }
        else if (element instanceof Package)
        {
            Package pakage = (Package) element;
            if (pakage.getOwner() != null && pakage.getOwner() != parent)
            {
                name = ((NamedElement) pakage.getOwner()).getName();
            }
        }

        if (name != null)
        {
            return "(from " + name + ")";
        }

        return "";
    }

  
    
    /**
     * Gets the from parent notation, it's the generic way of the previous method.
     * 
     * @param pElementChild the element child
     * @param pElementGraphicalParent the element graphical parent
     * 
     * @return the from parent notation
     */
    public static String getFromParentNotation(Element pElementChild, Element pElementGraphicalParent)
    {
        String name = "";

        EObject lEObjectParent = pElementChild.eContainer();
        
        if (lEObjectParent instanceof Element) {
			Element lElementModelParent = (Element) lEObjectParent;
			
			if (lElementModelParent != pElementGraphicalParent && lElementModelParent instanceof NamedElement){
				NamedElement lNamedElement = (NamedElement) lElementModelParent;
				name = "(from " + lNamedElement.getName() + ")";
			}
		}
        return name;
    }    
    
    
    
    /**
     * Return the string representation of the ValueSpecification.
     * 
     * @param valueSpec the ValueSpecification
     * 
     * @return the string representation of the ValueSpecification
     */
    public static String getStringValue(ValueSpecification valueSpec)
    {
        // TODO remove this unused code
        // if (valueSpec instanceof LiteralBoolean)
        // {
        // return Boolean.toString(((LiteralBoolean) valueSpec).booleanValue());
        // }
        // else if (valueSpec instanceof LiteralInteger)
        // {
        // return Integer.toString(((LiteralInteger) valueSpec).integerValue());
        // }
        // else if (valueSpec instanceof LiteralString)
        // {
        // return ((LiteralString) valueSpec).stringValue();
        // }
        // else if (valueSpec instanceof Expression)
        // {
        // return ((Expression) valueSpec).getBody();
        // }
        // else if (valueSpec instanceof InstanceValue)
        // {
        // return getCompleteName(((InstanceValue) valueSpec).getInstance());
        // }
        // return "-";
        String result = valueSpec.stringValue();
        return result == null ? "" : result;
    }

    /**
     * Compare two types. Return true is they are the same.
     * 
     * @param t1 first type to compare
     * @param t2 second type to compare
     * 
     * @return true if they are the same
     */
    public static boolean compareType(Type t1, Type t2)
    {
        if (t1 == null && t2 == null)
        {
            return true;
        }

        if (t1 == null || t2 == null)
        {
            return false;
        }

        if (t1 instanceof PrimitiveType && t2 instanceof PrimitiveType)
        {
            return t1.getName().equals(t2.getName());
        }

        return getCompleteName(t1).equals(getCompleteName(t2));
    }

    /**
     * Test if the type is a primitive type with the right name.
     * 
     * @param type type to test
     * @param primitiv the name of the primitiv name to cast
     * 
     * @return true if it's a primitive type of the specified name
     */
    public static boolean isPrimitiveType(Type type, String primitiv)
    {
        return type != null && type instanceof PrimitiveType && type.getName().equals(primitiv);
    }

    /**
     * Test if the type is a integer primitive type.
     * 
     * @param type type to test
     * 
     * @return true if it's a integer primitive type
     */
    public static boolean isPrimitiveTypeInteger(Type type)
    {
        return isPrimitiveType(type, INTEGER_PRIMITIVE_TYPE_NAME);
    }

    /**
     * Test if the type is a boolean primitive type.
     * 
     * @param type type to test
     * 
     * @return true if it's a boolean primitive type
     */
    public static boolean isPrimitiveTypeBoolean(Type type)
    {
        return isPrimitiveType(type, BOOLEAN_PRIMITIVE_TYPE_NAME);
    }

    /**
     * Test if the type is a string primitive type.
     * 
     * @param type type to test
     * 
     * @return true if it's a string primitive type
     */
    public static boolean isPrimitiveTypeString(Type type)
    {
        return isPrimitiveType(type, STRING_PRIMITIVE_TYPE_NAME);
    }

}
