package org.opaeum.runtime.event;

public interface INotificationConfiguration{
	String getAddressFor(NotificationType type);
	NotificationType getPreferredNotificationType();
}
