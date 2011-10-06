package org.opeum.runtime.bpm.util;

import java.util.HashSet;
import java.util.Set;

import org.opeum.runtime.jbpm.AbstractJbpmKnowledgeBase;

public class OpeumLibraryForBPMKnowledgeBase extends AbstractJbpmKnowledgeBase {
	static final public OpeumLibraryForBPMKnowledgeBase INSTANCE = new OpeumLibraryForBPMKnowledgeBase();


	public Set<String> getProcessLocations() {
		Set<String> result = new HashSet<String>();
		result.add("org/opeum/runtime/bpm/ProcessRequest.rf");
		result.add("org/opeum/runtime/bpm/TaskRequest.rf");
		result.add("org/opeum/runtime/bpm/AbstractRequest.rf");
		return result;
	}

}