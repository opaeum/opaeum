package org.opeum.demo1.util;

import java.util.HashSet;
import java.util.Set;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.opaeum.runtime.jbpm.AbstractJbpmKnowledgeBase;

public class Demo1JbpmKnowledgeBase extends AbstractJbpmKnowledgeBase {
	static final public Demo1JbpmKnowledgeBase INSTANCE = new Demo1JbpmKnowledgeBase();


	public Set<String> getProcessLocations() {
		Set<String> result = new HashSet<String>();
		result.addAll(org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJbpmKnowledgeBase.INSTANCE.getProcessLocations());
		return result;
	}

}