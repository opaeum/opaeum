package org.opaeum.eclipse.uml.propertysections.property;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractMasterDetailSection;
import org.opaeum.eclipse.uml.propertysections.core.AbstractTableComposite;
import org.opaeum.eclipse.uml.propertysections.subsections.AbstractDetailsSubsection;

public class PropertyQualifiersSection extends AbstractMasterDetailSection<Property>{
	public PropertyQualifiersSection(){
		super("Qualifier Details");
	}

	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getProperty_Qualifier();
	}
	@Override
	public String getLabelText(){
		return "";
	}
	@Override
	protected AbstractDetailsSubsection<Property> createDetails(Group elementDetailsGroup2){
		return  new PropertyComposite(elementDetailsGroup2, SWT.NONE, getWidgetFactory());
	}
	@Override
	protected AbstractTableComposite<Property> createTable(Composite composite){
		return  new PropertiesTableComposite(composite, SWT.NONE, getWidgetFactory(), getFeature());
	}
}
