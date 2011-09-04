package org.nakeduml.runtime.bpm.util;

import java.util.HashSet;
import java.util.Set;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.nakeduml.runtime.jbpm.AbstractJbpmKnowledgeBase;

public class OpiumLibraryForBPMKnowledgeBase extends AbstractJbpmKnowledgeBase {
	static final public OpiumLibraryForBPMKnowledgeBase INSTANCE = new OpiumLibraryForBPMKnowledgeBase();


	public Set<String> getProcessLocations() {
		Set<String> result = new HashSet<String>();
		result.add("org/nakeduml/runtime/bpm/TaskRequest.rf");
		result.add("org/nakeduml/runtime/bpm/ProcessRequest.rf");
		result.add("org/nakeduml/runtime/bpm/AbstractRequest.rf");
		return result;
	}

}