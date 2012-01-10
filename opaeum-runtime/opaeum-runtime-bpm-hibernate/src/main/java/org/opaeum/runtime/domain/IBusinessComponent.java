package org.opaeum.runtime.domain;

import org.opaeum.runtime.event.INotificationReceiver;



public interface IBusinessComponent extends IPersistentObject, INotificationReceiver{
	IOpaeumGroup getOpaeumGroup();
}
