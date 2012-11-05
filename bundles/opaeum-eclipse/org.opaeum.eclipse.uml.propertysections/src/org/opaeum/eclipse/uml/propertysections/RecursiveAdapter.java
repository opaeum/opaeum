package org.opaeum.eclipse.uml.propertysections;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.opaeum.emf.extraction.StereotypesHelper;

public abstract class RecursiveAdapter extends EContentAdapter{
	Set<EObject> subscriptions = new HashSet<EObject>();
	public void subscribeTo(EObject e,int levels){
		if(levels > 0){
			if(!e.eAdapters().contains(this)){
				e.eAdapters().add(this);
				subscriptions.add(e);
			}
			for(EObject eObject:e.eContents()){
				subscribeTo(eObject, levels - 1);
			}
			if(e instanceof DynamicEObjectImpl){
				e=UMLUtil.getBaseElement(e);
			}
			if(e instanceof Element){
				for(EObject sa:((Element) e).getStereotypeApplications()){
					if(!sa.eAdapters().contains(this)){
						sa.eAdapters().add(this);
						subscriptions.add(sa);
					}
					for(EObject c:sa.eContents()){
						subscribeTo(c, levels - 1);
					}
				}
				EAnnotation ann = StereotypesHelper.getNumlAnnotation((Element) e);
				if(ann != null){
					for(EObject eObject:ann.getContents()){
						subscribeTo(eObject, levels - 1);
					}
				}
			}
		}
	}
	private void syncNotifyChanged(final Notification msg){
		Display.getDefault().syncExec(new Runnable(){
			public void run(){
				safeNotifyChanged(msg);
			}
		});
	}
	protected abstract void safeNotifyChanged(Notification msg);
	@Override
	public final void notifyChanged(Notification msg){
		super.notifyChanged(msg);
		if(Display.getCurrent() != Display.getDefault()){
			syncNotifyChanged(msg);
		}else{
			if(msg.getNotifier() instanceof EObject && ((EObject) msg.getNotifier()).eResource() == null){
				EObject deleted = (EObject) msg.getNotifier();
				deleted.eAdapters().remove(this);
				TreeIterator<EObject> eAllContents = deleted.eAllContents();
				while(eAllContents.hasNext()){
					EObject eObject = (EObject) eAllContents.next();
					eObject.eAdapters().remove(this);
				}
			}else{
				safeNotifyChanged(msg);
			}
		}
	}
	public void unsubscribe(){
		for(EObject eObject:new HashSet<EObject>(this.subscriptions)){
			while(eObject.eAdapters().contains(this)){
				eObject.eAdapters().remove(this);
			}
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
