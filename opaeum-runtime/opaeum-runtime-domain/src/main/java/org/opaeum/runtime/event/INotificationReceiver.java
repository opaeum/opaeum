package org.opaeum.runtime.event;

import org.opaeum.runtime.contact.IPersonEMailAddress;
import org.opaeum.runtime.contact.IPersonPhoneNumber;

public interface INotificationReceiver{
	IPersonEMailAddress getPreferredEMailAddress();
	IPersonPhoneNumber getPreferredPhoneNumber();
	NotificationType getPreferredNotificationType();
}
