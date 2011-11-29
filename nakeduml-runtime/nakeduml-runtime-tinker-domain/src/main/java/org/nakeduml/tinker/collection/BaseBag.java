package org.nakeduml.tinker.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.nakeduml.runtime.domain.TinkerCompositionNode;
import org.nakeduml.tinker.runtime.GraphDb;
import org.nakeduml.tinker.runtime.TransactionThreadEntityVar;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class BaseBag<E> extends BaseCollection<E> implements TinkerBag<E> {

	protected Multiset<E> internalBag = HashMultiset.create();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void loadFromVertex() {
		for (Edge edge : getEdges()) {
			E node = null;
			try {
				Class<?> c = this.getClassToInstantiate(edge);
				Object value = this.getVertexForDirection(edge).getProperty("value");
				if (c.isEnum()) {
					node = (E) Enum.valueOf((Class<? extends Enum>) c, (String) value);
					this.internalVertexMap.put(value, this.getVertexForDirection(edge));
				} else if (TinkerCompositionNode.class.isAssignableFrom(c)) {
					node = (E) c.getConstructor(Vertex.class).newInstance(this.getVertexForDirection(edge));
				} else {
					node = (E) value;
					this.internalVertexMap.put(value, this.getVertexForDirection(edge));
				}
				this.internalBag.add(node);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
	}
	
	protected Iterable<Edge> getEdges() {
		if (this.inverse) {
			return this.vertex.getOutEdges(this.label);
		} else {
			return this.vertex.getInEdges(this.label);
		}
	}	

	@Override
	public boolean remove(Object o) {
		maybeLoad();
		boolean result = this.internalBag.remove(o);
		if (result) {
			Vertex v;
			if (o instanceof TinkerCompositionNode) {
				TinkerCompositionNode node = (TinkerCompositionNode) o;
				v = node.getVertex();
				Set<Edge> edges = GraphDb.getDb().getEdgesBetween(this.vertex, v, this.label);
				for (Edge edge : edges) {
					GraphDb.getDb().removeEdge(edge);
					break;
				}
			} else if (o.getClass().isEnum()) {
				v = this.internalVertexMap.get(((Enum<?>) o).name());
				GraphDb.getDb().removeVertex(v);
			} else {
				v = this.internalVertexMap.get(o);
				GraphDb.getDb().removeVertex(v);
			}
		}
		return result;
	}

	@Override
	public int size() {
		maybeLoad();
		return this.internalBag.size();
	}

	@Override
	public boolean isEmpty() {
		maybeLoad();
		return this.internalBag.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		maybeLoad();
		return this.internalBag.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		maybeLoad();
		return this.internalBag.iterator();
	}

	@Override
	public Object[] toArray() {
		maybeLoad();
		return this.internalBag.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		maybeLoad();
		return this.internalBag.toArray(a);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		maybeLoad();
		return this.internalBag.containsAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		if (!this.loaded) {
			loadFromVertex();
		}
		boolean result = true;
		for (E e : this.internalBag) {
			if (!c.contains(e)) {
				if (!this.remove(e)) {
					result = false;
				}
			}
		}
		return result;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		maybeLoad();
		boolean result = true;
		for (Object object : c) {
			if (!this.remove(object)) {
				result = false;
			}
		}
		return result;
	}

	@Override
	public void clear() {
		maybeLoad();
		for (E e : this.internalBag) {
			this.remove(e);
		}
	}

	@Override
	public int count(Object element) {
		maybeLoad();
		return this.internalBag.count(element);
	}

	@Override
	public int add(E element, int occurrences) {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public int remove(Object element, int occurrences) {
		maybeLoad();
		int count = count(element);
		if (count > occurrences) {
			for (int i = 0; i < occurrences; i++) {
				remove(element);
			}
		} else {
			for (int i = 0; i < count; i++) {
				remove(element);
			}
		}
		return count;
	}

	@Override
	public int setCount(E element, int count) {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public boolean setCount(E element, int oldCount, int newCount) {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public Set<E> elementSet() {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public Set<com.google.common.collect.Multiset.Entry<E>> entrySet() {
		throw new RuntimeException("Not yet implemented");
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

}
