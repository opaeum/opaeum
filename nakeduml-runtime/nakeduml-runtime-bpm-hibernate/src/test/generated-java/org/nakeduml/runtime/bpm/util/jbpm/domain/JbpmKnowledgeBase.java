package org.nakeduml.runtime.bpm.util.jbpm.domain;

import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.nakeduml.runtime.jbpm.AbstractJbpmKnowledgeBase;

public class JbpmKnowledgeBase extends AbstractJbpmKnowledgeBase {
	static private JbpmKnowledgeBase mockInstance;


	public void prepareKnowledgeBuilder(KnowledgeBuilder kbuilder) {
		kbuilder.add(ResourceFactory.newClassPathResource("org/nakeduml/runtime/bpm/TaskRequest.rf"), ResourceType.DRF);
		kbuilder.add(ResourceFactory.newClassPathResource("org/nakeduml/runtime/bpm/ProcessRequest.rf"), ResourceType.DRF);
		kbuilder.add(ResourceFactory.newClassPathResource("org/nakeduml/runtime/bpm/AbstractRequest.rf"), ResourceType.DRF);
	}

}