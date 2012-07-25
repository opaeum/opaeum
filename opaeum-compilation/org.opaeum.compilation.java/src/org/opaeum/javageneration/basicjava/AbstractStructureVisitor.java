package org.opaeum.javageneration.basicjava;

import java.util.Collection;
import java.util.Set;

import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.DurationObservation;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.ExpansionRegion;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.TimeObservation;
import org.eclipse.uml2.uml.Variable;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfElementUtil;
import org.opaeum.eclipse.EmfTimeUtil;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.javageneration.maps.AssociationClassEndMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;

public abstract class AbstractStructureVisitor extends StereotypeAnnotator{
	public AbstractStructureVisitor(){
		super();
	}
	protected boolean ignoreDeletedElements(){
		return true;
	}
	protected abstract void visitProperty(Classifier owner,NakedStructuralFeatureMap buildStructuralFeatureMap);
	protected abstract void visitComplexStructure(Classifier umlOwner);
	@VisitBefore(matchSubclasses = true,match = {
			Class.class,DataType.class,Association.class,Signal.class,Component.class,Enumeration.class,
			Behavior.class,Actor.class,Collaboration.class
	})
	public void visitFeaturesOf(Classifier c){
		if(OJUtil.hasOJClass(c) || (EmfElementUtil.isMarkedForDeletion(c) && !ignoreDeletedElements())){
			if(c instanceof ComplexStructure){
				visitComplexStructure((Classifier) c);
				if(c instanceof Behavior){
					Behavior umlOwner = (Behavior) c;
					if(EmfBehaviorUtil.hasExecutionInstance(umlOwner)){
						if(umlOwner.getSpecification() == null){
							for(Parameter parm:umlOwner.getOwnedParameters()){
								visitProperty(umlOwner, OJUtil.buildStructuralFeatureMap(umlOwner, parm));
							}
						}
					}
					if(umlOwner instanceof StateMachine){
						visitObservations(umlOwner, (Namespace) umlOwner);
					}
					if(umlOwner instanceof Activity){
						
						Activity a = (Activity) umlOwner;
						
						visitObservations(umlOwner, a);
						visitVariables(umlOwner, a.getVariables());
						if(EmfBehaviorUtil.hasExecutionInstance(a)){
							visitActivityNodesRecursively(a,  a);
						}
					}
				}
			}
			Set<Property> directlyImplementedAttributes = c.getDirectlyImplementedAttributes();
			for(Property p:directlyImplementedAttributes){
				if(p.isNavigable()){
					if(OJUtil.hasOJClass((Classifier) p.getAssociation())){
						visitAssociationClassProperty(c, new AssociationClassEndMap(p));
					}else{
						NakedStructuralFeatureMap buildStructuralFeatureMap = OJUtil.buildStructuralFeatureMap(p);
						visitProperty(c, buildStructuralFeatureMap);
					}
				}
			}
			
			for(Operation o:c.getDirectlyImplementedOperations()){
				visitOperation(o);
			}
		}
		 
	}
	protected void visitObservations(Classifier umlOwner,Namespace a){
		Collection<TimeObservation> timeObservations = EmfTimeUtil.getTimeObservations( a);
		Collection<DurationObservation> durationObservations = EmfTimeUtil.getDurationObservations(a);
		for(TimeObservation o:timeObservations){
			visitProperty(umlOwner, OJUtil.buildStructuralFeatureMap(umlOwner, o));
		}
		for(DurationObservation o:durationObservations){
			visitProperty(umlOwner, OJUtil.buildStructuralFeatureMap(umlOwner, o));
		}
	}
	protected void visitActivityNodesRecursively(Classifier owner,Namespace container){
		for(ActivityNode n:EmfActivityUtil.getActivityNodes(container)){
			if(n instanceof Action){
				visitOutputPins((Action) n);
			}
			if(n instanceof ExpansionRegion){
				visitExpansionNodes((ExpansionRegion) n);
			}
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
				visitObservations(msg, (StructuredActivityNode) n);
			}
		}
	}
	private void visitAcceptCallAction(AcceptCallAction node){
		if(EmfActionUtil.hasMessageStructure(node)){
			visitProperty(EmfActivityUtil. getContainingActivity(node), OJUtil.buildStructuralFeatureMap(node, getLibrary()));
		}
	}
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
	public void visitAssociationClassProperty(Classifier c,AssociationClassEndMap map){
	}
	private void visitVariables(Classifier owner,Collection<Variable> vars){
		for(Variable var:vars){
			visitProperty(owner, OJUtil.buildStructuralFeatureMap(owner, var));
		}
	}
	private void visitOutputPins(Action a){
		Collection<OutputPin> nodes = a.getOutputs();
		Namespace container = EmfActivityUtil.getNearestNodeContainer(a);
		Classifier msg = getLibrary().getMessageStructure(container);
		for(OutputPin node:nodes){
			visitProperty(msg,
					OJUtil.buildStructuralFeatureMap(msg, node, true));
		}
	}
	private void visitExpansionNodes(ExpansionRegion region){
		Namespace container = EmfActivityUtil.getNearestNodeContainer(region);
		Classifier msg = getLibrary().getMessageStructure(container);
		for(ExpansionNode node:region.getOutputElements()){
			// NB output expansion nodes sit on the parent container
			visitProperty(msg,
					OJUtil.buildStructuralFeatureMap(msg, node, true));
		}
		for(ExpansionNode node:region.getInputElements()){
			// NB input expansion nodes sit on the expansion region class
			visitProperty(getLibrary().getMessageStructure( region), OJUtil.buildStructuralFeatureMap(getLibrary().getMessageStructure( region), node, false));
		}
	}
	private void visitOperation(Operation o){
		if(EmfBehaviorUtil.hasExecutionInstance(o)){
			Classifier umlOwner =  getLibrary().getMessageStructure( o);
			visitFeaturesOf(umlOwner);
		}
	}
	public void visitTask(Action node){
		Classifier msg = getLibrary().getMessageStructure( node);
		Namespace container = EmfActivityUtil.getNearestNodeContainer(node);
		Classifier owner = getLibrary().getMessageStructure(container);
		visitProperty(owner, OJUtil.buildStructuralFeatureMap(node, getLibrary()));
		visitFeaturesOf(msg);
	}
	protected void visitCallAction(CallAction node){
		if(EmfBehaviorUtil.hasMessageStructure(node)){
			if(node.getCalledElement().getContext() == null){
				// Contextless behaviors need to be attached to the process in an emulated compositional association to ensure transitive
				// persistence
				Classifier umlOwner =  getLibrary().getMessageStructure( node);
				visitFeaturesOf(umlOwner);
			}else{
				// Their classes will be built elsewhere, so just visit the action as an artificial association with the message structure
				Namespace container = EmfActivityUtil.getNearestNodeContainer(node);
				Classifier owner = getLibrary().getMessageStructure(container);
				visitProperty(owner, OJUtil.buildStructuralFeatureMap(node, getLibrary()));
			}
		}
	}
	protected final boolean isMap(Property property){
		return property.getQualifiers().size() > 0 && (property.getName().equals("updateChangeLog") || !config.shouldBeCm1Compatible());
	}
}