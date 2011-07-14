package org.util;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.faces.model.DataModel;
import javax.faces.model.DataModelEvent;
import javax.faces.model.DataModelListener;

import org.apache.commons.collections.set.ListOrderedSet;
import org.nakeduml.runtime.domain.TinkerNode;
import org.util.TinkerCollection;
import org.util.TinkerSet;

public class TinkerJsfHashSet<E> extends DataModel<E> implements TinkerSet<E>, TinkerCollection<E> {

	private int _rowIndex = -1;
	private ListOrderedSet internal = new ListOrderedSet();

	Map<Class<? extends TinkerNode>, MethodHolder> methodMap = new HashMap<Class<? extends TinkerNode>, MethodHolder>();

	private class MethodHolder {
		private TinkerNode owner;
		private Method adder;
		private Method remover;

		MethodHolder(TinkerNode owner, Method adder, Method remover) {
			this.owner = owner;
			this.adder = adder;
			this.remover = remover;
		}
	}

	public TinkerJsfHashSet(TinkerJsfHashSet<E> ... tinkerSet) {
		for (TinkerJsfHashSet<E> tinkerHashSet : tinkerSet) {
			methodMap.putAll(tinkerHashSet.methodMap);
			tinkerAddAll(tinkerHashSet);
		}
	}

	public TinkerJsfHashSet(Class<? extends TinkerNode> collectionType, TinkerNode owner, Method adder, Method remover) {
		super();
		methodMap.put(collectionType, new MethodHolder(owner, adder, remover));
	}

	public boolean tinkerAddAll(Collection<? extends E> c) {
		boolean modified = false;
		Iterator<? extends E> e = c.iterator();
		while (e.hasNext()) {
			if (tinkerAdd(e.next()))
				modified = true;
		}
		return modified;
	}

	@Override
	public boolean tinkerAdd(E e) {
		return internal.add(e);
	}

	@Override
	public boolean add(E e) {
		try {
			MethodHolder methodHolder = methodMap.get(e.getClass());
			if (methodHolder == null) {
				for (Class<? extends TinkerNode> key : methodMap.keySet()) {
					if (key.isAssignableFrom(e.getClass())) {
						methodHolder = methodMap.get(key);
					}
				}
			}
			methodHolder.adder.invoke(methodHolder.owner, e);
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
		return internal.add(e);
	}

	@Override
	public boolean tinkerRemove(Object e) {
		return internal.remove(e);
	}

	@Override
	public boolean remove(Object o) {
		try {
			MethodHolder methodHolder = methodMap.get(o.getClass());
			if (methodHolder == null) {
				for (Class<? extends TinkerNode> key : methodMap.keySet()) {
					if (key.isAssignableFrom(o.getClass())) {
						methodHolder = methodMap.get(key);
					}
				}
			}
			methodHolder.remover.invoke(methodHolder.owner, o);
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
		return internal.remove(o);
	}

	@Override
	public int getRowCount() {
		if (this.internal == null) {
			return -1;
		}
		return this.internal.size();
	}

	@Override
	public E getRowData() {
		if (this.internal == null) {
			return null;
		}
		if (!isRowAvailable()) {
			throw new IllegalArgumentException("row is unavailable");
		}
		return (E) this.internal.get(_rowIndex);
	}
	
	@Override
	public int getRowIndex() {
		return _rowIndex;
	}

	@Override
	public Object getWrappedData() {
		return this.internal;
	}

	@Override
	public boolean isRowAvailable() {
		return this.internal != null && _rowIndex >= 0 && _rowIndex < this.internal.size();
	}

	@Override
	public void setRowIndex(int rowIndex) {
		if (rowIndex < -1) {
			throw new IllegalArgumentException("illegal rowIndex " + rowIndex);
		}
		int oldRowIndex = _rowIndex;
		_rowIndex = rowIndex;
		if (this.internal != null && oldRowIndex != _rowIndex) {
			E data = isRowAvailable() ? getRowData() : null;
			DataModelEvent event = new DataModelEvent(this, _rowIndex, data);
			DataModelListener[] listeners = getDataModelListeners();
			for (int i = 0; i < listeners.length; i++) {
				listeners[i].rowSelected(event);
			}
		}
	}

	@Override
	public void setWrappedData(Object data) {
		this.internal =  (ListOrderedSet) data;
		int rowIndex = this.internal != null ? 0 : -1;
		setRowIndex(rowIndex);
	}

	/* set semantics */

	@Override
	public int size() {
		return this.internal.size();
	}

	@Override
	public boolean isEmpty() {
		return this.internal.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return this.internal.contains(o);
	}

	@Override
	public Object[] toArray() {
		return this.internal.toArray();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.internal.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return this.internal.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return this.internal.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return this.internal.removeAll(c);
	}

	@Override
	public void clear() {
		this.internal.clear();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return (T[]) this.internal.toArray(a);
	}

}
