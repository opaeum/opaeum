package org.opaeum.runtime.event;

import org.opaeum.runtime.contact.IPersonEMailAddress;
import org.opaeum.runtime.contact.IPersonPhoneNumber;
import org.opaeum.runtime.domain.IPersistentObject;

public interface INotificationReceiver extends IPersistentObject{
	IPersonEMailAddress getPreferredEMailAddress();
	IPersonPhoneNumber getPreferredPhoneNumber();
	NotificationType getPreferredNotificationType();
}
