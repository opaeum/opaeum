/*******************************************************************************
 * Copyright (c) 2005 Anyware Technologies
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    David Sciamma (Anyware Technologies) - initial API and implementation
 *******************************************************************************/

package org.topcased.modeler.uml.actions;

import java.util.Comparator;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.common.edit.provider.IItemQualifiedTextProvider;
import org.topcased.modeler.editor.Modeler;

/**
 * Basic UML Action. Provides a text comparator...
 * 
 * @author <a href="david.sciamma@anyware-tech.com">David Sciamma</a>
 */
public abstract class UMLAction extends Action
{
    private Modeler editor;

    private ILabelProvider labelProvider;

    /**
     * TODO Comment this class
     * @param <T>
     */
    protected class TextComparator<T> implements Comparator<T>
    {
        /**
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        public int compare(T o1, T o2)
        {
            return getLabelProvider().getText(o1).compareTo(getLabelProvider().getText(o2));
        }
    }

    /**
     * Constructor
     * 
     * @param text the action title
     * @param ed the editor
     */
    public UMLAction(String text, Modeler ed)
    {
        super(text);
        this.editor = ed;
        labelProvider = new AdapterFactoryLabelProvider(this.editor.getAdapterFactory())
        {

            public String getColumnText(Object object, int columnIndex)
            {
                IItemQualifiedTextProvider itemQualifiedTextProvider = (IItemQualifiedTextProvider) adapterFactory.adapt(object, IItemQualifiedTextProvider.class);

                return itemQualifiedTextProvider != null ? itemQualifiedTextProvider.getQualifiedText(object) : super.getColumnText(object, columnIndex);
            }

            public String getText(Object object)
            {
                IItemQualifiedTextProvider itemQualifiedTextProvider = (IItemQualifiedTextProvider) adapterFactory.adapt(object, IItemQualifiedTextProvider.class);

                return itemQualifiedTextProvider != null ? itemQualifiedTextProvider.getQualifiedText(object) : super.getText(object);
            }
        };
    }

    /**
     * Get the Editor instance
     * 
     * @return Modeler
     */
    protected Modeler getEditor()
    {
        return this.editor;
    }

    /**
     * Get the LabelProvider
     * 
     * @return ILabelProvider
     */
    protected ILabelProvider getLabelProvider()
    {
        return labelProvider;
    }
}