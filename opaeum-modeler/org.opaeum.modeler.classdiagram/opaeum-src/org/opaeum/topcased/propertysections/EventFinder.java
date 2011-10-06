package org.opaeum.topcased.propertysections;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

public class EventFinder{
	public static Event findOrCreateEvent(Element start, Signal signal){
		for(Resource r:start.eResource().getResourceSet().getResources()){
			if(!r.getURI().toString().contains("UML.metamodel.uml") && r.getURI().fileExtension().equals("uml")){
				TreeIterator<EObject> allContents = r.getAllContents();
				while(allContents.hasNext()){
					EObject eObject = (EObject) allContents.next();
					if(eObject instanceof SignalEvent && ((SignalEvent) eObject).getSignal()==signal){
						return (Event) eObject;
					}
				}
			}
		}
		Package pkg = start.getNearestPackage().getNestedPackage("events", false,UMLPackage.eINSTANCE.getPackage(),true);
		SignalEvent event = UMLFactory.eINSTANCE.createSignalEvent();
		pkg.getPackagedElements().add(event);
		event.setSignal(signal);
		event.setName("On"+ signal.getName());
		return event;
	}
	public static Event findOrCreateEvent(Element start, Operation signal){
		for(Resource r:start.eResource().getResourceSet().getResources()){
			if(!r.getURI().toString().contains("UML.metamodel.uml") && r.getURI().fileExtension().equals("uml")){
				TreeIterator<EObject> allContents = r.getAllContents();
				while(allContents.hasNext()){
					EObject eObject = (EObject) allContents.next();
					if(eObject instanceof CallEvent && ((CallEvent) eObject).getOperation()==signal){
						return (Event) eObject;
					}
				}
			}
		}
		Package pkg = start.getNearestPackage().getNestedPackage("events", false,UMLPackage.eINSTANCE.getPackage(),true);
		CallEvent event = UMLFactory.eINSTANCE.createCallEvent();
		pkg.getPackagedElements().add(event);
		event.setOperation(signal);
		char[] name=signal.getName().toCharArray();
		name[0]=Character.toUpperCase(name[0]);
		event.setName("On"+ new String(name));
		return event;
	}
}
