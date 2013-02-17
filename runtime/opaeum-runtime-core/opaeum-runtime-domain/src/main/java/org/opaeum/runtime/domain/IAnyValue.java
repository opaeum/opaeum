package org.opaeum.runtime.domain;

import org.opaeum.runtime.persistence.AbstractPersistence;

public interface IAnyValue{
	public void updateBeforeFlush(AbstractPersistence hp);
	IPersistentObject getValue();
	public abstract IPersistentObject retrieveValue(AbstractPersistence p);
}
