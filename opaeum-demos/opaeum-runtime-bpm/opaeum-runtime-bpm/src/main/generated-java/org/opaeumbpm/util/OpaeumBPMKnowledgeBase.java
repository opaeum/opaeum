package org.opaeumbpm.util;

import java.util.HashSet;
import java.util.Set;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.opaeum.runtime.jbpm.AbstractJbpmKnowledgeBase;

public class OpaeumBPMKnowledgeBase extends AbstractJbpmKnowledgeBase {
	static final public OpaeumBPMKnowledgeBase INSTANCE = new OpaeumBPMKnowledgeBase();


	public Set<String> getProcessLocations() {
		Set<String> result = new HashSet<String>();
		result.addAll(org.opaeum.organization.util.OpaeumLibraryForBPMKnowledgeBase.INSTANCE.getProcessLocations());
		result.addAll(org.opaeum.runtime.bpm.opaeumsimpletypes.util.OpaeumSimpleTypesKnowledgeBase.INSTANCE.getProcessLocations());
		return result;
	}

}