package org.opaeum.rap.runtime.internal.views;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;

public class PropertyTreeItem {
	PropertyDescriptor descriptor;
	private IPersistentObject owner;
	private PersistentObjectTreeItem parent;

	public PropertyTreeItem(PersistentObjectTreeItem persistentObjectTreeItem,
			PropertyDescriptor descriptor) {
		super();
		this.descriptor = descriptor;
		owner =  persistentObjectTreeItem.getEntity();
		this.parent=persistentObjectTreeItem;
	}
	
	public PropertyDescriptor getDescriptor() {
		return descriptor;
	}

	public IPersistentObject getOwner() {
		return owner;
	}

	public PersistentObjectTreeItem getParent() {
		return parent;
	}

	public Object[] getChildren() {
		Collection<?> children = (Collection<?>) IntrospectionUtil.get(
				descriptor, owner);
		Collection<Object> result = new ArrayList<Object>();
		for (Object object : children) {
			result.add(new PersistentObjectTreeItem(this,(IPersistentObject) object));
		}
		return result.toArray();
	}

}
