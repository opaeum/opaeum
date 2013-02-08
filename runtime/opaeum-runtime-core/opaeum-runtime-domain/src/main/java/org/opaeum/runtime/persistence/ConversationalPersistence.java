package org.opaeum.runtime.persistence;

import java.util.Collection;

import org.opaeum.runtime.domain.IPersistentObject;




public interface ConversationalPersistence extends AbstractPersistence{
	void close();
	void flush();
	boolean containsStaleObjects();
	Collection<IPersistentObject> refreshStaleObjects();
	void upgradeStaleObjects();
	
}
