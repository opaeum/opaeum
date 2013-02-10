package org.opaeum.hibernate.domain;

import org.opaeum.runtime.domain.IPersistentObject;

public interface IAnyValue{
	public void updateBeforeFlush(InternalHibernatePersistence hp);
	IPersistentObject getValue();
}
