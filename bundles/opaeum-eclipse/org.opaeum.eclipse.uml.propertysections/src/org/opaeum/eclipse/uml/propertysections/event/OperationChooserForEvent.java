package org.opaeum.eclipse.uml.propertysections.event;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfOperationUtil;
import org.opaeum.eclipse.uml.propertysections.base.AbstractOpaeumPropertySection;
import org.opaeum.eclipse.uml.propertysections.base.AbstractTabbedPropertySubsection;
import org.opaeum.eclipse.uml.propertysections.common.IChoiceProvider;
import org.opaeum.eclipse.uml.propertysections.subsections.AbstractDetailsSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.ChooserSubsection;

public class OperationChooserForEvent extends AbstractDetailsSubsection<CallEvent> implements IChoiceProvider{
	private int labelWidth = AbstractOpaeumPropertySection.STANDARD_LABEL_WIDTH;
	private ChooserSubsection operationChooser;
	public OperationChooserForEvent(Composite parent,TabbedPropertySheetWidgetFactory widgetFactory){
		super(parent, SWT.NONE, widgetFactory);
	}
	public void setLabelWidth(int labelWidth){
		this.labelWidth = labelWidth;
		operationChooser.setLabelWidth(labelWidth);
	}
	@Override
	protected int getNumberOfColumns(){
		return 1;
	}

	@Override
	protected void addSubsections(){
		operationChooser = createChooser(UMLPackage.eINSTANCE.getCallEvent_Operation(), "Operation", labelWidth, AbstractTabbedPropertySubsection.FILL, this);
		operationChooser.setSingle(true);
	}
	@Override
	public Object[] getChoices(){
		Classifier a = (Classifier) EmfElementFinder.getNearestClassifier((Element) getSelectedObject());
		List<Operation> choices = new ArrayList<Operation>(EmfOperationUtil.getEffectiveOperations(a));
		if(a instanceof Behavior && ((Behavior) a).getContext() != null){
			choices.addAll(EmfOperationUtil.getEffectiveOperations(((Behavior) a).getContext()));
		}
		return choices.toArray();
	}
}
