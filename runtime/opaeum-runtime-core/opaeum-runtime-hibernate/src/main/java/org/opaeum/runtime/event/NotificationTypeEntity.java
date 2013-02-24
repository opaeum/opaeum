package org.opaeum.runtime.event;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.hibernate.domain.AbstractPersistentOpaeumIdEnum;
import org.opaeum.runtime.event.NotificationType;

@Table(name="notification_type")
@Entity(name="NotificationTypeEntity")
public class NotificationTypeEntity extends AbstractPersistentOpaeumIdEnum {


	/** Constructor for NotificationTypeEntity
	 * 
	 * @param e 
	 */
	public NotificationTypeEntity(NotificationType e) {
		super(e);
	}
	
	/** Default constructor for NotificationTypeEntity
	 */
	public NotificationTypeEntity() {
	}


}