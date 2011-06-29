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
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Mode;

public class BaseLocalDbTest {

	protected NakedGraph db;

	@Before
	public void before() {
		db = Environment.getInstance().getComponent(NakedGraph.class);
//		db.setTransactionMode(Mode.MANUAL);
//		db.startTransaction();
		db.clear();
//		db.stopTransaction(Conclusion.SUCCESS);
		GraphDb.setDb(db);
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
		db.setTransactionMode(Mode.MANUAL);
		if (Boolean.valueOf(properties.getProperty("tinkerdb.register.listeners", "true"))) {
			db.registerListeners();
		}
		db.startTransaction();
		db.addRoot();
		db.stopTransaction(Conclusion.SUCCESS);
		db.setTransactionMode(Mode.valueOf(properties.getProperty("tinkerdb.transactionmode", "MANUAL")));
	}

	protected long countVertices() {
		return db.countVertices();
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
