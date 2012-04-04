package org.opaeum.rap.runtime.internal.views;

import java.util.ArrayList;
import java.util.Collection;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.JavaTypedElement;
import org.opaeum.runtime.environment.JavaTypedElementContainer;

public class PersistentObjectTreeItem{
	private IPersistentObject entity;
	private Object parent;
	public Object getParent(){
		return parent;
	}
	@Override
	public boolean equals(Object obj){
		if(obj instanceof PersistentObjectTreeItem){
			return ((PersistentObjectTreeItem) obj).entity.equals(entity);
		}
		return false;
	}
	@Override
	public int hashCode(){
		return entity.hashCode();
	}
	public PersistentObjectTreeItem(Object parent,IPersistentObject entity){
		super();
		this.entity = entity;
		this.parent = parent;
	}
	public IPersistentObject getEntity(){
		return entity;
	}
	public Object[] getChildren(){
		Collection<Object> result = new ArrayList<Object>();
		Class<IPersistentObject> originalClass = IntrospectionUtil.getOriginalClass(entity);
		JavaTypedElementContainer tec = Environment.getInstance().getMetaInfoMap()
				.getTypedElementContainer(originalClass.getAnnotation(NumlMetaInfo.class).uuid());
		for(JavaTypedElement pd:tec.getTypedElements().values()){
			if(pd.isComposite()){
				if(pd.isMany()){
					result.add(new PropertyTreeItem(this, pd));
				}else{
					result.add(new PersistentObjectTreeItem(this, (IPersistentObject) pd.invokeGetter(entity)));
				}
			}
		}
		return result.toArray();
	}
}
