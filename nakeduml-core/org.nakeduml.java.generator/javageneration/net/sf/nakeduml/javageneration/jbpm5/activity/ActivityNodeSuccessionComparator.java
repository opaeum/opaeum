package net.sf.nakeduml.javageneration.jbpm5.activity;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedControlNode;
import net.sf.nakeduml.metamodel.activities.INakedParameterNode;

public class ActivityNodeSuccessionComparator implements Comparator {
	boolean byTarget = true;

	public ActivityNodeSuccessionComparator() {
	}

	public ActivityNodeSuccessionComparator(boolean byTarget) {
		super();
		this.byTarget = byTarget;
	}

	public int compare(Object o1, Object o2) {
		if (o1 instanceof INakedActivityNode) {
			return compare((INakedActivityNode) o1, (INakedActivityNode) o2);
		} else if (byTarget) {
			return compare(((INakedActivityEdge) o1).getEffectiveTarget(), ((INakedActivityEdge) o2).getEffectiveTarget());
		} else {
			return compare(((INakedActivityEdge) o1).getEffectiveSource(), ((INakedActivityEdge) o2).getEffectiveSource());
		}
	}

	private int compare(INakedActivityNode node1, INakedActivityNode node2) {
		if (leadsTo(node1, node2, 20)) {
			// check succession
			return -1;
		} else if (leadsTo(node2, node1, 20)) {
			return 1;
		} else if (isReturn(node1) && !isReturn(node2)) {
			// put result parameters last
			return 1;
		} else if (isReturn(node2) && !isReturn(node1)) {
			return -1;
		} else if (isActivityFinal(node1) && !isActivityFinal(node2)) {
			// put final nodes as close to last as possible
			return 1;
		} else if (isActivityFinal(node2) && !isActivityFinal(node1)) {
			return -1;
		} else {
			// check which one has the longest path to the common successor.
			INakedActivityNode commonSuccessor = getCommonSuccessor(node1, node2, 20);
			if (commonSuccessor == null) {
				return node1.getName().compareTo(node2.getName());
			} else {
				// The node with the most steps to the commonSuccessor should come first
				int numberOfSteps = numberOfEdgesTo(node1, commonSuccessor) - numberOfEdgesTo(node2, commonSuccessor);
				if (numberOfSteps == 0) {
					return node1.getName().compareTo(node2.getName());
				} else {
					return numberOfSteps;
				}
			}
		}
	}

	private boolean isActivityFinal(INakedActivityNode node1) {
		return node1 instanceof INakedControlNode && ((INakedControlNode) node1).getControlNodeType().isActivityFinalNode();
	}

	private boolean isReturn(INakedActivityNode node1) {
		return node1 instanceof INakedParameterNode && ((INakedParameterNode) node1).getParameter().isReturn();
	}

	private static int numberOfEdgesTo(INakedActivityNode from, INakedActivityNode to) {
		Set<INakedActivityEdge> oldEdges = from.getAllEffectiveOutgoing();
		for (int i = 0; i < 100; i++) {
			Set<INakedActivityEdge> newEdges = new HashSet<INakedActivityEdge>();
			for (INakedActivityEdge edge : oldEdges) {
				if (edge.getEffectiveTarget().equals(to)) {
					return i;
				} else {
					newEdges.addAll(to.getAllEffectiveOutgoing());
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
	public static boolean leadsTo(INakedActivityNode from, INakedActivityNode to, int levels) {
		Iterator outgoing = from.getAllEffectiveOutgoing().iterator();
		while (levels >= 0 && outgoing.hasNext()) {
			INakedActivityEdge edge = (INakedActivityEdge) outgoing.next();
			INakedActivityNode target = edge.getEffectiveTarget();
			if (target.equals(to) || leadsTo(target, to, --levels)) {
				return true;
			}
		}
		return false;
	}

	private static INakedActivityNode getCommonSuccessor(INakedActivityNode from, INakedActivityNode to, int levels) {
		levels--;
		if (leadsTo(from, to, levels)) {
			return to;
		} else if (leadsTo(to, from, levels)) {
			return from;
		} else if (levels >= 0) {
			for (INakedActivityEdge edge : from.getAllEffectiveOutgoing()) {
				INakedActivityNode cs = getCommonSuccessor(edge.getEffectiveTarget(), to, levels);
				if (cs != null) {
					return cs;
				}
			}
		}
		return null;
	}
}
