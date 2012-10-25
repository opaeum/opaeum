package org.opaeum.eclipse.uml.propertysections.bpmprofile;

import java.awt.Desktop.Action;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractOpaeumPropertySection;
import org.opaeum.emf.extraction.StereotypesHelper;

public class AcceptDeadlineDeadlineSection extends AbstractOpaeumPropertySection{
	Composite checkBoxComposite;
	private Composite parent;
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getAcceptEventAction_Trigger();
	}
	@Override
	public Control getPrimaryInput(){
		throw new IllegalStateException();
	}
	@Override
	public boolean shouldUseExtraSpace(){
		return true;
	}
	@Override
	public String getLabelText(){
		return "Select Deadlines";
	}
	protected void createWidgets(Composite composite){
		this.parent = composite;
		getWidgetFactory().createCLabel(composite, getLabelText());
		checkBoxComposite = getWidgetFactory().createGroup(composite, "Available Deadlines");
		FormData fd = new FormData();
		fd.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[0]));
		fd.right = new FormAttachment(100);
		fd.bottom = new FormAttachment(100);
		this.checkBoxComposite.setLayoutData(fd);
		checkBoxComposite.setLayoutData(fd);
		checkBoxComposite.setLayout(new GridLayout(1, true));
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		refreshCheckBoxes();
	}
	private void refreshCheckBoxes(){
		Control[] children = checkBoxComposite.getChildren();
		for(Control control:children){
			control.dispose();
		}
		for(ActivityEdge e:getAction().getIncomings()){
			if(e.getSource() instanceof OpaqueAction){
				List<Trigger> unboundTriggers = new ArrayList<Trigger>(getAction().getTriggers());
				for(EObject c:StereotypesHelper.getNumlAnnotation(e.getSource()).getContents()){
					if(c instanceof TimeEvent){
						final TimeEvent deadline = (TimeEvent) c;
						final Button btn = getWidgetFactory().createButton(checkBoxComposite, deadline.getName(), SWT.CHECK);
						for(Trigger t:getAction().getTriggers()){
							if(t.getEvent() == deadline){
								btn.setSelection(true);
								unboundTriggers.remove(t);
							}
						}
						// TODO if deadline not available, remove trigger
						btn.addSelectionListener(new SelectionListener(){
							public void widgetSelected(SelectionEvent e){
								if(btn.getSelection()){
									getAction().createTrigger(deadline.getName()).setEvent(deadline);
								}else{
									List<Trigger> triggers = new ArrayList<Trigger>(getAction().getTriggers());
									for(Trigger trigger:triggers){
										if(trigger.getEvent() == deadline){
											getAction().getTriggers().remove(trigger);
										}
									}
								}
							}
							public void widgetDefaultSelected(SelectionEvent e){
							}
						});
					}
				}
				for(Trigger trigger:unboundTriggers){
					getAction().getTriggers().remove(trigger);
				}
			}
		}
		checkBoxComposite.layout();
		// FormData fd = (FormData) checkBoxComposite.getLayoutData();
		// if(checkBoxComposite.getChildren().length > 0){
		// fd.height = checkBoxComposite.getChildren().length * 35;
		// }else{
		// fd.height=20;
		// }
		// parent.setSize(parent.getSize().x, fd.height+5);
		parent.getParent().getParent().layout();
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
}
