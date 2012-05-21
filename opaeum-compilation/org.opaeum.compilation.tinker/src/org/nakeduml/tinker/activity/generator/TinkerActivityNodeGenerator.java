package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.activity.maps.ConcreteEmulatedClassifier;
import org.nakeduml.tinker.activity.maps.EdgeBridge;
import org.nakeduml.tinker.activity.maps.TinkerActivityNodeMapFactory;
import org.nakeduml.tinker.activity.maps.TinkerStructuralFeatureMap;
import org.nakeduml.tinker.generator.TinkerAttributeImplementor;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.nakeduml.tinker.generator.TinkerImplementNodeStep;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.activities.ControlNodeType;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedControlNode;
import org.opaeum.metamodel.activities.INakedObjectFlow;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerImplementNodeStep.class }, after = { TinkerImplementNodeStep.class })
public class TinkerActivityNodeGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = true, match = { INakedActivityNode.class })
	public void visitActivityNode(INakedActivityNode node) {
		OJPathName path = OJUtil.packagePathname(node.getNameSpace());
		OJAnnotatedClass ojClass;
		OJPackage pack = findOrCreatePackage(path);
		ojClass = new OJAnnotatedClass(TinkerBehaviorUtil.activityNodePathName(node).getLast());
		ojClass.setMyPackage(pack);
		ojClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		addActivityNodeOperations(ojClass, node);
		addInitVertexToDefaultConstructor(ojClass, node);
		super.createTextPath(ojClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
		
	}
	
	private void addActivityNodeOperations(OJClass actionClass, INakedActivityNode oa) {
		addGetOutFlows(actionClass, oa);
		addGetInFlows(actionClass, oa);
		addOutControlFlowGetters(actionClass, oa);
		addInControlFlowGetters(actionClass, oa);
		addConstructorWithVertex(actionClass);
	}

	private void addGetOutFlows(OJClass actionClass, INakedActivityNode oa) {
		OJAnnotatedOperation getOutControlFlows = new OJAnnotatedOperation("getOutgoing");
		TinkerGenerationUtil.addOverrideAnnotation(getOutControlFlows);
	
		OJPathName returnType = new OJPathName("java.util.List");
		OJPathName edgePathName;
		StringBuilder sb;
		if (oa instanceof INakedObjectNode) {
			if (((INakedObjectNode) oa).getNakedMultiplicity().isOne()) {
				edgePathName = TinkerBehaviorUtil.tinkerOneObjectFlowKnownPathName.getCopy();
			} else {
				edgePathName = TinkerBehaviorUtil.tinkerManyObjectFlowKnownPathName.getCopy();
			}
			OJPathName tokenGenericPathName = OJUtil.classifierPathname(((INakedObjectNode) oa).getNakedBaseType());
			edgePathName.addToGenerics(tokenGenericPathName);
			returnType.addToElementTypes(edgePathName);
			sb = new StringBuilder("return Arrays.<");
			sb.append(edgePathName.getLast());
			sb.append(">asList(");
		} else if (oa instanceof INakedControlNode && !((INakedControlNode) oa).getControlNodeType().isInitialNode()) {
			INakedControlNode controlNode = (INakedControlNode) oa;
			INakedClassifier objectFlowClassifier = controlNode.getOriginatingObjectNodeClassifier();
			if (objectFlowClassifier == null && controlNode.hasIncomingObjectFlow()) {
				if (controlNode.getTinkerOriginatingMultiplicity().isOne()) {
					edgePathName = TinkerBehaviorUtil.tinkerOneObjectFlowUnknownPathName.getCopy();
				} else {
					edgePathName = TinkerBehaviorUtil.tinkerManyObjectFlowUnknownPathName.getCopy();
				}
				returnType.addToElementTypes(edgePathName);
				sb = new StringBuilder("return Arrays.");
				sb.append("<" + edgePathName.getLast() + ">asList(");
			} else if (objectFlowClassifier == null && !controlNode.hasIncomingObjectFlow()) {
				edgePathName = TinkerBehaviorUtil.tinkerControlFlowPathName.getCopy();
				returnType.addToElementTypes(edgePathName);
				sb = new StringBuilder("return Arrays.<ControlFlow>asList(");
			} else {
				OJPathName objectFlowKnownTokenPathName = OJUtil.classifierPathname(objectFlowClassifier);
				if (controlNode.getTinkerOriginatingMultiplicity().isOne()) {
					edgePathName = TinkerBehaviorUtil.tinkerOneObjectFlowKnownPathName.getCopy();
				} else {
					edgePathName = TinkerBehaviorUtil.tinkerManyObjectFlowKnownPathName.getCopy();
				}
				edgePathName.addToGenerics(objectFlowKnownTokenPathName);
				returnType.addToElementTypes(edgePathName);
				sb = new StringBuilder("return Arrays.<");
				sb.append(edgePathName.getLast());
				sb.append(">asList(");
			}
		} else {
			edgePathName = TinkerBehaviorUtil.tinkerControlFlowPathName.getCopy();
			returnType.addToElementTypes(edgePathName);
			sb = new StringBuilder("return Arrays.<ControlFlow>asList(");
		}
		getOutControlFlows.setReturnType(returnType);
	
		boolean first = true;
		for (INakedActivityEdge edge : oa.getOutgoing()) {
			if (!first) {
				sb.append(", ");
			}
			first = false;
			sb.append(TinkerBehaviorUtil.edgeGetter(edge));
			sb.append("()");
		}
		sb.append(")");
		getOutControlFlows.getBody().addToStatements(sb.toString());
		actionClass.addToImports(new OJPathName("java.util.Arrays"));
		actionClass.addToImports(TinkerBehaviorUtil.tinkerActivityEdgePathName);
		actionClass.addToOperations(getOutControlFlows);
	}

	private void addGetInFlows(OJClass actionClass, INakedActivityNode oa) {
		OJAnnotatedOperation getInControlFlows = new OJAnnotatedOperation("getIncoming");
		TinkerGenerationUtil.addOverrideAnnotation(getInControlFlows);
		OJPathName returnType = new OJPathName("java.util.List");
		OJPathName returnPathName;
		OJPathName genericReturnPathName = null;
		if (oa instanceof INakedControlNode
				&& (((INakedControlNode) oa).getControlNodeType() == ControlNodeType.FLOW_FINAL_NODE || ((INakedControlNode) oa).getControlNodeType() == ControlNodeType.ACTIVITY_FINAL_NODE)) {
			returnPathName = new OJPathName(TinkerBehaviorUtil.tinkerActivityEdgePathName.getCopy().getLast());
			genericReturnPathName = new OJPathName("?");
		} else {
			if (oa instanceof INakedObjectNode) {
				if (((INakedObjectNode) oa).getNakedMultiplicity().isOne()) {
					returnPathName = TinkerBehaviorUtil.tinkerOneObjectFlowKnownPathName.getCopy();
				} else {
					returnPathName = TinkerBehaviorUtil.tinkerManyObjectFlowKnownPathName.getCopy();
				}
				genericReturnPathName = OJUtil.classifierPathname(((INakedObjectNode) oa).getNakedBaseType());
			} else if (oa instanceof INakedControlNode) {
				INakedControlNode controlNode = (INakedControlNode) oa;
				INakedClassifier nodeTokenClassifier = controlNode.getOriginatingObjectNodeClassifier();
				if (nodeTokenClassifier == null && !controlNode.hasIncomingObjectFlow()) {
					returnPathName = TinkerBehaviorUtil.tinkerControlFlowPathName.getCopy();
				} else if (nodeTokenClassifier == null && controlNode.hasIncomingObjectFlow() && controlNode.hasIncomingControlFlow()) {
					returnPathName = TinkerBehaviorUtil.tinkerActivityEdgePathNameWithToken.getCopy();
					actionClass.addToImports(TinkerBehaviorUtil.tinkerTokenPathName.getCopy());
				} else if (nodeTokenClassifier == null && controlNode.hasIncomingObjectFlow() && !controlNode.hasIncomingControlFlow()) {
					if (controlNode.getTinkerOriginatingMultiplicity().isOne()) {
						returnPathName = TinkerBehaviorUtil.tinkerOneObjectFlowUnknownPathName.getCopy();
					} else {
						returnPathName = TinkerBehaviorUtil.tinkerManyObjectFlowUnknownPathName.getCopy();
					}
				} else {
					genericReturnPathName = OJUtil.classifierPathname(nodeTokenClassifier);
					if (controlNode.getTinkerOriginatingMultiplicity().isOne()) {
						returnPathName = TinkerBehaviorUtil.tinkerOneObjectFlowKnownPathName.getCopy();
					} else {
						returnPathName = TinkerBehaviorUtil.tinkerManyObjectFlowKnownPathName.getCopy();
					}
				}
			} else {
				returnPathName = TinkerBehaviorUtil.tinkerControlFlowPathName.getCopy();
			}
		}
		returnType.addToElementTypes(returnPathName);
		getInControlFlows.setReturnType(returnType);
	
		OJPathName returnPathNameTmp = returnPathName.getCopy();
		StringBuilder sb = new StringBuilder();
		sb.append("return Arrays.<");
		if (genericReturnPathName != null) {
			returnPathName.addToGenerics(genericReturnPathName);
		}
		sb.append(returnPathName.getLast());
		sb.append(">asList(");
		boolean first = true;
		for (INakedActivityEdge edge : oa.getIncoming()) {
	
			if (!first) {
				sb.append(", ");
			}
			first = false;
			sb.append(TinkerBehaviorUtil.edgeGetter(edge));
			sb.append("()");
			if (oa instanceof INakedObjectNode) {
				INakedObjectFlow objectFlow = (INakedObjectFlow) edge;
				INakedClassifier objectTokenClassifier = objectFlow.getOriginatingObjectNodeClassifier();
				if (objectTokenClassifier == null) {
					sb.append(".<");
					sb.append(genericReturnPathName.getLast());
					sb.append(">convertToKnownObjectFlow()");
				}
			} else if (oa instanceof INakedControlNode) {
				if (edge instanceof INakedObjectFlow) {
					INakedObjectFlow objectFlow = (INakedObjectFlow) edge;
					INakedClassifier objectTokenClassifier = objectFlow.getOriginatingObjectNodeClassifier();
					if (objectTokenClassifier == null) {
						// This flow is unknown, check if the activityNode is
						// known,
						// if so convert it
						if (returnPathNameTmp.equals(TinkerBehaviorUtil.tinkerOneObjectFlowKnownPathName)
								|| returnPathNameTmp.equals(TinkerBehaviorUtil.tinkerManyObjectFlowKnownPathName)) {
							sb.append(".<");
							sb.append(genericReturnPathName.getLast());
							sb.append(">convertToUnknownObjectFlow()");
						}
					} else {
						// This flow is known, check if the activityNode is
						// unknown,
						// if so convert it
						if (returnPathNameTmp.equals(TinkerBehaviorUtil.tinkerOneObjectFlowUnknownPathName)
								|| returnPathNameTmp.equals(TinkerBehaviorUtil.tinkerManyObjectFlowUnknownPathName)) {
							sb.append(".convertToUnknownObjectFlow()");
						}
					}
				}
			}
		}
		sb.append(")");
		getInControlFlows.getBody().addToStatements(sb.toString());
		actionClass.addToImports(new OJPathName("java.util.Arrays"));
		actionClass.addToImports(TinkerBehaviorUtil.tinkerActivityEdgePathName);
		actionClass.addToOperations(getInControlFlows);
	}

	private void addOutControlFlowGetters(OJClass actionClass, INakedActivityNode oa) {
		for (INakedActivityEdge edge : oa.getOutgoing()) {
			buildOutgoingControlFlowGetter(actionClass, edge);
		}
	}

	private void addInControlFlowGetters(OJClass actionClass, INakedActivityNode oa) {
		for (INakedActivityEdge edge : oa.getIncoming()) {
			buildIncomingControlFlowGetter(actionClass, edge);
		}
	}

	private OJConstructor addConstructorWithVertex(OJClass actionClass) {
		OJConstructor constructorWithEdge = new OJConstructor();
		constructorWithEdge.addParam("vertex", TinkerGenerationUtil.vertexPathName);
		constructorWithEdge.getBody().addToStatements("super(vertex)");
		actionClass.addToConstructors(constructorWithEdge);
		return constructorWithEdge;
	}

	private void buildOutgoingControlFlowGetter(OJClass actionClass, INakedActivityEdge edge) {
		
		ConcreteEmulatedClassifier sourceClassifier = new ConcreteEmulatedClassifier(edge.getSource().getNameSpace(), edge.getSource());
		TinkerStructuralFeatureMap map = TinkerActivityNodeMapFactory.get(sourceClassifier, edge.getTarget(), edge.getSource(), true);
		TinkerAttributeImplementor tinkerAttributeImplementor = new TinkerAttributeImplementor();
		tinkerAttributeImplementor.setJavaModel(this.javaModel);
		tinkerAttributeImplementor.implementAttributeFully(sourceClassifier, map);
		
		String otherAssociationName = TinkerGenerationUtil.getEdgeName(map, true);
		
		ConcreteEmulatedClassifier concreteEmulatedClassifier = new ConcreteEmulatedClassifier(edge.getSource().getNameSpace(), edge.getSource());
		NakedStructuralFeatureMap edgeMap = new NakedStructuralFeatureMap(new EdgeBridge(concreteEmulatedClassifier, edge));
		OJAnnotatedOperation flowGetter = new OJAnnotatedOperation(edgeMap.getter());
		OJPathName edgePathname = edgeMap.javaBaseTypePath();
		actionClass.addToImports(edgePathname);
		flowGetter.setReturnType(edgePathname);
		OJAnnotatedField field = new OJAnnotatedField(edgeMap.fieldname(), edgeMap.javaBaseTypePath());
		actionClass.addToFields(field);
		OJIfStatement ifNull = new OJIfStatement("this." + edgeMap.fieldname() + " == null");
		ifNull.addToThenPart("this." + edgeMap.fieldname() + " = new " + edgePathname.getLast() + "(vertex.getOutEdges(\"" + otherAssociationName
				+ "\").iterator().next(), getContextObject())");
		flowGetter.getBody().addToStatements(ifNull);
		flowGetter.getBody().addToStatements("return this." + edgeMap.fieldname());
		actionClass.addToOperations(flowGetter);
	}

	private void buildIncomingControlFlowGetter(OJClass actionClass, INakedActivityEdge edge) {
		
		ConcreteEmulatedClassifier sourceClassifier = new ConcreteEmulatedClassifier(edge.getTarget().getNameSpace(), edge.getTarget());
		TinkerStructuralFeatureMap map = TinkerActivityNodeMapFactory.get(sourceClassifier, edge.getSource(), edge.getTarget(), false);
		TinkerAttributeImplementor tinkerAttributeImplementor = new TinkerAttributeImplementor();
		tinkerAttributeImplementor.setJavaModel(this.javaModel);
		tinkerAttributeImplementor.implementAttributeFully(sourceClassifier, map);
		
		String otherAssociationName = TinkerGenerationUtil.getEdgeName(map, false);

		ConcreteEmulatedClassifier concreteEmulatedClassifier = new ConcreteEmulatedClassifier(edge.getSource().getNameSpace(), edge.getSource());
		NakedStructuralFeatureMap edgeMap = new NakedStructuralFeatureMap(new EdgeBridge(concreteEmulatedClassifier, edge));
		OJAnnotatedField flowField = new OJAnnotatedField(edgeMap.fieldname(), edgeMap.javaBaseTypePath());
		actionClass.addToFields(flowField);
		OJAnnotatedOperation flowGetter = new OJAnnotatedOperation(edgeMap.getter());
		OJPathName edgePathname = edgeMap.javaBaseTypePath();
		actionClass.addToImports(edgePathname);
		flowGetter.setReturnType(edgePathname);
		OJIfStatement ifNotNull = new OJIfStatement("this." + edgeMap.fieldname() + " == null");
		ifNotNull.addToThenPart("this." + edgeMap.fieldname() + " = new " + edgePathname.getLast() + "(vertex.getInEdges(\"" + otherAssociationName
				+ "\").iterator().next(), getContextObject())");
		flowGetter.getBody().addToStatements(ifNotNull);
		flowGetter.getBody().addToStatements("return this." + edgeMap.fieldname());
		actionClass.addToOperations(flowGetter);
	
	}

	private void addInitVertexToDefaultConstructor(OJAnnotatedClass actionClass, INakedActivityNode controlNode) {
		OJConstructor defaultConstructor = actionClass.getDefaultConstructor();
		initVertexInConstructor(actionClass, controlNode, defaultConstructor);
	}

}
