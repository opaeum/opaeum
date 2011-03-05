package org.nakeduml.audit;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuditSequencer implements Serializable {

	private static final long serialVersionUID = 3038586745870689036L;
	private long count = 0;
	private Map<Long, Long> sequence = new HashMap<Long, Long>();
	
	public boolean contains(Long l) {
		return sequence.containsKey(l);
	}
	
	public synchronized void put(Long l) {
		sequence.put(l, l);
	}
	
	public synchronized Long getAndIncrement() {
		return count++;
	}
	
}