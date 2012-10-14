package org.opaeum.eclipse.uml.propertysections.property;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.common.IChoiceProvider;
import org.opaeum.eclipse.uml.propertysections.core.TypedElementTypeSection;
import org.opaeum.eclipse.uml.propertysections.subsections.AbstractDetailsSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.ChooserSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.StringSubsection;


public class PropertyComposite extends AbstractDetailsSubsection<Property>{
	private StringSubsection propertyName;
	private ChooserSubsection propertyType;
	public PropertyComposite(Composite parent,int style,TabbedPropertySheetWidgetFactory widgetFactory){
		super(parent, style,widgetFactory);
		setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
	}
	private Object[] getChoices(){
		List<Object> choices = new ArrayList<Object>();
		choices.add("");
		Collection<EObject> types = TypedElementTypeSection.getValidTypeCollection(selectedObject);
		Iterator<? extends Object> iterator = types.iterator();
		while(iterator.hasNext()){
			Object object = (Object) iterator.next();
			if(!isQualifierType(object)){
				iterator.remove();
			}
		}
		choices.addAll(types);
		return choices.toArray();
	}
	private boolean isQualifierType(Object object){
		boolean isApplicableToQualifiers = true;
		if(object instanceof Class || object instanceof Enumeration || object instanceof PrimitiveType){
			// TODO filter out methods and screenflows
			isApplicableToQualifiers = true;
		}else{
			if(object instanceof DataType){
				for(Property property:((DataType) object).getAllAttributes()){
					if(!property.isDerived()){
						isApplicableToQualifiers = false;
						break;
					}
				}
			}
		}
		return isApplicableToQualifiers;
	}
	@Override
	protected void addSubsections(){
		propertyName=createString(UMLPackage.eINSTANCE.getNamedElement_Name(), "Name", 100, 280);
		propertyType=createChooser(UMLPackage.eINSTANCE.getTypedElement_Type(), "Text", 100, 280, new IChoiceProvider(){
			@Override
			public Object[] getChoices(){
				return PropertyComposite.this.getChoices();
			}
		});
	}
}