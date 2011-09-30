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
 ******************************************************************************/

package org.topcased.modeler.uml.classdiagram.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.Type;
import org.topcased.modeler.uml.UMLPlugin;

/**
 * Provide a structure for handling InstanceSpecification data <br>
 * creation : 27 may 2005
 * 
 * @author <a href="mailto:mathieu.garcia@anyware-tech.com">Mathieu Garcia</a>
 * @deprecated Do not use this class anymore since the associated dialog and command have been marked as deprecated too
 */
public class InstanceSpecificationDataStructure
{

    /**
     * Internal class to handle a property value in an InstanceSpecification object
     */
    public class InstanceSpecificationDataObject
    {

        /** Property name */
        private String name;

        /** Property type */
        private Type type;

        /** Value for the property */
        private String value;

        /**
         * The constructor
         * 
         * @param n property name
         * @param t property type
         * @param v property value
         */
        public InstanceSpecificationDataObject(String n, Type t, String v)
        {
            name = n;
            type = t;
            value = v;
        }

        /**
         * Get property name
         * 
         * @return name of the property
         */
        public String getName()
        {
            return name;
        }

        /**
         * Get property type
         * 
         * @return type of the property
         */
        public Type getType()
        {
            return type;
        }

        /**
         * Get property value
         * 
         * @return value of the property
         */
        public String getValue()
        {
            return value;
        }

        /**
         * Set new value for the property
         * 
         * @param newValue the new value
         */
        public void setValue(String newValue)
        {
            value = newValue;
        }

    } // End internal class

    /** A collection for InstanceSpecificationDataObject objects */
    private ArrayList<InstanceSpecificationDataObject> data;

    /**
     * The constructor
     * 
     * @param instance
     */
    public InstanceSpecificationDataStructure(InstanceSpecification instance)
    {
        this(instance.getClassifiers().size() != 0 ? (Class) instance.getClassifiers().get(0) : null, instance);
    }

    /**
     * The constructor
     * 
     * @param clazz
     * @param instance
     */
    public InstanceSpecificationDataStructure(Class clazz, InstanceSpecification instance)
    {
        data = new ArrayList<InstanceSpecificationDataObject>();
        if (clazz != null)
        {
            addAll(clazz.getOwnedAttributes(), instance);
            addAllFromGeneralClasses(clazz.getGeneralizations(), instance);
        }
    }

    private void addAllFromGeneralClasses(EList<Generalization> generalizations, InstanceSpecification instance)
    {
        if (!generalizations.isEmpty())
        {
            for (Generalization gen : generalizations)
            {
                Class currentClass = (Class) gen.getGeneral();
                if (currentClass != null)
                {
                    addAll(currentClass.getOwnedAttributes(), instance);
                    addAllFromGeneralClasses(currentClass.getGeneralizations(), instance);
                }
            }
        }
    }

    /**
     * Add a property to the structure
     * 
     * @param property
     * @param value
     */
    public void add(Property property, String value)
    {
        data.add(new InstanceSpecificationDataObject(property.getName(), property.getType(), value));
    }

    /**
     * Add a collection of properties to an instance specification
     * 
     * @param properties
     * @param instance
     */
    public void addAll(Collection<Property> properties, InstanceSpecification instance)
    {
        for (Property property : properties)
        {
            add(property, computeValueAsString(property, instance));
        }
    }

    /**
     * Get value from slot and convert it to a String
     * 
     * @param property property to compute
     * @param instance current instance
     * @return converted string
     */
    private String computeValueAsString(Property property, InstanceSpecification instance)
    {
        boolean found = false;
        Slot slot = null;
        // Look for slot that defines the current property
        for (Iterator<Slot> it = instance.getSlots().iterator(); it.hasNext() && !found;)
        {
            slot = it.next();
            if (property.equals(slot.getDefiningFeature()))
            {
                found = true;
            }
        }
        if (found)
        {
            if (slot.getValues().size() != 0)
            {
                // TODO check that is the correct behavior
                // Type type = property.getType();
                // Object value = slot.getValues().get(0);
                // if (type instanceof Class)
                // {
                // return ((InstanceValue) value).getInstance().getName();
                // }
                // else if (type instanceof PrimitiveType)
                // {
                // if (type.getName().equals("Boolean"))
                // {
                // return String.valueOf(((LiteralBoolean)
                // value).booleanValue());
                // }
                // else if (type.getName().equals("Integer"))
                // {
                // return String.valueOf(((LiteralInteger)
                // value).integerValue());
                // }
                // else
                // {
                // return ((Expression) value).getBody();
                // }
                // }
                // else
                // {
                // return value.toString();
                // }
                return slot.getValues().get(0).stringValue();
            }
        }
        return null;

    }

    /**
     * get the data collection
     * 
     * @return a Collection of InstanceStructureObject objects
     */
    public Collection<InstanceSpecificationDataObject> getData()
    {
        return data;
    }

    /**
     * Get the name of a given object
     * 
     * @param object InstanceSpecificationDataObject object
     * @return the name
     */
    public String getDisplayName(Object object)
    {
        String displayName = ((InstanceSpecificationDataObject) object).getName();

        if (displayName == null || displayName.length() == 0)
        {
            displayName = "Property name no set";
        }

        return displayName;
    }

    /**
     * Get the type name of a given object
     * 
     * @param object InstanceSpecificationDataObject object
     * @return the name of the type
     */
    public String getDisplayType(Object object)
    {
        String displayType = null;

        Type type = getType(object);
        if (type != null)
        {
            displayType = type.getName();
        }

        return displayType;
    }

    /**
     * Get the type of a given object
     * 
     * @param object InstanceSpecificationDataObject object
     * @return the type
     */
    public Type getType(Object object)
    {
        return ((InstanceSpecificationDataObject) object).getType();
    }

    /**
     * Get the value of a given object
     * 
     * @param object InstanceSpecificationDataObject object
     * @return the value as a String
     */
    public String getDisplayValue(Object object)
    {
        return ((InstanceSpecificationDataObject) object).getValue();
    }

    /**
     * Update the given object in the collection
     * 
     * @param object InstanceSpecificationDataObject object to update
     * @param value the new value
     * @param optionalText an optional text use when a string has been set by the user
     * @param instance the instance specification object
     */
    public void updateObject(Object object, Object value, String optionalText, InstanceSpecification instance)
    {
        InstanceSpecificationDataObject objectToUpdate = (InstanceSpecificationDataObject) object;

        int i = ((Integer) value).intValue();

        if (objectToUpdate.getType() instanceof Class)
        {
            if (i != -1)
            {
                String[] availableInstances = InstanceSpecificationHelper.getAvailableInstanceNames(instance.getModel(), objectToUpdate.getType());
                if (availableInstances.length != 0)
                {
                    String valueAsString = InstanceSpecificationHelper.getAvailableInstanceNames(instance.getModel(), objectToUpdate.getType())[i];
                    objectToUpdate.setValue(valueAsString);
                }
            }
        }
        else if (objectToUpdate.getType() instanceof PrimitiveType)
        {

            if (objectToUpdate.getType().getName().equals("Boolean"))
            {
                if (i != -1)
                {
                    String valueAsString = InstanceSpecificationHelper.getBooleanValues()[i];
                    objectToUpdate.setValue(valueAsString);
                }
            }
            else if (objectToUpdate.getType().getName().equals("Integer"))
            {
                try
                {
                    Integer.parseInt(optionalText);
                    objectToUpdate.setValue(optionalText);
                }
                catch (NumberFormatException e)
                {
                    UMLPlugin.log("Integer expected", IStatus.ERROR);
                }
            }
            else
            {
                objectToUpdate.setValue(optionalText);
            }
        }
        // TODO Handle other types
    }
}
