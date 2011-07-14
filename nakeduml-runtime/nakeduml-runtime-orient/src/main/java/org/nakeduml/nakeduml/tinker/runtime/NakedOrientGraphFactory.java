package org.nakeduml.nakeduml.tinker.runtime;

import org.nakeduml.tinker.runtime.NakedGraph;
import org.nakeduml.tinker.runtime.NakedGraphFactory;
import org.nakeduml.tinker.runtime.TinkerSchemaHelper;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Mode;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;

public class NakedOrientGraphFactory implements NakedGraphFactory {
	
	private static NakedOrientGraphFactory INSTANCE = new NakedOrientGraphFactory();
	
	private NakedOrientGraphFactory() {
	}
	
	public static NakedGraphFactory getInstance() {
		return INSTANCE;
	}
	
	@Override
	public NakedGraph getNakedGraph(String url, boolean withSchema) {
		OrientGraph db = new OrientGraph(url);
		db.setTransactionMode(Mode.MANUAL);
		NakedGraph nakedGraph = new NakedOrientGraph(db, withSchema);
		nakedGraph.startTransaction();
		nakedGraph.addRoot();
		nakedGraph.stopTransaction(Conclusion.SUCCESS);
//		nakedGraph.clearAutoIndices();
		nakedGraph.registerListeners();		
		if (withSchema) {
			try {
				TinkerSchemaHelper schemaHelper = (TinkerSchemaHelper) Class.forName("org.util.TinkerSchemaGenerator").newInstance();
				nakedGraph.createSchema(schemaHelper.getClassNames());
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		return nakedGraph;
	}

}
