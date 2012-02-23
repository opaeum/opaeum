package org.opaeum.runtime.bpm.util;

import java.util.HashSet;
import java.util.Set;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.opaeum.runtime.jbpm.AbstractJbpmKnowledgeBase;

public class OpaeumLibraryForBPMKnowledgeBase extends AbstractJbpmKnowledgeBase {
	static final public OpaeumLibraryForBPMKnowledgeBase INSTANCE = new OpaeumLibraryForBPMKnowledgeBase();


	public Set<String> getProcessLocations() {
		Set<String> result = new HashSet<String>();
		result.add("org/opaeum/runtime/bpm/request/AbstractRequest.rf");
		result.add("org/opaeum/runtime/bpm/request/ProcessRequest.rf");
		result.add("org/opaeum/runtime/bpm/request/TaskRequest.rf");
		return result;
	}

}