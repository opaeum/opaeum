package org.opaeum.eclipse.uml.propertysections.activitydiagram;

import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractTabbedPropertySubsection;
import org.opaeum.eclipse.uml.propertysections.common.IChoiceProvider;
import org.opaeum.eclipse.uml.propertysections.core.TypedElementTypeSection;
import org.opaeum.eclipse.uml.propertysections.subsections.AbstractDetailsSubsection;

public class PinDetailsComposite extends AbstractDetailsSubsection<Pin>{
	public PinDetailsComposite(Composite parent,int style,TabbedPropertySheetWidgetFactory widgetFactory){
		super(parent, style, widgetFactory);
	}
	@Override
	protected void addSubsections(){
		createString(UMLPackage.eINSTANCE.getNamedElement_Name(), "Name", 120, 280);
		createChooser(UMLPackage.eINSTANCE.getTypedElement_Type(), "Type", 120, 280, new IChoiceProvider(){
			@Override
			public Object[] getChoices(){
				return TypedElementTypeSection.getValidTypes(selectedObject);
			}
		}).setSingle(true);
		createOpaqueExpression(UMLPackage.eINSTANCE.getValuePin_Value(), "Value", 120, AbstractTabbedPropertySubsection.FILL);
		createInstanceValue(UMLPackage.eINSTANCE.getValuePin_Value(), "Value", 120, AbstractTabbedPropertySubsection.FILL);
	}
	@Override
	public void selectionChanged(SelectionChangedEvent event){
		super.selectionChanged(event);
		getContentPane().layout();
	}
	
}