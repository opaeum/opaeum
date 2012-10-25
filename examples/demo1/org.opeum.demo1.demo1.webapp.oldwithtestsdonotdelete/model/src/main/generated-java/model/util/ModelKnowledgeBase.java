package model.util;

import java.util.HashSet;
import java.util.Set;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.opaeum.runtime.jbpm.AbstractJbpmKnowledgeBase;

public class ModelKnowledgeBase extends AbstractJbpmKnowledgeBase {
	static final public ModelKnowledgeBase INSTANCE = new ModelKnowledgeBase();


	public Set<String> getProcessLocations() {
		Set<String> result = new HashSet<String>();
		result.addAll(org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMKnowledgeBase.INSTANCE.getProcessLocations());
		return result;
	}

}