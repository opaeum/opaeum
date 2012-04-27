package org.nakeduml.runtime.domain.activity;

import java.util.Collection;
import java.util.UUID;

import org.nakeduml.runtime.domain.TinkerCompositionNode;
import org.nakeduml.tinker.collection.BaseCollection;
import org.nakeduml.tinker.collection.TinkerSequenceImpl;
import org.nakeduml.tinker.runtime.GraphDb;
import org.opaeum.runtime.domain.CompositionNode;

import com.tinkerpop.blueprints.pgm.Vertex;

public class CollectionObjectToken<O> extends ObjectToken<O> implements TinkerCompositionNode {

	private static final long serialVersionUID = 1L;
	private BaseCollection<O> elements;

	public CollectionObjectToken(Vertex vertex) {
		super(vertex);
		this.elements = new TinkerSequenceImpl<O>(this, "org__activitytest__contextObject", getUid(), true, false, false);
	}

	public CollectionObjectToken(String edgeName, Collection<O> collection) {
		super(edgeName);
		this.elements = new TinkerSequenceImpl<O>(this, "org__activitytest__contextObject", getUid(), true, false, false);
		addCollection(collection);
	}

	private void addCollection(Collection<O> collection) {
		this.elements.addAll(collection);
	}

	public Collection<O> getElements() {
		return this.elements;
	}

	@Override
	public Object getObject() {
		return this.elements;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CollectionObjectToken<O> duplicate(String flowName) {
		CollectionObjectToken<O> objectToken = new CollectionObjectToken<O>(flowName, getElements());
		return objectToken;
	}

	@Override
	public int getNumberOfElements() {
		return this.elements.size();
	}

	@Override
	public void remove() {
		this.elements.clear();
		GraphDb.getDb().removeVertex(getVertex());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Collection Object Token value = ");
		sb.append(getElements());
		return sb.toString();
	}

	public String getUid() {
		String uid = (String) this.vertex.getProperty("uid");
		if (uid == null || uid.trim().length() == 0) {
			uid = UUID.randomUUID().toString();
			this.vertex.setProperty("uid", uid);
		}
		return uid;
	}

	@Override
	public boolean isTinkerRoot() {
		return false;
	}

	@Override
	public void clearCache() {
	}

	@Override
	public Long getId() {
		if (true) {
			throw new RuntimeException("check this out");
		}
		return null;
	}

	@Override
	public void setId(Long id) {
		if (true) {
			throw new RuntimeException("check this out");
		}

	}

	@Override
	public String getName() {
		if (true) {
			throw new RuntimeException("check this out");
		}
		return null;
	}

	@Override
	public int getObjectVersion() {
		if (true) {
			throw new RuntimeException("check this out");
		}
		return 0;
	}

	@Override
	public CompositionNode getOwningObject() {
		if (true) {
			throw new RuntimeException("check this out");
		}
		return null;
	}

	@Override
	public void init(CompositionNode owner) {
	}

	@Override
	public void removeFromOwningObject() {
		if (true) {
			throw new RuntimeException("check this out");
		}
	}

	@Override
	public void addToOwningObject() {
		if (true) {
			throw new RuntimeException("check this out");
		}
	}

	@Override
	public void markDeleted() {
		if (true) {
			throw new RuntimeException("check this out");
		}
	}

	@Override
	public boolean hasInitBeenCalled() {
		return true;
	}

}
