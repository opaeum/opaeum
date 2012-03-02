package org.opeum.demo1.util;

import java.util.HashSet;
import java.util.Set;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.opaeum.runtime.jbpm.AbstractJbpmKnowledgeBase;

public class Demo1KnowledgeBase extends AbstractJbpmKnowledgeBase {
	static final public Demo1KnowledgeBase INSTANCE = new Demo1KnowledgeBase();


	public Set<String> getProcessLocations() {
		Set<String> result = new HashSet<String>();
		result.addAll(structuredbusiness.util.StructuredbusinessKnowledgeBase.INSTANCE.getProcessLocations());
		return result;
	}

}