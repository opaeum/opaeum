package org.util;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.nakeduml.runtime.domain.TinkerNode;

public class TinkerArrayList<E> extends ArrayList<E> implements TinkerList<E> {

	private TinkerNode owner;
	private Method adder;
	private Method remover;

	public TinkerArrayList(TinkerNode owner, Method adder, Method remover) {
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
		return super.add(e);
	}

	public void tinkerAdd(int index, E e) {
		throw new IllegalArgumentException("Method not supprted");
//		super.add(index, e);
	}

	@Override
	public void add(int index, E e) {
		try {
			adder.invoke(owner, e);
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
		super.add(index, e);
	}

	@Override
	public E remove(int index) {
		throw new IllegalArgumentException("Method not supprted");
//		return super.remove(index);
	}

	@Override
	public boolean tinkerRemove(Object o) {
		return super.remove(o);
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
