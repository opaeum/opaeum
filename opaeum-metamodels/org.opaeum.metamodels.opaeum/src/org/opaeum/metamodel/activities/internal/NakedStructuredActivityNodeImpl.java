package org.opaeum.metamodel.activities.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedActivityVariable;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;
import org.opaeum.metamodel.commonbehaviors.INakedDurationObservation;
import org.opaeum.metamodel.commonbehaviors.INakedTimeObservation;
import org.opaeum.metamodel.core.DefaultOpaeumComparator;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedMessageStructure;

public class NakedStructuredActivityNodeImpl extends NakedActionImpl implements INakedStructuredActivityNode{
	private static final long serialVersionUID = 5226193508709902688L;
	SortedSet<INakedActivityNode> children = new TreeSet<INakedActivityNode>(new DefaultOpaeumComparator());
	SortedSet<INakedActivityEdge> activityEdges = new TreeSet<INakedActivityEdge>(new DefaultOpaeumComparator());
	SortedSet<INakedActivityVariable> variables = new TreeSet<INakedActivityVariable>(new DefaultOpaeumComparator());
	private List<INakedOutputPin> output = new ArrayList<INakedOutputPin>();
	private List<INakedInputPin> input = new ArrayList<INakedInputPin>();
	StructuredActivityNodeClassifier messageStructure;
	Collection<INakedTimeObservation> timeObservations = new HashSet<INakedTimeObservation>();
	Collection<INakedDurationObservation> durationObservations = new HashSet<INakedDurationObservation>();
	public Collection<INakedTimeObservation> getTimeObservations(){
		return timeObservations;
	}
	public Collection<INakedDurationObservation> getDurationObservations(){
		return durationObservations;
	}
	public Collection<INakedActivityEdge> getActivityEdges(){
		return activityEdges;
	}
	public Collection<INakedActivityVariable> getVariables(){
		return variables;
	}
	public Collection<INakedActivityNode> getActivityNodes(){
		return children;
	}
	public Collection<INakedElement> removeOwnedElement(INakedElement element,boolean recursively){
		Collection<INakedElement> result = super.removeOwnedElement(element, recursively);
		if(element instanceof INakedActivityNode){
			children.remove((INakedActivityNode) element);
		}
		if(element instanceof INakedActivityVariable){
			variables.remove((INakedActivityVariable) element);
		}
		if(element instanceof INakedActivityEdge){
			INakedActivityEdge e = (INakedActivityEdge) element;
			if(recursively){
				e.getSource().getOutgoing().remove(e);
				e.getTarget().getIncoming().remove(e);
				result.add(e.getSource());
				result.add(e.getTarget());
				e.setSource(null);
				e.setTarget(null);
			}
			this.activityEdges.remove((INakedActivityEdge) element);
		}
		if(element instanceof INakedDurationObservation){
			this.durationObservations.remove(element);
		}
		if(element instanceof INakedTimeObservation){
			this.timeObservations.remove(element);
		}
		if(element instanceof INakedInputPin){
			input.remove((INakedInputPin) element);
		}
		if(element instanceof INakedOutputPin){
			output.remove((INakedOutputPin) element);
		}
		return result;
	}
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
		if(element instanceof INakedActivityNode){
			children.add((INakedActivityNode) element);
		}
		if(element instanceof INakedActivityVariable){
			variables.add((INakedActivityVariable) element);
		}
		if(element instanceof INakedActivityEdge){
			activityEdges.add((INakedActivityEdge) element);
		}
		if(element instanceof INakedInputPin){
			input.add((INakedInputPin) element);
		}
		if(element instanceof INakedOutputPin){
			output.add((INakedOutputPin) element);
		}
		if(element instanceof INakedTimeObservation){
			this.timeObservations.add((INakedTimeObservation) element);
		}
		if(element instanceof INakedDurationObservation){
			this.durationObservations.add((INakedDurationObservation) element);
		}
	}
	@Override
	public Collection<INakedActivityNode> getStartNodes(){
		Collection<INakedActivityNode> results = new ArrayList<INakedActivityNode>();
		for(INakedActivityNode node:getActivityNodes()){
			if(!(node instanceof INakedPin) && node.getAllEffectiveIncoming().isEmpty()){
				if(!(node instanceof INakedAction && ((INakedAction) node).handlesException())){
					results.add(node);
				}
			}
		}
		return results;
	}
	@Override
	public Collection<INakedInputPin> getInput(){
		return this.input;
	}
	@Override
	public Collection<INakedOutputPin> getOutput(){
		return this.output;
	}
	public void setOutput(List<INakedOutputPin> output){
		this.output = output;
	}
	public void setInput(List<INakedInputPin> input){
		this.input = input;
	}
	@Override
	public INakedMessageStructure getMessageStructure(){
		return messageStructure;
	}
	@Override
	public void initMessageStructure(){
		this.messageStructure = new StructuredActivityNodeClassifier(this);
	}
	@Override
	public Collection<INakedDurationObservation> findDurationObservationFrom(INakedElement e){
		Collection<INakedDurationObservation> result = new HashSet<INakedDurationObservation>();
		for(INakedDurationObservation d:this.durationObservations){
			if(d.getFromObservedElement() == e){
				result.add(d);
			}
		}
		return result;
	}
	@Override
	public Collection<INakedDurationObservation> findDurationObservationTo(INakedElement e){
		Collection<INakedDurationObservation> result = new HashSet<INakedDurationObservation>();
		for(INakedDurationObservation d:this.durationObservations){
			if(d.getToObservedElement() == e){
				result.add(d);
			}
		}
		return result;
	}
	@Override
	public Collection<INakedTimeObservation> findTimeObservation(INakedElement e){
		Collection<INakedTimeObservation> result = new HashSet<INakedTimeObservation>();
		for(INakedTimeObservation d:this.timeObservations){
			if(d.getObservedElement() == e){
				result.add(d);
			}
		}
		return result;
	}
}
