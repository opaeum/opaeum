package org.opaeum.runtime.hibernate.test;

import java.util.Map;

import org.opaeum.opaeum_hibernate_tests.util.Opaeum_hibernate_testsEnvironment;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opaeum.runtime.persistence.event.ChangedEntity;
import org.opaeum.test.structure.PotentialPersistenceTestHelper;

public class HibernatePersistenceTestHelper extends
		PotentialPersistenceTestHelper {
	private static Opaeum_hibernate_testsEnvironment ENV;
	private static ConversationalPersistence readPersistence;
	private static ConversationalPersistence writePersistence;
	static {
		Opaeum_hibernate_testsEnvironment.INSTANCE.register();
		ENV = Opaeum_hibernate_testsEnvironment.INSTANCE;
		ENV.setIssueDdl(true);
		readPersistence = ENV.createConversationalPersistence();
		writePersistence = ENV.createConversationalPersistence();
	}
	public void clear(){
		readPersistence = ENV.createConversationalPersistence();
		writePersistence = ENV.createConversationalPersistence();
	}
	public void synch(){
		writePersistence.flush();
		Map<ChangedEntity, IPersistentObject> conflicts = readPersistence.synchronizeWithDatabaseAndFindConflicts();
		readPersistence.overwriteConflictsFromDatabase(conflicts);
	}
	public <T extends IPersistentObject> T read(T t) {
		return readPersistence.find(IntrospectionUtil.getOriginalClass(t),
				t.getId());
	}

	public void persist(IPersistentObject... po) {
		for (IPersistentObject o : po) {
			if (o instanceof CompositionNode) {
				CompositionNode cn = (CompositionNode) o;
				while (cn.getOwningObject() != null) {
					cn = cn.getOwningObject();
				}
				o = (IPersistentObject) cn;
			}
			writePersistence.persist(o);
		}
		writePersistence.flush();
	}
}
