package nakedumllibraryforbpm.util.jbpm.domain;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.nakeduml.environment.AbstractJbpmKnowledgeBase;

public class JbpmKnowledgeBase extends AbstractJbpmKnowledgeBase {
	static private JbpmKnowledgeBase mockInstance;


	public void prepareKnowledgeBuilder(KnowledgeBuilder kbuilder) {
		kbuilder.add(ResourceFactory.newClassPathResource("nakedumllibraryforbpm/AbstractRequest.rf"), ResourceType.DRF);
		kbuilder.add(ResourceFactory.newClassPathResource("nakedumllibraryforbpm/TaskRequest.rf"), ResourceType.DRF);
		kbuilder.add(ResourceFactory.newClassPathResource("nakedumllibraryforbpm/ProcessRequest.rf"), ResourceType.DRF);
	}

}