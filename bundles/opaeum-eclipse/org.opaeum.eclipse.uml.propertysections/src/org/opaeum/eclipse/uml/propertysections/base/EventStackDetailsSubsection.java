package org.opaeum.eclipse.uml.propertysections.base;

import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.TimeEvent;
import org.opaeum.eclipse.uml.propertysections.event.AbsoluteTimeEventDetailsComposite;
import org.opaeum.eclipse.uml.propertysections.event.ChangeEventDetailsComposite;
import org.opaeum.eclipse.uml.propertysections.event.OperationChooserForEvent;
import org.opaeum.eclipse.uml.propertysections.event.RelativeTimeEventDetailsComposite;
import org.opaeum.eclipse.uml.propertysections.event.SignalChooserForEvent;
import org.opaeum.eclipse.uml.propertysections.subsections.StackDetailsSubsection;

public class EventStackDetailsSubsection extends StackDetailsSubsection<Event> {
	private static final String ABSOLUTE_TIME_EVENT = "On a specific date";
	private static final String RELATIVE_TIME_EVENT = "Time passed since this node was entered";
	private static final String OPERATION_TEXT = "Operation called on the process or its context";
	private static final String SIGNAL_TEXT = "Signal received by the process or its context";
	private static final String CHANGE_EVENT = "A condition becoming true";
	private RelativeTimeEventDetailsComposite relativeTimeEventDetailsComposite;
	private ChangeEventDetailsComposite changeComposite;
	private AbsoluteTimeEventDetailsComposite absoluteTimeEventDetailsComposite;
	private SignalChooserForEvent signalChooserComposite;
	private OperationChooserForEvent operationChooserComposite;
	private int labelWidth;
	protected EventStackDetailsSubsection(TabbedPropertySheetWidgetFactory factory,Composite parent,int labelWidth,String eventDetailsLabel){
		super(factory,parent,eventDetailsLabel);
		this.labelWidth = labelWidth;
	}
	@Override
	protected void addLayers(Composite eventDetailsComposite,TabbedPropertySheetWidgetFactory factory){
		changeComposite = new ChangeEventDetailsComposite(factory, eventDetailsComposite);
		addLayer(CHANGE_EVENT, changeComposite);
		relativeTimeEventDetailsComposite = new RelativeTimeEventDetailsComposite(factory, eventDetailsComposite);
		addLayer(RELATIVE_TIME_EVENT, relativeTimeEventDetailsComposite);
		absoluteTimeEventDetailsComposite = new AbsoluteTimeEventDetailsComposite(factory, eventDetailsComposite);
		addLayer(ABSOLUTE_TIME_EVENT, absoluteTimeEventDetailsComposite);
		signalChooserComposite = new SignalChooserForEvent(eventDetailsComposite, factory);
		addLayer(SIGNAL_TEXT, signalChooserComposite);
		operationChooserComposite = new OperationChooserForEvent(eventDetailsComposite, factory);
		addLayer(OPERATION_TEXT, operationChooserComposite);
	}
	@Override
	protected String getKeyFor(List<Event> eObjectList){
		if(eObjectList.isEmpty()){
			return null;
		}else if(eObjectList.get(0) instanceof ChangeEvent){
			return CHANGE_EVENT;
		}else if(eObjectList.get(0) instanceof TimeEvent){
			TimeEvent te = (TimeEvent) eObjectList.get(0);
			return te.isRelative() ? RELATIVE_TIME_EVENT : ABSOLUTE_TIME_EVENT;
		}else if(eObjectList.get(0) instanceof CallEvent){
			return OPERATION_TEXT;
		}else if(eObjectList.get(0) instanceof SignalEvent){
			return SIGNAL_TEXT;
		}
		return null;
	}
	public void setLabelWidth(int labelWidth2){
		Object layoutData = changeComposite.getContentPane().getLayout();
		changeComposite.setLabelWidth(labelWidth-10);
		relativeTimeEventDetailsComposite.setLabelWidth(labelWidth-10);
		absoluteTimeEventDetailsComposite.setLabelWidth(labelWidth-10);
		signalChooserComposite.setLabelWidth(labelWidth-10);
		operationChooserComposite.setLabelWidth(labelWidth-10);
		setLayoutData(null);
	}
}
