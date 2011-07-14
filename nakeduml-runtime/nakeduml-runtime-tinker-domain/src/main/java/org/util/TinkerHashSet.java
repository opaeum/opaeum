package org.util;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.nakeduml.runtime.domain.TinkerNode;

public class TinkerHashSet<E> extends HashSet<E> implements TinkerSet<E>, TinkerCollection<E> {

	private static final long serialVersionUID = -3482884255366688370L;

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

	public TinkerHashSet(TinkerHashSet<E> ... tinkerSet) {
		for (TinkerHashSet<E> tinkerHashSet : tinkerSet) {
			methodMap.putAll(tinkerHashSet.methodMap);
			tinkerAddAll(tinkerHashSet);
		}
	}

	public TinkerHashSet(Class<? extends TinkerNode> collectionType, TinkerNode owner, Method adder, Method remover) {
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
		return super.add(e);
	}
	
	@Override
	public boolean add(E e) {
		try {
			MethodHolder methodHolder = methodMap.get(e.getClass());
			if (methodHolder==null) {
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
		return super.add(e);
	}

	@Override
	public boolean tinkerRemove(Object e) {
		return super.remove(e);
	}

	@Override
	public boolean remove(Object o) {
		try {
			MethodHolder methodHolder = methodMap.get(o.getClass());
			if (methodHolder==null) {
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
		return super.remove(o);
	}

}
