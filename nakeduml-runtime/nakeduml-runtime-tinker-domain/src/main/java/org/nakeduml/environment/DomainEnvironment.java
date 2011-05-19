package org.nakeduml.environment;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import org.util.DbListener;

import com.tinkerpop.blueprints.pgm.TransactionalGraph;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Mode;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;

public class DomainEnvironment extends Environment {

	private TransactionalGraph db;
	private Map<String,Object> components = new HashMap<String,Object>();
	public <T>void mockComponent(Class<T> clazz,T component){
		this.components.put(clazz.getName(), component);
	}
	
	@Override
	public <T> Class<T> getImplementationClass(T o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getComponent(Class<T> clazz) {
		if(clazz == TransactionalGraph.class){
			return (T) getTransactionalGraph();
		}
		return (T) components.get(clazz.getName());
	}

	private TransactionalGraph getTransactionalGraph() {
		if (this.db==null) {
			TransactionalGraph db = new OrientGraph("local:/tmp/tinker-huh");
			db.setTransactionMode(Mode.MANUAL);
			((OrientGraph)db).getRawGraph().registerListener(new DbListener());
		}
		return this.db;
	}

	@Override
	public <T> T getComponent(Class<T> clazz, Annotation qualifiers) {
		throw new IllegalArgumentException("Qualifiers is not yet supported in the domain environment");
	}

	@Override
	public void reset() {
		this.db.clear();
	}
}
