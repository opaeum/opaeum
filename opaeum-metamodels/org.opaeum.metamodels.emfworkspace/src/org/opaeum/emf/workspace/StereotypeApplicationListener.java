package org.opaeum.emf.workspace;

import java.util.Iterator;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.util.UMLUtil;

public class StereotypeApplicationListener extends AdapterImpl{
	@Override
	public void notifyChanged(Notification msg){
		if(msg.getEventType()==Notification.ADD && msg.getNewValue() instanceof DynamicEObjectImpl){
			EObject stereotypeApplication = (EObject) msg.getNewValue();
			Element be = UMLUtil.getBaseElement(stereotypeApplication);
			EList<Adapter> eAdapters = be.eAdapters();
			for(Adapter adapter:eAdapters){
				if(adapter instanceof StereotypeAttachable){
					stereotypeApplication.eAdapters().add(adapter);
				}
			}
		}else if(msg.getEventType()==Notification.REMOVE && msg.getOldValue() instanceof DynamicEObjectImpl){
			EObject stereotypeApplication = (EObject) msg.getOldValue();
			Iterator<Adapter> iterator = stereotypeApplication.eAdapters().iterator();
			while(iterator.hasNext()){
				if(iterator.next() instanceof StereotypeAttachable){
					iterator.remove();
				}
			}
		}
	}
}
