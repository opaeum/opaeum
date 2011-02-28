package net.sf.nakeduml.jbpm;

import javax.enterprise.context.ContextNotActiveException;

import net.sf.nakeduml.seam.Component;

import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;

public class JbpmKnowledgeBase extends AbstractJbpmKnowledgeBase  {
	static private JbpmKnowledgeBase mockInstance;

	static public JbpmKnowledgeBase getInstance() {
		try {
			return Component.INSTANCE.getInstance(JbpmKnowledgeBase.class);
		} catch (ContextNotActiveException e) {
			if ( mockInstance==null ) {
				mockInstance=new JbpmKnowledgeBase();
			}
			return mockInstance;
		}
	}
	
	public void prepareKnowledgeBuilder(KnowledgeBuilder kbuilder) {
		kbuilder.add(ResourceFactory.newClassPathResource("com/rorotika/cm/user/user/UserStateMachine.rf"), ResourceType.DRF);
		kbuilder.add(ResourceFactory.newClassPathResource("com/rorotika/cm/network/load/cmload/LoadProcess.rf"), ResourceType.DRF);
		kbuilder.add(ResourceFactory.newClassPathResource("com/rorotika/cm/network/load/cmload/loadprocess/DBLoadProcess.rf"), ResourceType.DRF);
		kbuilder.add(ResourceFactory.newClassPathResource("com/rorotika/cm/network/load/cmload/loadprocess/dbloadprocess/CreateNetworkElements.rf"), ResourceType.DRF);
		kbuilder.add(ResourceFactory.newClassPathResource("com/rorotika/cm/network/load/cmload/loadprocess/dbloadprocess/UpdateNetworkElements.rf"), ResourceType.DRF);
		kbuilder.add(ResourceFactory.newClassPathResource("com/rorotika/cm/network/workspace/userworkspace/UserWorkspaceStateMachine.rf"), ResourceType.DRF);
	}
}
