package net.sf.nakeduml.javageneration.jbpm5.activity;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedOperationMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.jbpm5.AbstractBehaviorVisitor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationAttributeValue;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.javametamodel.annotation.OJEnumValue;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedOpaqueBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.PreAndPostConstrained;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;

public class TaskExecutionImplementor extends AbstractBehaviorVisitor {
	@VisitBefore(matchSubclasses = true)
	public void visitOpaqueBehavior(INakedOpaqueBehavior c) {
		if (hasOJClass(c)) {
			OJAnnotatedClass ojBehavior = findJavaClass(c);
			INakedOpaqueBehavior ob = (INakedOpaqueBehavior) c;
			if (ob.getContext() != null) {
				implementExecute(ojBehavior, ob);
				implementSpecificationOrStartClassifierBehaviour(ob);
				super.addContextFieldAndConstructor(ojBehavior, ob, ob.getContext());
			}
			addGetName(c, ojBehavior);
		}
	}

	@VisitBefore
	public void visitOpaqueAction(INakedOpaqueAction oa) {
		INakedMessageStructure message = oa.getMessageStructure();
		OJAnnotatedClass ojClass = findJavaClass(message);
		implementExecute(oa,ojClass);
		super.addContextFieldAndConstructor(ojClass, message, oa.getActivity());
		addGetName(oa, ojClass);
		addTaskInstance(ojClass);
		addCompleteMethod(oa, ojClass);
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(oa.getActivity(), oa.getTargetElement());
		OJAnnotatedField user=OJUtil.addProperty(ojClass, "user", map.javaBaseTypePath(), true);
		user.putAnnotation(new OJAnnotationValue(new OJPathName(ManyToOne.class.getName())));
	}

	private void addTaskInstance(OJAnnotatedClass ojClass) {
		OJPathName type = new OJPathName("org.jbpm.taskmgmt.exe.TaskInstance");
		ojClass.addToImports(type);
		OJAnnotatedField field = OJUtil.addProperty(ojClass, "taskInstance", type, true);
		field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName(ManyToOne.class.getName())));
		OJAnnotationValue joinColumn = new OJAnnotationValue(new OJPathName(JoinColumn.class.getName()));
		joinColumn.putAttribute("name", "task_instance");
		field.addAnnotationIfNew(joinColumn);
	}

	@VisitAfter
	public void visitOperation(INakedOperation o) {
		if (BehaviorUtil.isUserResponsibility(o)) {
			super.implementRelationshipFromContextToMessage(o, findJavaClass(o.getOwner()));
			OperationMessageStructureImpl oc = new OperationMessageStructureImpl(o);
			OJAnnotatedClass ojOperationClass = findJavaClass(oc);
			implementExecute(o,ojOperationClass);
			OJAnnotatedClass ojContext = findJavaClass(o.getOwner());
			NakedOperationMap map = new NakedOperationMap(o);
			OJOperation ojOper = ojContext.findOperation(map.javaOperName(), map.javaParamTypePaths());
			// TODO implement process Invocation on Execute method
			OJPathName behaviorClass = ojOperationClass.getPathName();
			createJbpmProcess(o, ojOper);
			ojOper.getBody().addToStatements(o.getName() + " _" + o.getName() + "= new " + o.getName() + "(this)");
			ojOper.getBody().addToStatements("_" + o.getName() + ".execute()");
			ojOper.getBody().addToStatements("return _" + o.getName());
			ojOper.setReturnType(behaviorClass);
			super.addContextFieldAndConstructor(ojOperationClass, oc, o.getOwner());
			addGetName(o, ojOperationClass);
			addTaskInstance(ojOperationClass);
			addCompleteMethod(o, ojOperationClass);
		}
	}

	private void addCompleteMethod(PreAndPostConstrained constrained, OJAnnotatedClass ojOperationClass) {
		OJAnnotatedOperation complete = new OJAnnotatedOperation();
		ojOperationClass.addToOperations(complete);
		complete.setName("complete");
		if (constrained.getPostConditions().size() > 0) {
			complete.getBody().addToStatements("evaluatePostConditions()");
			OJUtil.addFailedConstraints(complete);
		}
		complete.getBody().addToStatements("taskInstance.end()");
	}

	private OJOperation implementExecute(PreAndPostConstrained element, OJAnnotatedClass ojClass) {
		OJOperation execute = new OJAnnotatedOperation();
		execute.setReturnType(new OJPathName("org.jbpm.taskmgmt.exe.TaskInstance"));
		execute.setName("execute");
		ojClass.addToOperations(execute);
		if(element instanceof INakedOperation && element.getPreConditions().size()>0){
			OJUtil.addFailedConstraints(execute);
			execute.getBody().addToStatements("evaluatePreConditions()");
		}
		// add executedOn property for sorting purposes
		OJUtil.addProperty(ojClass, "executedOn", new OJPathName(Date.class.getName()), true);
		OJAnnotatedField f = (OJAnnotatedField) ojClass.findField("executedOn");
		OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
		column.putAttribute(new OJAnnotationAttributeValue("name", "executed_on"));
		f.putAnnotation(column);
		OJAnnotationValue temporal = new OJAnnotationValue(new OJPathName("javax.persistence.Temporal"), new OJEnumValue(new OJPathName(
				"javax.persistence.TemporalType"), "TIMESTAMP"));
		f.putAnnotation(temporal);
		execute.getBody().addToStatements("setExecutedOn(new Date())");
		OJBlock block = execute.getBody();
		block.addToStatements("Token currentToken=ExecutionContext.currentExecutionContext().getToken()");
		block.addToStatements("TaskNode node=(TaskNode)currentToken.getNode()");
		block.addToStatements("Task task=(Task)node.getTasks().iterator().next()");
		block.addToStatements("TaskInstance taskInstance = currentToken.getProcessInstance().getTaskMgmtInstance().createTaskInstance(task, currentToken)");
		block.addToStatements("this.setTaskInstance(taskInstance)");
		block.addToStatements("taskInstance.create()");
		block.addToStatements("taskInstance.setVariableLocally(\"taskObject\",this)");
		block.addToStatements("taskInstance.setBlocking(true)");
		block.addToStatements("taskInstance.setSignalling(true)");
		block.addToStatements("return taskInstance");
		ojClass.addToImports("org.jbpm.graph.exe.ExecutionContext");
		ojClass.addToImports("org.jbpm.graph.exe.Token");
		ojClass.addToImports("org.jbpm.graph.node.TaskNode");
		ojClass.addToImports("org.jbpm.taskmgmt.def.Task");
		return execute;
	}

	private void addGetName(INakedElement c, OJAnnotatedClass ojOperationClass) {
		OJOperation getName = OJUtil.findOperation(ojOperationClass, "getName");
		if (getName == null) {
			getName = new OJAnnotatedOperation();
			getName.setName("getName");
			getName.setReturnType(new OJPathName("String"));
			ojOperationClass.addToOperations(getName);
		} else {
			getName.setBody(new OJBlock());
		}
		getName.getBody().addToStatements("return \"" + c.getName() + "On\"+getContextObject().getName()");
	}

	@VisitBefore(matchSubclasses = true)
	public void visitCallAction(INakedCallAction ca) {
		// Always order invocations of processes or tasks by the executedOn date
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(ca, getOclEngine().getOclLibrary());
		if (BehaviorUtil.isTaskOrProcess(ca) && map.isMany()) {
			OJAnnotatedClass ojOwner = findJavaClass(ca.getActivity());
			OJAnnotatedField field = (OJAnnotatedField) ojOwner.findField(map.umlName());
			field.putAnnotation(new OJAnnotationValue(new OJPathName("javax.persistence.OrderBy"), "executedOn"));
		}
	}
}