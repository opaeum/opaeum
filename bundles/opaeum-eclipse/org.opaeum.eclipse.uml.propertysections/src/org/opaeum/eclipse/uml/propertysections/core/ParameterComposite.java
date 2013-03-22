package org.opaeum.eclipse.uml.propertysections.core;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.common.IChoiceProvider;
import org.opaeum.eclipse.uml.propertysections.subsections.AbstractDetailsSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.BooleanSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.ChooserSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.ComboSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.LiteralIntegerSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.StringSubsection;


public class ParameterComposite extends AbstractDetailsSubsection<Parameter> implements ISelectionChangedListener {
	private StringSubsection name;
	private ChooserSubsection type;
	private ComboSubsection direction;
	private BooleanSubsection isException;
	private LiteralIntegerSubsection from;
	private LiteralIntegerSubsection to;
	private BooleanSubsection isUnique;
	private BooleanSubsection isOrdered;

	
	public ParameterComposite(Composite parent,int style,final TabbedPropertySheetWidgetFactory widgetFactory){
		super(parent, style,widgetFactory);
	}
	@Override
	protected int getNumberOfColumns(){
		return 4;
	}
	@Override
	protected void addSubsections(){
		name=createString(UMLPackage.eINSTANCE.getNamedElement_Name(), "Name", 120, 280);
		name.setColumnSpan(2);
		type=createChooser(UMLPackage.eINSTANCE.getTypedElement_Type(), "Type", 120, 280, new IChoiceProvider(){
			@Override
			public Object[] getChoices(){
				return TypedElementTypeSection.getValidTypes(getSelectedObject());
			}
		});
		type.setSingle(true);
		type.setColumnSpan(2);
		direction=createCombo(UMLPackage.eINSTANCE.getParameter_Direction(), "Direction", 120, 280, new IChoiceProvider(){
			@Override
			public Object[] getChoices(){
				return ParameterDirectionKind.values();
			}
		});
		direction.setColumnSpan(2);
		isException=createBoolean(UMLPackage.eINSTANCE.getParameter_IsException(), "Is Exception", 120);
		isException.setColumnSpan(2);
		from = createLiteralInteger(UMLPackage.eINSTANCE.getMultiplicityElement_LowerValue(), "Number of values:", 120, 50);
		from.setDefaultValue(0);
		to = createLiteralInteger(UMLPackage.eINSTANCE.getMultiplicityElement_UpperValue(), "to", 50, 50);
		to.setDefaultValue(1);
		isUnique = createBoolean(UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique(), "Every value is unique", 120);
		isOrdered = createBoolean(UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered(), "Values are ordered", 120);
	}
}