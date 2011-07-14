package org.nakeduml.tinker.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.logging.Logger;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;
import org.apache.myfaces.extensions.cdi.jsf.api.Jsf;
import org.apache.myfaces.extensions.cdi.message.api.MessageContext;
import org.apache.myfaces.extensions.cdi.message.impl.DefaultMessage;
import org.mvel2.MVEL;
import org.nakeduml.runtime.domain.TinkerNode;
import org.nakeduml.tinker.runtime.ApplicationScopedDb;
import org.nakeduml.tinker.runtime.NakedGraph;
import org.tinker.God;
import org.tinker.Universe;

@ConversationScoped
@Named("tinkerQuery")
public class TinkerQuery implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private Logger logger;
	@ApplicationScopedDb
	@Inject
	NakedGraph db;
	@Inject
	@Jsf
	private MessageContext messageContext;
	private TinkerNode root;
	private String query;
	private List<TinkerNode> queryResult;

	@PostConstruct
	public void init() {
	}

	@SuppressWarnings("unchecked")
	public void executeQuery() {
		queryResult = new ArrayList<TinkerNode>((HashSet<TinkerNode>) MVEL.eval(query, root));
	}

	public void createData() {
		logger.info("createData");
		System.out.println("doing stuff man");
		God god = new God();
		god.setName("didthiswork");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		Universe universe2 = new Universe(god);
		universe1.setName("universe2");
		Universe universe3 = new Universe(god);
		universe1.setName("universe3");
		messageContext.addMessage(new DefaultMessage("Successfully saved God"));
	}

	public List<TinkerNode> getQueryResult() {
		return queryResult;
	}

	public void setQueryResult(List<TinkerNode> queryResult) {
		this.queryResult = queryResult;
	}

	public TinkerNode getRoot() {
		return root;
	}

	public void setRoot(TinkerNode root) {
		this.root = root;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}
