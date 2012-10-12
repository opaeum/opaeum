package org.opaeum.eclipse.uml.propertysections.event;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.uml.propertysections.base.EventSourceChooserComposite;

public class SignalChooserForEvent extends EventSourceChooserComposite{
	public SignalChooserForEvent(Composite parent,int labelWidth,TabbedPropertySheetWidgetFactory f){
		super(parent, "Signal", f, labelWidth);
	}
	protected void updateElement(){
		if(cSingleObjectChooser.getSelectedObject() instanceof Signal){
			EditingDomain ed = OpaeumEclipseContext.findOpenUmlFileFor(trigger).getEditingDomain();
			Event event = EventFinder.findOrCreateEvent(trigger.getOwner(), (Signal) cSingleObjectChooser.getSelectedObject() );
			Command cmd = SetCommand.create(ed, trigger,UMLPackage.eINSTANCE.getTrigger_Event(), event);
			ed.getCommandStack().execute(cmd);
		}
	}
	@Override
	public void setTrigger(Trigger t){
		super.setTrigger(t);
		if(t.getEvent() instanceof SignalEvent){
			cSingleObjectChooser.setSelection(((SignalEvent) t.getEvent()).getSignal());
		}else{
			cSingleObjectChooser.setSelection((EObject)null);
		}
	}
	@Override
	public Object[] getChoices(){
		List<Object> choices = new ArrayList<Object>();
		choices.add("");
		choices.addAll(OpaeumEclipseContext.getReachableObjectsOfType(trigger, UMLPackage.eINSTANCE.getSignal()));
		return choices.toArray();
	}
}
