package org.opaeum.runtime.persistence;

import java.util.Collection;

import org.opaeum.runtime.domain.IPersistentObject;



/**
 * Conversational Persistence only flushes to the database when flush is explicitly called.
 * It is only closed when closed is explicitly called.
 * It is generally transaction agnostic,but if no transaction is active, it will create a transaction just
 * before flushing and if the flush was successful, the transaction will be committed 
 * @author ampie
 *
 */
public interface ConversationalPersistence extends AbstractPersistence{
	void close();
	void flush();
	boolean containsStaleObjects();
	Collection<IPersistentObject> refreshStaleObjects();
	void upgradeStaleObjects();
	
}
