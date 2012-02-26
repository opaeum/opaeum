package org.opaeum.runtime.domain;

import org.opaeum.runtime.event.INotificationReceiver;

/**
 * Maintain in admin gui, remains outside the uml model
 * 
 * @author ampie
 * 
 */
public interface IOpaeumGroup extends INotificationReceiver{
	String getName();
}
