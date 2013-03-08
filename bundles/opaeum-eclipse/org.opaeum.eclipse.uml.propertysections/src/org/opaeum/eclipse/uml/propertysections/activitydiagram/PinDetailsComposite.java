package org.opaeum.eclipse.uml.propertysections.activitydiagram;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValuePin;
import org.opaeum.eclipse.uml.propertysections.base.AbstractTabbedPropertySubsection;
import org.opaeum.eclipse.uml.propertysections.common.IChoiceProvider;
import org.opaeum.eclipse.uml.propertysections.core.TypedElementTypeSection;
import org.opaeum.eclipse.uml.propertysections.subsections.AbstractDetailsSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.StackDetailsSubsection;

public class PinDetailsComposite extends StackDetailsSubsection<InputPin>{
	public PinDetailsComposite(TabbedPropertySheetWidgetFactory factory,Composite parent){
		super(factory, parent, "Input Details");
	}
	@Override
	protected void addLayers(Composite composite,TabbedPropertySheetWidgetFactory factory){
		addLayer("OCL", new AbstractDetailsSubsection<InputPin>(composite, SWT.NONE, factory){
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
			}
		});
		addLayer("PIN", new AbstractDetailsSubsection<InputPin>(composite, SWT.NONE, factory){
			@Override
			protected void addSubsections(){
				createString(UMLPackage.eINSTANCE.getNamedElement_Name(), "Name", 120, 280);
				createChooser(UMLPackage.eINSTANCE.getTypedElement_Type(), "Type", 120, 280, new IChoiceProvider(){
					@Override
					public Object[] getChoices(){
						return TypedElementTypeSection.getValidTypes(selectedObject);
					}
				}).setSingle(true);
			}
		});
		addLayer("INSTANCE", new AbstractDetailsSubsection<InputPin>(composite, SWT.NONE, factory){
			@Override
			protected void addSubsections(){
				createString(UMLPackage.eINSTANCE.getNamedElement_Name(), "Name", 120, 280);
				createChooser(UMLPackage.eINSTANCE.getTypedElement_Type(), "Type", 120, 280, new IChoiceProvider(){
					@Override
					public Object[] getChoices(){
						return TypedElementTypeSection.getValidTypes(selectedObject);
					}
				}).setSingle(true);
				createInstanceValue(UMLPackage.eINSTANCE.getValuePin_Value(), "Value", 120, AbstractTabbedPropertySubsection.FILL);
			}
		});
	}
	@Override
	protected String getKeyFor(List<InputPin> eObjectList){
		if(eObjectList.isEmpty()){
			return "PIN";
		}
		if(eObjectList.get(0) instanceof ValuePin){
			ValuePin vp=(ValuePin) eObjectList.get(0);
			return (vp.getValue() instanceof OpaqueExpression? "OCL":"INSTANCE");
		}
		return "PIN";
	}
}