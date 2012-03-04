package org.opaeum.metamodel.activities.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.opaeum.metamodel.activities.ControlNodeType;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedControlNode;
import org.opaeum.metamodel.activities.INakedDecisionNode;
import org.opaeum.metamodel.activities.INakedExpansionNode;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedObjectFlow;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.core.INakedClassifier;

public class NakedObjectFlowImpl extends NakedActivityEdgeImpl implements INakedObjectFlow {
	private static final long serialVersionUID = 6481759202136150887L;
	private INakedBehavior transformation;
	private INakedBehavior selection;

	public NakedObjectFlowImpl() {
		super();
	}

	@Override
	public INakedActivityNode getEffectiveTarget() {
		if (getTarget() instanceof INakedExpansionNode) {
			INakedExpansionNode target = (INakedExpansionNode) getTarget();
			if (target.isInputElement()) {
				return target.getExpansionRegion();
			} else {
				return target;
			}
		}
		if (getTarget() instanceof INakedInputPin) {
			return (INakedActivityNode) getTarget().getOwnerElement();
		} else {
			return getTarget();
		}
	}

	public INakedBehavior getTransformation() {
		return this.transformation;
	}

	public void setTransformation(INakedBehavior transformation) {
		this.transformation = transformation;
	}

	public INakedBehavior getSelection() {
		return selection;
	}

	public void setSelection(INakedBehavior selection) {
		this.selection = selection;
	}

	@Override
	public INakedActivityNode getEffectiveSource() {
		if (getSource() instanceof INakedExpansionNode) {
			INakedExpansionNode source = (INakedExpansionNode) getSource();
			if (source.isOutputElement()) {
				return source.getExpansionRegion();
			} else {
				return source;
			}
		} else if (getSource() instanceof INakedOutputPin) {
			return (INakedActivityNode) getSource().getOwnerElement();
		} else {
			return getSource();
		}
	}

	@Override
	public INakedObjectNode getOriginatingObjectNode() {
		if (getSource() instanceof INakedObjectNode) {
			return (INakedObjectNode) getSource();
		} else if (getSource() instanceof INakedControlNode) {
			INakedControlNode c = (INakedControlNode) getSource();
			Set<INakedActivityEdge> allEffectiveIncoming = getSource().getAllEffectiveIncoming();
			if ((c.getControlNodeType() == ControlNodeType.JOIN_NODE || c.getControlNodeType() == ControlNodeType.MERGE_NODE) && multipleObjectFlows(allEffectiveIncoming)) {
				// Eliminate guess work. Under these conditions it would be
				// misleading to return anything
				return null;
			}
			for (INakedActivityEdge edge : allEffectiveIncoming) {
				if (edge instanceof INakedObjectFlow) {
					return ((INakedObjectFlow) edge).getOriginatingObjectNode();
				}
			}
		}
		return null;
	}

	@Override
	public INakedObjectNode getFedObjectNode() {
		if (getTarget() instanceof INakedObjectNode) {
			return (INakedObjectNode) getTarget();
		} else if (getTarget() instanceof INakedControlNode) {
			INakedControlNode c = (INakedControlNode) getTarget();
			Set<INakedActivityEdge> allEffectiveOutgoing = getTarget().getAllEffectiveOutgoing();
			if ((c.getControlNodeType() == ControlNodeType.FORK_NODE || c.getControlNodeType() == ControlNodeType.DECISION_NODE) && multipleObjectFlows(allEffectiveOutgoing)) {
				// Eliminate guess work. Under these conditions it would be
				// misleading to return anything
				return null;
			}
			for (INakedActivityEdge edge : allEffectiveOutgoing) {
				if (edge instanceof INakedObjectFlow) {
					return ((INakedObjectFlow) edge).getFedObjectNode();
				}
			}
		}
		return null;
	}

	private boolean multipleObjectFlows(Set<INakedActivityEdge> allEffectiveIncoming) {
		int count = 0;
		for (INakedActivityEdge edge : allEffectiveIncoming) {
			if (edge instanceof INakedObjectFlow) {
				count++;
			}
		}
		return count > 1;
	}

	@Override
	public INakedClassifier getOriginatingObjectNodeClassifier() {
		ArrayList<INakedClassifier> classifierList = new ArrayList<INakedClassifier>();
		Exitter exitter = new Exitter(false);
		getOriginatingObjectNodeClassifierInternal(classifierList, exitter);
		if (exitter.shouldExit) {
			return null;
		} else {
			return classifierList.get(0);
		}
	}

	void getOriginatingObjectNodeClassifierInternal(List<INakedClassifier> classifier, Exitter exitter) {
		if (!exitter.shouldExit) {
			INakedActivityNode source = getSource();
			if (source instanceof INakedObjectNode && classifier.isEmpty()) {
				classifier.add(((INakedObjectNode) source).getNakedBaseType());
			} else if (source instanceof INakedObjectNode && !classifier.isEmpty()) {
				if (!classifier.get(0).equals(((INakedObjectNode) source).getNakedBaseType())) {
					exitter.shouldExit = true;
				}
			} else if (source instanceof INakedControlNode) {
				INakedControlNode targetControlNode = (INakedControlNode) source;
				if (targetControlNode.getControlNodeType().isMergeNode() || targetControlNode.getControlNodeType().isJoinNode()) {

					for (INakedActivityEdge incoming : targetControlNode.getIncoming()) {
						if (incoming instanceof INakedObjectFlow) {
							((NakedObjectFlowImpl)incoming).getOriginatingObjectNodeClassifierInternal(classifier, exitter);
						} else {
							exitter.shouldExit = true;
						}
					}

				} else  if (targetControlNode.getControlNodeType().isDecisionNode()) {
					((NakedObjectFlowImpl)targetControlNode.getIncoming().iterator().next()).getOriginatingObjectNodeClassifierInternal(classifier, exitter);
				} else {
					throw new IllegalStateException("wtf");
				}
			}
		}
	}

	class Exitter {
		boolean shouldExit;

		public Exitter(boolean shouldExit) {
			super();
			this.shouldExit = shouldExit;
		}

	}

}
