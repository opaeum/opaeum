package net.sf.nakeduml.javageneration.bpm.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJParameter;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJStatement;
import net.sf.nakeduml.javametamodel.OJTryStatement;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.util.ExceptionHolder;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.oclengine.IOclEngine;

public class CallActionBuilder extends PotentialTaskActionBuilder<INakedCallAction> {
	private NakedStructuralFeatureMap callMap;

	public CallActionBuilder(IOclEngine engine, INakedCallAction node) {
		super(engine, node);
		callMap = OJUtil.buildStructuralFeatureMap(node, getOclEngine().getOclLibrary());
	}

	@Override
	public void implementActionOn(OJOperation operation) {
		ActionMap actionMap = new ActionMap(node);
		OJBlock block = buildLoopThroughTarget(operation, operation.getBody(), actionMap);
		// TODO hacked to get through the code
		if (node instanceof INakedOpaqueAction || BehaviorUtil.hasExecutionInstance(node.getCalledElement())) {
			INakedClassifier asClass = node.getMessageStructure();
			OJPathName ojPath = OJUtil.classifierPathname(asClass);
			operation.getOwner().addToImports(ojPath);
			String call = "new " + ojPath.getLast() + "()";
			storeMessage(block, call);
			String taskVarName = node.getName();
			if(targetElementAssigned()){
				block.addToStatements(taskVarName +".setContextObject("+ actionMap.targetName() + ")");
			}
			for (INakedPin pin : node.getArguments()) {
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node.getActivity(), pin);
				String pinExpression = buildPinExpression(operation, block, pin);
				block.addToStatements(taskVarName + "." + map.setter() + "(" + pinExpression + ")");
			}
			block.addToStatements(taskVarName + ".execute()");
			if (BehaviorUtil.returnsImmediately(node) && node.getResult().size() > 0) {
				storeResultsFromMessageStructure(block);
			} else if (BehaviorUtil.isUserTask(node)) {
				createTaskVariable(operation.getOwner(), block, node, taskVarName);
			}
		} else {
			StringBuilder arguments = populateArguments(operation, node.getArguments());
			String call = actionMap.targetName() + "." + node.getCalledElement().getMappingInfo().getJavaName() + "(" + arguments + ")";
			maybeStoreResultDirectly(block, call);
			surroundWithCatchIfRequired(operation, block);
		}
	}

	private boolean targetElementAssigned() {
		return (node.getTarget()!=null && node.getTarget().hasValidInput() )|| (node.getInPartition()!=null && node.getInPartition().getRepresents() instanceof INakedProperty);
	}

	private void surroundWithCatchIfRequired(OJOperation operationContext, OJBlock originalBlock) {
		boolean shouldSurround = !node.getCalledElement().isProcess() && node.getExceptionPins().size() > 0;
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
			operationContext.getOwner().addToImports(ExceptionHolder.class.getName());
			tryStatement.setCatchParam(new OJParameter());
			tryStatement.getCatchParam().setType(new OJPathName("ExceptionHolder"));
			tryStatement.getCatchParam().setName("e");
			// retrieve behavior instance
			String typeName = node.getMessageStructure().getName();
			tryStatement.getCatchPart().addToStatements(typeName + " " + callMap.umlName() + "=(" + typeName + ")e.getSource()");
			if (callMap.isCollection()) {
				tryStatement.getCatchPart().addToStatements(callMap.adder() + "(" + callMap.umlName() + ")");
			} else {
				tryStatement.getCatchPart().addToStatements(callMap.setter() + "(" + callMap.umlName() + ")");
			}
			if (node.getActivity().isProcess()) {
				tryStatement.getCatchPart().addToStatements("Token waitingToken=ExecutionContext.currentExecutionContext().getToken()");
				operationContext.getOwner().addToImports("org.jbpm.graph.exe.ExecutionContext");
				operationContext.getOwner().addToImports("org.jbpm.graph.exe.Token");
			}
			// store exceptions
			Iterator<INakedOutputPin> iter = node.getExceptionPins().iterator();
			while (iter.hasNext()) {
				INakedOutputPin e = iter.next();
				NakedStructuralFeatureMap pinMap = OJUtil.buildStructuralFeatureMap(e.getActivity(), e);
				StructuralFeatureMap linkedParameter = getParameterMap(node, e);
				OJPathName pathName = OJUtil.classifierPathname(e.getNakedBaseType());
				operationContext.getOwner().addToImports(pathName);
				OJIfStatement statement = new OJIfStatement();
				String readExpr = callMap.umlName() + "." + linkedParameter.getter() + "()";
				if (linkedParameter.isJavaPrimitive()) {
					statement.setCondition(readExpr + "!=" + linkedParameter.javaDefaultValue());
				} else {
					statement.setCondition(readExpr + "!=null");
				}
				statement.getThenPart().addToStatements(pinMap.setter() + "(" + readExpr + ")");
				tryStatement.getCatchPart().addToStatements(statement);
				if (e.getOutgoing().size() > 0) {
					maybeContinueFlow(operationContext, statement.getThenPart(), e.getOutgoing().iterator().next());
				}
				// break flow on exception
				statement.getThenPart().addToStatements("return");
				tryStatement.getCatchPart().addToStatements(statement);
			}
		}
	}

	private void maybeStoreResultDirectly(OJBlock body, String call) {
		if (node.getReturnPin() == null) {
			body.addToStatements(call);
		} else {
			StructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node.getActivity(), node.getReturnPin());
			if (map.isCollection()) {
				body.addToStatements(map.adder() + "(" + call + ")");
			} else {
				body.addToStatements(map.setter() + "(" + call + ")");
			}
		}
	}

	private void storeMessage(OJBlock body, String call) {
		body.addToStatements(node.getMessageStructure().getName() + " " + callMap.umlName() + "=" + call);
		// store the called activity instance in the calling activity for
		// input pins to retrieve result information later on.
		if (callMap.isCollection()) {
			body.addToStatements(callMap.adder() + "(" + callMap.umlName() + ")");
		} else {
			body.addToStatements(callMap.setter() + "(" + callMap.umlName() + ")");
		}
	}

	private void storeResultsFromMessageStructure(OJBlock body) {
		for (INakedOutputPin op : node.getResult()) {
			if (!op.isException()) {
				NakedStructuralFeatureMap targetFeature = OJUtil.buildStructuralFeatureMap(node.getActivity(), op);
				StructuralFeatureMap sourceFeature = getParameterMap(node, op);
				String readExpr = "(" + callMap.umlName() + "." + sourceFeature.getter() + "())";
				String call;
				if (targetFeature.isCollection()) {
					call = targetFeature.adder() + readExpr;
				} else {
					call = targetFeature.setter() + readExpr;
				}
				body.addToStatements(call);
			}
		}
	}

	@Override
	public boolean requiresUserInteraction() {
		return super.requiresUserInteraction() || node.getCalledElement().isProcess();
	}

	private StructuralFeatureMap getParameterMap(INakedCallAction nakedCall, INakedPin e) {
		return OJUtil.buildStructuralFeatureMap(nakedCall.getMessageStructure(), e.getLinkedTypedElement());
	}
}