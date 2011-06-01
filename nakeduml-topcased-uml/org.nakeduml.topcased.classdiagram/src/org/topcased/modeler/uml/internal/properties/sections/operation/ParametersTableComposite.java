/*******************************************************************************
 * Copyright (c) 2006 Anyware Technologies. All rights reserved. This program
 * and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jacques Lescot (Anyware Technologies) - initial API and
 * implementation
 ******************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections.operation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.editor.MixedEditDomain;
import org.topcased.modeler.utils.LabelHelper;

/**
 * This widget contains a TableViewer representing the Parameters of an Operation. Ther are two Buttons (one for adding
 * a new Parameter, the other for deleting the current selected Parameter)
 * 
 * Creation 22 mai 06
 * 
 * @author jlescot
 */
public class ParametersTableComposite extends Composite
{
    /**
     * A boolean that store if refreshing is happening and no model modifications should be performed
     */
    private boolean isRefreshing = false;

    /** The Operation that contains Parameters */
    private Operation operation;

    private MixedEditDomain mixedEditDomain;

    /** The widgetFactory to use to create the widgets */
    private TabbedPropertySheetWidgetFactory widgetFactory;

    private TableViewer parametersTableViewer;

    private Table parametersTable;

    private String[] columnNames = new String[] {"Direction", "Name", "Type"};

    private Button addButton;

    private Button removeButton;

    /**
     * The constructor
     * 
     * @param parent a widget which will be the parent of the new instance (cannot be null)
     * @param style the style of widget to construct
     * @param widgetFactory the widgetFactory to use to create the widgets
     */
    ParametersTableComposite(Composite parent, int style, TabbedPropertySheetWidgetFactory widgetFactory)
    {
        super(parent, style);

        this.widgetFactory = widgetFactory;
        setLayout(new GridLayout(2, false));

        widgetFactory.adapt(this);
        createContents(this);
    }

    /**
     * Set the Operation and update the content of the table
     * 
     * @param operation the Operation model object
     */
    public void setOperation(Operation operation)
    {
        this.operation = operation;

        refresh();
    }

    /**
     * Set the MixedEditDomain
     * 
     * @param mixedEditDomain the MixedEditDomain
     */
    public void setMixedEditDomain(MixedEditDomain mixedEditDomain)
    {
        this.mixedEditDomain = mixedEditDomain;
    }

    /**
     * Create the Composite composed of a Table and two Buttons
     * 
     * @param parent the parent Composite
     */
    protected void createContents(Composite parent)
    {
        parametersTable = widgetFactory.createTable(parent, SWT.BORDER);
        addButton = widgetFactory.createButton(parent, "Add", SWT.NONE);
        addButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
        removeButton = widgetFactory.createButton(parent, "Delete", SWT.NONE);
        removeButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
        
        GridData gridData = new GridData(GridData.FILL_BOTH);
        gridData.verticalSpan = 2;

        parametersTable.setLayoutData(gridData);

        TableColumn directionColumn = new TableColumn(parametersTable, SWT.LEFT);
        directionColumn.setText(columnNames[0]);
        directionColumn.setWidth(75);

        TableColumn nameColumn = new TableColumn(parametersTable, SWT.LEFT);
        nameColumn.setText(columnNames[1]);
        nameColumn.setWidth(150);

        TableColumn typeColumn = new TableColumn(parametersTable, SWT.LEFT);
        typeColumn.setText(columnNames[2]);
        typeColumn.setWidth(150);

        parametersTable.setHeaderVisible(true);
        parametersTable.setLinesVisible(true);

        parametersTableViewer = new TableViewer(parametersTable);
        parametersTableViewer.setContentProvider(new ParameterContentProvider());
        parametersTableViewer.setLabelProvider(new ParameterLabelProvider());

        parametersTableViewer.addSelectionChangedListener(new ISelectionChangedListener()
        {
            /**
             * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
             */
            public void selectionChanged(SelectionChangedEvent event)
            {
                if (!isRefreshing)
                {
                    if (parametersTable.getSelection().length > 0)
                    {
                        updateSelectedParameter((Parameter) parametersTable.getSelection()[0].getData());
                    }
                    else
                    {
                        updateSelectedParameter(null);
                    }
                }
            }
        });

        addButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent event)
            {
                AddCommand addCommand = (AddCommand) AddCommand.create(mixedEditDomain.getEMFEditingDomain(), operation, getFeature(), getNewChild());
                mixedEditDomain.getEMFEditingDomain().getCommandStack().execute(addCommand);

                refresh();
                parametersTableViewer.setSelection(new StructuredSelection(operation.getOwnedParameters().get(operation.getOwnedParameters().size() - 1)));

            }
        });

        removeButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent event)
            {
                Object object = parametersTable.getSelection()[0].getData();
                mixedEditDomain.getEMFEditingDomain().getCommandStack().execute(RemoveCommand.create(mixedEditDomain.getEMFEditingDomain(), object));

                refresh();
                if (operation.getOwnedParameters().size() > 0)
                {
                    parametersTableViewer.setSelection(new StructuredSelection(operation.getOwnedParameters().get(0)));
                }
                else
                {
                    parametersTableViewer.setSelection(null);
                }
            }
        });
    }

    /**
     * Refresh the Table contents
     */
    protected void refresh()
    {
        isRefreshing = true;
        parametersTableViewer.setInput(operation.getOwnedParameters());
        isRefreshing = false;
    }

    /**
     * Get the feature for the table field for the section.
     * 
     * @return the feature for the table.
     */
    protected EStructuralFeature getFeature()
    {
        return UMLPackage.eINSTANCE.getBehavioralFeature_OwnedParameter();
    }

    /**
     * Get a new child instance for the result of clicking the add button.
     * 
     * @return a new child instance.
     */
    protected Object getNewChild()
    {
        Parameter newParameter = UMLFactory.eINSTANCE.createParameter();
        LabelHelper.INSTANCE.initName(mixedEditDomain, operation, newParameter);
        return newParameter;
    }

    /**
     * This method is called each time a new Parameter is selected in the Table
     * 
     * @param newParameter the new Parameter that was selected
     */
    public void updateSelectedParameter(Parameter newParameter)
    {
        // By default, do nothing
    }

    /**
     * Internal class to handle modification
     * 
     * @author jlescot
     * 
     */
    class ParameterContentProvider implements IStructuredContentProvider
    {
        /**
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        public Object[] getElements(Object inputElement)
        {
            if (inputElement instanceof EList)
            {
                return ((EList< ? >) inputElement).toArray();
            }

            return new Object[0];
        }

        /**
         * @see org.eclipse.jface.viewers.IContentProvider#dispose()
         */
        public void dispose()
        {
            // nothing to do
        }

        /**
         * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
         *      java.lang.Object, java.lang.Object)
         */
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
        {
            // nothing to do
        }
    }

    /**
     * Internal class to handle modification
     * 
     * @author jlescot
     */
    class ParameterLabelProvider extends LabelProvider implements ITableLabelProvider
    {
        /**
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
         */
        public Image getColumnImage(Object element, int columnIndex)
        {
            return null;
        }

        /**
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
         */
        public String getColumnText(Object element, int columnIndex)
        {
            switch (columnIndex)
            {
                case 0:
                    return ((Parameter) element).getDirection().getName();
                case 1:
                    return ((Parameter) element).getName();
                case 2:
                    if (((Parameter) element).getType() != null)
                    {
                        return ((Parameter) element).getType().getName();
                    }
                default:
                    return "";
            }
        }
    }
}
