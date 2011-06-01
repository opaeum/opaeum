/*****************************************************************************
 * 
 * InheritMetaclassSection.java
 * 
 * Copyright (c) 2008 Atos Origin.
 *
 * Contributors:
 *  Thibault Landré (Atos Origin) thibault.landre@atosorigin.com - Initial API and Implementation
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *****************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections.stereotype;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.profilediagram.edit.StereotypeEditPart;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

/**
 * The Class InheritMetaclassSection.
 */
public class InheritMetaclassSection extends AbstractTabbedPropertySection
{

    /** The stereotype. */
    private Stereotype stereotype;

    /** The table compo. */
    private Composite tableCompo;

    /** The table. */
    private Table table;

    /** The column names. */
    private final String[] columnNames = new String[] {"Stereotype", "Metaclass"};

    /*
     * (non-Javadoc)
     * 
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
     */
    @Override
    protected EStructuralFeature getFeature()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
     */
    @Override
    protected String getLabelText()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#createWidgets(org.eclipse.swt.widgets.Composite
     * )
     */
    @Override
    protected void createWidgets(Composite composite)
    {
        tableCompo = new Composite(composite, SWT.NONE);
        tableCompo.setLayout(new GridLayout());
        getWidgetFactory().adapt(tableCompo);

        FormData data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, 0);
        tableCompo.setLayoutData(data);

        createContents();
    }

    /**
     * Create the Composite composed of a Table and two Buttons.
     */
    protected void createContents()
    {
        Group group = getWidgetFactory().createGroup(tableCompo, "Inherit Metaclass");

        group.setLayout(new GridLayout());
        group.setLayoutData(new GridData(GridData.FILL_BOTH));

        table = getWidgetFactory().createTable(group, SWT.BORDER);

        GridData gridData = new GridData(GridData.FILL_BOTH);
        gridData.verticalSpan = 2;
        gridData.horizontalSpan = 2;
        table.setLayoutData(gridData);

        TableColumn stereotypeColumn = new TableColumn(table, SWT.LEFT);
        stereotypeColumn.setText(columnNames[0]);
        stereotypeColumn.setWidth(150);

        TableColumn metaclassColumn = new TableColumn(table, SWT.LEFT);
        metaclassColumn.setText(columnNames[1]);
        metaclassColumn.setWidth(150);

        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        new TableViewer(table);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
     */
    @Override
    public void refresh()
    {
        super.refresh();
        updateTable();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
     * org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(IWorkbenchPart part, ISelection selection)
    {
        super.setInput(part, selection);
        Object input = ((IStructuredSelection) selection).getFirstElement();
        if (input instanceof StereotypeEditPart)
        {
            StereotypeEditPart editPart = (StereotypeEditPart) input;
            stereotype = (Stereotype) editPart.getEObject();
        }
        // Fix #1930: Missing edition from the outline
        else if (input instanceof Stereotype)
        {
            stereotype = (Stereotype) input;
        }
    }

    /**
     * Update the table of the stereotype.
     */
    private void updateTable()
    {
        // Remove all previous lines contained in the table
        table.removeAll();
        // Get inheritance metaclass
        for (Classifier classifier : stereotype.getGenerals())
        {
            if (classifier instanceof Stereotype)
            {
                // Fill the table
                getGeneralizationMetaclassesNames((Stereotype) classifier, stereotype);
            }
        }
    }

    /**
     * Fill the sourceStereoTable with inherited stereotype and their metaclass. It will throw an error if there is a
     * cycle in the inheritance hierarchy
     * 
     * @param stereo the parent stereotype that sourceStereo inherits
     * @param sourceStereo the stereotype on which the method looks inheritance
     */
    private void getGeneralizationMetaclassesNames(Stereotype stereo, Stereotype sourceStereo)
    {
        // if the stereotype inherits from another stereotype
        for (Classifier classifier : stereo.getGenerals())
        {
            // if there is a cycle in the inheritance, display a dialog and throws an error
            if (classifier.equals(sourceStereo))
            {
                UMLPlugin.displayDialog("Unable to open properties view", "There is a cycle in the inheritance hierarchy", IStatus.ERROR);
                throw new IllegalStateException("A cycle is present in the inheritance hierarchy");
            }
            if (classifier instanceof Stereotype)
            {
                getGeneralizationMetaclassesNames((Stereotype) classifier, sourceStereo);
            }
        }

        // Fill the sourceStereo table with inherited stereotype and their metaclass
        if (table != null)
        {
            for (org.eclipse.uml2.uml.Class classe : getMetaclasses(stereo))
            {
                TableItem ligne = new TableItem(table, SWT.NONE);
                ligne.setText(new String[] {stereo.getName(), classe.getName()});
            }
        }
    }

    /**
     * Gets the metaclasses.
     * 
     * @param stereo the stereo
     * 
     * @return the metaclasses associated with the stereotype given
     */
    private List<org.eclipse.uml2.uml.Class> getMetaclasses(Stereotype stereo)
    {
        ArrayList<org.eclipse.uml2.uml.Class> metaclasses = new ArrayList<org.eclipse.uml2.uml.Class>();
        for (Property property : stereo.getOwnedAttributes())
        {

            if (property.getAssociation() instanceof Extension && property.getType() != null && property.getType() instanceof org.eclipse.uml2.uml.Class)
            {
                metaclasses.add((org.eclipse.uml2.uml.Class) property.getType());
            }
        }
        return metaclasses;
    }

}
