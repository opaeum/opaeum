package org.opeum.runtime.bpm.util;

import java.util.HashSet;
import java.util.Set;

import org.opeum.runtime.jbpm.AbstractJbpmKnowledgeBase;

public class OpiumLibraryForBPMKnowledgeBase extends AbstractJbpmKnowledgeBase {
	static final public OpiumLibraryForBPMKnowledgeBase INSTANCE = new OpiumLibraryForBPMKnowledgeBase();


	public Set<String> getProcessLocations() {
		Set<String> result = new HashSet<String>();
		result.add("org/opeum/runtime/bpm/ProcessRequest.rf");
		result.add("org/opeum/runtime/bpm/TaskRequest.rf");
		result.add("org/opeum/runtime/bpm/AbstractRequest.rf");
		return result;
	}

}