package org.nakeduml.environment;

import java.lang.annotation.Annotation;

import org.nakeduml.nakeduml.tinker.runtime.NakedNeo4jGraph;
import org.util.NakedGraph;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Mode;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jGraph;

public class Neo4jEnvironment extends Environment {

	@Override
	public <T> Class<T> getImplementationClass(T o) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getComponent(Class<T> clazz) {
		if (clazz == NakedGraph.class) {
			Neo4jGraph db = new Neo4jGraph(this.properties.getProperty("tinkerdb"));
			db.setTransactionMode(Mode.MANUAL);
			NakedGraph nakedGraph = new NakedNeo4jGraph(db);
			nakedGraph.registerListeners();
			return (T) nakedGraph;
		}
		return null;
	}

	@Override
	public <T> T getComponent(Class<T> clazz, Annotation qualifiers) {
		return null;
	}

	@Override
	public void reset() {
	}

}
