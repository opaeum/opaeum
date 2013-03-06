package org.opaeum.runtime.jface.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.JavaTypedElement;
import org.opaeum.runtime.environment.JavaTypedElementContainer;
import org.opaeum.runtime.jface.entityeditor.SecurityWrapper;
import org.opaeum.runtime.rwt.OpaeumRapSession;
import org.opaeum.uim.perspective.ClassNavigationConstraint;
import org.opaeum.uim.perspective.PropertyNavigationConstraint;

public class PersistentObjectTreeItem extends SecurityWrapper implements IAdaptable{
	private NavigationContext context;
	private IPersistentObject entity;
	private Object parent;
	private ClassNavigationConstraint configuration;
	public PersistentObjectTreeItem(IPersistentObject entity,NavigationContext context){
		this.entity = entity;
		this.context = context;
	}
	public PersistentObjectTreeItem(Object parent,IPersistentObject entity){
		super();
		this.entity = entity;
		this.parent = parent;
		getNavigationContext().getObjectItemMap().put(entity, this);
	}
	protected NavigationContext getNavigationContext(){
		if(context == null){
			if(parent instanceof PersistentObjectTreeItem){
				context = ((PersistentObjectTreeItem) parent).getNavigationContext();
			}else if(parent instanceof PropertyTreeItem){
				context = ((PropertyTreeItem) parent).getParent().getNavigationContext();
			}
		}
		return context;
	}
	public Object getParent(){
		return parent;
	}
	@Override
	public boolean equals(Object obj){
		if(obj instanceof PersistentObjectTreeItem && ((PersistentObjectTreeItem) obj).entity != null){
			return ((PersistentObjectTreeItem) obj).entity.equals(entity);
		}
		return false;
	}
	@Override
	public int hashCode(){
		return entity.hashCode();
	}
	public IPersistentObject getEntity(){
		return entity;
	}
	public Object[] getChildren(){
		Collection<Object> result = new ArrayList<Object>();
		ClassNavigationConstraint tec = getConfiguration();
		for(PropertyNavigationConstraint pc:tec.getProperties()){
			if(!pc.isHidden()){
				JavaTypedElement jte = getEnv().getMetaInfoMap().getTypedElement(IntrospectionUtil.getOriginalClass(entity), pc.getUmlElementUid());
				if(jte.isComposite()){
					if(jte.isMany()){
						result.add(new PropertyTreeItem(this, jte, pc));
					}else{
						result.add(new PersistentObjectTreeItem(this, (IPersistentObject) jte.invokeGetter(entity)));
					}
				}
			}
		}
		return result.toArray();
	}
	private ClassNavigationConstraint getConfiguration(){
		if(this.configuration==null){
			Class<? extends IPersistentObject> originalClass = IntrospectionUtil.getOriginalClass(entity);
			configuration= getNavigationContext().getClassConfiguration(originalClass.getAnnotation(NumlMetaInfo.class).uuid());
		}
		return configuration;
	}
	@Override
	public Object getAdapter(Class adapter){
		if(adapter == IPersistentObject.class){
			return entity;
		}
		return null;
	}
	public Environment getEnv(){
		return getNavigationContext().getEnvironment();
	}
	@Override
	public OpaeumRapSession getSession(){
		return getNavigationContext().getOpaeumRapSession();
	}
	@Override
	public IPersistentObject getSelectedObject(){
		return entity;
	}
}
