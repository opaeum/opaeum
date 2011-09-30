package org.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.opeum.runtime.domain.TinkerNode;

public class TinkerArrayList<E> extends ArrayList<E> implements TinkerList<E>, TinkerCollection<E> {

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

	public TinkerArrayList(TinkerArrayList ... tinkerList) {
		for (TinkerArrayList tinkerArrayList : tinkerList) {
			methodMap.putAll(tinkerArrayList.methodMap);
			tinkerAddAll(tinkerArrayList);
		}
	}
	
	public boolean tinkerAddAll(Collection<? extends E> c) {
		boolean result = true;
		for (E e : c) {
			if (super.add(e)) {
				result = false;
			}
		}
		return result;
	}

	public TinkerArrayList(Class<? extends TinkerNode> collectionType, TinkerNode owner, Method adder, Method remover) {
		super();
		methodMap.put(collectionType, new MethodHolder(owner, adder, remover));
	}
	
	@Override
	public boolean tinkerAdd(E e) {
		return super.add(e); 
	}
	
	@Override
	public boolean add(E e) {
		try {
			MethodHolder methodHolder = methodMap.get(e.getClass());
			methodHolder.adder.invoke(methodHolder.owner, e);
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
		return super.add(e);
	}

	public void tinkerAdd(int index, E e) {
		super.add(index, e);
	}

	@Override
	public void add(int index, E e) {
		try {
			MethodHolder methodHolder = methodMap.get(e.getClass());
			methodHolder.adder.invoke(methodHolder.owner, e);
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
			MethodHolder methodHolder = methodMap.get(o.getClass());
			methodHolder.remover.invoke(methodHolder.owner, o);
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
		return super.remove(o);
	}

}
