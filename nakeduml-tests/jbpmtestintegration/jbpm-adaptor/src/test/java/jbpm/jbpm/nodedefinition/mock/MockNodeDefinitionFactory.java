package jbpm.jbpm.nodedefinition.mock;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import jbpm.jbpm.Application;
import jbpm.jbpm.nodedefinition.INodeDefinitionFactory;
import jbpm.jbpm.nodedefinition.rip.test.RipHelperTest;
import jbpm.jbpm.rip.Network;
import jbpm.jbpm.rip.NetworkSoftwareVersion;
import jbpm.jbpm.rip.NodeDefinition;

@Alternative
@Singleton
@Startup
public class MockNodeDefinitionFactory implements INodeDefinitionFactory {

	private List<NodeDefinition> nodeDefinitions;
	
	@PostConstruct
	public void startUp() {
		Application application = RipHelperTest.createTestData();
		nodeDefinitions = new ArrayList<NodeDefinition>();
		for(Network network:application.getNetwork()) {
			for(NetworkSoftwareVersion networkSoftwareVersion : network.getNetworkSoftwareVersion()) {
				nodeDefinitions.addAll(networkSoftwareVersion.getNodeDefinition());
			}
		}
	}
	
	@RequestScoped
	@Produces
	public List<NodeDefinition> get() {
		return this.nodeDefinitions;
	}	
}
