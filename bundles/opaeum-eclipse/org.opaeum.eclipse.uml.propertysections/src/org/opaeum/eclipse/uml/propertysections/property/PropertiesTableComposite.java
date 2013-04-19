package org.opaeum.eclipse.uml.propertysections.property;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.opaeum.eclipse.uml.editingsupport.NamedElementNameEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.TypedElementTypeEditingSupport;
import org.opaeum.eclipse.uml.propertysections.core.AbstractTableComposite;

public class PropertiesTableComposite extends AbstractTableComposite<Property>{
	public PropertiesTableComposite(Composite parent,int style,TabbedPropertySheetWidgetFactory widgetFactory,EStructuralFeature feature){
		super(parent, style,widgetFactory,feature);
	}
	@Override
	protected void createColumns(){
		createTableViewerColumn(new NamedElementNameEditingSupport(super.tableViewer));
		createTableViewerColumn(new TypedElementTypeEditingSupport(super.tableViewer,widgetFactory));
		
	}
	protected Object getNewChild(){
		Property newParameter = UMLFactory.eINSTANCE.createProperty();
		newParameter.setName("qualifier" + getObjectList().size());
		return newParameter;
	}
}
