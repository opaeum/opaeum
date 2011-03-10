package jbpm.jbpm.nodedefinition;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import jbpm.jbpm.rip.NodeDefinition;

import org.hibernate.Session;

@Singleton
public class NodeConnectionSpecFactory {

	@Inject
	private Session session;
	
	@SuppressWarnings("unchecked")
	@RequestScoped
	@Produces
	public List<NodeDefinition> get() {
		return session.createQuery("from NodeDefinition a order by a.name").list();
	}
}
