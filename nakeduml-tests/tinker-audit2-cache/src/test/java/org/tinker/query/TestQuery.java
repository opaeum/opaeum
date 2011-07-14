package org.tinker.query;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nakeduml.environment.Environment;
import org.nakeduml.tinker.runtime.GraphDb;
import org.nakeduml.tinker.runtime.NakedGraph;
import org.nakeduml.tinker.runtime.NakedGraphFactory;
import org.tinker.God;
import org.tinker.Universe;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestQuery {

	protected NakedGraph db;

	@After
	public void after() {
		db.shutdown();
	}
	
	@Before
	public void before() {
		db = createNakedGraph();
		GraphDb.setDb(db);
	}
	
	protected NakedGraph createNakedGraph() {
		Properties properties = Environment.loadProperties();
		File dir = new File(properties.getProperty("tinkerdb"));
		if (dir.exists()) {
			try {
				FileUtils.deleteDirectory(dir);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} 
		}		
		try {
			@SuppressWarnings("unchecked")
			Class<NakedGraphFactory> factory = (Class<NakedGraphFactory>) Class.forName(properties.getProperty("nakedgraph.factory"));
			Method m = factory.getDeclaredMethod("getInstance", new Class[0]);
			NakedGraphFactory nakedGraphFactory = (NakedGraphFactory) m.invoke(null);
			return nakedGraphFactory.getNakedGraph(properties.getProperty("tinkerdb"), true);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}	
	
	protected long countVertices() {
		return db.countVertices();
	}

	protected long countEdges() {
		return db.countEdges();
	}	
	
	@Test
	public void testQuery() {
		db.startTransaction();
		God god = new God(true);
		god.setName("GOD");
		for (int i = 0; i < 1000; i++) {
			Universe universe1 = new Universe(god);
			universe1.setName("universe" + i);
		}
		db.stopTransaction(Conclusion.SUCCESS);
//		Assert.assertEquals(2002, countVertices());
//		List<Universe> universes = db.query("Universe", 0);
//		Assert.assertEquals(1000, universes.size());
		
	}
	
}
