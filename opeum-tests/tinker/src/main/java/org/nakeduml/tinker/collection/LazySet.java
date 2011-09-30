package org.opeum.tinker.collection;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public class LazySet<E> extends AbstractCollection<E> implements Set<E> {

	private Iterator<Edge> outEdges;
	private Collection<E> delegate = new HashSet<E>();
	private boolean complete = false;

	public LazySet(Set set) {
		if (set instanceof LazySet) {
			this.outEdges = ((LazySet)set).outEdges;
		} else {
			complete=true;
			delegate = set;
		}
	}
	
	public LazySet(Iterable<Edge> outEdges) {
		this.outEdges = outEdges.iterator();
	}

	@Override
	public Iterator<E> iterator() {
		return new DelegateIterator();
	}

	@Override
	public int size() {
		if (complete) {
			return delegate.size();
		} else {
			int count = 0;
			while (outEdges.hasNext()) {
				outEdges.next();
				count++;
			}
			complete = true;
			return count;
		}
	}

	@Override
	public boolean contains(Object o) {
		if (complete) {
			return delegate.contains(o);
		} else {
			while (outEdges.hasNext()) {
				Edge edge = (Edge) outEdges.next();
				try {
					Class<?> c = Class.forName((String) edge.getProperty("inClass"));
					E newInstance = null;
					if (o == null && edge.getInVertex() == null) {
					} else {
						newInstance = (E) c.getConstructor(Vertex.class).newInstance(edge.getInVertex());
					}
					delegate.add(newInstance);
					return true;
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			complete = true;
		}
		return false;
	}

	@Override
	public boolean add(E e) {
		throw new UnsupportedOperationException();
	}
	
	private final class DelegateIterator implements Iterator<E> {

		private Iterator<E> delegateIterator = delegate.iterator();
		boolean finshed = false;
		boolean started = false;
		private E previousE;
		
		@Override
		public boolean hasNext() {
			if (complete && !started && !finshed) {
				return delegateIterator.hasNext();
			} else {
				return outEdges.hasNext();
			}
		}

		@Override
		public E next() {
			if (complete && !started && !finshed) {
				return delegateIterator.next();
			} else {
				if (delegateIterator.hasNext()) {
					return delegateIterator.next();
				} else {
					try {
						started = true;
						Edge edge = outEdges.next();
						Class<?> c = Class.forName((String) edge.getProperty("inClass"));
						previousE = (E)c.getConstructor(Vertex.class).newInstance(edge.getInVertex());
						delegate.add(previousE);
						complete = !outEdges.hasNext();
						finshed = complete;
						return previousE;
					} catch (NoSuchElementException e) {
						throw e;
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}
		}

		@Override
		public void remove() {
			if (complete && !started && !finshed) {
				delegateIterator.remove();	
			} else {
				if (delegateIterator.hasNext()) {
					delegateIterator.remove();
				} else {
					outEdges.remove();
					delegate.remove(previousE);				}
			}
		}
		
	}
}
