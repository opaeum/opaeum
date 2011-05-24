package org.nakeduml.topcased.propertysections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.uml2.common.edit.provider.IItemQualifiedTextProvider;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;
import org.topcased.tabbedproperties.sections.widgets.CSingleObjectChooser;
import org.topcased.tabbedproperties.utils.ITypeCacheAdapter;
import org.topcased.tabbedproperties.utils.TypeCacheAdapter;

public abstract class AbstractTriggerSection extends AbstractTabbedPropertySection{
	private static final String ABSOLUTE_TIME_EVENT = "On a specific date";
	private static final String RELATIVE_TIME_EVENT = "Time passed since this node was entered";
	private static final String OPERATION_TEXT = "Operation called on the process or its context";
	private static final String SIGNAL_TEXT = "Signal received by the process or its context";
	class ObjectChooserComposite extends Composite{
		protected CLabel label;
		protected CSingleObjectChooser cSingleObjectChooser;
		public ObjectChooserComposite(Composite parent){
			super(parent, SWT.NONE);
			setBackground(parent.getBackground());
			super.setLayout(new FormLayout());
			FormData fd = new FormData();
			label = getWidgetFactory().createCLabel(this, "Choose Operation");
			label.setLayoutData(fd);
			cSingleObjectChooser = new CSingleObjectChooser(this, getWidgetFactory(), SWT.NONE);
			cSingleObjectChooser.setLabelProvider(getLabelProvider());
			cSingleObjectChooser.setAdvancedLabelProvider(getAdvancedLabeProvider());
			cSingleObjectChooser.addSelectionListener(new SelectionAdapter(){
				@Override
				public void widgetSelected(SelectionEvent e){
					if(cSingleObjectChooser.getSelection() instanceof Signal){
						getTriggers().get(0).setEvent(EventFinder.findOrCreateEvent(getOwner(), (Signal) cSingleObjectChooser.getSelection()));
					}
					if(cSingleObjectChooser.getSelection() instanceof Operation){
						getTriggers().get(0).setEvent(EventFinder.findOrCreateEvent(getOwner(), (Operation) cSingleObjectChooser.getSelection()));
					}
				}
			});
			fd = new FormData();
			fd.left = new FormAttachment(0, getStandardLabelWidth(parent.getParent(), new String[]{
				getLabelText()
			}));
			fd.right = new FormAttachment(100, 0);
			cSingleObjectChooser.setLayoutData(fd);
		}
	}

	protected CLabel eventTypeLabel;
	protected CCombo eventTypeCombo;
	protected ObjectChooserComposite objectChooserComposite;
	protected CLabel eventDetailsLabel;
	protected Composite eventDetailsComposite;
	protected RelativeTimeEventDetailsComposite relativeTimeEventDetailsComposite;
	protected AbsoluteTimeEventDetailsComposite absoluteTimeEventDetailsComposite;
	private StackLayout stack;

	public AbstractTriggerSection(){
		super();
	}
	protected abstract List<Trigger> getTriggers();
	protected abstract NamedElement getOwner();

	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
		eventTypeLabel = getWidgetFactory().createCLabel(composite, getEventTypeLabel());
		eventTypeCombo = getWidgetFactory().createCCombo(composite);
		eventTypeCombo.setItems(new String[]{"",
				SIGNAL_TEXT,OPERATION_TEXT,RELATIVE_TIME_EVENT,ABSOLUTE_TIME_EVENT
		});
		eventTypeCombo.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				refresh();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		eventDetailsLabel = getWidgetFactory().createCLabel(composite, getEventDetailsLabel());
		eventDetailsComposite = getWidgetFactory().createComposite(composite);
		this.stack = new StackLayout();
		eventDetailsComposite.setLayout(stack);
		relativeTimeEventDetailsComposite = new RelativeTimeEventDetailsComposite(getWidgetFactory(), eventDetailsComposite, getStandardLabelWidth(composite));
		absoluteTimeEventDetailsComposite = new AbsoluteTimeEventDetailsComposite(getWidgetFactory(), eventDetailsComposite, getStandardLabelWidth(composite));
		objectChooserComposite = new ObjectChooserComposite(eventDetailsComposite);
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
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.top = new FormAttachment(eventTypeLabel, 0, ITabbedPropertyConstants.VSPACE);
		eventDetailsLabel.setLayoutData(data);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.top = new FormAttachment(eventDetailsLabel, 0, ITabbedPropertyConstants.VSPACE);
		data.right = new FormAttachment(100, 0);
		data.bottom = new FormAttachment(100, 0);
		data.height = 85;
		eventDetailsComposite.setLayoutData(data);
		eventDetailsComposite.layout();
		relativeTimeEventDetailsComposite.layout();
	}

	protected ILabelProvider getLabelProvider(){
		return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory());
	}

	protected ILabelProvider getAdvancedLabeProvider(){
		return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory()){
			public String getText(Object object){
				IItemQualifiedTextProvider itemQualifiedTextProvider = (IItemQualifiedTextProvider) adapterFactory.adapt(object, IItemQualifiedTextProvider.class);
				return itemQualifiedTextProvider != null ? itemQualifiedTextProvider.getQualifiedText(object) : super.getText(object);
			}
		};
	}

	@Override
	protected String getLabelText(){
		return "Trigger";
	}

	protected void handleModelChanged(Notification msg){
		Object notifier = msg.getNotifier();
		if(notifier.equals(getEObject())){
			handleModelChanged();
		}
	}

	private void handleModelChanged(){
		if(getTriggers().isEmpty()){
			Trigger trigger = UMLFactory.eINSTANCE.createTrigger();
			trigger.setName(getOwner(). getName() + "Trigger");
			getEditingDomain().getCommandStack()
					.execute(AddCommand.create(getEditingDomain(), getOwner(), UMLPackage.eINSTANCE.getAcceptEventAction_Trigger(), trigger));
		}
		calculateEventType();
		refresh();
	}

	private void calculateEventType(){
		Event event = getTriggers().get(0).getEvent();
		if(event instanceof SignalEvent){
			eventTypeCombo.setText(SIGNAL_TEXT);
		}else if(event instanceof CallEvent){
			eventTypeCombo.setText(OPERATION_TEXT);
		}else if(event instanceof TimeEvent){
			if(((TimeEvent) event).isRelative()){
				eventTypeCombo.setText(RELATIVE_TIME_EVENT);
			}else{
				eventTypeCombo.setText(ABSOLUTE_TIME_EVENT);
			}
		}else{
			eventTypeCombo.setText("");
		}
	}

	@Override
	public void refresh(){
		super.refresh();
		String text = eventTypeCombo.getText();
		if(SIGNAL_TEXT.equals(text)){
			List<Object> choices = new ArrayList<Object>();
			choices.add("");
			ITypeCacheAdapter typeCacheAdapter = TypeCacheAdapter.getExistingTypeCacheAdapter(getEObject());
			choices.addAll(typeCacheAdapter.getReachableObjectsOfType(getEObject(), UMLPackage.eINSTANCE.getSignal()));
			objectChooserComposite.cSingleObjectChooser.setChoices(choices.toArray());
			objectChooserComposite.label.setText("Choose signal");
			List<Trigger> triggers = getTriggers();
			Trigger t = triggers.get(0);
			if(t.getEvent() instanceof SignalEvent){
				objectChooserComposite.cSingleObjectChooser.setSelection(((SignalEvent) t.getEvent()).getSignal());
			}
			stack.topControl = objectChooserComposite;
		}else if(OPERATION_TEXT.equals(text)){
			Element o = getOwner();
			while(!(o instanceof Classifier)){
				o = o.getOwner();
			}
			Classifier a = (Classifier) o;
			List<Operation> choices = new ArrayList<Operation>(a.getAllOperations());
			if(a instanceof Behavior && ((Behavior)a) .getContext() != null){
				choices.addAll(((Behavior)a).getContext().getAllOperations());
			}
			objectChooserComposite.cSingleObjectChooser.setChoices(choices.toArray());
			objectChooserComposite.label.setText("Choose operation");
			List<Trigger> triggers = getTriggers();
			Event event = triggers.get(0).getEvent();
			if(event instanceof CallEvent){
				objectChooserComposite.cSingleObjectChooser.setSelection(((CallEvent) event).getOperation());
			}
			stack.topControl = objectChooserComposite;
		}else if(RELATIVE_TIME_EVENT.equals(text)){
			stack.topControl = relativeTimeEventDetailsComposite;
			relativeTimeEventDetailsComposite.setTrigger(getEditingDomain(), getTriggers().get(0));
		}else if(ABSOLUTE_TIME_EVENT.equals(text)){
			stack.topControl = absoluteTimeEventDetailsComposite;
			absoluteTimeEventDetailsComposite.setTrigger(getEditingDomain(), getTriggers().get(0));
		}else{
			stack.topControl = null;
		}
		relativeTimeEventDetailsComposite.layout();
		objectChooserComposite.layout();
		eventDetailsComposite.layout();
	}


	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		handleModelChanged();
	}
}