package org.nakeduml.tinker.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.nakeduml.runtime.domain.TinkerCompositionNode;
import org.nakeduml.runtime.domain.TinkerNode;
import org.nakeduml.tinker.runtime.GraphDb;
import org.nakeduml.tinker.runtime.NakedTinkerIndex;
import org.nakeduml.tinker.runtime.TransactionThreadEntityVar;

import com.tinkerpop.blueprints.pgm.CloseableSequence;
import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class BaseSequence<E> extends BaseCollection<E> implements TinkerSequence<E> {

	protected List<E> internalList = new ArrayList<E>();
	protected NakedTinkerIndex<Edge> index;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void loadFromVertex() {
		CloseableSequence<Edge> edges = this.index.queryList(0F, true, false);
		for (Edge edge : edges) {
			E node = null;
			try {
				Class<?> c = this.getClassToInstantiate(edge);
				Object value = this.getVertexForDirection(edge).getProperty("value");
				if (c.isEnum()) {
					node = (E) Enum.valueOf((Class<? extends Enum>) c, (String) value);
					this.internalVertexMap.put(value, this.getVertexForDirection(edge));
				} else if (TinkerNode.class.isAssignableFrom(c)) {
					node = (E) c.getConstructor(Vertex.class).newInstance(this.getVertexForDirection(edge));
				} else {
					node = (E) value;
					this.internalVertexMap.put(value, this.getVertexForDirection(edge));
				}
				this.internalList.add(node);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		this.loaded = true;
	}

	protected Edge addToListAndListIndex(int indexOf, E e) {
		E previous = this.internalList.get(indexOf - 1);
		E current = this.internalList.get(indexOf);
		this.internalList.add(indexOf, e);
		Edge edge = addInternal(e);

		float min;
		float max;
		if (e instanceof TinkerCompositionNode) {
			min = (Float) ((TinkerCompositionNode)previous).getVertex().getProperty("tinkerIndex");
			max = (Float) ((TinkerCompositionNode)current).getVertex().getProperty("tinkerIndex");
		} else if (e.getClass().isEnum()) {
			min = (Float) this.internalVertexMap.get(((Enum<?>) previous).name()).getProperty("tinkerIndex");
			max = (Float) this.internalVertexMap.get(((Enum<?>) current).name()).getProperty("tinkerIndex");
		} else {
			min = (Float) this.internalVertexMap.get(previous).getProperty("tinkerIndex");
			max = (Float) this.internalVertexMap.get(current).getProperty("tinkerIndex");
		}
		float tinkerIndex = (min + max) / 2; 
		this.index.put("index", tinkerIndex, edge);
		edge.getInVertex().setProperty("tinkerIndex", tinkerIndex);
		return edge;
	}
	
	@Override
	public boolean remove(Object o) {
		maybeLoad();
		int indexOf = this.internalList.indexOf(o);
		boolean result = this.internalList.remove(o);
		if (result) {
			Vertex v;
			if (o instanceof TinkerCompositionNode) {
				TinkerCompositionNode node = (TinkerCompositionNode) o;
				v = node.getVertex();
				Set<Edge> edges = GraphDb.getDb().getEdgesBetween(this.vertex, v, this.label);
				for (Edge edge : edges) {
					GraphDb.getDb().removeEdge(edge);
					removeEdgefromIndex(v, edge, indexOf);
					break;
				}
			} else if (o.getClass().isEnum()) {
				v = this.internalVertexMap.get(((Enum<?>) o).name());
				Edge edge = v.getInEdges(this.label).iterator().next();
				removeEdgefromIndex(v, edge, indexOf);
				GraphDb.getDb().removeVertex(v);
			} else {
				v = this.internalVertexMap.get(o);
				Edge edge = v.getInEdges(this.label).iterator().next();
				removeEdgefromIndex(v, edge, indexOf);
				GraphDb.getDb().removeVertex(v);
			}
		}
		return result;
	}

	@Override
	public int size() {
		maybeLoad();
		return this.internalList.size();
	}

	@Override
	public boolean isEmpty() {
		maybeLoad();
		return this.internalList.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		maybeLoad();
		return this.internalList.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		maybeLoad();
		return this.internalList.iterator();
	}

	@Override
	public Object[] toArray() {
		maybeLoad();
		return this.internalList.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		maybeLoad();
		return this.internalList.toArray(a);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		maybeLoad();
		return this.internalList.containsAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		if (!this.loaded) {
			loadFromVertex();
		}
		boolean result = true;
		for (E e : this.internalList) {
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
		for (E e : this.internalList) {
			this.remove(e);
		}
	}

	@Override
	public E get(int index) {
		if (!this.loaded) {
			loadFromVertex();
		}
		return this.internalList.get(index);
	}

	@Override
	public E remove(int index) {
		E e = this.get(index);
		this.remove(e);
		return e;
	}

	@Override
	public int indexOf(Object o) {
		maybeLoad();
		return this.internalList.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		maybeLoad();
		return this.internalList.lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		maybeLoad();
		return this.internalList.listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		maybeLoad();
		return this.internalList.listIterator(index);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		maybeLoad();
		return this.internalList.subList(fromIndex, toIndex);
	}

	protected abstract void removeEdgefromIndex(Vertex v, Edge edge, int indexOf);
}
