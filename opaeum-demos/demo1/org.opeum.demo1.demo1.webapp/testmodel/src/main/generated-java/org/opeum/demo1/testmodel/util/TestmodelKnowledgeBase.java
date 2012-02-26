package org.opeum.demo1.testmodel.util;

import java.util.HashSet;
import java.util.Set;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.opaeum.runtime.jbpm.AbstractJbpmKnowledgeBase;

public class TestmodelKnowledgeBase extends AbstractJbpmKnowledgeBase {
	static final public TestmodelKnowledgeBase INSTANCE = new TestmodelKnowledgeBase();


	public Set<String> getProcessLocations() {
		Set<String> result = new HashSet<String>();
		result.addAll(org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMKnowledgeBase.INSTANCE.getProcessLocations());
		return result;
	}

}