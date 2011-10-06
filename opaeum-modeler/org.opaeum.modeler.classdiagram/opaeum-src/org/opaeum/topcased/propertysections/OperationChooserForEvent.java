package org.opaeum.topcased.propertysections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Trigger;

public class OperationChooserForEvent extends ObjectChooserComposite{
	public OperationChooserForEvent(Composite parent,int labelWidth,TabbedPropertySheetWidgetFactory toolkit){
		super(parent, "Operation", toolkit, labelWidth);
	}
	protected void updateTrigger(){
		if(cSingleObjectChooser.getSelection() instanceof Operation){
			trigger.setEvent(EventFinder.findOrCreateEvent(trigger.getOwner(), (Operation) cSingleObjectChooser.getSelection()));
		}
	}
	@Override
	public void setTrigger(Trigger t){
		super.setTrigger(t);
		Element o = t.getOwner();
		while(!(o instanceof Classifier)){
			o = o.getOwner();
		}
		Classifier a = (Classifier) o;
		List<Operation> choices = new ArrayList<Operation>(a.getAllOperations());
		if(a instanceof Behavior && ((Behavior) a).getContext() != null){
			choices.addAll(((Behavior) a).getContext().getAllOperations());
		}
		cSingleObjectChooser.setChoices(choices.toArray());
		Event event = trigger.getEvent();
		if(event instanceof CallEvent){
			cSingleObjectChooser.setSelection(((CallEvent) event).getOperation());
		}
	}
}
