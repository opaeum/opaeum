package org.opaeum.javageneration.basicjava;

import java.util.Set;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfElementUtil;
import org.opaeum.eclipse.EmfOperationUtil;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.javageneration.maps.AssociationClassEndMap;

public abstract class AbstractStructureVisitor extends StereotypeAnnotator{
	public AbstractStructureVisitor(){
		super();
	}
	protected boolean ignoreDeletedElements(){
		return true;
	}
	protected abstract void visitProperty(Classifier owner,PropertyMap buildStructuralFeatureMap);
	protected abstract void visitComplexStructure(Classifier umlOwner);
	@VisitBefore(matchSubclasses = true,match = {Class.class,DataType.class,Association.class,Signal.class,Component.class,Enumeration.class,
			Behavior.class,Actor.class,Collaboration.class})
	public void visitFeaturesOf(Classifier c){
		if(ojUtil.hasOJClass(c) || (EmfElementUtil.isMarkedForDeletion(c) && !ignoreDeletedElements())){
			if(EmfClassifierUtil.isComplexStructure(c)){
				visitComplexStructure((Classifier) c);
				if(c instanceof Behavior){
					Behavior umlOwner = (Behavior) c;
					if(umlOwner instanceof Activity){
						Activity a = (Activity) umlOwner;
						if(EmfBehaviorUtil.hasExecutionInstance(a)){
							visitActivityNodesRecursively(a, a);
						}
					}
				}
			}
			Set<Property> directlyImplementedAttributes = getLibrary().getDirectlyImplementedAttributes(c);
			for(Property p:directlyImplementedAttributes){
				if(p.isNavigable()){
					if( ojUtil.hasOJClass((Classifier) p.getAssociation())){
						visitAssociationClassProperty(c, new AssociationClassEndMap(ojUtil, p));
					}else{
						PropertyMap buildStructuralFeatureMap = ojUtil.buildStructuralFeatureMap(p);
						visitProperty(c, buildStructuralFeatureMap);
						
					}
				}
			}
			for(Operation o:EmfOperationUtil.getDirectlyImplementedOperations(c)){
				visitOperation(o);
			}
		}
	}
	protected void visitActivityNodesRecursively(Classifier owner,Namespace container){
		for(ActivityNode n:EmfActivityUtil.getActivityNodes(container)){

			if(EmfActionUtil.isEmbeddedTask(n)){
				visitTask((Action) n);
			}else if(n instanceof CallAction){
				visitCallAction((CallAction) n);
			}else if(n instanceof AcceptCallAction){
				visitAcceptCallAction((AcceptCallAction) n);
			}
			if(n instanceof StructuredActivityNode){
				Classifier msg = getLibrary().getMessageStructure((StructuredActivityNode) n);
				visitFeaturesOf(msg);
				visitActivityNodesRecursively(msg, (StructuredActivityNode) n);
			}
		}
	}
	private void visitAcceptCallAction(AcceptCallAction node){
		if(EmfActionUtil.hasMessageStructure(node)){
			visitProperty(EmfActivityUtil.getContainingActivity(node), ojUtil.buildStructuralFeatureMap(node));
		}
	}
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
	public void visitAssociationClassProperty(Classifier c,AssociationClassEndMap map){
	}

	private void visitOperation(Operation o){
		if(EmfBehaviorUtil.hasExecutionInstance(o)){
			Classifier umlOwner = getLibrary().getMessageStructure(o);
			visitFeaturesOf(umlOwner);
		}
	}
	public void visitTask(Action node){
		Classifier msg = getLibrary().getMessageStructure((OpaqueAction) node);
		visitFeaturesOf(msg);
	}
	protected void visitCallAction(CallAction node){
		if(EmfBehaviorUtil.hasMessageStructure(node)){
			if(node instanceof CallBehaviorAction && ((CallBehaviorAction) node).getBehavior().getContext() == null){
				// Contextless behaviors need to be attached to the process in an emulated compositional association to ensure transitive
				// persistence
				Classifier umlOwner = getLibrary().getMessageStructure(node);
				visitFeaturesOf(umlOwner);
			}else{
				// Their classes will be built elsewhere, so just visit the action as an artificial association with the message structure
				Namespace container = EmfActivityUtil.getNearestNodeContainer(node);
				Classifier owner = getLibrary().getMessageStructure(container);
				visitProperty(owner, ojUtil.buildStructuralFeatureMap(node));
			}
		}
	}
	protected final boolean isMap(Property property){
		return property.getQualifiers().size() > 0 && (property.getName().equals("updateChangeLog") || property.getName().equals("user") || !EmfElementFinder.getRootObject(property).getName().equals("com"));
	}
}