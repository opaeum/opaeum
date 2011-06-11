package org.nakeduml.test.tinker;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.junit.Before;
import org.nakeduml.environment.Environment;
import org.util.GraphDb;
import org.util.NakedGraph;
import org.util.TinkerSchemaHelper;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class BaseLocalDbTest {

	protected NakedGraph db;

	@Before
	public void before() {
		GraphDb.setDb(Environment.getInstance().getComponent(NakedGraph.class));
		db = GraphDb.getDb();
		db.clear();
		Properties properties = loadProperties();
		if (new Boolean(properties.getProperty("tinkerdb.withschema", "false"))) {
			try {
				TinkerSchemaHelper schemaHelper = (TinkerSchemaHelper) Class.forName(properties.getProperty("tinker.schema.list")).newInstance();
				db.createSchema(schemaHelper.getClassNames());
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		db.registerListeners();
		db.startTransaction();
		db.addRoot();
		db.stopTransaction(Conclusion.SUCCESS);
	}

	protected long countVertices() {
		return db.countVertices() - 1;
	}

	protected long countEdges() {
		return db.countEdges();
	}

	public static Properties loadProperties() {
		Properties properties;
		try {
			properties = new Properties();
			URL resource = Thread.currentThread().getContextClassLoader().getResource("/" + Environment.PROPERTIES_FILE_NAME);
			if (resource == null) {
				resource = Thread.currentThread().getContextClassLoader().getResource(Environment.PROPERTIES_FILE_NAME);
			}
			properties.load(resource.openStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return properties;
	}

}