package org.opaeum.runtime.event;

import org.opaeum.runtime.domain.IEnum;

public enum NotificationType implements IEnum{
	EMAIL,
	FACE_BOOK,
	LINKED_IN,
	SMS,
	PREFERRED;

	@Override
	public String getUuid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getOpaeumId() {
		// TODO Auto-generated method stub
		return 0;
	}
}
