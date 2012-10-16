package org.opaeum.hibernate.domain;

import java.util.Collection;

import org.opaeum.runtime.domain.ISignal;
import org.opaeum.runtime.event.INotificationReceiver;
import org.opaeum.runtime.event.INotificationService;

public class EMailNotificationService implements INotificationService{
	@Override
	public void sendNotification(ISignal signal,INotificationReceiver from,Collection<? extends INotificationReceiver> to,
			Collection<? extends INotificationReceiver> cc,Collection<INotificationReceiver> bcc){
		// TODO Auto-generated method stub
	}
	@Override
	public void registerTemplate(Class<? extends ISignal> type,String templateText){
		// TODO Auto-generated method stub
	}
}
