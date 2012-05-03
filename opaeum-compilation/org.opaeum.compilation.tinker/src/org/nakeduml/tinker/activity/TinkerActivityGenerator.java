package org.nakeduml.tinker.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.model.ParameterDirectionKind;

import org.nakeduml.tinker.activity.generator.TinkerActivityNodeExGenerator;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.nakeduml.tinker.generator.TinkerImplementNodeStep;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedAcceptEventAction;
import org.opaeum.metamodel.actions.INakedSendSignalAction;
import org.opaeum.metamodel.activities.ControlNodeType;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedControlNode;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.activities.INakedParameterNode;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.activities.INakedValuePin;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import org.opaeum.name.NameConverter;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerImplementNodeStep.class, TinkerActivityNodeExGenerator.class }, after = { TinkerImplementNodeStep.class })
public class TinkerActivityGenerator extends AbstractJavaProducingVisitor {

	@VisitBefore(matchSubclasses = true, match = { INakedActivity.class })
	public void visitActivity(INakedActivity a) {
		if (OJUtil.hasOJClass(a)) {
			OJAnnotatedClass activityClass = findJavaClass(a);

			activityClass.setSuperclass(TinkerBehaviorUtil.tinkerAbstractActivityPathName);
			OJAnnotatedOperation execute = addExecute(a, activityClass);

			List<INakedAction> actions = new ArrayList<INakedAction>();
			List<String> instantiatedClasses = new ArrayList<String>();
			List<INakedActivityNode> initNodes = getInitNodes(a);
			for (INakedActivityNode initNode : initNodes) {
				visitNodes(initNode, execute, actions, instantiatedClasses);
			}

			implementGetInitialNode(activityClass, initNodes);

			completeExecute(a, execute);

			implementIsFinished(activityClass, a);

			activityClass.addToImports(new OJPathName("java.util.Arrays"));

			// addOutParameters(instantiatedClasses, activityClass, a);
			// if (a.getSpecification()!=null) {
			// addFinalNode(instantiatedClasses, activityClass, a);
			// }
		}
	}

	private void completeExecute(INakedActivity a, OJAnnotatedOperation execute) {

		for (INakedParameter param : a.getOwnedParameters()) {
			if ((param.getDirection() == ParameterDirectionKind.OUT || param.getDirection() == ParameterDirectionKind.INOUT) && !param.isReturn()) {
				if (param.getNakedMultiplicity().isOne()) {
					List<INakedParameterNode> outAndInOutParamNodes = getOutAndInOutParameterNodes(a);
					for (INakedParameterNode iNakedParameterNode : outAndInOutParamNodes) {
						if (iNakedParameterNode.getParameter().equals(param)) {
							NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(a.getSpecification().getOwner(), iNakedParameterNode.getParameter());

							if (param.getNakedBaseType() instanceof INakedSimpleType) {
								execute.getBody().addToStatements(
										"_" + map.fieldname() + "."+TinkerGenerationUtil.clearMutable(map));

								execute.getBody().addToStatements(
										"_" + map.fieldname() + "."+TinkerGenerationUtil.setMutable(map)+"(" + TinkerBehaviorUtil.activityNodeGetter(iNakedParameterNode)
												+ "().getReturnParameterValues().get(0))");

							} else {
								execute.getBody().addToStatements(
										"_" + map.fieldname() + ".setVertex(" + TinkerBehaviorUtil.activityNodeGetter(iNakedParameterNode)
												+ "().getReturnParameterValues().get(0).getVertex())");
							}
							continue;
						}
					}
				} else {
					List<INakedParameterNode> outParamNodes = getOutParameterNodes(a);
					for (INakedParameterNode iNakedParameterNode : outParamNodes) {
						if (iNakedParameterNode.getParameter().equals(param)) {
							//TODO Simple types
							NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(a.getSpecification().getOwner(), iNakedParameterNode.getParameter());
							execute.getBody().addToStatements("_" + map.fieldname() + ".clear()");
							execute.getBody().addToStatements("_" + map.fieldname() + ".addAll(" + TinkerBehaviorUtil.activityNodeGetter(iNakedParameterNode) + "().getReturnParameterValues())");
							continue;
						}
					}

				}
			}
		}

		if (a.getReturnParameter() != null && a.getReturnParameter().isReturn()) {
			INakedParameterNode returnParameterNode = getReturnParameterNodes(a);
			if (a.getReturnParameter().getNakedMultiplicity().isOne()) {
				execute.getBody().addToStatements("return " + TinkerBehaviorUtil.activityNodeGetter(returnParameterNode) + "().getReturnParameterValues().get(0)");
			} else {
				execute.getBody().addToStatements("return " + TinkerBehaviorUtil.activityNodeGetter(returnParameterNode) + "().getReturnParameterValues()");
			}
		}
	}

	private void addFinalNode(List<String> instantiatedClasses, OJAnnotatedClass activityClass, INakedActivity a) {
		OJOperation init = activityClass.findOperation("init", Arrays.asList(TinkerGenerationUtil.compositionNodePathName));
		List<INakedActivityNode> finalNodes = getFinalNodes(a);
		for (INakedActivityNode finalNode : finalNodes) {
			addGetterToNode(activityClass, finalNode);
			addEdgeToInitNode(instantiatedClasses, finalNode, init.getBody());
		}

	}

	private void addOutParameters(List<String> instantiatedClasses, OJAnnotatedClass activityClass, INakedActivity a) {
		OJOperation init = activityClass.findOperation("init", Arrays.asList(TinkerGenerationUtil.compositionNodePathName));
		List<INakedParameterNode> outParameters = getOutAndInOutParameterNodes(a);
		for (INakedParameterNode outNakedParameterNode : outParameters) {
			addGetterToNode(activityClass, outNakedParameterNode);
			addEdgeToInitNode(instantiatedClasses, outNakedParameterNode, init.getBody());
		}
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
			if ((node instanceof INakedParameterNode && (((INakedParameterNode) node).getParameter().getDirection() == ParameterDirectionKind.IN || ((INakedParameterNode) node)
					.getParameter().getDirection() == ParameterDirectionKind.INOUT))
					|| node instanceof INakedAcceptEventAction
					|| (node instanceof INakedControlNode && ((INakedControlNode) node).getControlNodeType().isInitialNode())) {
				if (node.getIncoming().isEmpty()) {
					result.add(node);
				}
			}

		}
		return result;
	}

	private List<INakedParameterNode> getOutAndInOutParameterNodes(INakedActivity a) {
		List<INakedParameterNode> result = new ArrayList<INakedParameterNode>();
		Collection<INakedActivityNode> nodes = a.getActivityNodes();
		for (INakedActivityNode node : nodes) {
			if (node instanceof INakedParameterNode && node.getOutgoing().isEmpty()) {
				INakedParameterNode parameterNode = (INakedParameterNode) node;
				if (parameterNode.getParameter().getDirection() == ParameterDirectionKind.INOUT || parameterNode.getParameter().getDirection() == ParameterDirectionKind.OUT) {
					result.add(parameterNode);
				}
			}
		}
		return result;
	}

	private List<INakedParameterNode> getOutParameterNodes(INakedActivity a) {
		List<INakedParameterNode> result = new ArrayList<INakedParameterNode>();
		Collection<INakedActivityNode> nodes = a.getActivityNodes();
		for (INakedActivityNode node : nodes) {
			if (node instanceof INakedParameterNode && node.getOutgoing().isEmpty()) {
				INakedParameterNode parameterNode = (INakedParameterNode) node;
				if (parameterNode.getParameter().getDirection() == ParameterDirectionKind.OUT) {
					result.add(parameterNode);
				}
			}
		}
		return result;
	}

	private INakedParameterNode getReturnParameterNodes(INakedActivity a) {
		Collection<INakedActivityNode> nodes = a.getActivityNodes();
		for (INakedActivityNode node : nodes) {
			if (node instanceof INakedParameterNode && node.getOutgoing().isEmpty()) {
				INakedParameterNode parameterNode = (INakedParameterNode) node;
				if (parameterNode.getParameter().isReturn()) {
					return parameterNode;
				}
			}
		}
		return null;
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

	private void visitNodes(INakedActivityNode node, OJAnnotatedOperation execute, List<INakedAction> actions, List<String> instantiatedClasses) {
		INakedActivity activity = node.getActivity();
		OJAnnotatedClass activityClass = findJavaClass(activity);
		// addGetterToNode(activityClass, node);
		OJOperation init = activityClass.findOperation("init", Arrays.asList(TinkerGenerationUtil.compositionNodePathName));
		createActivityGraph(activityClass, init.getBody(), node, actions, instantiatedClasses);
		// addEdgeToInitNode(instantiatedClasses, node, init.getBody());
		addPinEdgesToActions(instantiatedClasses, actions, init.getBody());
		startProcessInExecute(activityClass, node, execute);
	}

	private void implementGetInitialNode(OJAnnotatedClass activityClass, List<INakedActivityNode> nodes) {
		OJAnnotatedOperation getInitialNode = new OJAnnotatedOperation("getInitialNodes");
		getInitialNode.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		OJPathName returnType = new OJPathName("java.util.List");
		returnType.addToElementTypes(new OJPathName("? extends ActivityNode<?, ?>"));
		activityClass.addToImports(TinkerBehaviorUtil.tinkerActivityNodePathName.getCopy());
		getInitialNode.setReturnType(returnType);
		StringBuilder sb = new StringBuilder("Arrays.asList(");
		for (INakedActivityNode initNode : nodes) {
			sb.append(TinkerBehaviorUtil.activityNodeGetter(initNode) + "(),");
		}
		getInitialNode.getBody().addToStatements("return " + sb.toString().substring(0, sb.toString().length() - 1) + ")");
		activityClass.addToOperations(getInitialNode);
	}

	private void startProcessInExecute(OJAnnotatedClass activityClass, INakedActivityNode node, OJAnnotatedOperation execute) {
		OJSimpleStatement initNode = new OJSimpleStatement();
		initNode.setExpression(TinkerBehaviorUtil.activityNodePathName(node).getLast() + " " + NameConverter.decapitalize(node.getName()) + " = "
				+ TinkerBehaviorUtil.activityNodeGetter(node) + "()");
		execute.getBody().addToStatements(initNode);
		OJSimpleStatement statement1 = new OJSimpleStatement();
		OJPathName nodePathName;
		if (node instanceof INakedParameterNode) {
			INakedParameterNode parameterNode = (INakedParameterNode) node;
			OJUtil.unlock();

			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new TypedElementPropertyBridge(node.getActivity(), parameterNode.getParameter()));

			nodePathName = TinkerBehaviorUtil.tinkerObjectTokenPathName.getCopy();
			nodePathName.addToGenerics(OJUtil.classifierPathname(((INakedObjectNode) node).getNakedBaseType()));
			if (((INakedParameterNode) node).getNakedMultiplicity().isMany()) {
				String expression = NameConverter.decapitalize(NameConverter.decapitalize(node.getName()) + ".setStarts(new "
						+ TinkerBehaviorUtil.tinkerCollectionObjectTokenInteratorPathName.getLast() + "<" + OJUtil.classifierPathname(((INakedObjectNode) node).getNakedBaseType()) + ">(\""
						+ node.getName() + "\",");
				activityClass.addToImports(TinkerBehaviorUtil.tinkerCollectionObjectTokenInteratorPathName);
				if (node.getActivity().getSpecification() == null) {
					expression += map.getter() + "()))";
				} else {
					expression += "_";
					expression += map.fieldname() + "))";
				}
				statement1.setExpression(expression);
			} else {
				if (node.getActivity().getSpecification() == null) {
					statement1.setExpression(NameConverter.decapitalize(NameConverter.decapitalize(node.getName()) + ".setStarts(new "
							+ TinkerBehaviorUtil.tinkerSingleObjectTokenInteratorPathName.getLast() + "<" + OJUtil.classifierPathname(((INakedObjectNode) node).getNakedBaseType())
							+ ">(\"" + node.getName() + "\", Arrays.asList(" + map.getter() + "()).iterator()))"));
				} else {
					statement1.setExpression(NameConverter.decapitalize(NameConverter.decapitalize(node.getName()) + ".setStarts(new "
							+ TinkerBehaviorUtil.tinkerSingleObjectTokenInteratorPathName.getLast() + "<" + OJUtil.classifierPathname(((INakedObjectNode) node).getNakedBaseType())
							+ ">(\"" + node.getName() + "\", Arrays.asList(_" + map.fieldname() + ").iterator()))"));
				}
				activityClass.addToImports(TinkerBehaviorUtil.tinkerSingleObjectTokenInteratorPathName);
			}
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

	private void createActivityGraph(OJAnnotatedClass activityClass, OJBlock block, INakedActivityNode initNode, List<INakedAction> actions, List<String> instantiatedClasses) {
		walkActivityForNodes(instantiatedClasses, activityClass, block, initNode, actions);
		walkActivityForEdges(instantiatedClasses, block, initNode);
	}

	private void addPinEdgesToActions(List<String> instantiatedClasses, List<INakedAction> actions, OJBlock block) {
		for (INakedAction node : actions) {
			Collection<INakedInputPin> inputs = ((INakedAction) node).getInput();
			for (INakedInputPin inputPin : inputs) {
				if ((node instanceof INakedSendSignalAction && ((INakedSendSignalAction) node).getTarget() == inputPin) || inputPin instanceof INakedValuePin) {
					continue;
				}
				addPinEdge(instantiatedClasses, node, inputPin, block);
			}
			for (INakedOutputPin outputPin : ((INakedAction) node).getOutput()) {
				addPinEdge(instantiatedClasses, node, outputPin, block);
			}
		}
	}

	private void walkActivityForEdges(List<String> instantiatedClasses, OJBlock block, INakedActivityNode node) {
		addEdgeToInitNode(instantiatedClasses, node, block);
		for (INakedActivityEdge edge : node.getOutgoing()) {
			addEdge(instantiatedClasses, edge, block);
			walkActivityForEdges(instantiatedClasses, block, edge.getTarget());
		}
		if (node instanceof INakedAction) {
			for (INakedOutputPin outputPin : ((INakedAction) node).getOutput()) {
				walkActivityForEdges(instantiatedClasses, block, outputPin);
			}
		}
		if (node instanceof INakedInputPin) {
			INakedInputPin inputPin = (INakedInputPin) node;
			walkActivityForEdges(instantiatedClasses, block, inputPin.getAction());
		}
	}

	private void walkActivityForNodes(List<String> instantiatedClasses, OJAnnotatedClass activityClass, OJBlock block, INakedActivityNode node, List<INakedAction> actions) {
		instantiateNode(instantiatedClasses, activityClass, node, block);
		for (INakedActivityEdge edge : node.getOutgoing()) {
			walkActivityForNodes(instantiatedClasses, activityClass, block, edge.getTarget(), actions);
		}
		if (node instanceof INakedAction) {
			INakedAction action = (INakedAction) node;
			actions.add(action);
			for (INakedOutputPin outputPin : action.getOutput()) {
				walkActivityForNodes(instantiatedClasses, activityClass, block, outputPin, actions);
			}
		} else if (node instanceof INakedInputPin) {
			INakedAction action = ((INakedInputPin) node).getAction();
			walkActivityForNodes(instantiatedClasses, activityClass, block, action, actions);
		}
	}

	private void addPinEdge(List<String> instantiatedClasses, INakedActivityNode node, INakedPin pin, OJBlock block) {
		String edgeName = NameConverter.decapitalize(TinkerBehaviorUtil.activityNodePathName(node).getLast()) + TinkerBehaviorUtil.activityNodePathName(pin).getLast() + "Edge";
		if (!instantiatedClasses.contains(edgeName)) {
			instantiatedClasses.add(edgeName);
			OJSimpleStatement addEdge1 = new OJSimpleStatement();
			addEdge1.setExpression("Edge " + edgeName + " = GraphDb.getDb().addEdge(null, " + NameConverter.decapitalize(TinkerBehaviorUtil.activityNodePathName(node).getLast())
					+ ".getVertex(), " + NameConverter.decapitalize(TinkerBehaviorUtil.activityNodePathName(pin).getLast()) + ".getVertex(), \""
					+ TinkerBehaviorUtil.pinActionEdgeName(pin) + "\")");
			block.addToStatements(addEdge1);

			OJSimpleStatement addEdge2 = new OJSimpleStatement();
			addEdge2.setExpression(edgeName + ".setProperty(\"inClass\", " + NameConverter.decapitalize(TinkerBehaviorUtil.activityNodePathName(pin).getLast())
					+ ".getClass().getName())");
			block.addToStatements(addEdge2);

			OJSimpleStatement addEdge3 = new OJSimpleStatement();
			addEdge3.setExpression(edgeName + ".setProperty(\"outClass\", " + NameConverter.decapitalize(TinkerBehaviorUtil.activityNodePathName(node).getLast())
					+ ".getClass().getName())");
			block.addToStatements(addEdge3);
		}
	}

	private void addEdge(List<String> instantiatedClasses, INakedActivityEdge edge, OJBlock block) {
		if (!instantiatedClasses.contains(TinkerBehaviorUtil.activityEdgePathName(edge).getLast())) {
			instantiatedClasses.add(TinkerBehaviorUtil.activityEdgePathName(edge).getLast());
			OJSimpleStatement addEdge1 = new OJSimpleStatement();
			addEdge1.setExpression("Edge " + NameConverter.decapitalize(TinkerBehaviorUtil.activityEdgePathName(edge).getLast()) + " = GraphDb.getDb().addEdge(null, "
					+ NameConverter.decapitalize(TinkerBehaviorUtil.activityNodePathName(edge.getSource()).getLast()) + ".getVertex(), "
					+ NameConverter.decapitalize(TinkerBehaviorUtil.activityNodePathName(edge.getTarget()).getLast()) + ".getVertex(), " + "\"" + edge.getName() + "\")");
			block.addToStatements(addEdge1);

			OJSimpleStatement addEdge2 = new OJSimpleStatement();
			addEdge2.setExpression(NameConverter.decapitalize(TinkerBehaviorUtil.activityEdgePathName(edge).getLast()) + ".setProperty(\"inClass\", "
					+ NameConverter.decapitalize(TinkerBehaviorUtil.activityNodePathName(edge.getTarget()).getLast()) + ".getClass().getName())");
			block.addToStatements(addEdge2);

			OJSimpleStatement addEdge3 = new OJSimpleStatement();
			addEdge3.setExpression(NameConverter.decapitalize(TinkerBehaviorUtil.activityEdgePathName(edge).getLast()) + ".setProperty(\"outClass\", "
					+ NameConverter.decapitalize(TinkerBehaviorUtil.activityNodePathName(edge.getSource()).getLast()) + ".getClass().getName())");
			block.addToStatements(addEdge3);
		}
	}

	private void addEdgeToInitNode(List<String> instantiatedClasses, INakedActivityNode initNode, OJBlock block) {
		if (!instantiatedClasses.contains(TinkerBehaviorUtil.activityNodePathName(initNode).getLast())) {
			instantiatedClasses.add(TinkerBehaviorUtil.activityNodePathName(initNode).getLast());
			OJSimpleStatement addEdge1 = new OJSimpleStatement();
			addEdge1.setExpression("Edge "
					+ NameConverter.decapitalize(TinkerBehaviorUtil.activityNodePathName(initNode).getLast())
					+ "Edge = GraphDb.getDb().addEdge(null, "
					+ NameConverter.decapitalize("getVertex(), " + NameConverter.decapitalize(TinkerBehaviorUtil.activityNodePathName(initNode).getLast()) + ".getVertex(), "
							+ "\"" + NameConverter.decapitalize(TinkerBehaviorUtil.activityNodePathName(initNode).getLast()) + "Edge\")"));
			block.addToStatements(addEdge1);

			OJSimpleStatement addEdge2 = new OJSimpleStatement();
			addEdge2.setExpression(NameConverter.decapitalize(TinkerBehaviorUtil.activityNodePathName(initNode).getLast()) + "Edge.setProperty(\"inClass\", "
					+ "getClass().getName())");
			block.addToStatements(addEdge2);

			OJSimpleStatement addEdge3 = new OJSimpleStatement();
			addEdge3.setExpression(NameConverter.decapitalize(TinkerBehaviorUtil.activityNodePathName(initNode).getLast()) + "Edge.setProperty(\"outClass\", "
					+ NameConverter.decapitalize(TinkerBehaviorUtil.activityNodePathName(initNode).getLast()) + ".getClass().getName())");
			block.addToStatements(addEdge3);
		}
	}

	private void instantiateNode(List<String> instantiatedClasses, OJAnnotatedClass activityClass, INakedActivityNode node, OJBlock block) {
		if (!instantiatedClasses.contains(TinkerBehaviorUtil.activityNodePathName(node).toJavaString())) {
			instantiatedClasses.add(TinkerBehaviorUtil.activityNodePathName(node).toJavaString());

			addGetterToNode(activityClass, node);

			if (node instanceof INakedControlNode && ((INakedControlNode) node).getControlNodeType().isInitialNode()
					|| (node instanceof INakedAcceptEventAction && node.getIncoming().isEmpty())) {
				OJSimpleStatement instantiateNode = new OJSimpleStatement();
				instantiateNode.setExpression(TinkerBehaviorUtil.activityNodePathName(node).getLast() + " "
						+ NameConverter.decapitalize(TinkerBehaviorUtil.activityNodePathName(node).getLast()) + " = new " + TinkerBehaviorUtil.activityNodePathName(node).getLast()
						+ "(this.contextObject)");
				block.addToStatements(3, instantiateNode);
				instantiateNode = new OJSimpleStatement();
				instantiateNode.setExpression(NameConverter.decapitalize(node.getName()) + ".setNodeStatus(NodeStatus.ENABLED)");
				activityClass.addToImports(TinkerBehaviorUtil.tinkerNodeStatusPathName);
				block.addToStatements(4, instantiateNode);
			} else {
				OJSimpleStatement instantiateNode = new OJSimpleStatement();
				instantiateNode.setExpression(TinkerBehaviorUtil.activityNodePathName(node).getLast() + " "
						+ NameConverter.decapitalize(TinkerBehaviorUtil.activityNodePathName(node).getLast()) + " = new " + TinkerBehaviorUtil.activityNodePathName(node).getLast()
						+ "(this.contextObject)");
				block.addToStatements(3, instantiateNode);
			}
			activityClass.addToImports(TinkerBehaviorUtil.activityNodePathName(node));
		}
	}

	private void addGetterToNode(OJClass activityClass, INakedActivityNode node) {
		OJAnnotatedOperation getter = new OJAnnotatedOperation(TinkerBehaviorUtil.activityNodeGetter(node));
		getter.setReturnType(TinkerBehaviorUtil.activityNodePathName(node));
		getter.getBody().addToStatements(
				"return new " + TinkerBehaviorUtil.activityNodePathName(node).getLast() + "(this.vertex.getOutEdges(\""
						+ NameConverter.decapitalize(TinkerBehaviorUtil.activityNodePathName(node).getLast()) + "Edge\").iterator().next().getInVertex(), this.contextObject)");
		activityClass.addToOperations(getter);
	}

	private OJAnnotatedOperation addExecute(INakedActivity a, OJClass activityClass) {
		OJAnnotatedOperation execute = new OJAnnotatedOperation("execute");
		if (a.getReturnParameter() != null && a.getReturnParameter().isReturn()) {
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(a, a.getReturnParameter());
			execute.setReturnType(map.javaBaseTypePath());
		}
		activityClass.addToOperations(execute);
		if (a.getSpecification() != null) {
			for (INakedParameter param : a.getOwnedParameters()) {
				if (!param.isReturn()) {
					OJParameter p = new OJParameter();
					NakedStructuralFeatureMap pMap = OJUtil.buildStructuralFeatureMap(a, param);
					p.setName("_" + pMap.fieldname());

					if (pMap.isOne() && (param.getNakedBaseType() instanceof INakedSimpleType
							&& (param.getDirection() == ParameterDirectionKind.OUT || param.getDirection() == ParameterDirectionKind.INOUT))) {
						p.setType(TinkerGenerationUtil.convertToMutable(pMap.javaTypePath()));
					} else {
						p.setType(pMap.javaTypePath());
					}

					execute.addToParameters(p);
					execute.getOwner().addToImports(pMap.javaTypePath());
				}
			}
		}
		return execute;
	}
}
