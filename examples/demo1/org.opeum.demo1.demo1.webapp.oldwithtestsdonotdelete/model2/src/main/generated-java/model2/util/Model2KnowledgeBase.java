package model2.util;

import java.util.HashSet;
import java.util.Set;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.opaeum.runtime.jbpm.AbstractJbpmKnowledgeBase;

public class Model2KnowledgeBase extends AbstractJbpmKnowledgeBase {
	static final public Model2KnowledgeBase INSTANCE = new Model2KnowledgeBase();


	public Set<String> getProcessLocations() {
		Set<String> result = new HashSet<String>();
		return result;
	}

}