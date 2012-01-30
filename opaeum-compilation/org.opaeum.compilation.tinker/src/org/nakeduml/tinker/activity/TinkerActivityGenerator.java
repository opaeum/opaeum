package org.nakeduml.tinker.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.nakeduml.tinker.generator.TinkerImplementNodeStep;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedAcceptEventAction;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedControlNode;
import org.opaeum.name.NameConverter;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerImplementNodeStep.class, TinkerActionGenerator.class }, after = { TinkerImplementNodeStep.class })
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

		}
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

	//TODO init nodes imlemented as one at the moment, change to many
	public void visitInitNodes(INakedActivityNode node) {
		INakedActivity activity = node.getActivity();
		if (OJUtil.hasOJClass(activity)) {
			OJAnnotatedClass activityClass = findJavaClass(activity);
			addInitNode(activityClass, node);
			OJOperation init = activityClass.findOperation("init", Arrays.asList(TinkerGenerationUtil.compositionNodePathName));
			createActivityGraph(activityClass, init.getBody(), node);
			startProcessInExecute(activityClass, node);
			implementGetInitialNode(activityClass, node);
		}
	}

	private void implementGetInitialNode(OJAnnotatedClass activityClass, INakedActivityNode node) {
		OJAnnotatedOperation getInitialNode = new OJAnnotatedOperation("getInitialNode");
		getInitialNode.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		getInitialNode.getBody().addToStatements("return this." + NameConverter.decapitalize(node.getName()));
		getInitialNode.setReturnType(findJavaPath(node));
		activityClass.addToOperations(getInitialNode);
	}

	private void startProcessInExecute(OJAnnotatedClass activityClass, INakedActivityNode node) {
		OJOperation execute = activityClass.findOperation("execute", Collections.EMPTY_LIST);
		OJSimpleStatement statement1 = new OJSimpleStatement();
		statement1.setExpression(NameConverter.decapitalize(node.getName()) + ".setStarts(new SingleIterator<ControlToken>(new ControlToken(\"" + node.getName() + "\")))");
		execute.getBody().addToStatements(statement1);
		OJSimpleStatement statement2 = new OJSimpleStatement("return " + NameConverter.decapitalize(node.getName()) + ".next()");
		execute.getBody().addToStatements(statement2);
		activityClass.addToImports(TinkerBehaviorUtil.tinkerSingleIteratorPathName);
		activityClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
	}

	private void createActivityGraph(OJAnnotatedClass activityClass, OJBlock block, INakedActivityNode initNode) {
		walkActivityForNodes(new ArrayList<String>(), activityClass, block, initNode);
		walkActivityForEdges(new ArrayList<String>(), block, initNode);
	}

	private void walkActivityForNodes(List<String> instantiatedClasses, OJAnnotatedClass activityClass, OJBlock block, INakedActivityNode node) {
		instantiateNode(instantiatedClasses, activityClass, node, block);
		for (INakedActivityEdge edge : node.getOutgoing()) {
			walkActivityForNodes(instantiatedClasses, activityClass, block, edge.getTarget());
		}
	}

	private void walkActivityForEdges(List<String> instantiatedClasses, OJBlock block, INakedActivityNode node) {
		for (INakedActivityEdge edge : node.getOutgoing()) {
			addEdge(instantiatedClasses, edge, block);
			walkActivityForEdges(instantiatedClasses, block, edge.getTarget());
		}
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
			addEdge2.setExpression(NameConverter.decapitalize(edge.getName()) + ".setProperty(\"outClass\", " + NameConverter.decapitalize(edge.getTarget().getName())
					+ ".getClass().getName())");
			block.addToStatements(addEdge2);

			OJSimpleStatement addEdge3 = new OJSimpleStatement();
			addEdge3.setExpression(NameConverter.decapitalize(edge.getName()) + ".setProperty(\"outClass\", " + NameConverter.decapitalize(edge.getSource().getName())
					+ ".getClass().getName())");
			block.addToStatements(addEdge3);
		}
	}

	private void instantiateNode(List<String> instantiatedClasses, OJAnnotatedClass activityClass, INakedActivityNode node, OJBlock block) {
		if (!instantiatedClasses.contains(NameConverter.capitalize(node.getName()))) {
			instantiatedClasses.add(NameConverter.capitalize(node.getName()));
			if (node instanceof INakedControlNode && ((INakedControlNode) node).getControlNodeType().isInitialNode() || (node instanceof INakedAcceptEventAction && node.getIncoming().isEmpty())) {
				OJSimpleStatement instantiateNode = new OJSimpleStatement();
				instantiateNode.setExpression("this." + NameConverter.decapitalize(node.getName()) + " = new " + NameConverter.capitalize(node.getName()) + "(this.contextObject)");
				block.addToStatements(instantiateNode);
				instantiateNode = new OJSimpleStatement();
				instantiateNode.setExpression("this." + NameConverter.decapitalize(node.getName()) + ".setNodeStatus(NodeStatus.ENABLED)");
				activityClass.addToImports(TinkerBehaviorUtil.tinkerNodeStatusPathName);
				block.addToStatements(instantiateNode);
			} else {
				OJSimpleStatement instantiateNode = new OJSimpleStatement();
				instantiateNode.setExpression(NameConverter.capitalize(node.getName()) + " " + NameConverter.decapitalize(node.getName()) + " = new "
						+ NameConverter.capitalize(node.getName()) + "(this.contextObject)");
				block.addToStatements(instantiateNode);
			}
			activityClass.addToImports(findJavaPath(node));
		}
	}

	private void addInitNode(OJClass activityClass, INakedActivityNode node) {
		OJField initNode = new OJField();
		initNode.setName(NameConverter.decapitalize(node.getName()));
		findJavaPath(node);
		initNode.setType(findJavaPath(node));
		activityClass.addToFields(initNode);
		OJAnnotatedOperation getter = new OJAnnotatedOperation("get" + NameConverter.capitalize(node.getName()));
		getter.setReturnType(findJavaPath(node));
		getter.getBody().addToStatements("return " + NameConverter.decapitalize(node.getName()));
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
