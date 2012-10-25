package org.nakeduml.nakeduml.tinker.runtime;

import java.io.File;

import org.nakeduml.tinker.runtime.NakedGraph;
import org.nakeduml.tinker.runtime.NakedGraphFactory;
import org.nakeduml.tinker.runtime.TinkerSchemaHelper;
import org.nakeduml.tinker.runtime.TransactionThreadEntityVar;

import com.tinkerpop.blueprints.pgm.Index;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jGraph;

public class NakedNeo4jGraphFactory implements NakedGraphFactory {
	
	public static NakedNeo4jGraphFactory INSTANCE = new NakedNeo4jGraphFactory();
	
	private NakedNeo4jGraphFactory() {
	}
	
	public static NakedGraphFactory getInstance() {
		return INSTANCE;
	}
	
	@Override
	public NakedGraph getNakedGraph(String url, TinkerSchemaHelper schemaHelper, boolean withSchema) {
		File f = new File(url);
		Neo4jGraph db = new Neo4jGraph(f.getAbsolutePath());
		db.dropIndex(Index.VERTICES);
		db.dropIndex(Index.EDGES);
//		db.setTransactionMode(Mode.MANUAL);
		db.setMaxBufferSize(0);
		TransactionThreadEntityVar.clear();
		NakedGraph nakedGraph = new NakedNeo4jGraph(db, schemaHelper);
		nakedGraph.startTransaction();
		nakedGraph.addRoot();
		nakedGraph.stopTransaction(Conclusion.SUCCESS);
		nakedGraph.registerListeners();			
		return nakedGraph;
	}

}
