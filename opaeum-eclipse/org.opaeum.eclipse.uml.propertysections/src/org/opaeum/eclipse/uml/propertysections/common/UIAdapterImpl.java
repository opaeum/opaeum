package org.opaeum.eclipse.uml.propertysections.common;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.swt.widgets.Display;

public abstract class UIAdapterImpl extends AdapterImpl{
	public final void notifyChanged(Notification msg){
		if(Display.getCurrent() != Display.getDefault()){
			syncNotifyChanged(msg);
		}else{
			safeNotifyChanged(msg);
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
}
