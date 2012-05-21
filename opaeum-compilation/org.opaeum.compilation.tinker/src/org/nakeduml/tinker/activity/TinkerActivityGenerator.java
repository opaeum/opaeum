package org.nakeduml.tinker.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import nl.klasse.octopus.model.ParameterDirectionKind;

import org.nakeduml.tinker.activity.generator.TinkerActivityNodeExGenerator;
import org.nakeduml.tinker.activity.maps.ConcreteEmulatedClassifier;
import org.nakeduml.tinker.activity.maps.TinkerActivityNodeMapFactory;
import org.nakeduml.tinker.activity.maps.TinkerStructuralFeatureMap;
import org.nakeduml.tinker.generator.TinkerAttributeImplementor;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.nakeduml.tinker.generator.TinkerImplementNodeStep;
import org.nakeduml.tinker.javageneration.composition.TinkerComponentInitializer;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJIfStatement;
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
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.INakedProperty;
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
			addExecuteValidation(a, execute);

			List<INakedAction> actions = new ArrayList<INakedAction>();
			List<String> instantiatedClasses = new ArrayList<String>();
			List<INakedProperty> instantiatedProperties = new ArrayList<INakedProperty>();
			List<INakedActivityNode> initNodes = getInitNodes(a);
			for (INakedActivityNode initNode : initNodes) {
				visitNodes(initNode, execute, actions, instantiatedClasses, instantiatedProperties);
				startProcessInExecute(activityClass, initNode, execute);
			}
			implementGetInitialNode(activityClass, initNodes);
			completeExecute(a, execute);
			implementIsFinished(activityClass, a);
			activityClass.addToImports(new OJPathName("java.util.Arrays"));
		}
	}

	private void completeExecute(INakedActivity a, OJAnnotatedOperation execute) {
		OJIfStatement ifFinished = new OJIfStatement("isFinished()");
		for (INakedParameter param : a.getOwnedParameters()) {
			if ((param.getDirection() == ParameterDirectionKind.OUT || param.getDirection() == ParameterDirectionKind.INOUT) && !param.isReturn()) {
				if (param.getNakedMultiplicity().isOne()) {
					List<INakedParameterNode> outAndInOutParamNodes = getOutAndInOutParameterNodes(a);
					for (INakedParameterNode iNakedParameterNode : outAndInOutParamNodes) {
						if (iNakedParameterNode.getParameter().equals(param)) {
							NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(a, iNakedParameterNode.getParameter());

							if (param.getNakedBaseType() instanceof INakedSimpleType) {
								execute.getBody().addToStatements("_" + map.fieldname() + "." + TinkerGenerationUtil.clearMutable(map));
								String str = TinkerBehaviorUtil.activityNodeGetter(iNakedParameterNode) + "().getReturnParameterValues().get(0)";
								ifFinished.addToThenPart("_" + map.fieldname() + "." + TinkerGenerationUtil.setMutable(map) + "(" + str + ")");
							} else {
								String str = TinkerBehaviorUtil.activityNodeGetter(iNakedParameterNode) + "().getReturnParameterValues().get(0).getVertex()";
								ifFinished.addToThenPart("_" + map.fieldname() + ".setVertex(" + str + ")");
							}
							continue;
						}
					}
				} else {
					List<INakedParameterNode> outParamNodes = getOutParameterNodes(a);
					for (INakedParameterNode iNakedParameterNode : outParamNodes) {
						if (iNakedParameterNode.getParameter().equals(param)) {
							// TODO Simple types
							NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(a, iNakedParameterNode.getParameter());
							ifFinished.addToThenPart("_" + map.fieldname() + ".clear()");
							ifFinished.addToThenPart("_" + map.fieldname() + ".addAll(" + TinkerBehaviorUtil.activityNodeGetter(iNakedParameterNode)
									+ "().getReturnParameterValues())");
							continue;
						}
					}

				}
			}
		}

		if (a.getReturnParameter() != null && a.getReturnParameter().isReturn()) {
			INakedParameterNode returnParameterNode = getReturnParameterNodes(a);
			if (a.getReturnParameter().getNakedMultiplicity().isOne()) {
				ifFinished.addToThenPart("return " + TinkerBehaviorUtil.activityNodeGetter(returnParameterNode) + "().getReturnParameterValues().get(0)");
				ifFinished.addToElsePart("return null");
			} else {
				ifFinished.addToThenPart("return " + TinkerBehaviorUtil.activityNodeGetter(returnParameterNode) + "().getReturnParameterValues()");
				ifFinished.addToElsePart("return Collections.emptyList()");
			}
		}
		execute.getBody().addToStatements(ifFinished);
	}

	private void addExecuteValidation(INakedActivity a, OJAnnotatedOperation execute) {
		// Add validation for out parameters, may not have a vertex
		for (INakedParameter param : a.getOwnedParameters()) {
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(a, param);
			if (param.getDirection() == ParameterDirectionKind.OUT) {
				// Return parameter can not have a validation check
				if (!param.isReturn()) {
					if (param.getNakedBaseType() instanceof INakedSimpleType) {
						OJIfStatement ifHasVertex = new OJIfStatement("_" + TinkerGenerationUtil.validateMutableCondition(map));
						ifHasVertex.addToThenPart("throw new IllegalStateException(\"Out parameter, " + param.getName() + " may not have a value!\")");
						execute.getBody().addToStatements(ifHasVertex);
					} else {
						if (map.isOne()) {
							OJIfStatement ifHasVertex = new OJIfStatement("_" + map.fieldname() + ".getVertex() != null");
							ifHasVertex.addToThenPart("throw new IllegalStateException(\"Out parameter, " + param.getName() + " may not have a vertex!\")");
							execute.getBody().addToStatements(ifHasVertex);
						} else {
							OJIfStatement ifHasVertex = new OJIfStatement("!" + "_" + map.fieldname() + ".isEmpty()");
							ifHasVertex.addToThenPart("throw new IllegalStateException(\"Out parameter, " + param.getName() + " must be an empty collection!\")");
							execute.getBody().addToStatements(ifHasVertex);
						}
					}
				}

			}
		}
		// Add validation for in & inout parameters, must have a vertex
		for (INakedParameter param : a.getOwnedParameters()) {
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(a, param);
			if (param.getDirection() == ParameterDirectionKind.INOUT || param.getDirection() == ParameterDirectionKind.IN) {
				INakedProperty prop = map.getProperty();
				if (!(prop.getBaseType() instanceof INakedSimpleType || prop.getBaseType() instanceof INakedEnumeration)) {
					if (map.isOne()) {
						OJIfStatement ifHasVertex = new OJIfStatement("_" + map.fieldname() + ".getVertex() == null");
						ifHasVertex.addToThenPart("throw new IllegalStateException(\" " + (param.getDirection() == ParameterDirectionKind.INOUT ? "INOUT" : "IN")
								+ " token on inputpin, " + param.getName() + " must have a vertex!\")");
						execute.getBody().addToStatements(ifHasVertex);
					} else {
						OJIfStatement ifHasVertex = new OJIfStatement("!" + "_" + map.fieldname() + ".isEmpty()");
						ifHasVertex.addToThenPart("throw new IllegalStateException(\"Out parameter, " + param.getName() + " must be an empty collection!\")");
						execute.getBody().addToStatements(ifHasVertex);
					}
				}

			}
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

	private void visitNodes(INakedActivityNode node, OJAnnotatedOperation execute, List<INakedAction> actions, List<String> instantiatedClasses,
			List<INakedProperty> instantiatedProperties) {
		INakedActivity activity = node.getActivity();
		OJAnnotatedClass activityClass = findJavaClass(activity);
		OJOperation init = activityClass.findOperation("init", Arrays.asList(TinkerGenerationUtil.compositionNodePathName));
		createActivityGraph(activityClass, init.getBody(), node, actions, instantiatedClasses, instantiatedProperties);
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
						+ TinkerBehaviorUtil.tinkerCollectionObjectTokenInteratorPathName.getLast() + "<" + OJUtil.classifierPathname(((INakedObjectNode) node).getNakedBaseType())
						+ ">(\"" + node.getName() + "\",");
				activityClass.addToImports(TinkerBehaviorUtil.tinkerCollectionObjectTokenInteratorPathName);
				// if (node.getActivity().getSpecification() == null) {
				// expression += map.getter() + "()))";
				// } else {
				expression += "_";
				expression += map.fieldname() + "))";
				// }
				statement1.setExpression(expression);
			} else {
				// if (node.getActivity().getSpecification() == null) {
				// statement1.setExpression(NameConverter.decapitalize(NameConverter.decapitalize(node.getName())
				// + ".setStarts(new "
				// +
				// TinkerBehaviorUtil.tinkerSingleObjectTokenInteratorPathName.getLast()
				// + "<" + OJUtil.classifierPathname(((INakedObjectNode)
				// node).getNakedBaseType())
				// + ">(\"" + node.getName() + "\", Arrays.asList(" +
				// map.getter() + "()).iterator()))"));
				// } else {
				statement1.setExpression(NameConverter.decapitalize(NameConverter.decapitalize(node.getName()) + ".setStarts(new "
						+ TinkerBehaviorUtil.tinkerSingleObjectTokenInteratorPathName.getLast() + "<" + OJUtil.classifierPathname(((INakedObjectNode) node).getNakedBaseType())
						+ ">(\"" + node.getName() + "\", Arrays.asList(_" + map.fieldname() + ").iterator()))"));
				// }
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
		OJSimpleStatement statement2 = new OJSimpleStatement("boolean " + NameConverter.decapitalize(node.getName()) + "Result = " + NameConverter.decapitalize(node.getName())
				+ ".next()");
		execute.getBody().addToStatements(statement2);
		activityClass.addToImports(TinkerBehaviorUtil.tinkerSingleIteratorPathName);
		activityClass.addToImports(TinkerBehaviorUtil.tinkerTokenPathName);
	}

	private void createActivityGraph(OJAnnotatedClass activityClass, OJBlock block, INakedActivityNode initNode, List<INakedAction> actions, List<String> instantiatedClasses,
			List<INakedProperty> instantiatedProperties) {
		walkActivityForNodes(instantiatedClasses, instantiatedProperties, activityClass, block, initNode, actions);
		walkActivityForEdges(instantiatedClasses, block, initNode);
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
		if (node instanceof INakedInputPin) {
			INakedInputPin inputPin = (INakedInputPin) node;
			walkActivityForEdges(instantiatedClasses, block, inputPin.getAction());
		}
	}

	private void walkActivityForNodes(List<String> instantiatedClasses, List<INakedProperty> instantiatedProperties, OJAnnotatedClass activityClass, OJBlock block,
			INakedActivityNode node, List<INakedAction> actions) {
		if (!(node instanceof INakedPin)) {
			instantiateNode(instantiatedClasses, instantiatedProperties, activityClass, node, block);
		}
		for (INakedActivityEdge edge : node.getOutgoing()) {
			walkActivityForNodes(instantiatedClasses, instantiatedProperties, activityClass, block, edge.getTarget(), actions);
		}
		if (node instanceof INakedAction) {
			INakedAction action = (INakedAction) node;
			actions.add(action);
			for (INakedOutputPin outputPin : action.getOutput()) {
				walkActivityForNodes(instantiatedClasses, instantiatedProperties, activityClass, block, outputPin, actions);
			}
		} else if (node instanceof INakedInputPin) {
			INakedAction action = ((INakedInputPin) node).getAction();
			walkActivityForNodes(instantiatedClasses, instantiatedProperties, activityClass, block, action, actions);
		}
	}

	private void addEdge(List<String> instantiatedClasses, INakedActivityEdge edge, OJBlock block) {
		if (!instantiatedClasses.contains(TinkerBehaviorUtil.activityEdgePathName(edge).getLast())) {
			instantiatedClasses.add(TinkerBehaviorUtil.activityEdgePathName(edge).getLast());

			ConcreteEmulatedClassifier sourceClassifier = new ConcreteEmulatedClassifier(edge.getSource().getNameSpace(), edge.getSource());
			TinkerStructuralFeatureMap mapSource = TinkerActivityNodeMapFactory.getNodeToActivityAssociationMap(sourceClassifier, edge.getSource());
			TinkerStructuralFeatureMap mapTarget = TinkerActivityNodeMapFactory.getNodeToActivityAssociationMap(sourceClassifier, edge.getTarget());
			TinkerStructuralFeatureMap edgeMap = TinkerActivityNodeMapFactory.get(sourceClassifier, edge.getTarget(), edge.getSource(), true);

			String sourceGetter;
			if (edge.getSource() instanceof INakedPin) {
				INakedPin pin = (INakedPin) edge.getSource();
				INakedAction action = pin.getAction();
				TinkerStructuralFeatureMap mapActionSource = TinkerActivityNodeMapFactory.getNodeToActivityAssociationMap(sourceClassifier, action);
				sourceGetter = mapActionSource.getter() + "()." + mapSource.getter();
			} else {
				sourceGetter = mapSource.getter();
			}
			String targetGetter;
			if (edge.getTarget() instanceof INakedPin) {
				INakedPin pin = (INakedPin) edge.getTarget();
				INakedAction action = pin.getAction();
				TinkerStructuralFeatureMap mapActionTarget = TinkerActivityNodeMapFactory.getNodeToActivityAssociationMap(sourceClassifier, action);
				targetGetter = mapActionTarget.getter() + "()." + mapTarget.getter();
			} else {
				targetGetter = mapTarget.getter();
			}
			block.addToStatements(sourceGetter + "()." + edgeMap.setter() + "(" + targetGetter + "())");
		}
	}

	private void instantiateNode(List<String> instantiatedClasses, List<INakedProperty> instantiatedProperties, OJAnnotatedClass activityClass, INakedActivityNode node,
			OJBlock block) {
		if (!instantiatedClasses.contains(TinkerBehaviorUtil.activityNodePathName(node).toJavaString())) {
			instantiatedClasses.add(TinkerBehaviorUtil.activityNodePathName(node).toJavaString());
			addGetterToNode(activityClass, node, instantiatedProperties);
			activityClass.addToImports(TinkerBehaviorUtil.activityNodePathName(node));
		}
	}

	private void addGetterToNode(OJClass activityClass, INakedActivityNode node, List<INakedProperty> instantiatedProperties) {
		ConcreteEmulatedClassifier activityClassifier = new ConcreteEmulatedClassifier(node.getActivity().getNameSpace(), node.getActivity());
		TinkerStructuralFeatureMap map = TinkerActivityNodeMapFactory.getNodeToActivityAssociationMap(activityClassifier, node);
		TinkerAttributeImplementor tinkerAttributeImplementor = new TinkerAttributeImplementor();
		tinkerAttributeImplementor.setJavaModel(this.javaModel);
		tinkerAttributeImplementor.implementAttributeFully(activityClassifier, map);

		// Add to createComponent to constructor
		TinkerComponentInitializer tinkerComponentInitializer = new TinkerComponentInitializer();
		tinkerComponentInitializer.setJavaModel(this.javaModel);
		OJAnnotatedOperation createComponents = (OJAnnotatedOperation) activityClass.findOperation("createComponents", Collections.emptyList());
		if (createComponents == null) {
			createComponents = new OJAnnotatedOperation("createComponents");
			createComponents.setBody(new OJBlock());
			activityClass.addToOperations(createComponents);
		}
		tinkerComponentInitializer.initProperty(createComponents, map.getProperty());
	}

	private OJAnnotatedOperation addExecute(INakedActivity a, OJClass activityClass) {
		OJAnnotatedOperation execute = new OJAnnotatedOperation("execute");
		if (a.getReturnParameter() != null && a.getReturnParameter().isReturn()) {
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(a, a.getReturnParameter());
			execute.setReturnType(map.javaBaseTypePath());
		}
		activityClass.addToOperations(execute);
		for (INakedParameter param : a.getOwnedParameters()) {
			if (!param.isReturn()) {
				OJParameter p = new OJParameter();
				NakedStructuralFeatureMap pMap = OJUtil.buildStructuralFeatureMap(a, param);
				p.setName("_" + pMap.fieldname());

				if (pMap.isOne()
						&& (param.getNakedBaseType() instanceof INakedSimpleType && (param.getDirection() == ParameterDirectionKind.OUT || param.getDirection() == ParameterDirectionKind.INOUT))) {
					p.setType(TinkerGenerationUtil.convertToMutable(pMap.javaTypePath()));
				} else {
					p.setType(pMap.javaTypePath());
				}

				execute.addToParameters(p);
				execute.getOwner().addToImports(pMap.javaTypePath());
			}
		}
		return execute;
	}
}
