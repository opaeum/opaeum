package org.opaeum.javageneration.jbpm5;


import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Vertex;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfStateMachineUtil;
import org.opaeum.eclipse.PersistentNameUtil;
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
import org.opaeum.metamodel.name.SingularNameWrapper;
import org.opaeum.name.NameConverter;
import org.opaeum.runtime.domain.ExceptionHolder;
import org.opaeum.runtime.environment.Environment;

public class Jbpm5Util{
	public static final OJPathName UML_NODE_INSTANCE = new OJPathName("org.opaeum.runtime.domain.UmlNodeInstance");
	public static String stepLiteralName(NamedElement s){
		String result="";
		if(s instanceof State){
			result=EmfStateMachineUtil.getStatePath((Vertex) s);
		}else if(s instanceof ActivityNode){
			result=EmfActivityUtil.getNodePath((ActivityNode) s);
		}
		return result.replace("::", "_").toUpperCase();
	}
	public static OJPathName getNodeInstance(){
		return new OJPathName("org.jbpm.workflow.instance.impl.NodeInstanceImpl");
	}
	public static String generateProcessName(Element parameterOwner){
		return PersistentNameUtil.getPersistentName((Element) EmfElementFinder.getContainer( parameterOwner)) + "_" + PersistentNameUtil.getPersistentName( parameterOwner);
	}
	public static String getArtificialJoinName(Element target){
		return "join_for_" + PersistentNameUtil.getPersistentName(target);
	}
	public static String getGuardMethod(NamedElement source, NamedElement flow){
		return "is" + NameConverter.capitalize(source.getName()) + NameConverter.capitalize(flow.getName());
	}

	public static String getArtificialForkName(Element owner){
		return "fork_for_" + PersistentNameUtil.getPersistentName(owner);
	}
	public static OJPathName getExceptionHolder(){
		return new OJPathName(ExceptionHolder.class.getName());
	}
	public static String endNodeFieldNameFor(NamedElement flow){
		return "endNodeIn" + NameConverter.capitalize(flow.getName());
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
	public static String getArtificialChoiceName(ActivityNode node){
		return PersistentNameUtil.getPersistentName( node).getAsIs() + "_choice";
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
