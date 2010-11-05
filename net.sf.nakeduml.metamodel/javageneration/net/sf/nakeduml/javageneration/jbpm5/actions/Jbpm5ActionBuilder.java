package net.sf.nakeduml.javageneration.jbpm5.actions;

import java.util.Collection;

import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractActionBuilder;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ObjectNodeExpressor;
import net.sf.nakeduml.javageneration.oclexpressions.ConstraintGenerator;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJClassifier;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedObjectFlow;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.PreAndPostConstrained;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.octopus.oclengine.IOclEngine;
import nl.klasse.octopus.stdlib.IOclLibrary;

public abstract class Jbpm5ActionBuilder<A extends INakedActivityNode> extends AbstractActionBuilder {
	protected static final class Jbpm5ObjectNodeExpressor extends ObjectNodeExpressor {
		private final IOclEngine oclEngine;

		protected Jbpm5ObjectNodeExpressor(IOclEngine oclEngine) {
			this.oclEngine = oclEngine;
		}

		public String expressInputPinOrOutParam(OJBlock block, INakedObjectNode pin) {
			// Either an outputpin or parameterNode
			INakedObjectFlow edge = (INakedObjectFlow) pin.getIncoming().iterator().next();
			INakedObjectNode feedingNode = pin.getFeedingNode();
			NakedStructuralFeatureMap feedingMap = OJUtil.buildStructuralFeatureMap(feedingNode.getActivity(), feedingNode);
			String call = feedingMap.getter() + "()";
			if (feedingNode instanceof INakedOutputPin) {
				call = retrieveFromExecutionInstanceIfNecessary(feedingNode, call);
			}
			return surroundWithSelectionAndTransformation(call, edge);
		}

		protected String initForResultVariable(NakedStructuralFeatureMap map) {
			return map.getter() + "()";
		}
	}

	protected A node;
	protected Jbpm5ObjectNodeExpressor expressor;

	protected Jbpm5ActionBuilder(final IOclEngine oclEngine, A node) {
		super(oclEngine, new Jbpm5ObjectNodeExpressor(oclEngine));
		this.node = node;
		this.expressor = (Jbpm5ObjectNodeExpressor) super.expressor;
	}

	public abstract void implementActionOn(OJAnnotatedOperation oper);

	public void implementPreConditions(OJOperation oper) {
		if (node instanceof PreAndPostConstrained) {
			implmementConditions(oper, (PreAndPostConstrained) node, true);
		}
	}

	public void implmementConditions(OJOperation oper, PreAndPostConstrained constrained, boolean pre) {
		Collection<IOclContext> conditions = pre ? constrained.getPreConditions() : constrained.getPostConditions();
		if (conditions.size() > 0) {
			OJBlock block = new OJBlock();
			if (node instanceof INakedAction) {
				// preConditions and PostConditions work on parameters - emulate
				// pins as parameters
				for (INakedPin pin : ((INakedAction) node).getInput()) {
					buildPinField(oper, block, pin, false);
				}
			}
			ConstraintGenerator cg = new ConstraintGenerator((OJClass) oper.getOwner(), constrained);
			oper.getBody().addToStatements(cg.buildConstraintsBlock(oper, block, conditions, pre));
		}
	}

	public void implementPostConditions(OJOperation oper) {
		if (node instanceof PreAndPostConstrained) {
			implmementConditions(oper, (PreAndPostConstrained) node, false);
		}
	}

	public void maybeContinueFlow(OJOperation operationContext, OJBlock block, INakedActivityEdge edge) {
		if (edge.getSource() instanceof INakedOutputPin) {
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(edge.getActivity(), (INakedOutputPin) edge.getSource());
			if (edge.getWeight() != null) {
				if (map.isCollection()) {
					OJIfStatement ifStatement = new OJIfStatement();
					IClassifier integerType = getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName);
					if (edge.getWeight() != null) {
					}
					String weight = ValueSpecificationUtil.expressValue(operationContext, edge.getWeight(), edge.getSource().getActivity(),
							integerType);
					ifStatement.setCondition(map.getter() + "().size()>=" + weight);
					block.addToStatements(ifStatement);
					block = ifStatement.getThenPart();
				} else {
					// would not make sense - ignore
				}
			}
		}
		continueFlow(block, edge);
	}

	public boolean requiresUserInteraction() {
		return false;
	}

	public void implementSupportingTaskMethods(OJClass activityClass) {
	}

	protected void continueFlow(OJBlock block, INakedActivityEdge edge) {
		INakedActivityNode target = edge.getEffectiveTarget();
		if (target.isImplicitJoin()) {
			block.addToStatements("getProcessInstance().signalEvent(\"signal\",\"artificial_join_for_"
					+ edge.getMappingInfo().getPersistentName().getWithoutId() + "\")");
		} else {
			block.addToStatements("getProcessInstance().signalEvent(\"signal\",\""
					+ edge.getMappingInfo().getPersistentName().getWithoutId() + "\")");
		}
	}

	public void implementConditionalFlows(OJOperation operationContext, OJBlock parentBlock, boolean getToken) {
		if (getToken) {
			getTokenFromExecutionContext(operationContext.getOwner(), parentBlock);
		}
		// TODO this is fine for decisision nodes. Everywhere else it should
		// actually offer the token to ALL
		// transitions to allow for possible forks
		OJIfStatement ifGuard = null;
		for (INakedActivityEdge flow : node.getConditionalOutgoing()) {
			OJIfStatement newIf = new OJIfStatement();
			newIf.setCondition(ValueSpecificationUtil.expressValue(operationContext, flow.getGuard(), node.getActivity(), oclEngine
					.getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName)));
			maybeContinueFlow(operationContext, newIf.getThenPart(), flow);
			OJBlock block = null;
			if (ifGuard == null) {
				block = parentBlock;
			} else {
				block = new OJBlock();
				ifGuard.setElsePart(block);
			}
			block.addToStatements(newIf);
			ifGuard = newIf;
		}
		OJBlock block;
		if (ifGuard == null) {
			block = parentBlock;
		} else {
			ifGuard.setElsePart(new OJBlock());
			block = ifGuard.getElsePart();
		}
		if (node.isImplicitFork() && node.getActivity().isProcess()) {
			// ignore guards and weight here, just go straight to the artificial
			// fork
			// TODO implement a fork that evaluates conditions before leaving
			block.addToStatements("getProcessInstance().signalEvent(\"signal\",\"artificial_fork_for_" + node.getMappingInfo().getPersistentName().getWithoutId()
					+ "\")");
		} else {
			for (INakedActivityEdge e : node.getDefaultOutgoing()) {
				maybeContinueFlow(operationContext, block, e);
			}
		}
	}

	private void getTokenFromExecutionContext(OJClassifier ojClass, OJBlock parentBlock) {
		parentBlock.addToStatements("Token waitingToken=ExecutionContext.currentExecutionContext().getToken()");
		ojClass.addToImports("org.jbpm.graph.exe.ExecutionContext");
		ojClass.addToImports("org.jbpm.graph.exe.Token");
	}

	public boolean waitsForEvent() {
		return false;
	}

	protected final String buildPinField(OJOperation operationContext, OJBlock block, INakedObjectNode pin) {
		return buildPinField(operationContext, block, pin, true);
	}

	protected final String buildPinField(OJOperation operationContext, OJBlock block, INakedObjectNode pin, boolean ensureUniqueness) {
		if (pin == null) {
			return "!!NoPin!!";
		} else {
			String pinName = " " + pin.getMappingInfo().getJavaName().toString();
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(pin.getActivity(), pin, ensureUniqueness);
			operationContext.getOwner().addToImports(map.javaTypePath());
			OJAnnotatedField field = new OJAnnotatedField(map.umlName(), map.javaTypePath());
			field.setInitExp(buildPinExpression(operationContext, block, pin));
			block.addToLocals(field);
			return pinName;
		}
	}
}
