package org.opeum.environment;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;

import org.util.DbListener;

import com.tinkerpop.blueprints.pgm.TransactionalGraph;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Mode;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;

public class ConversationScopedDbProducer {

	@ConversationScoped
	@Produces
	public TransactionalGraph getTransactionalGraph() {
		TransactionalGraph db = new OrientGraph("local:/tmp/tinker-huh");
		db.setTransactionMode(Mode.MANUAL);
		((OrientGraph)db).getRawGraph().registerListener(new DbListener());
		return db;
	}

}
