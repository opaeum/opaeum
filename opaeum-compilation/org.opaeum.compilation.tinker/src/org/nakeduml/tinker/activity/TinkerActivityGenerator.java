package org.nakeduml.tinker.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import nl.klasse.octopus.model.ParameterDirectionKind;

import org.eclipse.uml2.uml.ControlNodeType;
import org.eclipse.uml2.uml.INakedAcceptEventAction;
import org.eclipse.uml2.uml.INakedAction;
import org.eclipse.uml2.uml.INakedActivity;
import org.eclipse.uml2.uml.INakedActivityEdge;
import org.eclipse.uml2.uml.INakedActivityNode;
import org.eclipse.uml2.uml.INakedControlNode;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedInputPin;
import org.eclipse.uml2.uml.INakedObjectNode;
import org.eclipse.uml2.uml.INakedOutputPin;
import org.eclipse.uml2.uml.INakedParameterNode;
import org.eclipse.uml2.uml.INakedPin;
import org.eclipse.uml2.uml.INakedSendSignalAction;
import org.eclipse.uml2.uml.INakedValuePin;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.nakeduml.tinker.generator.TinkerImplementNodeStep;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerImplementNodeStep.class, TinkerActivityNodeGenerator.class }, after = { TinkerImplementNodeStep.class })
public class TinkerActivityGenerator extends AbstractJavaProducingVisitor {

	@VisitBefore(matchSubclasses = true, match = { INakedActivity.class })
	public void visitActivity(INakedActivity a) {
		if (OJUtil.hasOJClass(a)) {
			OJAnnotatedClass activityClass = findJavaClass(a);
			activityClass.setSuperclass(TinkerBehaviorUtil.tinkerAbstractActivityPathName);
			addExecute(activityClass);

			List<INakedActivityNode> initNodes = getInitNodes(a);
			for (INakedActivityNode initNode : initNodes) {
				visitInitNodes(initNode);
			}
			
			implementGetInitialNode(activityClass, initNodes);
			
			OJOperation execute = activityClass.findOperation("execute", Collections.EMPTY_LIST);
			execute.getBody().addToStatements("return false");
			implementIsFinished(activityClass, a);
			implementGetInOutgoingParameters(activityClass, a);
		}
	}

	private void implementGetInOutgoingParameters(OJAnnotatedClass activityClass, INakedActivity a) {
		List<INakedParameterNode> incomingParameters = new ArrayList<INakedParameterNode>();
		List<INakedParameterNode> outgoingParameters = new ArrayList<INakedParameterNode>();
		for (INakedElement e : a.getOwnedElements()) {
			if (e instanceof INakedParameterNode) {
				INakedParameterNode parameter = (INakedParameterNode) e;
				if (parameter.getParameter().getDirection() == ParameterDirectionKind.IN || parameter.getParameter().getDirection() == ParameterDirectionKind.INOUT) {
					OJAnnotatedOperation getIncomingParameter = new OJAnnotatedOperation("get" + NameConverter.capitalize(parameter.getParameter().getName()));
					getIncomingParameter.setReturnType(OJUtil.classifierPathname(parameter.getNakedBaseType()));
					activityClass.addToOperations(getIncomingParameter);
					getIncomingParameter.getBody().addToStatements("return getObject(\"" + parameter.getParameter().getName() + "\")");
					incomingParameters.add(parameter);

					OJAnnotatedOperation setIncomingParameter = new OJAnnotatedOperation("set" + NameConverter.capitalize(parameter.getParameter().getName()));
					setIncomingParameter.addParam(parameter.getParameter().getName(), OJUtil.classifierPathname(parameter.getNakedBaseType()));
					setIncomingParameter.getBody().addToStatements("addEdgeToObject(" + parameter.getParameter().getName() + ",\"" + parameter.getParameter().getName() + "\")");
					activityClass.addToOperations(setIncomingParameter);

				} else if (parameter.getParameter().getDirection() == ParameterDirectionKind.OUT || parameter.getParameter().getDirection() == ParameterDirectionKind.INOUT) {
					OJAnnotatedOperation getOutgoingParameter = new OJAnnotatedOperation("get" + NameConverter.capitalize(parameter.getParameter().getName()));
					getOutgoingParameter.setReturnType(OJUtil.classifierPathname(parameter.getNakedBaseType()));
					activityClass.addToOperations(getOutgoingParameter);
					getOutgoingParameter.getBody().addToStatements("return getObject(\"" + parameter.getParameter().getName() + "\")");
					outgoingParameters.add(parameter);

					OJAnnotatedOperation setOutgoingParameter = new OJAnnotatedOperation("set" + NameConverter.capitalize(parameter.getParameter().getName()));
					setOutgoingParameter.addParam(parameter.getParameter().getName(), OJUtil.classifierPathname(parameter.getNakedBaseType()));
					setOutgoingParameter.getBody().addToStatements("addEdgeToObject(" + parameter.getParameter().getName() + ",\"" + parameter.getParameter().getName() + "\")");
					activityClass.addToOperations(setOutgoingParameter);
				}
			}
		}
		OJAnnotatedOperation getIncomingParameters = new OJAnnotatedOperation("getIncomingParameters");
		TinkerGenerationUtil.addOverrideAnnotation(getIncomingParameters);
		OJPathName returnType = new OJPathName("java.util.List");
		returnType.addToElementTypes(new OJPathName("? extends " + TinkerBehaviorUtil.tinkerActivityParameterNodePathName.getLast() + "<?>"));
		getIncomingParameters.setReturnType(returnType);
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (INakedParameterNode incomingParameter : incomingParameters) {
			if (!first) {
				sb.append(", ");
			}
			sb.append("get");
			sb.append(NameConverter.capitalize(incomingParameter.getName()));
			sb.append("()");
		}
		getIncomingParameters.getBody().addToStatements("return Arrays.asList(" + sb.toString() + ")");
		activityClass.addToOperations(getIncomingParameters);
		activityClass.addToImports(TinkerBehaviorUtil.tinkerActivityParameterNodePathName);
		activityClass.addToImports(new OJPathName("java.util.Arrays"));

		OJAnnotatedOperation getOutgoingParameters = new OJAnnotatedOperation("getOutgoingParameters");
		TinkerGenerationUtil.addOverrideAnnotation(getOutgoingParameters);
		returnType = new OJPathName("java.util.List");
		returnType.addToElementTypes(new OJPathName("? extends " + TinkerBehaviorUtil.tinkerActivityParameterNodePathName.getLast() + "<?>"));
		getOutgoingParameters.setReturnType(returnType);
		sb = new StringBuilder();
		first = true;
		for (INakedParameterNode incomingParameter : outgoingParameters) {
			if (!first) {
				sb.append(", ");
			}
			sb.append("get");
			sb.append(NameConverter.capitalize(incomingParameter.getName()));
			sb.append("()");
		}
		getOutgoingParameters.getBody().addToStatements("return Arrays.asList(" + sb.toString() + ")");
		activityClass.addToOperations(getOutgoingParameters);

	}

	private void implementIsFinished(OJAnnotatedClass activityClass, INakedActivity a) {
		List<INakedActivityNode> finalNodes = getFinalNodes(a);
		OJAnnotatedOperation isFinished = new OJAnnotatedOperation("isFinished");
		isFinished.setReturnType(new OJPathName("java.lang.Boolean"));
		StringBuilder sb = new StringBuilder();
		sb.append("return ");
		boolean first = true;
		for (INakedActivityNode finalNode : finalNodes) {
			if (!first) {
				sb.append(" || ");
			}
			first = false;
			sb.append("getNodeForName(\"");
			sb.append(NameConverter.capitalize(finalNode.getName()));
			sb.append("\").isComplete()");

		}
		if (finalNodes.isEmpty()) {
			sb.append("false");
		}
		isFinished.getBody().addToStatements(sb.toString());
		activityClass.addToOperations(isFinished);
	}

	private List<INakedActivityNode> getInitNodes(INakedActivity a) {
		List<INakedActivityNode> result = new ArrayList<INakedActivityNode>();
		Collection<INakedActivityNode> nodes = a.getActivityNodes();
		for (INakedActivityNode node : nodes) {
			if (node.getIncoming().isEmpty()) {
				result.add(node);
			}

		}
		return result;
	}

	private List<INakedActivityNode> getFinalNodes(INakedActivity a) {
		List<INakedActivityNode> result = new ArrayList<INakedActivityNode>();
		Collection<INakedActivityNode> nodes = a.getActivityNodes();
		for (INakedActivityNode node : nodes) {
			if (node.getOutgoing().isEmpty() && (node instanceof INakedControlNode) && ((INakedControlNode) node).getControlNodeType() == ControlNodeType.ACTIVITY_FINAL_NODE) {
				result.add(node);
			}

		}
		return result;
	}

	private void visitInitNodes(INakedActivityNode node) {
		INakedActivity activity = node.getActivity();
		OJAnnotatedClass activityClass = findJavaClass(activity);
		addInitNode(activityClass, node);
		OJOperation init = activityClass.findOperation("init", Arrays.asList(TinkerGenerationUtil.compositionNodePathName));
		createActivityGraph(activityClass, init.getBody(), node);
		startProcessInExecute(activityClass, node);
	}

	private void implementGetInitialNode(OJAnnotatedClass activityClass, List<INakedActivityNode> nodes) {
		OJAnnotatedOperation getInitialNode = new OJAnnotatedOperation("getInitialNodes");
		getInitialNode.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		OJPathName returnType = new OJPathName("java.util.List");
		returnType.addToElementTypes(new OJPathName("? extends ActivityNode<?>"));
		activityClass.addToImports(TinkerBehaviorUtil.tinkerActivityNodePathName.getCopy());
		getInitialNode.setReturnType(returnType);
		StringBuilder sb = new StringBuilder("Arrays.asList(");
		for (INakedActivityNode initNode : nodes) {
			sb.append(TinkerBehaviorUtil.activityNodeGetter(initNode) + "(),");
		}
		getInitialNode.getBody().addToStatements("return " + sb.toString().substring(0, sb.toString().length()-1) + ")");
		activityClass.addToOperations(getInitialNode);
	}

	private void startProcessInExecute(OJAnnotatedClass activityClass, INakedActivityNode node) {
		OJOperation execute = activityClass.findOperation("execute", Collections.EMPTY_LIST);
		OJSimpleStatement initNode = new OJSimpleStatement();
		initNode.setExpression(TinkerBehaviorUtil.activityNodePathName(node).getLast() + " " + NameConverter.decapitalize(node.getName()) + " = get"
				+ NameConverter.capitalize(node.getName()) + "()");
		execute.getBody().addToStatements(initNode);
		OJSimpleStatement statement1 = new OJSimpleStatement();
		OJPathName nodePathName;
		if (node instanceof INakedParameterNode) {
			nodePathName = TinkerBehaviorUtil.tinkerObjectTokenPathName.getCopy();
			nodePathName.addToGenerics(OJUtil.classifierPathname(((INakedObjectNode) node).getNakedBaseType()));
			statement1.setExpression(NameConverter.decapitalize(NameConverter.decapitalize(node.getName()) + ".setStarts(new SingleIterator<" + nodePathName.getLast() + ">(new "
					+ nodePathName.getLast() + "(\"" + node.getName() + "\", get"+ NameConverter.capitalize(((INakedParameterNode)node).getParameter().getName()) +"())))"));
		} else {
			nodePathName = TinkerBehaviorUtil.tinkerControlTokenPathName.getCopy();
			statement1.setExpression(NameConverter.decapitalize(NameConverter.decapitalize(node.getName()) + ".setStarts(new SingleIterator<"
					+ TinkerBehaviorUtil.tinkerControlTokenPathName.getLast() + ">(new " + nodePathName.getLast() + "(\"" + node.getName() + "\")))"));
		}
		activityClass.addToImports(nodePathName);
		activityClass.addToImports(TinkerBehaviorUtil.tinkerTokenPathName);
		execute.getBody().addToStatements(statement1);
		OJSimpleStatement statement2 = new OJSimpleStatement(NameConverter.decapitalize(node.getName()) + ".next()");
		execute.getBody().addToStatements(statement2);
		activityClass.addToImports(TinkerBehaviorUtil.tinkerSingleIteratorPathName);
		activityClass.addToImports(TinkerBehaviorUtil.tinkerTokenPathName);
	}

	private void createActivityGraph(OJAnnotatedClass activityClass, OJBlock block, INakedActivityNode initNode) {
		List<INakedAction> actions = new ArrayList<INakedAction>();
		walkActivityForNodes(new ArrayList<String>(), activityClass, block, initNode, actions);
		walkActivityForEdges(new ArrayList<String>(), block, initNode);
		addEdgeToInitNode(new ArrayList<String>(), initNode, block);
		addPinEdgesToActions(actions, block);
	}
	
	private void addPinEdgesToActions(List<INakedAction> actions, OJBlock block) {
		for (INakedAction node : actions) {
			Collection<INakedInputPin> inputs = ((INakedAction) node).getInput();
			for (INakedInputPin inputPin : inputs) {
				if ((node instanceof INakedSendSignalAction && ((INakedSendSignalAction) node).getTarget() == inputPin) || inputPin instanceof INakedValuePin) {
					continue;
				}
				addPinEdge(node, inputPin, block);
			}
			for (INakedOutputPin outputPin : ((INakedAction) node).getOutput()) {
				addPinEdge(node, outputPin, block);
			}
		}
	}

	private void walkActivityForEdges(List<String> instantiatedClasses, OJBlock block, INakedActivityNode node) {
		for (INakedActivityEdge edge : node.getOutgoing()) {
			addEdge(instantiatedClasses, edge, block);
			walkActivityForEdges(instantiatedClasses, block, edge.getTarget());
		}
		if (node instanceof INakedAction) {
			for (INakedOutputPin outputPin : ((INakedAction) node).getOutput()) {
				walkActivityForEdges(instantiatedClasses, block, outputPin);
			}
		}
	}

	private void walkActivityForNodes(List<String> instantiatedClasses, OJAnnotatedClass activityClass, OJBlock block, INakedActivityNode node, List<INakedAction> actions) {
		instantiateNode(instantiatedClasses, activityClass, node, block);
		for (INakedActivityEdge edge : node.getOutgoing()) {
			walkActivityForNodes(instantiatedClasses, activityClass, block, edge.getTarget(), actions);
		}
		if (node instanceof INakedAction) {
			actions.add((INakedAction)node);
			for (INakedOutputPin outputPin : ((INakedAction) node).getOutput()) {
				walkActivityForNodes(instantiatedClasses, activityClass, block, outputPin, actions);
			}
		}
	}

	private void addPinEdge(/*List<String> instantiatedClasses, */INakedActivityNode node, INakedPin pin, OJBlock block) {
//		if (!instantiatedClasses.contains(NameConverter.capitalize(node.getName() + "::" + pin.getName()))) {
//			instantiatedClasses.add(NameConverter.capitalize(node.getName() + "::" + pin.getName()));
			OJSimpleStatement addEdge1 = new OJSimpleStatement();
			String edgeName = NameConverter.decapitalize(node.getName()) + NameConverter.capitalize(pin.getName()) + "Edge";
			addEdge1.setExpression("Edge " + edgeName + " = GraphDb.getDb().addEdge(null, " + NameConverter.decapitalize(node.getName()) + ".getVertex(), "
					+ NameConverter.decapitalize(pin.getName()) + ".getVertex(), \"" + ((pin instanceof INakedInputPin) ? "inputPin" : "outputPin")
					+ NameConverter.capitalize(pin.getName()) + "\")");
			block.addToStatements(addEdge1);

			OJSimpleStatement addEdge2 = new OJSimpleStatement();
			addEdge2.setExpression(edgeName + ".setProperty(\"inClass\", " + NameConverter.decapitalize(pin.getName()) + ".getClass().getName())");
			block.addToStatements(addEdge2);

			OJSimpleStatement addEdge3 = new OJSimpleStatement();
			addEdge3.setExpression(edgeName + ".setProperty(\"outClass\", " + NameConverter.decapitalize(node.getName()) + ".getClass().getName())");
			block.addToStatements(addEdge3);
//		}
	}

	private void addEdge(List<String> instantiatedClasses, INakedActivityEdge edge, OJBlock block) {
		if (!instantiatedClasses.contains(NameConverter.capitalize(edge.getName()))) {
			instantiatedClasses.add(NameConverter.capitalize(edge.getName()));
			OJSimpleStatement addEdge1 = new OJSimpleStatement();
			addEdge1.setExpression("Edge " + NameConverter.decapitalize(edge.getName()) + " = GraphDb.getDb().addEdge(null, "
					+ NameConverter.decapitalize(edge.getSource().getName()) + ".getVertex(), " + NameConverter.decapitalize(edge.getTarget().getName()) + ".getVertex(), " + "\""
					+ edge.getName() + "\")");
			block.addToStatements(addEdge1);

			OJSimpleStatement addEdge2 = new OJSimpleStatement();
			addEdge2.setExpression(NameConverter.decapitalize(edge.getName()) + ".setProperty(\"inClass\", " + NameConverter.decapitalize(edge.getTarget().getName())
					+ ".getClass().getName())");
			block.addToStatements(addEdge2);

			OJSimpleStatement addEdge3 = new OJSimpleStatement();
			addEdge3.setExpression(NameConverter.decapitalize(edge.getName()) + ".setProperty(\"outClass\", " + NameConverter.decapitalize(edge.getSource().getName())
					+ ".getClass().getName())");
			block.addToStatements(addEdge3);
		}
	}

	private void addEdgeToInitNode(List<String> instantiatedClasses, INakedActivityNode initNode, OJBlock block) {
		if (!instantiatedClasses.contains(NameConverter.capitalize(initNode.getName()))) {
			instantiatedClasses.add(NameConverter.capitalize(initNode.getName()));
			OJSimpleStatement addEdge1 = new OJSimpleStatement();
			addEdge1.setExpression("Edge " + NameConverter.decapitalize(initNode.getName()) + "Edge = GraphDb.getDb().addEdge(null, "
					+ NameConverter.decapitalize("getVertex(), " + NameConverter.decapitalize(initNode.getName()) + ".getVertex(), " + "\"" + initNode.getName() + "Edge\")"));
			block.addToStatements(addEdge1);

			OJSimpleStatement addEdge2 = new OJSimpleStatement();
			addEdge2.setExpression(NameConverter.decapitalize(initNode.getName()) + "Edge.setProperty(\"inClass\", " + "getClass().getName())");
			block.addToStatements(addEdge2);

			OJSimpleStatement addEdge3 = new OJSimpleStatement();
			addEdge3.setExpression(NameConverter.decapitalize(initNode.getName()) + "Edge.setProperty(\"outClass\", " + NameConverter.decapitalize(initNode.getName())
					+ ".getClass().getName())");
			block.addToStatements(addEdge3);
		}
	}

	private void instantiateNode(List<String> instantiatedClasses, OJAnnotatedClass activityClass, INakedActivityNode node, OJBlock block) {
		if (!instantiatedClasses.contains(NameConverter.capitalize(node.getName()))) {
			instantiatedClasses.add(NameConverter.capitalize(node.getName()));
			if (node instanceof INakedControlNode && ((INakedControlNode) node).getControlNodeType().isInitialNode()
					|| (node instanceof INakedAcceptEventAction && node.getIncoming().isEmpty())) {
				OJSimpleStatement instantiateNode = new OJSimpleStatement();
				instantiateNode.setExpression(NameConverter.capitalize(node.getName()) + " " + NameConverter.decapitalize(node.getName()) + " = new "
						+ NameConverter.capitalize(node.getName()) + "(this.contextObject)");
				block.addToStatements(3, instantiateNode);
				instantiateNode = new OJSimpleStatement();
				instantiateNode.setExpression(NameConverter.decapitalize(node.getName()) + ".setNodeStatus(NodeStatus.ENABLED)");
				activityClass.addToImports(TinkerBehaviorUtil.tinkerNodeStatusPathName);
				block.addToStatements(4,instantiateNode);
			} else {
				OJSimpleStatement instantiateNode = new OJSimpleStatement();
				instantiateNode.setExpression(NameConverter.capitalize(node.getName()) + " " + NameConverter.decapitalize(node.getName()) + " = new "
						+ NameConverter.capitalize(node.getName()) + "(this.contextObject)");
				block.addToStatements(3,instantiateNode);
			}
			activityClass.addToImports(findJavaPath(node));
		}
	}

	private void addInitNode(OJClass activityClass, INakedActivityNode node) {
		OJAnnotatedOperation getter = new OJAnnotatedOperation(TinkerBehaviorUtil.activityNodeGetter(node));
		getter.setReturnType(findJavaPath(node));
		getter.getBody().addToStatements(
				"return new " + NameConverter.capitalize(node.getName()) + "(this.vertex.getOutEdges(\"" + node.getName()
						+ "Edge\").iterator().next().getInVertex(), this.contextObject)");
		activityClass.addToOperations(getter);
	}

	private OJPathName findJavaPath(INakedActivityNode node) {
		OJPathName path = OJUtil.packagePathname(node.getNameSpace());
		return new OJPathName(path.toJavaString() + "." + NameConverter.capitalize(node.getName()));
	}

	private void addExecute(OJClass activityClass) {
		OJAnnotatedOperation execute = new OJAnnotatedOperation("execute");
		execute.setReturnType(new OJPathName("boolean"));
		activityClass.addToOperations(execute);
	}
}
