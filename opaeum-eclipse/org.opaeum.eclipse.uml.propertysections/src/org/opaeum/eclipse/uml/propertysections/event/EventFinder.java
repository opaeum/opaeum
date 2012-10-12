package org.opaeum.eclipse.uml.propertysections.event;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.context.OpaeumEclipseContext;

public class EventFinder{
public static Event findOrCreateEvent(Element start,Signal signal){
		for(Resource r:start.eResource().getResourceSet().getResources()){
			if(!r.getURI().toString().contains("UML.metamodel.uml") && r.getURI().fileExtension().equals("uml")){
				TreeIterator<EObject> allContents = r.getAllContents();
				while(allContents.hasNext()){
					EObject eObject = (EObject) allContents.next();
					if(eObject instanceof SignalEvent && ((SignalEvent) eObject).getSignal() == signal){
						return (Event) eObject;
					}
				}
			}
		}
		Package pkg = findOrCreatePackage(start);
		SignalEvent event = UMLFactory.eINSTANCE.createSignalEvent();
		event.setName("On" + signal.getName());
		event.setSignal(signal);
		addEvent(pkg, event);
		return event;
	}
	private static Package findOrCreatePackage(Element start){
		Package owner = start.getNearestPackage();
		Package pkg = owner.getNestedPackage("events");
		if(pkg == null){
			pkg = UMLFactory.eINSTANCE.createPackage();
			EditingDomain ed = OpaeumEclipseContext.findOpenUmlFileFor(start).getEditingDomain();
			pkg.setName("events");
			Command add = AddCommand.create(ed, owner, UMLPackage.eINSTANCE.getPackage_PackagedElement(), pkg);
			ed.getCommandStack().execute(add);
		}
		return pkg;
	}
	private static void addEvent(Package pkg,Event event){
		EditingDomain ed = OpaeumEclipseContext.findOpenUmlFileFor(pkg).getEditingDomain();
		Command add = AddCommand.create(ed, pkg, UMLPackage.eINSTANCE.getPackage_PackagedElement(), event);
		ed.getCommandStack().execute(add);
	}
	public static Event findOrCreateEvent(Element start,Operation signal){
		for(Resource r:start.eResource().getResourceSet().getResources()){
			if(!r.getURI().toString().contains("UML.metamodel.uml") && r.getURI().fileExtension().equals("uml")){
				TreeIterator<EObject> allContents = r.getAllContents();
				while(allContents.hasNext()){
					EObject eObject = (EObject) allContents.next();
					if(eObject instanceof CallEvent && ((CallEvent) eObject).getOperation() == signal){
						return (Event) eObject;
					}
				}
			}
		}
		Package pkg = findOrCreatePackage(start);
		CallEvent event = UMLFactory.eINSTANCE.createCallEvent();
		event.setOperation(signal);
		char[] name = signal.getName().toCharArray();
		name[0] = Character.toUpperCase(name[0]);
		event.setName("On" + new String(name));
		addEvent(pkg, event);
		return event;
	}
}
