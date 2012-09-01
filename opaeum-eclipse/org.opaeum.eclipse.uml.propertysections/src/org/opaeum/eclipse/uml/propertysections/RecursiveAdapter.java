package org.opaeum.eclipse.uml.propertysections;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.uml2.uml.Element;

public class RecursiveAdapter extends EContentAdapter{
	Set<EObject> subscriptions = new HashSet<EObject>();
	public void subscribeTo(EObject e,int levels){
		if(levels > 0){
			e.eAdapters().add(this);
			subscriptions.add(e);
			for(EObject eObject:e.eContents()){
				subscribeTo(eObject, levels - 1);
			}
			if(e instanceof Element)
				for(EObject sa:((Element) e).getStereotypeApplications()){
					subscriptions.add(sa);
					sa.eAdapters().add(this);
					for(EObject c:sa.eContents()){
						subscribeTo(c, levels - 1);
					}
				}
		}
	}
	@Override
	public void notifyChanged(Notification msg){
		super.notifyChanged(msg);
	}
	public void unsubscribe(){
		for(EObject eObject:new HashSet<EObject>( this.subscriptions)){
			eObject.eAdapters().remove(this);
		}
	}
	protected void addAdapter(Notifier notifier){
		super.addAdapter(notifier);
		if(notifier instanceof EObject){
			subscriptions.add((EObject) notifier);
		}
	}
	protected void removeAdapter(Notifier notifier){
		super.removeAdapter(notifier);
		if(notifier instanceof EObject){
			subscriptions.remove((EObject) notifier);
		}
	}
}
