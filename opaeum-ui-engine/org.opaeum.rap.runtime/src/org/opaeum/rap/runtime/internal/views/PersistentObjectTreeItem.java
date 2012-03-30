package org.opaeum.rap.runtime.internal.views;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;

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
		}else{
			return false;
		}
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
		PropertyDescriptor[] properties = IntrospectionUtil.getProperties(IntrospectionUtil.getOriginalClass(entity));
		for(PropertyDescriptor pd:properties){
			Method rm = pd.getReadMethod();
			if(rm != null && rm.isAnnotationPresent(PropertyMetaInfo.class)){
				PropertyMetaInfo metaData = rm.getAnnotation(PropertyMetaInfo.class);
				if(metaData.isComposite()){
					if(Collection.class.isAssignableFrom(rm.getReturnType())){
						result.add(new PropertyTreeItem(this, pd));
					}else{
						result.add(new PersistentObjectTreeItem(this, (IPersistentObject) IntrospectionUtil.get(pd, entity)));
					}
				}
			}
		}
		return result.toArray();
	}
}
