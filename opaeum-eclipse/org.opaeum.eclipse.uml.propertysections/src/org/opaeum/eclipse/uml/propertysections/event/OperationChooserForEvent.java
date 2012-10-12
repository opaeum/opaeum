package org.opaeum.eclipse.uml.propertysections.event;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.uml.propertysections.base.EventSourceChooserComposite;

public class OperationChooserForEvent extends EventSourceChooserComposite{
	public OperationChooserForEvent(Composite parent,int labelWidth,TabbedPropertySheetWidgetFactory toolkit){
		super(parent, "Operation", toolkit, labelWidth);
	}
	protected void updateElement(){
		if(cSingleObjectChooser.getSelectedObject() instanceof Operation){
			EditingDomain ed = OpaeumEclipseContext.findOpenUmlFileFor(trigger).getEditingDomain();
			Event event = EventFinder.findOrCreateEvent(trigger.getOwner(), (Operation) cSingleObjectChooser.getSelectedObject() );
			Command cmd = SetCommand.create(ed, trigger,UMLPackage.eINSTANCE.getTrigger_Event(), event);
			ed.getCommandStack().execute(cmd);
		}
	}
	@Override
	public void setTrigger(Trigger t){
		super.setTrigger(t);
		Event event = trigger.getEvent();
		if(event instanceof CallEvent){
			cSingleObjectChooser.setSelection(((CallEvent) event).getOperation());
		}else{
			cSingleObjectChooser.setSelection((EObject)null);
		}
	}
	@Override
	public Object[] getChoices(){
		Classifier a = (Classifier) EmfElementFinder.getNearestClassifier(trigger);
		List<Operation> choices = new ArrayList<Operation>(a.getAllOperations());
		if(a instanceof Behavior && ((Behavior) a).getContext() != null){
			choices.addAll(((Behavior) a).getContext().getAllOperations());
		}
		return choices.toArray();
	}
}
