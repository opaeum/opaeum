package org.opaeum.runtime.jface.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaTypedElement;

public class PropertyTreeItem implements IAdaptable{
	JavaTypedElement descriptor;
	private IPersistentObject owner;
	private PersistentObjectTreeItem parent;
	private Map<IPersistentObject,PersistentObjectTreeItem> objectItemMap;
	public PropertyTreeItem(PersistentObjectTreeItem persistentObjectTreeItem,JavaTypedElement descriptor,
			Map<IPersistentObject,PersistentObjectTreeItem> objectItemMap){
		super();
		this.descriptor = descriptor;
		owner = persistentObjectTreeItem.getEntity();
		this.parent = persistentObjectTreeItem;
		this.objectItemMap = objectItemMap;
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
			result.add(new PersistentObjectTreeItem(this, (IPersistentObject) object, this.objectItemMap,parent.getEnv()));
		}
		return result.toArray();
	}
	public JavaTypedElement getTypedElement(){
		return descriptor;
	}
	@Override
	public Object getAdapter(Class adapter){
		if(adapter==IPersistentObject.class){
			return owner;
		}
		return null;
	}

}
