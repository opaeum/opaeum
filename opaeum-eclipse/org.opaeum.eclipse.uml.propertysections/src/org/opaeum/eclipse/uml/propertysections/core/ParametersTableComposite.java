package org.opaeum.eclipse.uml.propertysections.core;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Parameter;
import org.opaeum.eclipse.uml.editingsupport.EditingDomainEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.MultiplicityElementUpperValueEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.NamedElementNameEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.ParameterDirectionEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.TypedElementTypeEditingSupport;

public class ParametersTableComposite extends AbstractTableComposite<Parameter>{
	public ParametersTableComposite(Composite parent,int style,TabbedPropertySheetWidgetFactory widgetFactory,EStructuralFeature feature){
		super(parent, style,widgetFactory,feature);
	}
	// This will create the columns for the table
	@Override
	protected void createColumns(){
		EditingDomainEditingSupport e = new NamedElementNameEditingSupport(tableViewer);
		TableViewerColumn name = createTableViewerColumn(e);
		EditingDomainEditingSupport de = new ParameterDirectionEditingSupport(tableViewer);
		TableViewerColumn direction = createTableViewerColumn(de);
		EditingDomainEditingSupport te = new TypedElementTypeEditingSupport(tableViewer, widgetFactory);
		TableViewerColumn type = createTableViewerColumn(te);
		createTableViewerColumn(new MultiplicityElementUpperValueEditingSupport(tableViewer));
	}
}
