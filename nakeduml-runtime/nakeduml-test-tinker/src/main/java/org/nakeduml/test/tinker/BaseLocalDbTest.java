package org.nakeduml.test.tinker;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.nakeduml.environment.Environment;
import org.nakeduml.tinker.runtime.GraphDb;
import org.nakeduml.tinker.runtime.NakedGraph;
import org.nakeduml.tinker.runtime.NakedGraphFactory;
import org.nakeduml.tinker.runtime.TinkerSchemaHelper;

public class BaseLocalDbTest {

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
			TinkerSchemaHelper schemaHelper = (TinkerSchemaHelper) Class.forName(properties.getProperty("schema.generator")).newInstance();
			String dbPath = properties.getProperty("tinkerdb");
			String dbWithSchemata = properties.getProperty("tinkerdb.withschema", "false");
			return nakedGraphFactory.getNakedGraph(dbPath, schemaHelper, new Boolean(dbWithSchemata));
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

}
