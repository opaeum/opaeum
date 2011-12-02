package org.nakeduml.tinker.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.nakeduml.runtime.domain.TinkerCompositionNode;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class BaseSet<E> extends BaseCollection<E> implements TinkerSet<E> {

	protected Set<E> internalSet = new HashSet<E>();
	protected Map<Object, Vertex> internalVertexMap = new HashMap<Object, Vertex>();

	@SuppressWarnings({ "unchecked", "rawtypes" })
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
				this.internalSet.add(node);
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
	public int size() {
		maybeLoad();
		return this.internalSet.size();
	}

	@Override
	public boolean isEmpty() {
		maybeLoad();
		return this.internalSet.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		maybeLoad();
		return this.internalSet.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		maybeLoad();
		return this.internalSet.iterator();
	}

	@Override
	public Object[] toArray() {
		maybeLoad();
		return this.internalSet.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		maybeLoad();
		return this.internalSet.toArray(a);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		maybeLoad();
		return this.internalSet.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		maybeLoad();
		boolean result = true;
		for (E object : c) {
			if (!this.add(object)) {
				result = false;
			}
		}
		return result;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		if (!this.loaded) {
			loadFromVertex();
		}
		boolean result = true;
		for (E e : this.internalSet) {
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
		for (E e : new HashSet<E>(this.internalSet)) {
			this.remove(e);
		}
	}

}
