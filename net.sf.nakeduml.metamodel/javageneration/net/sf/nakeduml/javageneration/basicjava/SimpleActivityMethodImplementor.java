package net.sf.nakeduml.javageneration.basicjava;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.NakedOperationMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.Caller;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ClassifierBehaviorStarter;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ObjectCreator;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ParameterNodeImplementor;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ObjectNodeExpressor;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.SignalSender;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.SimpleActionBuilder;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.StructuralFeatureClearer;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.StructuralFeatureReader;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.StructuralFeatureValueAdder;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.StructuralFeatureValueRemover;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.VariableClearer;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.VariableReader;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.VariableValueAdder;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.VariableValueRemover;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJClassifier;
import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.OJForStatement;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJParameter;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJStatement;
import net.sf.nakeduml.javametamodel.OJTryStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.actions.INakedAddStructuralFeatureValueAction;
import net.sf.nakeduml.metamodel.actions.INakedAddVariableValueAction;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedClearStructuralFeatureAction;
import net.sf.nakeduml.metamodel.actions.INakedClearVariableAction;
import net.sf.nakeduml.metamodel.actions.INakedCreateObjectAction;
import net.sf.nakeduml.metamodel.actions.INakedReadStructuralFeatureAction;
import net.sf.nakeduml.metamodel.actions.INakedReadVariableAction;
import net.sf.nakeduml.metamodel.actions.INakedRemoveStructuralFeatureValueAction;
import net.sf.nakeduml.metamodel.actions.INakedRemoveVariableValueAction;
import net.sf.nakeduml.metamodel.actions.INakedSendSignalAction;
import net.sf.nakeduml.metamodel.actions.INakedStartClassifierBehaviorAction;
import net.sf.nakeduml.metamodel.activities.ActivityKind;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedActivityVariable;
import net.sf.nakeduml.metamodel.activities.INakedControlNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionRegion;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedParameterNode;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;
import nl.klasse.octopus.oclengine.IOclEngine;
import nl.klasse.octopus.stdlib.IOclLibrary;

public class SimpleActivityMethodImplementor extends AbstractJavaProducingVisitor {
	@Override
	public void initialize(INakedModelWorkspace workspace, OJPackage javaModel, NakedUmlConfig config, TextWorkspace textWorkspace) {
		super.initialize(workspace, javaModel, config, textWorkspace);
	}

	@VisitAfter
	public void addExceptionParameter(INakedModel m) {
		OJClass ojClass = UtilityCreator.getUtilPack().findClass(new OJPathName("ExceptionParameter"));
		if (ojClass != null) {
			super.createTextPath(ojClass, JavaTextSource.GEN_SRC);
		}
	}

	@VisitBefore
	public void implementActivity(INakedActivity a) {
		if (a.getActivityKind() == ActivityKind.SIMPLE_SYNCHRONOUS_METHOD && OJUtil.hasOJClass(a.getContext()) && !a.isClassifierBehavior()
				&& a.getOwnerElement() instanceof INakedClassifier) {
			// DO not do effects, state actions or classifier behavior - will be
			// invoked elsewhere
			// Does not support: implicit or explicit parallelism
			// Does not have loopbacks
			// Does not accept any events
			// output pin names must be unique
			// cannot call processes, user responsibilities, or any process that
			// does not return immediately
			// Object flows: sourceAndTarget must be conformant and
			// multilplicity must be compatible
			// only one node should
			OJAnnotatedClass owner = findJavaClass(a.getContext());
			OperationMap am = new NakedOperationMap(a.getSpecification() == null ? a : a.getSpecification());
			OJAnnotatedOperation oper = (OJAnnotatedOperation) owner.findOperation(am.javaOperName(), am.javaParamTypePaths());
			implementActivityOn(a, oper);
		}
	}

	public void implementActivityOn(INakedActivity a, OJAnnotatedOperation oper) {
		oper.setBody(new OJBlock());
		OJBlock block = oper.getBody();
		INakedActivityNode first = getFirstNode(a.getStartNodes());
		addVariables(a, a.getVariables(), oper.getBody(), oper.getOwner());
		implementNode(oper, block, first);
	}

	private void addVariables(INakedActivity a, Collection<INakedActivityVariable> vars, OJBlock body, OJClassifier owner) {
		for (INakedActivityVariable var : vars) {
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(a, var);
			OJField field = new OJField();
			field.setName(map.umlName());
			field.setType(map.javaTypePath());
			field.setInitExp(map.javaBaseDefaultValue());
			body.addToLocals(field);
			owner.addToImports(map.javaBaseTypePath());
			owner.addToImports(map.javaDefaultTypePath());
		}
	}

	private void implementNode(OJAnnotatedOperation operation, OJBlock block, INakedActivityNode node) {
		if (node instanceof INakedControlNode) {
			implementControlNode(operation, block, (INakedControlNode) node);
		}else if(node instanceof INakedExpansionRegion){
			implementExpansionRegion(operation, block, (INakedExpansionRegion) node);
		} else if (node != null) {
			implementAction(operation, block, node);
		}
	}

	private void implementAction(OJAnnotatedOperation operation, OJBlock block, INakedActivityNode node) {
		SimpleActionBuilder<?> actionBuilder = resolveActionBuilder(node, getOclEngine());
		if (actionBuilder != null) {
			actionBuilder.implementActionOn(operation, block);
			if (actionBuilder instanceof Caller) {
				block = surroundWithCatchIfRequired((INakedCallAction) node, operation, block);
			}
		}
		maybeImplementNextNode(operation, block, node);
	}

	private void implementExpansionRegion(OJAnnotatedOperation operation, OJBlock block, INakedExpansionRegion region) {
		INakedExpansionNode input = region.getInputElement().get(0);
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(region.getActivity(), input);
		OJForStatement forEach = new OJForStatement(input.getName(), map.javaBaseTypePath(), new ObjectNodeExpressor().expressInputPinOrOutParam(block, input));
		block.addToStatements(forEach);
		addVariables(region.getActivity(), region.getVariables(), forEach.getBody(), operation.getOwner());
		//TODO get first node, likely an inputElement to implement
		if (input.getDefaultOutgoing().size() == 1) {
			implementNode(operation, forEach.getBody(), input.getDefaultOutgoing().iterator().next().getEffectiveTarget());
		}else{
			implementNode(operation, forEach.getBody(), getFirstNode(region.getStartNodes()));
		}
		maybeImplementNextNode(operation, block, region);
	}

	private void implementControlNode(OJAnnotatedOperation operation, OJBlock block, INakedControlNode cn) {
		switch (cn.getControlNodeType()) {
		case INITIAL_NODE:
		case MERGE_NODE:
			// TODO Does not seem like this would work. We need to pop the block
			// stack
			maybeImplementNextNode(operation, block, cn);
			break;
		case ACTIVITY_FINAL_NODE:
			// implementNode(oper, block,
			// first.getDefaultOutgoing().iterator().next().getEffectiveTarget());
			break;
		case DECISION_NODE:
			// implementNode(oper, block,
			// first.getDefaultOutgoing().iterator().next().getEffectiveTarget());
			OJBlock elseBlock = block;
			OJIfStatement ifStatement = null;
			for (INakedActivityEdge edge : cn.getConditionalOutgoing()) {
				ifStatement = new OJIfStatement();
				elseBlock.addToStatements(ifStatement);
				ifStatement.setCondition(ValueSpecificationUtil.expressValue(operation, edge.getGuard(), cn.getActivity().getContext(),
						super.workspace.getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName)));
				implementNode(operation, ifStatement.getThenPart(), edge.getEffectiveTarget());
				ifStatement.setElsePart(new OJBlock());
				elseBlock = ifStatement.getElsePart();
			}
			if (cn.getDefaultOutgoing().isEmpty()) {
				if (ifStatement != null) {
					ifStatement.setElsePart(null);
				}
			} else {
				maybeImplementNextNode(operation, elseBlock, cn);
			}
			break;
		default:
			break;
		}
	}

	public static SimpleActionBuilder<?> resolveActionBuilder(INakedActivityNode node, IOclEngine oclEngine) {
		SimpleActionBuilder<?> actionBuilder = null;
		if (node instanceof INakedParameterNode) {
			actionBuilder = new ParameterNodeImplementor(oclEngine, (INakedParameterNode) node, new ObjectNodeExpressor());
		} else if (node instanceof INakedAddStructuralFeatureValueAction) {
			actionBuilder = new StructuralFeatureValueAdder(oclEngine, (INakedAddStructuralFeatureValueAction) node, new ObjectNodeExpressor());
		} else if (node instanceof INakedRemoveStructuralFeatureValueAction) {
			actionBuilder = new StructuralFeatureValueRemover(oclEngine, (INakedRemoveStructuralFeatureValueAction) node, new ObjectNodeExpressor());
		} else if (node instanceof INakedClearStructuralFeatureAction) {
			actionBuilder = new StructuralFeatureClearer(oclEngine, (INakedClearStructuralFeatureAction) node, new ObjectNodeExpressor());
		} else if (node instanceof INakedReadStructuralFeatureAction) {
			actionBuilder = new StructuralFeatureReader(oclEngine, (INakedReadStructuralFeatureAction) node, new ObjectNodeExpressor());
		} else if (node instanceof INakedAddVariableValueAction) {
			actionBuilder = new VariableValueAdder(oclEngine, (INakedAddVariableValueAction) node, new ObjectNodeExpressor());
		} else if (node instanceof INakedRemoveVariableValueAction) {
			actionBuilder = new VariableValueRemover(oclEngine, (INakedRemoveVariableValueAction) node, new ObjectNodeExpressor());
		} else if (node instanceof INakedClearVariableAction) {
			actionBuilder = new VariableClearer(oclEngine, (INakedClearVariableAction) node, new ObjectNodeExpressor());
		} else if (node instanceof INakedReadVariableAction) {
			actionBuilder = new VariableReader(oclEngine, (INakedReadVariableAction) node, new ObjectNodeExpressor());
		} else if (node instanceof INakedCallAction) {
			actionBuilder = new Caller(oclEngine, (INakedCallAction) node, new ObjectNodeExpressor());
		} else if (node instanceof INakedCreateObjectAction) {
			actionBuilder = new ObjectCreator(oclEngine, (INakedCreateObjectAction) node, new ObjectNodeExpressor());
		} else if (node instanceof INakedStartClassifierBehaviorAction) {
			actionBuilder = new ClassifierBehaviorStarter(oclEngine, (INakedStartClassifierBehaviorAction) node, new ObjectNodeExpressor());
		} else if (node instanceof INakedSendSignalAction) {
			actionBuilder = new SignalSender(oclEngine, (INakedSendSignalAction) node, new ObjectNodeExpressor());
		}
		return actionBuilder;
	}

	private void maybeImplementNextNode(OJAnnotatedOperation operation, OJBlock block, INakedActivityNode pn) {
		if (pn.getDefaultOutgoing().size() == 1) {
			implementNode(operation, block, pn.getDefaultOutgoing().iterator().next().getEffectiveTarget());
		}
	}

	private static INakedActivityNode getFirstNode(Collection<INakedActivityNode> startNodes) {
		if(startNodes.isEmpty()){
			return null;
		}else{
			return startNodes.iterator().next();
		}
	}

	private OJBlock surroundWithCatchIfRequired(INakedCallAction nakedCall, OJAnnotatedOperation operationContext, OJBlock originalBlock) {
		boolean shouldSurround = nakedCall.getExceptionPins().size() > 0;
		if (shouldSurround) {
			List<OJField> locals = new ArrayList<OJField>(originalBlock.getLocals());
			List<OJStatement> statements = new ArrayList<OJStatement>(originalBlock.getStatements());
			originalBlock.removeAllFromLocals();
			originalBlock.removeAllFromStatements();
			OJTryStatement tryStatement = new OJTryStatement();
			originalBlock.addToStatements(tryStatement);
			tryStatement.setCatchPart(new OJBlock());
			tryStatement.setTryPart(new OJBlock());
			tryStatement.getTryPart().addToLocals(locals);
			tryStatement.getTryPart().addToStatements(statements);
			tryStatement.setCatchParam(new OJParameter());
			tryStatement.getCatchParam().setType(new OJPathName(UtilityCreator.getUtilPathName() + ".ExceptionParameter"));
			tryStatement.getCatchParam().setName("e");
			for (INakedOutputPin e : nakedCall.getExceptionPins()) {
				NakedStructuralFeatureMap pinMap = OJUtil.buildStructuralFeatureMap(nakedCall.getContext(), e);
				OJPathName pathName = OJUtil.classifierPathname(e.getNakedBaseType());
				operationContext.getOwner().addToImports(pathName);
				OJIfStatement statement = new OJIfStatement();
				statement.setCondition("e.isParameter(\"" + e.getLinkedTypedElement().getName() + "\")");
				OJField parm = new OJField();
				parm.setName(pinMap.umlName());
				parm.setType(pinMap.javaTypePath());
				parm.setInitExp("(" + pinMap.javaType() + ")e.getValue()");
				statement.getThenPart().addToLocals(parm);
				tryStatement.getCatchPart().addToStatements(statement);
				if (e.getOutgoing().size() > 0) {
					INakedActivityEdge outgoing = e.getOutgoing().iterator().next();
					implementNode(operationContext, statement.getThenPart(), outgoing.getEffectiveTarget());
				}
				// break flow on exception
				statement.getThenPart().addToStatements("return");
				tryStatement.getCatchPart().addToStatements(statement);
			}
			return tryStatement.getTryPart();
		} else {
			return originalBlock;
		}
	}
}
