package jbpm.jbpm.nodedefinition;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import jbpm.jbpm.rip.NodeDefinition;

import org.hibernate.Session;

public class NodeDefinitionFactory {

	@Inject
	private Session session;
	
	@SuppressWarnings("unchecked")
	@ApplicationScoped
	@Produces
	public List<NodeDefinition> get() {
		return session.createQuery("from NodeDefinition a order by a.name").list();
	}
}
