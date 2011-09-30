package org.opeum.tinker;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;

import org.util.DbListener;

import com.orientechnologies.orient.core.record.impl.ODocument;
import com.tinkerpop.blueprints.pgm.TransactionalGraph;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Mode;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;

public class ConversationScopedDbProducer {

	@ConversationScoped
	@Produces
	public TransactionalGraph get() {
		OrientGraph db = new OrientGraph("local:/tmp/orientdbtest2");
		db.setTransactionMode(Mode.MANUAL);
		((OrientGraph)db).getRawGraph().registerListener(new DbListener());
		db.startTransaction();
		ODocument root = ((OrientGraph)db).getRawGraph().newInstance();
		((OrientGraph)db).getRawGraph().setRoot("root", root);
		root.field("transactionCount", 1);
		return db;
	}
	
}
