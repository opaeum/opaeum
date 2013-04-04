package org.opaeum.eclipse.uml.propertysections.base;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.commands.SetEventCommand;
import org.opaeum.eclipse.uml.propertysections.DummySelectionProvider;

public abstract class AbstractTriggerSection extends AbstractOpaeumPropertySection{
	private static final String ABSOLUTE_TIME_EVENT = "On a specific date";
	private static final String RELATIVE_TIME_EVENT = "Time passed since this node was entered";
	private static final String OPERATION_TEXT = "Operation called on the process or its context";
	private static final String SIGNAL_TEXT = "Signal received by the process or its context";
	private static final String CHANGE_EVENT = "A condition becoming true";
	private static final Map<String,EClass> typeMap = new HashMap<String,EClass>();
	private static final String NO_EVENT = "No Event";
	{
		typeMap.put(ABSOLUTE_TIME_EVENT, UMLPackage.eINSTANCE.getTimeEvent());
		typeMap.put(RELATIVE_TIME_EVENT, UMLPackage.eINSTANCE.getTimeEvent());
		typeMap.put(OPERATION_TEXT, UMLPackage.eINSTANCE.getCallEvent());
		typeMap.put(SIGNAL_TEXT, UMLPackage.eINSTANCE.getSignalEvent());
		typeMap.put(CHANGE_EVENT, UMLPackage.eINSTANCE.getChangeEvent());
	}
	protected CLabel eventTypeLabel;
	protected Combo eventTypeCombo;
	protected EventStackDetailsSubsection eventStackDetailsSubsection;
	public AbstractTriggerSection(){
		super();
	}
	protected abstract List<Trigger> getTriggers();
	protected abstract NamedElement getOwner();
	@Override
	public boolean shouldUseExtraSpace(){
		return true;
	}
	@Override
	public Control getPrimaryInput(){
		throw new IllegalStateException();
	}
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
		eventTypeLabel = getWidgetFactory().createCLabel(composite, getEventTypeLabel());
		eventTypeCombo = new Combo(composite, SWT.READ_ONLY);
		eventTypeCombo.setItems(new String[]{NO_EVENT,SIGNAL_TEXT,OPERATION_TEXT,RELATIVE_TIME_EVENT,ABSOLUTE_TIME_EVENT,CHANGE_EVENT});
		eventTypeCombo.addSelectionListener(new SelectionListener(){
			public void widgetSelected(SelectionEvent e){
				if(!isRefreshing()){
					String text = eventTypeCombo.getText();
					if(text.equals(NO_EVENT)){
						getEditingDomain().getCommandStack().execute(
								RemoveCommand.create(getEditingDomain(), getSelectedObject(), getTriggerStructuralFeature(), getTriggers()));
					}else{
						if(getTriggers().isEmpty()){
							Trigger trigger = UMLFactory.eINSTANCE.createTrigger();
							trigger.setName(getOwner().getName() + "Trigger");
							//This will cause handleModelChanged to be called, which in turn will refresh this sec
							getEditingDomain().getCommandStack().execute(AddCommand.create(getEditingDomain(), getOwner(), getTriggerStructuralFeature(), trigger));
						}
						getEditingDomain().getCommandStack().execute(
								new SetEventCommand(getEditingDomain(), getTriggers().get(0), typeMap.get(text), text.equals(RELATIVE_TIME_EVENT)));
						eventStackDetailsSubsection.selectionChanged(new SelectionChangedEvent(new DummySelectionProvider(), new StructuredSelection(getTriggers().get(0).getEvent())));

					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		int labelWidth = getStandardLabelWidth(composite);
		eventStackDetailsSubsection = new EventStackDetailsSubsection(getWidgetFactory(), composite, labelWidth, getEventDetailsLabel());
	}
	@Override
	public void setLabelWidth(int labelWidth){
		// TODO remove duplication with setSectionData
		eventStackDetailsSubsection.setLabelWidth(labelWidth);
		FormData data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(0, labelWidth);
		eventTypeLabel.setLayoutData(data);
		data = new FormData();
		data.left = new FormAttachment(eventTypeLabel);
		data.right = new FormAttachment(100, 0);
		data.height = 18;
		eventTypeCombo.setLayoutData(data);
		FormData edcData = new FormData();
		edcData.top = new FormAttachment(eventTypeCombo);
		edcData.left = new FormAttachment(0, 0);
		edcData.right = new FormAttachment(100, 0);
		edcData.bottom = new FormAttachment(100, 0);
		eventStackDetailsSubsection.setLayoutData(edcData);
	}
	protected String getEventDetailsLabel(){
		return "Event Details";
	}
	protected String getEventTypeLabel(){
		return "Event Type";
	}
	private int getStandardLabelWidth(Composite composite){
		return getStandardLabelWidth(composite, new String[]{"VERY LONG LABEL"});
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
		FormData edcData = new FormData();
		edcData.top = new FormAttachment(eventTypeCombo);
		edcData.left = new FormAttachment(0, 0);
		edcData.right = new FormAttachment(100, 0);
		edcData.bottom = new FormAttachment(100, 0);
		eventStackDetailsSubsection.setLayoutData(edcData);
	}
	@Override
	public String getLabelText(){
		return null;
	}
	@Override
	public void populateControls(){
		eventStackDetailsSubsection.setEditingDomain(getEditingDomain());
		if(getTriggers().size() > 0){
			Event event = getTriggers().get(0).getEvent();
			if(event == null){
				// Trigger still being created - do nothing
			}else{
				eventStackDetailsSubsection.selectionChanged(new SelectionChangedEvent(new DummySelectionProvider(), new StructuredSelection(event)));
				eventTypeCombo.setText(eventStackDetailsSubsection.getKeyFor(Arrays.asList(event)));
			}
		}else{
			eventStackDetailsSubsection.selectionChanged(new SelectionChangedEvent(new DummySelectionProvider(), new StructuredSelection()));
			eventTypeCombo.setText(NO_EVENT);
		}
	}
	protected abstract EReference getTriggerStructuralFeature();
}