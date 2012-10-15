package org.opaeum.eclipse.uml.propertysections.event;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.uml.propertysections.base.AbstractOpaeumPropertySection;
import org.opaeum.eclipse.uml.propertysections.base.AbstractTabbedPropertySubsection;
import org.opaeum.eclipse.uml.propertysections.common.IChoiceProvider;
import org.opaeum.eclipse.uml.propertysections.subsections.AbstractDetailsSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.ChooserSubsection;

public class SignalChooserForEvent extends AbstractDetailsSubsection<SignalEvent> implements IChoiceProvider{
	private int labelWidth=AbstractOpaeumPropertySection.STANDARD_LABEL_WIDTH;
	private ChooserSubsection signalChooser;
	public SignalChooserForEvent(Composite parent,TabbedPropertySheetWidgetFactory widgetFactory){
		super(parent, SWT.NONE, widgetFactory);
	}
	public void setLabelWidth(int labelWidth){
		this.labelWidth = labelWidth;
		signalChooser.setLabelWidth(labelWidth);
		
	}
	@Override
	protected int getNumberOfColumns(){
		return 1;
	}

	@Override
	protected void addSubsections(){
		signalChooser = createChooser(UMLPackage.eINSTANCE.getSignalEvent_Signal(), "Signal", labelWidth, AbstractTabbedPropertySubsection.FILL, this);
		signalChooser.setSingle(true);
	}
	@Override
	public Object[] getChoices(){
		List<Object> choices = new ArrayList<Object>();
		choices.add("");
		choices.addAll(OpaeumEclipseContext.getReachableObjectsOfType(getSelectedObject(), UMLPackage.eINSTANCE.getSignal()));
		return choices.toArray();
	}
}
