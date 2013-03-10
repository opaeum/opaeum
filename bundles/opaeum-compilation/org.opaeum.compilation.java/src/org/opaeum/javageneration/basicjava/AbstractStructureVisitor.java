package org.opaeum.javageneration.basicjava;

import java.util.Set;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfElementUtil;
import org.opaeum.eclipse.EmfOperationUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.emulated.AbstractEmulatedProperty;
import org.opaeum.eclipse.emulated.EmulatedPropertyHolderForAssociation;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.javageneration.maps.AssociationClassEndMap;

public abstract class AbstractStructureVisitor extends StereotypeAnnotator{
	public AbstractStructureVisitor(){
		super();
	}
	protected boolean ignoreDeletedElements(){
		return true;
	}
	protected abstract void visitProperty(OJAnnotatedClass cls,Classifier owner,PropertyMap map);
	protected void visitInterfaceProperty(OJAnnotatedClass oj,Interface owner,PropertyMap map){
	}
	protected abstract boolean visitComplexStructure(OJAnnotatedClass ojClass,Classifier umlOwner);
	@VisitBefore(matchSubclasses = false)
	public void visitInterface(Interface i){
		if(shouldVisit(i)){
			OJAnnotatedInterface oi = (OJAnnotatedInterface) resolveMatchingOJClass(i);
			for(Property p:i.getOwnedAttributes()){
				visitInterfaceProperty(oi, i, ojUtil.buildStructuralFeatureMap(p));
			}
			for(AbstractEmulatedProperty p:getLibrary().getEmulatedPropertyHolder(i).getEmulatedAttributes()){
				if(p.shouldEmulate()){
					visitInterfaceProperty(oi, i, ojUtil.buildStructuralFeatureMap(p));
				}
			}
			for(Association a:i.getAssociations()){
				for(Property p:a.getMemberEnds()){
					if(p.getOtherEnd().getType() == i){
						if(EmfAssociationUtil.isClass(a)){
							EmulatedPropertyHolderForAssociation epha = (EmulatedPropertyHolderForAssociation) getLibrary().getEmulatedPropertyHolder(a);
							visitInterfaceProperty(oi, i, ojUtil.buildStructuralFeatureMap(epha.getEndToAssociation(p)));
							visitAssociationClassProperty(i, new AssociationClassEndMap(ojUtil, p));

						}
						visitInterfaceProperty(oi, i, ojUtil.buildStructuralFeatureMap(p));
					}
				}
			}
		}
	}
	private boolean shouldVisit(Interface i){
		if(EmfClassifierUtil.isHelper(i)){
			return false;
		}else{
			return ojUtil.hasOJClass(i) || (EmfElementUtil.isMarkedForDeletion(i) && !ignoreDeletedElements()) || forceVisit(i);
		}
	}
	@VisitBefore(matchSubclasses = false,match = {Class.class,AssociationClass.class,DataType.class,Association.class,Signal.class,
			Component.class,OpaqueBehavior.class,Activity.class,StateMachine.class,Actor.class,Collaboration.class,Enumeration.class})
	public boolean visitFeaturesOf(Classifier c){
		if(ojUtil.hasOJClass(c) || (EmfElementUtil.isMarkedForDeletion(c) && !ignoreDeletedElements()) || forceVisit(c)){
			if(EmfClassifierUtil.isComplexStructure(c)){
				OJAnnotatedClass ojOwner = resolveMatchingOJClass(c);
				boolean ownerVisited = visitComplexStructure(ojOwner, (Classifier) c);
				if(c instanceof Behavior){
					Behavior umlOwner = (Behavior) c;
					if(umlOwner instanceof Activity){
						Activity a = (Activity) umlOwner;
						if(EmfBehaviorUtil.hasExecutionInstance(a)){
							visitActivityNodesRecursively(ojOwner, a, a, ownerVisited);
						}
					}
				}
				if(ownerVisited){
					if(ojOwner == null){
						// probably created after the initial lookup
						ojOwner = resolveMatchingOJClass(c);
					}
					Set<Property> directlyImplementedAttributes = getLibrary().getDirectlyImplementedAttributes(c);
					for(Property p:directlyImplementedAttributes){
						if(p.isNavigable()){
							if(ojUtil.hasOJClass((Classifier) p.getAssociation())){
								visitAssociationClassProperty(c, new AssociationClassEndMap(ojUtil, p));
							}else{
								PropertyMap buildStructuralFeatureMap = ojUtil.buildStructuralFeatureMap(p);
								visitProperty(ojOwner, c, buildStructuralFeatureMap);
							}
						}
					}
				}
				for(Operation o:EmfOperationUtil.getDirectlyImplementedOperations(c)){
					visitOperation(o, ownerVisited);
				}
				return ownerVisited;
			}
		}
		return false;
	}
	protected boolean forceVisit(Classifier c){
		return false;
	}
	protected OJAnnotatedClass resolveMatchingOJClass(Classifier c){
		return findJavaClass(c);
	}
	protected void visitActivityNodesRecursively(OJAnnotatedClass ojOwner,Classifier owner,Namespace container,boolean ownerVisited){
		for(ActivityNode n:EmfActivityUtil.getActivityNodes(container)){
			if(EmfActionUtil.isEmbeddedTask(n)){
				visitTask((Action) n);
			}else if(n instanceof CallAction){
				visitCallAction(ojOwner, (CallAction) n, ownerVisited);
			}else if(n instanceof AcceptCallAction && ownerVisited){
				visitAcceptCallAction(ojOwner, (AcceptCallAction) n);
			}
			if(n instanceof StructuredActivityNode){
				Classifier msg = getLibrary().getMessageStructure((StructuredActivityNode) n);
				boolean messageVisited = visitFeaturesOf(msg);
				visitActivityNodesRecursively(resolveMatchingOJClass(msg), msg, (StructuredActivityNode) n, messageVisited);
			}
		}
	}
	private void visitAcceptCallAction(OJAnnotatedClass ojOwner,AcceptCallAction node){
		if(EmfActionUtil.hasMessageStructure(node)){
			visitProperty(ojOwner, EmfActivityUtil.getContainingActivity(node), ojUtil.buildStructuralFeatureMap(node));
		}
	}
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
	public void visitAssociationClassProperty(Classifier c,AssociationClassEndMap map){
	}
	private void visitOperation(Operation o,boolean ownerVisited){
		if(EmfBehaviorUtil.hasExecutionInstance(o)){
			Classifier umlOwner = getLibrary().getMessageStructure(o);
			visitFeaturesOf(umlOwner);
		}
	}
	public void visitTask(Action node){
		Classifier msg = getLibrary().getMessageStructure((OpaqueAction) node);
		visitFeaturesOf(msg);
	}
	protected void visitCallAction(OJAnnotatedClass ojOwner,CallAction node,boolean ownerVisited){
		if(EmfBehaviorUtil.hasMessageStructure(node)){
			if(node instanceof CallBehaviorAction && ((CallBehaviorAction) node).getBehavior().getContext() == null){
				// Contextless behaviors need to be attached to the process in an emulated compositional association to ensure transitive
				// persistence
				Classifier umlOwner = getLibrary().getMessageStructure(node);
				visitFeaturesOf(umlOwner);
			}else if(ownerVisited){
				// Their classes will be built elsewhere, so just visit the action as an artificial association with the message structure
				Namespace container = EmfActivityUtil.getNearestNodeContainer(node);
				Classifier owner = getLibrary().getMessageStructure(container);
				visitProperty(ojOwner, owner, ojUtil.buildStructuralFeatureMap(node));
			}
		}
	}
	/**
	 * An interim solution to allow the qualified associations in CM to remain collections
	 * @param property
	 * @return
	 */
	protected final boolean isMap(Property property){
		
		return property.getQualifiers().size() > 0
				&& (property.getName().equals("updateChangeLog") || property.getName().equals("user") || property.getName().equals("userGroup") || !EmfElementFinder.getRootObject(property)
						.getName().equals("com")) && config.implementMaps();
	}
	protected final boolean isOtherEndModifiable(PropertyMap map){
		return isOtherEndModifiable(map.getProperty());
	}
	protected final boolean isModifiable(PropertyMap map){
		return isModifiable(map.getProperty());
	}
	protected final boolean isOtherEndModifiable(Property property){
		Property otherEnd = property.getOtherEnd();
		return isModifiable(otherEnd);
	}
	protected final boolean isModifiable(Property otherEnd){
		return !(otherEnd == null || EmfPropertyUtil.isDerived(otherEnd)) && otherEnd.isNavigable() && !otherEnd.isReadOnly();
	}
	protected final boolean isInvolvedInAnAssociationClass(PropertyMap map){
		boolean isInvolvedInAnAssociationClass = EmfAssociationUtil.isClass(map.getProperty().getAssociation()) || map.getBaseType() instanceof Association
				|| (map.getProperty().getOtherEnd() != null && map.getProperty().getOtherEnd().getType() instanceof Association);
		return isInvolvedInAnAssociationClass;
	}

}