package org.nakeduml.tinker.collection;

import java.util.Collection;

import org.nakeduml.runtime.domain.TinkerCompositionNode;
import org.nakeduml.tinker.runtime.GraphDb;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public class TinkerSequenceImpl<E> extends BaseSequence<E> implements TinkerSequence<E> {

	public TinkerSequenceImpl(TinkerCompositionNode owner, String label, String uid, boolean isInverse, boolean isManyToMany) {
		super();
		this.owner = owner;
		this.vertex = owner.getVertex();
		this.label = label;
		this.parentClass = owner.getClass();
		this.index = GraphDb.getDb().getIndex(uid + ":::" + label, Edge.class);
		if (this.index == null) {
			this.index = GraphDb.getDb().createManualIndex(uid + ":::" + label, Edge.class);
		}
		this.inverse = isInverse;
		this.manyToMany = isManyToMany;
	}

	@Override
	public boolean add(E e) {
		maybeCallInit(e);
		maybeLoad();
		boolean result = this.internalList.add(e);
		if (result) {
			Edge edge = addInternal(e);
			this.index.put("index", new Float(this.internalList.size() - 1), edge);
			edge.getInVertex().setProperty("tinkerIndex", new Float(this.internalList.size() - 1));
		}
		return result;
	}

	@Override
	public void add(int indexOf, E e) {
		maybeCallInit(e);
		maybeLoad();
		addToListAndListIndex(indexOf, e);
	}


	@Override
	public boolean addAll(Collection<? extends E> c) {
		maybeLoad();
		boolean result = true;
		for (E e : c) {
			if (!this.add(e)) {
				result = false;
			}
		}
		return result;
	}


	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		maybeLoad();
		int indexOf = index;
		for (E e : c) {
			this.add(indexOf++, e);
		}
		return true;
	}

	@Override
	public E set(int index, E element) {
		E removedElement = this.remove(index);
		this.add(index, element);
		return removedElement;
	}

	protected void removeEdgefromIndex(Vertex v, Edge edge, int indexOf) {
		this.index.remove("index", v.getProperty("tinkerIndex"), edge);
	}

}
