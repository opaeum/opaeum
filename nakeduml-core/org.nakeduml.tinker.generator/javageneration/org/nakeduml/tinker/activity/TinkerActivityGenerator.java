package org.nakeduml.tinker.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedControlNode;
import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedProperty;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.name.NameConverter;
import org.nakeduml.tinker.basicjava.tinker.TinkerBehaviorUtil;

public class TinkerActivityGenerator extends AbstractJavaProducingVisitor {

	@VisitBefore(matchSubclasses = true, match = { INakedActivity.class })
	public void visitActivity(INakedActivity a) {
		if (OJUtil.hasOJClass(a)) {
			OJAnnotatedClass activityClass = findJavaClass(a);
			addExecute(activityClass);
		}
	}

	@VisitBefore(matchSubclasses = true, match = { INakedControlNode.class })
	public void visitActivity(INakedControlNode node) {
		if (node.getControlNodeType().isInitialNode()) {
			INakedActivity activity = node.getActivity();
			if (OJUtil.hasOJClass(activity)) {
				OJAnnotatedClass activityClass = findJavaClass(activity);
				addInitNode(activityClass, node);
				OJConstructor constructor = activityClass.findConstructor(OJUtil.classifierPathname((INakedClassifier) activity.getOwnerElement()));
				createActivityGraph(activityClass, constructor.getBody(), node);
				startProcessInExecute(activityClass, node);
			}
		}
	}

	private void startProcessInExecute(OJAnnotatedClass activityClass, INakedControlNode node) {
		OJOperation execute = activityClass.findOperation("execute", Collections.EMPTY_LIST);
		OJSimpleStatement statement1 = new OJSimpleStatement();
		statement1.setExpression(NameConverter.decapitalize(node.getName()) + ".setStarts(new SingleIterator<ControlToken>(new ControlToken(\""
				+ node.getName() + "\")))");
		execute.getBody().addToStatements(statement1);
		OJSimpleStatement statement2 = new OJSimpleStatement("return " + NameConverter.decapitalize(node.getName()) + ".next()");
		execute.getBody().addToStatements(statement2);
		activityClass.addToImports(TinkerBehaviorUtil.tinkerSingleIteratorPathName);
		activityClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
	}

	private void createActivityGraph(OJAnnotatedClass activityClass, OJBlock block, INakedControlNode initNode) {
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
					+ NameConverter.decapitalize(edge.getSource().getName()) + ".getVertex(), " + NameConverter.decapitalize(edge.getTarget().getName())
					+ ".getVertex(), " + "\"" + edge.getName() + "\")");
			block.addToStatements(addEdge1);

			OJSimpleStatement addEdge2 = new OJSimpleStatement();
			addEdge2.setExpression(NameConverter.decapitalize(edge.getName()) + ".setProperty(\"outClass\", "
					+ NameConverter.decapitalize(edge.getTarget().getName()) + ".getClass().getName())");
			block.addToStatements(addEdge2);

			OJSimpleStatement addEdge3 = new OJSimpleStatement();
			addEdge3.setExpression(NameConverter.decapitalize(edge.getName()) + ".setProperty(\"outClass\", "
					+ NameConverter.decapitalize(edge.getSource().getName()) + ".getClass().getName())");
			block.addToStatements(addEdge3);
		}
	}

	private void instantiateNode(List<String> instantiatedClasses, OJAnnotatedClass activityClass, INakedActivityNode node, OJBlock block) {
		if (!instantiatedClasses.contains(NameConverter.capitalize(node.getName()))) {
			instantiatedClasses.add(NameConverter.capitalize(node.getName()));
			OJSimpleStatement instantiateNode = new OJSimpleStatement();
			INakedProperty endToComposite = ((ICompositionParticipant) node.getActivity().getContext()).getEndToComposite().getOtherEnd();
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(endToComposite);
			if (node instanceof INakedControlNode && ((INakedControlNode) node).getControlNodeType().isInitialNode()) {
				instantiateNode.setExpression(NameConverter.decapitalize(node.getName()) + " = new " + NameConverter.capitalize(node.getName()) + "(this."
						+ map.umlName() + ")");
			} else {
				instantiateNode.setExpression(NameConverter.capitalize(node.getName()) + " " + NameConverter.decapitalize(node.getName()) + " = new "
						+ NameConverter.capitalize(node.getName()) + "(this." + map.umlName() + ")");
			}
			activityClass.addToImports(findJavaPath(node));
			block.addToStatements(instantiateNode);
		}
	}

	private void addInitNode(OJClass activityClass, INakedControlNode node) {
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
