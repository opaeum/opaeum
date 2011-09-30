package jbpm.jbpm.nodedefinition;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import jbpm.jbpm.rip.NodeDefinition;

import org.hibernate.Session;

public class NodeDefinitionFactory implements INodeDefinitionFactory {

	@Inject
	private Session session;
	private List<NodeDefinition> nodeDefinitions;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void startUp() {
		nodeDefinitions = session.createQuery("from NodeDefinition a order by a.name").list();
	}
	
	@RequestScoped
	@Produces
	public List<NodeDefinition> get() {
		return this.nodeDefinitions;
	}
}
