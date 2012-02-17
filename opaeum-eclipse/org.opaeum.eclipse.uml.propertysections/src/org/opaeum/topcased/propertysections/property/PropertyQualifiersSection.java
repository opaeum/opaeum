package org.opaeum.topcased.propertysections.property;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public class PropertyQualifiersSection extends AbstractTabbedPropertySection{
	private PropertiesTableComposite propertiesTableComposite;
	private PropertyComposite propertiesDetailsComposite;
	private Group propertiesDetailsGroup;
	protected void createWidgets(Composite composite){
		propertiesTableComposite = new PropertiesTableComposite(composite, SWT.NONE, getWidgetFactory(), getFeature()){
			@Override
			public void updateSelectedQualifier(Property newParameter){
				propertiesDetailsComposite.setProperty(newParameter);
				refresh();
			}
		};
		propertiesDetailsGroup = getWidgetFactory().createGroup(composite, "Qualifier Details");
		propertiesDetailsGroup.setLayout(new GridLayout());
		propertiesDetailsComposite = new PropertyComposite(propertiesDetailsGroup, SWT.NONE, getWidgetFactory());
	}
	protected void setSectionData(Composite composite){
		FormData data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, 0);
		propertiesTableComposite.setLayoutData(data);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(propertiesTableComposite, ITabbedPropertyConstants.VSPACE);
		propertiesDetailsGroup.setLayoutData(data);
	}
	public void refresh(){
		super.refresh();
		EditingDomain mixedEditDomain = (EditingDomain) getPart().getAdapter(EditingDomain.class);
		propertiesTableComposite.setEditingDomain(mixedEditDomain);
		propertiesTableComposite.setOwner((Element) getEObject());
		propertiesDetailsComposite.setEditingDomain(mixedEditDomain);
		propertiesDetailsComposite.setProperty(null);
	}
	public void setEnabled(boolean b){
		super.setEnabled(b);
		propertiesDetailsComposite.setEnabled(b);
		propertiesTableComposite.setEnabled(b);
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getProperty_Qualifier();
	}
	@Override
	protected String getLabelText(){
		return "Qualifiers";
	}
}
