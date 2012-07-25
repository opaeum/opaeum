package org.opaeum.javageneration.jbpm5.activity;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityFinalNode;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.opaeum.eclipse.EmfActivityUtil;

public class ActivityNodeSuccessionComparator implements Comparator<ActivityNode>{
	boolean byTarget = true;
	public ActivityNodeSuccessionComparator(){
	}
	public ActivityNodeSuccessionComparator(boolean byTarget){
		super();
		this.byTarget = byTarget;
	}
	public int compare(ActivityNode o1,ActivityNode o2){
		return compareImpl(o1, o2);
	}
	private int compareImpl(ActivityNode node1,ActivityNode node2){
		if(leadsTo(node1, node2, 20)){
			// check succession
			return -1;
		}else if(leadsTo(node2, node1, 20)){
			return 1;
		}else if(isReturn(node1) && !isReturn(node2)){
			// put result parameters last
			return 1;
		}else if(isReturn(node2) && !isReturn(node1)){
			return -1;
		}else if(isActivityFinal(node1) && !isActivityFinal(node2)){
			// put final nodes as close to last as possible
			return 1;
		}else if(isActivityFinal(node2) && !isActivityFinal(node1)){
			return -1;
		}else{
			// check which one has the longest path to the common successor.
			ActivityNode commonSuccessor = getCommonSuccessor(node1, node2, 20);
			if(commonSuccessor == null){
				return node1.getName().compareTo(node2.getName());
			}else{
				// The node with the most steps to the commonSuccessor should come first
				int numberOfSteps = numberOfEdgesTo(node1, commonSuccessor) - numberOfEdgesTo(node2, commonSuccessor);
				if(numberOfSteps == 0){
					return node1.getName().compareTo(node2.getName());
				}else{
					return numberOfSteps;
				}
			}
		}
	}
	private boolean isActivityFinal(ActivityNode node1){
		return node1 instanceof ActivityFinalNode;
	}
	private boolean isReturn(ActivityNode node1){
		return node1 instanceof ActivityParameterNode && ((ActivityParameterNode) node1).getParameter().getDirection()==ParameterDirectionKind.RETURN_LITERAL;
	}
	private static int numberOfEdgesTo(ActivityNode from,ActivityNode to){
		Set<ActivityEdge> oldEdges = EmfActivityUtil.getAllEffectiveOutgoing(from);
		for(int i = 0;i < 100;i++){
			Set<ActivityEdge> newEdges = new HashSet<ActivityEdge>();
			for(ActivityEdge edge:oldEdges){
				if(EmfActivityUtil.getEffectiveTarget(edge).equals(to)){
					return i;
				}else{
					newEdges.addAll(EmfActivityUtil.getAllEffectiveOutgoing(to));
				}
			}
			oldEdges = newEdges;
		}
		return 100;
	}
	/**
	 * Returns true if "from" leads to "to" in any possible way
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static boolean leadsTo(ActivityNode from,ActivityNode to,int levels){
		Iterator<ActivityEdge> outgoing = EmfActivityUtil.getAllEffectiveOutgoing(from).iterator();
		while(levels >= 0 && outgoing.hasNext()){
			ActivityEdge edge = (ActivityEdge) outgoing.next();
			ActivityNode target = EmfActivityUtil.getEffectiveTarget(edge);
			if(target.equals(to) || leadsTo(target, to, --levels)){
				return true;
			}
		}
		return false;
	}
	private static ActivityNode getCommonSuccessor(ActivityNode from,ActivityNode to,int levels){
		levels--;
		if(leadsTo(from, to, levels)){
			return to;
		}else if(leadsTo(to, from, levels)){
			return from;
		}else if(levels >= 0){
			for(ActivityEdge edge:EmfActivityUtil.getAllEffectiveOutgoing(from)){
				ActivityNode cs = getCommonSuccessor(EmfActivityUtil.getEffectiveTarget(edge), to, levels);
				if(cs != null){
					return cs;
				}
			}
		}
		return null;
	}
}
