package org.opaeum.eclipse.uml.propertysections.event;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.ObjectChooserComposite;
import org.topcased.tabbedproperties.utils.ITypeCacheAdapter;
import org.topcased.tabbedproperties.utils.TypeCacheAdapter;

public class SignalChooserForEvent extends ObjectChooserComposite{
	public SignalChooserForEvent(Composite parent,int labelWidth,TabbedPropertySheetWidgetFactory f){
		super(parent, "Signal", f, labelWidth);
	}
	protected void updateElement(){
		if(cSingleObjectChooser.getSelection() instanceof Signal){
			trigger.setEvent(EventFinder.findOrCreateEvent(trigger.getOwner(), (Signal) cSingleObjectChooser.getSelection()));
		}
	}
	@Override
	public void setTrigger(Trigger t){
		super.setTrigger(t);
		List<Object> choices = new ArrayList<Object>();
		choices.add("");
		ITypeCacheAdapter typeCacheAdapter = TypeCacheAdapter.getExistingTypeCacheAdapter(trigger);
		choices.addAll(typeCacheAdapter.getReachableObjectsOfType(trigger, UMLPackage.eINSTANCE.getSignal()));
		cSingleObjectChooser.setChoices(choices.toArray());
		if(trigger.getEvent() instanceof SignalEvent){
			cSingleObjectChooser.setSelection(((SignalEvent) t.getEvent()).getSignal());
		}
	}
}
