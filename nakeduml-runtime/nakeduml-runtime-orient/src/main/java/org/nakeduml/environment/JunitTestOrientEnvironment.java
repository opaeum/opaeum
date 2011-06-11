package org.nakeduml.environment;

import java.lang.annotation.Annotation;

import org.nakeduml.nakeduml.tinker.runtime.NakedOrientGraph;
import org.util.NakedGraph;
import org.util.TransactionThreadEntityVar;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Mode;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;

public class JunitTestOrientEnvironment extends Environment {

	@Override
	public <T> Class<T> getImplementationClass(T o) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getComponent(Class<T> clazz) {
		if (clazz == NakedGraph.class) {
			OrientGraph db = new OrientGraph(this.properties.getProperty("tinkerdb"));
			db.setTransactionMode(Mode.MANUAL);
			TransactionThreadEntityVar.clear();
			NakedGraph nakedGraph = new NakedOrientGraph(db, new Boolean(this.properties.getProperty("tinkerdb.withschema", "false")));
			nakedGraph.registerListeners();
			return (T)nakedGraph;
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
