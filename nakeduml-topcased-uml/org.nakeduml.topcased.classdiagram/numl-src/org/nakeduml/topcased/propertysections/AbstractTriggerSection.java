package org.nakeduml.topcased.propertysections;

import java.util.List;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public abstract class AbstractTriggerSection extends AbstractTabbedPropertySection{
	private static final String ABSOLUTE_TIME_EVENT = "On a specific date";
	private static final String RELATIVE_TIME_EVENT = "Time passed since this node was entered";
	private static final String OPERATION_TEXT = "Operation called on the process or its context";
	private static final String SIGNAL_TEXT = "Signal received by the process or its context";
	private static final String CHANGE_EVENT = "A condition becoming true";
	protected CLabel eventTypeLabel;
	protected CCombo eventTypeCombo;
	protected Group eventDetailsComposite;
	protected ObjectChooserComposite signalChooserComposite;
	protected ObjectChooserComposite operationChooserComposite;
	protected RelativeTimeEventDetailsComposite relativeTimeEventDetailsComposite;
	protected AbsoluteTimeEventDetailsComposite absoluteTimeEventDetailsComposite;
	private StackLayout stack;
	private ChangeEventDetailsComposite changeComposite;
	public AbstractTriggerSection(){
		super();
	}
	protected abstract List<Trigger> getTriggers();
	protected abstract NamedElement getOwner();
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
		eventTypeLabel = getWidgetFactory().createCLabel(composite, getEventTypeLabel());
		eventTypeCombo = getWidgetFactory().createCCombo(composite);
		eventTypeCombo.setItems(new String[]{
				"",SIGNAL_TEXT,OPERATION_TEXT,RELATIVE_TIME_EVENT,ABSOLUTE_TIME_EVENT,CHANGE_EVENT
		});
		eventTypeCombo.addSelectionListener(new SelectionListener(){
			public void widgetSelected(SelectionEvent e){
				String text = eventTypeCombo.getText();
				

				if(SIGNAL_TEXT.equals(text)){
					forceTriggerCreation();
					if(!(forceTriggerCreation().getEvent() instanceof SignalEvent)){
						forceTriggerCreation().setEvent(null);
					}
					selectSignalState();
				}else if(CHANGE_EVENT.equals(text)){
					if(!(forceTriggerCreation().getEvent() instanceof ChangeEvent)){
						forceTriggerCreation().setEvent(null);
					}
					selectChangeSignalState();
				}else if(OPERATION_TEXT.equals(text)){
					if(!(forceTriggerCreation().getEvent() instanceof CallEvent)){
						forceTriggerCreation().setEvent(null);
					}
					selectOperationState();
				}else if(RELATIVE_TIME_EVENT.equals(text)){
					if(!(forceTriggerCreation().getEvent() instanceof TimeEvent && ((TimeEvent) getTriggers().get(0).getEvent()).isRelative())){
						forceTriggerCreation().setEvent(null);
					}
					selectRelativeTimeEventState();
				}else if(ABSOLUTE_TIME_EVENT.equals(text)){
					if(!(forceTriggerCreation().getEvent() instanceof TimeEvent && !((TimeEvent) getTriggers().get(0).getEvent()).isRelative())){
						forceTriggerCreation().setEvent(null);
					}
					selectAbsoluteTimeEventState();
				}else{
					//TODO do in commmand
					getTriggers().clear();
					stack.topControl=null;
				}
				refresh();
			}
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		eventDetailsComposite = getWidgetFactory().createGroup(composite,"Event Details");
		this.stack = new StackLayout();
		
		eventDetailsComposite.setLayout(stack);
		int labelWidth = getStandardLabelWidth(composite)-5;
		changeComposite=new ChangeEventDetailsComposite(getEditingDomain(), eventDetailsComposite, labelWidth, getWidgetFactory());
		relativeTimeEventDetailsComposite = new RelativeTimeEventDetailsComposite(getEditingDomain(), getWidgetFactory(), eventDetailsComposite, labelWidth);
		absoluteTimeEventDetailsComposite = new AbsoluteTimeEventDetailsComposite(getEditingDomain(),getWidgetFactory(), eventDetailsComposite, labelWidth);
		signalChooserComposite = new SignalChooserForEvent(eventDetailsComposite, labelWidth,getWidgetFactory());
		operationChooserComposite = new OperationChooserForEvent(eventDetailsComposite, labelWidth,getWidgetFactory());
	}
	private Trigger forceTriggerCreation(){
		if(getTriggers().isEmpty()){
			Trigger trigger = UMLFactory.eINSTANCE.createTrigger();
			trigger.setName(getOwner().getName() + "Trigger");
			getEditingDomain().getCommandStack().execute(AddCommand.create(getEditingDomain(), getOwner(), getTriggerStructuralFeature(), trigger));
		}
		return getTriggers().get(0);
	}
	protected String getEventDetailsLabel(){
		return "Event Details";
	}
	protected String getEventTypeLabel(){
		return "Event Type";
	}
	private int getStandardLabelWidth(Composite composite){
		return getStandardLabelWidth(composite, new String[]{
			"VERY LONG LABEL"
		});
	}
	@Override
	protected void setSectionData(Composite composite){
		FormData data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(eventTypeCombo, -ITabbedPropertyConstants.HSPACE);
		eventTypeLabel.setLayoutData(data);
		data = new FormData();
		data.left = new FormAttachment(0, getStandardLabelWidth(composite));
		data.right = new FormAttachment(100, 0);
		data.height = 18;
		eventTypeCombo.setLayoutData(data);
		FormData edcData= new FormData();
		edcData.left = new FormAttachment(0, 0);
		edcData.top = new FormAttachment(eventTypeCombo);
		edcData.right = new FormAttachment(100, 0);
		edcData.bottom = new FormAttachment(100, 0);
		eventDetailsComposite.setLayoutData(edcData);
		eventDetailsComposite.layout();
	}
	@Override
	protected String getLabelText(){
		return "Trigger";
	}
	@Override
	public void refresh(){
		super.refresh();
		relativeTimeEventDetailsComposite.layout();
		signalChooserComposite.layout();
		operationChooserComposite.layout();
		changeComposite.layout();
		eventDetailsComposite.layout();
		
	}
	private void selectChangeSignalState(){
		stack.topControl = changeComposite;
		changeComposite.setTrigger(getTriggers().get(0));
	}
	private void selectAbsoluteTimeEventState(){
		stack.topControl = absoluteTimeEventDetailsComposite;
		absoluteTimeEventDetailsComposite.setTrigger(getTriggers().get(0));
	}
	private void selectRelativeTimeEventState(){
		stack.topControl = relativeTimeEventDetailsComposite;
		relativeTimeEventDetailsComposite.setTrigger(getTriggers().get(0));
	}
	private void selectOperationState(){
		operationChooserComposite.setTrigger(getTriggers().get(0));
		stack.topControl = operationChooserComposite;
	}
	private void selectSignalState(){
		signalChooserComposite.setTrigger(getTriggers().get(0));
		stack.topControl = signalChooserComposite;
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		
		Event event = getTriggers().isEmpty()?null: getTriggers().get(0).getEvent();
		if(event instanceof SignalEvent){
			eventTypeCombo.setText(SIGNAL_TEXT);
			selectSignalState();
		}else if(event instanceof CallEvent){
			eventTypeCombo.setText(OPERATION_TEXT);
			selectOperationState();
		}else if(event instanceof ChangeEvent){
			eventTypeCombo.setText(CHANGE_EVENT);
			selectChangeSignalState();
		}else if(event instanceof TimeEvent){
			if(((TimeEvent) event).isRelative()){
				
				eventTypeCombo.setText(RELATIVE_TIME_EVENT);
				selectRelativeTimeEventState();
			}else{
				eventTypeCombo.setText(ABSOLUTE_TIME_EVENT);
				selectAbsoluteTimeEventState();
			}
		}else{
			stack.topControl=null;
			eventTypeCombo.setText("");
		}
	}
	protected abstract EReference getTriggerStructuralFeature();
}