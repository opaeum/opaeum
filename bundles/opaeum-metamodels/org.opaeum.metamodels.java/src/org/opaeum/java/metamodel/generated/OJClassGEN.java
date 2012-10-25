package org.opaeum.java.metamodel.generated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;

abstract public class OJClassGEN extends OJClassifier{
	protected Map<String,OJField> fields = new TreeMap<String,OJField>();
	private OJPackage owningPackage = null;
	private Set<OJConstructor> constructors = new HashSet<OJConstructor>();
	private SortedSet<OJPathName> implementedInterfaces = new TreeSet<OJPathName>();
	private OJPathName superclass = null;
	protected OJClassGEN(String name){
		super(name);
	}
	@Override
	public void finalize(){
		super.finalize();
	}
	public OJConstructor getDefaultConstructor(){
		return any1();
	}
	public OJField findField(String name){
		return fields.get(name);
	}
	public void setFields(Collection<OJField> elements){
		for(OJField _internal:this.fields.values()){
			_internal.z_internalRemoveFromOwner(((OJClass) this));
		}
		for(OJField _internal:elements){
			addToFields(_internal);
		}
	}
	public void addToFields(OJField element){
		if(element == null){
			return;
		}
		if(element.getOwner() != null){
			element.getOwner().z_internalRemoveFromFields(element);
		}
		this.fields.put(element.getName(), element);
		element.z_internalAddToOwner(((OJClass) this));
	}
	public void removeFromFields(OJField element){
		if(element == null){
			return;
		}
		this.fields.remove(element.getName());
		element.z_internalRemoveFromOwner(((OJClass) this));
	}
	public Collection<OJField> getFields(){
		if(fields != null){
			return Collections.unmodifiableCollection(fields.values());
		}else{
			return null;
		}
	}
	public void z_internalAddToFields(OJField element){
		this.fields.put(element.getName(), element);
	}
	public void z_internalRemoveFromFields(OJField element){
		this.fields.remove(element);
	}
	public void addToFields(Collection<OJField> newElems){
		for(OJField item:newElems){
			this.addToFields(item);
		}
	}
	public void removeFromFields(Collection<OJField> oldElems){
		for(OJField item:oldElems){
			this.removeFromFields(item);
		}
	}
	public void removeAllFromFields(){
		for(OJField item:new ArrayList<OJField>(getFields())){
			this.removeFromFields(item);
		}
	}
	public void setMyPackage(OJPackage element){
		if(this.owningPackage != element){
			if(this.owningPackage != null){
				this.owningPackage.z_internalRemoveFromClasses(((OJClass) this));
			}
			this.owningPackage = element;
			if(element != null){
				element.z_internalAddToClasses(((OJClass) this));
			}
		}
	}
	public OJPackage getMyPackage(){
		return owningPackage;
	}
	public void z_internalAddToMyPackage(OJPackage element){
		this.owningPackage = element;
	}
	public void z_internalRemoveFromMyPackage(OJPackage element){
		this.owningPackage = null;
	}
	public void setConstructors(Set<OJConstructor> elements){
		if(this.constructors != elements){
			for(OJConstructor _internal:this.constructors){
				_internal.z_internalRemoveFromOwningClass(((OJClass) this));
			}
			this.constructors = elements;
			if(constructors != null){
				for(OJConstructor _internal:constructors){
					_internal.z_internalAddToOwningClass(((OJClass) this));
				}
			}
		}
	}
	public void addToConstructors(OJConstructor element){
		if(element == null){
			return;
		}
		if(this.constructors.contains(element)){
			this.constructors.remove(element);
		}
		if(element.getOwningClass() != null){
			element.getOwningClass().z_internalRemoveFromConstructors(element);
		}
		this.constructors.add(element);
		element.z_internalAddToOwningClass(((OJClass) this));
	}
	public void removeFromConstructors(OJConstructor element){
		if(element == null){
			return;
		}
		this.constructors.remove(element);
		element.z_internalRemoveFromOwningClass(((OJClass) this));
	}
	public Set<OJConstructor> getConstructors(){
		if(constructors != null){
			return Collections.unmodifiableSet(constructors);
		}else{
			return null;
		}
	}
	public void z_internalAddToConstructors(OJConstructor element){
		this.constructors.add(element);
	}
	public void z_internalRemoveFromConstructors(OJConstructor element){
		this.constructors.remove(element);
	}
	public void addToConstructors(Collection<OJConstructor> newElems){
		for(OJConstructor item:newElems){
			this.addToConstructors(item);
		}
	}
	public void removeFromConstructors(Collection<OJConstructor> oldElems){
		for(OJConstructor item:oldElems){
			this.removeFromConstructors(item);
		}
	}
	public void removeAllFromConstructors(){
		for(OJConstructor item:new HashSet<OJConstructor>(getConstructors())){
			this.removeFromConstructors(item);
		}
	}
	public Set<OJPathName> getImplementedInterfaces(){
		return implementedInterfaces;
	}
	public void setImplementedInterfaces(SortedSet<OJPathName> element){
		if(implementedInterfaces != element){
			implementedInterfaces = element;
		}
	}
	public void addToImplementedInterfaces(OJPathName element){
		if(implementedInterfaces.contains(element)){
			return;
		}
		implementedInterfaces.add(element);
	}
	public void removeFromImplementedInterfaces(OJPathName element){
		implementedInterfaces.remove(element);
	}
	public void addToImplementedInterfaces(Collection<OJPathName> newElems){
		for(OJPathName item:newElems){
			addToImplementedInterfaces(item);
		}
	}
	public void removeFromImplementedInterfaces(Collection<OJPathName> oldElems){
		for(OJPathName item:oldElems){
			removeFromImplementedInterfaces(item);
		}
	}
	public void removeAllFromImplementedInterfaces(){
		for(OJPathName item:new HashSet<OJPathName>(getImplementedInterfaces())){
			removeFromImplementedInterfaces(item);
		}
	}
	public OJPathName getSuperclass(){
		return superclass;
	}
	public void setSuperclass(OJPathName element){
		if(superclass != element){
			superclass = element;
		}
	}
	private OJConstructor any1(){
		OJConstructor result = null;
		Iterator<OJConstructor> it = this.getConstructors().iterator();
		while(it.hasNext()){
			OJConstructor c = it.next();
			if(c.getParameters().isEmpty()){
				return c;
			}
		}
		return result;
	}
	public String getIdString(){
		String result = "";
		result = super.getIdString();
		return result;
	}
}