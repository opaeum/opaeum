package org.opaeum.uimodeler.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;

public class InMemoryNotationCommandQueue extends EContentAdapter{
	private static Map<IPath,InMemoryNotationCommandQueue> instances=new HashMap<IPath,InMemoryNotationCommandQueue>();
	public static InMemoryNotationCommandQueue getInstance(IPath iPath){
		InMemoryNotationCommandQueue inMemoryNotationCommandQueue = instances.get(iPath);
		if(inMemoryNotationCommandQueue==null){
			instances.put(iPath, inMemoryNotationCommandQueue=new InMemoryNotationCommandQueue());
		}
		return inMemoryNotationCommandQueue;
	}
	public static void remove(IFile f){
		instances.remove(f);
	}
	private List<Notification> notifications=new ArrayList<Notification>();
	public List<Notification> getNotificationQueue(){
		List<Notification> notifications2 = notifications;
		this.notifications=new ArrayList<Notification>();
		return notifications2;
	}
	@Override
	public void notifyChanged(Notification notification){
		super.notifyChanged(notification);
//		if(notification.getNotifier() instanceof EObject && notification.getFeature() instanceof EStructuralFeature){
			notifications.add(notification);
			
//		}
	}
}
