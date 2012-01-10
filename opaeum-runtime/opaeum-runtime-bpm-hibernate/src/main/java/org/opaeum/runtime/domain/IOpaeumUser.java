package org.opaeum.runtime.domain;

import java.util.Collection;

import org.opaeum.runtime.event.INotificationReceiver;

/**
 * Maintain in admin gui, remains outside the uml model
 */
public interface IOpaeumUser extends INotificationReceiver{
	public String getUsername();
	public Collection<IBusinessRole> getRoles();
}
