package org.nakeduml.environment;

import java.lang.annotation.Annotation;

import org.nakeduml.nakeduml.tinker.runtime.NakedOrientGraph;
import org.util.NakedGraph;
import org.util.TinkerSchemaHelper;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Mode;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;

public class OrientEnvironment extends Environment {

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
			NakedGraph nakedGraph = new NakedOrientGraph(db, new Boolean(this.properties.getProperty("tinkerdb.withschema", "false")));
			nakedGraph.registerListeners();
			if (new Boolean(properties.getProperty("tinkerdb.withschema", "false"))) {
				try {
					TinkerSchemaHelper schemaHelper = (TinkerSchemaHelper) Class.forName(this.properties.getProperty("tinker.schema.list")).newInstance();
					nakedGraph.createSchema(schemaHelper.getClassNames());
				} catch (InstantiationException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				} catch (ClassNotFoundException e) {
					throw new RuntimeException(e);
				}
			}
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
