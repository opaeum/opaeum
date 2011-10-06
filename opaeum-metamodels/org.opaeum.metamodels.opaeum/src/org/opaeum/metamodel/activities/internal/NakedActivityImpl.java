package org.opaeum.metamodel.activities.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.klasse.octopus.model.IAttribute;

import org.opaeum.metamodel.actions.INakedAcceptEventAction;
import org.opaeum.metamodel.activities.ActivityKind;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedActivityPartition;
import org.opaeum.metamodel.activities.INakedActivityVariable;
import org.opaeum.metamodel.activities.INakedParameterNode;
import org.opaeum.metamodel.bpm.INakedEmbeddedTask;
import org.opaeum.metamodel.commonbehaviors.INakedEvent;
import org.opaeum.metamodel.commonbehaviors.INakedMessageEvent;
import org.opaeum.metamodel.commonbehaviors.INakedTrigger;
import org.opaeum.metamodel.commonbehaviors.internal.NakedBehaviorImpl;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.ArtificialProperty;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;

public class NakedActivityImpl extends NakedBehaviorImpl implements INakedActivity{
	private static final long serialVersionUID = -8111895180462880035L;
	public static final String META_CLASS = "activity";
	private ActivityKind activityKind;
	private Set<INakedActivityEdge> activityEdges = new HashSet<INakedActivityEdge>();
	private Set<INakedActivityNode> activityNodes = new HashSet<INakedActivityNode>();
	private Set<INakedActivityPartition> partitions = new HashSet<INakedActivityPartition>();
	private Set<INakedActivityVariable> variables = new HashSet<INakedActivityVariable>();
	public Set<INakedActivityPartition> getPartitions(){
		return this.partitions;
	}
	public Collection<INakedActivityNode> getStartNodes(){
		Collection<INakedActivityNode> results = new ArrayList<INakedActivityNode>();
		for(INakedActivityNode node:getActivityNodes()){
			if(node instanceof INakedParameterNode){
				INakedParameterNode parmNode = (INakedParameterNode) node;
				// Ignore parameter nodes that have no outgoing edges, e.g.
				// out-parameters
				if(parmNode.getParameter().isArgument() && parmNode.getAllEffectiveIncoming().isEmpty()){
					results.add(node);
				}
			}else if(node.getAllEffectiveIncoming().isEmpty()){
				if(!(node instanceof INakedAction && ((INakedAction) node).handlesException()) || node instanceof INakedAcceptEventAction){
					results.add(node);
				}
			}
		}
		return results;
	}
	@Override
	public String getMetaClass(){
		return META_CLASS;
	}
	public boolean isProcess(){
		return getActivityKind() == ActivityKind.PROCESS;
	}
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
		if(element instanceof INakedActivityPartition){
			this.partitions.add((INakedActivityPartition) element);
		}
		if(element instanceof INakedActivityNode){
			this.activityNodes.add((INakedActivityNode) element);
		}
		if(element instanceof INakedActivityEdge){
			this.activityEdges.add((INakedActivityEdge) element);
		}
		if(element instanceof INakedActivityVariable){
			this.variables.add((INakedActivityVariable) element);
		}
	}
	public void removeOwnedElement(INakedElement element,boolean recursively){
		super.removeOwnedElement(element, recursively);
		if(element instanceof INakedActivityPartition){
			this.partitions.remove((INakedActivityPartition) element);
		}
		if(element instanceof INakedActivityNode){
			this.activityNodes.remove((INakedActivityNode) element);
		}
		if(element instanceof INakedActivityEdge){
			INakedActivityEdge e = (INakedActivityEdge) element;
			if(recursively){
				e.setSource(null);
				e.setTarget(null);
			}
			this.activityEdges.remove((INakedActivityEdge) element);
		}
		if(element instanceof INakedActivityVariable){
			this.variables.remove((INakedActivityVariable) element);
		}
	}
	@Override
	protected List<IAttribute> getAllAttributesForOcl(boolean classScope){
		List<IAttribute> results = super.getAllAttributesForOcl(classScope);
		if(!classScope){
			for(INakedParameter p:getArgumentParameters()){
				results.add(new TypedElementPropertyBridge(this, p));
			}
		}
		return results;
	}
	public List<INakedActivityNode> getActivityNodesRecursively(){
		List<INakedElement> children = new ArrayList<INakedElement>(this.activityNodes);
		List<INakedActivityNode> result = new ArrayList<INakedActivityNode>();
		addNodesRecursively(children, result);
		return result;
	}
	private static void addNodesRecursively(Collection<? extends INakedElement> children,List<? super INakedActivityNode> result){
		for(INakedElement node:children){
			if(node instanceof INakedActivityNode){
				result.add((INakedActivityNode) node);
			}
			if(node instanceof INakedElementOwner){
				addNodesRecursively(((INakedElementOwner) node).getOwnedElements(), result);
			}
		}
	}
	public Set<INakedActivityEdge> getActivityEdges(){
		return this.activityEdges;
	}
	public Set<INakedActivityNode> getActivityNodes(){
		return this.activityNodes;
	}
	public Set<INakedMessageEvent> getAllMessageEvents(){
		boolean messageEvents = true;
		Set<INakedMessageEvent> results = getEvents(messageEvents);
		return results;
	}
	@SuppressWarnings({
			"unchecked","rawtypes"
	})
	protected <T>Set<T> getEvents(boolean messageEvents){
		Set<T> results = new HashSet<T>();
		for(INakedActivityNode node:getActivityNodesRecursively()){
			if(node instanceof INakedAcceptEventAction){
				INakedAcceptEventAction acceptEventAction = (INakedAcceptEventAction) node;
				for(INakedTrigger t:acceptEventAction.getTriggers()){
					if(messageEvents ? t.getEvent() instanceof INakedMessageEvent : true){
						results.add((T) t.getEvent());
					}
				}
			}
			if(node instanceof INakedEmbeddedTask && messageEvents==false){
				results.addAll((Collection) ((INakedEmbeddedTask) node).getTaskDefinition().getDeadlines());
			}
		}
		return results;
	}
	public Collection<INakedActivityVariable> getVariables(){
		return this.variables;
	}
	public ActivityKind getActivityKind(){
		return this.activityKind;
	}
	public void setActivityKind(ActivityKind activityKind){
		this.activityKind = activityKind;
	}
	@Override
	public INakedProperty findEmulatedAttribute(INakedAction node){
		for(INakedProperty p:this.ownedAttributes){
			if(p instanceof ArtificialProperty && p.getName().equalsIgnoreCase(node.getName())){
				return p;
			}
		}
		return null;
	}
	@Override
	public Set<INakedEvent> getAllEvents(){
		return getEvents(false);
	}
}
