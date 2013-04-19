package org.nakeduml.tinker.runtime;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.core.api.logging.Logger;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;
import org.opaeum.runtime.environment.Environment;

@ApplicationScoped
public class DbProducer implements Serializable {

	private static final long serialVersionUID = 4879917017708775265L;
	@Inject
	private Logger logger;

	protected NakedGraph createNakedGraph() {
		logger.info("create db");
		Properties properties = Environment.loadProperties();
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

	@DependentScopedDb
	@Dependent
	@Produces
	public NakedGraph getDependentNakedGraph() {
		logger.info("produce dependent scoped db");
		NakedGraph db = createNakedGraph();
		return db;
	}	
	
	public void closeDependentScoped(@Disposes @DependentScopedDb NakedGraph db) {
		logger.info("shutdown dependent scoped db");
		if (db != null) {
			db.shutdown();
		}
	}	
	
	@RequestScopedDb
	@RequestScoped
	@Produces
	public NakedGraph getRequestScopedNakedGraph() {
		logger.info("produce request scoped db");
		NakedGraph db = createNakedGraph();
		return db;
	}

	public void closeRequestScoped(@Disposes @RequestScopedDb NakedGraph db) {
		logger.info("shutdown request scoped db");
		if (db != null) {
			db.shutdown();
		}
	}
	
	@ConversationScopedDb
	@Produces
    @ConversationScoped
	public NakedGraph getConversationScopedNakedGraph() {
		logger.info("produce conversation scoped db");
		NakedGraph db = createNakedGraph();
		return db;
	}
	
	public void closeConversationScoped(@Disposes @ConversationScopedDb NakedGraph db) {
		logger.info("shutdown conversation scoped db");
		if (db != null) {
			db.shutdown();
		}
	}	

	@ApplicationScopedDb
	@Produces
    @ApplicationScoped
	public NakedGraph getApplicationScopedNakedGraph() {
		logger.info("produce Application scoped db");
		NakedGraph db = createNakedGraph();
		return db;
	}
	
	public void closeApplicationScoped(@Disposes @ApplicationScopedDb NakedGraph db) {
		logger.info("shutdown Application scoped db");
		if (db != null) {
			db.shutdown();
		}
	}	
	
}
