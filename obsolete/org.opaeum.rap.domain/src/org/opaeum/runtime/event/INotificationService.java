package org.opaeum.runtime.event;

import java.util.Collection;

import org.opaeum.runtime.domain.ISignal;

public interface INotificationService{
	public void sendNotification(ISignal signal,INotificationReceiver from,Collection<? extends INotificationReceiver> to,Collection<? extends INotificationReceiver> cc,
			Collection<INotificationReceiver> bcc);
	public void registerTemplate(Class<? extends ISignal> type,String templateText);
}
