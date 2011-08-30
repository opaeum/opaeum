package org.nakeduml.topcased.activitydiagram.propertysections;

import java.awt.Desktop.Action;
import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UMLPackage;
import org.nakeduml.eclipse.ImportLibraryAction;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public class AcceptTaskEventActionTaskEventSection extends AbstractTabbedPropertySection{
	Group checkBoxComposite;
	private CLabel label;
	private Composite parent;
	private List<CallEvent> taskEvents;
	private Group eventsWithoutUser;
	private Group eventsWithUser;
	private Button radioForEventsWIthNewUser;
	private Button radioForEventsWithoutNewUser;
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getAcceptEventAction_Trigger();
	}
	@Override
	protected String getLabelText(){
		return "Select Task Event";
	}
	protected void createWidgets(Composite composite){
		this.parent = composite;
		label = getWidgetFactory().createCLabel(composite, getLabelText());
		checkBoxComposite = getWidgetFactory().createGroup(composite, "Available Task Events");
		GridLayout layout = new GridLayout(2, true);
		checkBoxComposite.setLayout(layout);
		this.radioForEventsWIthNewUser = getWidgetFactory().createButton(checkBoxComposite, "Events With New User", SWT.RADIO);
		radioForEventsWIthNewUser.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL));
		this.radioForEventsWithoutNewUser = getWidgetFactory().createButton(checkBoxComposite, "Events Without New User", SWT.RADIO);
		radioForEventsWIthNewUser.addSelectionListener(new SelectionListener(){
			public void widgetSelected(SelectionEvent e){
				setEnablement(true);
			}
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		radioForEventsWithoutNewUser.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL));
		radioForEventsWithoutNewUser.addSelectionListener(new SelectionListener(){
			public void widgetSelected(SelectionEvent e){
				setEnablement(true);
			}
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		this.eventsWithUser = getWidgetFactory().createGroup(checkBoxComposite, "");
		eventsWithUser.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL | GridData.FILL_BOTH));
		eventsWithUser.setLayout(new GridLayout(1, true));
		this.eventsWithoutUser = getWidgetFactory().createGroup(checkBoxComposite, "");
		eventsWithoutUser.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL | GridData.FILL_BOTH));
		eventsWithoutUser.setLayout(new GridLayout(1, true));
		FormData fd = new FormData();
		fd.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[0]));
		fd.right = new FormAttachment(100);
		fd.bottom = new FormAttachment(100);
		fd.top = new FormAttachment(0, 0);
		checkBoxComposite.setLayoutData(fd);
	}
	private void setEnablement(boolean removeTriggers){
		setEnablement(radioForEventsWIthNewUser, eventsWithUser,removeTriggers);
		setEnablement(radioForEventsWithoutNewUser, eventsWithoutUser,removeTriggers);
	}
	private void setEnablement(final Button radio,Group asdf,boolean removeTriggers){
		asdf.setEnabled(radio.getSelection());
		Control[] children = asdf.getChildren();
		for(Control control:children){
			if(control instanceof Button){
				Button button = (Button) control;
				if(removeTriggers){
					removeTriggers((CallEvent) button.getData());
					button.setSelection(false);
				}
				button.setEnabled(radio.getSelection());
			}
		}
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		if(this.taskEvents == null){
			this.taskEvents = new ArrayList<CallEvent>();
			Model lib = ImportLibraryAction.importLibraryIfNecessary(getAction().getModel(), StereotypeNames.OPIUM_BPM_LIBRARY);
			for(PackageableElement pe:lib.getNestedPackage("events").getPackagedElements()){
				if(pe instanceof CallEvent){
					this.taskEvents.add((CallEvent) pe);
				}
			}
		}
		refreshCheckBoxes();
	}
	private void refreshCheckBoxes(){
		for(Control control:eventsWithoutUser.getChildren()){
			control.dispose();
		}
		for(Control control:eventsWithUser.getChildren()){
			control.dispose();
		}
		radioForEventsWIthNewUser.setSelection(false);
		radioForEventsWithoutNewUser.setSelection(false);
		for(ActivityEdge e:getAction().getIncomings()){
			if(e.getSource() instanceof OpaqueAction){
				for(final CallEvent event:this.taskEvents){
					final Button btn;
					if(event.getOperation().getOwnedParameters().size() == 1){
						btn = getWidgetFactory().createButton(eventsWithUser, event.getName(), SWT.CHECK);
					}else{
						btn = getWidgetFactory().createButton(eventsWithoutUser, event.getName(), SWT.CHECK);
					}
					btn.setData(event);
					for(Trigger t:getAction().getTriggers()){
						if(t.getEvent() == event){
							btn.setSelection(true);
							if(event.getOperation().getOwnedParameters().size() == 1){
								radioForEventsWIthNewUser.setSelection(true);
							}else{
								radioForEventsWithoutNewUser.setSelection(true);
							}
						}
					}
					btn.addSelectionListener(new SelectionListener(){
						public void widgetSelected(SelectionEvent e){
							CallEvent data = (CallEvent) ((Button) e.getSource()).getData();
							if(btn.getSelection()){
								getAction().createTrigger(data.getName()).setEvent(data);
							}else{
								removeTriggers(data);
							}
						}
						public void widgetDefaultSelected(SelectionEvent e){
						}
					});
				}
				break;
			}
		}
		setEnablement(false);
		FormData fd = (FormData) checkBoxComposite.getLayoutData();
		int rows = Math.max(eventsWithoutUser.getChildren().length, eventsWithUser.getChildren().length);
		fd.height = rows * 38 + 45;
		parent.setSize(parent.getSize().x, fd.height + 5);
		parent.getParent().getParent().layout();
		if(radioForEventsWIthNewUser.getSelection() && radioForEventsWithoutNewUser.getSelection()){
			System.out.println(getAction().getTriggers());
		}
		eventsWithoutUser.layout();
		eventsWithUser.layout();
		super.refresh();
	}
	@Override
	protected void handleModelChanged(Notification msg){
		super.handleModelChanged(msg);
		if(msg.getNotifier() == getAction()){
			switch(msg.getFeatureID(Action.class)){
			case UMLPackage.ACTION__INCOMING:
				if(msg.getEventType() == Notification.ADD || msg.getEventType() == Notification.REMOVE){
					refreshCheckBoxes();
				}
				break;
			}
		}
	}
	public AcceptEventAction getAction(){
		return (AcceptEventAction) getEObject();
	}
	private void removeTriggers(CallEvent data){
		List<Trigger> triggers = new ArrayList<Trigger>(getAction().getTriggers());
		for(Trigger trigger:triggers){
			if(trigger.getEvent() == data){
				getAction().getTriggers().remove(trigger);
			}
		}
	}
}
