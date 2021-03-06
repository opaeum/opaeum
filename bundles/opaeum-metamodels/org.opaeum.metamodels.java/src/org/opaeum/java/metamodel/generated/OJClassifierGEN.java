package org.opaeum.java.metamodel.generated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJInterface;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibleElement;

abstract public class OJClassifierGEN extends OJVisibleElement{
	private int uniqueNumber = 0;
	private boolean isAbstract = false;
	protected Map<String,Set<OJOperation>> f_operations = new TreeMap<String,Set<OJOperation>>();
	private Set<OJPathName> imports = new HashSet<OJPathName>();
	protected OJPackage f_myPackage = null;
	protected OJClassifierGEN(String name){
		super(name);
	}
	public OJOperation findOperation(String name,List<OJPathName> types){
		Set<OJOperation> set = this.f_operations.get(name);
		if(set==null){
			return null;
		}else{
			for(OJOperation o:set){
				if(o.isEqual(name, types)){
					return o;
				}
			}
		}
		return null;
	}
	public OJPathName getPathName(){
		OJPackage myPackage = this.getMyPackage();
		return myPackage.getPathName().append(this.getName());
	}
	public int getUniqueNumber(){
		return uniqueNumber;
	}
	public void setUniqueNumber(int element){
		if(uniqueNumber != element){
			uniqueNumber = element;
		}
	}
	public boolean isAbstract(){
		return isAbstract;
	}
	public void setAbstract(boolean element){
		if(isAbstract != element){
			isAbstract = element;
		}
	}
	/**
	 * Implements the setter for feature '+ operations : OrderedSet(OJOperation)'
	 * 
	 * @param elements
	 */
	public void setOperations(Set<OJOperation> elements){
		Set<OJOperation> operations = getOperations();
		for(OJOperation x:operations){
			x.z_internalRemoveFromOwner((OJClassifier) ((OJClassifier) this));
		}
		this.f_operations.clear();
		for(OJOperation ojOperation:elements){
			addToOperations(ojOperation);
		}
	}
	/**
	 * Implements addition of a single element to feature '+ operations : OrderedSet(OJOperation)'
	 * 
	 * @param element
	 */
	public void addToOperations(OJOperation element){
		if(element == null){
			return;
		}
		Set<OJOperation> set = this.f_operations.get(element.getName());
		if(set != null && set.contains(element)){
			set.remove(element);
		}
		if(element.getOwner() != null){
			element.getOwner().z_internalRemoveFromOperations(element);
		}
		z_internalAddToOperations(element);
		element.z_internalAddToOwner((OJClassifier) ((OJClassifier) this));
	}
	/**
	 * Implements removal of a single element from feature '+ operations : OrderedSet(OJOperation)'
	 * 
	 * @param element
	 */
	public void removeFromOperations(OJOperation element){
		if(element == null){
			return;
		}
		z_internalAddToOperations(element);
		element.z_internalRemoveFromOwner((OJClassifier) ((OJClassifier) this));
	}
	/**
	 * Implements the getter for + operations : OrderedSet(OJOperation)
	 */
	public Set<OJOperation> getOperations(){
		if(f_operations != null){
			Set<OJOperation> result = new HashSet<OJOperation>();
			for(Set<OJOperation> set:f_operations.values()){
				result.addAll(set);
			}
			return Collections.unmodifiableSet(result);
		}else{
			return null;
		}
	}
	/**
	 * This operation should NOT be used by clients. It implements the correct addition of an element in an association.
	 * 
	 * @param element
	 */
	public void z_internalAddToOperations(OJOperation element){
		Set<OJOperation> set = this.f_operations.get(element.getName());
		if(set == null){
			set = new HashSet<OJOperation>();
			this.f_operations.put(element.getName(), set);
		}
		set.add(element);
	}
	/**
	 * This operation should NOT be used by clients. It implements the correct removal of an element in an association.
	 * 
	 * @param element
	 */
	public void z_internalRemoveFromOperations(OJOperation element){
		Set<OJOperation> set = this.f_operations.get(element.getName());
		if(set != null && set.contains(element)){
			set.remove(element);
		}
	}
	public Set<OJPathName> getImports(){
		return imports;
	}
	public void setImports(Set<OJPathName> element){
		if(imports != element){
			imports = element;
		}
	}
	public void addToImports(OJPathName element){
		if(imports.contains(element)){
			return;
		}
		imports.add(element);
	}
	public void removeFromImports(OJPathName element){
		imports.remove(element);
	}
	public void addToImports(Collection<OJPathName> newElems){
		Iterator<OJPathName> it = newElems.iterator();
		while((it.hasNext())){
				addToImports(it.next());
		}
	}


	public void removeAllFromImports(){
		/* make a copy of the collection in order to avoid a ConcurrentModificationException */
		Iterator<OJPathName> it = new HashSet<OJPathName>(getImports()).iterator();
		while((it.hasNext())){
				removeFromImports(it.next());
		}
	}
	public OJPackage getMyPackage(){
		setMyPackage(((this.getClass() == OJClass.class) ? ((OJClass) ((OJClassifier) this)).getMyPackage()
				: ((this.getClass() == OJInterface.class) ? (OJPackage) ((OJInterface) ((OJClassifier) this)).getMyPackage() : (OJPackage) null)));
		return f_myPackage;
	}
	protected void setMyPackage(OJPackage element){
		if(f_myPackage != element){
			f_myPackage = element;
		}
	}
	public String toString(){
		String result = "";
		result = super.toString();
		result = result + " uniqueNumber:" + this.getUniqueNumber() + ", ";
		result = result + " isAbstract:" + this.isAbstract();
		return result;
	}
	public String getIdString(){
		String result = "";
		result = result + this.getUniqueNumber();
		return result;
	}
	public void copyInfoInto(OJClassifier copy){
		super.copyInfoInto(copy);
		copy.setUniqueNumber(getUniqueNumber());
		copy.setAbstract(isAbstract());
		Iterator<OJOperation> operationsIt = new ArrayList<OJOperation>(getOperations()).iterator();
		while(operationsIt.hasNext()){
			OJOperation elem =operationsIt.next();
			copy.addToOperations(elem);
		}
		Iterator<OJPathName> importsIt = new ArrayList<OJPathName>(getImports()).iterator();
		while(importsIt.hasNext()){
			OJPathName elem = importsIt.next();
			copy.addToImports(elem);
		}
	}
}