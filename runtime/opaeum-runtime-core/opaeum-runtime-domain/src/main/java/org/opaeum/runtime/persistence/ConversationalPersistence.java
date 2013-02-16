package org.opaeum.runtime.persistence;

import java.util.Collection;
import java.util.Map;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.persistence.event.ChangedEntity;



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
	Collection<IPersistentObject> reloadStaleObjects();
	Map<ChangedEntity,IPersistentObject> synchronizeWithDatabaseAndFindConflicts();
	void overwriteConflictsFromDatabase(Map<ChangedEntity,IPersistentObject> changes);
	void overwriteDatabaseWithConflicts(Map<ChangedEntity,IPersistentObject> changes);
	
}
