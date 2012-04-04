package org.opaeum.rap.runtime.internal.views;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.JavaTypedElement;

public class PropertyTreeItem{
	JavaTypedElement descriptor;
	private IPersistentObject owner;
	private PersistentObjectTreeItem parent;
	public PropertyTreeItem(PersistentObjectTreeItem persistentObjectTreeItem,JavaTypedElement descriptor){
		super();
		this.descriptor = descriptor;
		owner = persistentObjectTreeItem.getEntity();
		this.parent = persistentObjectTreeItem;
	}
	public IPersistentObject getOwner(){
		return owner;
	}
	public PersistentObjectTreeItem getParent(){
		return parent;
	}
	public Object[] getChildren(){
		Collection<?> children = (Collection<?>) descriptor.invokeGetter(owner);
		Collection<Object> result = new ArrayList<Object>();
		for(Object object:children){
			result.add(new PersistentObjectTreeItem(this, (IPersistentObject) object));
		}
		return result.toArray();
	}
	public JavaTypedElement getTypedElement(){
		return descriptor;
	}
}
