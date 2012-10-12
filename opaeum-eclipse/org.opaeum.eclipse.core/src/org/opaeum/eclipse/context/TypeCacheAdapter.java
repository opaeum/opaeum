package org.opaeum.eclipse.context;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.eclipse.ProfileApplier;

public class TypeCacheAdapter implements Adapter.Internal{
	private Map<EClassifier,Collection<EObject>> cache = Collections.synchronizedMap(new HashMap<EClassifier,Collection<EObject>>());
	private Map<Stereotype,Collection<Element>> stereotypeCache = Collections.synchronizedMap(new HashMap<Stereotype,Collection<Element>>());
	Set<EClass> relevantClasses = new HashSet<EClass>();
	private Set<String> ignoredLibraries = new HashSet<String>();
	{
		ignoredLibraries.add("ECorePrimitiveTypes".toUpperCase());
		ignoredLibraries.add("UMLPrimitiveTypes".toUpperCase());
		ignoredLibraries.add("JavaPrimitiveTypes".toUpperCase());
	}
	public TypeCacheAdapter(){
		this.relevantClasses = new HashSet<EClass>();
	}
	private void caseEobject(Notification notification){
		switch(notification.getEventType()){
		case Notification.ADD:{
			Object newValue = notification.getNewValue();
			if(newValue != null && (newValue instanceof EObject)){
				addObjectInCache((EObject) newValue);
			}
			break;
		}
		case Notification.ADD_MANY:{
			for(Object newValue:(Collection<?>) notification.getNewValue()){
				if(newValue != null && (newValue instanceof EObject)){
					addObjectInCache((EObject) newValue);
				}
			}
			break;
		}
		case Notification.REMOVE:{
			Object oldValue = notification.getOldValue();
			if(oldValue != null && (oldValue instanceof EObject)){
				removeObjectFromCache((EObject) oldValue);
			}
			break;
		}
		case Notification.REMOVE_MANY:{
			for(Object oldValue:(Collection<?>) notification.getOldValue()){
				if(oldValue != null && (oldValue instanceof EObject)){
					removeObjectFromCache((EObject) oldValue);
				}
			}
			break;
		}
		}
	}
	private void addObjectInCache(EObject newObj){
		EClass eClass = newObj.eClass();
		putObjectInCache(eClass, newObj);
		for(EClass eSuperClass:eClass.getEAllSuperTypes()){
			putObjectInCache(eSuperClass, newObj);
		}
	}
	private void putObjectInCache(EClassifier eClassifier,EObject eObject){
		boolean valid = true;
		if(eObject instanceof NamedElement){
			EObject rootContainer = EcoreUtil.getRootContainer(eObject);
			if(rootContainer instanceof Package){
				org.eclipse.uml2.uml.Package model = (org.eclipse.uml2.uml.Package) rootContainer;
				// filter out metamodels
				valid = model.getAppliedProfile("Ecore") == null && !ignoredLibraries.contains(model.getName().toUpperCase());
			}else{
				System.out.println();
			}
		}
		valid = valid && !(eObject instanceof ENamedElement);
		if(valid){
			Collection<EObject> listOfClassifiers = cache.get(eClassifier);
			if(listOfClassifiers != null && relevantClasses.contains(eClassifier)){
				listOfClassifiers.add(eObject);
				if(eObject instanceof Element){
					putInStereotypeCache((Element) eObject);
				}
			}
		}
	}
	protected void putInStereotypeCache(Element element){
		for(Stereotype st:element.getAppliedStereotypes()){
			Collection<Element> collection = stereotypeCache.get(st);
			if(collection == null){
				collection = new HashSet<Element>();
				stereotypeCache.put(st, collection);
			}
			collection.add(element);
		}
	}
	private void removeObjectFromCache(EObject newObj){
		EClass eClass = newObj.eClass();
		removeObjectFromCache(eClass, newObj);
		for(EClass eSuperClass:eClass.getEAllSuperTypes()){
			removeObjectFromCache(eSuperClass, newObj);
		}
	}
	private void removeObjectFromCache(EClassifier eClassifier,EObject newObj){
		Collection<EObject> listOfClassifiers = cache.get(eClassifier);
		if(listOfClassifiers != null){
			listOfClassifiers.remove(newObj);
		}
	}
	public boolean isAdapterForType(Object type){
		return TypeCacheAdapter.class.equals(type);
	}
	public Collection<EObject> getReachableObjectsOfType(EObject object,EClass type){
		if(!cache.containsKey(type)){
			relevantClasses.add(type);
			cache.put(type, new HashSet<EObject>());
			Collection<EObject> elements = ItemPropertyDescriptor.getReachableObjectsOfType(object, type);
			for(EObject eObject:elements){
				putObjectInCache(type, eObject);
				if(eObject instanceof Element){
					putInStereotypeCache((Element) eObject);
				}
			}
		}
		return cache.get(type);
	}
	public void dispose(){
		cache.clear();
		cache = null;
	}
	public void notifyChanged(Notification notification){
		if(notification.getNotifier() instanceof EObject){
			caseEobject(notification);
		}
	}
	public void setTarget(Notifier target){
	}
	public void unsetTarget(Notifier target){
	}
	public Notifier getTarget(){
		return null;
	}
	public boolean isAlreadyComputed(EClassifier type){
		return cache.containsKey(type);
	}
	public Collection<Element> getReachableObjectsOfStereotype(EObject element,String profile,String type){
		Stereotype st = ProfileApplier.getProfile(element, profile).getOwnedStereotype(type);
		Collection<Element> result = stereotypeCache.get(st);
		if(result == null){
			result = new HashSet<Element>();
			stereotypeCache.put(st, result);
			Collection<EObject> reachableObjectsOfType = ItemPropertyDescriptor.getReachableObjectsOfType(element, st.getDefinition());
			for(EObject sa:reachableObjectsOfType){
				result.add(UMLUtil.getBaseElement(sa));
			}
		}
		return result;
	}
}
