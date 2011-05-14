package org.util;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.nakeduml.runtime.domain.TinkerNode;

public class TinkerHashSet<E> extends HashSet<E> implements TinkerSet<E> {

	private TinkerNode owner;
	private Method adder;
	private Method remover;

	public TinkerHashSet(TinkerNode owner, Method adder, Method remover) {
		super();
		this.owner = owner;
		this.adder = adder;
		this.remover = remover;
	}

	@Override
	public boolean tinkerAdd(E e) {
		return super.add(e);
	}
	
	@Override
	public boolean add(E e) {
		try {
			adder.invoke(owner, e);
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
		return super.add(e);
	}

	@Override
	public boolean tinkerRemove(TinkerNode e) {
		return super.remove(e);
	}

	@Override
	public boolean remove(Object o) {
		try {
			remover.invoke(owner, o);
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
		return super.remove(o);
	}

}
