package jbpm.jbpm.nodedefinition.pool;

import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import jbpm.jbpm.rip.NodeDefinition;

import org.hibernate.Session;

public class NodeDefinitionProducer {

	@Inject
	private Session session;
	
	@SuppressWarnings("unchecked")
	@Produces
	public List<NodeDefinition> getNodeDefinitions() {
		return session.createQuery("from NodeDefinition a order by a.name").list();
	}
}
