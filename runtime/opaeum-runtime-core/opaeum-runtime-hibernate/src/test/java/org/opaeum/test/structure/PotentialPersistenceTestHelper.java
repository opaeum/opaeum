package org.opaeum.test.structure;

import org.opaeum.runtime.domain.IPersistentObject;

public class PotentialPersistenceTestHelper {
	public <T extends IPersistentObject> T read(T t) {
		return t;
	}
	public <T extends IPersistentObject> T readWithoutFilter(T t) {
		return t;
	}
	public void persist(IPersistentObject ... po){
		
	}
	public void synch() {
		
	}
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
