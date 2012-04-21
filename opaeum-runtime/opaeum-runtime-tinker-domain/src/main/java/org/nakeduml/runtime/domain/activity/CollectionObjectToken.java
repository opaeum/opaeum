package org.nakeduml.runtime.domain.activity;

import java.util.Collection;

import org.nakeduml.tinker.collection.BaseCollection;
import org.nakeduml.tinker.runtime.GraphDb;

import com.tinkerpop.blueprints.pgm.Vertex;

public class CollectionObjectToken<O> extends ObjectToken<O> {
	
	private BaseCollection<O> collection;

	public CollectionObjectToken(Vertex vertex) {
		super(vertex);
	}

	public CollectionObjectToken(String edgeName, Collection<O> collection) {
		super(edgeName);
		addCollection(collection);
	}

	private void addCollection(Collection<O> collection) {
		this.collection.addAll(collection);
	}

	protected void removeCollection() {
		this.collection.removeAll(this.collection);
	}

	public Collection<O> getCollection() {
		return this.collection;
	}

	@Override
	public CollectionObjectToken<O> duplicate(String flowName) {
		CollectionObjectToken<O> objectToken = new CollectionObjectToken<O>(flowName, getCollection());
		return objectToken;
	}

	@Override
	public void remove() {
		removeCollection();
		GraphDb.getDb().removeVertex(getVertex());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Collection Object Token value = ");
		sb.append(getCollection());
		return sb.toString();
	}

}
