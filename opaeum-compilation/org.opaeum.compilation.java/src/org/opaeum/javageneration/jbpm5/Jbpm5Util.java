package org.opaeum.javageneration.jbpm5;


import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.commonbehaviors.GuardedFlow;
import org.opaeum.metamodel.commonbehaviors.INakedStep;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.name.SingularNameWrapper;
import org.opaeum.runtime.domain.ExceptionHolder;
import org.opaeum.runtime.environment.Environment;

public class Jbpm5Util{
	public static final OJPathName UML_NODE_INSTANCE = new OJPathName("org.opaeum.runtime.domain.UmlNodeInstance");
	public static OJPathName jbpmKnowledgeBase(INakedElementOwner m){
		OJPathName result = new OJPathName(m.getMappingInfo().getQualifiedJavaName());
		return result.append("util").append(m.getMappingInfo().getJavaName().getCapped() + "KnowledgeBase");
	}
	public static String stepLiteralName(INakedStep s){
		return s.getStatePath().toString().replace("::", "_").toUpperCase();
	}
	public static OJPathName getNodeInstance(){
		return new OJPathName("org.jbpm.workflow.instance.impl.NodeInstanceImpl");
	}
	public static String generateProcessName(INakedElement parameterOwner){
		return parameterOwner.getOwnerElement().getMappingInfo().getPersistentName() + "_" + parameterOwner.getMappingInfo().getPersistentName();
	}
	public static String getArtificialJoinName(INakedElement target){
		return "join_for_" + target.getMappingInfo().getPersistentName();
	}
	public static String getGuardMethod(GuardedFlow t){
		return "is" + t.getSource().getMappingInfo().getJavaName().getCapped() + t.getMappingInfo().getJavaName().getCapped();
	}

	public static String getArtificialForkName(INakedElement owner){
		return "fork_for_" + owner.getMappingInfo().getPersistentName();
	}
	public static OJPathName getExceptionHolder(){
		return new OJPathName(ExceptionHolder.class.getName());
	}
	public static String endNodeFieldNameFor(INakedElement flow){
		return "endNodeIn" + flow.getMappingInfo().getJavaName().getCapped();
	}
	public static OJPathName getWorkflowProcesInstance(){
		return new OJPathName("org.jbpm.workflow.instance.WorkflowProcessInstance");
	}
	public static OJPathName getWorkflowProcessImpl(){
		return new OJPathName("org.jbpm.workflow.core.impl.WorkflowProcessImpl");
	}
	public static OJPathName getNode(){
		return new OJPathName("org.jbpm.workflow.core.impl.NodeImpl");
	}
	public static String getArtificialChoiceName(INakedActivityNode node){
		return node.getMappingInfo().getPersistentName().getAsIs() + "_choice";
	}
	public static void implementRelationshipWithProcess(OJAnnotatedClass ojBehavior,boolean persistent,String propertyPrefix){
		OJAnnotatedField processInstanceField = OJUtil.addPersistentProperty(ojBehavior, propertyPrefix+"Instance", new OJPathName("WorkflowProcessInstance"), true);
		processInstanceField.setTransient(true);
		SingularNameWrapper name = new SingularNameWrapper(propertyPrefix, null);
		if(persistent){
			OJAnnotatedField processInstanceIdField = OJUtil.addPersistentProperty(ojBehavior, propertyPrefix + "InstanceId", new OJPathName("Long"), true);
			OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
			column.putAttribute(new OJAnnotationAttributeValue("name", name.getUnderscored() + "_instance_id"));
			processInstanceIdField.putAnnotation(column);
			processInstanceField.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
			OJOperation getter = ojBehavior.getUniqueOperation("get" + name.getCapped() + "Instance");
			ojBehavior.addToImports(getWorkflowProcesInstance());
			ojBehavior.addToImports(getWorkflowProcessImpl());
			getter.setBody(new OJBlock());
			OJIfStatement ifNull = new OJIfStatement(
					"this." + propertyPrefix + "Instance==null",
					"this."
							+ propertyPrefix
							+ "Instance=(WorkflowProcessInstance)"+Environment.class.getName()+ ".getInstance().getComponent(StatefulKnowledgeSession.class).getProcessInstance(get"+name.getCapped()+"InstanceId())");
			OJIfStatement ifNotNull = new OJIfStatement("this." + propertyPrefix + "Instance!=null", "((WorkflowProcessImpl)this." + propertyPrefix
					+ "Instance.getProcess()).setAutoComplete(true)");
			ifNull.getThenPart().addToStatements(ifNotNull);
			ojBehavior.addToImports(new OJPathName("org.drools.runtime.StatefulKnowledgeSession"));
			getter.getBody().addToStatements(ifNull);
			getter.getBody().addToStatements("return this." + propertyPrefix+"Instance");
		}
		OJOperation getProcessDefinition = new OJAnnotatedOperation("get" + name.getCapped() + "Definition");
		OJPathName processDefinition = new OJPathName("org.jbpm.workflow.core.WorkflowProcess");
		getProcessDefinition.setReturnType(processDefinition);
		ojBehavior.addToOperations(getProcessDefinition);
		getProcessDefinition.getBody().addToStatements("return (WorkflowProcess) get" + name.getCapped() + "Instance().getProcess()");

	}
	public static OJPathName getProcessContext(){
		return new OJPathName("org.drools.runtime.process.ProcessContext");
	}
}
