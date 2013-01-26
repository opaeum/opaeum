package org.opaeum.runtime.event;

import java.util.Set;

public interface INotificationHandler extends ISignalEventHandler{
	public Set<INotificationReceiver> getBcc();
	public Set<INotificationReceiver> getCc();
	public INotificationReceiver getFrom();
	public Set<INotificationReceiver> getTo();
}
